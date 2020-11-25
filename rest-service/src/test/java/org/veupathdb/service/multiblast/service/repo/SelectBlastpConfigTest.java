package org.veupathdb.service.multiblast.service.repo;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.blast.impl.LocationImpl;
import org.veupathdb.service.multiblast.model.blast.impl.SegImpl;
import org.veupathdb.service.multiblast.model.blast.p.BlastpConfig;
import org.veupathdb.service.multiblast.model.blast.p.BlastpScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.p.BlastpTask;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("SelectBlastpConfig")
class SelectBlastpConfigTest
{
  private SelectBlastpConfig target;
  private BlastpConfig       dummy;

  @BeforeEach
  void setUp() {
    target = mock(SelectBlastpConfig.class);
    dummy  = mock(BlastpConfig.class);
  }

  @Nested
  @DisplayName("#parseBlastConfig(BlastpConfig, ToolOption, String)")
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

      @Test
      @DisplayName("-task")
      void test27() {
        target.parseBlastConfig(dummy, ToolOption.Task, "blastp");

        verify(dummy).setTask(BlastpTask.BlastP);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-word_size")
      void test28() {
        target.parseBlastConfig(dummy, ToolOption.WordSize, "12");

        verify(dummy).setWordSize(12);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-gapopen")
      void test29() {
        target.parseBlastConfig(dummy, ToolOption.GapCostOpen, "12");

        verify(dummy).setGapCostOpen(12);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-gapextend")
      void test30() {
        target.parseBlastConfig(dummy, ToolOption.GapCostExtend, "12");

        verify(dummy).setGapCostExtend(12);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-matrix")
      void test31() {
        target.parseBlastConfig(dummy, ToolOption.ScoringMatrix, "BLOSUM45");

        verify(dummy).setScoringMatrix(BlastpScoringMatrix.Blosum45);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-threshold")
      void test32() {
        target.parseBlastConfig(dummy, ToolOption.Threshold, "12.12121212");

        verify(dummy).setScoreThreshold(12.12121212);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-comp_based_stats")
      void test33() {
        target.parseBlastConfig(dummy, ToolOption.CompositionBasedStats, "2");

        verify(dummy).setCompBasedStatisticsType(CompBasedStats.ConditionalScoreAdjustment);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-subject")
      void test34() {
        target.parseBlastConfig(dummy, ToolOption.SubjectFile, "nope");

        verify(dummy).setSubjectFile(new File("nope"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-subject_loc")
      void test35() {
        target.parseBlastConfig(dummy, ToolOption.SubjectLocation, "23-45");

        verify(dummy).setSubjectLocation(new LocationImpl(23, 45));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-seg")
      void test36() {
        target.parseBlastConfig(dummy, ToolOption.SEGFilter, "1 0.666 0.777");

        verify(dummy).setSeg(new SegImpl(1, "0.666", "0.777"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-gilist")
      void test37() {
        target.parseBlastConfig(dummy, ToolOption.GIListFile, "hoopla");

        verify(dummy).setGenInfoIdListFile(new File("hoopla"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-negative_gilist")
      void test38() {
        target.parseBlastConfig(dummy, ToolOption.NegativeGIListFile, "hoopla");

        verify(dummy).setNegativeGenInfoIdListFile(new File("hoopla"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-seqidlist")
      void test39() {
        target.parseBlastConfig(dummy, ToolOption.SequenceIDListFile, "hoopla");

        verify(dummy).setSequenceIdListFile(new File("hoopla"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-negative_seqidlist")
      void test40() {
        target.parseBlastConfig(dummy, ToolOption.NegativeSequenceIDListFile, "hoopla");

        verify(dummy).setNegativeSequenceIdListFile(new File("hoopla"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-taxidlist")
      void test41() {
        target.parseBlastConfig(dummy, ToolOption.TaxonomyIDListFile, "hoopla");

        verify(dummy).setTaxIdListFile(new File("hoopla"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-negative_taxidlist")
      void test42() {
        target.parseBlastConfig(dummy, ToolOption.NegativeTaxonomyIDListFile, "hoopla");

        verify(dummy).setNegativeTaxIdListFile(new File("hoopla"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-taxids")
      void test43() {
        target.parseBlastConfig(dummy, ToolOption.TaxonomyIDs, "123,456,789");

        verify(dummy).setTaxIds(new int[]{123, 456, 789});
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-negative_taxids")
      void test44() {
        target.parseBlastConfig(dummy, ToolOption.NegativeTaxonomyIDs, "123,456,789");

        verify(dummy).setNegativeTaxIds(new int[]{123, 456, 789});
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-ipglist")
      void test45() {
        target.parseBlastConfig(dummy, ToolOption.IdenticalProteinGroupListFile, "hoopla");

        verify(dummy).setIdenticalProteinGroupListFile(new File("hoopla"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-negative_ipglist")
      void test46() {
        target.parseBlastConfig(dummy, ToolOption.NegativeIdenticalProteinGroupListFile, "hoopla");

        verify(dummy).setNegativeIdenticalProteinGroupListFile(new File("hoopla"));
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-db_soft_mask")
      void test47() {
        target.parseBlastConfig(dummy, ToolOption.DatabaseSoftMask, "yo");

        verify(dummy).setDbSoftMaskAlgorithmId("yo");
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-db_hard_mask")
      void test48() {
        target.parseBlastConfig(dummy, ToolOption.DatabaseHardMask, "nah");

        verify(dummy).setDbHardMaskAlgorithmId("nah");
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-culling_limit")
      void test49() {
        target.parseBlastConfig(dummy, ToolOption.CullingLimit, "88");

        verify(dummy).setCullingLimit(88);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-best_hit_overhang")
      void test50() {
        target.parseBlastConfig(dummy, ToolOption.BestHitOverhang, "0.666");

        verify(dummy).setBestHitOverhang(0.666);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-best_hit_score_edge")
      void test51() {
        target.parseBlastConfig(dummy, ToolOption.BestHitScoreEdge, "0.666");

        verify(dummy).setBestHitScoreEdge(0.666);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-subject_besthit")
      void test52() {
        target.parseBlastConfig(dummy, ToolOption.SubjectBestHit, "true");

        verify(dummy).enableSubjectBestHit(true);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-xdrop_gap")
      void test53() {
        target.parseBlastConfig(dummy, ToolOption.ExtensionDropoffPrelimGapped, "0.000333");

        verify(dummy).setExtensionDropoffPreliminaryGapped(0.000333);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-xdrop_gap_final")
      void test54() {
        target.parseBlastConfig(dummy, ToolOption.ExtensionDropoffFinalGapped, "0.000333");

        verify(dummy).setExtensionDropoffFinalGapped(0.000333);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-ungapped")
      void test55() {
        target.parseBlastConfig(dummy, ToolOption.UngappedAlignmentOnly, "true");

        verify(dummy).enableUngappedAlignmentOnly(true);
        verifyNoMoreInteractions(dummy);
      }

      @Test
      @DisplayName("-use_sw_tback")
      void test56() {
        target.parseBlastConfig(dummy, ToolOption.UseSmithWatermanAlignments, "true");

        verify(dummy).enableSmithWatermanTraceback(true);
        verifyNoMoreInteractions(dummy);
      }
    }
  }
}
