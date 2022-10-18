package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagDBSize
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagDBSize)
internal class DBSizeTest {

  @Nested
  @DisplayName("ParseDBSize()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object containing a non-number value")
    inner class NonString {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseDBSize(Json.new { put(FlagDBSize, "yodeling") })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object with no db file value")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted DBSize instance")
      fun t1() {
        assertTrue(ParseDBSize(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a text value")
    inner class Present {

      @Test
      @DisplayName("returns a DBSize instance wrapping")
      fun t1() {
        val inp = Json.newObject { put(FlagDBSize, 32) }
        assertEquals(32, ParseDBSize(inp).value)
      }
    }
  }

  @Nested
  @DisplayName("DBSize()")
  inner class Type {

    @Nested
    @DisplayName("when constructed with no arguments")
    inner class NoArg {

      @Test
      @DisplayName("default == true")
      fun t1() {
        assertTrue(DBSize().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        DBSize().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        DBSize().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        DBSize().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }
    }

    @Nested
    @DisplayName("when constructed with an argument")
    inner class Arg {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(DBSize(99).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the flag to the JSON object input.")
      fun t2() {
        val inp = Json.newObject()
        DBSize(3).appendJson(inp)
        assertEquals("""{"$FlagDBSize":3}""", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the flag to the StringBuilder input.")
      fun t3() {
        val inp = StringBuilder(32)
        DBSize(69).appendCliSegment(inp)
        assertEquals(" $FlagDBSize 69", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the flag to the List input.")
      fun t4() {
        val inp = ArrayList<String>(2)
        DBSize(42).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagDBSize, inp[0])
        assertEquals("42", inp[1])
      }
    }
  }
  

}