package org.veupathdb.lib.mblast.sdk

import org.veupathdb.lib.mblast.sdk.except.MBlastAPIException
import java.net.URI
import java.net.http.HttpClient

internal class MultiBlastClientConfigBuilderImpl : MultiBlastClientConfigBuilder {

  private var host: String? = null

  private var port: UShort? = null

  private var authKey: String? = null

  private var client: HttpClient? = null

  override fun httpClient(client: HttpClient): MultiBlastClientConfigBuilder {
    this.client = client
    return this
  }

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
    if (port < 1u)
      throw IllegalArgumentException("port number must be in the valid port range")

    this.port = port
    return this
  }

  override fun authKey(authKey: String): MultiBlastClientConfigBuilder {
    this.authKey = authKey
    return this
  }

  internal fun build(): MultiBlastClientConfig {
    if (host == null)
      throw MBlastAPIException("MultiBlastClientConfig service hostname was not set.")
    if (authKey == null)
      throw MBlastAPIException("MultiBlastClientConfig service auth key was not set.")

    val uri = if (port == null)
      URI(host!!)
    else
      URI("$host:$port")

    return MultiBlastClientConfig(uri, authKey!!, client ?: HttpClient.newHttpClient())
  }
}