package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagPseudoCount
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagPseudoCount)
internal class PseudoCountTest {

  @Nested
  @DisplayName("ParsePseudoCount()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object containing a non-number value")
    inner class NonString {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParsePseudoCount(Json.new { put(FlagPseudoCount, "yodeling") })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object with no db file value")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted PseudoCount instance")
      fun t1() {
        assertTrue(ParsePseudoCount(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a text value")
    inner class Present {

      @Test
      @DisplayName("returns a PseudoCount instance wrapping")
      fun t1() {
        val inp = Json.newObject { put(FlagPseudoCount, 32) }
        assertEquals(32, ParsePseudoCount(inp).value)
      }
    }
  }

  @Nested
  @DisplayName("PseudoCount()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("default == true")
      fun t1() {
        assertTrue(PseudoCount().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        PseudoCount().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        PseudoCount().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        PseudoCount().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when constructed with an argument")
    inner class Arg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(PseudoCount(99).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the flag to the JSON object input.")
      fun t2() {
        val inp = Json.newObject()
        PseudoCount(3).appendJson(inp)
        assertEquals("""{"$FlagPseudoCount":3}""", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the flag to the StringBuilder input.")
      fun t3() {
        val inp = StringBuilder(32)
        PseudoCount(69).appendCliSegment(inp)
        assertEquals(" $FlagPseudoCount 69", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the flag to the List input.")
      fun t4() {
        val inp = ArrayList<String>(2)
        PseudoCount(42).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagPseudoCount, inp[0])
        assertEquals("42", inp[1])
      }
    }
  }
  

}