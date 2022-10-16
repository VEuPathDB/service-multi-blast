package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagBestHitOverhang
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagBestHitOverhang)
internal class BestHitOverhangTest {

  @Nested
  @DisplayName("ParseBestHitOverhang()")
  inner class Parse {

    @Nested
    @DisplayName("when the given JSON object does not contain this flag")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted BestHitOverhang instance")
      fun t1() {
        assertTrue(ParseBestHitOverhang(Json.newObject()).isDefault)
      }
    }

    @Nested
    @DisplayName("when the given JSON object contains a double value in the set (0..0.5)")
    inner class Present {

      @Test
      @DisplayName("returns a BestHitOverhang instance wrapping the JSON value")
      fun t1() {
        var i = 0.1
        while (i < 0.5) {
          val inp = Json.newObject { put(FlagBestHitOverhang, i) }

          assertEquals(i, ParseBestHitOverhang(inp).value)

          i += 0.1
        }
      }
    }

    @Nested
    @DisplayName("when the given JSON object contains a double value not in the set (0..0.5)")
    inner class OutOfBounds {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        val inp = Json.newObject { put(FlagBestHitOverhang, 0.0) }

        assertThrows<IllegalArgumentException> { ParseBestHitOverhang(inp) }

        inp.put(FlagBestHitOverhang, 0.5)

        assertThrows<IllegalArgumentException> { ParseBestHitOverhang(inp) }
      }
    }

    @Nested
    @DisplayName("when the given JSON object contains a non-numeric value")
    inner class BadType {

      @Test
      @DisplayName("throws an exception")
      fun t2() {
        val inp = Json.newObject { put(FlagBestHitOverhang, "gravy boat") }

        assertThrows<IllegalArgumentException> { ParseBestHitOverhang(inp) }
      }
    }
  }

  @Nested
  @DisplayName("BestHitOverhang()")
  inner class Type {

    @Nested
    @DisplayName("when instantiated with a no-arg constructor")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(BestHitOverhang().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        BestHitOverhang().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        BestHitOverhang().appendCliSegment(inp)
        assertEquals(0, inp.length)
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        BestHitOverhang().appendCliParts(inp)
        assertEquals(0, inp.size)
      }
    }

    @Nested
    @DisplayName("when instantiated with an argument")
    inner class Arg {

      @Nested
      @DisplayName("that is in the range (0..0.5)")
      inner class InRange {

        @Test
        @DisplayName("default == false")
        fun t1() {
          assertFalse(BestHitOverhang(0.1).isDefault)
        }

        @Test
        @DisplayName("appendJson() appends the value to the input JSON object")
        fun t2() {
          val inp = Json.newObject()
          BestHitOverhang(0.1).appendJson(inp)
          assertEquals("{\"$FlagBestHitOverhang\":0.1}", inp.toString())
        }

        @Test
        @DisplayName("appendCliSegment() appends the value to the input StringBuilder")
        fun t3() {
          val inp = StringBuilder(23)
          BestHitOverhang(0.1).appendCliSegment(inp)
          assertEquals(" $FlagBestHitOverhang 0.1", inp.toString())
        }

        @Test
        @DisplayName("appendCliParts() appends the value to the input List")
        fun t4() {
          val inp = ArrayList<String>(2)
          BestHitOverhang(0.1).appendCliParts(inp)
          assertEquals(2, inp.size)
          assertEquals(FlagBestHitOverhang, inp[0])
          assertEquals("0.1", inp[1])
        }
      }

      @Nested
      @DisplayName("that is not in the range (0..0.5)")
      inner class OutOfRange {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> { BestHitOverhang(0.5) }
        }
      }
    }
  }
}