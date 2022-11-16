package org.veupathdb.lib.mblast.sdk

/**
 * # Multi-Blast REST API Client
 *
 * Contains sub-clients for the different components of the Multi-Blast API.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
sealed interface MultiBlastClient {

  /**
   * Sub-client for the BLAST+ query job segment of the Multi-Blast service API.
   */
  val query: MultiBlastQueryServiceAPI

  /**
   * Sub-client for the BLAST+ formatter job segment of the Multi-Blast service
   * API.
   */
  val report: MultiBlastReportServiceAPI
}