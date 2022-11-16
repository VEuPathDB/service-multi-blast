package org.veupathdb.lib.mblast.sdk

import java.net.http.HttpClient

/**
 * # Multi-Blast API Client Configuration Builder
 *
 * Builder for constructing a Multi-Blast API client configuration.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
sealed interface MultiBlastClientConfigBuilder {

  /**
   * Sets the HTTP client instance to use for making requests to the Multi-Blast
   * API.
   *
   * If unset, the constructed client will use a basic default instance provided
   * by [HttpClient.newHttpClient].
   *
   * @param client HTTP client to use for API requests.
   *
   * @return This builder instance.
   */
  fun httpClient(client: HttpClient): MultiBlastClientConfigBuilder

  /**
   * Sets the host URL for the Multi-Blast service instance that will be
   * interacted with.
   *
   * This value is required.
   *
   * @param host Host URL for the target Multi-Blast service instance.
   *
   * **NOTE** This value _MUST_ be prefixed with a protocol value of either
   * `http://` or `https://`.
   *
   * @return This builder instance.
   *
   * @throws IllegalArgumentException If the given value does not have a
   * protocol prefix of `http` or `https`.
   */
  fun url(host: String): MultiBlastClientConfigBuilder

  /**
   * Sets the port to use when making requests to the target Multi-Blast service
   * instance.
   *
   * If unset, this will default to the standard port for the host URL's
   * protocol (`80` or `443`).
   *
   * @param port Host port for the target Multi-Blast service instance.
   *
   * @return This builder instance.
   *
   * @throws IllegalArgumentException If the given value is less than `1` or
   * greater than `65535`.
   */
  fun port(port: Int): MultiBlastClientConfigBuilder

  /**
   * Sets the port to use when making requests to the target Multi-Blast service
   * instance.
   *
   * If unset, this will default to the standard port for the host URL's
   * protocol (`80` or `443`).
   *
   * @param port Host port for the target Multi-Blast service instance.
   *
   * @return This builder instance.
   *
   * @throws IllegalArgumentException If the given value is less than `1`.
   */
  fun port(port: UShort): MultiBlastClientConfigBuilder

  /**
   * Auth-Key value of the user that will be used when making requests to the
   * target Multi-Blast service instance.
   *
   * This value is required.
   *
   * @param authKey Authentication key to use when making requests to the target
   * Multi-Blast service instance.
   *
   * @return This builder instance.
   */
  fun authKey(authKey: String): MultiBlastClientConfigBuilder
}

