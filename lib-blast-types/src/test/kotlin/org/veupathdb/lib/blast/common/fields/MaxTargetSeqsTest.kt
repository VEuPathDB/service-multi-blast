package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagMaxTargetSeqs
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagMaxTargetSeqs)
internal class MaxTargetSeqsTest {

  @Nested
  @DisplayName("ParseMaxTargetSeqs()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object containing an int value greater than or equal to 0")
    inner class InRange {

      @Test
      @DisplayName("returns a new MaxTargetSeqs instance wrapping that value")
      fun t1() {
        for (i in 1u .. 10u) {
          val inp = Json.newObject { put(FlagMaxTargetSeqs, i.toInt()) }
          assertEquals(i, ParseMaxTargetSeqs(inp).value)
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
          ParseMaxTargetSeqs(Json.new { put(FlagMaxTargetSeqs, -1) })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object that does not contain this flag")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted MaxTargetSeqs instance")
      fun t1() {
        assertTrue(ParseMaxTargetSeqs(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a non-integral value")
    inner class BadType {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseMaxTargetSeqs(Json.new { put(FlagMaxTargetSeqs, "nope") })
        }
      }
    }
  }

  @Nested
  @DisplayName("MaxTargetSeqs()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(MaxTargetSeqs().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        MaxTargetSeqs().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        MaxTargetSeqs().appendCliSegment(inp)
        assertEquals(0, inp.length)
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        MaxTargetSeqs().appendCliParts(inp)
        assertEquals(0, inp.size)
      }
    }

    @Nested
    @DisplayName("when constructed with an argument")
    inner class Arg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(MaxTargetSeqs(1u).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the value to the input JSON object")
      fun t2() {
        val inp = Json.newObject()
        MaxTargetSeqs(1u).appendJson(inp)
        assertEquals("{\"$FlagMaxTargetSeqs\":1}", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the value to the input StringBuilder")
      fun t3() {
        val inp = StringBuilder(32)
        MaxTargetSeqs(1u).appendCliSegment(inp)
        assertEquals(" $FlagMaxTargetSeqs 1", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the value to the input list")
      fun t4() {
        val inp = ArrayList<String>(2)
        MaxTargetSeqs(1u).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagMaxTargetSeqs, inp[0])
        assertEquals("1", inp[1])
      }
    }
  }
}