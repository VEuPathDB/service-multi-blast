package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagInclusionEThresh
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagInclusionEThresh)
internal class InclusionEValueThresholdTest {

  @Nested
  @DisplayName("ParseInclusionEValueThreshold()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object containing a non-number value")
    inner class NonString {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseInclusionEValueThreshold(Json.new { put(FlagInclusionEThresh, "yodeling") })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object with no db file value")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted InclusionEThresh instance")
      fun t1() {
        assertTrue(ParseInclusionEValueThreshold(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a text value")
    inner class Present {

      @Test
      @DisplayName("returns a InclusionEThresh instance wrapping")
      fun t1() {
        val inp = Json.newObject { put(FlagInclusionEThresh, 32.32) }
        assertEquals(32.32, ParseInclusionEValueThreshold(inp).value)
      }
    }
  }

  @Nested
  @DisplayName("InclusionEValueThreshold()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("default == true")
      fun t1() {
        assertTrue(InclusionEValueThreshold().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        InclusionEValueThreshold().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        InclusionEValueThreshold().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        InclusionEValueThreshold().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when constructed with an argument")
    inner class Arg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(InclusionEValueThreshold(99.0).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the flag to the JSON object input.")
      fun t2() {
        val inp = Json.newObject()
        InclusionEValueThreshold(3.3).appendJson(inp)
        assertEquals("""{"$FlagInclusionEThresh":3.3}""", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the flag to the StringBuilder input.")
      fun t3() {
        val inp = StringBuilder(32)
        InclusionEValueThreshold(69.25).appendCliSegment(inp)
        assertEquals(" $FlagInclusionEThresh 69.25", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the flag to the List input.")
      fun t4() {
        val inp = ArrayList<String>(2)
        InclusionEValueThreshold(42.5).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagInclusionEThresh, inp[0])
        assertEquals("42.5", inp[1])
      }
    }
  }
  

}