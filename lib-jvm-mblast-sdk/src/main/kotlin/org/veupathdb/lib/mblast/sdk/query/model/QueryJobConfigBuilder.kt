package org.veupathdb.lib.mblast.sdk.query.model

data class QueryJobConfigBuilder(
  var site: TargetSite? = null,
  val targets: MutableList<QueryJobTarget> = ArrayList(1),
  var query: String? = null,
  var addToUserCollection: Boolean? = null,
) {

  fun build(): QueryJobConfig {
    if (site == null)
      throw IllegalStateException("site is required")

    if (targets.isEmpty())
      throw IllegalStateException("targets must not be empty")

    return QueryJobConfig(site!!, targets, query, addToUserCollection)
  }
}