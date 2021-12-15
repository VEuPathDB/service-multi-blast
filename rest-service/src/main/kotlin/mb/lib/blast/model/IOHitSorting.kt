package mb.lib.blast.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import org.veupathdb.lib.blast.field.HitSorting

enum class IOHitSorting(val value: String) {
  ByExpectValue     ("by-eval"),
  ByBitScore        ("by-bit-score"),
  ByTotalScore      ("by-total-score"),
  ByPercentIdentity ("by-percent-identity"),
  ByQueryCoverage   ("by-query-coverage");

  @JsonValue
  override fun toString(): String {
    return value
  }

  val internalValue get() = HitSorting.values()[ordinal]

  companion object {
    operator fun get(i: Int) = values()[i]

    operator fun get(i: HitSorting) = when(i) {
      HitSorting.ByExpectValue     -> ByExpectValue
      HitSorting.ByBitScore        -> ByBitScore
      HitSorting.ByTotalScore      -> ByTotalScore
      HitSorting.ByPercentIdentity -> ByPercentIdentity
      HitSorting.ByQueryCoverage   -> ByQueryCoverage
      else                         -> throw NoSuchElementException()
    }

    @JsonCreator
    operator fun get(name: String) = values().first { it.value == name }
  }
}
