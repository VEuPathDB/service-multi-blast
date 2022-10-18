package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagNumAlignments
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagNumAlignments)
internal class NumAlignmentsTest {

  @Nested
  @DisplayName("ParseNumAlignments()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object containing an int value greater than or equal to 1")
    inner class InRange {

      @Test
      @DisplayName("returns a new NumAlignments instance wrapping that value")
      fun t1() {
        for (i in 0u .. 10u) {
          val inp = Json.newObject { put(FlagNumAlignments, i.toInt()) }
          assertEquals(i, ParseNumAlignments(inp).value)
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing an int value less than 0")
    inner class OutOfRange {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseNumAlignments(Json.new { put(FlagNumAlignments, -1) })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object that does not contain this flag")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted NumAlignments instance")
      fun t1() {
        assertTrue(ParseNumAlignments(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a non-integral value")
    inner class BadType {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseNumAlignments(Json.new { put(FlagNumAlignments, "nope") })
        }
      }
    }
  }

  @Nested
  @DisplayName("NumAlignments()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(NumAlignments().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        NumAlignments().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        NumAlignments().appendCliSegment(inp)
        assertEquals(0, inp.length)
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        NumAlignments().appendCliParts(inp)
        assertEquals(0, inp.size)
      }
    }

    @Nested
    @DisplayName("when constructed with an argument")
    inner class Arg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(NumAlignments(1u).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the value to the input JSON object")
      fun t2() {
        val inp = Json.newObject()
        NumAlignments(1u).appendJson(inp)
        assertEquals("{\"$FlagNumAlignments\":1}", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the value to the input StringBuilder")
      fun t3() {
        val inp = StringBuilder(32)
        NumAlignments(1u).appendCliSegment(inp)
        assertEquals(" $FlagNumAlignments 1", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the value to the input list")
      fun t4() {
        val inp = ArrayList<String>(2)
        NumAlignments(1u).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagNumAlignments, inp[0])
        assertEquals("1", inp[1])
      }
    }
  }
}