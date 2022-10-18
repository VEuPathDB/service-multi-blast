package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagQueryLocation
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagQueryLocation)
class QueryLocationTest : BlastFieldImplTest() {

  override fun blank() = QueryLocation()
  override fun populated() = QueryLocation(10u, 11u)
  override val jsonString = """{"$FlagQueryLocation":{"start":10,"stop":11}}"""
  override val cliString = "$FlagQueryLocation '10-11'"
  override val argList = listOf(FlagQueryLocation, "10-11")

  @Nested
  @DisplayName("ParseQueryLocation()")
  inner class Parse {

    @Nested
    @DisplayName("when given an input JSON object")
    inner class When {

      @Nested
      @DisplayName("that does not contain a $FlagQueryLocation property")
      inner class Absent {

        @Test
        @DisplayName("returns a defaulted instance")
        fun t1() = assertTrue(ParseQueryLocation(Json.new {}).isDefault)
      }

      @Nested
      @DisplayName("that contains a non-object $FlagQueryLocation value")
      inner class NonObject {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            ParseQueryLocation(Json.new { put(FlagQueryLocation, "object") })
          }
        }
      }

      @Nested
      @DisplayName("that contains an empty $FlagQueryLocation object")
      inner class EmptyObject {

        @Test
        @DisplayName("returns a defaulted instance")
        fun t1() = assertTrue(ParseQueryLocation(Json.new { put(FlagQueryLocation, Json.newObject())}).isDefault)
      }

      @Nested
      @DisplayName("that contains a correctly populated $FlagQueryLocation object")
      inner class Happy {

        @Test
        @DisplayName("returns an instance populated with the parsed values")
        fun t1() {
          val inp = Json.newObject {
            set<ObjectNode>(FlagQueryLocation, Json.newObject {
              put("start", 10)
              put("stop", 12)
            })
          }

          val tgt = ParseQueryLocation(inp)

          assertEquals(10u, tgt.start)
          assertEquals(12u, tgt.stop)
        }

      }

      @Nested
      @DisplayName("that contains a $FlagQueryLocation object with a non-int \"start\" value")
      inner class BadStartType {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            val inp = Json.newObject {
              set<ObjectNode>(FlagQueryLocation, Json.newObject {
                put("start", "hi")
              })
            }

            ParseQueryLocation(inp)
          }
        }
      }

      @Nested
      @DisplayName("that contains a $FlagQueryLocation object with a \"start\" value less than zero")
      inner class BadStartValue {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            val inp = Json.newObject {
              set<ObjectNode>(FlagQueryLocation, Json.newObject {
                put("start", -1)
              })
            }

            ParseQueryLocation(inp)
          }
        }
      }

      @Nested
      @DisplayName("that contains a $FlagQueryLocation object with a non-int \"stop\" value")
      inner class BadStopType {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            val inp = Json.newObject {
              set<ObjectNode>(FlagQueryLocation, Json.newObject {
                put("stop", "hi")
              })
            }

            ParseQueryLocation(inp)
          }
        }
      }

      @Nested
      @DisplayName("that contains a $FlagQueryLocation object with a \"stop\" value less than zero")
      inner class BadStopValue {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            val inp = Json.newObject {
              set<ObjectNode>(FlagQueryLocation, Json.newObject {
                put("stop", -1)
              })
            }

            ParseQueryLocation(inp)
          }
        }
      }

      @Nested
      @DisplayName("that contains a $FlagQueryLocation object with a \"stop\" value that is less than its \"start\" value")
      inner class LT {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            val inp = Json.newObject {
              set<ObjectNode>(FlagQueryLocation, Json.newObject {
                put("start", 5)
                put("stop", 1)
              })
            }

            ParseQueryLocation(inp)
          }
        }
      }
    }
  }

  @Nested
  @DisplayName("constructor()")
  inner class Type {

    @Nested
    @DisplayName("when the start value is less than the stop value")
    inner class Invalid {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> { QueryLocation(11u, 10u) }
      }
    }
  }
}