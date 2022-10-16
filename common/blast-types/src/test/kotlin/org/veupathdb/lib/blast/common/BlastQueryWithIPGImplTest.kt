package org.veupathdb.lib.blast.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.common.fields.*

@DisplayName("Blast Query with IPG Lists Config Interface Compliance")
internal abstract class BlastQueryWithIPGImplTest : BlastQueryWithListsImplTest() {
  abstract override fun getEmptyImpl(): BlastQueryWithIPG

  @Nested
  @DisplayName(FlagIPGList)
  inner class BlastCLIIPGList {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIIPGListJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.ipgListFile = IPGList("hi")

        val json = tgt.toJson()

        val expect = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagIPGList" : "hi"
          }
        """.trimIndent()

        assertEquals(expect, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIIPGListCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.ipgListFile = IPGList("hi")

        assertEquals("${tgt.tool.value} $FlagIPGList 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.ipgListFile = IPGList("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagIPGList, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagNegativeIPGList)
  inner class BlastCLINegativeIPGList {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINegativeIPGListJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeIPGListFile = NegativeIPGList("hi")

        val json = tgt.toJson()

        val expect = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagNegativeIPGList" : "hi"
          }
        """.trimIndent()

        assertEquals(expect, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINegativeIPGListCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeIPGListFile = NegativeIPGList("hi")

        assertEquals("${tgt.tool.value} $FlagNegativeIPGList 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeIPGListFile = NegativeIPGList("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNegativeIPGList, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

}