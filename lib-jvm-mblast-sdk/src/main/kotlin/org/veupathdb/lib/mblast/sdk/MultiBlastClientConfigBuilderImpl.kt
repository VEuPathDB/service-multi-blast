package org.veupathdb.lib.mblast.sdk

import java.net.URI

internal class MultiBlastClientConfigBuilderImpl : MultiBlastClientConfigBuilder {

  private var host: String? = null

  private var port: UShort? = null

  private var authKey: String? = null

  override fun url(host: String): MultiBlastClientConfigBuilder {
    if (!(host.startsWith("http://") || host.startsWith("https://")))
      throw IllegalArgumentException("url string must begin with either http:// or https://")

    this.host = host
    return this
  }

  override fun port(port: Int): MultiBlastClientConfigBuilder {
    if (port < 1 || port > 65_535)
      throw IllegalArgumentException("port number must be in the valid port range")

    this.port = port.toUShort()
    return this
  }

  override fun port(port: UShort): MultiBlastClientConfigBuilder {
    this.port = port
    return this
  }

  override fun authKey(authKey: String): MultiBlastClientConfigBuilder {
    this.authKey = authKey
    return this
  }

  internal fun build(): MultiBlastClientConfig {
    if (host == null)
      throw IllegalStateException("MultiBlastClientConfig service hostname was not set.")
    if (authKey == null)
      throw IllegalStateException("MultiBlastClientConfig service auth key was not set.")

    val uri = if (port == null)
      URI(host!!)
    else
      URI("$host:$port")

    return MultiBlastClientConfig(uri, authKey!!)
  }
}