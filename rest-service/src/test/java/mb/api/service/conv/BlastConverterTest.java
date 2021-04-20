package mb.api.service.conv;

import mb.api.model.blast.IOBlastFormat;
import mb.api.model.blast.IOHSPSorting;
import mb.lib.blast.model.BlastReportField;
import mb.lib.blast.model.BlastReportFormat;
import mb.lib.blast.model.BlastReportType;
import mb.lib.blast.model.HSPSorting;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import mb.lib.blast.model.impl.ReportFormatImpl;

@DisplayName("BlastConverter")
class BlastConverterTest
{
  @Nested
  @DisplayName("::toExternal(BlastReportType)")
  class ToExternal1
  {
    @Test
    @DisplayName("Correctly converts BlastReportType value.")
    void test1() {
      Assertions.assertEquals(IOBlastFormat.PAIRWISE, BlastConverter.toExternal(BlastReportType.Pairwise));
      Assertions.assertEquals(IOBlastFormat.QUERYANCHOREDWITHIDENTITIES, BlastConverter.toExternal(
        BlastReportType.QueryAnchoredWithIdentities));
      Assertions.assertEquals(IOBlastFormat.QUERYANCHOREDWITHOUTIDENTITIES, BlastConverter.toExternal(
        BlastReportType.QueryAnchoredWithoutIdentities));
      Assertions.assertEquals(IOBlastFormat.FLATQUERYANCHOREDWITHIDENTITIES, BlastConverter.toExternal(
        BlastReportType.FlatQueryAnchoredWithIdentities));
      Assertions.assertEquals(IOBlastFormat.FLATQUERYANCHOREDWITHOUTIDENTITIES, BlastConverter.toExternal(
        BlastReportType.FlatQueryAnchoredWithoutIdentities));
      Assertions.assertEquals(IOBlastFormat.XML, BlastConverter.toExternal(BlastReportType.XML));
      Assertions.assertEquals(IOBlastFormat.TABULAR, BlastConverter.toExternal(BlastReportType.Tabular));
      Assertions.assertEquals(IOBlastFormat.TABULARWITHCOMMENTS, BlastConverter.toExternal(
        BlastReportType.TabularWithComments));
      Assertions.assertEquals(IOBlastFormat.TEXTASN_1, BlastConverter.toExternal(BlastReportType.TextASN1));
      Assertions.assertEquals(IOBlastFormat.BINARYASN_1, BlastConverter.toExternal(BlastReportType.BinaryASN1));
      Assertions.assertEquals(IOBlastFormat.CSV, BlastConverter.toExternal(BlastReportType.CSV));
      Assertions.assertEquals(IOBlastFormat.ARCHIVEASN_1, BlastConverter.toExternal(BlastReportType.ArchiveASN1));
      Assertions.assertEquals(IOBlastFormat.SEQALIGNJSON, BlastConverter.toExternal(BlastReportType.SeqAlignJSON));
      Assertions.assertEquals(IOBlastFormat.MULTIFILEJSON, BlastConverter.toExternal(
        BlastReportType.MultiFileJSON));
      Assertions.assertEquals(IOBlastFormat.MULTIFILEXML2, BlastConverter.toExternal(
        BlastReportType.MultiFileXML2));
      Assertions.assertEquals(IOBlastFormat.SINGLEFILEJSON, BlastConverter.toExternal(
        BlastReportType.SingleFileJSON));
      Assertions.assertEquals(IOBlastFormat.SINGLEFILEXML2, BlastConverter.toExternal(
        BlastReportType.SingleFileXML2));
      Assertions.assertEquals(IOBlastFormat.SAM, BlastConverter.toExternal(BlastReportType.SAM));
      Assertions.assertEquals(IOBlastFormat.ORGANISMREPORT, BlastConverter.toExternal(
        BlastReportType.OrganismReport));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(BlastConverter.toExternal((BlastReportType) null));
    }
  }

  @Nested
  @DisplayName("::toExternal(HspSorting)")
  class ToExternal4
  {
    @Test
    @DisplayName("Correctly converts HspSorting values.")
    void test1() {
      Assertions.assertEquals(IOHSPSorting.BYHSPEVALUE, BlastConverter.toExternal(HSPSorting.ByExpectValue));
      Assertions.assertEquals(IOHSPSorting.BYHSPSCORE, BlastConverter.toExternal(HSPSorting.ByScore));
      Assertions.assertEquals(IOHSPSorting.BYHSPQUERYSTART, BlastConverter.toExternal(HSPSorting.ByQueryStart));
      Assertions.assertEquals(IOHSPSorting.BYHSPPERCENTIDENTITY, BlastConverter.toExternal(
        HSPSorting.ByPercentIdentity));
      Assertions.assertEquals(IOHSPSorting.BYHSPSUBJECTSTART, BlastConverter.toExternal(HSPSorting.BySubjectStart));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(BlastConverter.toExternal((HSPSorting) null));
    }
  }

