package org.veupathdb.lib.mblast.sdk

import java.net.http.HttpClient

sealed interface MultiBlastClientConfigBuilder {

  fun httpClient(client: HttpClient): MultiBlastClientConfigBuilder

  fun url(host: String): MultiBlastClientConfigBuilder

  fun port(port: Int): MultiBlastClientConfigBuilder

  fun port(port: UShort): MultiBlastClientConfigBuilder

  fun authKey(authKey: String): MultiBlastClientConfigBuilder

}

