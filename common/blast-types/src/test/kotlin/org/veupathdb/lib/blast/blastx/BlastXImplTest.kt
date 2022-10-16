package org.veupathdb.lib.blast.blastx

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.blastx.field.*
import org.veupathdb.lib.blast.common.*
import org.veupathdb.lib.blast.common.fields.*

@DisplayName("blastx")
internal class BlastXImplTest : BlastQueryWithIPGImplTest() {
  override fun getEmptyImpl(): BlastX {
    return BlastXImpl()
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
              "tool" : "blastx",
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
  @DisplayName(FlagQueryGenCode)
  inner class BlastCLIQueryGenCode {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIQueryGenCodeJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryGenCode = QueryGenCode(33u)

        assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagQueryGenCode" : 33
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIQueryGenCodeCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryGenCode = QueryGenCode(33u)

        assertEquals("${tgt.tool.value} $FlagQueryGenCode 33", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryGenCode = QueryGenCode(33u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagQueryGenCode, cli[1])
        assertEquals("33", cli[2])
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
        tgt.task = BlastXTask(BlastXTaskType.BlastXFast)

        assertEquals(
          """
            {
              "tool" : "blastx",
              "$FlagTask" : "blastx-fast"
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
        tgt.task = BlastXTask(BlastXTaskType.BlastXFast)

        assertEquals("${tgt.tool.value} $FlagTask 'blastx-fast'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.task = BlastXTask(BlastXTaskType.BlastXFast)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagTask, cli[1])
        assertEquals("blastx-fast", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagWordSize)
  inner class BlastCLIWordSize {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIWordSizeJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.wordSize = WordSizeX(35u)

        assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagWordSize" : 35
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIWordSizeCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.wordSize = WordSizeX(35u)

        assertEquals("${tgt.tool.value} $FlagWordSize 35", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.wordSize = WordSizeX(35u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagWordSize, cli[1])
        assertEquals("35", cli[2])
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
  @DisplayName(FlagMaxIntronLength)
  inner class BlastCLIMaxIntronLength {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIMaxIntronLengthJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.maxIntronLength = MaxIntronLength(35u)

        assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagMaxIntronLength" : 35
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIMaxIntronLengthCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.maxIntronLength = MaxIntronLength(35u)

        assertEquals("${tgt.tool.value} $FlagMaxIntronLength 35", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.maxIntronLength = MaxIntronLength(35u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagMaxIntronLength, cli[1])
        assertEquals("35", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagMatrix)
  inner class BlastCLIMatrix {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIMatrixJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.matrix = ScoringMatrixX(ScoringMatrixXType.Blosum45)

        assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagMatrix" : "BLOSUM45"
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIMatrixCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.matrix = ScoringMatrixX(ScoringMatrixXType.Blosum45)

        assertEquals("${tgt.tool.value} $FlagMatrix 'BLOSUM45'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.matrix = ScoringMatrixX(ScoringMatrixXType.Blosum45)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagMatrix, cli[1])
        assertEquals("BLOSUM45", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagThreshold)
  inner class BlastCLIThresholdFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIThresholdFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.threshold = Threshold(10.1)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagThreshold))
        assertTrue(json[FlagThreshold].isDouble)
        assertEquals(10.1, json[FlagThreshold].doubleValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIThresholdCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.threshold = Threshold(10.1)

        assertEquals("${tgt.tool.value} $FlagThreshold 10.1", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.threshold = Threshold(10.1)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagThreshold, cli[1])
        assertEquals("10.1", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagCompBasedStats)
  inner class BlastCLICompBasedStats {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLICompBasedStatsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.compBasedStats = CompBasedStatsX(CompBasedStatsXValue.UnconditionalScoreAdjustment)

        assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagCompBasedStats" : 3
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLICompBasedStatsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.compBasedStats = CompBasedStatsX(CompBasedStatsXValue.UnconditionalScoreAdjustment)

        assertEquals("${tgt.tool.value} $FlagCompBasedStats 3", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.compBasedStats = CompBasedStatsX(CompBasedStatsXValue.UnconditionalScoreAdjustment)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagCompBasedStats, cli[1])
        assertEquals("3", cli[2])
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
  @DisplayName(FlagSeg)
  inner class BlastCLISeg {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISegJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.seg = YesSegX

        assertEquals(
          """
            {
              "tool" : "blastx",
              "$FlagSeg" : "yes"
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISegCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.seg = YesSegX

        assertEquals("${tgt.tool.value} $FlagSeg yes", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.seg = YesSegX

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSeg, cli[1])
        assertEquals("yes", cli[2])
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
  @DisplayName(FlagUseSWTBack)
  inner class BlastCLIUseSWTBack {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIUseSWTBackJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.useSmithWatermanTraceback = UseSmithWatermanTraceback(true)

        assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagUseSWTBack" : true
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIUseSWTBackCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.useSmithWatermanTraceback = UseSmithWatermanTraceback(true)

        assertEquals("${tgt.tool.value} $FlagUseSWTBack", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.useSmithWatermanTraceback = UseSmithWatermanTraceback(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagUseSWTBack, cli[1])
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
        tgt.softMasking = SoftMaskingX(true)

        assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagSoftMasking" : true
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
        tgt.softMasking = SoftMaskingX(true)

        assertEquals("${tgt.tool.value} $FlagSoftMasking true", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.softMasking = SoftMaskingX(true)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSoftMasking, cli[1])
        assertEquals("true", cli[2])
      }
    }
  }

}