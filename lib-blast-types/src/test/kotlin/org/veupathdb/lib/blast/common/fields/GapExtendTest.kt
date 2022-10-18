package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagGapExtend
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagGapExtend)
internal class GapExtendTest {

  @Nested
  @DisplayName("ParseGapExtend()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object containing a non-number value")
    inner class NonString {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseGapExtend(Json.new { put(FlagGapExtend, "yodeling") })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object with no db file value")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted GapExtend instance")
      fun t1() {
        assertTrue(ParseGapExtend(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a text value")
    inner class Present {

      @Test
      @DisplayName("returns a GapExtend instance wrapping")
      fun t1() {
        val inp = Json.newObject { put(FlagGapExtend, 32) }
        assertEquals(32, ParseGapExtend(inp).value)
      }
    }
  }

  @Nested
  @DisplayName("GapExtend()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("default == true")
      fun t1() {
        assertTrue(GapExtend().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        GapExtend().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        GapExtend().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        GapExtend().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when constructed with an argument")
    inner class Arg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(GapExtend(99).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the flag to the JSON object input.")
      fun t2() {
        val inp = Json.newObject()
        GapExtend(3).appendJson(inp)
        assertEquals("""{"$FlagGapExtend":3}""", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the flag to the StringBuilder input.")
      fun t3() {
        val inp = StringBuilder(32)
        GapExtend(69).appendCliSegment(inp)
        assertEquals(" $FlagGapExtend 69", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the flag to the List input.")
      fun t4() {
        val inp = ArrayList<String>(2)
        GapExtend(42).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagGapExtend, inp[0])
        assertEquals("42", inp[1])
      }
    }
  }
  

}