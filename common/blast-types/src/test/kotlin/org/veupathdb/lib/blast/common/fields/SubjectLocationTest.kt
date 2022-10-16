package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagSubjectLocation
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagSubjectLocation)
class SubjectLocationTest : BlastFieldImplTest() {

  override fun blank() = SubjectLocation()
  override fun populated() = SubjectLocation(10u, 11u)
  override val jsonString = """{"$FlagSubjectLocation":{"start":10,"stop":11}}"""
  override val cliString = "$FlagSubjectLocation '10-11'"
  override val argList = listOf(FlagSubjectLocation, "10-11")

  @Nested
  @DisplayName("ParseSubjectLocation()")
  inner class Parse {

    @Nested
    @DisplayName("when given an input JSON object")
    inner class When {

      @Nested
      @DisplayName("that does not contain a $FlagSubjectLocation property")
      inner class Absent {

        @Test
        @DisplayName("returns a defaulted instance")
        fun t1() = assertTrue(ParseSubjectLocation(Json.new {}).isDefault)
      }

      @Nested
      @DisplayName("that contains a non-object $FlagSubjectLocation value")
      inner class NonObject {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            ParseSubjectLocation(Json.new { put(FlagSubjectLocation, "object") })
          }
        }
      }

      @Nested
      @DisplayName("that contains an empty $FlagSubjectLocation object")
      inner class EmptyObject {

        @Test
        @DisplayName("returns a defaulted instance")
        fun t1() = assertTrue(ParseSubjectLocation(Json.new { put(FlagSubjectLocation, Json.newObject())}).isDefault)
      }

      @Nested
      @DisplayName("that contains a correctly populated $FlagSubjectLocation object")
      inner class Happy {

        @Test
        @DisplayName("returns an instance populated with the parsed values")
        fun t1() {
          val inp = Json.newObject {
            set<ObjectNode>(FlagSubjectLocation, Json.newObject {
              put("start", 10)
              put("stop", 12)
            })
          }

          val tgt = ParseSubjectLocation(inp)

          assertEquals(10u, tgt.start)
          assertEquals(12u, tgt.stop)
        }

      }

      @Nested
      @DisplayName("that contains a $FlagSubjectLocation object with a non-int \"start\" value")
      inner class BadStartType {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            val inp = Json.newObject {
              set<ObjectNode>(FlagSubjectLocation, Json.newObject {
                put("start", "hi")
              })
            }

            ParseSubjectLocation(inp)
          }
        }
      }

      @Nested
      @DisplayName("that contains a $FlagSubjectLocation object with a \"start\" value less than zero")
      inner class BadStartValue {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            val inp = Json.newObject {
              set<ObjectNode>(FlagSubjectLocation, Json.newObject {
                put("start", -1)
              })
            }

            ParseSubjectLocation(inp)
          }
        }
      }

      @Nested
      @DisplayName("that contains a $FlagSubjectLocation object with a non-int \"stop\" value")
      inner class BadStopType {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            val inp = Json.newObject {
              set<ObjectNode>(FlagSubjectLocation, Json.newObject {
                put("stop", "hi")
              })
            }

            ParseSubjectLocation(inp)
          }
        }
      }

      @Nested
      @DisplayName("that contains a $FlagSubjectLocation object with a \"stop\" value less than zero")
      inner class BadStopValue {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            val inp = Json.newObject {
              set<ObjectNode>(FlagSubjectLocation, Json.newObject {
                put("stop", -1)
              })
            }

            ParseSubjectLocation(inp)
          }
        }
      }

      @Nested
      @DisplayName("that contains a $FlagSubjectLocation object with a \"stop\" value that is less than its \"start\" value")
      inner class LT {

        @Test
        @DisplayName("throws an exception")
        fun t1() {
          assertThrows<IllegalArgumentException> {
            val inp = Json.newObject {
              set<ObjectNode>(FlagSubjectLocation, Json.newObject {
                put("start", 5)
                put("stop", 1)
              })
            }

            ParseSubjectLocation(inp)
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
        assertThrows<IllegalArgumentException> { SubjectLocation(11u, 10u) }
      }
    }
  }
}