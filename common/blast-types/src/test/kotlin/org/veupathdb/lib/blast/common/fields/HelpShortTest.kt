package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.jackson.Json

@DisplayName("-h")
internal class HelpShortTest {

  @Test
  @DisplayName("no-arg constructor sets the default to false")
  fun t1() {
    assertFalse(HelpShort().value)
  }

  @Nested
  @DisplayName("when set to true")
  inner class IsTrue {

    @Test
    @DisplayName("isDefault == false")
    fun t1() {
      assertFalse(HelpShort(true).isDefault)
    }

    @Test
    @DisplayName("appendJson sets the field to the given json object")
    fun t2() {
      val inp = Json.newObject()

      HelpShort(true).appendJson(inp)

      assertEquals(1, inp.size())
      assertTrue(inp.has("-h"))
      assertTrue(inp["-h"].isBoolean)
      assertTrue(inp["-h"].booleanValue())
    }

    @Test
    @DisplayName("appendCliSegment appends the field to the given builder")
    fun t3() {
      val inp = StringBuilder(6)

      HelpShort(true).appendCliSegment(inp)

      assertEquals(" -h", inp.toString())
    }

    @Test
    @DisplayName("appendCliParts adds the field to the given list")
    fun t4() {
      val inp = ArrayList<String>(1)

      HelpShort(true).appendCliParts(inp)

      assertEquals(1, inp.size)
      assertEquals("-h", inp[0])
    }
  }


  @Nested
  @DisplayName("when set to false")
  inner class IsFalse {

    @Test
    @DisplayName("isDefault == true")
    fun t1() {
      assertTrue(HelpShort(false).isDefault)
    }

    @Test
    @DisplayName("appendJson sets nothing on the input json object")
    fun t2() {
      val inp = Json.newObject()

      HelpShort(false).appendJson(inp)

      assertEquals(0, inp.size())
    }

    @Test
    @DisplayName("appendCliSegment appends nothing to the given builder")
    fun t3() {
      val inp = StringBuilder(0)

      HelpShort(false).appendCliSegment(inp)

      assertEquals(0, inp.length)
    }

    @Test
    @DisplayName("appendCliParts adds nothing to the given list")
    fun t4() {
      val inp = ArrayList<String>(0)

      HelpShort(false).appendCliParts(inp)

      assertTrue(inp.isEmpty())
    }
  }
}