package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagTaxIDs
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagTaxIDs)
class TaxIDsTest {

  @Nested
  @DisplayName("ParseTaxIDs()")
  inner class Parse {

    @Nested
    @DisplayName("when called with a JSON object not containing the $FlagTaxIDs key")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted instance")
      fun t1() {
        assertTrue(ParseTaxIDs(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when called with a JSON object containing a non-array value")
    inner class NonArray {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseTaxIDs(Json.new { put(FlagTaxIDs, "not an array") })
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
          val inp = Json.newObject { put(FlagTaxIDs, Json.newArray {
            add(123)
          }) }

          ParseTaxIDs(inp)
        }
      }
    }

    @Nested
    @DisplayName("when called with a JSON object containing a string array value")
    inner class StrArray {

      @Test
      @DisplayName("returns an instance wrapping the parsed value")
      fun t1() {
        val inp = Json.newObject { put(FlagTaxIDs, Json.newArray {
          add("hello")
        }) }

        val tgt = ParseTaxIDs(inp)

        assertEquals(1, tgt.value.size)
        assertEquals("hello", tgt.value[0])
      }
    }
  }

  @Nested
  @DisplayName("TaxIDs()")
  inner class Type {

    @Nested
    @DisplayName("when instantiated with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(TaxIDs().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        TaxIDs().appendJson(inp)
        assertEquals("{}", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        TaxIDs().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        TaxIDs().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when instantiated with a non-empty list")
    inner class NonEmptyArg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(TaxIDs(listOf("hi")).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the value to the input JSON object")
      fun t2() {
        val inp = Json.newObject()
        TaxIDs(listOf("hi")).appendJson(inp)
        assertEquals("""{"$FlagTaxIDs":["hi"]}""", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the value to the input StringBuilder")
      fun t3() {
        val inp = StringBuilder(32)
        TaxIDs(listOf("hi", "bye")).appendCliSegment(inp)
        assertEquals(" $FlagTaxIDs 'hi,bye'", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the value to the input List")
      fun t4() {
        val inp = ArrayList<String>(2)
        TaxIDs(listOf("hi", "bye")).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagTaxIDs, inp[0])
        assertEquals("hi,bye", inp[1])
      }
    }
  }
}