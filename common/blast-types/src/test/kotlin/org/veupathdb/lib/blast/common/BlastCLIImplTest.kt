package org.veupathdb.lib.blast.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.common.fields.*

@DisplayName("Blast CLI Config Interface Compliance")
internal abstract class BlastCLIImplTest {

  abstract fun getEmptyImpl(): BlastCLI

  @Nested
  @DisplayName("tool")
  inner class BlastCLITool {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIToolJson {

      @Test
      @DisplayName("appends the blast tool to the output json")
      fun t1() {
        val tgt = getEmptyImpl()

        assertEquals("""{"tool":"${tgt.tool.value}"}""", tgt.toJson().toString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIToolCLIString {

      @Test
      @DisplayName("appends the blast tool to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        val cli = tgt.toCliString()

        assertEquals(tgt.tool.value, cli)
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the blast tool to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        val cli = tgt.toCliArray()

        assertEquals(1, cli.size)
        assertEquals(tgt.tool.value, cli[0])
      }
    }
  }

  @Nested
  @DisplayName(FlagHelpShort)
  inner class BlastCLIShortHelp {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIShortHelpJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.shortHelp = HelpShort(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagHelpShort))
        assertTrue(json[FlagHelpShort].isBoolean)
        assertTrue(json[FlagHelpShort].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIShortHelpCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.shortHelp = HelpShort(true)

        assertEquals("${tgt.tool.value} $FlagHelpShort", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.shortHelp = HelpShort(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagHelpShort, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagHelpLong)
  inner class BlastCLILongHelp {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLILongHelpJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.longHelp = HelpLong(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagHelpLong))
        assertTrue(json[FlagHelpLong].isBoolean)
        assertTrue(json[FlagHelpLong].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLILongHelpCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.longHelp = HelpLong(true)

        assertEquals("${tgt.tool.value} $FlagHelpLong", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.longHelp = HelpLong(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagHelpLong, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagVersion)
  inner class BlastCLIVersion {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIVersionJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.version = Version(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagVersion))
        assertTrue(json[FlagVersion].isBoolean)
        assertTrue(json[FlagVersion].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIVersionCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.version = Version(true)

        assertEquals("${tgt.tool.value} $FlagVersion", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.version = Version(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagVersion, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagOut)
  inner class BlastCLIOutFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIOutFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.outFile = OutFile("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagOut))
        assertTrue(json[FlagOut].isTextual)
        assertEquals("hi", json[FlagOut].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIOutFileCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.outFile = OutFile("hi")

        assertEquals("${tgt.tool.value} $FlagOut 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.outFile = OutFile("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagOut, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagOutFormat)
  inner class BlastCLIOutFormat {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIOutFormatJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.outFormat = OutFormat(FormatType.BlastXML)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagOutFormat))
        assertTrue(json[FlagOutFormat].isObject)
        assertEquals(1, json[FlagOutFormat].size())

        assertTrue(json[FlagOutFormat].has("type"))
        assertTrue(json[FlagOutFormat]["type"].isIntegralNumber)
        assertEquals(5, json[FlagOutFormat]["type"].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIOutFormatCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.outFormat = OutFormat(FormatType.BlastXML)

        assertEquals("${tgt.tool.value} $FlagOutFormat '5'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.outFormat = OutFormat(FormatType.BlastXML)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagOutFormat, cli[1])
        assertEquals("5", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagShowGIs)
  inner class BlastCLIShowGIs {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIShowGIsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.showGIs = ShowGIs(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagShowGIs))
        assertTrue(json[FlagShowGIs].isBoolean)
        assertTrue(json[FlagShowGIs].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIShowGIsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.showGIs = ShowGIs(true)

        assertEquals("${tgt.tool.value} $FlagShowGIs", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.showGIs = ShowGIs(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagShowGIs, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagNumDescriptions)
  inner class BlastCLINumDescriptions {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINumDescriptionsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numDescriptions = NumDescriptions(12u)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagNumDescriptions))
        assertTrue(json[FlagNumDescriptions].isIntegralNumber)
        assertEquals(12, json[FlagNumDescriptions].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINumDescriptionsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numDescriptions = NumDescriptions(12u)

        assertEquals("${tgt.tool.value} $FlagNumDescriptions 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numDescriptions = NumDescriptions(12u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNumDescriptions, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagNumAlignments)
  inner class BlastCLINumAlignments {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLINumAlignmentsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numAlignments = NumAlignments(12u)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagNumAlignments))
        assertTrue(json[FlagNumAlignments].isIntegralNumber)
        assertEquals(12, json[FlagNumAlignments].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLINumAlignmentsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numAlignments = NumAlignments(12u)

        assertEquals("${tgt.tool.value} $FlagNumAlignments 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.numAlignments = NumAlignments(12u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagNumAlignments, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagLineLength)
  inner class BlastCLILineLength {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLILineLengthJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.lineLength = LineLength(12u)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagLineLength))
        assertTrue(json[FlagLineLength].isIntegralNumber)
        assertEquals(12, json[FlagLineLength].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLILineLengthCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.lineLength = LineLength(12u)

        assertEquals("${tgt.tool.value} $FlagLineLength 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.lineLength = LineLength(12u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagLineLength, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagHTML)
  inner class BlastCLIHTML {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIHTMLJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.html = HTML(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagHTML))
        assertTrue(json[FlagHTML].isBoolean)
        assertTrue(json[FlagHTML].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIHTMLCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.html = HTML(true)

        assertEquals("${tgt.tool.value} $FlagHTML", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.html = HTML(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagHTML, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagSortHits)
  inner class BlastCLISortHits {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISortHitsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.sortHits = SortHits(HitSorting.ByExpectValue)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagSortHits))
        assertTrue(json[FlagSortHits].isIntegralNumber)
        assertEquals(0, json[FlagSortHits].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISortHitsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.sortHits = SortHits(HitSorting.ByExpectValue)

        assertEquals("${tgt.tool.value} $FlagSortHits 0", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.sortHits = SortHits(HitSorting.ByExpectValue)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSortHits, cli[1])
        assertEquals("0", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagSortHSPs)
  inner class BlastCLISortHSPs {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISortHSPsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.sortHSPs = SortHSPs(HSPSorting.ByHSPExpectValue)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagSortHSPs))
        assertTrue(json[FlagSortHSPs].isIntegralNumber)
        assertEquals(0, json[FlagSortHSPs].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISortHSPsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.sortHSPs = SortHSPs(HSPSorting.ByHSPExpectValue)

        assertEquals("${tgt.tool.value} $FlagSortHSPs 0", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.sortHSPs = SortHSPs(HSPSorting.ByHSPExpectValue)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSortHSPs, cli[1])
        assertEquals("0", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagMaxTargetSeqs)
  inner class BlastCLIMaxTargetSeqs {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIMaxTargetSeqsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.maxTargetSeqs = MaxTargetSeqs(12u)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagMaxTargetSeqs))
        assertTrue(json[FlagMaxTargetSeqs].isIntegralNumber)
        assertEquals(12, json[FlagMaxTargetSeqs].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIMaxTargetSeqsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.maxTargetSeqs = MaxTargetSeqs(12u)

        assertEquals("${tgt.tool.value} $FlagMaxTargetSeqs 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.maxTargetSeqs = MaxTargetSeqs(12u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagMaxTargetSeqs, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagParseDefLines)
  inner class BlastCLIParseDefLines {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIParseDefLinesJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.parseDefLines = ParseDefLines(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagParseDefLines))
        assertTrue(json[FlagParseDefLines].isBoolean)
        assertTrue(json[FlagParseDefLines].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIParseDefLinesCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.parseDefLines = ParseDefLines(true)

        assertEquals("${tgt.tool.value} $FlagParseDefLines", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.parseDefLines = ParseDefLines(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagParseDefLines, cli[1])
      }
    }
  }
}