package api.test

import api.Paths
import api.TestConfig
import api.TestProps
import api.test.support.BlastTargets
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

import io.restassured.RestAssured.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Assertions.assertEquals

@DisplayName("POST /query/jobs")
open class PostNewJobEndpoint {

  inline fun makeRequest() =
    given()
      .baseUri(TestConfig.MBlastURL)
      .basePath(Paths.QueryJobs)
      .header("Auth-Key", TestProps.authKey)

  inline fun givenARequestWithMultipart() =
    makeRequest()
      .contentType("multipart/form-data; boundary=foops")

  @Nested
  @DisplayName("responds with a 400 when")
  inner class Responds400When {

    @Test
    @DisplayName("the request body is empty")
    fun `request-body-empty`() {
      givenARequestWithMultipart()
      .`when`()
        .post()
        .prettyPeek()
      .then()
        .statusCode(400)
        .contentType("application/json")
        .body("status", equalTo("bad-request"))
    }

    @Test
    @DisplayName("the request config is not a json object")
    fun `request-config-is-not-json`() {
      givenARequestWithMultipart()
        .multiPart("config", "hello")
      .`when`()
        .post()
        .prettyPeek()
      .then()
        .statusCode(400)
        .contentType("application/json")
        .body("status", equalTo("bad-request"))
    }
  }

  @Nested
  @DisplayName("responds with a 422 when")
  inner class Responds422When {

    @Test
    @DisplayName("the request config is an empty json object")
    fun `request-config-is-empty-object`() {
      givenARequestWithMultipart()
        .multiPart("config", "{}")
      .`when`()
        .post()
        .prettyPeek()
      .then()
        .statusCode(422)
        .contentType("application/json")
        .body("status", equalTo("invalid-input"))
    }

    @Test
    @DisplayName("the job config is an empty json object")
    fun `job-config-is-empty-object`() {
      givenARequestWithMultipart()
        .multiPart("config", """{"jobConfig":{}, "blastConfig":{"tool":"blastn"}}""")
        .multiPart("query", "cat")
        .`when`()
        .post()
        .prettyPeek()
        .then()
        .statusCode(422)
        .contentType("application/json")
        .body("status", equalTo("invalid-input"))
    }
  }
}