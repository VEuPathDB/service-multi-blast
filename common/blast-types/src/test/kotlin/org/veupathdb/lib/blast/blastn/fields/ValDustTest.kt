package org.veupathdb.lib.blast.blastn.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.jackson.Json

@DisplayName("ValDust")
class ValDustTest {

  @Test
  @DisplayName("isYes == false")
  fun test1() {
    assertFalse(ValDust().isYes)
  }

  @Test
  @DisplayName("isNo == false")
  fun test2() {
    assertFalse(ValDust().isNo)
  }

  @Test
  @DisplayName("isDefault == true with no-arg constructor")
  fun test3() {
    assertTrue(ValDust().isDefault)
  }

  @Test
  @DisplayName("isDefault == false with non-default level")
  fun test4() {
    assertFalse(ValDust(level = 1).isDefault)
  }

  @Test
  @DisplayName("isDefault == false with non-default window")
  fun test5() {
    assertFalse(ValDust(window = 1).isDefault)
  }

  @Test
  @DisplayName("isDefault == false with non-default linker")
  fun test6() {
    assertFalse(ValDust(linker = 2).isDefault)
  }

  @Nested
  @DisplayName("With default values")
  inner class Defaults {

    @Test
    @DisplayName("#appendJson(ObjectNode) does not mutate the input.")
    fun test1() {
      val inp = Json.newObject()

      ValDust().appendJson(inp)

      assertEquals(0, inp.size())
    }

    @Test
    @DisplayName("#appendCliSegment(StringBuilder) does not mutate the input.")
    fun test2() {
      val inp = StringBuilder()

      ValDust().appendCliSegment(inp)

      assertEquals(0, inp.length)
    }

    @Test
    @DisplayName("#appendCliParts(List) does not mutate the input.")
    fun test3() {
      val inp = ArrayList<String>(0)

      ValDust().appendCliParts(inp)

      assertEquals(0, inp.size)
    }
  }

  @Nested
  @DisplayName("With non-default values")
  inner class NonDefaults {

    @Test
    @DisplayName("#appendJson(ObjectNode) appends dust values as an object")
    fun test1() {
      val tgt = ValDust(1, 2, 3)
      val inp = Json.newObject()

      tgt.appendJson(inp)

      assertEquals("""{"-dust":{"level":1,"window":2,"linker":3}}""", inp.toString())
    }

    @Test
    @DisplayName("#appendCliSegment(StringBuilder) appends dust cli arg string")
    fun test2() {
      val tgt = ValDust(2, 3, 4)
      val inp = StringBuilder()

      tgt.appendCliSegment(inp)

      assertEquals(" -dust '2 3 4'", inp.toString())
    }

    @Test
    @DisplayName("#appendCliParts(List) appends dust cli arg parts")
    fun test3() {
      val tgt = ValDust(3, 4, 5)
      val inp = ArrayList<String>(2)

      tgt.appendCliParts(inp)

      assertEquals(2, inp.size)
      assertEquals("-dust", inp[0])
      assertEquals("3 4 5", inp[1])
    }
  }
}