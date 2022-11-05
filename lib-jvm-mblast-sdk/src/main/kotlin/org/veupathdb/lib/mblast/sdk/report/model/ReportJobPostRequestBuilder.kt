package org.veupathdb.lib.mblast.sdk.report.model

import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.report.blast.BlastFormatConfig
import org.veupathdb.lib.mblast.sdk.report.blast.BlastFormatConfigBuilder

class ReportJobPostRequestBuilder {
  var queryJobID: HashID? = null

  var blastConfig: BlastFormatConfig? = null

  var userMeta: ReportJobUserMeta? = null

  var addToUserCollection: Boolean? = null

  fun queryJobID(id: HashID): ReportJobPostRequestBuilder {
    queryJobID = id
    return this
  }

  fun blastConfig(fn: BlastFormatConfigBuilder.() -> Unit) {
    blastConfig = BlastFormatConfigBuilder().apply(fn).build()
  }

  fun blastConfig(config: BlastFormatConfig): ReportJobPostRequestBuilder {
    blastConfig = config
    return this
  }

  fun userMeta(report: ReportJobUserMeta): ReportJobPostRequestBuilder {
    userMeta = report
    return this
  }

  fun addToUserCollection(value: Boolean): ReportJobPostRequestBuilder {
    addToUserCollection = value
    return this
  }

  fun build() =
    ReportJobPostRequest(
      queryJobID ?: throw IllegalStateException("queryJobID was null"),
      blastConfig,
      addToUserCollection,
      userMeta
    )
}