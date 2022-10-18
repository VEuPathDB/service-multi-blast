package org.veupathdb.lib.blast.tblastx

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.common.*
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.tblastx.fields.*

@DisplayName("tblastx")
internal class TBlastXImplTest : BlastQueryWithListsImplTest() {
  override fun getEmptyImpl(): TBlastX = TBlastXImpl()

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
              "tool" : "${tgt.tool.value}",
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
  @DisplayName(FlagWordSize)
  inner class BlastCLIWordSize {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIWordSizeJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.wordSize = WordSizeTX(35u)

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
        tgt.wordSize = WordSizeTX(35u)

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
        tgt.wordSize = WordSizeTX(35u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagWordSize, cli[1])
        assertEquals("35", cli[2])
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
        tgt.matrix = ScoringMatrixTX(ScoringMatrixTXType.Blosum45)

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
        tgt.matrix = ScoringMatrixTX(ScoringMatrixTXType.Blosum45)

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
        tgt.matrix = ScoringMatrixTX(ScoringMatrixTXType.Blosum45)

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

        assertEquals(
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

        assertEquals("${tgt.tool.value} $FlagDBGenCode 33",
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

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagDBGenCode, cli[1])
        assertEquals("33", cli[2])
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
        tgt.seg = YesSegTX

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
        tgt.seg = YesSegTX

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
        tgt.seg = YesSegTX

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
        tgt.softMasking = SoftMaskingTX(true)

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
        tgt.softMasking = SoftMaskingTX(true)

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
        tgt.softMasking = SoftMaskingTX(true)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSoftMasking, cli[1])
        assertEquals("true", cli[2])
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
}