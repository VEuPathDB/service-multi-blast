package org.veupathdb.lib.mblast.sdk

import java.net.URI

internal data class MultiBlastClientConfig(
  var baseURI: URI,
  var authKey: String,
) {
  override fun toString(): String {
    return "MultiBlastClientConfig(baseURI=$baseURI, authKey=***)"
  }
}