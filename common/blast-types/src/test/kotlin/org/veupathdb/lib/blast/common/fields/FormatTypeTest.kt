package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.jackson.Json

@DisplayName("FormatType")
internal class FormatTypeTest {

  @Nested
  @DisplayName("isDefault")
  inner class IsDefault {

    @Test
    @DisplayName("returns false for every value except Pairwise")
    fun t1() {
      for (v in FormatType.values()) {
        if (v == FormatType.Pairwise)
          assertTrue(v.isDefault)
        else
          assertFalse(v.isDefault)
      }
    }
  }

  @Nested
  @DisplayName("#appendJson()")
  inner class AppendJson {

    @Test
    @DisplayName("Appends the enum ordinal value along with the correct json key to the given json object for all values except Pairwise.")
    fun t1() {
      for (v in FormatType.values()) {
        if (v == FormatType.Pairwise)
          continue

        val inp = Json.newObject()

        v.appendJson(inp)

        assertEquals(1, inp.size())
        assertTrue(inp.has("type"))
        assertTrue(inp["type"].isIntegralNumber)
        assertEquals(v.ordinal, inp["type"].intValue())
      }
    }
  }

  @Nested
  @DisplayName("#appendCliSegment()")
  inner class AppendCLI {

    @Test
    @DisplayName("Appends the enum ordinal value to the input string builder for all values except Pairwise")
    fun t1() {
      for (v in FormatType.values()) {
        if (v == FormatType.Pairwise)
          continue

        val inp = StringBuilder(2)

        v.appendCliSegment(inp)

        assertEquals(v.ordinal.toString(), inp.toString())
      }
    }
  }
}