package api

import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

object MBlast {
  object Jobs {

    fun postRequest(withAuth: Boolean = true, fn: RequestSpecification.() -> Unit = {}): Response =
      givenMBlastEP(Paths.QueryJobs)
        .withOptAuth(withAuth)
        .apply(fn)
        .post()
        .prettyPeek()

    fun getRequest(withAuth: Boolean = true, fn: RequestSpecification.() -> Unit = {}): Response =
      givenMBlastEP(Paths.QueryJobs)
        .withOptAuth(withAuth)
        .apply(fn)
        .get()
        .prettyPeek()

    object ByID {

      fun getRequest(jobID: String, withAuth: Boolean = true, fn: RequestSpecification.() -> Unit = {}): Response =
        givenMBlastEP(Paths.JobByID(jobID))
          .withOptAuth(withAuth)
          .apply(fn)
          .get()
          .prettyPeek()

      fun deleteRequest(jobID: String, withAuth: Boolean = true, fn: RequestSpecification.() -> Unit = {}): Response =
        givenMBlastEP(Paths.JobByID(jobID))
          .withOptAuth(withAuth)
          .apply(fn)
          .delete()
          .prettyPeek()

      object Query {

        fun getRequest(jobID: String, withAuth: Boolean = true, fn: RequestSpecification.() -> Unit = {}): Response =
          givenMBlastEP(Paths.JobQueryByID(jobID))
            .withOptAuth(withAuth)
            .apply(fn)
            .get()
            .prettyPeek()
      }

      object Result {

        fun getRequest(jobID: String, withAuth: Boolean = true, fn: RequestSpecification.() -> Unit = {}): Response =
          givenMBlastEP(Paths.QueryJobResultByID(jobID))
            .withOptAuth(withAuth)
            .apply(fn)
            .get()
            .prettyPeek()
      }
    }
  }
}

private inline fun givenMBlastEP(path: String) =
  RestAssured.given()
    .baseUri(TestConfig.MBlastURL)
    .basePath(path)

private inline fun RequestSpecification.withOptAuth(auth: Boolean) =
  if (auth)
    withAuth()
  else
    this

private inline fun RequestSpecification.withAuth(): RequestSpecification =
  header("Auth-Key", TestProps.authKey)