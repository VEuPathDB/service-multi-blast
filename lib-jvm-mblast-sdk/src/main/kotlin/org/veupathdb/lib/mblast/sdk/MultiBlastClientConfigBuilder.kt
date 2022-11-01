package org.veupathdb.lib.mblast.sdk

sealed interface MultiBlastClientConfigBuilder {

  fun url(host: String): MultiBlastClientConfigBuilder

  fun port(port: Int): MultiBlastClientConfigBuilder

  fun port(port: UShort): MultiBlastClientConfigBuilder

  fun authKey(authKey: String): MultiBlastClientConfigBuilder

}

