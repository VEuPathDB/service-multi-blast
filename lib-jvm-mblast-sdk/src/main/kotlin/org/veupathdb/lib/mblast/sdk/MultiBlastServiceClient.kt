package org.veupathdb.lib.mblast.sdk

import org.veupathdb.lib.mblast.sdk.util.BOUNDARY_LEADER
import org.veupathdb.lib.mblast.sdk.util.CRLF
import java.io.BufferedWriter
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URI

internal sealed class MultiBlastServiceClient(
  protected val config: MultiBlastClientConfig,
) {

  protected inline fun resolvePath(path: String) =
    config.baseURI.resolve(path)

  // // //
  //
  //   URI Mixins
  //
  // // //


  protected inline fun URI.openHTTP() =
    (toURL().openConnection() as HttpURLConnection).apply { addAuthHeader() }


  protected inline fun URI.openSimplePATCH() =
    openHTTP().apply { requestMethod = "PATCH" }

  protected inline fun URI.openSimpleDELETE() =
    openHTTP().apply { requestMethod = "DELETE" }

  protected inline fun URI.openSimplePOST() =
    openHTTP().apply { requestMethod = "POST" }

  protected inline fun URI.openSimpleGET() =
    openHTTP().apply { requestMethod = "GET" }


  protected inline fun URI.openGETWithBody() =
    openSimpleGET().apply { doInput = true }

  protected inline fun URI.openPOSTWithIO(contentType: String) =
    openSimplePOST().apply {
      doInput = true
      doOutput = true
      setRequestProperty("Content-Type", contentType)
    }


  protected inline fun HttpURLConnection.addAuthHeader() =
    setRequestProperty("Auth-Key", config.authKey)

  protected inline fun newBoundary() =
    "MBLAST_CLIENT_" + System.currentTimeMillis().toString(16)

  protected fun HttpURLConnection.throwUnexpectedResponse(): Nothing {
    val body = String(errorStream.readAllBytes())
    throw IllegalStateException("Unexpected response from ${this.url} : $responseCode : $body")
  }

  protected fun BufferedWriter.endTransmission(boundary: String) =
    append(BOUNDARY_LEADER).append(boundary).append(BOUNDARY_LEADER).append(CRLF)

  protected fun BufferedWriter.writeJsonPart(boundary: String, name: String, body: Any) {
    append(BOUNDARY_LEADER).append(boundary).append(CRLF)
    append("Content-Disposition: form-data; name=\"$name\"").append(CRLF)
    append("Content-Type: application/json").append(CRLF)
    append(CRLF)
    MultiBlast.JSON.writeValue(MultiBlast.JSON.createGenerator(this), body)
    append(CRLF)
  }

  protected fun BufferedWriter.writeStringPart(boundary: String, name: String, body: Reader) {
    append(BOUNDARY_LEADER).append(boundary).append(CRLF)
    append("Content-Disposition: form-data; name=\"$name\"").append(CRLF)
    append("Content-Type: text/plain").append(CRLF)
    append(CRLF)
    body.transferTo(this)
    append(CRLF)
  }

}
