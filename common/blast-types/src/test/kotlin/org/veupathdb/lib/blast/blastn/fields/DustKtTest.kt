package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.jackson.Json


@DisplayName("DustKt")
class DustKtTest {

  @Nested
  @DisplayName("ParseDust")
  inner class ParseDust {

    @Nested
    @DisplayName("With string -dust value")
    inner class StringInput {

      @Test
      @DisplayName("accepts \"yes\"")
      fun test1() {
        val inp = Json.new<ObjectNode> { put("-dust", "yes") }
        val tgt = ParseDust(inp)

        Assertions.assertTrue(tgt is YesDust)
      }

      @Test
      @DisplayName("accepts \"no\"")
      fun test2() {
        val inp = Json.new<ObjectNode> { put("-dust", "no") }
        val tgt = ParseDust(inp)

        Assertions.assertTrue(tgt is NoDust)
      }

      @Test
      @DisplayName("rejects other strings")
      fun test3() {
        val inp = Json.new<ObjectNode> { put("-dust", "hoopla") }

        assertThrows<IllegalArgumentException> { ParseDust(inp) }
      }
    }

    @Nested
    @DisplayName("With json object -dust value")
    inner class ObjectInput {

      @Test
      @DisplayName("parses all values when all are present.")
      fun test1() {
        val inp = Json.new<ObjectNode> {
          set<ObjectNode>("-dust", Json.new<ObjectNode> {
            put("level", 10)
            put("window", 11)
            put("linker", 12)
          })
        }
        val tgt = ParseDust(inp)

        Assertions.assertTrue(tgt is ValDust)
        Assertions.assertEquals(10, tgt.level)
        Assertions.assertEquals(11, tgt.window)
        Assertions.assertEquals(12, tgt.linker)
      }

      @Test
      @DisplayName("parses level when only value present and defaults others.")
      fun test2() {
        val inp = Json.new<ObjectNode> {
          set<ObjectNode>("-dust", Json.new<ObjectNode> {
            put("level",  10)
          })
        }
        val tgt = ParseDust(inp)

        Assertions.assertTrue(tgt is ValDust)
        Assertions.assertEquals(10, tgt.level)
        Assertions.assertEquals(64, tgt.window)
        Assertions.assertEquals(1, tgt.linker)
      }

      @Test
      @DisplayName("parses window when only value present and defaults others.")
      fun test3() {
        val inp = Json.new<ObjectNode> {
          set<ObjectNode>("-dust", Json.new<ObjectNode> {
            put("window",  10)
          })
        }
        val tgt = ParseDust(inp)

        Assertions.assertTrue(tgt is ValDust)
        Assertions.assertEquals(20, tgt.level)
        Assertions.assertEquals(10, tgt.window)
        Assertions.assertEquals(1, tgt.linker)
      }

      @Test
      @DisplayName("parses linker when only value present and defaults others.")
      fun test4() {
        val inp = Json.new<ObjectNode> {
          set<ObjectNode>("-dust", Json.new<ObjectNode> {
            put("linker",  10)
          })
        }
        val tgt = ParseDust(inp)

        Assertions.assertTrue(tgt is ValDust)
        Assertions.assertEquals(20, tgt.level)
        Assertions.assertEquals(64, tgt.window)
        Assertions.assertEquals(10, tgt.linker)
      }

      @Test
      @DisplayName("defaults all values when no relevant properties are present")
      fun test5() {
        val inp = Json.new<ObjectNode> { putObject("-dust") }
        val tgt = ParseDust(inp)

        Assertions.assertTrue(tgt is ValDust)
        Assertions.assertEquals(20, tgt.level)
        Assertions.assertEquals(64, tgt.window)
        Assertions.assertEquals(1, tgt.linker)
      }

      @Test
      @DisplayName("throws when level property is not an int value")
      fun test6() {
        val inp = Json.new<ObjectNode> { set<ObjectNode>("-dust", Json.new<ObjectNode> {
          put("level", "hi")
        }) }

        assertThrows<IllegalArgumentException> { ParseDust(inp) }
      }

      @Test
      @DisplayName("throws when window property is not an int value")
      fun test7() {
        val inp = Json.new<ObjectNode> { set<ObjectNode>("-dust", Json.new<ObjectNode> {
          put("window", "hi")
        }) }

        assertThrows<IllegalArgumentException> { ParseDust(inp) }
      }

      @Test
      @DisplayName("throws when window property is not an int value")
      fun test8() {
        val inp = Json.new<ObjectNode> { set<ObjectNode>("-dust", Json.new<ObjectNode> {
          put("linker", "hi")
        }) }

        assertThrows<IllegalArgumentException> { ParseDust(inp) }
      }
    }

    @Nested
    @DisplayName("With no -dust value")
    inner class NoInput {

      @Test
      @DisplayName("defaults all values.")
      fun test1() {
        val inp = Json.newObject()
        val tgt = ParseDust(inp)

        Assertions.assertTrue(tgt is ValDust)
        Assertions.assertEquals(20, tgt.level)
        Assertions.assertEquals(64, tgt.window)
        Assertions.assertEquals(1, tgt.linker)
      }
    }

    @Nested
    @DisplayName("With non-string, non-object -dust value")
    inner class BadInput {

      @Test
      @DisplayName("throws.")
      fun test1() {
        val inp = Json.new<ObjectNode> { putArray("-dust") }

        assertThrows<IllegalArgumentException> { ParseDust(inp) }
      }
    }
  }
}
