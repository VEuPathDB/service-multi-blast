package org.veupathdb.service.multiblast.model.blast;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ReportFormatType")
class ReportFormatTypeTest
{
  @Nested
  @DisplayName("#getValue()")
  class GetValue
  {
    @Test
    @DisplayName("For Pairwise returns 0")
    void test1() {
      assertEquals(0, ReportFormatType.Pairwise.getValue());
    }

    @Test
    @DisplayName("For QueryAnchoredWithIdentities returns 1")
    void test2() {
      assertEquals(1, ReportFormatType.QueryAnchoredWithIdentities.getValue());
    }

    @Test
    @DisplayName("For QueryAnchoredWithoutIdentities returns 2")
    void test3() {
      assertEquals(2, ReportFormatType.QueryAnchoredWithoutIdentities.getValue());
    }

    @Test
    @DisplayName("For FlatQueryAnchoredWithIdentities returns 3")
    void test4() {
      assertEquals(3, ReportFormatType.FlatQueryAnchoredWithIdentities.getValue());
    }

    @Test
    @DisplayName("For FlatQueryAnchoredWithoutIdentities returns 4")
    void test5() {
      assertEquals(4, ReportFormatType.FlatQueryAnchoredWithoutIdentities.getValue());
    }

    @Test
    @DisplayName("For XML returns 5")
    void test6() {
      assertEquals(5, ReportFormatType.XML.getValue());
    }

    @Test
    @DisplayName("For Tabular returns 6")
    void test7() {
      assertEquals(6, ReportFormatType.Tabular.getValue());
    }

    @Test
    @DisplayName("For TabularWithComments returns 7")
    void test8() {
      assertEquals(7, ReportFormatType.TabularWithComments.getValue());
    }

    @Test
    @DisplayName("For TextASN1 returns 8")
    void test9() {
      assertEquals(8, ReportFormatType.TextASN1.getValue());
    }

    @Test
    @DisplayName("For BinaryASN1 returns 9")
    void test10() {
      assertEquals(9, ReportFormatType.BinaryASN1.getValue());
    }

    @Test
    @DisplayName("For CSV returns 10")
    void test11() {
      assertEquals(10, ReportFormatType.CSV.getValue());
    }

    @Test
    @DisplayName("For ArchiveASN1 returns 11")
    void test12() {
      assertEquals(11, ReportFormatType.ArchiveASN1.getValue());
    }

    @Test
    @DisplayName("For SeqAlignJSON returns 12")
    void test13() {
      assertEquals(12, ReportFormatType.SeqAlignJSON.getValue());
    }

    @Test
    @DisplayName("For MultiFileJSON returns 13")
    void test14() {
      assertEquals(13, ReportFormatType.MultiFileJSON.getValue());
    }

    @Test
    @DisplayName("For MultiFileXML2 returns 14")
    void test15() {
      assertEquals(14, ReportFormatType.MultiFileXML2.getValue());
    }

    @Test
    @DisplayName("For SingleFileJSON returns 15")
    void test16() {
      assertEquals(15, ReportFormatType.SingleFileJSON.getValue());
    }

    @Test
    @DisplayName("For SingleFileXML2 returns 16")
    void test17() {
      assertEquals(16, ReportFormatType.SingleFileXML2.getValue());
    }

    @Test
    @DisplayName("For SAM returns 17")
    void test18() {
      assertEquals(17, ReportFormatType.SAM.getValue());
    }

    @Test
    @DisplayName("For OrganismReport returns 18")
    void test19() {
      assertEquals(18, ReportFormatType.OrganismReport.getValue());
    }
  }

  @Nested
  @DisplayName("#toString()")
  class ToString
  {
    @Test
    @DisplayName("For Pairwise returns \"0\"")
    void test1() {
      assertEquals("0", ReportFormatType.Pairwise.toString());
    }

    @Test
    @DisplayName("For QueryAnchoredWithIdentities returns \"1\"")
    void test2() {
      assertEquals("1", ReportFormatType.QueryAnchoredWithIdentities.toString());
    }

    @Test
    @DisplayName("For QueryAnchoredWithoutIdentities returns \"2\"")
    void test3() {
      assertEquals("2", ReportFormatType.QueryAnchoredWithoutIdentities.toString());
    }

    @Test
    @DisplayName("For FlatQueryAnchoredWithIdentities returns \"3\"")
    void test4() {
      assertEquals("3", ReportFormatType.FlatQueryAnchoredWithIdentities.toString());
    }

    @Test
    @DisplayName("For FlatQueryAnchoredWithoutIdentities returns \"4\"")
    void test5() {
      assertEquals("4", ReportFormatType.FlatQueryAnchoredWithoutIdentities.toString());
    }

    @Test
    @DisplayName("For XML returns \"5\"")
    void test6() {
      assertEquals("5", ReportFormatType.XML.toString());
    }

    @Test
    @DisplayName("For Tabular returns \"6\"")
    void test7() {
      assertEquals("6", ReportFormatType.Tabular.toString());
    }

    @Test
    @DisplayName("For TabularWithComments returns \"7\"")
    void test8() {
      assertEquals("7", ReportFormatType.TabularWithComments.toString());
    }

    @Test
    @DisplayName("For TextASN1 returns \"8\"")
    void test9() {
      assertEquals("8", ReportFormatType.TextASN1.toString());
    }

    @Test
    @DisplayName("For BinaryASN1 returns \"9\"")
    void test10() {
      assertEquals("9", ReportFormatType.BinaryASN1.toString());
    }

