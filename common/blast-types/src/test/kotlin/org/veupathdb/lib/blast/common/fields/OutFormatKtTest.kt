package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.jackson.Json

@DisplayName("ParseOutFormat()")
internal class OutFormatKtTest {

  private inline fun input(f: ObjectNode.() -> Unit): ObjectNode =
    Json.new { with(putObject("-outfmt"), f) }

  @Nested
  @DisplayName("When the input json")
  inner class When {

    @Nested
    @DisplayName("-outfmt value")
    inner class HasFlag {

      @Nested
      @DisplayName("is absent")
      inner class Missing {

        @Test
        @DisplayName("it returns a default OutFormat instance")
        fun t1() {
          assertTrue(ParseOutFormat(Json.newObject()).isDefault)
        }
      }

      @Nested
      @DisplayName("is not an object")
      inner class NonObj {

        @Test
        @DisplayName("it throws an exception")
        fun t1() {
          val inp = Json.new<ObjectNode> { put("-outfmt", "not an object") }

          assertThrows<IllegalArgumentException> {
            ParseOutFormat(inp)
          }
        }
      }
    }

    @Nested
    @DisplayName("-outfmt.delimiter value")
    inner class Delimiter {

      @Nested
      @DisplayName("is absent")
      inner class Missing {

        @Test
        @DisplayName("it sets the default delimiter")
        fun t1() {
          val inp = Json.new<ObjectNode> { with(putObject("-outfmt")) {
            put("type", 1)
            with(putArray("fields")) { add("qgi") }
          } }

          val tgt = ParseOutFormat(inp)

          assertFalse(tgt.isDefault)
          assertTrue(tgt.delimiter.isDefault)
        }
      }

      @Nested
      @DisplayName("is not a string")
      inner class NonString {

        @Test
        @DisplayName("it throws an exception")
        fun t1() {
          val inp = Json.new<ObjectNode> { with(putObject("-outfmt")) {
            put("delimiter", 12)
          } }

          assertThrows<IllegalArgumentException> { ParseOutFormat(inp) }
        }
      }

      @Nested
      @DisplayName("is a string")
      inner class IsString {

        @Test
        @DisplayName("it sets the delimiter value on the new OutFormat instance")
        fun t1() {
          val inp = Json.new<ObjectNode> { with(putObject("-outfmt")) {
            put("delimiter", "hi")
          } }

          assertEquals("hi", ParseOutFormat(inp).delimiter.value)
        }
      }
    }

    @Nested
    @DisplayName("-outfmt.type")
    inner class Type {

      @Nested
      @DisplayName("is absent")
      inner class Missing {

        @Test
        @DisplayName("it sets the default type")
        fun t1() {
          val inp = input {
            put("delimiter", "@")
            with(putArray("fields")) {
              add("std")
            }
          }

          val tgt = ParseOutFormat(inp)

          assertFalse(tgt.isDefault)
          assertTrue(tgt.type.isDefault)
        }
      }

      @Nested
      @DisplayName("is not an int")
      inner class NotInt {

        @Test
        @DisplayName("it throws an exception")
        fun t1() {
          val inp = input { put("type", "hi") }
          assertThrows<IllegalArgumentException> { ParseOutFormat(inp) }
        }
      }

      @Nested
      @DisplayName("is out of the valid range")
      inner class OutOfRange {

        @Test
        @DisplayName("it throws an exception")
        fun t1() {
          val inp1 = input { put("type", -1) }
          assertThrows<IllegalArgumentException> { ParseOutFormat(inp1) }

          val inp2 = input { put("type", 100) }
          assertThrows<IllegalArgumentException> { ParseOutFormat(inp2) }
        }
      }

      @Nested
      @DisplayName("is a valid int value")
      inner class Valid {

        @Test
        @DisplayName("it sets the matching format type")
        fun t1() {
          for (v in FormatType.values()) {
            val inp = input { put("type", v.value) }

            assertEquals(v, ParseOutFormat(inp).type)
          }
        }
      }
    }

    @Nested
    @DisplayName("-outfmt.fields")
    inner class Fields {

      @Nested
      @DisplayName("is not an array")
      inner class NonArray {

        @Test
        @DisplayName("it throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            ParseOutFormat(input { put("fields", "not an array") })
          }
        }
      }

      @Nested
      @DisplayName("contains a value that is not a string")
      inner class NonString {

        @Test
        @DisplayName("it throws an exception")
        fun t1() {
          val inp = input { with(putArray("fields")) { add(12) } }

          assertThrows<IllegalArgumentException> { ParseOutFormat(inp) }
        }
      }

      @Nested
      @DisplayName("contains a value that is not a valid field name")
      inner class Invalid {

        @Test
        @DisplayName("it throws an exception")
        fun t1() {
          val inp = input { with(putArray("fields")) { add("hello") } }

          assertThrows<IllegalArgumentException> { ParseOutFormat(inp) }
        }
      }

      @Nested
      @DisplayName("is absent")
      inner class Absent {

        @Test
        @DisplayName("it sets the default value")
        fun t1() {
          val inp = input {
            put("type", 1)
            put("delimiter", "@")
          }
          val tgt = ParseOutFormat(inp)

          assertFalse(tgt.isDefault)
          assertTrue(tgt.fields.isDefault)
        }
      }

      @Nested
      @DisplayName("is an empty array")
      inner class Empty {

        @Test
        @DisplayName("it sets the default value")
        fun t1() {
          val inp = input {
            put("type", 1)
            put("delimiter", "@")
            putArray("fields")
          }
          val tgt = ParseOutFormat(inp)

          assertFalse(tgt.isDefault)
          assertTrue(tgt.fields.isDefault)
        }
      }

      @Nested
      @DisplayName("contains valid values")
      inner class Valid {

        @Test
        @DisplayName("it sets the given value")
        fun t1() {
          for (v in FormatField.values()) {
            val inp = input { with(putArray("fields")) { add(v.value )} }
            val tgt = ParseOutFormat(inp)

            assertEquals(1, tgt.fields.value.size)
            assertEquals(v, tgt.fields.value[0])
          }
        }
      }
    }
  }
}