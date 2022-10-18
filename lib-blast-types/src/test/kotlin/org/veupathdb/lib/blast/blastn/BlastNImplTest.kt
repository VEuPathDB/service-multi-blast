package org.veupathdb.lib.blast.blastn

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.blastn.fields.*
import org.veupathdb.lib.blast.common.*
import org.veupathdb.lib.blast.common.fields.*

@DisplayName("blastn")
internal class BlastNImplTest : BlastQueryWithListsImplTest() {

  override fun getEmptyImpl(): BlastN {
    return BlastNImpl()
  }

  @Nested
  @DisplayName(FlagStrand)
  inner class BlastCLIStrand {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIStrandJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.strand = Strand(StrandType.Minus)

        assertEquals(
          """
            {
              "tool" : "blastn",
              "$FlagStrand" : "minus"
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIStrandCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.strand = Strand(StrandType.Minus)

        assertEquals("${tgt.tool.value} $FlagStrand 'minus'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.strand = Strand(StrandType.Minus)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagStrand, cli[1])
        assertEquals("minus", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagTask)
  inner class BlastCLITask {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLITaskJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.task = BlastNTask(BlastNTaskType.RMBlastN)

        assertEquals(
          """
            {
              "tool" : "blastn",
              "$FlagTask" : "rmblastn"
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLITaskCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.task = BlastNTask(BlastNTaskType.RMBlastN)

        assertEquals("${tgt.tool.value} $FlagTask 'rmblastn'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.task = BlastNTask(BlastNTaskType.RMBlastN)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagTask, cli[1])
        assertEquals("rmblastn", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagGapOpen)
  inner class BlastCLIGapOpen {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIGapOpenJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.gapOpen = GapOpen(12)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagGapOpen))
        assertTrue(json[FlagGapOpen].isIntegralNumber)
        assertEquals(12, json[FlagGapOpen].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIGapOpenCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.gapOpen = GapOpen(12)

        assertEquals("${tgt.tool.value} $FlagGapOpen 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.gapOpen = GapOpen(12)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagGapOpen, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagGapExtend)
  inner class BlastCLIGapExtend {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIGapExtendJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.gapExtend = GapExtend(12)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagGapExtend))
        assertTrue(json[FlagGapExtend].isIntegralNumber)
        assertEquals(12, json[FlagGapExtend].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIGapExtendCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.gapExtend = GapExtend(12)

        assertEquals("${tgt.tool.value} $FlagGapExtend 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.gapExtend = GapExtend(12)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagGapExtend, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagPenalty)
  inner class BlastCLIPenalty {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIPenaltyJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.penalty = Penalty(-23)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagPenalty))
        assertTrue(json[FlagPenalty].isIntegralNumber)
        assertEquals(-23, json[FlagPenalty].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIPenaltyCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.penalty = Penalty(-23)

        assertEquals("${tgt.tool.value} $FlagPenalty -23", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.penalty = Penalty(-23)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagPenalty, cli[1])
        assertEquals("-23", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagReward)
  inner class BlastCLIReward {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIRewardJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.reward = Reward(35u)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagReward))
        assertTrue(json[FlagReward].isIntegralNumber)
        assertEquals(35, json[FlagReward].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIRewardCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.reward = Reward(35u)

        assertEquals("${tgt.tool.value} $FlagReward 35", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.reward = Reward(35u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagReward, cli[1])
        assertEquals("35", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagUseIndex)
  inner class BlastCLIUseIndex {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIUseIndexJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.useIndex = UseIndex(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagUseIndex))
        assertTrue(json[FlagUseIndex].isBoolean)
        assertTrue(json[FlagUseIndex].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIUseIndexCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.useIndex = UseIndex(true)

        assertEquals("${tgt.tool.value} $FlagUseIndex true", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.useIndex = UseIndex(true)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagUseIndex, cli[1])
        assertEquals("true", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagIndexName)
  inner class BlastCLIIndexNameFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIIndexNameFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.indexName = IndexName("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagIndexName))
        assertTrue(json[FlagIndexName].isTextual)
        assertEquals("hi", json[FlagIndexName].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIIndexNameCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.indexName = IndexName("hi")

        assertEquals("${tgt.tool.value} $FlagIndexName 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.indexName = IndexName("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagIndexName, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagSubjectFile)
  inner class BlastCLISubjectFileFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISubjectFileFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.subjectFile = SubjectFile("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagSubjectFile))
        assertTrue(json[FlagSubjectFile].isTextual)
        assertEquals("hi", json[FlagSubjectFile].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISubjectFileCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.subjectFile = SubjectFile("hi")

        assertEquals("${tgt.tool.value} $FlagSubjectFile 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.subjectFile = SubjectFile("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSubjectFile, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagSubjectLocation)
  inner class BlastCLISubjectLocation {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISubjectLocationJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.subjectLocation = SubjectLocation(10u, 12u)

        val json = tgt.toJson()

        val expected = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagSubjectLocation" : {
              "start" : 10,
              "stop" : 12
            }
          }""".trimIndent()

        assertEquals(expected, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISubjectLocationCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.subjectLocation = SubjectLocation(10u, 12u)

        assertEquals("${tgt.tool.value} $FlagSubjectLocation '${tgt.subjectLocation.start}-${tgt.subjectLocation.stop}'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.subjectLocation = SubjectLocation(10u, 12u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSubjectLocation, cli[1])
        assertEquals("${tgt.subjectLocation.start}-${tgt.subjectLocation.stop}", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagDust)
  inner class BlastCLIDust {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIDustJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dust = ValDust(60, 60, 60)

        val json = tgt.toJson()

        val expected = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagDust" : {
              "level" : 60,
              "window" : 60,
              "linker" : 60
            }
          }""".trimIndent()

        assertEquals(expected, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIDustCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dust = ValDust(60, 60, 60)

        assertEquals(
          "${tgt.tool.value} $FlagDust '${tgt.dust.level} ${tgt.dust.window} ${tgt.dust.linker}'",
          tgt.toCliString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dust = ValDust(60, 60, 60)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagDust, cli[1])
        assertEquals("${tgt.dust.level} ${tgt.dust.window} ${tgt.dust.linker}", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagFilteringDB)
  inner class BlastCLIFilteringDBFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIFilteringDBFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.filteringDBFile = FilteringDB("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagFilteringDB))
        assertTrue(json[FlagFilteringDB].isTextual)
        assertEquals("hi", json[FlagFilteringDB].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIFilteringDBCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.filteringDBFile = FilteringDB("hi")

        assertEquals("${tgt.tool.value} $FlagFilteringDB 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.filteringDBFile = FilteringDB("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagFilteringDB, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagWindowMaskerTaxID)
  inner class BlastCLIWindowMaskerTaxID {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIWindowMaskerTaxIDJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.windowMaskerTaxID = WindowMaskerTaxID(12)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagWindowMaskerTaxID))
        assertTrue(json[FlagWindowMaskerTaxID].isIntegralNumber)
        assertEquals(12, json[FlagWindowMaskerTaxID].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIWindowMaskerTaxIDCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.windowMaskerTaxID = WindowMaskerTaxID(12)

        assertEquals("${tgt.tool.value} $FlagWindowMaskerTaxID 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.windowMaskerTaxID = WindowMaskerTaxID(12)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagWindowMaskerTaxID, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagWindowMaskerDB)
  inner class BlastCLIWindowMaskerDBFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIWindowMaskerDBFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.windowMaskerDBFile = WindowMaskerDB("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagWindowMaskerDB))
        assertTrue(json[FlagWindowMaskerDB].isTextual)
        assertEquals("hi", json[FlagWindowMaskerDB].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIWindowMaskerDBCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.windowMaskerDBFile = WindowMaskerDB("hi")

        assertEquals("${tgt.tool.value} $FlagWindowMaskerDB 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.windowMaskerDBFile = WindowMaskerDB("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagWindowMaskerDB, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagDBSoftMask)
  inner class BlastCLIDBSoftMaskFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIDBSoftMaskFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbSoftMask = DBSoftMask("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagDBSoftMask))
        assertTrue(json[FlagDBSoftMask].isTextual)
        assertEquals("hi", json[FlagDBSoftMask].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIDBSoftMaskCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbSoftMask = DBSoftMask("hi")

        assertEquals("${tgt.tool.value} $FlagDBSoftMask 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbSoftMask = DBSoftMask("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagDBSoftMask, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagDBHardMask)
  inner class BlastCLIDBHardMaskFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIDBHardMaskFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbHardMask = DBHardMask("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagDBHardMask))
        assertTrue(json[FlagDBHardMask].isTextual)
        assertEquals("hi", json[FlagDBHardMask].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIDBHardMaskCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbHardMask = DBHardMask("hi")

        assertEquals("${tgt.tool.value} $FlagDBHardMask 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbHardMask = DBHardMask("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagDBHardMask, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagPercentIdentity)
  inner class BlastCLIPercentIdentityFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIPercentIdentityFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.percentIdentity = PercentIdentity(10.1)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagPercentIdentity))
        assertTrue(json[FlagPercentIdentity].isDouble)
        assertEquals(10.1, json[FlagPercentIdentity].doubleValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIPercentIdentityCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.percentIdentity = PercentIdentity(10.1)

        assertEquals("${tgt.tool.value} $FlagPercentIdentity 10.1", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.percentIdentity = PercentIdentity(10.1)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagPercentIdentity, cli[1])
        assertEquals("10.1", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagCullingLimit)
  inner class BlastCLICullingLimit {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLICullingLimitJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.cullingLimit = CullingLimit(35u)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagCullingLimit))
        assertTrue(json[FlagCullingLimit].isIntegralNumber)
        assertEquals(35, json[FlagCullingLimit].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLICullingLimitCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.cullingLimit = CullingLimit(35u)

        assertEquals("${tgt.tool.value} $FlagCullingLimit 35", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.cullingLimit = CullingLimit(35u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagCullingLimit, cli[1])
        assertEquals("35", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagTemplateType)
  inner class BlastCLITemplateType {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLITemplateTypeJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.templateType = TemplateType(TemplateTypeType.Coding)

        assertEquals(
          """
            {
              "tool" : "blastn",
              "$FlagTemplateType" : "coding"
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLITemplateTypeCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.templateType = TemplateType(TemplateTypeType.Coding)

        assertEquals("${tgt.tool.value} $FlagTemplateType 'coding'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.templateType = TemplateType(TemplateTypeType.Coding)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagTemplateType, cli[1])
        assertEquals("coding", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagTemplateLength)
  inner class BlastCLITemplateLength {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLITemplateLengthJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.templateLength = TemplateLength(TemplateLengthValue.Sixteen)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagTemplateLength))
        assertTrue(json[FlagTemplateLength].isIntegralNumber)
        assertEquals(16, json[FlagTemplateLength].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLITemplateLengthCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.templateLength = TemplateLength(TemplateLengthValue.Sixteen)

        assertEquals("${tgt.tool.value} $FlagTemplateLength 16", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.templateLength = TemplateLength(TemplateLengthValue.Sixteen)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagTemplateLength, cli[1])
        assertEquals("16", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagSumStats)
  inner class BlastCLISumStats {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISumStatsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.sumStats = SumStats(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagSumStats))
        assertTrue(json[FlagSumStats].isBoolean)
        assertTrue(json[FlagSumStats].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISumStatsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.sumStats = SumStats(true)

        assertEquals("${tgt.tool.value} $FlagSumStats true", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.sumStats = SumStats(true)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSumStats, cli[1])
        assertEquals("true", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagXDropPrelimGap)
  inner class BlastCLIXDropPrelimGapFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIXDropPrelimGapFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.extensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(10.1)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagXDropPrelimGap))
        assertTrue(json[FlagXDropPrelimGap].isDouble)
        assertEquals(10.1, json[FlagXDropPrelimGap].doubleValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIXDropPrelimGapCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.extensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(10.1)

        assertEquals("${tgt.tool.value} $FlagXDropPrelimGap 10.1", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.extensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(10.1)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagXDropPrelimGap, cli[1])
        assertEquals("10.1", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagXDropFinalGap)
  inner class BlastCLIXDropFinalGapFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIXDropFinalGapFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.extensionDropoffFinalGapped = ExtensionDropoffFinalGapped(10.1)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagXDropFinalGap))
        assertTrue(json[FlagXDropFinalGap].isDouble)
        assertEquals(10.1, json[FlagXDropFinalGap].doubleValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIXDropFinalGapCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.extensionDropoffFinalGapped = ExtensionDropoffFinalGapped(10.1)

        assertEquals("${tgt.tool.value} $FlagXDropFinalGap 10.1", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.extensionDropoffFinalGapped = ExtensionDropoffFinalGapped(10.1)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagXDropFinalGap, cli[1])
        assertEquals("10.1", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagNonGreedy)
  inner class BlastCLINonGreedy {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINonGreedyJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.nonGreedy = NonGreedy(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagNonGreedy))
        assertTrue(json[FlagNonGreedy].isBoolean)
        assertTrue(json[FlagNonGreedy].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINonGreedyCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.nonGreedy = NonGreedy(true)

        assertEquals("${tgt.tool.value} $FlagNonGreedy", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.nonGreedy = NonGreedy(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNonGreedy, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagUngapped)
  inner class BlastCLIUngapped {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIUngappedJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.ungappedAlignmentsOnly = UngappedAlignmentsOnly(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagUngapped))
        assertTrue(json[FlagUngapped].isBoolean)
        assertTrue(json[FlagUngapped].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIUngappedCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.ungappedAlignmentsOnly = UngappedAlignmentsOnly(true)

        assertEquals("${tgt.tool.value} $FlagUngapped", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.ungappedAlignmentsOnly = UngappedAlignmentsOnly(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagUngapped, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagNumThreads)
  inner class BlastCLINumThreads {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINumThreadsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numCPUCores = NumCPUCores(35u)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagNumThreads))
        assertTrue(json[FlagNumThreads].isIntegralNumber)
        assertEquals(35, json[FlagNumThreads].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINumThreadsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numCPUCores = NumCPUCores(35u)

        assertEquals("${tgt.tool.value} $FlagNumThreads 35", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numCPUCores = NumCPUCores(35u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNumThreads, cli[1])
        assertEquals("35", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagBestHitOverhang)
  inner class BlastCLIBestHitOverhangFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIBestHitOverhangFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.bestHitOverhang = BestHitOverhang(0.4)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagBestHitOverhang))
        assertTrue(json[FlagBestHitOverhang].isDouble)
        assertEquals(0.4, json[FlagBestHitOverhang].doubleValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIBestHitOverhangCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.bestHitOverhang = BestHitOverhang(0.4)

        assertEquals("${tgt.tool.value} $FlagBestHitOverhang 0.4", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.bestHitOverhang = BestHitOverhang(0.4)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagBestHitOverhang, cli[1])
        assertEquals("0.4", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagBestHitScoreEdge)
  inner class BlastCLIBestHitScoreEdgeFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIBestHitScoreEdgeFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.bestHitScoreEdge = BestHitScoreEdge(0.4)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagBestHitScoreEdge))
        assertTrue(json[FlagBestHitScoreEdge].isDouble)
        assertEquals(0.4, json[FlagBestHitScoreEdge].doubleValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIBestHitScoreEdgeCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.bestHitScoreEdge = BestHitScoreEdge(0.4)

        assertEquals("${tgt.tool.value} $FlagBestHitScoreEdge 0.4", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.bestHitScoreEdge = BestHitScoreEdge(0.4)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagBestHitScoreEdge, cli[1])
        assertEquals("0.4", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagSubjectBestHit)
  inner class BlastCLISubjectBestHit {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISubjectBestHitJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.subjectBestHit = SubjectBestHit(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagSubjectBestHit))
        assertTrue(json[FlagSubjectBestHit].isBoolean)
        assertTrue(json[FlagSubjectBestHit].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISubjectBestHitCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.subjectBestHit = SubjectBestHit(true)

        assertEquals("${tgt.tool.value} $FlagSubjectBestHit", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.subjectBestHit = SubjectBestHit(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSubjectBestHit, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagSoftMasking)
  inner class BlastCLISoftMasking {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISoftMaskingJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.softMasking = SoftMaskingN(false)

        assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagSoftMasking" : false
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISoftMaskingCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.softMasking = SoftMaskingN(false)

        assertEquals("${tgt.tool.value} $FlagSoftMasking false", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.softMasking = SoftMaskingN(false)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSoftMasking, cli[1])
        assertEquals("false", cli[2])
      }
    }
  }

}