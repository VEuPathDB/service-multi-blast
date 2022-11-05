package e2e.test1

import e2e.conf.E2EConfig
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.MultiBlast
import org.veupathdb.lib.mblast.sdk.MultiBlastClient
import org.veupathdb.lib.mblast.sdk.query.blast.BlastQueryTool
import org.veupathdb.lib.mblast.sdk.common.model.JobStatus
import org.veupathdb.lib.mblast.sdk.query.model.QueryJobPatchRequest
import org.veupathdb.lib.mblast.sdk.query.model.QueryJobTarget
import org.veupathdb.lib.mblast.sdk.query.model.QueryJobUserMeta
import org.veupathdb.lib.mblast.sdk.query.model.TargetSite
import org.veupathdb.lib.mblast.sdk.report.blast.BlastFormatConfig
import org.veupathdb.lib.mblast.sdk.report.blast.BlastOutFormat
import java.util.*

private const val Query1   = "catacatacat"

private data class BlastTarget(val site: TargetSite, val target: String, val db: String)

fun RunEndToEndTest1(config: E2EConfig) {
  println("Running end to end test 1")

  val client = MultiBlast.newClient {
    url(config.serviceHost)
    port(config.servicePort)
    authKey(config.authToken)
  }

  println("  selecting blast target")
  val target = client.selectBlastNTarget()

  // Create a query job
  println("  creating query job")
  val job1ID = client.createQueryJob1(target)

  // Verify the query job exists in the user's job list
  println("  checking that new query job $job1ID exists in user's job list")
  client.verifyQueryJobInJobList(job1ID)

  // Go to the job directly and verify the config
  println("  verifying job configuration aligns with what was sent")
  client.verifyQueryJob1Config(job1ID, target)

  // Resubmit the same job and verify the ID is the same
  println("  attempt to recreate the same query job expecting the same hash ID")
  require(job1ID == client.createQueryJob1(target))

  // Fetch the query for the job and ensure it resembles the input query
  println("  verifying the query")
  require(Query1 == client.getJobQuery(job1ID))

  // Wait for the job to complete (if it hasn't already)
  println("  waiting for the query job to be completed")
  client.waitForQueryJob(job1ID)

  // Fetch the job result
  println("  checking the query job result")
  require(client.getQueryJobResult(job1ID).isNotBlank())

  // Delete the job from the user's collection
  println("  deleting the query job from the user's collection")
  client.query.deleteJob(job1ID)

  // list the user's jobs and ensure the target job no longer appears
  println("  verifying that $job1ID is not in the user's collection")
  client.verifyQueryJobNotInJobList(job1ID)

  // Get the job details with saveJob = false
  println("  looking up job $job1ID with save_job=false")
  client.query.getJob(job1ID, false)

  // list the user's jobs and ensure the target job still doesn't appear
  println("  verifying that $job1ID is not in the user's collection")
  client.verifyQueryJobNotInJobList(job1ID)

  // Get the job details with save_job = true
  println("  looking up job $job1ID with save_job=true")
  client.query.getJob(job1ID, true)

  // list the user's jobs and ensure the target job appears again
  println("  checking that new query job $job1ID exists in user's job list")
  client.verifyQueryJobInJobList(job1ID)

  // bulk status check
  println("  running bulk status check")
  require(client.query.getBulkJobStatuses(listOf(job1ID)).contains(job1ID))

  // patch the job summary
  println("  patching job summary")
  val newSummary = UUID.randomUUID().toString()
  client.query.patchJob(job1ID, QueryJobPatchRequest(QueryJobUserMeta(summary = newSummary, description = null)))

  // verify the new job summary
  println("  verifying patched summary")
  require(client.query.getJob(job1ID)!!.meta!!.summary == newSummary)

  // Create a report job for the created query job
  println("  creating a report job for $job1ID")
  val rJobID = client.createReportJob1(job1ID)

  // verify the report job exists in the user's job list
  println("  verifying report job $rJobID is in report job list")
  client.verifyReportJobInJobList(rJobID)

  // Go to the report job directly and verify the config
  println("  verifying report job $rJobID configuration")
  client.verifyReportJob1Config(rJobID, job1ID)

  // Resubmit the same config and verify the ID is the same
  println("  attempt to recreate the same report job expecting the same hash ID")
  require(rJobID == client.createReportJob1(job1ID))

  // Change the report job summary
  // verify the changed report job summary
  // wait for the job to complete
}

