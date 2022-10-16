package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagMultiThreadingMode
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagMultiThreadingMode)
internal class MultiThreadingModeTest {

  @Nested
  @DisplayName("ParseMultiThreadingMode()")
  inner class ParseAuto {

    @Test
    @DisplayName("Returns SplitByDatabase MultiThreadingMode flag when given 0")
    fun t1() {
      val inp = Json.newObject { put(FlagMultiThreadingMode, 0) }
      val tgt = ParseMultiThreadingMode(inp)

      assertEquals(MultiThreadingModeValue.SplitByDatabase, tgt.value)
    }

    @Test
    @DisplayName("Returns SplitByQuery MultiThreadingMode flag when given 1")
    fun t2() {
      val inp = Json.newObject { put(FlagMultiThreadingMode, 1) }
      val tgt = ParseMultiThreadingMode(inp)

      assertEquals(MultiThreadingModeValue.SplitByQuery, tgt.value)
    }

    @Test
    @DisplayName("Throws when given a value greater than 1")
    fun t3() {
      val inp = Json.newObject { put(FlagMultiThreadingMode, 2) }

      assertThrows<IllegalArgumentException> { ParseMultiThreadingMode(inp) }
    }

    @Test
    @DisplayName("Throws when given a non numeric value")
    fun t4() {
      val inp = Json.newObject { put(FlagMultiThreadingMode, "hi") }

      assertThrows<IllegalArgumentException> { ParseMultiThreadingMode(inp) }
    }
  }

  @Nested
  @DisplayName("MultiThreadingMode")
  inner class TypeTest {

    @Nested
    @DisplayName("When instantiated with a no-arg constructor")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(MultiThreadingMode().isDefault)
      }

      @Test
      @DisplayName("appendJson() appends nothing")
      fun t2() {
        val inp = Json.newObject()
        MultiThreadingMode().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() appends nothing")
      fun t3() {
        val inp = StringBuilder(0)
        MultiThreadingMode().appendCliSegment(inp)
        assertEquals(0, inp.length)
      }

      @Test
      @DisplayName("appendCliParts() appends nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        MultiThreadingMode().appendCliParts(inp)
        assertEquals(0, inp.size)
      }

      @Test
      @DisplayName("value == SplitByDatabase")
      fun t5() {
        assertEquals(MultiThreadingModeValue.SplitByDatabase, MultiThreadingMode().value)
      }
    }

    @Nested
    @DisplayName("When instantiated with the SplitByDatabase value")
    inner class SplitByDatabase {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(MultiThreadingMode(MultiThreadingModeValue.SplitByDatabase).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends nothing")
      fun t2() {
        val inp = Json.newObject()
        MultiThreadingMode(MultiThreadingModeValue.SplitByDatabase).appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() appends nothing")
      fun t3() {
        val inp = StringBuilder(0)
        MultiThreadingMode(MultiThreadingModeValue.SplitByDatabase).appendCliSegment(inp)
        assertEquals(0, inp.length)
      }

      @Test
      @DisplayName("appendCliParts() appends nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        MultiThreadingMode(MultiThreadingModeValue.SplitByDatabase).appendCliParts(inp)
        assertEquals(0, inp.size)
      }

      @Test
      @DisplayName("value == SplitByDatabase")
      fun t5() {
        assertEquals(MultiThreadingModeValue.SplitByDatabase, MultiThreadingMode(MultiThreadingModeValue.SplitByDatabase).value)
      }
    }


    @Nested
    @DisplayName("When instantiated with the SplitByQuery value")
    inner class SplitByQuery {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(MultiThreadingMode(MultiThreadingModeValue.SplitByQuery).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the flag")
      fun t2() {
        val inp = Json.newObject()
        MultiThreadingMode(MultiThreadingModeValue.SplitByQuery).appendJson(inp)
        assertEquals("""{"$FlagMultiThreadingMode":1}""".trimIndent(), inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the flag")
      fun t3() {
        val inp = StringBuilder(0)
        MultiThreadingMode(MultiThreadingModeValue.SplitByQuery).appendCliSegment(inp)
        assertEquals(" $FlagMultiThreadingMode 1", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the flag")
      fun t4() {
        val inp = ArrayList<String>(0)
        MultiThreadingMode(MultiThreadingModeValue.SplitByQuery).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagMultiThreadingMode, inp[0])
        assertEquals("1", inp[1])
      }

      @Test
      @DisplayName("value == SplitByQuery")
      fun t5() {
        assertEquals(MultiThreadingModeValue.SplitByQuery, MultiThreadingMode(MultiThreadingModeValue.SplitByQuery).value)
      }
    }
  }
}