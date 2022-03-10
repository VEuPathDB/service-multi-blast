package mb.lib.blast.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.blast.field.HSPSorting

enum class IOHSPSorting(val value: String) {
  ByExpectValue     ("by-hsp-evalue"),
  ByScore           ("by-hsp-score"),
  ByQueryStart      ("by-hsp-query-start"),
  ByPercentIdentity ("by-hsp-percent-identity"),
  BySubjectStart    ("by-hsp-subject-start");

  @JsonValue
  fun toJSON(): JsonNode = TextNode(value)

  override fun toString() = value

  val internalValue get() = HSPSorting.values()[ordinal]

  companion object {
    operator fun get(i: Int) = when (i) {
      0 -> ByExpectValue
      1 -> ByScore
      2 -> ByQueryStart
      3 -> ByPercentIdentity
      4 -> BySubjectStart
      else -> throw NoSuchElementException()
    }

    operator fun get(i: HSPSorting) = when(i) {
      HSPSorting.ByHSPExpectValue     -> ByExpectValue
      HSPSorting.ByHSPScore           -> ByScore
      HSPSorting.ByHSPQueryStart      -> ByQueryStart
      HSPSorting.ByHSPPercentIdentity -> ByPercentIdentity
      HSPSorting.ByHSPSubjectStart    -> BySubjectStart
      else                            -> throw NoSuchElementException()
    }

    @JsonCreator
    operator fun get(i: String) = when(i) {
      ByExpectValue.value     -> ByExpectValue
      ByScore.value           -> ByScore
      ByQueryStart.value      -> ByQueryStart
      ByPercentIdentity.value -> ByPercentIdentity
      BySubjectStart.value    -> BySubjectStart
      else                    -> throw NoSuchElementException()
    }
  }
}
