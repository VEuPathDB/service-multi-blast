package org.veupathdb.lib.mblast.sdk


internal class MultiBlastClientImpl(config: MultiBlastClientConfig) : MultiBlastClient {
  override val query: MultiBlastQueryServiceAPI by lazy { MultiBlastQueryServiceAPIImpl(config) }
  override val report: MultiBlastReportServiceAPI by lazy { MultiBlastReportServiceAPIImpl(config) }
}