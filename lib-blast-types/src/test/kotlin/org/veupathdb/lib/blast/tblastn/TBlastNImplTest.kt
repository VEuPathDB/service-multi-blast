package org.veupathdb.lib.blast.tblastn

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.common.*
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.tblastn.fields.*

@DisplayName("tblastn")
internal class TBlastNImplTest : BlastQueryWithListsImplTest() {
  override fun getEmptyImpl(): TBlastN = TBlastNImpl()

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
        tgt.task = TBlastNTask(TBlastNTaskType.TBlastNFast)

        Assertions.assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagTask" : "tblastn-fast"
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
        tgt.task = TBlastNTask(TBlastNTaskType.TBlastNFast)

        Assertions.assertEquals("${tgt.tool.value} $FlagTask 'tblastn-fast'",
          tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.task = TBlastNTask(TBlastNTaskType.TBlastNFast)

        val cli = tgt.toCliArray()

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagTask, cli[1])
        Assertions.assertEquals("tblastn-fast", cli[2])
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
        tgt.wordSize = WordSizeTN(35u)

        Assertions.assertEquals(
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
        tgt.wordSize = WordSizeTN(35u)

        Assertions.assertEquals("${tgt.tool.value} $FlagWordSize 35",
          tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.wordSize = WordSizeTN(35u)

        val cli = tgt.toCliArray()

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagWordSize, cli[1])
        Assertions.assertEquals("35", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagGapOpen))
        Assertions.assertTrue(json[FlagGapOpen].isIntegralNumber)
        Assertions.assertEquals(12, json[FlagGapOpen].intValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagGapOpen 12",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagGapOpen, cli[1])
        Assertions.assertEquals("12", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagGapExtend))
        Assertions.assertTrue(json[FlagGapExtend].isIntegralNumber)
        Assertions.assertEquals(12, json[FlagGapExtend].intValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagGapExtend 12",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagGapExtend, cli[1])
        Assertions.assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagDBGenCode)
  inner class BlastCLIDBGenCode {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIDBGenCodeJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbGenCode = DBGenCode(33u)

        Assertions.assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagDBGenCode" : 33
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIDBGenCodeCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbGenCode = DBGenCode(33u)

        Assertions.assertEquals("${tgt.tool.value} $FlagDBGenCode 33",
          tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbGenCode = DBGenCode(33u)

        val cli = tgt.toCliArray()

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagDBGenCode, cli[1])
        Assertions.assertEquals("33", cli[2])
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

        Assertions.assertEquals(
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

        Assertions.assertEquals("${tgt.tool.value} $FlagMaxIntronLength 35",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagMaxIntronLength, cli[1])
        Assertions.assertEquals("35", cli[2])
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
        tgt.matrix = ScoringMatrixTN(ScoringMatrixTNType.Blosum45)

        Assertions.assertEquals(
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
        tgt.matrix = ScoringMatrixTN(ScoringMatrixTNType.Blosum45)

        Assertions.assertEquals("${tgt.tool.value} $FlagMatrix 'BLOSUM45'",
          tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.matrix = ScoringMatrixTN(ScoringMatrixTNType.Blosum45)

        val cli = tgt.toCliArray()

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagMatrix, cli[1])
        Assertions.assertEquals("BLOSUM45", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagThreshold))
        Assertions.assertTrue(json[FlagThreshold].isDouble)
        Assertions.assertEquals(10.1, json[FlagThreshold].doubleValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagThreshold 10.1",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagThreshold, cli[1])
        Assertions.assertEquals("10.1", cli[2])
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
        tgt.compBasedStats = CompBasedStatsTN(CompBasedStatsTNValue.UnconditionalScoreAdjustment)

        Assertions.assertEquals(
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
        tgt.compBasedStats = CompBasedStatsTN(CompBasedStatsTNValue.UnconditionalScoreAdjustment)

        Assertions.assertEquals("${tgt.tool.value} $FlagCompBasedStats 3",
          tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.compBasedStats = CompBasedStatsTN(CompBasedStatsTNValue.UnconditionalScoreAdjustment)

        val cli = tgt.toCliArray()

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagCompBasedStats, cli[1])
        Assertions.assertEquals("3", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagSubjectFile))
        Assertions.assertTrue(json[FlagSubjectFile].isTextual)
        Assertions.assertEquals("hi", json[FlagSubjectFile].textValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagSubjectFile 'hi'",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagSubjectFile, cli[1])
        Assertions.assertEquals("hi", cli[2])
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

        Assertions.assertEquals(expected, json.toPrettyString())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagSubjectLocation '${tgt.subjectLocation.start}-${tgt.subjectLocation.stop}'",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagSubjectLocation, cli[1])
        Assertions.assertEquals("${tgt.subjectLocation.start}-${tgt.subjectLocation.stop}",
          cli[2])
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
        tgt.seg = YesSegTN

        Assertions.assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
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
        tgt.seg = YesSegTN

        Assertions.assertEquals("${tgt.tool.value} $FlagSeg yes",
          tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.seg = YesSegTN

        val cli = tgt.toCliArray()

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagSeg, cli[1])
        Assertions.assertEquals("yes", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagDBSoftMask))
        Assertions.assertTrue(json[FlagDBSoftMask].isTextual)
        Assertions.assertEquals("hi", json[FlagDBSoftMask].textValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagDBSoftMask 'hi'",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagDBSoftMask, cli[1])
        Assertions.assertEquals("hi", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagDBHardMask))
        Assertions.assertTrue(json[FlagDBHardMask].isTextual)
        Assertions.assertEquals("hi", json[FlagDBHardMask].textValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagDBHardMask 'hi'",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagDBHardMask, cli[1])
        Assertions.assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagQueryCoverageHSPPercent)
  inner class BlastCLIQueryCoverageHSPPercent {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIQueryCoverageHSPPercentJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryCoverageHSPPercent = QueryCoverageHSPPercent(33.3)

        Assertions.assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagQueryCoverageHSPPercent" : 33.3
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIQueryCoverageHSPPercentCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryCoverageHSPPercent = QueryCoverageHSPPercent(33.3)

        Assertions.assertEquals("${tgt.tool.value} $FlagQueryCoverageHSPPercent 33.3",
          tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryCoverageHSPPercent = QueryCoverageHSPPercent(33.3)

        val cli = tgt.toCliArray()

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagQueryCoverageHSPPercent, cli[1])
        Assertions.assertEquals("33.3", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagCullingLimit))
        Assertions.assertTrue(json[FlagCullingLimit].isIntegralNumber)
        Assertions.assertEquals(35, json[FlagCullingLimit].intValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagCullingLimit 35",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagCullingLimit, cli[1])
        Assertions.assertEquals("35", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagBestHitOverhang))
        Assertions.assertTrue(json[FlagBestHitOverhang].isDouble)
        Assertions.assertEquals(0.4, json[FlagBestHitOverhang].doubleValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagBestHitOverhang 0.4",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagBestHitOverhang, cli[1])
        Assertions.assertEquals("0.4", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagBestHitScoreEdge))
        Assertions.assertTrue(json[FlagBestHitScoreEdge].isDouble)
        Assertions.assertEquals(0.4, json[FlagBestHitScoreEdge].doubleValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagBestHitScoreEdge 0.4",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagBestHitScoreEdge, cli[1])
        Assertions.assertEquals("0.4", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagSubjectBestHit))
        Assertions.assertTrue(json[FlagSubjectBestHit].isBoolean)
        Assertions.assertTrue(json[FlagSubjectBestHit].booleanValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagSubjectBestHit",
          tgt.toCliString())
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

        Assertions.assertEquals(2, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagSubjectBestHit, cli[1])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagSumStats))
        Assertions.assertTrue(json[FlagSumStats].isBoolean)
        Assertions.assertTrue(json[FlagSumStats].booleanValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagSumStats true",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagSumStats, cli[1])
        Assertions.assertEquals("true", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagXDropPrelimGap))
        Assertions.assertTrue(json[FlagXDropPrelimGap].isDouble)
        Assertions.assertEquals(10.1, json[FlagXDropPrelimGap].doubleValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagXDropPrelimGap 10.1",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagXDropPrelimGap, cli[1])
        Assertions.assertEquals("10.1", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagXDropFinalGap))
        Assertions.assertTrue(json[FlagXDropFinalGap].isDouble)
        Assertions.assertEquals(10.1, json[FlagXDropFinalGap].doubleValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagXDropFinalGap 10.1",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagXDropFinalGap, cli[1])
        Assertions.assertEquals("10.1", cli[2])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagUngapped))
        Assertions.assertTrue(json[FlagUngapped].isBoolean)
        Assertions.assertTrue(json[FlagUngapped].booleanValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagUngapped",
          tgt.toCliString())
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

