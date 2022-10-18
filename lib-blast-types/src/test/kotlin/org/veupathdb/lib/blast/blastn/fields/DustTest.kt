package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.jackson.Json


@DisplayName("Dust")
class DustTest {

  @Test
  @DisplayName("yes() returns YesDust instance.")
  fun test1() {
    assertSame(YesDust, Dust.yes())
  }

  @Test
  @DisplayName("no() returns NoDust instance.")
  fun test2() {
    assertSame(NoDust, Dust.no())
  }

  @Nested
  @DisplayName("#of(Int, Int, Int)")
  inner class Of {

    @Test
    @DisplayName("level =")
    fun test1() {
      val tgt = Dust.of(level = 10)

      assertTrue(tgt is ValDust)
      assertEquals(10, tgt.level)
      assertEquals(64, tgt.window)
      assertEquals(1, tgt.linker)
    }

    @Test
    @DisplayName("window =")
    fun test2() {
      val tgt = Dust.of(window = 10)

      assertTrue(tgt is ValDust)
      assertEquals(20, tgt.level)
      assertEquals(10, tgt.window)
      assertEquals(1, tgt.linker)
    }

    @Test
    @DisplayName("linker =")
    fun test3() {
      val tgt = Dust.of(linker = 10)

      assertTrue(tgt is ValDust)
      assertEquals(20, tgt.level)
      assertEquals(64, tgt.window)
      assertEquals(10, tgt.linker)
    }

    @Test
    @DisplayName("all")
    fun test4() {
      val tgt = Dust.of(10, 11, 12)

      assertTrue(tgt is ValDust)
      assertEquals(10, tgt.level)
      assertEquals(11, tgt.window)
      assertEquals(12, tgt.linker)
    }

  }
}