  @Nested
  @DisplayName("::toExternal(BlastReportFormat)")
  class ToExternal5
  {
    @Test
    @DisplayName("Correctly converts BlastReportFormat values.")
    void test1() {
      var in = new ReportFormatImpl(
        BlastReportType.CSV,
        ";",
        BlastReportField.SubjectUniqueTaxonomyIDs,
        BlastReportField.BlastTracebackOps
      );

      var out = BlastConverter.toExternal(in);

      Assertions.assertEquals(in.getDelimiter(), out.getDelim());
      Assertions.assertEquals(IOBlastFormat.CSV, out.getFormat());

      Assertions.assertEquals(2, out.getFields().size());
      Assertions.assertEquals(BlastReportField.SubjectUniqueTaxonomyIDs, out.getFields().get(0));
      Assertions.assertEquals(BlastReportField.BlastTracebackOps, out.getFields().get(1));
    }

    @Test
    @DisplayName("Correctly converts BlastReportFormat values.")
    void test2() {
      var in = new ReportFormatImpl(
        BlastReportType.CSV,
        ";",
        BlastReportField.Standard
      );

      var out = BlastConverter.toExternal(in);

      Assertions.assertEquals(in.getDelimiter(), out.getDelim());
      Assertions.assertEquals(IOBlastFormat.CSV, out.getFormat());
      Assertions.assertNull(out.getFields());
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test3() {
      Assertions.assertNull(BlastConverter.toExternal((BlastReportFormat) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOBlastFormat)")
  class ToInternal1
  {
    @Test
    @DisplayName("Correctly converts IOBlastFormat values.")
    void test1() {
      Assertions.assertEquals(BlastReportType.Pairwise, BlastConverter.toInternal(IOBlastFormat.PAIRWISE));
      Assertions.assertEquals(BlastReportType.QueryAnchoredWithIdentities, BlastConverter.toInternal(IOBlastFormat.QUERYANCHOREDWITHIDENTITIES));
      Assertions.assertEquals(BlastReportType.QueryAnchoredWithoutIdentities, BlastConverter.toInternal(IOBlastFormat.QUERYANCHOREDWITHOUTIDENTITIES));
      Assertions.assertEquals(BlastReportType.FlatQueryAnchoredWithIdentities, BlastConverter.toInternal(IOBlastFormat.FLATQUERYANCHOREDWITHIDENTITIES));
      Assertions.assertEquals(BlastReportType.FlatQueryAnchoredWithoutIdentities, BlastConverter.toInternal(IOBlastFormat.FLATQUERYANCHOREDWITHOUTIDENTITIES));
      Assertions.assertEquals(BlastReportType.XML, BlastConverter.toInternal(IOBlastFormat.XML));
      Assertions.assertEquals(BlastReportType.Tabular, BlastConverter.toInternal(IOBlastFormat.TABULAR));
      Assertions.assertEquals(BlastReportType.TabularWithComments, BlastConverter.toInternal(IOBlastFormat.TABULARWITHCOMMENTS));
      Assertions.assertEquals(BlastReportType.TextASN1, BlastConverter.toInternal(IOBlastFormat.TEXTASN_1));
      Assertions.assertEquals(BlastReportType.BinaryASN1, BlastConverter.toInternal(IOBlastFormat.BINARYASN_1));
      Assertions.assertEquals(BlastReportType.CSV, BlastConverter.toInternal(IOBlastFormat.CSV));
      Assertions.assertEquals(BlastReportType.ArchiveASN1, BlastConverter.toInternal(IOBlastFormat.ARCHIVEASN_1));
      Assertions.assertEquals(BlastReportType.SeqAlignJSON, BlastConverter.toInternal(IOBlastFormat.SEQALIGNJSON));
      Assertions.assertEquals(BlastReportType.MultiFileJSON, BlastConverter.toInternal(IOBlastFormat.MULTIFILEJSON));
      Assertions.assertEquals(BlastReportType.MultiFileXML2, BlastConverter.toInternal(IOBlastFormat.MULTIFILEXML2));
      Assertions.assertEquals(BlastReportType.SingleFileJSON, BlastConverter.toInternal(IOBlastFormat.SINGLEFILEJSON));
      Assertions.assertEquals(BlastReportType.SingleFileXML2, BlastConverter.toInternal(IOBlastFormat.SINGLEFILEXML2));
      Assertions.assertEquals(BlastReportType.SAM, BlastConverter.toInternal(IOBlastFormat.SAM));
      Assertions.assertEquals(BlastReportType.OrganismReport, BlastConverter.toInternal(IOBlastFormat.ORGANISMREPORT));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(BlastConverter.toInternal((IOBlastFormat) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOHSPSorting)")
  class ToInternal4
  {
    @Test
    @DisplayName("Correctly converts IOHSPSorting values.")
    void test1() {
      Assertions.assertEquals(HSPSorting.ByExpectValue, BlastConverter.toInternal(IOHSPSorting.BYHSPEVALUE));
      Assertions.assertEquals(HSPSorting.ByScore, BlastConverter.toInternal(IOHSPSorting.BYHSPSCORE));
      Assertions.assertEquals(HSPSorting.ByQueryStart, BlastConverter.toInternal(IOHSPSorting.BYHSPQUERYSTART));
      Assertions.assertEquals(HSPSorting.ByPercentIdentity, BlastConverter.toInternal(IOHSPSorting.BYHSPPERCENTIDENTITY));
      Assertions.assertEquals(HSPSorting.BySubjectStart, BlastConverter.toInternal(IOHSPSorting.BYHSPSUBJECTSTART));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(BlastConverter.toInternal((IOHSPSorting) null));
    }
  }
}