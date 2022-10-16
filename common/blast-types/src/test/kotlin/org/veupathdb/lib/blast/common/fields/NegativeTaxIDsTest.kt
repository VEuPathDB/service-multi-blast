package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagNegativeTaxIDs
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagNegativeTaxIDs)
class NegativeTaxIDsTest {

  @Nested
  @DisplayName("ParseNegTaxIDs()")
  inner class Parse {

    @Nested
    @DisplayName("when called with a JSON object not containing the $FlagNegativeTaxIDs key")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted instance")
      fun t1() {
        assertTrue(ParseNegTaxIDs(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when called with a JSON object containing a non-array value")
    inner class NonArray {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseNegTaxIDs(Json.new { put(FlagNegativeTaxIDs, "not an array") })
        }
      }
    }

    @Nested
    @DisplayName("when called with a JSON object containing an array value that contains a non-string value")
    inner class NonString {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          val inp = Json.newObject { put(FlagNegativeTaxIDs, Json.newArray {
            add(123)
          }) }

          ParseNegTaxIDs(inp)
        }
      }
    }

    @Nested
    @DisplayName("when called with a JSON object containing a string array value")
    inner class StrArray {

      @Test
      @DisplayName("returns an instance wrapping the parsed value")
      fun t1() {
        val inp = Json.newObject { put(FlagNegativeTaxIDs, Json.newArray {
          add("hello")
        }) }

        val tgt = ParseNegTaxIDs(inp)

        assertEquals(1, tgt.value.size)
        assertEquals("hello", tgt.value[0])
      }
    }
  }

  @Nested
  @DisplayName("NegativeTaxIDs()")
  inner class Type {

    @Nested
    @DisplayName("when instantiated with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(NegativeTaxIDs().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        NegativeTaxIDs().appendJson(inp)
        assertEquals("{}", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        NegativeTaxIDs().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        NegativeTaxIDs().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when instantiated with a non-empty list")
    inner class NonEmptyArg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(NegativeTaxIDs(listOf("hi")).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the value to the input JSON object")
      fun t2() {
        val inp = Json.newObject()
        NegativeTaxIDs(listOf("hi")).appendJson(inp)
        assertEquals("""{"$FlagNegativeTaxIDs":["hi"]}""", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the value to the input StringBuilder")
      fun t3() {
        val inp = StringBuilder(32)
        NegativeTaxIDs(listOf("hi", "bye")).appendCliSegment(inp)
        assertEquals(" $FlagNegativeTaxIDs 'hi,bye'", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the value to the input List")
      fun t4() {
        val inp = ArrayList<String>(2)
        NegativeTaxIDs(listOf("hi", "bye")).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagNegativeTaxIDs, inp[0])
        assertEquals("hi,bye", inp[1])
      }
    }
  }
}