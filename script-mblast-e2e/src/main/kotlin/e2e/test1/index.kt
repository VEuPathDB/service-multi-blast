package e2e.test1

import e2e.conf.E2EConfig
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.MultiBlast
import org.veupathdb.lib.mblast.sdk.MultiBlastClient
import org.veupathdb.lib.mblast.sdk.query.blast.BlastQueryTool
import org.veupathdb.lib.mblast.sdk.query.http.JobStatus
import org.veupathdb.lib.mblast.sdk.query.http.QueryJobTarget
import org.veupathdb.lib.mblast.sdk.query.http.TargetSite

private const val Query1 = "catacatacat"

private data class BlastTarget(val site: TargetSite, val target: String, val db: String)

fun RunEndToEndTest1(config: E2EConfig) {
  println("Running end to end test 1")

  val client = MultiBlast.newClient {
    url("http://localhost")
    port(8080)
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
  println("  attempt to recreate the same job expecting the same hash ID")
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
  println("  looking up job $job1ID with saveJob=false")
  client.query.getJob(job1ID, false)

  // list the user's jobs and ensure the target job still doesn't appear
  println("  verifying that $job1ID is not in the user's collection")
  client.verifyQueryJobNotInJobList(job1ID)

  // Get the job details with saveJob = true
  println("  looking up job $job1ID with saveJob=true")
  client.query.getJob(job1ID, true)

  // list the user's jobs and ensure the target job appears again
  println("  checking that new query job $job1ID exists in user's job list")
  client.verifyQueryJobInJobList(job1ID)

  // bulk status check
  println("  running bulk status check")
  require(client.query.getBulkJobStatuses(listOf(job1ID)).contains(job1ID))

  // Create a report job for the created query job
  // verify the report job exists in the user's job list
  // Go to the report job directly and verify the config
  // Resubmit the same config and verify the ID is the same
  // wait for the job to complete
}

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
      JobStatus.Failed   -> return
      JobStatus.Expired  -> return
      else               -> {}
    }
  }
}