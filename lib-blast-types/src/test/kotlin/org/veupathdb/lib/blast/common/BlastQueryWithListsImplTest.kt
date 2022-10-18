package org.veupathdb.lib.blast.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.common.fields.*

@DisplayName("Blast Query with Lists Config Interface Compliance")
internal abstract class BlastQueryWithListsImplTest : BlastQueryBaseImplTest() {
  abstract override fun getEmptyImpl(): BlastQueryWithLists

  @Nested
  @DisplayName(FlagGIList)
  inner class BlastCLIGIList {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIGIListJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.giListFile = GIList("hi")

        val json = tgt.toJson()

        val expect = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagGIList" : "hi"
          }
        """.trimIndent()

        assertEquals(expect, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIGIListCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.giListFile = GIList("hi")

        assertEquals("${tgt.tool.value} $FlagGIList 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.giListFile = GIList("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagGIList, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagNegativeGIList)
  inner class BlastCLINegativeGIList {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINegativeGIListJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeGIListFile = NegativeGIList("hi")

        val json = tgt.toJson()

        val expect = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagNegativeGIList" : "hi"
          }
        """.trimIndent()

        assertEquals(expect, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINegativeGIListCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeGIListFile = NegativeGIList("hi")

        assertEquals("${tgt.tool.value} $FlagNegativeGIList 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeGIListFile = NegativeGIList("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNegativeGIList, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagSeqIDList)
  inner class BlastCLISeqIDList {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISeqIDListJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.seqIDListFile = SeqIDList("hi")

        val json = tgt.toJson()

        val expect = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagSeqIDList" : "hi"
          }
        """.trimIndent()

        assertEquals(expect, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISeqIDListCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.seqIDListFile = SeqIDList("hi")

        assertEquals("${tgt.tool.value} $FlagSeqIDList 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.seqIDListFile = SeqIDList("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSeqIDList, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagNegativeSeqIDList)
  inner class BlastCLINegativeSeqIDList {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINegativeSeqIDListJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeSeqIDListFile = NegativeSeqIDList("hi")

        val json = tgt.toJson()

        val expect = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagNegativeSeqIDList" : "hi"
          }
        """.trimIndent()

        assertEquals(expect, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINegativeSeqIDListCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeSeqIDListFile = NegativeSeqIDList("hi")

        assertEquals("${tgt.tool.value} $FlagNegativeSeqIDList 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeSeqIDListFile = NegativeSeqIDList("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNegativeSeqIDList, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagTaxIDList)
  inner class BlastCLITaxIDList {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLITaxIDListJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.taxIDListFile = TaxIDList("hi")

        val json = tgt.toJson()

        val expect = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagTaxIDList" : "hi"
          }
        """.trimIndent()

        assertEquals(expect, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLITaxIDListCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.taxIDListFile = TaxIDList("hi")

        assertEquals("${tgt.tool.value} $FlagTaxIDList 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.taxIDListFile = TaxIDList("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagTaxIDList, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagNegativeTaxIDList)
  inner class BlastCLINegativeTaxIDList {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINegativeTaxIDListJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeTaxIDListFile = NegativeTaxIDList("hi")

        val json = tgt.toJson()

        val expect = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagNegativeTaxIDList" : "hi"
          }
        """.trimIndent()

        assertEquals(expect, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINegativeTaxIDListCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeTaxIDListFile = NegativeTaxIDList("hi")

        assertEquals("${tgt.tool.value} $FlagNegativeTaxIDList 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeTaxIDListFile = NegativeTaxIDList("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNegativeTaxIDList, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagTaxIDs)
  inner class BlastCLITaxIDs {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLITaxIDsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.taxIDs = TaxIDs(listOf("hi"))

        val json = tgt.toJson()

        val expect = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagTaxIDs" : [ "hi" ]
          }
        """.trimIndent()

        assertEquals(expect, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLITaxIDsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.taxIDs = TaxIDs(listOf("hi"))

        assertEquals("${tgt.tool.value} $FlagTaxIDs 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.taxIDs = TaxIDs(listOf("hi"))

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagTaxIDs, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagNegativeTaxIDs)
  inner class BlastCLINegativeTaxIDs {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINegativeTaxIDsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeTaxIDs = NegativeTaxIDs(listOf("hi"))

        val json = tgt.toJson()

        val expect = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagNegativeTaxIDs" : [ "hi" ]
          }
        """.trimIndent()

        assertEquals(expect, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINegativeTaxIDsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeTaxIDs = NegativeTaxIDs(listOf("hi"))

        assertEquals("${tgt.tool.value} $FlagNegativeTaxIDs 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.negativeTaxIDs = NegativeTaxIDs(listOf("hi"))

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNegativeTaxIDs, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

}