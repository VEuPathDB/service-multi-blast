package org.veupathdb.lib.mblast.sdk

import java.net.URI
import java.net.http.HttpClient

internal data class MultiBlastClientConfig(
  var baseURI: URI,
  var authKey: String,
  var client: HttpClient,
) {
  override fun toString(): String {
    return "MultiBlastClientConfig(baseURI=$baseURI, authKey=***)"
  }
}