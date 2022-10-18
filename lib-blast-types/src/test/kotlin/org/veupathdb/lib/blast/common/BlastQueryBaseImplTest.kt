package org.veupathdb.lib.blast.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.common.fields.*

@DisplayName("Blast Query Config Interface Compliance")
internal abstract class BlastQueryBaseImplTest : BlastCLIImplTest() {

  abstract override fun getEmptyImpl(): BlastQueryBase

  @Nested
  @DisplayName(FlagQueryFile)
  inner class BlastCLIQueryFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIQueryFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryFile = QueryFile("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagQueryFile))
        assertTrue(json[FlagQueryFile].isTextual)
        assertEquals("hi", json[FlagQueryFile].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIQueryFileCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryFile = QueryFile("hi")

        assertEquals("${tgt.tool.value} $FlagQueryFile 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryFile = QueryFile("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagQueryFile, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagQueryLocation)
  inner class BlastCLIQueryLocation {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIQueryLocationJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryLocation = QueryLocation(10u, 12u)

        val json = tgt.toJson()

        val expected = """
          {
            "tool" : "${tgt.tool.value}",
            "$FlagQueryLocation" : {
              "start" : 10,
              "stop" : 12
            }
          }""".trimIndent()

        assertEquals(expected, json.toPrettyString())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIQueryLocationCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryLocation = QueryLocation(10u, 12u)

        assertEquals("${tgt.tool.value} $FlagQueryLocation '${tgt.queryLocation.start}-${tgt.queryLocation.stop}'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.queryLocation = QueryLocation(10u, 12u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagQueryLocation, cli[1])
        assertEquals("${tgt.queryLocation.start}-${tgt.queryLocation.stop}", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagDBFile)
  inner class BlastCLIDBFile {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIDBFileJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbFile = DBFiles("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagDBFile))
        assertTrue(json[FlagDBFile].isArray)
        assertEquals(1, json[FlagDBFile].size())
        assertTrue(json[FlagDBFile].get(0).isTextual)
        assertEquals("hi", json[FlagDBFile].get(0).textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIDBFileCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbFile = DBFiles("hi", "bye")

        assertEquals("${tgt.tool.value} $FlagDBFile 'hi bye'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbFile = DBFiles("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagDBFile, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagExpectValue)
  inner class BlastCLIExpectValue {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIExpectValueJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.expectValue = ExpectValue("33")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagExpectValue))
        assertTrue(json[FlagExpectValue].isTextual)
        assertEquals("33", json[FlagExpectValue].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIExpectValueCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.expectValue = ExpectValue("33")

        assertEquals("${tgt.tool.value} $FlagExpectValue '33'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.expectValue = ExpectValue("33")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagExpectValue, cli[1])
        assertEquals("33", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagLowercaseMasking)
  inner class BlastCLIShortHelp {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIShortHelpJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.lowercaseMasking = LowercaseMasking(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagLowercaseMasking))
        assertTrue(json[FlagLowercaseMasking].isBoolean)
        assertTrue(json[FlagLowercaseMasking].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIShortHelpCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.lowercaseMasking = LowercaseMasking(true)

        assertEquals("${tgt.tool.value} $FlagLowercaseMasking", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.lowercaseMasking = LowercaseMasking(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagLowercaseMasking, cli[1])
      }
    }
  }

  @Nested
  @DisplayName(FlagMaxHSPs)
  inner class BlastCLIMaxHSPs {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIMaxHSPsJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.maxHSPs = MaxHSPs(12u)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagMaxHSPs))
        assertTrue(json[FlagMaxHSPs].isIntegralNumber)
        assertEquals(12, json[FlagMaxHSPs].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIMaxHSPsCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.maxHSPs = MaxHSPs(12u)

        assertEquals("${tgt.tool.value} $FlagMaxHSPs 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.maxHSPs = MaxHSPs(12u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagMaxHSPs, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagDBSize)
  inner class BlastCLIDBSize {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIDBSizeJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbSize = DBSize(12)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagDBSize))
        assertTrue(json[FlagDBSize].isIntegralNumber)
        assertEquals(12, json[FlagDBSize].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIDBSizeCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbSize = DBSize(12)

        assertEquals("${tgt.tool.value} $FlagDBSize 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.dbSize = DBSize(12)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagDBSize, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagSearchSpace)
  inner class BlastCLISearchSpace {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLISearchSpaceJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.searchSpace = SearchSpace(12)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagSearchSpace))
        assertTrue(json[FlagSearchSpace].isIntegralNumber)
        assertEquals(12, json[FlagSearchSpace].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLISearchSpaceCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.searchSpace = SearchSpace(12)

        assertEquals("${tgt.tool.value} $FlagSearchSpace 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.searchSpace = SearchSpace(12)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagSearchSpace, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagImportSearchStrategy)
  inner class BlastCLIImportSearchStrategy {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIImportSearchStrategyJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.importSearchStrategy = ImportSearchStrategy("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagImportSearchStrategy))
        assertTrue(json[FlagImportSearchStrategy].isTextual)
        assertEquals("hi", json[FlagImportSearchStrategy].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIImportSearchStrategyCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.importSearchStrategy = ImportSearchStrategy("hi")

        assertEquals("${tgt.tool.value} $FlagImportSearchStrategy 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.importSearchStrategy = ImportSearchStrategy("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagImportSearchStrategy, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagExportSearchStrategy)
  inner class BlastCLIExportSearchStrategy {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIExportSearchStrategyJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.exportSearchStrategy = ExportSearchStrategy("hi")

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagExportSearchStrategy))
        assertTrue(json[FlagExportSearchStrategy].isTextual)
        assertEquals("hi", json[FlagExportSearchStrategy].textValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIExportSearchStrategyCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.exportSearchStrategy = ExportSearchStrategy("hi")

        assertEquals("${tgt.tool.value} $FlagExportSearchStrategy 'hi'", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.exportSearchStrategy = ExportSearchStrategy("hi")

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagExportSearchStrategy, cli[1])
        assertEquals("hi", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagXDropUngap)
  inner class BlastCLIExtensionDropoffUngapped {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIExtensionDropoffUngappedJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.extensionDropoffUngapped = ExtensionDropoffUngapped(0.1)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagXDropUngap))
        assertTrue(json[FlagXDropUngap].isDouble)
        assertEquals(0.1, json[FlagXDropUngap].doubleValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIExtensionDropoffUngappedCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.extensionDropoffUngapped = ExtensionDropoffUngapped(0.1)

        assertEquals("${tgt.tool.value} $FlagXDropUngap 0.1", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.extensionDropoffUngapped = ExtensionDropoffUngapped(0.1)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagXDropUngap, cli[1])
        assertEquals("0.1", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagWindowSize)
  inner class BlastCLIWindowSize {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIWindowSizeJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.windowSize = WindowSize(12u)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagWindowSize))
        assertTrue(json[FlagWindowSize].isIntegralNumber)
        assertEquals(12, json[FlagWindowSize].intValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIWindowSizeCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.windowSize = WindowSize(12u)

        assertEquals("${tgt.tool.value} $FlagWindowSize 12", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.windowSize = WindowSize(12u)

        val cli = tgt.toCliArray()

        assertEquals(3, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagWindowSize, cli[1])
        assertEquals("12", cli[2])
      }
    }
  }

  @Nested
  @DisplayName(FlagRemote)
  inner class BlastCLIRemote {

    @Nested
    @DisplayName("when generating json output")
    inner class BlastCLIRemoteJson {

      @Test
      @DisplayName("appends the flag to the output json")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.remote = Remote(true)

        val json = tgt.toJson()

        assertEquals(2, json.size())

        assertTrue(json.has("tool"))
        assertTrue(json["tool"].isTextual)
        assertEquals(tgt.tool.value, json["tool"].textValue())

        assertTrue(json.has(FlagRemote))
        assertTrue(json[FlagRemote].isBoolean)
        assertTrue(json[FlagRemote].booleanValue())
      }
    }

    @Nested
    @DisplayName("when generating cli call strings")
    inner class BlastCLIRemoteCLIString {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.remote = Remote(true)

        assertEquals("${tgt.tool.value} $FlagRemote", tgt.toCliString())
      }
    }

    @Nested
    @DisplayName("when generating cli call argument lists")
    inner class BlastCLIToolCLIList {

      @Test
      @DisplayName("appends the flag to the cli call string")
      fun t1() {
        val tgt = getEmptyImpl()
        tgt.remote = Remote(true)

        val cli = tgt.toCliArray()

        assertEquals(2, cli.size)
        assertEquals(tgt.tool.value, cli[0])
        assertEquals(FlagRemote, cli[1])
      }
    }
  }

}