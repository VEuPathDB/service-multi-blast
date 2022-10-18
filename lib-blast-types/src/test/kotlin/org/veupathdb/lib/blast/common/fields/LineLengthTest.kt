package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagLineLength
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagLineLength)
internal class LineLengthTest {

  @Nested
  @DisplayName("ParseLineLength()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object containing an int value greater than or equal to 1")
    inner class InRange {

      @Test
      @DisplayName("returns a new LineLength instance wrapping that value")
      fun t1() {
        for (i in 1u .. 10u) {
          val inp = Json.newObject { put(FlagLineLength, i.toInt()) }
          assertEquals(i, ParseLineLength(inp).value)
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing an int value less than 1")
    inner class OutOfRange {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseLineLength(Json.new { put(FlagLineLength, 0) })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object that does not contain this flag")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted LineLength instance")
      fun t1() {
        assertTrue(ParseLineLength(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a non-integral value")
    inner class BadType {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseLineLength(Json.new { put(FlagLineLength, "nope") })
        }
      }
    }
  }

  @Nested
  @DisplayName("LineLength()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(LineLength().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        LineLength().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        LineLength().appendCliSegment(inp)
        assertEquals(0, inp.length)
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        LineLength().appendCliParts(inp)
        assertEquals(0, inp.size)
      }
    }

    @Nested
    @DisplayName("when constructed with an argument")
    inner class Arg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(LineLength(1u).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the value to the input JSON object")
      fun t2() {
        val inp = Json.newObject()
        LineLength(1u).appendJson(inp)
        assertEquals("{\"$FlagLineLength\":1}", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the value to the input StringBuilder")
      fun t3() {
        val inp = StringBuilder(32)
        LineLength(1u).appendCliSegment(inp)
        assertEquals(" $FlagLineLength 1", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the value to the input list")
      fun t4() {
        val inp = ArrayList<String>(2)
        LineLength(1u).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagLineLength, inp[0])
        assertEquals("1", inp[1])
      }
    }
  }
}