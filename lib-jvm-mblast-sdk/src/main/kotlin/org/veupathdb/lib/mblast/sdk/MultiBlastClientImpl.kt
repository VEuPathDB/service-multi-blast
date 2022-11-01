package org.veupathdb.lib.mblast.sdk


internal class MultiBlastClientImpl(config: MultiBlastClientConfig) : MultiBlastClient {
  override val query: MultiBlastQueryServiceAPI by lazy { MBlastQueryClient(config) }
}