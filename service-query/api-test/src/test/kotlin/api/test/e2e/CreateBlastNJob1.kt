package api.test.e2e

import api.*
import api.test.support.BlastTargets
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import io.restassured.http.ContentType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.veupathdb.lib.jackson.Json

private const val JobSummary     = "test blastn job 1"
private const val JobDescription = "some description"
private const val JobQuery       = "catacat"

private inline fun JSON_CONFIG(tgt: BlastTargets.BlastTarget) = """
{
  "jobConfig": {
    "site": "${tgt.site}",
    "targets": [
      {
        "targetDisplayName": "${tgt.organism}",
        "targetFile": "${tgt.target}"
      }
    ],
    "query": "$JobQuery",
    "addToUserCollection": true
  },
  "blastConfig": {
    "tool": "blastn"
  },
  "meta": {
    "summary": "$JobSummary",
    "description": "$JobDescription"
  }
}
"""

private const val TestDescription = """
Simple Single-Sequence BlastN Job Workflow 1

This test will go through the following steps to exercise a workflow for a
simple, single-sequence job:

 1. Creates a single sequence blastn job with no custom blast configuration
 2. Lists the user's jobs and ensures the newly created job is in the list and
    resembles the expected state.
 3. Fetches the job details directly and ensures they resemble the expected
    state.
 4. Waits for the job to complete.
 5. Fetches the query for the job and ensures it matches the input query.
 6. Fetches the result for the job and ensures it is non-empty
 7. Deletes the job from the user's collection
 8. Lists the user's jobs and ensures the target job no longer appears in that
    list.
 9. Fetches the job details directly with saveJob=false
10. Lists the user's jobs and ensures the target still does not appear in that
    list.
11. Fetches the job details directly with saveJob=true
12. Lists the user's jobs and ensures the job appears in the that list again.
"""

@DisplayName("blast job creation: blastn 1")
class CreateBlastNJob1 {

  @Test
  fun run() {
    println(TestDescription)

    val tgt = BlastTargets.randomTarget(true)

    println("\nStep 1 ------------------------------------------------------\n")
    val jobID = submitJob(tgt)

    println("\nStep 2 ------------------------------------------------------\n")
    listJobs().also { jobList ->
      assertTrue(jobList.size() > 0)

      jobList.forEach { job ->
        if (job.getQueryJobID() == jobID) {
          job.validateFullMeta(JobSummary, JobDescription)
          return@also
        }
      }

      fail<Unit>("Job was not found in the user's job list")
    }

    println("\nStep 3 ------------------------------------------------------\n")
    getJobAfterCreate(jobID, tgt)

    println("\nStep 4 ------------------------------------------------------\n")
    awaitJob(jobID)

    println("\nStep 5 ------------------------------------------------------\n")
    getJobQuery(jobID)

    println("\nStep 6 ------------------------------------------------------\n")
    getJobResult(jobID)

    println("\nStep 7 ------------------------------------------------------\n")
    deleteJob(jobID)

    println("\nStep 8 ------------------------------------------------------\n")
    listJobs().also { jobList ->
      jobList.forEach { job ->
        if (job.getQueryJobID() == jobID)
          fail<Unit>("job was not deleted from user collection")
      }
    }

    println("\nStep 9 ------------------------------------------------------\n")
    getJobAfterUnlink(jobID, tgt)

    println("\nStep 10 -----------------------------------------------------\n")
    listJobs().also { jobList ->
      jobList.forEach { job ->
        if (job.getQueryJobID() == jobID)
          fail<Unit>("job was not deleted from user collection")
      }
    }

    println("\nStep 11 -----------------------------------------------------\n")
    getJobAfterUnlink(jobID, tgt, true)

    println("\nStep 12 -----------------------------------------------------\n")
    listJobs().also { jobList ->
      assertTrue(jobList.size() > 0)

      jobList.forEach { job ->
        if (job.getQueryJobID() == jobID) {
          assertFalse(job.has(JsonKeys.Meta))
          return@also
        }
      }

      fail<Unit>("Job was not found in the user's job list")
    }
  }

  private fun submitJob(tgt: BlastTargets.BlastTarget): String {
    val res = MBlast.Jobs.postRequest {
      contentType(ContentType.MULTIPART)
      multiPart("config", JSON_CONFIG(tgt))
    }

    res.then()
      .statusCode(200)

    val body = Json.parse<ObjectNode>(res.body.asString())

    assertTrue(body.has("queryJobID"))
    assertTrue(body["queryJobID"].isTextual)

    return body["queryJobID"].textValue()
  }

  private fun listJobs(): ArrayNode {
    val res = MBlast.Jobs.getRequest()

    res.then()
      .statusCode(200)
      .contentType(ContentType.JSON)

    return Json.parse<ArrayNode>(res.body.asString()).onEach { job ->
      job.validateQueryJobID()
      job.validateStatus()
    }
  }

