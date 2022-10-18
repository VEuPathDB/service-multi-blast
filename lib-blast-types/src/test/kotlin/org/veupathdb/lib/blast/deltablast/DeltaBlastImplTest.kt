package org.veupathdb.lib.blast.deltablast

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.common.*
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.deltablast.fields.*

@DisplayName("deltablast")
internal class DeltaBlastImplTest() : BlastQueryWithListsImplTest() {
  override fun getEmptyImpl(): DeltaBlast = DeltaBlastImpl()

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
        tgt.wordSize = WordSizeDelta(35u)

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
        tgt.wordSize = WordSizeDelta(35u)

        assertEquals("${tgt.tool.value} $FlagWordSize 35",
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
        tgt.wordSize = WordSizeDelta(35u)

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
  @DisplayName(FlagMatrix)
  inner class BlastCLIMatrix {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIMatrixJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.matrix = ScoringMatrixDelta(ScoringMatrixDeltaType.Blosum45)

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
        tgt.matrix = ScoringMatrixDelta(ScoringMatrixDeltaType.Blosum45)

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
        tgt.matrix = ScoringMatrixDelta(ScoringMatrixDeltaType.Blosum45)

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
        tgt.compBasedStats = CompBasedStatsDelta(CompBasedStatsDeltaValue.NoCompBasedStats)

        assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagCompBasedStats" : 0
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
        tgt.compBasedStats = CompBasedStatsDelta(CompBasedStatsDeltaValue.NoCompBasedStats)

        assertEquals("${tgt.tool.value} $FlagCompBasedStats 0", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.compBasedStats = CompBasedStatsDelta(CompBasedStatsDeltaValue.NoCompBasedStats)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagCompBasedStats, cli[1])
        assertEquals("0", cli[2])
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
        tgt.seg = YesSegD

        assertEquals(
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
        tgt.seg = YesSegD

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
        tgt.seg = YesSegD

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSeg, cli[1])
        assertEquals("yes", cli[2])
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
        tgt.softMasking = SoftMaskingDelta(true)

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
        tgt.softMasking = SoftMaskingDelta(true)

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
        tgt.softMasking = SoftMaskingDelta(true)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSoftMasking, cli[1])
        assertEquals("true", cli[2])
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

        assertEquals(
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

        assertEquals("${tgt.tool.value} $FlagQueryCoverageHSPPercent 33.3", tgt.toCliString())
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

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagQueryCoverageHSPPercent, cli[1])
        assertEquals("33.3", cli[2])
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
  @DisplayName(FlagGapTrigger)
  inner class BlastCLIGapTriggerFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIGapTriggerFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.gapTrigger = GapTrigger(10.1)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagGapTrigger))
        assertTrue(json[FlagGapTrigger].isDouble)
        assertEquals(10.1, json[FlagGapTrigger].doubleValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIGapTriggerCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.gapTrigger = GapTrigger(10.1)

        assertEquals("${tgt.tool.value} $FlagGapTrigger 10.1", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.gapTrigger = GapTrigger(10.1)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagGapTrigger, cli[1])
        assertEquals("10.1", cli[2])
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
  @DisplayName(FlagNumIterations)
  inner class BlastCLINumIterations {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINumIterationsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numIterations = NumIterations(35u)

        assertEquals(
          """
            {
              "tool" : "${tgt.tool.value}",
              "$FlagNumIterations" : 35
            }
          """.trimIndent(),
          tgt.toJson().toPrettyString()
        )
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINumIterationsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numIterations = NumIterations(35u)

        assertEquals("${tgt.tool.value} $FlagNumIterations 35", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numIterations = NumIterations(35u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNumIterations, cli[1])
        assertEquals("35", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagOutPSSM)
  inner class BlastCLIOutPSSMFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIOutPSSMFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.outPSSMFile = OutPSSMFile("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagOutPSSM))
        assertTrue(json[FlagOutPSSM].isTextual)
        assertEquals("hi", json[FlagOutPSSM].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIOutPSSMCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.outPSSMFile = OutPSSMFile("hi")

        assertEquals("${tgt.tool.value} $FlagOutPSSM 'hi'",
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
        tgt.outPSSMFile = OutPSSMFile("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagOutPSSM, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagOutASCIIPSSM)
  inner class BlastCLIOutASCIIPSSMFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIOutASCIIPSSMFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.outASCIIPSSMFile = OutASCIIPSSMFile("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagOutASCIIPSSM))
        assertTrue(json[FlagOutASCIIPSSM].isTextual)
        assertEquals("hi", json[FlagOutASCIIPSSM].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIOutASCIIPSSMCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.outASCIIPSSMFile = OutASCIIPSSMFile("hi")

        assertEquals("${tgt.tool.value} $FlagOutASCIIPSSM 'hi'",
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
        tgt.outASCIIPSSMFile = OutASCIIPSSMFile("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagOutASCIIPSSM, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagSavePSSMAfterLastRound)
  inner class BlastCLISavePSSMAfterLastRound {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISavePSSMAfterLastRoundJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.savePSSMAfterLastRound = SavePSSMAfterLastRound(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagSavePSSMAfterLastRound))
        assertTrue(json[FlagSavePSSMAfterLastRound].isBoolean)
        assertTrue(json[FlagSavePSSMAfterLastRound].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISavePSSMAfterLastRoundCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.savePSSMAfterLastRound = SavePSSMAfterLastRound(true)

        assertEquals("${tgt.tool.value} $FlagSavePSSMAfterLastRound",
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
        tgt.savePSSMAfterLastRound = SavePSSMAfterLastRound(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSavePSSMAfterLastRound, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagSaveEachPSSM)
  inner class BlastCLISaveEachPSSM {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISaveEachPSSMJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.saveEachPSSM = SaveEachPSSM(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagSaveEachPSSM))
        assertTrue(json[FlagSaveEachPSSM].isBoolean)
        assertTrue(json[FlagSaveEachPSSM].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISaveEachPSSMCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.saveEachPSSM = SaveEachPSSM(true)

        assertEquals("${tgt.tool.value} $FlagSaveEachPSSM",
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
        tgt.saveEachPSSM = SaveEachPSSM(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSaveEachPSSM, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagPseudoCount)
  inner class BlastCLIPseudoCount {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIPseudoCountJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.pseudoCount = PseudoCount(12)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagPseudoCount))
        assertTrue(json[FlagPseudoCount].isIntegralNumber)
        assertEquals(12, json[FlagPseudoCount].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIPseudoCountCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.pseudoCount = PseudoCount(12)

        assertEquals("${tgt.tool.value} $FlagPseudoCount 12",
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
        tgt.pseudoCount = PseudoCount(12)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagPseudoCount, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagDomainInclusionEThresh)
  inner class BlastCLIDomainInclusionEThreshFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIDomainInclusionEThreshFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.domainInclusionEValueThreshold = DomainInclusionEValueThreshold(10.1)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagDomainInclusionEThresh))
        assertTrue(json[FlagDomainInclusionEThresh].isDouble)
        assertEquals(10.1, json[FlagDomainInclusionEThresh].doubleValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIDomainInclusionEThreshCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.domainInclusionEValueThreshold = DomainInclusionEValueThreshold(10.1)

        assertEquals("${tgt.tool.value} $FlagDomainInclusionEThresh 10.1", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.domainInclusionEValueThreshold = DomainInclusionEValueThreshold(10.1)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagDomainInclusionEThresh, cli[1])
        assertEquals("10.1", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagInclusionEThresh)
  inner class BlastCLIInclusionEThreshFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIInclusionEThreshFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.inclusionEValueThreshold = InclusionEValueThreshold(10.1)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagInclusionEThresh))
        assertTrue(json[FlagInclusionEThresh].isDouble)
        assertEquals(10.1, json[FlagInclusionEThresh].doubleValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIInclusionEThreshCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.inclusionEValueThreshold = InclusionEValueThreshold(10.1)

        assertEquals("${tgt.tool.value} $FlagInclusionEThresh 10.1", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.inclusionEValueThreshold = InclusionEValueThreshold(10.1)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagInclusionEThresh, cli[1])
        assertEquals("10.1", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagRPSDB)
  inner class BlastCLIRPSDBFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIRPSDBFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.rpsDB = RPSDB("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagRPSDB))
        assertTrue(json[FlagRPSDB].isTextual)
        assertEquals("hi", json[FlagRPSDB].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIRPSDBCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.rpsDB = RPSDB("hi")

        assertEquals("${tgt.tool.value} $FlagRPSDB 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.rpsDB = RPSDB("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagRPSDB, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagShowDomainHits)
  inner class BlastCLIShowDomainHits {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIShowDomainHitsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.showDomainHits = ShowDomainHits(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagShowDomainHits))
        assertTrue(json[FlagShowDomainHits].isBoolean)
        assertTrue(json[FlagShowDomainHits].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIShowDomainHitsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.showDomainHits = ShowDomainHits(true)

        assertEquals("${tgt.tool.value} $FlagShowDomainHits", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.showDomainHits = ShowDomainHits(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagShowDomainHits, cli[1])
      }
    }
  }
}