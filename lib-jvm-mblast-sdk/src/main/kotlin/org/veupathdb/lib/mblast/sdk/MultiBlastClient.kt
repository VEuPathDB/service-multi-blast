package org.veupathdb.lib.mblast.sdk

sealed interface MultiBlastClient {
  val query: MultiBlastQueryServiceAPI
  val report: MultiBlastReportServiceAPI
}