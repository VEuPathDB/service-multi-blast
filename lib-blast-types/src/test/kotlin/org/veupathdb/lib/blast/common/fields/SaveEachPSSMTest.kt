package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.*
import org.veupathdb.lib.blast.common.FlagRemote
import org.veupathdb.lib.blast.common.FlagSaveEachPSSM
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagSaveEachPSSM)
class SaveEachPSSMTest : BlastFieldImplTest() {
  override fun blank() = SaveEachPSSM()
  override fun populated() = SaveEachPSSM(true)
  override val jsonString = """{"$FlagSaveEachPSSM":true}"""
  override val cliString = FlagSaveEachPSSM
  override val argList = listOf(FlagSaveEachPSSM)

  @Nested
  @DisplayName("ParseSaveEachPSSM()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object that does not contain this field")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted SaveEachPSSM instance")
      fun t1() {
        Assertions.assertTrue(ParseSaveEachPSSM(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object that contains a non boolean value")
    inner class NonBoolean {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseSaveEachPSSM(Json.new { put(FlagSaveEachPSSM, 666) })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object that contains a boolean value")
    inner class IsBoolean {

      @Test
      @DisplayName("returns a SaveEachPSSM instance wrapping that value")
      fun t1() {
        val inp = Json.newObject { put(FlagSaveEachPSSM, true) }
        Assertions.assertTrue(ParseSaveEachPSSM(inp).value)
      }
    }
  }
}