package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.*
import org.veupathdb.lib.blast.common.FlagRemote
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagRemote)
class RemoteTest : BlastFieldImplTest() {
  override fun blank() = Remote()
  override fun populated() = Remote(true)
  override val jsonString = """{"$FlagRemote":true}"""
  override val cliString = FlagRemote
  override val argList = listOf(FlagRemote)

  @Nested
  @DisplayName("ParseRemote()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object that does not contain this field")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted Remote instance")
      fun t1() {
        Assertions.assertTrue(ParseRemote(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object that contains a non boolean value")
    inner class NonBoolean {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseRemote(Json.new { put(FlagRemote, 666) })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object that contains a boolean value")
    inner class IsBoolean {

      @Test
      @DisplayName("returns a Remote instance wrapping that value")
      fun t1() {
        val inp = Json.newObject { put(FlagRemote, true) }
        Assertions.assertTrue(ParseRemote(inp).value)
      }
    }
  }
}