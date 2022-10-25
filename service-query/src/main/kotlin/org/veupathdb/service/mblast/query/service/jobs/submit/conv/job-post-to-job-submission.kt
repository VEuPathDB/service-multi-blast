package org.veupathdb.service.mblast.query.service.jobs.submit.conv

import mblast.query.pipe.SequenceFileWriter
import mblast.query.validate.NucleotideSequenceValidationStream
import mblast.query.validate.ProteinSequenceValidationStream
import mblast.util.io.toIOStream
import org.veupathdb.lib.blast.common.fields.DBFiles
import org.veupathdb.service.mblast.query.Const
import org.veupathdb.service.mblast.query.ServiceOptions
import org.veupathdb.service.mblast.query.blast.convert.toInternal
import org.veupathdb.service.mblast.query.generated.model.BlastQueryTool
import org.veupathdb.service.mblast.query.generated.model.JobsPostMultipartFormData
import org.veupathdb.service.mblast.query.mixins.toDBFiles
import org.veupathdb.service.mblast.query.model.BlastTarget
import org.veupathdb.service.mblast.query.model.QueryUserMetaImpl
import org.veupathdb.service.mblast.query.service.JobHasher
import org.veupathdb.service.mblast.query.service.jobs.submit.model.JobSubmission
import java.io.File
import java.io.InputStream
import java.util.*


/**
 * Translates the target IO job submission type and builds the state necessary
 * to perform the job submission process.
 */
fun JobsPostMultipartFormData.toJobSubmission(userID: Long) =
  JobSubmission.build {
    // Get the project ID
    projectID = config.jobConfig.site.name

    // Get the internal blast CLI config
    blastConfigWODB = config.blastConfig.toInternal()
      // Set the constant fields on the config
      .apply {
        queryFile(Const.QueryFileName)
        outFile(Const.ReportFileName)
      }

    // Translate the IO target values to the internal type
    blastTargets = translateTargets()

    // Clone the CLI config for a version that has the DBFiles set (this is what
    // gets put into the S3 workspace).  We don't record the blast DB files in
    // the DB or use them as part of the hash as the paths to the DB files are
    // invalidated with every new site build number.
    //
    // A path generated on build-56 will never work after build-57, and so on.
    blastConfigWDB = blastConfigWODB!!.clone()
      .apply { dbFile = DBFiles(blastTargets!!.toDBFiles(projectID!!)) }

    // Does the user want to collect this job?
    addToUserJobs = config.jobConfig.addToUserCollection ?: false

    // Grab the user metadata
    userMeta = QueryUserMetaImpl(
      userID,
      if (addToUserJobs == true) config.meta?.summary else null,
      if (addToUserJobs == true) config.meta?.description else null,
    )

    // Split the query into sequence files.
    // TODO: extract this from here
    queryFiles = queryToFiles(File(Const.QueryTempDir))

    // Hash the configuration and important values to form a job ID.
    parentJobID = JobHasher.hashJob(
      blastConfigWODB!!,
      config.jobConfig.site.name,
      queryFiles!!.overallQueryFile,
      blastTargets!!
    )

    // No need to hold onto trash
    config = null
    query?.delete()
    query = null
  }

/**
 * Writes out the query from the job post to query file(s).
 */
private fun JobsPostMultipartFormData.queryToFiles(dir: File) =
  SequenceFileWriter(UUID.randomUUID().toString(), dir, getSequenceValidator())
    .also { it.run() }
    .getResult()

/**
 * Translates the IO blast-target data type into the internally used
 * blast-target data type.
 */
private fun JobsPostMultipartFormData.translateTargets() =
  config.jobConfig.targets.map { BlastTarget(it.targetDisplayName, it.targetFile) }


private fun JobsPostMultipartFormData.getSequenceValidator() =
  if (query == null)
    getSequenceValidator(config.jobConfig.query.byteInputStream())
  else
    getSequenceValidator(query.inputStream())

private fun JobsPostMultipartFormData.getSequenceValidator(query: InputStream) =
  when (config.blastConfig.tool!!) {
    BlastQueryTool.BLASTP  -> newAAValidator(query)
    BlastQueryTool.TBLASTN -> newAAValidator(query)
    else                   -> newNAValidator(query)
  }

private fun newNAValidator(query: InputStream) =
  NucleotideSequenceValidationStream(
    ServiceOptions.maxNASeqSize,
    ServiceOptions.maxQueriesPerJob,
    ServiceOptions.maxInputQuerySize,
    query.toIOStream(),
  )

private fun newAAValidator(query: InputStream) =
  ProteinSequenceValidationStream(
    ServiceOptions.maxAASeqSize,
    ServiceOptions.maxQueriesPerJob,
    ServiceOptions.maxInputQuerySize,
    query.toIOStream()
  )
