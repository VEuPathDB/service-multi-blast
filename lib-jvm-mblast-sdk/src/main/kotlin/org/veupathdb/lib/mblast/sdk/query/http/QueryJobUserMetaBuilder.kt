package org.veupathdb.lib.mblast.sdk.query.http

data class QueryJobUserMetaBuilder(
  var summary: String? = null,
  var description: String? = null,
) {
  fun build() = QueryJobUserMeta(summary, description)
}