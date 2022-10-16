package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagDBGenCode
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagDBGenCode)
internal class DBGenCodeTest {

  @Nested
  @DisplayName("ParseDBGenCode()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object containing a non-number value")
    inner class NonString {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseDBGenCode(Json.new { put(FlagDBGenCode, "yodeling") })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object with no $FlagDBGenCode value")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted DBGenCode instance")
      fun t1() {
        assertTrue(ParseDBGenCode(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a valid int value")
    inner class Present {

      @Test
      @DisplayName("returns a DBGenCode instance wrapping the parsed value")
      fun t1() {
        val inp = Json.newObject { put(FlagDBGenCode, 33) }
        assertEquals(33u.toUByte(), ParseDBGenCode(inp).value)
      }
    }

    @Nested
    @DisplayName("when given a json object containing an invalid int value")
    inner class Invalid {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseDBGenCode(Json.new { put(FlagDBGenCode, 32) })
        }
      }
    }
  }

  @Nested
  @DisplayName("DBGenCode()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("default == true")
      fun t1() {
        assertTrue(DBGenCode().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        DBGenCode().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        DBGenCode().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        DBGenCode().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when constructed with an argument")
    inner class Arg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(DBGenCode(33u).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the flag to the JSON object input.")
      fun t2() {
        val inp = Json.newObject()
        DBGenCode(33u).appendJson(inp)
        assertEquals("""{"$FlagDBGenCode":33}""", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the flag to the StringBuilder input.")
      fun t3() {
        val inp = StringBuilder(32)
        DBGenCode(33u).appendCliSegment(inp)
        assertEquals(" $FlagDBGenCode 33", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the flag to the List input.")
      fun t4() {
        val inp = ArrayList<String>(2)
        DBGenCode(33u).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagDBGenCode, inp[0])
        assertEquals("33", inp[1])
      }
    }
  }
  

}