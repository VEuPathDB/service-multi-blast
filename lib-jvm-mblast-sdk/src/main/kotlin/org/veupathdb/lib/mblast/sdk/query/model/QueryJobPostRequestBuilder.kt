package org.veupathdb.lib.mblast.sdk.query.model

import org.veupathdb.lib.mblast.sdk.query.blast.*

data class QueryJobPostRequestBuilder(
  var jobConfig: QueryJobConfig? = null,
  var blastConfig: BlastQueryConfig? = null,
  var userMeta: QueryJobUserMeta? = null,
) {

  fun withJobConfig(fn: QueryJobConfigBuilder.() -> Unit) {
    jobConfig = QueryJobConfigBuilder().apply(fn).build()
  }

  fun withBlastN(fn: BlastNConfig.() -> Unit) {
    blastConfig = BlastNConfig().apply(fn)
  }

  fun withBlastP(fn: BlastPConfig.() -> Unit) {
    blastConfig = BlastPConfig().apply(fn)
  }

  fun withBlastX(fn: BlastXConfig.() -> Unit) {
    blastConfig = BlastXConfig().apply(fn)
  }

  fun withDeltaBlast(fn: DeltaBlastConfig.() -> Unit) {
    blastConfig = DeltaBlastConfig().apply(fn)
  }

  fun withPSIBlast(fn: PSIBlastConfig.() -> Unit) {
    blastConfig = PSIBlastConfig().apply(fn)
  }

  fun withRPSBlast(fn: RPSBlastConfig.() -> Unit) {
    blastConfig = RPSBlastConfig().apply(fn)
  }

  fun withRPSTBlastN(fn: RPSTBlastNConfig.() -> Unit) {
    blastConfig = RPSTBlastNConfig().apply(fn)
  }

  fun withTBlastN(fn: TBlastNConfig.() -> Unit) {
    blastConfig = TBlastNConfig().apply(fn)
  }

  fun withTBlastX(fn: TBlastXConfig.() -> Unit) {
    blastConfig = TBlastXConfig().apply(fn)
  }

  fun withMeta(fn: QueryJobUserMetaBuilder.() -> Unit) {
    userMeta = QueryJobUserMetaBuilder().apply(fn).build()
  }

  fun build(): QueryJobPostRequest {
    if (jobConfig == null)
      throw IllegalStateException("jobConfig is required")
    if (blastConfig == null)
      throw IllegalStateException("blastConfig is required")

    return QueryJobPostRequest(jobConfig!!, blastConfig!!, userMeta)
  }
}