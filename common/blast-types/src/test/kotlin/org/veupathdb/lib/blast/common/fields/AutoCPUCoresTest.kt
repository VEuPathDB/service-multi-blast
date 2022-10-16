package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagNumThreads
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagNumThreads)
internal class AutoCPUCoresTest {

  @Nested
  @DisplayName("ParseAutoCPUCores()")
  inner class ParseAuto {

    @Test
    @DisplayName("Returns enabled AutoCPUCores flag when given 0")
    fun t1() {
      val inp = Json.newObject { put(FlagNumThreads, 0) }
      val tgt = ParseAutoCPUCores(inp)

      assertEquals(AutoCPUCoresValue.Enable, tgt.value)
    }

    @Test
    @DisplayName("Returns disabled AutoCPUCores flag when given 1")
    fun t2() {
      val inp = Json.newObject { put(FlagNumThreads, 1) }
      val tgt = ParseAutoCPUCores(inp)

      assertEquals(AutoCPUCoresValue.Disable, tgt.value)
    }

    @Test
    @DisplayName("Throws when given a value greater than 1")
    fun t3() {
      val inp = Json.newObject { put(FlagNumThreads, 2) }

      assertThrows<IllegalArgumentException> { ParseAutoCPUCores(inp) }
    }

    @Test
    @DisplayName("Throws when given a non numeric value")
    fun t4() {
      val inp = Json.newObject { put(FlagNumThreads, "hi") }

      assertThrows<IllegalArgumentException> { ParseAutoCPUCores(inp) }
    }
  }

  @Nested
  @DisplayName("AutoCPUCores")
  inner class TypeTest {

    @Nested
    @DisplayName("When instantiated with a no-arg constructor")
    inner class NoArg {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(AutoCPUCores().isDefault)
      }

      @Test
      @DisplayName("appendJson() appends nothing")
      fun t2() {
        val inp = Json.newObject()
        AutoCPUCores().appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() appends nothing")
      fun t3() {
        val inp = StringBuilder(0)
        AutoCPUCores().appendCliSegment(inp)
        assertEquals(0, inp.length)
      }

      @Test
      @DisplayName("appendCliParts() appends nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        AutoCPUCores().appendCliParts(inp)
        assertEquals(0, inp.size)
      }

      @Test
      @DisplayName("value == Disable")
      fun t5() {
        assertEquals(AutoCPUCoresValue.Disable, AutoCPUCores().value)
      }
    }

    @Nested
    @DisplayName("When instantiated with the Disable value")
    inner class Disable {

      @Test
      @DisplayName("isDefault == true")
      fun t1() {
        assertTrue(AutoCPUCores(AutoCPUCoresValue.Disable).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends nothing")
      fun t2() {
        val inp = Json.newObject()
        AutoCPUCores(AutoCPUCoresValue.Disable).appendJson(inp)
        assertEquals(0, inp.size())
      }

      @Test
      @DisplayName("appendCliSegment() appends nothing")
      fun t3() {
        val inp = StringBuilder(0)
        AutoCPUCores(AutoCPUCoresValue.Disable).appendCliSegment(inp)
        assertEquals(0, inp.length)
      }

      @Test
      @DisplayName("appendCliParts() appends nothing")
      fun t4() {
        val inp = ArrayList<String>(0)
        AutoCPUCores(AutoCPUCoresValue.Disable).appendCliParts(inp)
        assertEquals(0, inp.size)
      }

      @Test
      @DisplayName("value == Disable")
      fun t5() {
        assertEquals(AutoCPUCoresValue.Disable, AutoCPUCores(AutoCPUCoresValue.Disable).value)
      }
    }


    @Nested
    @DisplayName("When instantiated with the Enable value")
    inner class Enable {

      @Test
      @DisplayName("isDefault == false")
      fun t1() {
        assertFalse(AutoCPUCores(AutoCPUCoresValue.Enable).isDefault)
      }

      @Test
      @DisplayName("appendJson() appends the flag")
      fun t2() {
        val inp = Json.newObject()
        AutoCPUCores(AutoCPUCoresValue.Enable).appendJson(inp)
        assertEquals("""{"$FlagNumThreads":0}""".trimIndent(), inp.toString())
      }

      @Test
      @DisplayName("appendCliSegment() appends the flag")
      fun t3() {
        val inp = StringBuilder(0)
        AutoCPUCores(AutoCPUCoresValue.Enable).appendCliSegment(inp)
        assertEquals(" $FlagNumThreads 0", inp.toString())
      }

      @Test
      @DisplayName("appendCliParts() appends the flag")
      fun t4() {
        val inp = ArrayList<String>(0)
        AutoCPUCores(AutoCPUCoresValue.Enable).appendCliParts(inp)
        assertEquals(2, inp.size)
        assertEquals(FlagNumThreads, inp[0])
        assertEquals("0", inp[1])
      }

      @Test
      @DisplayName("value == Enable")
      fun t5() {
        assertEquals(AutoCPUCoresValue.Enable, AutoCPUCores(AutoCPUCoresValue.Enable).value)
      }
    }
  }
}