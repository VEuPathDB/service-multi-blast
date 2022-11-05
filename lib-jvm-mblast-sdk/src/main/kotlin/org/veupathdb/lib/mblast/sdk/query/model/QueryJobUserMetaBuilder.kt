package org.veupathdb.lib.mblast.sdk.query.model

data class QueryJobUserMetaBuilder(
  var summary: String? = null,
  var description: String? = null,
) {
  fun build() = QueryJobUserMeta(summary, description)
}