        Assertions.assertEquals(2, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagUngapped, cli[1])
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

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagNumThreads))
        Assertions.assertTrue(json[FlagNumThreads].isIntegralNumber)
        Assertions.assertEquals(35, json[FlagNumThreads].intValue())
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

        Assertions.assertEquals("${tgt.tool.value} $FlagNumThreads 35",
          tgt.toCliString())
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

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagNumThreads, cli[1])
        Assertions.assertEquals("35", cli[2])
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

        Assertions.assertEquals(
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

        Assertions.assertEquals("${tgt.tool.value} $FlagUseSWTBack",
          tgt.toCliString())
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

        Assertions.assertEquals(2, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagUseSWTBack, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagInPSSM)
  inner class BlastCLIInPSSMFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIInPSSMFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.inPSSMFile = InPSSMFile("hi")

        val json = tgt.toJson()

        Assertions.assertEquals(2, json.size())

        Assertions.assertTrue(json.has("tool"))
        Assertions.assertTrue(json["tool"].isTextual)
        Assertions.assertEquals(tgt.tool.value, json["tool"].textValue())

        Assertions.assertTrue(json.has(FlagInPSSM))
        Assertions.assertTrue(json[FlagInPSSM].isTextual)
        Assertions.assertEquals("hi", json[FlagInPSSM].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIInPSSMCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.inPSSMFile = InPSSMFile("hi")

        Assertions.assertEquals("${tgt.tool.value} $FlagInPSSM 'hi'",
          tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.inPSSMFile = InPSSMFile("hi")

        val cli = tgt.toCliArray()

        Assertions.assertEquals(3, cli.size)
        Assertions.assertEquals(tgt.tool.value, cli[0])
        Assertions.assertEquals(FlagInPSSM, cli[1])
        Assertions.assertEquals("hi", cli[2])
      }
    }
  }
}