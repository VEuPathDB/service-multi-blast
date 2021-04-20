package mb.lib.blast.model.impl;

import java.io.File;
import java.math.BigDecimal;

import mb.lib.blast.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import mb.api.service.cli.CliBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@DisplayName("BlastConfigImpl")
class BlastConfigImplTest
{
  CliBuilder mockCLI;

  @BeforeEach
  void setUp() {
    mockCLI = Mockito.mock(CliBuilder.class);
    Mockito.doReturn(mockCLI).when(mockCLI).append(ArgumentMatchers.any(), ArgumentMatchers.any());
    Mockito.doReturn(mockCLI).when(mockCLI).appendNonNull(ArgumentMatchers.any(), ArgumentMatchers.any());
  }

  @Nested
  @DisplayName("-help flag")
  class Help
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.enableHelp(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.Help);
        Mockito.verifyNoMoreInteractions(mockCLI);
      }

      @Test
      @DisplayName("is the only flag in cli output when multiple are set")
      void test2() {
        var tgt = new BlastConfigImpl<>();
        tgt.enableHelp(true);
        tgt.enableVersion(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.Help);
        Mockito.verifyNoMoreInteractions(mockCLI);
      }
    }
  }

  @Nested
  @DisplayName("-version flag")
  class Version
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.enableVersion(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.Version);
        Mockito.verifyNoMoreInteractions(mockCLI);
      }

      @Test
      @DisplayName("is the only flag in cli output when multiple are set")
      void test2() {
        var tgt = new BlastConfigImpl<>();
        tgt.enableVersion(true);
        tgt.setQuery("hello");

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.Version);
        Mockito.verifyNoMoreInteractions(mockCLI);
      }
    }
  }

  @Nested
  @DisplayName("-query flag")
  class Query
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setQuery("happy");

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.Query, "happy");
      }
    }
  }

  @Nested
  @DisplayName("-query_loc flag")
  class QueryLocation
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        var loc = Mockito.mock(Location.class);
        tgt.setQueryLocation(loc);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.QueryLocation, loc);
      }
    }
  }

  @Nested
  @DisplayName("-db flag")
  class BlastDatabase
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt  = new BlastConfigImpl<>();
        var path = "holo";
        tgt.setDatabase(path);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.BlastDatabase, path);
      }
    }
  }

  @Nested
  @DisplayName("-out flag")
  class OutputFile
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt  = new BlastConfigImpl<>();
        var file = Mockito.mock(File.class);
        tgt.setOutputFile(file);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.OutputFile, file);
      }
    }
  }

  @Nested
  @DisplayName("-evalue flag")
  class ExpectValue
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        var exp = new BigDecimal("1234");
        tgt.setExpectValue(exp);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.ExpectValue, exp);
      }
    }
  }

  @Nested
  @DisplayName("-fmt flag")
  class OutFormat
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        var fmt = Mockito.mock(BlastReportFormat.class);
        tgt.setReportFormat(fmt);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.OutputFormat, fmt);
      }
    }
  }

  @Nested
  @DisplayName("-num_descriptions flag")
  class NumDescriptions
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setNumDescriptions(66);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.NumDescriptions, 66);
      }
    }
  }

  @Nested
  @DisplayName("-num_alignments flag")
  class NumAlignments
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setNumAlignments(66);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.NumAlignments, 66);
      }
    }
  }

  @Nested
  @DisplayName("-line_length flag")
  class LineLength
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setLineLength(42);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.LineLength, 42);
      }
    }
  }

  @Nested
  @DisplayName("-sorthsps flag")
  class HSPSorting
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setHSPSorting(mb.lib.blast.model.HSPSorting.ByPercentIdentity);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.HSPSorting, mb.lib.blast.model.HSPSorting.ByPercentIdentity);
      }
    }
  }

  @Nested
  @DisplayName("-sorthits flag")
  class HitSorting
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setHitSorting(mb.lib.blast.model.HitSorting.ByPercentIdentity);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(
          ToolOption.HitSorting,
          mb.lib.blast.model.HitSorting.ByPercentIdentity
        );
      }
    }
  }

  @Nested
  @DisplayName("-qcov_hsp_perc flag")
  class QueryCoveragePercentHSP
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setQueryCoveragePercentHSP(2.3);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.QueryCoveragePercentHSP, 2.3);
      }
    }
  }

  @Nested
  @DisplayName("-max_hsps flag")
  class MaxHSPs
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setMaxHSPs(69);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.MaxHSPs, 69);
      }
    }
  }

  @Nested
  @DisplayName("-max_target_seqs flag")
  class MaxTargetSequences
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setMaxTargetSequences(69);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.MaxTargetSequences, 69);
      }
    }
  }

  @Nested
  @DisplayName("-dbsize flag")
  class EffectiveDatabaseSize
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setEffectiveDatabaseSize((byte) 69);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.EffectiveDatabaseSize, (byte) 69);
      }
    }
  }

  @Nested
  @DisplayName("-searchsp flag")
  class SearchSpaceEffectiveLength
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setEffectiveSearchSpaceLength((byte) 57);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.SearchSpaceEffectiveLength, (byte) 57);
      }
    }
  }

  @Nested
  @DisplayName("-import_search_strategy flag")
  class ImportSearchStrategy
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        var inp = new File("test");
        tgt.setSearchStrategyImportFile(inp);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.ImportSearchStrategy, inp);
      }
    }
  }

  @Nested
  @DisplayName("-export_search_strategy flag")
  class ExportSearchStrategy
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        var inp = new File("test");
        tgt.setSearchStrategyExportFile(inp);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.ExportSearchStrategy, inp);
      }
    }
  }

  @Nested
  @DisplayName("-xdrop_ungap flag")
  class UngappedExtensionDropoff
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setExtensionDropoffUngapped(5.6);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.ExtensionDropoffUngapped, 5.6);
      }
    }
  }

  @Nested
  @DisplayName("-num_threads flag")
  class NumThreads
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setThreadCount((byte) 5);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.NumberOfThreads, (byte) 5);
      }
    }
  }

  @Nested
  @DisplayName("-entrez_query flag")
  class EntrezQuery
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setEntrezQuery("goodbye");

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.EntrezQuery, "goodbye");
      }
    }
  }

  @Nested
  @DisplayName("-soft_masking flag")
  class SoftMasking
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.enableSoftMasking(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.SoftMasking, true);
      }
    }
  }

  @Nested
  @DisplayName("-window_size flag")
  class MultiHitWindowSize
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.setMultiHitWindowSize(77);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.MultiHitWindowSize, 77);
      }
    }
  }

  @Nested
  @DisplayName("-show_gis flag")
  class ShowNCBIGIs
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.enableNCBIGenInfoIds(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.ShowNCBIGIs);
      }
    }
  }

  @Nested
  @DisplayName("-html flag")
  class HTMLOutput
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.enableHTMLOutput(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.HTMLOutput);
      }
    }
  }

  @Nested
  @DisplayName("-lcase_masking flag")
  class LowercaseMasking
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.enableLowercaseMasking(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.LowercaseMasking);
      }
    }
  }

  @Nested
  @DisplayName("-parse_deflines flag")
  class ParseDefLines
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.enableDefLineParsing(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.ParseDefLines);
      }
    }
  }

  @Nested
  @DisplayName("-remote flag")
  class Remote
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastConfigImpl<>();
        tgt.enableRemoteSearchExecution(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.Remote);
      }
    }
  }

  @Nested
  @DisplayName("all flags end to end")
  class AllFlags
  {
    @Test
    @DisplayName("all flags appear in CLI string")
    void test1() {
      var tgt = new BlastConfigImpl<>();

      tgt.setQuery("hello 1")
        .setDatabase("hi")
        .setOutputFile(new File("test1"))
        .setExpectValue(new BigDecimal(77))
        .setReportFormat(new ReportFormatImpl(BlastReportType.CSV, "v", BlastReportField.BlastTracebackOps))
        .setNumDescriptions(69)
        .setNumAlignments(777)
        .setLineLength(666)
        .setHSPSorting(mb.lib.blast.model.HSPSorting.ByScore)
        .setHitSorting(mb.lib.blast.model.HitSorting.ByBitScore)
        .setQueryCoveragePercentHSP(1.3)
        .setMaxHSPs(13)
        .setMaxTargetSequences(86)
        .setEffectiveDatabaseSize((byte) 76)
        .setEffectiveSearchSpaceLength((byte) 88)
        .setSearchStrategyImportFile(new File("test2"))
        .setSearchStrategyExportFile(new File("test3"))
        .setExtensionDropoffUngapped(3.33)
        .setThreadCount((byte) 12)
        .setEntrezQuery("query")
        .enableSoftMasking(true)
        .setMultiHitWindowSize(67)
        .enableNCBIGenInfoIds(true)
        .enableHTMLOutput(true)
        .enableLowercaseMasking(true)
        .enableDefLineParsing(true)
        .enableRemoteSearchExecution(true)
        .setQueryLocation(new LocationImpl(23, 32));

      var cli = new CliBuilder();
      tgt.toCli(cli);

      var args = cli.toArgPairs();
      Assertions.assertEquals(args.length, 28);
      Assertions.assertEquals(args[0].length, 2);
      Assertions.assertEquals(args[0][0], "-query");
      Assertions.assertEquals(args[0][1], "hello 1");

      Assertions.assertEquals(args[1].length, 2);
      Assertions.assertEquals(args[1][0], "-query_loc");
      Assertions.assertEquals(args[1][1], "23-32");

      Assertions.assertEquals(args[2].length, 2);
      Assertions.assertEquals(args[2][0], "-db");
      Assertions.assertEquals(args[2][1], "hi");

      Assertions.assertEquals(args[3].length, 2);
      Assertions.assertEquals(args[3][0], "-out");
      Assertions.assertEquals(args[3][1], "test1");

      Assertions.assertEquals(args[4].length, 2);
      Assertions.assertEquals(args[4][0], "-evalue");
      Assertions.assertEquals(args[4][1], "77");

      Assertions.assertEquals(args[5].length, 2);
      Assertions.assertEquals(args[5][0], "-outfmt");
      Assertions.assertEquals(args[5][1], "10 delim=v btop");

      Assertions.assertEquals(args[6].length, 2);
      Assertions.assertEquals(args[6][0], "-num_descriptions");
      Assertions.assertEquals(args[6][1], "69");

      Assertions.assertEquals(args[7].length, 2);
      Assertions.assertEquals(args[7][0], "-num_alignments");
      Assertions.assertEquals(args[7][1], "777");

      Assertions.assertEquals(args[8].length, 2);
      Assertions.assertEquals(args[8][0], "-line_length");
      Assertions.assertEquals(args[8][1], "666");

      Assertions.assertEquals(args[9].length, 2);
      Assertions.assertEquals(args[9][0], "-sorthsps");
      Assertions.assertEquals(args[9][1], "1");

      Assertions.assertEquals(args[10].length, 2);
      Assertions.assertEquals(args[10][0], "-sorthits");
      Assertions.assertEquals(args[10][1], "1");

      Assertions.assertEquals(args[11].length, 2);
      Assertions.assertEquals(args[11][0], "-qcov_hsp_perc");
      Assertions.assertEquals(args[11][1], "1.3");

      Assertions.assertEquals(args[12].length, 2);
      Assertions.assertEquals(args[12][0], "-max_hsps");
      Assertions.assertEquals(args[12][1], "13");

      Assertions.assertEquals(args[13].length, 2);
      Assertions.assertEquals(args[13][0], "-max_target_seqs");
      Assertions.assertEquals(args[13][1], "86");

      Assertions.assertEquals(args[14].length, 2);
      Assertions.assertEquals(args[14][0], "-dbsize");
      Assertions.assertEquals(args[14][1], "76");

      Assertions.assertEquals(args[15].length, 2);
      Assertions.assertEquals(args[15][0], "-searchsp");
      Assertions.assertEquals(args[15][1], "88");

      Assertions.assertEquals(args[16].length, 2);
      Assertions.assertEquals(args[16][0], "-import_search_strategy");
      Assertions.assertEquals(args[16][1], "test2");

      Assertions.assertEquals(args[17].length, 2);
      Assertions.assertEquals(args[17][0], "-export_search_strategy");
      Assertions.assertEquals(args[17][1], "test3");

      Assertions.assertEquals(args[18].length, 2);
      Assertions.assertEquals(args[18][0], "-xdrop_ungap");
      Assertions.assertEquals(args[18][1], "3.33");

      Assertions.assertEquals(args[19].length, 2);
      Assertions.assertEquals(args[19][0], "-num_threads");
      Assertions.assertEquals(args[19][1], "12");

      Assertions.assertEquals(args[20].length, 2);
      Assertions.assertEquals(args[20][0], "-entrez_query");
      Assertions.assertEquals(args[20][1], "query");

      Assertions.assertEquals(args[21].length, 2);
      Assertions.assertEquals(args[21][0], "-soft_masking");
      Assertions.assertEquals(args[21][1], "true");

      Assertions.assertEquals(args[22].length, 2);
      Assertions.assertEquals(args[22][0], "-window_size");
      Assertions.assertEquals(args[22][1], "67");

      Assertions.assertEquals(args[23].length, 2);
      Assertions.assertEquals(args[23][0], "-show_gis");
      Assertions.assertNull(args[23][1]);

      Assertions.assertEquals(args[24].length, 2);
      Assertions.assertEquals(args[24][0], "-html");
      Assertions.assertNull(args[24][1]);

      Assertions.assertEquals(args[25].length, 2);
      Assertions.assertEquals(args[25][0], "-lcase_masking");
      Assertions.assertNull(args[25][1]);

      Assertions.assertEquals(args[26].length, 2);
      Assertions.assertEquals(args[26][0], "-parse_deflines");
      Assertions.assertNull(args[26][1]);

      Assertions.assertEquals(args[27].length, 2);
      Assertions.assertEquals(args[27][0], "-remote");
      Assertions.assertNull(args[27][1]);
    }
  }
}