  private fun getJobAfterCreate(jobID: String, tgt: BlastTargets.BlastTarget) {
    val res = MBlast.Jobs.ByID.getRequest(jobID)

    res.then()
      .statusCode(200)
      .contentType(ContentType.JSON)

    Json.parse<ObjectNode>(res.body.asString()).apply {
      validateQueryJobID()
      validateStatus()

      assertTrue(has("jobConfig"))
      get("jobConfig").apply {
        assertTrue(isObject, "jobConfig is not an object")

        assertTrue(has("site"))
        get("site").apply {
          assertTrue(isTextual, "jobConfig.site is not a string")
          assertEquals("PlasmoDB", textValue())
        }

        assertTrue(has("targets"))
        get("targets").apply {
          assertTrue(isArray, "jobConfig.targets is not an array")
          assertEquals(1, size(), "jobConfig.targets contained an unexpected number of targets")

          get(0).validateTarget(tgt)
        }
      }

      assertTrue(has("blastConfig"))
      get("blastConfig").apply {
        assertTrue(isObject, "blastConfig is not an object")
        assertTrue(has("tool"), "blastConfig.tool is missing")

        get("tool").apply {
          assertTrue(isTextual, "blastConfig.tool is not a string")
          assertEquals("blastn", textValue())
        }
      }

      assertTrue(has("subJobs"))
      get("subJobs").apply {
        assertTrue(isArray)
        assertEquals(0, size())
      }

      validateFullMeta(JobSummary, JobDescription)
    }
  }

  private fun getJobQuery(jobID: String) {
    val res = MBlast.Jobs.ByID.Query.getRequest(jobID) {
      queryParam("download", "true")
    }

    res.then()
      .statusCode(200)
      .contentType(ContentType.TEXT)
      .header("Content-Disposition", "attachment; filename=\"query.txt\"")

    assertEquals(JobQuery, res.body.asString())
  }

  private fun getJobResult(jobID: String) {
    val res = MBlast.Jobs.ByID.Result.getRequest(jobID) {
      queryParam("download", "true")
    }

    res.then()
      .statusCode(200)
      .contentType(ContentType.TEXT)
      .header("Content-Disposition", "attachment; filename=\"report.asn1\"")

    assertFalse(res.body.asString().isEmpty())
  }

  private fun awaitJob(jobID: String) {
    var counter = 0

    while (true) {
      if (counter > 5)
        fail<Unit>("job took too long to complete")

      Json.parse<ObjectNode>(MBlast.Jobs.ByID.getRequest(jobID).body.asString())
        .apply {
          when (get(JsonKeys.Status).textValue()) {
            "complete" -> return
            "failed"   -> fail<Unit>("job failed")
            else       -> {}
          }
        }

      println("\n... waiting for job to complete ...\n")

      Thread.sleep(3000)
      counter++
    }
  }

  private fun deleteJob(jobID: String) {
    val res = MBlast.Jobs.ByID.deleteRequest(jobID)

    res.then()
      .statusCode(204)
  }

  private fun getJobAfterUnlink(jobID: String, tgt: BlastTargets.BlastTarget, saveJob: Boolean = false) {
    val res = MBlast.Jobs.ByID.getRequest(jobID) {
      queryParam("saveJob", saveJob.toString())
    }

    res.then()
      .statusCode(200)
      .contentType(ContentType.JSON)

    Json.parse<ObjectNode>(res.body.asString()).apply {
      validateQueryJobID()
      validateStatus()

      assertTrue(has("jobConfig"))
      get("jobConfig").apply {
        assertTrue(isObject, "jobConfig is not an object")

        assertTrue(has("site"))
        get("site").apply {
          assertTrue(isTextual, "jobConfig.site is not a string")
          assertEquals("PlasmoDB", textValue())
        }

        assertTrue(has("targets"))
        get("targets").apply {
          assertTrue(isArray, "jobConfig.targets is not an array")
          assertEquals(1, size(), "jobConfig.targets contained an unexpected number of targets")

          get(0).validateTarget(tgt)
        }
      }

      assertTrue(has("blastConfig"))
      get("blastConfig").apply {
        assertTrue(isObject, "blastConfig is not an object")
        assertTrue(has("tool"), "blastConfig.tool is missing")

        get("tool").apply {
          assertTrue(isTextual, "blastConfig.tool is not a string")
          assertEquals("blastn", textValue())
        }
      }

      assertTrue(has("subJobs"))
      get("subJobs").apply {
        assertTrue(isArray)
        assertEquals(0, size())
      }

      assertFalse(has(JsonKeys.Meta))
    }
  }

}