package mb.lib.blast.model.impl;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;

import mb.lib.blast.model.*;
import mb.lib.blast.model.n.DcTemplateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import mb.lib.blast.model.n.BlastNTask;
import mb.lib.blast.model.n.Dust;
import mb.api.service.cli.CliBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("BlastNConfigImpl")
class BlastNConfigImplTest
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
        var tgt = new BlastNConfigImpl();
        tgt.enableHelp(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.Help);
        Mockito.verifyNoMoreInteractions(mockCLI);
      }

      @Test
      @DisplayName("is the only flag in cli output when multiple are set")
      void test2() {
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
        tgt.enableVersion(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.Version);
        Mockito.verifyNoMoreInteractions(mockCLI);
      }

      @Test
      @DisplayName("is the only flag in cli output when multiple are set")
      void test2() {
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt  = new BlastNConfigImpl();
        var path = "hello";
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
        var tgt  = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
        tgt.setLineLength(42);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.LineLength, 42);
      }
    }
  }

  @Nested
  @DisplayName("-sorthsps flag")
  class HSPSortingTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setHSPSorting(HSPSorting.ByPercentIdentity);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.HSPSorting, HSPSorting.ByPercentIdentity);
      }
    }
  }

  @Nested
  @DisplayName("-sorthits flag")
  class HitSortingTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setHitSorting(HitSorting.ByPercentIdentity);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(
          ToolOption.HitSorting,
          HitSorting.ByPercentIdentity
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
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
        var tgt = new BlastNConfigImpl();
        tgt.enableRemoteSearchExecution(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.Remote);
      }
    }
  }

  @Nested
  @DisplayName("-task flag")
  class Task
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setTask(BlastNTask.Megablast);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.Task, BlastNTask.Megablast);
      }
    }
  }

  @Nested
  @DisplayName("-reward flag")
  class Reward
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setNucleotideMatchReward(33);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.MatchReward, 33);
      }
    }
  }

  @Nested
  @DisplayName("-penalty flag")
  class Penalty
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setNucleotideMismatchPenalty(33);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.MismatchPenalty, 33);
      }
    }
  }

  @Nested
  @DisplayName("-use_index flag")
  class UseIndex
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.enableMegablastDbIndexUsage(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.UseMegablastIndex, true);
      }
    }
  }

  @Nested
  @DisplayName("-index_name flag")
  class IndexName
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setMegablastDbIndexName("toast");

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.MegablastIndexName, "toast");
      }
    }
  }

  @Nested
  @DisplayName("-dust flag")
  class DustTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt  = new BlastNConfigImpl();
        var dust = Mockito.mock(Dust.class);
        tgt.setDust(dust);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.Dust, dust);
      }
    }
  }

  @Nested
  @DisplayName("-filtering_db flag")
  class FilteringDBTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt  = new BlastNConfigImpl();
        var path = Mockito.mock(Path.class);
        tgt.setFilteringDbPath(path);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.FilteringDatabasePath, path);
      }
    }
  }

  @Nested
  @DisplayName("-window_masker_taxid flag")
  class WindowMaskerTaxIDTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setWindowMaskerTaxId(78);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.WindowMaskerTaxonomicID, 78);
      }
    }
  }

  @Nested
  @DisplayName("-window_masker_db flag")
  class WindowMaskerDBPathTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt  = new BlastNConfigImpl();
        var path = Mockito.mock(Path.class);
        tgt.setWindowMaskerDbPath(path);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.WindowMaskerDatabasePath, path);
      }
    }
  }

  @Nested
  @DisplayName("-template_type flag")
  class DCMegablastTemplateTypeTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setDiscontiguousMegablastTemplateType(DcTemplateType.Both);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(
          ToolOption.DiscontiguousMegablastTemplateType,
          DcTemplateType.Both
        );
      }
    }
  }

  @Nested
  @DisplayName("-template_length flag")
  class DCMegablastTemplateLengthTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setDiscontiguousMegablastTemplateLength((byte) 78);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.DiscontiguousMegablastTemplateLength, (byte) 78);
      }
    }
  }

  @Nested
  @DisplayName("-perc_identity flag")
  class PercentIdentityTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setPercentIdentity(7.9);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.PercentIdentity, 7.9);
      }
    }
  }

  @Nested
  @DisplayName("-min_raw_gapped_score flag")
  class MinRawGappedScoreTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setMinRawGappedScore(88);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.MinRawGappedScore, 88);
      }
    }
  }

  @Nested
  @DisplayName("-off_diagonal_range flag")
  class OffDiagonalRangeTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setOffDiagonalRange(90);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.OffDiagonalRange, 90);
      }
    }
  }

  @Nested
  @DisplayName("-no_greedy flag")
  class NonGreedyExtensionTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.enableNonGreedyDynamicProgramExtension(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.NonGreedyExtension);
      }
    }
  }

  @Nested
  @DisplayName("-best_hit_overhang flag")
  class BestHitOverhangTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setBestHitOverhang(1.1);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.BestHitOverhang, 1.1);
      }
    }
  }

  @Nested
  @DisplayName("-best_hit_score_edge flag")
  class BestHitScoreEdgeTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setBestHitScoreEdge(2.1);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.BestHitScoreEdge, 2.1);
      }
    }
  }

  @Nested
  @DisplayName("-subject_besthit flag")
  class SubjectBestHitTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.enableSubjectBestHit(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.SubjectBestHit);
      }
    }
  }

  @Nested
  @DisplayName("-culling_limit flag")
  class CullingLimitTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setCullingLimit(28);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.CullingLimit, 28);
      }
    }
  }

  @Nested
  @DisplayName("-db_soft_mask flag")
  class DBSoftMaskTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setDbSoftMaskAlgorithmId("whot");

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.DatabaseSoftMask, "whot");
      }
    }
  }

  @Nested
  @DisplayName("-db_hard_mask flag")
  class DBHardMaskTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setDbHardMaskAlgorithmId("this");

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.DatabaseHardMask, "this");
      }
    }
  }

  @Nested
  @DisplayName("-gapopen flag")
  class GapOpenTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setGapCostOpen(32);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.GapCostOpen, 32);
      }
    }
  }

  @Nested
  @DisplayName("-gapextend flag")
  class GapExtendTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setGapCostExtend(32);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.GapCostExtend, 32);
      }
    }
  }

  @Nested
  @DisplayName("-xdrop_gap flag")
  class XDropGapTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setExtensionDropoffPreliminaryGapped(5.6);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.ExtensionDropoffPrelimGapped, 5.6);
      }
    }
  }

  @Nested
  @DisplayName("-xdrop_gap_final flag")
  class XDropGapFinalTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setExtensionDropoffFinalGapped(6.7);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.ExtensionDropoffFinalGapped, 6.7);
      }
    }
  }

  @Nested
  @DisplayName("-taxids flag")
  class TaxIDsTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        var inp = new int[] {32, 64};
        tgt.setTaxIDs(inp);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.TaxonomyIDs, "32,64");
      }
    }
  }

  @Nested
  @DisplayName("-negative_taxids flag")
  class NegativeTaxIDsTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        var inp = new int[] {32, 64};
        tgt.setNegativeTaxIds(inp);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.NegativeTaxonomyIDs, "32,64");
      }
    }
  }

  @Nested
  @DisplayName("-ungapped flag")
  class UngappedTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.enableUngappedAlignmentOnly(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).append(ToolOption.UngappedAlignmentOnly);
      }
    }
  }

  @Nested
  @DisplayName("-subject flag")
  class SubjectTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        var in  = Mockito.mock(File.class);
        tgt.setSubjectFile(in);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.SubjectFile, in);
      }
    }
  }

  @Nested
  @DisplayName("-subject_loc flag")
  class SubjectLocationTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        var in  = Mockito.mock(Location.class);
        tgt.setSubjectLocation(in);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.SubjectLocation, in);
      }
    }
  }

  @Nested
  @DisplayName("-word_size flag")
  class WordSizeTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setWordSize(34);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.WordSize, 34);
      }
    }
  }

  @Nested
  @DisplayName("-seqidlist flag")
  class SeqIDListTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        var in  = Mockito.mock(File.class);
        tgt.setSequenceIDListFile(in);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.SequenceIDListFile, in);
      }
    }
  }

  @Nested
  @DisplayName("-negative_seqidlist flag")
  class NegativeSeqIDListTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        var in  = Mockito.mock(File.class);
        tgt.setNegativeSequenceIDListFile(in);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.NegativeSequenceIDListFile, in);
      }
    }
  }

  @Nested
  @DisplayName("-taxidlist flag")
  class TaxIDListTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        var in  = Mockito.mock(File.class);
        tgt.setTaxIDListFile(in);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.TaxonomyIDListFile, in);
      }
    }
  }

  @Nested
  @DisplayName("-negative_taxidlist flag")
  class NegativeTaxIDListTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        var in  = Mockito.mock(File.class);
        tgt.setNegativeTaxIDListFile(in);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.NegativeTaxonomyIDListFile, in);
      }
    }
  }

  @Nested
  @DisplayName("-gilist flag")
  class GIListTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        var in  = Mockito.mock(File.class);
        tgt.setGenInfoIdListFile(in);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.GIListFile, in);
      }
    }
  }

  @Nested
  @DisplayName("-negative_gilist flag")
  class NegativeGIListTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        var in  = Mockito.mock(File.class);
        tgt.setNegativeGenInfoIdListFile(in);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.NegativeGIListFile, in);
      }
    }
  }

  @Nested
  @DisplayName("-strand flag")
  class StrandTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.setStrand(QueryStrand.Minus);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.QueryStrand, QueryStrand.Minus);
      }
    }
  }

  @Nested
  @DisplayName("-sum_stats flag")
  class SumStatsTest
  {
    @Nested
    @DisplayName("When set")
    class Set
    {
      @Test
      @DisplayName("appears in cli output")
      void test1() {
        var tgt = new BlastNConfigImpl();
        tgt.enableSumStatistics(true);

        tgt.toCli(mockCLI);

        Mockito.verify(mockCLI).appendNonNull(ToolOption.SumStats, true);
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
      var tgt = new BlastNConfigImpl();

      tgt.setQuery("hello 1")
        .setDatabase("hi")
        .setOutputFile(new File("test1"))
        .setExpectValue(new BigDecimal(77))
        .setReportFormat(new ReportFormatImpl(
          BlastReportType.CSV,
          "v",
          BlastReportField.BlastTracebackOps
        ))
        .setNumDescriptions(69)
        .setNumAlignments(777)
        .setLineLength(666)
        .setHSPSorting(HSPSorting.ByScore)
        .setHitSorting(HitSorting.ByBitScore)
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
        .setQueryLocation(new LocationImpl(23, 32))
        // BlastN specific
        .setTask(BlastNTask.BlastNShort)
        .setNucleotideMatchReward(10)
        .setNucleotideMismatchPenalty(11)
        .enableMegablastDbIndexUsage(true)
        .setMegablastDbIndexName("name")
        .setDust(new DustImpl(1, 2, 3))
        .setFilteringDbPath(Path.of("bye"))
        .setWindowMaskerTaxId(12)
        .setWindowMaskerDbPath(Path.of("yo"))
        .setDiscontiguousMegablastTemplateType(DcTemplateType.Coding)
        .setDiscontiguousMegablastTemplateLength((byte) 13)
        .setPercentIdentity(1.23)
        .setMinRawGappedScore(14)
        .setOffDiagonalRange(15)
        .setBestHitOverhang(2.34)
        .setBestHitScoreEdge(3.45)
        .enableSubjectBestHit(true)
        .setCullingLimit(16)
        .setDbSoftMaskAlgorithmId("id1")
        .setDbHardMaskAlgorithmId("id2")
        .setGapCostOpen(17)
        .setGapCostExtend(18)
        .setExtensionDropoffPreliminaryGapped(4.56)
        .setExtensionDropoffFinalGapped(5.67)
        .setTaxIDs(new int[]{19, 20})
        .setNegativeTaxIds(new int[]{21, 22})
        .enableUngappedAlignmentOnly(true)
        .setSubjectFile(new File("test4"))
        .setSubjectLocation(new LocationImpl(23, 24))
        .setWordSize(25)
        .setSequenceIDListFile(new File("test5"))
        .setNegativeSequenceIDListFile(new File("test6"))
        .setTaxIDListFile(new File("test7"))
        .setNegativeTaxIDListFile(new File("test8"))
        .setGenInfoIdListFile(new File("test9"))
        .setNegativeGenInfoIdListFile(new File("test10"))
        .setStrand(QueryStrand.Plus)
        .enableSumStatistics(true)
        ;

      var cli = new CliBuilder();
      tgt.toCli(cli);

      var args = cli.toArgPairs();
      Assertions.assertEquals(args.length, 66);

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

      Assertions.assertEquals(args[28].length, 2);
      Assertions.assertEquals(args[28][0], "-task");
      Assertions.assertEquals(args[28][1], BlastNTask.BlastNShort.getValue());

      Assertions.assertEquals(args[29].length, 2);
      Assertions.assertEquals(args[29][0], "-reward");
      Assertions.assertEquals(args[29][1], "10");

      Assertions.assertEquals(args[30].length, 2);
      Assertions.assertEquals(args[30][0], "-penalty");
      Assertions.assertEquals(args[30][1], "11");

      Assertions.assertEquals(args[31].length, 2);
      Assertions.assertEquals(args[31][0], "-use_index");
      Assertions.assertEquals(args[31][1], "true");

      Assertions.assertEquals(args[32].length, 2);
      Assertions.assertEquals(args[32][0], "-index_name");
      Assertions.assertEquals(args[32][1], "name");

      Assertions.assertEquals(args[33].length, 2);
      Assertions.assertEquals(args[33][0], "-dust");
      Assertions.assertEquals(args[33][1], "1 2 3");

      Assertions.assertEquals(args[34].length, 2);
      Assertions.assertEquals(args[34][0], "-filtering_db");
      Assertions.assertEquals(args[34][1], "bye");

      Assertions.assertEquals(args[35].length, 2);
      Assertions.assertEquals(args[35][0], "-window_masker_taxid");
      Assertions.assertEquals(args[35][1], "12");

      Assertions.assertEquals(args[36].length, 2);
      Assertions.assertEquals(args[36][0], "-window_masker_db");
      Assertions.assertEquals(args[36][1], "yo");

      Assertions.assertEquals(args[37].length, 2);
      Assertions.assertEquals(args[37][0], "-template_type");
      Assertions.assertEquals(args[37][1], "coding");

      Assertions.assertEquals(args[38].length, 2);
      Assertions.assertEquals(args[38][0], "-template_length");
      Assertions.assertEquals(args[38][1], "13");

      Assertions.assertEquals(args[39].length, 2);
      Assertions.assertEquals(args[39][0], "-perc_identity");
      Assertions.assertEquals(args[39][1], "1.23");

      Assertions.assertEquals(args[40].length, 2);
      Assertions.assertEquals(args[40][0], "-min_raw_gapped_score");
      Assertions.assertEquals(args[40][1], "14");

      Assertions.assertEquals(args[41].length, 2);
      Assertions.assertEquals(args[41][0], "-off_diagonal_range");
      Assertions.assertEquals(args[41][1], "15");

      Assertions.assertEquals(args[42].length, 2);
      Assertions.assertEquals(args[42][0], "-best_hit_overhang");
      Assertions.assertEquals(args[42][1], "2.34");

      Assertions.assertEquals(args[43].length, 2);
      Assertions.assertEquals(args[43][0], "-best_hit_score_edge");
      Assertions.assertEquals(args[43][1], "3.45");

      Assertions.assertEquals(args[44].length, 2);
      Assertions.assertEquals(args[44][0], "-subject_besthit");
      Assertions.assertNull(args[44][1]);

      Assertions.assertEquals(args[45].length, 2);
      Assertions.assertEquals(args[45][0], "-culling_limit");
      Assertions.assertEquals(args[45][1], "16");

      Assertions.assertEquals(args[46].length, 2);
      Assertions.assertEquals(args[46][0], "-db_soft_mask");
      Assertions.assertEquals(args[46][1], "id1");

      Assertions.assertEquals(args[47].length, 2);
      Assertions.assertEquals(args[47][0], "-db_hard_mask");
      Assertions.assertEquals(args[47][1], "id2");

      Assertions.assertEquals(args[48].length, 2);
      Assertions.assertEquals(args[48][0], "-gapopen");
      Assertions.assertEquals(args[48][1], "17");

      Assertions.assertEquals(args[49].length, 2);
      Assertions.assertEquals(args[49][0], "-gapextend");
      Assertions.assertEquals(args[49][1], "18");

      Assertions.assertEquals(args[50].length, 2);
      Assertions.assertEquals(args[50][0], "-xdrop_gap");
      Assertions.assertEquals(args[50][1], "4.56");

      Assertions.assertEquals(args[51].length, 2);
      Assertions.assertEquals(args[51][0], "-xdrop_gap_final");
      Assertions.assertEquals(args[51][1], "5.67");

      Assertions.assertEquals(args[52].length, 2);
      Assertions.assertEquals(args[52][0], "-taxids");
      Assertions.assertEquals(args[52][1], "19,20");

      Assertions.assertEquals(args[53].length, 2);
      Assertions.assertEquals(args[53][0], "-negative_taxids");
      Assertions.assertEquals(args[53][1], "21,22");

      Assertions.assertEquals(args[54].length, 2);
      Assertions.assertEquals(args[54][0], "-ungapped");
      Assertions.assertNull(args[54][1]);

      Assertions.assertEquals(args[55].length, 2);
      Assertions.assertEquals(args[55][0], "-subject");
      Assertions.assertEquals(args[55][1], "test4");

      Assertions.assertEquals(args[56].length, 2);
      Assertions.assertEquals(args[56][0], "-subject_loc");
      Assertions.assertEquals(args[56][1], "23-24");

      Assertions.assertEquals(args[57].length, 2);
      Assertions.assertEquals(args[57][0], "-word_size");
      Assertions.assertEquals(args[57][1], "25");

      Assertions.assertEquals(args[58].length, 2);
      Assertions.assertEquals(args[58][0], "-seqidlist");
      Assertions.assertEquals(args[58][1], "test5");

      Assertions.assertEquals(args[59].length, 2);
      Assertions.assertEquals(args[59][0], "-negative_seqidlist");
      Assertions.assertEquals(args[59][1], "test6");

      Assertions.assertEquals(args[60].length, 2);
      Assertions.assertEquals(args[60][0], "-taxidlist");
      Assertions.assertEquals(args[60][1], "test7");

      Assertions.assertEquals(args[61].length, 2);
      Assertions.assertEquals(args[61][0], "-negative_taxidlist");
      Assertions.assertEquals(args[61][1], "test8");

      Assertions.assertEquals(args[62].length, 2);
      Assertions.assertEquals(args[62][0], "-gilist");
      Assertions.assertEquals(args[62][1], "test9");

      Assertions.assertEquals(args[63].length, 2);
      Assertions.assertEquals(args[63][0], "-negative_gilist");
      Assertions.assertEquals(args[63][1], "test10");

      Assertions.assertEquals(args[64].length, 2);
      Assertions.assertEquals(args[64][0], "-strand");
      Assertions.assertEquals(args[64][1], "plus");

      Assertions.assertEquals(args[65].length, 2);
      Assertions.assertEquals(args[65][0], "-sum_stats");
      Assertions.assertEquals(args[65][1], "true");
    }
  }
}