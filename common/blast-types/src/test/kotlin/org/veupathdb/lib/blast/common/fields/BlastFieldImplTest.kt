package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.jackson.Json

abstract class BlastFieldImplTest {
  protected abstract fun blank(): BlastField

  protected abstract fun populated(): BlastField

  protected abstract val jsonString: String

  protected abstract val cliString: String

  protected abstract val argList: List<String>

  @Nested
  @DisplayName("constructor()")
  inner class ConstructorBF {

    @Nested
    @DisplayName("when constructed with no args")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(blank().isDefault)
      }

      @Test
      @DisplayName("appendJson() does nothing")
      fun t2() {
        val inp = Json.newObject()
        blank().appendJson(inp)
        assertEquals("{}", inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() does nothing")
      fun t3() {
        val inp = StringBuilder(0)
        blank().appendCliSegment(inp)
        assertEquals("", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() does nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        blank().appendCliParts(inp)
        assertTrue(inp.isEmpty())
      }

      @Test
      @DisplayName("toJson() returns an empty JSON object")
      fun t5() {
        assertEquals("{}", blank().toJson().toString())
      }
    }

    @Nested
    @DisplayName("when constructed with valid args")
    inner class Args {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(populated().isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the field value to the input JSON object")
      fun t2() {
        val inp = Json.newObject()
        populated().appendJson(inp)
        assertEquals(jsonString, inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the field value to the input StringBuilder")
      fun t3() {
        val inp = StringBuilder(32)
        populated().appendCliSegment(inp)
        assertEquals(" $cliString", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the field value to the input List")
      fun t4() {
        val inp = ArrayList<String>(2)
        populated().appendCliParts(inp)
        assertEquals(argList, inp)
      }

      @Test
      @DisplayName("toJson() returns a populated JSON object")
      fun t5() {
        assertEquals(jsonString, populated().toJson().toString())
      }
    }
  }
}