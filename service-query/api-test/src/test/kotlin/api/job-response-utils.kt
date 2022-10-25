package api

import api.test.support.BlastTargets
import com.fasterxml.jackson.databind.JsonNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

fun JsonNode.getQueryJobID(): String {
  validateQueryJobID()
  return get(JsonKeys.QueryJobID).textValue()
}

fun JsonNode.validateQueryJobID() {
  assertTrue(has(JsonKeys.QueryJobID))
  get(JsonKeys.QueryJobID).apply {
    assertTrue(isTextual)
    assertEquals(32, textValue().length)
  }
}

fun JsonNode.validateStatus(expected: String? = null) {
  assertTrue(has(JsonKeys.Status))
  get(JsonKeys.Status).apply {
    assertTrue(isTextual)

    if (expected != null)
      assertEquals(expected, textValue())
    else
      assertTrue(when (textValue()) {
        "queued"      -> true
        "in-progress" -> true
        "complete"    -> true
        "failed"      -> true
        "expired"     -> true
        else          -> false
      }, { "status value was unrecognized: ${textValue()}"})
  }
}

fun JsonNode.validateTarget(tgt: BlastTargets.BlastTarget) {
  assertTrue(isObject)

  assertTrue(has(JsonKeys.TargetDisplayName))
  get(JsonKeys.TargetDisplayName).apply {
    assertTrue(isTextual)
    assertEquals(tgt.organism, textValue())
  }

  assertTrue(has(JsonKeys.TargetFile))
  get(JsonKeys.TargetFile).apply {
    assertTrue(isTextual)
    assertEquals(tgt.target, textValue())
  }
}

fun JsonNode.validateFullMeta(expectedSummary: String? = null, expectedDescription: String? = null) {
  assertTrue(has(JsonKeys.Meta))
  get(JsonKeys.Meta).apply {
    assertTrue(isObject)

    validateMetaSummary(expectedSummary)
    validateMetaDescription(expectedDescription)
  }
}

fun JsonNode.validateMetaSummary(expected: String? = null) {
  assertTrue(has(JsonKeys.Summary))
  get(JsonKeys.Summary).apply {
    assertTrue(isTextual)

    if (expected != null)
      assertEquals(expected, textValue())
  }
}

fun JsonNode.validateMetaDescription(expected: String? = null) {
  assertTrue(has(JsonKeys.Description))
  get(JsonKeys.Description).apply {
    assertTrue(isTextual)

    if (expected != null)
      assertEquals(expected, textValue())
  }
}