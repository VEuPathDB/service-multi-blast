package org.veupathdb.lib.mblast.sdk

import io.foxcapades.lib.k.multipart.MultiPartBody
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.except.MBlastAPIException
import org.veupathdb.lib.mblast.sdk.except.MBlastJobNotFoundException
import java.io.InputStream
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Suppress("NOTHING_TO_INLINE")
internal sealed class MultiBlastServiceClient(
  protected val config: MultiBlastClientConfig,
) {

  protected inline fun resolvePath(path: String) =
    config.baseURI.resolve(path)


  protected inline fun HttpRequest.Builder.addAuthHeader(): HttpRequest.Builder =
    header("Auth-Key", config.authKey)

  protected inline fun HttpRequest.Builder.submit(): HttpResponse<InputStream> =
    config.client.send(build(), HttpResponse.BodyHandlers.ofInputStream())


  // // //
  //
  //   HttpResponse Mixins
  //
  // // //


  protected inline fun <reified T> HttpResponse<InputStream>.listResponse(): List<T> =
    when (statusCode()) {
      200  -> body().use { MultiBlast.JSON.readerForListOf(T::class.java).readValue(it) }
      else -> throwUnexpectedResponse()
    }

  protected inline fun <reified T> HttpResponse<InputStream>.response(): T =
    when (statusCode()) {
      200  -> body().use { MultiBlast.JSON.readValue(it, T::class.java) }
      else -> throwUnexpectedResponse()
    }

  protected inline fun <reified T> HttpResponse<InputStream>.optionalResponse(): T? =
    when (statusCode()) {
      200  -> body().use { MultiBlast.JSON.readValue(it, T::class.java) }
      404  -> null
      else -> throwUnexpectedResponse()
    }

  protected inline fun HttpResponse<InputStream>.optionalStreamResponse(): InputStream? =
    when (statusCode()) {
      200  -> body()
      404  -> null
      else -> throwUnexpectedResponse()
    }

  protected inline fun HttpResponse<InputStream>.require204(jobID: HashID, err404: String) =
    when (statusCode()) {
      204  -> {}
      404  -> throw MBlastJobNotFoundException(jobID, err404)
      else -> throwUnexpectedResponse()
    }

  protected inline fun HttpResponse<InputStream>.throwUnexpectedResponse(): Nothing {
    val body = String(body().readAllBytes())
    throw MBlastAPIException("Unexpected response from ${uri()} : ${statusCode()} : $body")
  }


  // // //
  //
  //   URI Mixins
  //
  // // //


  protected inline fun URI.toRequest() =
    HttpRequest.newBuilder(this)
      .addAuthHeader()

  protected inline fun URI.getRequest(): HttpRequest.Builder =
    toRequest()
      .GET()

  protected inline fun URI.deleteRequest(): HttpRequest.Builder =
    toRequest()
      .DELETE()

  protected inline fun URI.postRequest(): HttpRequest.Builder =
    toRequest()
      .POST(HttpRequest.BodyPublishers.noBody())

  protected inline fun URI.postRequest(contentType: String, body: InputStream): HttpRequest.Builder =
    toRequest()
      .header("Content-Type", contentType)
      .POST(HttpRequest.BodyPublishers.ofInputStream { body })

  protected inline fun URI.postRequest(body: MultiPartBody): HttpRequest.Builder =
    toRequest()
      .header("Content-Type", body.getContentTypeHeader())
      .POST(body.makePublisher())

  protected inline fun URI.jsonPostRequest(body: Any): HttpRequest.Builder =
    toRequest()
      .header("Content-Type", "application/json")
      .POST(HttpRequest.BodyPublishers.ofString(MultiBlast.JSON.writeValueAsString(body)))

  protected inline fun URI.jsonPatchRequest(body: Any): HttpRequest.Builder =
    toRequest()
      .header("Content-Type", "application/json")
      .method("PATCH", HttpRequest.BodyPublishers.ofString(MultiBlast.JSON.writeValueAsString(body)))
}
