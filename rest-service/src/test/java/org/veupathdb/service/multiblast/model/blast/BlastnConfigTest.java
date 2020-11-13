package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

import static org.junit.jupiter.api.Assertions.*;

class BlastnConfigTest
{
  @Nested
  @DisplayName("#toArgs(CliBuilder)")
  class ToArgs
  {
    @Test
    @DisplayName("Returns nothing with no properties set.")
    void test1() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.toArgs(build);
      assertEquals("", build.toString());
    }

    @Test
    @DisplayName("Returns an argumentless help flag when help is set")
    void test2() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setHelp(true);
      target.toArgs(build);
      assertEquals("-help", build.toString());
    }

    @Test
    @DisplayName("Returns an argumentless version flag when version is set")
    void test3() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setVersion(true);
      target.toArgs(build);
      assertEquals("-version", build.toString());
    }

    @Test
    @DisplayName("Returns a db flag when dbName is set")
    void test4() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setDbName("foo");
      target.toArgs(build);
      assertEquals("-db='foo'", build.toString());
    }

    @Test
    @DisplayName("Returns a dbsize flag when dbSize is set")
    void test5() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setDbSize((byte) 8);
      target.toArgs(build);
      assertEquals("-dbsize='8'", build.toString());
    }

    @Test
    @DisplayName("Returns an entrez_query flag when entrezQuery is set")
    void test6() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setEntrezQuery("taco bell");
      target.toArgs(build);
      assertEquals("-entrez_query='taco bell'", build.toString());
    }

    @Test
    @DisplayName("Returns an evalue flag when expectValue is set")
    void test7() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setExpectValue(3.5);
      target.toArgs(build);
      assertEquals("-evalue='3.5'", build.toString());
    }

    @Test
    @DisplayName("Returns an export_search_strategy flag when exportSearchStrategy is set")
    void test8() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setExportSearchStrategy(new File("hi.txt"));
      target.toArgs(build);
      assertEquals("-export_search_strategy='hi.txt'", build.toString());
    }

    @Test
    @DisplayName("Returns an html flag when html is set")
    void test9() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setHtml(true);
      target.toArgs(build);
      assertEquals("-html", build.toString());
    }

    @Test
    @DisplayName("Returns an import_search_strategy flag when importSearchStrategy is set")
    void test10() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setImportSearchStrategy(new File("howdy.txt"));
      target.toArgs(build);
      assertEquals("-import_search_strategy='howdy.txt'", build.toString());
    }

    @Test
    @DisplayName("Returns a line_length flag when lineLength is set")
    void test11() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setLineLength(32);
      target.toArgs(build);
      assertEquals("-line_length='32'", build.toString());
    }

    @Test
    @DisplayName("Returns a lcase_masking flag when lowercaseMasking is set")
    void test12() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setLowercaseMasking(true);
      target.toArgs(build);
      assertEquals("-lcase_masking", build.toString());
    }

    @Test
    @DisplayName("Returns a max_hsps flag when maxHSPs is set")
    void test13() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setMaxHSPs(42);
      target.toArgs(build);
      assertEquals("-max_hsps='42'", build.toString());
    }

    @Test
    @DisplayName("Returns a max_target_seqs flag when maxHSPs is set")
    void test14() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setMaxTargetSequences(42);
      target.toArgs(build);
      assertEquals("-max_target_seqs='42'", build.toString());
    }

    @Test
    @DisplayName("Returns a num_descriptions flag when numDescriptions is set")
    void test15() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setNumDescriptions(42);
      target.toArgs(build);
      assertEquals("-num_descriptions='42'", build.toString());
    }

    @Test
    @DisplayName("Returns a num_alignments flag when numAlignments is set")
    void test16() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setNumAlignments(42);
      target.toArgs(build);
      assertEquals("-num_alignments='42'", build.toString());
    }

    @Test
    @DisplayName("Returns a num_threads flag when numThreads is set")
    void test17() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setNumThreads((byte) 42);
      target.toArgs(build);
      assertEquals("-num_threads='42'", build.toString());
    }

    @Test
    @DisplayName("Returns an out flag when out is set")
    void test18() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setOut(new File("good.txt"));
      target.toArgs(build);
      assertEquals("-out='good.txt'", build.toString());
    }

    @Test
    @DisplayName("Returns a query flag when query is set")
    void test19() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setQuery(new File("file.file"));
      target.toArgs(build);
      assertEquals("-query='file.file'", build.toString());
    }

    @Test
    @DisplayName("Returns a query_loc flag when queryLoc is set")
    void test20() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setQueryLoc(new QueryLocation(1, 100));
      target.toArgs(build);
      assertEquals("-query_loc='1-100'", build.toString());
    }

    @Test
    @DisplayName("Returns a remote flag when remote is set")
    void test21() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setRemote(true);
      target.toArgs(build);
      assertEquals("-remote", build.toString());
    }

    @Test
    @DisplayName("Returns a searchsp flag when searchSpace is set")
    void test22() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setSearchSpace(55);
      target.toArgs(build);
      assertEquals("-searchsp='55'", build.toString());
    }

    @Test
    @DisplayName("Returns a show_gis flag when showGIs is set")
    void test23() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setShowGIs(true);
      target.toArgs(build);
      assertEquals("-show_gis", build.toString());
    }

    @Test
    @DisplayName("Returns a soft_masking flag when softMasking is set")
    void test24() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setSoftMasking(true);
      target.toArgs(build);
      assertEquals("-soft_masking='true'", build.toString());
    }

    @Test
    @DisplayName("Returns a sorthits flag when sortHits is set")
    void test25() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setSortHits(HitSorting.BY_PERCENT_IDENTITY);
      target.toArgs(build);
      assertEquals("-sorthits='3'", build.toString());
    }

    @Test
    @DisplayName("Returns a sorthsps flag when sortHSPs is set")
    void test26() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setSortHsps(HspSorting.BY_SUBJECT_START);
      target.toArgs(build);
      assertEquals("-sorthsps='4'", build.toString());
    }

    @Test
    @DisplayName("Returns a window_size flag when windowSize is set")
    void test27() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setWindowSize(450);
      target.toArgs(build);
      assertEquals("-window_size='450'", build.toString());
    }

    @Test
    @DisplayName("Returns an xdrop_ungap flag when xDropUngap is set")
    void test28() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setXDropUngap(66.6);
      target.toArgs(build);
      assertEquals("-xdrop_ungap='66.6'", build.toString());
    }

    @Test
    @DisplayName("Returns an outfmt flag when outFormat is set")
    void test29() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();
      var fmt    = new OutFormat();

      fmt.setFormat(ReportFormatType.ARCHIVE_ASN_1);

      target.setOutFormat(fmt);
      target.toArgs(build);
      assertEquals("-outfmt='11'", build.toString());
    }

    @Test
    @DisplayName("Returns a qcov_hsp_perc flag when queryCoveragePerHSP is set")
    void test30() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setQueryCoveragePerHSP(55.5);
      target.toArgs(build);
      assertEquals("-qcov_hsp_perc='55.5'", build.toString());
    }

    @Test
    @DisplayName("Returns a parse_deflines flag when parseDefLines is set")
    void test31() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setParseDefLines(true);
      target.toArgs(build);
      assertEquals("-parse_deflines", build.toString());
    }

    @Test
    @DisplayName("Returns a db_soft_mask flag when dbSoftMask is set")
    void test32() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setDbSoftMask("waka waka waka");
      target.toArgs(build);
      assertEquals("-db_soft_mask='waka waka waka'", build.toString());
    }

    @Test
    @DisplayName("Returns a db_hard_mask flag when dbHardMask is set")
    void test33() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setDbHardMask("orang strat");
      target.toArgs(build);
      assertEquals("-db_hard_mask='orang strat'", build.toString());
    }

    @Test
    @DisplayName("Returns a gilist flag when giList is set")
    void test34() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setGiList(new File("chickem.kfc"));
      target.toArgs(build);
      assertEquals("-gilist='chickem.kfc'", build.toString());
    }

    @Test
    @DisplayName("Returns a negative_gilist flag when negativeGiList is set")
    void test35() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setNegativeGiList(new File("square.burger"));
      target.toArgs(build);
      assertEquals("-negative_gilist='square.burger'", build.toString());
    }

    @Test
    @DisplayName("Returns a seqidlist flag when sequenceIdList is set")
    void test36() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setSequenceIdList(new File("corn.chips"));
      target.toArgs(build);
      assertEquals("-seqidlist='corn.chips'", build.toString());
    }


    @Test
    @DisplayName("Returns a negative_seqidlist flag when negativeSequenceIdList is set")
    void test36_2() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setNegativeSequenceIdList(new File("corn.chips"));
      target.toArgs(build);
      assertEquals("-negative_seqidlist='corn.chips'", build.toString());
    }

    @Test
    @DisplayName("Returns a taxidlist flag when taxIdList is set")
    void test37() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setTaxIdList(new File("smol.birb"));
      target.toArgs(build);
      assertEquals("-taxidlist='smol.birb'", build.toString());
    }

    @Test
    @DisplayName("Returns a negative_taxidlist flag when negativeTaxIdList is set")
    void test38() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setNegativeTaxIdList(new File("test.38"));
      target.toArgs(build);
      assertEquals("-negative_taxidlist='test.38'", build.toString());
    }

    @Test
    @DisplayName("Returns a subject flag when subject is set")
    void test39() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setSubject(new File("acab"));
      target.toArgs(build);
      assertEquals("-subject='acab'", build.toString());
    }

    @Test
    @DisplayName("Returns a subject_loc flag when subjectLocation is set")
    void test40() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setSubjectLocation(new QueryLocation(1, 250));
      target.toArgs(build);
      assertEquals("-subject_loc='1-250'", build.toString());
    }

    @Test
    @DisplayName("Returns a word_size flag when wordSize is set")
    void test41() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setWordSize(420);
      target.toArgs(build);
      assertEquals("-word_size='420'", build.toString());
    }

    @Test
    @DisplayName("Returns a taxids flag when taxIds is set")
    void test42() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setTaxIds(new String[]{"hello", "goodbye"});
      target.toArgs(build);
      assertEquals("-taxids='hello,goodbye'", build.toString());
    }

    @Test
    @DisplayName("Returns a negative_taxids flag when negativeTaxIds is set")
    void test43() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setNegativeTaxIds(new String[]{"hello", "goodbye"});
      target.toArgs(build);
      assertEquals("-negative_taxids='hello,goodbye'", build.toString());
    }

    @Test
    @DisplayName("Returns a strand flag when strand is set")
    void test44() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setStrand(QueryStrand.MINUS);
      target.toArgs(build);
      assertEquals("-strand='minus'", build.toString());
    }

    @Test
    @DisplayName("Returns a task flag when task is set")
    void test45() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setTask(BlastnTask.DC_MEGABLAST);
      target.toArgs(build);
      assertEquals("-task='dc-megablast'", build.toString());
    }

    @Test
    @DisplayName("Returns a gapopen flag when gapOpen is set")
    void test46() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setGapOpen(33);
      target.toArgs(build);
      assertEquals("-gapopen='33'", build.toString());
    }

    @Test
    @DisplayName("Returns a gapextend flag when gapExtend is set")
    void test47() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setGapExtend(77);
      target.toArgs(build);
      assertEquals("-gapextend='77'", build.toString());
    }

    @Test
    @DisplayName("Returns a penalty flag when penalty is set")
    void test48() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setPenalty(77);
      target.toArgs(build);
      assertEquals("-penalty='77'", build.toString());
    }

    @Test
    @DisplayName("Returns a reward flag when reward is set")
    void test49() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setReward(77);
      target.toArgs(build);
      assertEquals("-reward='77'", build.toString());
    }

    @Test
    @DisplayName("Returns a use_index flag when useIndex is set")
    void test50() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setUseIndex(true);
      target.toArgs(build);
      assertEquals("-use_index='true'", build.toString());
    }

    @Test
    @DisplayName("Returns a index_name flag when indexName is set")
    void test51() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setIndexName("potato");
      target.toArgs(build);
      assertEquals("-index_name='potato'", build.toString());
    }

    @Test
    @DisplayName("Returns a dust flag when dust is set")
    void test52() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setDust(new Dust(10, 12, 13));
      target.toArgs(build);
      assertEquals("-dust='10 12 13'", build.toString());
    }

    @Test
    @DisplayName("Returns a filtering_db flag when filteringDb is set")
    void test53() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setFilteringDb(new File("yes.no"));
      target.toArgs(build);
      assertEquals("-filtering_db='yes.no'", build.toString());
    }

    @Test
    @DisplayName("Returns a window_masker_taxid flag when windowMaskerTaxId is set")
    void test54() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setWindowMaskerTaxID(12345);
      target.toArgs(build);
      assertEquals("-window_masker_taxid='12345'", build.toString());
    }

    @Test
    @DisplayName("Returns a perc_identity flag when percIdentity is set")
    void test55() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setPercIdentity(12.5);
      target.toArgs(build);
      assertEquals("-perc_identity='12.5'", build.toString());
    }

    @Test
    @DisplayName("Returns a culling_limit flag when cullingLimit is set")
    void test56() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setCullingLimit(12);
      target.toArgs(build);
      assertEquals("-culling_limit='12'", build.toString());
    }

    @Test
    @DisplayName("Returns a best_hit_overhang flag when bestHitOverhang is set")
    void test57() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setBestHitOverhang(12.5);
      target.toArgs(build);
      assertEquals("-best_hit_overhang='12.5'", build.toString());
    }

    @Test
    @DisplayName("Returns a best_hit_score_edge flag when bestHitScoreEdge is set")
    void test58() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setBestHitScoreEdge(12.5);
      target.toArgs(build);
      assertEquals("-best_hit_score_edge='12.5'", build.toString());
    }

    @Test
    @DisplayName("Returns a subject_besthit flag when subjectBestHit is set")
    void test59() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setSubjectBestHit(true);
      target.toArgs(build);
      assertEquals("-subject_besthit", build.toString());
    }

    @Test
    @DisplayName("Returns a template_type flag when templateType is set")
    void test60() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setTemplateType(TemplateType.Optimal);
      target.toArgs(build);
      assertEquals("-template_type='optimal'", build.toString());
    }

    @Test
    @DisplayName("Returns a template_length flag when templateLength is set")
    void test61() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setTemplateLength(16);
      target.toArgs(build);
      assertEquals("-template_length='16'", build.toString());
    }

    @Test
    @DisplayName("Returns a template_length flag when templateLength is set")
    void test62() {
      var target = new BlastnConfig();
      var build  = new CliBuilder();

      target.setSumStats(true);
      target.toArgs(build);
      assertEquals("-sum_stats='true'", build.toString());

      build  = new CliBuilder();

      target.setSumStats(false);
      target.toArgs(build);
      assertEquals("-sum_stats='false'", build.toString());
    }
  }

  @Nested
  @DisplayName("#toString()")
  class ToString
  {
    @Test
    @DisplayName("Returns nothing with no properties set.")
    void test1() {
      var target = new BlastnConfig();

      assertEquals("", target.toString());
    }

    @Test
    @DisplayName("Returns an argumentless help flag when help is set")
    void test2() {
      var target = new BlastnConfig();

      target.setHelp(true);
      assertEquals("-help", target.toString());
    }

    @Test
    @DisplayName("Returns an argumentless version flag when version is set")
    void test3() {
      var target = new BlastnConfig();

      target.setVersion(true);
      assertEquals("-version", target.toString());
    }

    @Test
    @DisplayName("Returns a db flag when dbName is set")
    void test4() {
      var target = new BlastnConfig();

      target.setDbName("foo");
      assertEquals("-db='foo'", target.toString());
    }

    @Test
    @DisplayName("Returns a dbsize flag when dbSize is set")
    void test5() {
      var target = new BlastnConfig();

      target.setDbSize((byte) 8);
      assertEquals("-dbsize='8'", target.toString());
    }

    @Test
    @DisplayName("Returns an entrez_query flag when entrezQuery is set")
    void test6() {
      var target = new BlastnConfig();

      target.setEntrezQuery("taco bell");
      assertEquals("-entrez_query='taco bell'", target.toString());
    }

    @Test
    @DisplayName("Returns an evalue flag when expectValue is set")
    void test7() {
      var target = new BlastnConfig();

      target.setExpectValue(3.5);
      assertEquals("-evalue='3.5'", target.toString());
    }

    @Test
    @DisplayName("Returns an export_search_strategy flag when exportSearchStrategy is set")
    void test8() {
      var target = new BlastnConfig();

      target.setExportSearchStrategy(new File("hi.txt"));
      assertEquals("-export_search_strategy='hi.txt'", target.toString());
    }

    @Test
    @DisplayName("Returns an html flag when html is set")
    void test9() {
      var target = new BlastnConfig();

      target.setHtml(true);
      assertEquals("-html", target.toString());
    }

    @Test
    @DisplayName("Returns an import_search_strategy flag when importSearchStrategy is set")
    void test10() {
      var target = new BlastnConfig();

      target.setImportSearchStrategy(new File("howdy.txt"));
      assertEquals("-import_search_strategy='howdy.txt'", target.toString());
    }

    @Test
    @DisplayName("Returns a line_length flag when lineLength is set")
    void test11() {
      var target = new BlastnConfig();

      target.setLineLength(32);
      assertEquals("-line_length='32'", target.toString());
    }

    @Test
    @DisplayName("Returns a lcase_masking flag when lowercaseMasking is set")
    void test12() {
      var target = new BlastnConfig();

      target.setLowercaseMasking(true);
      assertEquals("-lcase_masking", target.toString());
    }

    @Test
    @DisplayName("Returns a max_hsps flag when maxHSPs is set")
    void test13() {
      var target = new BlastnConfig();

      target.setMaxHSPs(42);
      assertEquals("-max_hsps='42'", target.toString());
    }

    @Test
    @DisplayName("Returns a max_target_seqs flag when maxHSPs is set")
    void test14() {
      var target = new BlastnConfig();

      target.setMaxTargetSequences(42);
      assertEquals("-max_target_seqs='42'", target.toString());
    }

    @Test
    @DisplayName("Returns a num_descriptions flag when numDescriptions is set")
    void test15() {
      var target = new BlastnConfig();

      target.setNumDescriptions(42);
      assertEquals("-num_descriptions='42'", target.toString());
    }

    @Test
    @DisplayName("Returns a num_alignments flag when numAlignments is set")
    void test16() {
      var target = new BlastnConfig();

      target.setNumAlignments(42);
      assertEquals("-num_alignments='42'", target.toString());
    }

    @Test
    @DisplayName("Returns a num_threads flag when numThreads is set")
    void test17() {
      var target = new BlastnConfig();

      target.setNumThreads((byte) 42);
      assertEquals("-num_threads='42'", target.toString());
    }

    @Test
    @DisplayName("Returns an out flag when out is set")
    void test18() {
      var target = new BlastnConfig();

      target.setOut(new File("good.txt"));
      assertEquals("-out='good.txt'", target.toString());
    }

    @Test
    @DisplayName("Returns a query flag when query is set")
    void test19() {
      var target = new BlastnConfig();

      target.setQuery(new File("file.file"));
      assertEquals("-query='file.file'", target.toString());
    }

    @Test
    @DisplayName("Returns a query_loc flag when queryLoc is set")
    void test20() {
      var target = new BlastnConfig();

      target.setQueryLoc(new QueryLocation(1, 100));
      assertEquals("-query_loc='1-100'", target.toString());
    }

    @Test
    @DisplayName("Returns a remote flag when remote is set")
    void test21() {
      var target = new BlastnConfig();

      target.setRemote(true);
      assertEquals("-remote", target.toString());
    }

    @Test
    @DisplayName("Returns a searchsp flag when searchSpace is set")
    void test22() {
      var target = new BlastnConfig();

      target.setSearchSpace(55);
      assertEquals("-searchsp='55'", target.toString());
    }

    @Test
    @DisplayName("Returns a show_gis flag when showGIs is set")
    void test23() {
      var target = new BlastnConfig();

      target.setShowGIs(true);
      assertEquals("-show_gis", target.toString());
    }

    @Test
    @DisplayName("Returns a soft_masking flag when softMasking is set")
    void test24() {
      var target = new BlastnConfig();

      target.setSoftMasking(true);
      assertEquals("-soft_masking='true'", target.toString());
    }

    @Test
    @DisplayName("Returns a sorthits flag when sortHits is set")
    void test25() {
      var target = new BlastnConfig();

      target.setSortHits(HitSorting.BY_PERCENT_IDENTITY);
      assertEquals("-sorthits='3'", target.toString());
    }

    @Test
    @DisplayName("Returns a sorthsps flag when sortHSPs is set")
    void test26() {
      var target = new BlastnConfig();

      target.setSortHsps(HspSorting.BY_SUBJECT_START);
      assertEquals("-sorthsps='4'", target.toString());
    }

    @Test
    @DisplayName("Returns a window_size flag when windowSize is set")
    void test27() {
      var target = new BlastnConfig();

      target.setWindowSize(450);
      assertEquals("-window_size='450'", target.toString());
    }

    @Test
    @DisplayName("Returns an xdrop_ungap flag when xDropUngap is set")
    void test28() {
      var target = new BlastnConfig();

      target.setXDropUngap(66.6);
      assertEquals("-xdrop_ungap='66.6'", target.toString());
    }

    @Test
    @DisplayName("Returns an outfmt flag when outFormat is set")
    void test29() {
      var target = new BlastnConfig();
      var fmt    = new OutFormat();

      fmt.setFormat(ReportFormatType.ARCHIVE_ASN_1);

      target.setOutFormat(fmt);
      assertEquals("-outfmt='11'", target.toString());
    }

    @Test
    @DisplayName("Returns a qcov_hsp_perc flag when queryCoveragePerHSP is set")
    void test30() {
      var target = new BlastnConfig();

      target.setQueryCoveragePerHSP(55.5);
      assertEquals("-qcov_hsp_perc='55.5'", target.toString());
    }

    @Test
    @DisplayName("Returns a parse_deflines flag when parseDefLines is set")
    void test31() {
      var target = new BlastnConfig();

      target.setParseDefLines(true);
      assertEquals("-parse_deflines", target.toString());
    }

    @Test
    @DisplayName("Returns a db_soft_mask flag when dbSoftMask is set")
    void test32() {
      var target = new BlastnConfig();

      target.setDbSoftMask("waka waka waka");
      assertEquals("-db_soft_mask='waka waka waka'", target.toString());
    }

    @Test
    @DisplayName("Returns a db_hard_mask flag when dbHardMask is set")
    void test33() {
      var target = new BlastnConfig();

      target.setDbHardMask("orang strat");
      assertEquals("-db_hard_mask='orang strat'", target.toString());
    }

    @Test
    @DisplayName("Returns a gilist flag when giList is set")
    void test34() {
      var target = new BlastnConfig();

      target.setGiList(new File("chickem.kfc"));
      assertEquals("-gilist='chickem.kfc'", target.toString());
    }

    @Test
    @DisplayName("Returns a negative_gilist flag when negativeGiList is set")
    void test35() {
      var target = new BlastnConfig();

      target.setNegativeGiList(new File("square.burger"));
      assertEquals("-negative_gilist='square.burger'", target.toString());
    }

    @Test
    @DisplayName("Returns a seqidlist flag when sequenceIdList is set")
    void test36() {
      var target = new BlastnConfig();

      target.setSequenceIdList(new File("corn.chips"));
      assertEquals("-seqidlist='corn.chips'", target.toString());
    }


    @Test
    @DisplayName("Returns a negative_seqidlist flag when negativeSequenceIdList is set")
    void test36_2() {
      var target = new BlastnConfig();

      target.setNegativeSequenceIdList(new File("corn.chips"));
      assertEquals("-negative_seqidlist='corn.chips'", target.toString());
    }

    @Test
    @DisplayName("Returns a taxidlist flag when taxIdList is set")
    void test37() {
      var target = new BlastnConfig();

      target.setTaxIdList(new File("smol.birb"));
      assertEquals("-taxidlist='smol.birb'", target.toString());
    }

    @Test
    @DisplayName("Returns a negative_taxidlist flag when negativeTaxIdList is set")
    void test38() {
      var target = new BlastnConfig();

      target.setNegativeTaxIdList(new File("test.38"));
      assertEquals("-negative_taxidlist='test.38'", target.toString());
    }

    @Test
    @DisplayName("Returns a subject flag when subject is set")
    void test39() {
      var target = new BlastnConfig();

      target.setSubject(new File("acab"));
      assertEquals("-subject='acab'", target.toString());
    }

    @Test
    @DisplayName("Returns a subject_loc flag when subjectLocation is set")
    void test40() {
      var target = new BlastnConfig();

      target.setSubjectLocation(new QueryLocation(1, 250));
      assertEquals("-subject_loc='1-250'", target.toString());
    }

    @Test
    @DisplayName("Returns a word_size flag when wordSize is set")
    void test41() {
      var target = new BlastnConfig();

      target.setWordSize(420);
      assertEquals("-word_size='420'", target.toString());
    }

    @Test
    @DisplayName("Returns a taxids flag when taxIds is set")
    void test42() {
      var target = new BlastnConfig();

      target.setTaxIds(new String[]{"hello", "goodbye"});
      assertEquals("-taxids='hello,goodbye'", target.toString());
    }

    @Test
    @DisplayName("Returns a negative_taxids flag when negativeTaxIds is set")
    void test43() {
      var target = new BlastnConfig();

      target.setNegativeTaxIds(new String[]{"hello", "goodbye"});
      assertEquals("-negative_taxids='hello,goodbye'", target.toString());
    }

    @Test
    @DisplayName("Returns a strand flag when strand is set")
    void test44() {
      var target = new BlastnConfig();

      target.setStrand(QueryStrand.MINUS);
      assertEquals("-strand='minus'", target.toString());
    }

    @Test
    @DisplayName("Returns a task flag when task is set")
    void test45() {
      var target = new BlastnConfig();

      target.setTask(BlastnTask.DC_MEGABLAST);
      assertEquals("-task='dc-megablast'", target.toString());
    }

    @Test
    @DisplayName("Returns a gapopen flag when gapOpen is set")
    void test46() {
      var target = new BlastnConfig();

      target.setGapOpen(33);
      assertEquals("-gapopen='33'", target.toString());
    }

    @Test
    @DisplayName("Returns a gapextend flag when gapExtend is set")
    void test47() {
      var target = new BlastnConfig();

      target.setGapExtend(77);
      assertEquals("-gapextend='77'", target.toString());
    }

    @Test
    @DisplayName("Returns a penalty flag when penalty is set")
    void test48() {
      var target = new BlastnConfig();

      target.setPenalty(77);
      assertEquals("-penalty='77'", target.toString());
    }

    @Test
    @DisplayName("Returns a reward flag when reward is set")
    void test49() {
      var target = new BlastnConfig();

      target.setReward(77);
      assertEquals("-reward='77'", target.toString());
    }

    @Test
    @DisplayName("Returns a use_index flag when useIndex is set")
    void test50() {
      var target = new BlastnConfig();

      target.setUseIndex(true);
      assertEquals("-use_index='true'", target.toString());
    }

    @Test
    @DisplayName("Returns a index_name flag when indexName is set")
    void test51() {
      var target = new BlastnConfig();

      target.setIndexName("potato");
      assertEquals("-index_name='potato'", target.toString());
    }

    @Test
    @DisplayName("Returns a dust flag when dust is set")
    void test52() {
      var target = new BlastnConfig();

      target.setDust(new Dust(10, 12, 13));
      assertEquals("-dust='10 12 13'", target.toString());
    }

    @Test
    @DisplayName("Returns a filtering_db flag when filteringDb is set")
    void test53() {
      var target = new BlastnConfig();

      target.setFilteringDb(new File("yes.no"));
      assertEquals("-filtering_db='yes.no'", target.toString());
    }

    @Test
    @DisplayName("Returns a window_masker_taxid flag when windowMaskerTaxId is set")
    void test54() {
      var target = new BlastnConfig();

      target.setWindowMaskerTaxID(12345);
      assertEquals("-window_masker_taxid='12345'", target.toString());
    }

    @Test
    @DisplayName("Returns a perc_identity flag when percIdentity is set")
    void test55() {
      var target = new BlastnConfig();

      target.setPercIdentity(12.5);
      assertEquals("-perc_identity='12.5'", target.toString());
    }

    @Test
    @DisplayName("Returns a culling_limit flag when cullingLimit is set")
    void test56() {
      var target = new BlastnConfig();

      target.setCullingLimit(12);
      assertEquals("-culling_limit='12'", target.toString());
    }

    @Test
    @DisplayName("Returns a best_hit_overhang flag when bestHitOverhang is set")
    void test57() {
      var target = new BlastnConfig();

      target.setBestHitOverhang(12.5);
      assertEquals("-best_hit_overhang='12.5'", target.toString());
    }

    @Test
    @DisplayName("Returns a best_hit_score_edge flag when bestHitScoreEdge is set")
    void test58() {
      var target = new BlastnConfig();

      target.setBestHitScoreEdge(12.5);
      assertEquals("-best_hit_score_edge='12.5'", target.toString());
    }

    @Test
    @DisplayName("Returns a subject_besthit flag when subjectBestHit is set")
    void test59() {
      var target = new BlastnConfig();

      target.setSubjectBestHit(true);
      assertEquals("-subject_besthit", target.toString());
    }

    @Test
    @DisplayName("Returns a template_type flag when templateType is set")
    void test60() {
      var target = new BlastnConfig();

      target.setTemplateType(TemplateType.Optimal);
      assertEquals("-template_type='optimal'", target.toString());
    }

    @Test
    @DisplayName("Returns a template_length flag when templateLength is set")
    void test61() {
      var target = new BlastnConfig();

      target.setTemplateLength(16);
      assertEquals("-template_length='16'", target.toString());
    }

    @Test
    @DisplayName("Returns a template_length flag when templateLength is set")
    void test62() {
      var target = new BlastnConfig();

      target.setSumStats(true);
      assertEquals("-sum_stats='true'", target.toString());

      target.setSumStats(false);
      assertEquals("-sum_stats='false'", target.toString());
    }
  }

}
