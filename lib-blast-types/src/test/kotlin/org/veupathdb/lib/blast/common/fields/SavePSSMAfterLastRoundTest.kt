package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.*
import org.veupathdb.lib.blast.common.FlagRemote
import org.veupathdb.lib.blast.common.FlagSavePSSMAfterLastRound
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagSavePSSMAfterLastRound)
class SavePSSMAfterLastRoundTest : BlastFieldImplTest() {
  override fun blank() = SavePSSMAfterLastRound()
  override fun populated() = SavePSSMAfterLastRound(true)
  override val jsonString = """{"$FlagSavePSSMAfterLastRound":true}"""
  override val cliString = FlagSavePSSMAfterLastRound
  override val argList = listOf(FlagSavePSSMAfterLastRound)

  @Nested
  @DisplayName("ParseSavePSSMAfterLastRound()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object that does not contain this field")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted SavePSSMAfterLastRound instance")
      fun t1() {
        Assertions.assertTrue(ParseSavePSSMAfterLastRound(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object that contains a non boolean value")
    inner class NonBoolean {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseSavePSSMAfterLastRound(Json.new { put(FlagSavePSSMAfterLastRound, 666) })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object that contains a boolean value")
    inner class IsBoolean {

      @Test
      @DisplayName("returns a SavePSSMAfterLastRound instance wrapping that value")
      fun t1() {
        val inp = Json.newObject { put(FlagSavePSSMAfterLastRound, true) }
        Assertions.assertTrue(ParseSavePSSMAfterLastRound(inp).value)
      }
    }
  }
}