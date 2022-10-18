package org.veupathdb.lib.blast.formatter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.common.BlastCLIImplTest
import org.veupathdb.lib.blast.common.FlagArchive
import org.veupathdb.lib.blast.common.FlagRID
import org.veupathdb.lib.blast.formatter.fields.Archive
import org.veupathdb.lib.blast.formatter.fields.RID

@DisplayName("blast_formatter")
internal class BlastFormatterImplTest : BlastCLIImplTest() {
  override fun getEmptyImpl(): BlastFormatter = BlastFormatterImpl()

  @Nested
  @DisplayName(FlagRID)
  inner class BlastCLIRIDFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIRIDFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.rid = RID("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagRID))
        assertTrue(json[FlagRID].isTextual)
        assertEquals("hi", json[FlagRID].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIRIDCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.rid = RID("hi")

        assertEquals("${tgt.tool.value} $FlagRID 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.rid = RID("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagRID, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagArchive)
  inner class BlastCLIArchiveFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIArchiveFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.archive = Archive("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagArchive))
        assertTrue(json[FlagArchive].isTextual)
        assertEquals("hi", json[FlagArchive].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIArchiveCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.archive = Archive("hi")

        assertEquals("${tgt.tool.value} $FlagArchive 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.archive = Archive("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagArchive, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }
}