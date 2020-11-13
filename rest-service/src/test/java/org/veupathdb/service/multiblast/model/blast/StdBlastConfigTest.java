package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

import static org.junit.jupiter.api.Assertions.*;

class StdBlastConfigTest
{
  @Nested
  @DisplayName("#toArgs(CliBuilder)")
  class ToArgs
  {
    @Test
    @DisplayName("Returns nothing with no properties set.")
    void test1() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.toArgs(build);
      assertEquals("", build.toString());
    }

    @Test
    @DisplayName("Returns an argumentless help flag when help is set")
    void test2() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setHelpEnabled(true);
      target.toArgs(build);
      assertEquals("-help", build.toString());
    }

    @Test
    @DisplayName("Returns an argumentless version flag when version is set")
    void test3() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setVersionEnabled(true);
      target.toArgs(build);
      assertEquals("-version", build.toString());
    }

    @Test
    @DisplayName("Returns a db flag when dbName is set")
    void test4() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setBlastDatabase("foo");
      target.toArgs(build);
      assertEquals("-db='foo'", build.toString());
    }

    @Test
    @DisplayName("Returns a dbsize flag when dbSize is set")
    void test5() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setDbSize((byte) 8);
      target.toArgs(build);
      assertEquals("-dbsize='8'", build.toString());
    }

    @Test
    @DisplayName("Returns an entrez_query flag when entrezQuery is set")
    void test6() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setEntrezQuery("taco bell");
      target.toArgs(build);
      assertEquals("-entrez_query='taco bell'", build.toString());
    }

    @Test
    @DisplayName("Returns an evalue flag when expectValue is set")
    void test7() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setExpectValue(3.5);
      target.toArgs(build);
      assertEquals("-evalue='3.5'", build.toString());
    }

    @Test
    @DisplayName("Returns an export_search_strategy flag when exportSearchStrategy is set")
    void test8() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setExportSearchStrategy(new File("hi.txt"));
      target.toArgs(build);
      assertEquals("-export_search_strategy='hi.txt'", build.toString());
    }

    @Test
    @DisplayName("Returns an html flag when html is set")
    void test9() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setHtmlOutputEnabled(true);
      target.toArgs(build);
      assertEquals("-html", build.toString());
    }

    @Test
    @DisplayName("Returns an import_search_strategy flag when importSearchStrategy is set")
    void test10() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setImportSearchStrategy(new File("howdy.txt"));
      target.toArgs(build);
      assertEquals("-import_search_strategy='howdy.txt'", build.toString());
    }

    @Test
    @DisplayName("Returns a line_length flag when lineLength is set")
    void test11() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setLineLength(32);
      target.toArgs(build);
      assertEquals("-line_length='32'", build.toString());
    }

    @Test
    @DisplayName("Returns a lcase_masking flag when lowercaseMasking is set")
    void test12() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setLowercaseMaskingEnabled(true);
      target.toArgs(build);
      assertEquals("-lcase_masking", build.toString());
    }

    @Test
    @DisplayName("Returns a max_hsps flag when maxHSPs is set")
    void test13() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setMaxHSPs(42);
      target.toArgs(build);
      assertEquals("-max_hsps='42'", build.toString());
    }

    @Test
    @DisplayName("Returns a max_target_seqs flag when maxHSPs is set")
    void test14() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setMaxTargetSequences(42);
      target.toArgs(build);
      assertEquals("-max_target_seqs='42'", build.toString());
    }

    @Test
    @DisplayName("Returns a num_descriptions flag when numDescriptions is set")
    void test15() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setNumDescriptions(42);
      target.toArgs(build);
      assertEquals("-num_descriptions='42'", build.toString());
    }

    @Test
    @DisplayName("Returns a num_alignments flag when numAlignments is set")
    void test16() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setNumAlignments(42);
      target.toArgs(build);
      assertEquals("-num_alignments='42'", build.toString());
    }

    @Test
    @DisplayName("Returns a num_threads flag when numThreads is set")
    void test17() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setNumThreads((byte) 42);
      target.toArgs(build);
      assertEquals("-num_threads='42'", build.toString());
    }

    @Test
    @DisplayName("Returns an out flag when out is set")
    void test18() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setOut(new File("good.txt"));
      target.toArgs(build);
      assertEquals("-out='good.txt'", build.toString());
    }

    @Test
    @DisplayName("Returns a query flag when query is set")
    void test19() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setQuery(new File("file.file"));
      target.toArgs(build);
      assertEquals("-query='file.file'", build.toString());
    }

    @Test
    @DisplayName("Returns a query_loc flag when queryLoc is set")
    void test20() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setQueryLoc(new QueryLocation(1, 100));
      target.toArgs(build);
      assertEquals("-query_loc='1-100'", build.toString());
    }

    @Test
    @DisplayName("Returns a remote flag when remote is set")
    void test21() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setRemoteEnabled(true);
      target.toArgs(build);
      assertEquals("-remote", build.toString());
    }

    @Test
    @DisplayName("Returns a searchsp flag when searchSpace is set")
    void test22() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setSearchSpace((byte) 55);
      target.toArgs(build);
      assertEquals("-searchsp='55'", build.toString());
    }

    @Test
    @DisplayName("Returns a show_gis flag when showGIs is set")
    void test23() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setShowGIsEnabled(true);
      target.toArgs(build);
      assertEquals("-show_gis", build.toString());
    }

    @Test
    @DisplayName("Returns a soft_masking flag when softMasking is set")
    void test24() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setSoftMasking(true);
      target.toArgs(build);
      assertEquals("-soft_masking='true'", build.toString());
    }

    @Test
    @DisplayName("Returns a sorthits flag when sortHits is set")
    void test25() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setSortHits(HitSorting.BY_PERCENT_IDENTITY);
      target.toArgs(build);
      assertEquals("-sorthits='3'", build.toString());
    }

    @Test
    @DisplayName("Returns a sorthsps flag when sortHSPs is set")
    void test26() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setSortHsps(HspSorting.BY_SUBJECT_START);
      target.toArgs(build);
      assertEquals("-sorthsps='4'", build.toString());
    }

    @Test
    @DisplayName("Returns a window_size flag when windowSize is set")
    void test27() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setWindowSize(450);
      target.toArgs(build);
      assertEquals("-window_size='450'", build.toString());
    }

    @Test
    @DisplayName("Returns an xdrop_ungap flag when xDropUngap is set")
    void test28() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setXDropUngap(66.6);
      target.toArgs(build);
      assertEquals("-xdrop_ungap='66.6'", build.toString());
    }

    @Test
    @DisplayName("Returns an outfmt flag when outFormat is set")
    void test29() {
      var target = new StdBlastConfig();
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
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setQueryCoveragePercentHSP(55.5);
      target.toArgs(build);
      assertEquals("-qcov_hsp_perc='55.5'", build.toString());
    }

    @Test
    @DisplayName("Returns a parse_deflines flag when parseDefLines is set")
    void test31() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setParseDefLinesEnabled(true);
      target.toArgs(build);
      assertEquals("-parse_deflines", build.toString());
    }

    @Test
    @DisplayName("Returns a db_soft_mask flag when dbSoftMask is set")
    void test32() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setDbSoftMask("waka waka waka");
      target.toArgs(build);
      assertEquals("-db_soft_mask='waka waka waka'", build.toString());
    }

    @Test
    @DisplayName("Returns a db_hard_mask flag when dbHardMask is set")
    void test33() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setDbHardMask("orang strat");
      target.toArgs(build);
      assertEquals("-db_hard_mask='orang strat'", build.toString());
    }

    @Test
    @DisplayName("Returns a gilist flag when giList is set")
    void test34() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setGiList(new File("chickem.kfc"));
      target.toArgs(build);
      assertEquals("-gilist='chickem.kfc'", build.toString());
    }

    @Test
    @DisplayName("Returns a negative_gilist flag when negativeGiList is set")
    void test35() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setNegativeGiList(new File("square.burger"));
      target.toArgs(build);
      assertEquals("-negative_gilist='square.burger'", build.toString());
    }

    @Test
    @DisplayName("Returns a seqidlist flag when sequenceIdList is set")
    void test36() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setSequenceIdList(new File("corn.chips"));
      target.toArgs(build);
      assertEquals("-seqidlist='corn.chips'", build.toString());
    }


    @Test
    @DisplayName("Returns a negative_seqidlist flag when negativeSequenceIdList is set")
    void test36_2() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setNegativeSequenceIdList(new File("corn.chips"));
      target.toArgs(build);
      assertEquals("-negative_seqidlist='corn.chips'", build.toString());
    }

    @Test
    @DisplayName("Returns a taxidlist flag when taxIdList is set")
    void test37() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setTaxIdList(new File("smol.birb"));
      target.toArgs(build);
      assertEquals("-taxidlist='smol.birb'", build.toString());
    }

    @Test
    @DisplayName("Returns a negative_taxidlist flag when negativeTaxIdList is set")
    void test38() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setNegativeTaxIdList(new File("test.38"));
      target.toArgs(build);
      assertEquals("-negative_taxidlist='test.38'", build.toString());
    }

    @Test
    @DisplayName("Returns a subject flag when subject is set")
    void test39() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setSubject(new File("acab"));
      target.toArgs(build);
      assertEquals("-subject='acab'", build.toString());
    }

    @Test
    @DisplayName("Returns a subject_loc flag when subjectLocation is set")
    void test40() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setSubjectLocation(new QueryLocation(1, 250));
      target.toArgs(build);
      assertEquals("-subject_loc='1-250'", build.toString());
    }

    @Test
    @DisplayName("Returns a word_size flag when wordSize is set")
    void test41() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setWordSize(420);
      target.toArgs(build);
      assertEquals("-word_size='420'", build.toString());
    }

    @Test
    @DisplayName("Returns a taxids flag when taxIds is set")
    void test42() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setTaxIds(new String[]{"hello", "goodbye"});
      target.toArgs(build);
      assertEquals("-taxids='hello,goodbye'", build.toString());
    }

    @Test
    @DisplayName("Returns a negative_taxids flag when negativeTaxIds is set")
    void test43() {
      var target = new StdBlastConfig();
      var build  = new CliBuilder();

      target.setNegativeTaxIds(new String[]{"hello", "goodbye"});
      target.toArgs(build);
      assertEquals("-negative_taxids='hello,goodbye'", build.toString());
    }
  }

  @Nested
  @DisplayName("#toString()")
  class ToString
  {
    @Test
    @DisplayName("Returns nothing with no properties set.")
    void test1() {
      var target = new StdBlastConfig();

      assertEquals("", target.toString());
    }

    @Test
    @DisplayName("Returns an argumentless help flag when help is set")
    void test2() {
      var target = new StdBlastConfig();

      target.setHelpEnabled(true);
      assertEquals("-help", target.toString());
    }

    @Test
    @DisplayName("Returns an argumentless version flag when version is set")
    void test3() {
      var target = new StdBlastConfig();

      target.setVersionEnabled(true);
      assertEquals("-version", target.toString());
    }

    @Test
    @DisplayName("Returns a db flag when dbName is set")
    void test4() {
      var target = new StdBlastConfig();

      target.setBlastDatabase("foo");
      assertEquals("-db='foo'", target.toString());
    }

    @Test
    @DisplayName("Returns a dbsize flag when dbSize is set")
    void test5() {
      var target = new StdBlastConfig();

      target.setDbSize((byte) 8);
      assertEquals("-dbsize='8'", target.toString());
    }

    @Test
    @DisplayName("Returns an entrez_query flag when entrezQuery is set")
    void test6() {
      var target = new StdBlastConfig();

      target.setEntrezQuery("taco bell");
      assertEquals("-entrez_query='taco bell'", target.toString());
    }

    @Test
    @DisplayName("Returns an evalue flag when expectValue is set")
    void test7() {
      var target = new StdBlastConfig();

      target.setExpectValue(3.5);
      assertEquals("-evalue='3.5'", target.toString());
    }

    @Test
    @DisplayName("Returns an export_search_strategy flag when exportSearchStrategy is set")
    void test8() {
      var target = new StdBlastConfig();

      target.setExportSearchStrategy(new File("hi.txt"));
      assertEquals("-export_search_strategy='hi.txt'", target.toString());
    }

    @Test
    @DisplayName("Returns an html flag when html is set")
    void test9() {
      var target = new StdBlastConfig();

      target.setHtmlOutputEnabled(true);
      assertEquals("-html", target.toString());
    }

    @Test
    @DisplayName("Returns an import_search_strategy flag when importSearchStrategy is set")
    void test10() {
      var target = new StdBlastConfig();

      target.setImportSearchStrategy(new File("howdy.txt"));
      assertEquals("-import_search_strategy='howdy.txt'", target.toString());
    }

    @Test
    @DisplayName("Returns a line_length flag when lineLength is set")
    void test11() {
      var target = new StdBlastConfig();

      target.setLineLength(32);
      assertEquals("-line_length='32'", target.toString());
    }

    @Test
    @DisplayName("Returns a lcase_masking flag when lowercaseMasking is set")
    void test12() {
      var target = new StdBlastConfig();

      target.setLowercaseMaskingEnabled(true);
      assertEquals("-lcase_masking", target.toString());
    }

    @Test
    @DisplayName("Returns a max_hsps flag when maxHSPs is set")
    void test13() {
      var target = new StdBlastConfig();

      target.setMaxHSPs(42);
      assertEquals("-max_hsps='42'", target.toString());
    }

    @Test
    @DisplayName("Returns a max_target_seqs flag when maxHSPs is set")
    void test14() {
      var target = new StdBlastConfig();

      target.setMaxTargetSequences(42);
      assertEquals("-max_target_seqs='42'", target.toString());
    }

    @Test
    @DisplayName("Returns a num_descriptions flag when numDescriptions is set")
    void test15() {
      var target = new StdBlastConfig();

      target.setNumDescriptions(42);
      assertEquals("-num_descriptions='42'", target.toString());
    }

    @Test
    @DisplayName("Returns a num_alignments flag when numAlignments is set")
    void test16() {
      var target = new StdBlastConfig();

      target.setNumAlignments(42);
      assertEquals("-num_alignments='42'", target.toString());
    }

    @Test
    @DisplayName("Returns a num_threads flag when numThreads is set")
    void test17() {
      var target = new StdBlastConfig();

      target.setNumThreads((byte) 42);
      assertEquals("-num_threads='42'", target.toString());
    }

    @Test
    @DisplayName("Returns an out flag when out is set")
    void test18() {
      var target = new StdBlastConfig();

      target.setOut(new File("good.txt"));
      assertEquals("-out='good.txt'", target.toString());
    }

    @Test
    @DisplayName("Returns a query flag when query is set")
    void test19() {
      var target = new StdBlastConfig();

      target.setQuery(new File("file.file"));
      assertEquals("-query='file.file'", target.toString());
    }

    @Test
    @DisplayName("Returns a query_loc flag when queryLoc is set")
    void test20() {
      var target = new StdBlastConfig();

      target.setQueryLoc(new QueryLocation(1, 100));
      assertEquals("-query_loc='1-100'", target.toString());
    }

    @Test
    @DisplayName("Returns a remote flag when remote is set")
    void test21() {
      var target = new StdBlastConfig();

      target.setRemoteEnabled(true);
      assertEquals("-remote", target.toString());
    }

    @Test
    @DisplayName("Returns a searchsp flag when searchSpace is set")
    void test22() {
      var target = new StdBlastConfig();

      target.setSearchSpace((byte) 55);
      assertEquals("-searchsp='55'", target.toString());
    }

    @Test
    @DisplayName("Returns a show_gis flag when showGIs is set")
    void test23() {
      var target = new StdBlastConfig();

      target.setShowGIsEnabled(true);
      assertEquals("-show_gis", target.toString());
    }

    @Test
    @DisplayName("Returns a soft_masking flag when softMasking is set")
    void test24() {
      var target = new StdBlastConfig();

      target.setSoftMasking(true);
      assertEquals("-soft_masking='true'", target.toString());
    }

    @Test
    @DisplayName("Returns a sorthits flag when sortHits is set")
    void test25() {
      var target = new StdBlastConfig();

      target.setSortHits(HitSorting.BY_PERCENT_IDENTITY);
      assertEquals("-sorthits='3'", target.toString());
    }

    @Test
    @DisplayName("Returns a sorthsps flag when sortHSPs is set")
    void test26() {
      var target = new StdBlastConfig();

      target.setSortHsps(HspSorting.BY_SUBJECT_START);
      assertEquals("-sorthsps='4'", target.toString());
    }

    @Test
    @DisplayName("Returns a window_size flag when windowSize is set")
    void test27() {
      var target = new StdBlastConfig();

      target.setWindowSize(450);
      assertEquals("-window_size='450'", target.toString());
    }

    @Test
    @DisplayName("Returns an xdrop_ungap flag when xDropUngap is set")
    void test28() {
      var target = new StdBlastConfig();

      target.setXDropUngap(66.6);
      assertEquals("-xdrop_ungap='66.6'", target.toString());
    }

    @Test
    @DisplayName("Returns an outfmt flag when outFormat is set")
    void test29() {
      var target = new StdBlastConfig();
      var fmt    = new OutFormat();

      fmt.setFormat(ReportFormatType.ARCHIVE_ASN_1);

      target.setOutFormat(fmt);
      assertEquals("-outfmt='11'", target.toString());
    }

    @Test
    @DisplayName("Returns a qcov_hsp_perc flag when queryCoveragePerHSP is set")
    void test30() {
      var target = new StdBlastConfig();

      target.setQueryCoveragePercentHSP(55.5);
      assertEquals("-qcov_hsp_perc='55.5'", target.toString());
    }

    @Test
    @DisplayName("Returns a parse_deflines flag when parseDefLines is set")
    void test31() {
      var target = new StdBlastConfig();

      target.setParseDefLinesEnabled(true);
      assertEquals("-parse_deflines", target.toString());
    }

    @Test
    @DisplayName("Returns a db_soft_mask flag when dbSoftMask is set")
    void test32() {
      var target = new StdBlastConfig();

      target.setDbSoftMask("waka waka waka");
      assertEquals("-db_soft_mask='waka waka waka'", target.toString());
    }

    @Test
    @DisplayName("Returns a db_hard_mask flag when dbHardMask is set")
    void test33() {
      var target = new StdBlastConfig();

      target.setDbHardMask("orang strat");
      assertEquals("-db_hard_mask='orang strat'", target.toString());
    }

    @Test
    @DisplayName("Returns a gilist flag when giList is set")
    void test34() {
      var target = new StdBlastConfig();

      target.setGiList(new File("chickem.kfc"));
      assertEquals("-gilist='chickem.kfc'", target.toString());
    }

    @Test
    @DisplayName("Returns a negative_gilist flag when negativeGiList is set")
    void test35() {
      var target = new StdBlastConfig();

      target.setNegativeGiList(new File("square.burger"));
      assertEquals("-negative_gilist='square.burger'", target.toString());
    }

    @Test
    @DisplayName("Returns a seqidlist flag when sequenceIdList is set")
    void test36() {
      var target = new StdBlastConfig();

      target.setSequenceIdList(new File("corn.chips"));
      assertEquals("-seqidlist='corn.chips'", target.toString());
    }


    @Test
    @DisplayName("Returns a negative_seqidlist flag when negativeSequenceIdList is set")
    void test36_2() {
      var target = new StdBlastConfig();

      target.setNegativeSequenceIdList(new File("corn.chips"));
      assertEquals("-negative_seqidlist='corn.chips'", target.toString());
    }

    @Test
    @DisplayName("Returns a taxidlist flag when taxIdList is set")
    void test37() {
      var target = new StdBlastConfig();

      target.setTaxIdList(new File("smol.birb"));
      assertEquals("-taxidlist='smol.birb'", target.toString());
    }

    @Test
    @DisplayName("Returns a negative_taxidlist flag when negativeTaxIdList is set")
    void test38() {
      var target = new StdBlastConfig();

      target.setNegativeTaxIdList(new File("test.38"));
      assertEquals("-negative_taxidlist='test.38'", target.toString());
    }

    @Test
    @DisplayName("Returns a subject flag when subject is set")
    void test39() {
      var target = new StdBlastConfig();

      target.setSubject(new File("acab"));
      assertEquals("-subject='acab'", target.toString());
    }

    @Test
    @DisplayName("Returns a subject_loc flag when subjectLocation is set")
    void test40() {
      var target = new StdBlastConfig();

      target.setSubjectLocation(new QueryLocation(1, 250));
      assertEquals("-subject_loc='1-250'", target.toString());
    }

    @Test
    @DisplayName("Returns a word_size flag when wordSize is set")
    void test41() {
      var target = new StdBlastConfig();

      target.setWordSize(420);
      assertEquals("-word_size='420'", target.toString());
    }

    @Test
    @DisplayName("Returns a taxids flag when taxIds is set")
    void test42() {
      var target = new StdBlastConfig();

      target.setTaxIds(new String[]{"hello", "goodbye"});
      assertEquals("-taxids='hello,goodbye'", target.toString());
    }

    @Test
    @DisplayName("Returns a negative_taxids flag when negativeTaxIds is set")
    void test43() {
      var target = new StdBlastConfig();

      target.setNegativeTaxIds(new String[]{"hello", "goodbye"});
      assertEquals("-negative_taxids='hello,goodbye'", target.toString());
    }
  }
}
