package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.jackson.Json

@DisplayName("-outfmt.fields")
internal class FormatFieldsTest {

  @Nested
  @DisplayName("with default values")
  inner class Default {

    @Test
    @DisplayName("isDefault == true")
    fun t1() {
      assertTrue(FormatFields().isDefault)
    }

    @Test
    @DisplayName("does not append anything to the json serialization object")
    fun t2() {
      val inp = Json.newObject()

      FormatFields().appendJson(inp)

      assertEquals(0, inp.size())
    }

    @Test
    @DisplayName("does not append anything to the cli string")
    fun t3() {
      val inp = StringBuilder(0)

      FormatFields().appendCliSegment(inp)

      assertTrue(inp.isEmpty())
    }
  }

  @Nested
  @DisplayName("with a single non-default value")
  inner class Single {

    @Test
    @DisplayName("appends the value to the json serialization object in an array")
    fun t1() {
      for (v in FormatField.values()) {
        if (v == FormatField.StandardFields)
          continue

        val inp = Json.newObject()

        FormatFields(listOf(v)).appendJson(inp)

        assertEquals(1, inp.size())
        assertTrue(inp.has("fields"))
        assertTrue(inp["fields"].isArray)
        assertEquals(1, inp["fields"].size())
        assertTrue(inp["fields"][0].isTextual)
        assertEquals(v.value, inp["fields"][0].textValue())
      }
    }

    @Test
    @DisplayName("appends the value to the cli string")
    fun t2() {
      for (v in FormatField.values()) {
        if (v == FormatField.StandardFields)
          continue

        val inp = StringBuilder(10)

        FormatFields(listOf(v)).appendCliSegment(inp)

        assertEquals(" ${v.value}", inp.toString())
      }
    }
  }

  @Nested
  @DisplayName("with multiple values")
  inner class Multiple {

    @Test
    @DisplayName("appends the values to the json serialization object in an array")
    fun t1() {
      val inp = Json.newObject()

      FormatFields(listOf(
        FormatField.QuerySeqID,
        FormatField.AlignmentLength,
        FormatField.AllSubjectGIs,
      )).appendJson(inp)

      assertEquals(1, inp.size())
      assertTrue(inp.has("fields"))
      assertTrue(inp["fields"].isArray)
      assertEquals(3, inp["fields"].size())
      assertTrue(inp["fields"][0].isTextual)
      assertTrue(inp["fields"][1].isTextual)
      assertTrue(inp["fields"][2].isTextual)
      assertEquals(FormatField.QuerySeqID.value, inp["fields"][0].textValue())
      assertEquals(FormatField.AlignmentLength.value, inp["fields"][1].textValue())
      assertEquals(FormatField.AllSubjectGIs.value, inp["fields"][2].textValue())
    }

    @Test
    @DisplayName("appends the values to the cli string")
    fun t2() {
      val inp = StringBuilder(128)

      FormatFields(listOf(
        FormatField.QuerySeqID,
        FormatField.AlignmentLength,
        FormatField.AllSubjectGIs,
      )).appendCliSegment(inp)

      assertEquals(" qseqid length sallgi", inp.toString())
    }
  }
}