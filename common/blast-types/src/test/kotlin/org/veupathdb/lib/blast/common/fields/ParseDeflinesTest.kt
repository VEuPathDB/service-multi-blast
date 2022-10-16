package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagParseDefLines
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagParseDefLines)
class ParseDeflinesTest {

  @Nested
  @DisplayName("ParseParseDefLines()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object that does not contain this field")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted ParseDefLines instance")
      fun t1() {
        assertTrue(ParseParseDefLines(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object that contains a non boolean value")
    inner class NonBoolean {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseParseDefLines(Json.new { put(FlagParseDefLines, 666) })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object that contains a boolean value")
    inner class IsBoolean {

      @Test
      @DisplayName("returns a ParseDefLines instance wrapping that value")
      fun t1() {
        val inp = Json.newObject { put(FlagParseDefLines, true) }
        assertTrue(ParseParseDefLines(inp).value)
      }
    }
  }

  @Nested
  @DisplayName("ParseDefLines()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(ParseDefLines().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        ParseDefLines().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        ParseDefLines().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        ParseDefLines().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when constructed with the value `false`")
    inner class False {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(ParseDefLines(false).isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        ParseDefLines(false).appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        ParseDefLines(false).appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        ParseDefLines(false).appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when constructed with the value `true`")
    inner class True {


      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(ParseDefLines(true).isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        ParseDefLines(true).appendJson(inp)
        assertEquals("""{"$FlagParseDefLines":true}""", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        ParseDefLines(true).appendCliSegment(inp)
        assertEquals(" $FlagParseDefLines", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        ParseDefLines(true).appendCliParts(inp)
        assertEquals(1, inp.size)
        assertEquals(FlagParseDefLines, inp[0])
      }
    }
  }
}