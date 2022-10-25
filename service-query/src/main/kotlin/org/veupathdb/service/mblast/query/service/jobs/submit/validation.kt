package org.veupathdb.service.mblast.query.service.jobs.submit

import jakarta.ws.rs.BadRequestException
import mblast.util.isNotNullOrEmpty
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException
import org.veupathdb.service.mblast.query.Const
import org.veupathdb.service.mblast.query.blast.validate.validate
import org.veupathdb.service.mblast.query.generated.model.*
import org.veupathdb.service.mblast.query.service.BlastTargetManager

private typealias ErrorMap = MutableMap<String, MutableList<String>>

/**
 * Static JSON Paths to the fields in a job post request.
 */
@Suppress("NOTHING_TO_INLINE")
private object JsonPath {

  const val JOB_CONFIG   = "jobConfig"
  const val BLAST_CONFIG = "blastConfig"
  const val META         = "meta"

  object JobConfig {
    const val SITE                   = "$JOB_CONFIG.site"
    const val TARGETS                = "$JOB_CONFIG.targets"
    const val QUERY                  = "$JOB_CONFIG.query"
    const val ADD_TO_USER_COLLECTION = "$JOB_CONFIG.addToUserCollection"

    inline fun TARGET_I(i: Int) = "$TARGETS[$i]"

    object Targets {
      inline fun TARGET_DISPLAY_NAME(i: Int) = "${TARGET_I(i)}.targetDisplayName"
      inline fun TARGET_FILE(i: Int) = "${TARGET_I(i)}.targetFile"
    }
  }

  object Meta {
    const val SUMMARY = "$META.summary"
  }
}


private const val IS_REQUIRED = "this field is required"


/**
 * Validates the target multipart job request body.
 *
 * @receiver The request body to validate.
 *
 * @throws BadRequestException If any of the following are true:
 * * The request is missing all configuration.
 * * The request has 2 queries.
 * * The request has 0 queries.
 *
 * @throws UnprocessableEntityException If the request contains a config object
 * but the config object contains invalid options or option combinations.
 */
fun JobsPostMultipartFormData.validate() {
  if (config == null)
    throw BadRequestException("Request contained no job configuration")

  val errors = HashMap<String, MutableList<String>>(0)

  config.validate(errors)

  if (errors.isNotEmpty())
    throw UnprocessableEntityException(errors)

  if (config.jobConfig.query.isNullOrEmpty() && query == null)
    throw BadRequestException("Request contained no BLAST query")
  if (config.jobConfig.query.isNotNullOrEmpty() && query != null)
    throw BadRequestException("Request contained both a query upload and query string (${JsonPath.JobConfig.QUERY})")
}


/**
 * Validates the `config` section of a multiblast job request.
 *
 * This function validates that the request contains both a job config and a
 * blast config and validates the options for both of those fields.
 *
 * Optionally, if the request contains a user meta field, this method will
 * validate that as well.
 *
 * @receiver Config block to validate.
 *
 * @param errors Error map that validation errors will be appended to.
 */
private fun QueryJobPostRequest.validate(errors: ErrorMap) {
  if (blastConfig == null)
    errors.addError(JsonPath.BLAST_CONFIG, IS_REQUIRED)
  else
    blastConfig.validate(errors)

  if (jobConfig == null)
    errors.addError(JsonPath.JOB_CONFIG, IS_REQUIRED)
  else
    jobConfig.validate(errors)

  meta?.validate(errors)
}


/**
 * Validates the job config portion of the job request.
 *
 * @receiver The job config block to validate.
 *
 * @param errors Error map that validation errors will be appended to.
 */
private fun QueryJobConfig.validate(errors: ErrorMap) {
  if (site == null)
    errors.addError(JsonPath.JobConfig.SITE, IS_REQUIRED)

  if (targets.isNullOrEmpty())
    errors.addError(JsonPath.JobConfig.TARGETS, "one or more targets are required")
  else
    targets.validate(site, errors)
}


/**
 * Validates the user metadata portion of a job request.
 *
 * @receiver The user meta block to validate.
 *
 * @param errors Error map that validation errors will be appended to.
 */
private fun QueryJobUserMeta.validate(errors: ErrorMap) {
  if (summary.isNotNullOrEmpty() && summary.length > Const.MaxSummaryLength)
    errors.addError(JsonPath.Meta.SUMMARY, "must not be longer than ${Const.MaxSummaryLength} characters")
}


/**
 * Validates a list of [QueryJobTarget] instances.
 *
 * @receiver The list of job target configs to validate.
 *
 * @param site The target site for the job (required to validate the existence
 * of the targets).
 *
 * @param errors Error map that validation errors will be appended to.
 */
private fun List<QueryJobTarget?>.validate(site: TargetSite, errors: ErrorMap) =
  forEachIndexed { i, it ->
    if (it == null)
      errors.addError(JsonPath.JobConfig.TARGET_I(i), "must not be null")
    else
      it.validate(i, site, errors)
  }


/**
 * Validates a query job target to ensure that it has both required components
 * and points to a real target that exists on the mounted filesystem.
 *
 * @receiver Query job target to validate.
 *
 * @param i Index of the current query job target being validated in the posted
 * list of query job targets.  This is used to form error message JSON paths.
 *
 * @param site Target site for the job (used to validate the existence of the
 * target BLAST database files).
 *
 * @param errors Error map that validation errors will be appended to.
 */
private fun QueryJobTarget.validate(i: Int, site: TargetSite, errors: ErrorMap) {
  // If we have both values
  if (targetDisplayName.isNotNullOrEmpty() && targetFile.isNotNullOrEmpty()) {
    // Validate the DB path
    if (!BlastTargetManager.dbExists(site.name, targetDisplayName, targetFile)) {
      errors.addError(JsonPath.JobConfig.TARGET_I(i), "unrecognized target")
    }
  }

  // Else, if we don't have both values, figure out which are missing and report
  // them.
  else {
    if (targetDisplayName.isNullOrEmpty())
      errors.addError(JsonPath.JobConfig.Targets.TARGET_DISPLAY_NAME(i), IS_REQUIRED)

    if (targetFile.isNullOrEmpty())
      errors.addError(JsonPath.JobConfig.Targets.TARGET_FILE(i), IS_REQUIRED)
  }
}


/**
 * Appends an error to the error map, creating a wrapping list if needed.
 *
 * @receiver Error map that the error will be appended to.
 *
 * @param key JSON Path to the field that is in error.
 *
 * @param error Error message relating to the given JSON path.
 */
@Suppress("NOTHING_TO_INLINE")
private inline fun ErrorMap.addError(key: String, error: String) =
  computeIfAbsent(key, { ArrayList(1) }).add(error)
