package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagDBFile
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.jackson.Json

@DisplayName(FlagDBFile)
internal class DBFileTest : BlastFieldImplTest() {

  override fun blank() = DBFiles()
  override fun populated() = DBFiles("yo-yo", "and'more")
  override val jsonString = """{"$FlagDBFile":["yo-yo","and'more"]}"""
  override val cliString = """$FlagDBFile 'yo-yo and'"'"'more'"""
  override val argList = listOf(FlagDBFile, "yo-yo and'more")

  @Nested
  @DisplayName("ParseDBFile()")
  inner class Parse {

    @Nested
    @DisplayName("when given a JSON object containing a non-text value")
    inner class NonString {

      @Test
      @DisplayName("throws an exception")
      fun t1() {
        assertThrows<IllegalArgumentException> {
          ParseDBFiles(Json.new { put(FlagDBFile, 40) })
        }
      }
    }

    @Nested
    @DisplayName("when given a JSON object with no db file value")
    inner class Absent {

      @Test
      @DisplayName("returns a defaulted DBFile instance")
      fun t1() {
        assertTrue(ParseDBFiles(Json.new {}).isDefault)
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing a text value")
    inner class Present1 {

      @Test
      @DisplayName("returns a DBFile instance wrapping the text value")
      fun t1() {
        val inp = Json.newObject { put(FlagDBFile, "bunny") }
        val out = ParseDBFiles(inp)

        assertEquals(1, out.files.size)
        assertEquals("bunny", out.files[0])
      }
    }

    @Nested
    @DisplayName("when given a JSON object containing an array of text values")
    inner class Present2 {

      @Test
      @DisplayName("returns a DBFile instance wrapping the text values")
      fun t1() {
        val inp = Json.newObject { put(FlagDBFile, Json.newArray(2) {
          add("bunny")
          add("rabbit")
        }) }

        val out = ParseDBFiles(inp)

        assertEquals(2, out.files.size)
        assertEquals("bunny", out.files[0])
        assertEquals("rabbit", out.files[1])
      }
    }
  }
}