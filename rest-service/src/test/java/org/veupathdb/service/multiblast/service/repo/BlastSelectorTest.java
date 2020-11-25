package org.veupathdb.service.multiblast.service.repo;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.HitSorting;
import org.veupathdb.service.multiblast.model.blast.HspSorting;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.impl.LocationImpl;

import static org.mockito.Mockito.*;

@DisplayName("BlastSelector")
class BlastSelectorTest
{
  private BlastSelector<BC> target;
  private BC                dummy;

  @BeforeEach
  void setUp() {
    target = mock(BlastSelector.class);
    dummy  = mock(BC.class);
  }

  private abstract class BC implements BlastConfig<BC>
  {
  }

  @Nested
  @DisplayName("#parseBlastConfig(T, ToolOption, String)")
  class ParseBlastConfig
  {
    @BeforeEach
    void setUp() {
      doCallRealMethod().when(target).parseBlastConfig(any(), any(), any());
    }

    @Nested
    @DisplayName("Sets the expected value when given")
    class Set
    {
      @Test
      @DisplayName("-help")
      void test1() {
        target.parseBlastConfig(dummy, ToolOption.Help, "true");

        verify(dummy).enableHelp(true);
        verifyNoMoreInteractions(dummy);

        target.parseBlastConfig(dummy, ToolOption.Help, "false");

        verify(dummy).enableHelp(false);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-version")
      void test2() {
        target.parseBlastConfig(dummy, ToolOption.Version, "true");

        verify(dummy).enableVersion(true);
        verifyNoMoreInteractions(dummy);

        target.parseBlastConfig(dummy, ToolOption.Version, "false");

        verify(dummy).enableVersion(false);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-query")
      void test3() {
        target.parseBlastConfig(dummy, ToolOption.Query, "yo");

        verify(dummy).setQueryFile(new File("yo"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-query_loc")
      void test4() {
        target.parseBlastConfig(dummy, ToolOption.QueryLocation, "10-199");

        verify(dummy).setQueryLocation(new LocationImpl(10, 199));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-db")
      void test5() {
        target.parseBlastConfig(dummy, ToolOption.BlastDatabase, "hello");

        verify(dummy).setDatabase(Path.of("hello"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-out")
      void test6() {
        target.parseBlastConfig(dummy, ToolOption.OutputFile, "goodbye");

        verify(dummy).setOutputFile(new File("goodbye"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-evalue")
      void test7() {
        target.parseBlastConfig(dummy, ToolOption.ExpectationValue, "3.555");

        verify(dummy).setExpectValue(new BigDecimal("3.555"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-show_gis")
      void test8() {
        target.parseBlastConfig(dummy, ToolOption.ShowNCBIGIs, "true");

        verify(dummy).enableNCBIGenInfoIds(true);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-num_descriptions")
      void test9() {
        target.parseBlastConfig(dummy, ToolOption.NumDescriptions, "35");

        verify(dummy).setNumDescriptions(35);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-num_alignments")
      void test10() {
        target.parseBlastConfig(dummy, ToolOption.NumAlignments, "35");

        verify(dummy).setNumAlignments(35);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-line_length")
      void test11() {
        target.parseBlastConfig(dummy, ToolOption.LineLength, "35");

        verify(dummy).setLineLength(35);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-sorthits")
      void test12() {
        target.parseBlastConfig(dummy, ToolOption.SortHits, "4");

        verify(dummy).setHitSorting(HitSorting.ByQueryCoverage);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-sorthsps")
      void test13() {
        target.parseBlastConfig(dummy, ToolOption.SortHSPs, "3");

        verify(dummy).setHspSorting(HspSorting.ByPercentIdentity);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-lcase_masking")
      void test14() {
        target.parseBlastConfig(dummy, ToolOption.LowercaseMasking, "true");

        verify(dummy).enableLowercaseMasking(true);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-qcov_hsp_perc")
      void test15() {
        target.parseBlastConfig(dummy, ToolOption.QueryCoveragePercentHSP, "4.3232");

        verify(dummy).setQueryCoverageHspPercent(4.3232);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-max_hsps")
      void test16() {
        target.parseBlastConfig(dummy, ToolOption.MaxHSPs, "42");

        verify(dummy).setMaxHsps(42);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-max_target_seqs")
      void test17() {
        target.parseBlastConfig(dummy, ToolOption.MaxTargetSequences, "56");

        verify(dummy).setMaxTargetSequences(56);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-dbsize")
      void test18() {
        target.parseBlastConfig(dummy, ToolOption.DatabaseEffectiveSize, "28");

        verify(dummy).setEffectiveDatabaseSize((byte) 28);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-searchsp")
      void test19() {
        target.parseBlastConfig(dummy, ToolOption.SearchSpaceEffectiveLength, "32");

        verify(dummy).setEffectiveSearchSpaceLength((byte) 32);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-xdrop_ungap")
      void test20() {
        target.parseBlastConfig(dummy, ToolOption.XDropoffUngappedExtensions, "0.77777");

        verify(dummy).setExtensionDropoffUngapped(0.77777);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-parse_deflines")
      void test21() {
        target.parseBlastConfig(dummy, ToolOption.ParseDefLines, "true");

        verify(dummy).enableDefLineParsing(true);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-num_threads")
      void test22() {
        target.parseBlastConfig(dummy, ToolOption.NumberOfThreads, "64");

        verify(dummy).setThreadCount((byte) 64);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-remote")
      void test23() {
        target.parseBlastConfig(dummy, ToolOption.Remote, "true");

        verify(dummy).enableRemoteSearchExecution(true);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-entrez_query")
      void test24() {
        target.parseBlastConfig(dummy, ToolOption.EntrezQuery, "hello world");

        verify(dummy).setEntrezQuery("hello world");
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-soft_masking")
      void test25() {
        target.parseBlastConfig(dummy, ToolOption.SoftMasking, "true");

        verify(dummy).enableSoftMasking(true);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-window_size")
      void test26() {
        target.parseBlastConfig(dummy, ToolOption.MultiHitWindowSize, "123");

        verify(dummy).setMultiHitWindowSize(123);
        verifyNoMoreInteractions(dummy);
      }
    }
  }
}