    @Test
    @DisplayName("For CSV returns \"10\"")
    void test11() {
      assertEquals("10", ReportFormatType.CSV.toString());
    }

    @Test
    @DisplayName("For ArchiveASN1 returns \"11\"")
    void test12() {
      assertEquals("11", ReportFormatType.ArchiveASN1.toString());
    }

    @Test
    @DisplayName("For SeqAlignJSON returns \"12\"")
    void test13() {
      assertEquals("12", ReportFormatType.SeqAlignJSON.toString());
    }

    @Test
    @DisplayName("For MultiFileJSON returns \"13\"")
    void test14() {
      assertEquals("13", ReportFormatType.MultiFileJSON.toString());
    }

    @Test
    @DisplayName("For MultiFileXML2 returns \"14\"")
    void test15() {
      assertEquals("14", ReportFormatType.MultiFileXML2.toString());
    }

    @Test
    @DisplayName("For SingleFileJSON returns \"15\"")
    void test16() {
      assertEquals("15", ReportFormatType.SingleFileJSON.toString());
    }

    @Test
    @DisplayName("For SingleFileXML2 returns \"16\"")
    void test17() {
      assertEquals("16", ReportFormatType.SingleFileXML2.toString());
    }

    @Test
    @DisplayName("For SAM returns \"17\"")
    void test18() {
      assertEquals("17", ReportFormatType.SAM.toString());
    }

    @Test
    @DisplayName("For OrganismReport returns \"18\"")
    void test19() {
      assertEquals("18", ReportFormatType.OrganismReport.toString());
    }
  }

  @Nested
  @DisplayName("#fromInt(int)")
  class FromInt
  {
    @Test
    @DisplayName("For 0 returns Pairwise")
    void test1() {
      var opt = ReportFormatType.fromInt(0);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.Pairwise, opt.get());
    }

    @Test
    @DisplayName("For 1 returns QueryAnchoredWithIdentities")
    void test2() {
      var opt = ReportFormatType.fromInt(1);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.QueryAnchoredWithIdentities, opt.get());
    }

    @Test
    @DisplayName("For 2 returns QueryAnchoredWithoutIdentities")
    void test3() {
      var opt = ReportFormatType.fromInt(2);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.QueryAnchoredWithoutIdentities, opt.get());
    }

    @Test
    @DisplayName("For 3 returns FlatQueryAnchoredWithIdentities")
    void test4() {
      var opt = ReportFormatType.fromInt(3);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.FlatQueryAnchoredWithIdentities, opt.get());
    }

    @Test
    @DisplayName("For 4 returns FlatQueryAnchoredWithoutIdentities")
    void test5() {
      var opt = ReportFormatType.fromInt(4);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.FlatQueryAnchoredWithoutIdentities, opt.get());
    }

    @Test
    @DisplayName("For 5 returns XML")
    void test6() {
      var opt = ReportFormatType.fromInt(5);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.XML, opt.get());
    }

    @Test
    @DisplayName("For 6 returns Tabular")
    void test7() {
      var opt = ReportFormatType.fromInt(6);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.Tabular, opt.get());
    }

    @Test
    @DisplayName("For 7 returns TabularWithComments")
    void test8() {
      var opt = ReportFormatType.fromInt(7);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.TabularWithComments, opt.get());
    }

    @Test
    @DisplayName("For 8 returns TextASN1")
    void test9() {
      var opt = ReportFormatType.fromInt(8);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.TextASN1, opt.get());
    }

    @Test
    @DisplayName("For 9 returns BinaryASN1")
    void test10() {
      var opt = ReportFormatType.fromInt(9);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.BinaryASN1, opt.get());
    }

    @Test
    @DisplayName("For 10 returns CSV")
    void test11() {
      var opt = ReportFormatType.fromInt(10);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.CSV, opt.get());
    }

    @Test
    @DisplayName("For 11 returns ArchiveASN1")
    void test12() {
      var opt = ReportFormatType.fromInt(11);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.ArchiveASN1, opt.get());
    }

    @Test
    @DisplayName("For 12 returns SeqAlignJSON")
    void test13() {
      var opt = ReportFormatType.fromInt(12);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.SeqAlignJSON, opt.get());
    }

    @Test
    @DisplayName("For 13 returns MultiFileJSON")
    void test14() {
      var opt = ReportFormatType.fromInt(13);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.MultiFileJSON, opt.get());
    }

    @Test
    @DisplayName("For 14 returns MultiFileXML2")
    void test15() {
      var opt = ReportFormatType.fromInt(14);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.MultiFileXML2, opt.get());
    }

    @Test
    @DisplayName("For 15 returns SingleFileJSON")
    void test16() {
      var opt = ReportFormatType.fromInt(15);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.SingleFileJSON, opt.get());
    }

    @Test
    @DisplayName("For 16 returns SingleFileXML2")
    void test17() {
      var opt = ReportFormatType.fromInt(16);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.SingleFileXML2, opt.get());
    }

    @Test
    @DisplayName("For 17 returns SAM")
    void test18() {
      var opt = ReportFormatType.fromInt(17);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.SAM, opt.get());
    }

    @Test
    @DisplayName("For 18 returns OrganismReport")
    void test19() {
      var opt = ReportFormatType.fromInt(18);

      assertTrue(opt.isPresent());
      assertEquals(ReportFormatType.OrganismReport, opt.get());
    }
  }
}
