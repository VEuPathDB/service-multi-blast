package mblast.lib.query

interface QuerySequence {
  val defLine: String?
  val body: String
}

internal data class QuerySeq(
  override val defLine: String?,
  override val body: String,
) : QuerySequence {

  override fun toString() =
    if (defLine == null)
      body
    else
      with(StringBuilder(defLine.length + body.length + 1)) {
        append(defLine)
        append('\n')
        append(body)
      }.toString()
}