// // //
//
//   Query Job Mixins
//
// // //


private fun MultiBlastClient.selectBlastNTarget(): BlastTarget {
  val res = query.getBlastableTargets()

  for ((site, targets) in res) {
    for ((target, dbs) in targets) {
      for (db in dbs.naTargets) {
        return BlastTarget(site, target, db)
      }
    }
  }

  throw IllegalStateException("No nucleotide blast targets available!")
}

private fun MultiBlastClient.createQueryJob1(target: BlastTarget): HashID {
  val res = query.createJob {
    withJobConfig {
      site = target.site
      query = Query1
      targets.add(QueryJobTarget(target.target, target.db))
      addToUserCollection = true
    }

    withBlastN {

    }
  }

  if (res.isError)
  // TODO: print out the error text maybe?
    throw IllegalStateException("Blast service rejected configuration")

  return res.value!!.queryJobID
}

private fun MultiBlastClient.verifyQueryJob1Config(jobID: HashID, target: BlastTarget) {
  val res = query.getJob(jobID)
    ?: throw IllegalStateException("404 when attempting to get job $jobID")

  require(res.jobConfig.site == target.site)
  require(res.jobConfig.targets.size == 1)
  require(res.jobConfig.targets[0].targetDisplayName == target.target)
  require(res.jobConfig.targets[0].targetFile == target.db)
  require(res.blastConfig.tool == BlastQueryTool.BlastN)
}

private fun MultiBlastClient.verifyQueryJobInJobList(jobID: HashID) {
  query.listJobs()
    .find { it.queryJobID == jobID }
    ?: throw IllegalStateException("Job ID $jobID did not appear in the job list results.")
}

private fun MultiBlastClient.verifyQueryJobNotInJobList(jobID: HashID) {
  query.listJobs()
    .find { it.queryJobID == jobID }
    ?.run { throw IllegalStateException("Job ID $jobID appeared in the job list results") }
}

private fun MultiBlastClient.getJobQuery(jobID: HashID) =
  query.getJobQuery(jobID)!!.reader().use { it.readText() }

private fun MultiBlastClient.getQueryJobResult(jobID: HashID) =
  query.getJobResult(jobID)!!.reader().use { it.readText() }

private fun MultiBlastClient.waitForQueryJob(jobID: HashID) {
  while (true) {
    when (query.getJob(jobID)!!.status) {
      JobStatus.Complete -> return
      JobStatus.Failed   -> throw IllegalStateException("Job $jobID marked as failed")
      JobStatus.Expired  -> throw IllegalStateException("Job $jobID marked as expired")
      else               -> {}
    }
    Thread.sleep(100)
  }
}

// // //
//
//   Report Job Mixins
//
// // //

private fun MultiBlastClient.createReportJob1(queryJobID: HashID): HashID =
  report.createJob {
    this.queryJobID = queryJobID
    blastConfig { formatType = BlastOutFormat.MultiFileBlastJSON }
  }
    .value!!
    .reportJobID

private fun MultiBlastClient.verifyReportJob1Config(reportJobID: HashID, queryJobID: HashID) {
  val res = report.getJob(reportJobID)
    ?: throw IllegalStateException("404 when attempting to get job $reportJobID")

  require(res.queryJobID == queryJobID)
  require(res.blastConfig.formatType == BlastOutFormat.MultiFileBlastJSON)
}

private fun MultiBlastClient.verifyReportJobInJobList(reportJobID: HashID) {
  report.listJobs()
    .find { it.reportJobID == reportJobID }
    ?: throw IllegalStateException("Job ID $reportJobID did not appear in the job list results.")
}

private fun MultiBlastClient.waitForReportJob(reportJobID: HashID) {
  while (true) {
    when (report.getJob(reportJobID)!!.status) {
      JobStatus.Complete -> return
      JobStatus.Failed   -> throw IllegalStateException("Job $reportJobID marked as failed")
      JobStatus.Expired  -> throw IllegalStateException("Job $reportJobID marked as expired")
      else               -> {}
    }
    Thread.sleep(100)
  }
}