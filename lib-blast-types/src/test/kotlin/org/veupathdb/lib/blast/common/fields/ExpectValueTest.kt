package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagExpectValue
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagExpectValue)
internal class ExpectValueTest {

  @Nested
  @DisplayName("ParseExpectValue()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object containing a non-text, non-number value")
    inner class NonString {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseEValue(Json.new { put(FlagExpectValue, true) })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object with no db file value")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted ExpectValue instance")
      fun t1() {
        assertTrue(ParseEValue(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a non-numeric text value")
    inner class PresentNonNumber {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseEValue(Json.new { put(FlagExpectValue, "bunny") })
        }
      }

      @Test
      @DisplayName("throws an exception")
      fun t2() {
        assertThrows<IllegalArgumentException> {
          ParseEValue(Json.new { put(FlagExpectValue, "1..2") })
        }
      }

      @Test
      @DisplayName("throws an exception")
      fun t3() {
        assertThrows<IllegalArgumentException> {
          ParseEValue(Json.new { put(FlagExpectValue, "1.2j+12") })
        }
      }

      @Test
      @DisplayName("throws an exception")
      fun t4() {
        assertThrows<IllegalArgumentException> {
          ParseEValue(Json.new { put(FlagExpectValue, "1.2ee+12") })
        }
      }

      @Test
      @DisplayName("throws an exception")
      fun t5() {
        assertThrows<IllegalArgumentException> {
          ParseEValue(Json.new { put(FlagExpectValue, "1.2+E12") })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a plain, integral numeric text value")
    inner class PresentIntegralText {

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t1() {
        val inp = Json.newObject { put(FlagExpectValue, "123") }
        assertEquals("123", ParseEValue(inp).value)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a plain, integral value")
    inner class PresentIntegral {

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t1() {
        val inp = Json.newObject { put(FlagExpectValue, 123) }
        assertEquals("123", ParseEValue(inp).value)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a plain, decimal numeric text value")
    inner class PresentDecimalText {

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t1() {
        val inp = Json.newObject { put(FlagExpectValue, "12.3") }
        assertEquals("12.3", ParseEValue(inp).value)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a plain, decimal value")
    inner class PresentDecimal {

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t1() {
        val inp = Json.newObject { put(FlagExpectValue, 12.3) }
        assertEquals("12.3", ParseEValue(inp).value)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a plain, integral numeric text value in scientific notation")
    inner class PresentIntegralSciText {

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t1() {
        val inp = Json.newObject { put(FlagExpectValue, "12e+10") }
        assertEquals("12e+10", ParseEValue(inp).value)
      }

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t2() {
        val inp = Json.newObject { put(FlagExpectValue, "12E+10") }
        assertEquals("12E+10", ParseEValue(inp).value)
      }

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t3() {
        val inp = Json.newObject { put(FlagExpectValue, "12e-10") }
        assertEquals("12e-10", ParseEValue(inp).value)
      }

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t4() {
        val inp = Json.newObject { put(FlagExpectValue, "12E-10") }
        assertEquals("12E-10", ParseEValue(inp).value)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a plain, decimal numeric text value in scientific notation")
    inner class PresentDecimalSciText {

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t1() {
        val inp = Json.newObject { put(FlagExpectValue, "1.2e+10") }
        assertEquals("1.2e+10", ParseEValue(inp).value)
      }

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t2() {
        val inp = Json.newObject { put(FlagExpectValue, "1.2E+10") }
        assertEquals("1.2E+10", ParseEValue(inp).value)
      }

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t3() {
        val inp = Json.newObject { put(FlagExpectValue, "1.2e-10") }
        assertEquals("1.2e-10", ParseEValue(inp).value)
      }

      @Test
      @DisplayName("returns a ExpectValue instance wrapping the given value")
      fun t4() {
        val inp = Json.newObject { put(FlagExpectValue, "1.2E-10") }
        assertEquals("1.2E-10", ParseEValue(inp).value)
      }
    }
  }

  @Nested
  @DisplayName("ExpectValue()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("default == true")
      fun t1() {
        assertTrue(ExpectValue().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        ExpectValue().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        ExpectValue().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        ExpectValue().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when constructed with an argument")
    inner class Arg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(ExpectValue("12").isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the flag to the JSON object input.")
      fun t2() {
        val inp = Json.newObject()
        ExpectValue("13").appendJson(inp)
        assertEquals("""{"$FlagExpectValue":"13"}""", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the flag to the StringBuilder input.")
      fun t3() {
        val inp = StringBuilder(32)
        ExpectValue("75").appendCliSegment(inp)
        assertEquals(" $FlagExpectValue '75'", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the flag to the List input.")
      fun t4() {
        val inp = ArrayList<String>(2)
        ExpectValue("1E+23").appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagExpectValue, inp[0])
        assertEquals("1E+23", inp[1])
      }
    }
  }
}