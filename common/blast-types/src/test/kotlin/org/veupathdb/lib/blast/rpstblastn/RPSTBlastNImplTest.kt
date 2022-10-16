package org.veupathdb.lib.blast.rpstblastn

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.common.*
import org.veupathdb.lib.blast.common.BlastQueryBaseImplTest
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.rpstblastn.fields.CompBasedStatsRPSTN
import org.veupathdb.lib.blast.rpstblastn.fields.CompBasedStatsRPSTNValue
import org.veupathdb.lib.blast.rpstblastn.fields.SoftMaskingRPSTN
import org.veupathdb.lib.blast.rpstblastn.fields.YesSegRPSTN

@DisplayName("rpstblastn")
internal class RPSTBlastNImplTest : BlastQueryBaseImplTest() {

  override fun getEmptyImpl(): RPSTBlastN {
    return RPSTBlastNImpl()
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
  @DisplayName(FlagCompBasedStats)
  inner class BlastCLICompBasedStats {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLICompBasedStatsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.compBasedStats = CompBasedStatsRPSTN(CompBasedStatsRPSTNValue.NoCompBasedStats)

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
        tgt.compBasedStats = CompBasedStatsRPSTN(CompBasedStatsRPSTNValue.NoCompBasedStats)

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
        tgt.compBasedStats = CompBasedStatsRPSTN(CompBasedStatsRPSTNValue.NoCompBasedStats)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagCompBasedStats, cli[1])
        assertEquals("0", cli[2])
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
        tgt.seg = YesSegRPSTN

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
        tgt.seg = YesSegRPSTN

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
        tgt.seg = YesSegRPSTN

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
        tgt.softMasking = SoftMaskingRPSTN(true)

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
        tgt.softMasking = SoftMaskingRPSTN(true)

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
        tgt.softMasking = SoftMaskingRPSTN(true)

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
  inner class BlastCLINumThreadsFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINumThreadsFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.autoCPUCores = AutoCPUCores(AutoCPUCoresValue.Enable)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagNumThreads))
        assertTrue(json[FlagNumThreads].isIntegralNumber)
        assertEquals(0, json[FlagNumThreads].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINumThreadsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.autoCPUCores = AutoCPUCores(AutoCPUCoresValue.Enable)

        assertEquals("${tgt.tool.value} $FlagNumThreads 0", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.autoCPUCores = AutoCPUCores(AutoCPUCoresValue.Enable)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNumThreads, cli[1])
        assertEquals("0", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagMultiThreadingMode)
  inner class BlastCLIMultiThreadingModeFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIMultiThreadingModeFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.multiThreadingMode = MultiThreadingMode(MultiThreadingModeValue.SplitByQuery)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagMultiThreadingMode))
        assertTrue(json[FlagMultiThreadingMode].isIntegralNumber)
        assertEquals(1, json[FlagMultiThreadingMode].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIMultiThreadingModeCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.multiThreadingMode = MultiThreadingMode(MultiThreadingModeValue.SplitByQuery)

        assertEquals("${tgt.tool.value} $FlagMultiThreadingMode 1", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.multiThreadingMode = MultiThreadingMode(MultiThreadingModeValue.SplitByQuery)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagMultiThreadingMode, cli[1])
        assertEquals("1", cli[2])
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

}