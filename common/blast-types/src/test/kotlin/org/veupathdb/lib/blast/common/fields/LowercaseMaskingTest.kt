package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagLowercaseMasking
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagLowercaseMasking)
class LowercaseMaskingTest {

  @Nested
  @DisplayName("ParseLowercaseMasking()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object that does not contain this field")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted LowercaseMasking instance")
      fun t1() {
        assertTrue(ParseLowercaseMasking(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object that contains a non boolean value")
    inner class NonBoolean {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseLowercaseMasking(Json.new { put(FlagLowercaseMasking, 666) })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object that contains a boolean value")
    inner class IsBoolean {

      @Test
      @DisplayName("returns a LowercaseMasking instance wrapping that value")
      fun t1() {
        val inp = Json.newObject { put(FlagLowercaseMasking, true) }
        assertTrue(ParseLowercaseMasking(inp).value)
      }
    }
  }

  @Nested
  @DisplayName("LowercaseMasking()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(LowercaseMasking().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        LowercaseMasking().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        LowercaseMasking().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        LowercaseMasking().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when constructed with the value `false`")
    inner class False {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(LowercaseMasking(false).isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        LowercaseMasking(false).appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        LowercaseMasking(false).appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        LowercaseMasking(false).appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when constructed with the value `true`")
    inner class True {


      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(LowercaseMasking(true).isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        LowercaseMasking(true).appendJson(inp)
        assertEquals("""{"$FlagLowercaseMasking":true}""", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        LowercaseMasking(true).appendCliSegment(inp)
        assertEquals(" $FlagLowercaseMasking", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        LowercaseMasking(true).appendCliParts(inp)
        assertEquals(1, inp.size)
        assertEquals(FlagLowercaseMasking, inp[0])
      }
    }
  }
}