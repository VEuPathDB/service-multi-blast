package org.veupathdb.service.multiblast.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.blast.impl.ReportFormatImpl;

import static org.junit.jupiter.api.Assertions.*;

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
      assertEquals(IOBlastFormat.PAIRWISE, BlastConverter.toExternal(BlastReportType.Pairwise));
      assertEquals(IOBlastFormat.QUERYANCHOREDWITHIDENTITIES, BlastConverter.toExternal(BlastReportType.QueryAnchoredWithIdentities));
      assertEquals(IOBlastFormat.QUERYANCHOREDWITHOUTIDENTITIES, BlastConverter.toExternal(BlastReportType.QueryAnchoredWithoutIdentities));
      assertEquals(IOBlastFormat.FLATQUERYANCHOREDWITHIDENTITIES, BlastConverter.toExternal(BlastReportType.FlatQueryAnchoredWithIdentities));
      assertEquals(IOBlastFormat.FLATQUERYANCHOREDWITHOUTIDENTITIES, BlastConverter.toExternal(BlastReportType.FlatQueryAnchoredWithoutIdentities));
      assertEquals(IOBlastFormat.XML, BlastConverter.toExternal(BlastReportType.XML));
      assertEquals(IOBlastFormat.TABULAR, BlastConverter.toExternal(BlastReportType.Tabular));
      assertEquals(IOBlastFormat.TABULARWITHCOMMENTS, BlastConverter.toExternal(BlastReportType.TabularWithComments));
      assertEquals(IOBlastFormat.TEXTASN_1, BlastConverter.toExternal(BlastReportType.TextASN1));
      assertEquals(IOBlastFormat.BINARYASN_1, BlastConverter.toExternal(BlastReportType.BinaryASN1));
      assertEquals(IOBlastFormat.CSV, BlastConverter.toExternal(BlastReportType.CSV));
      assertEquals(IOBlastFormat.ARCHIVEASN_1, BlastConverter.toExternal(BlastReportType.ArchiveASN1));
      assertEquals(IOBlastFormat.SEQALIGNJSON, BlastConverter.toExternal(BlastReportType.SeqAlignJSON));
      assertEquals(IOBlastFormat.MULTIFILEJSON, BlastConverter.toExternal(BlastReportType.MultiFileJSON));
      assertEquals(IOBlastFormat.MULTIFILEXML2, BlastConverter.toExternal(BlastReportType.MultiFileXML2));
      assertEquals(IOBlastFormat.SINGLEFILEJSON, BlastConverter.toExternal(BlastReportType.SingleFileJSON));
      assertEquals(IOBlastFormat.SINGLEFILEXML2, BlastConverter.toExternal(BlastReportType.SingleFileXML2));
      assertEquals(IOBlastFormat.SAM, BlastConverter.toExternal(BlastReportType.SAM));
      assertEquals(IOBlastFormat.ORGANISMREPORT, BlastConverter.toExternal(BlastReportType.OrganismReport));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastConverter.toExternal((BlastReportType) null));
    }
  }

  @Nested
  @DisplayName("::toExternal(HspSorting)")
  class ToExternal4
  {
    @Test
    @DisplayName("Correctly converts HspSorting values.")
    void test1() {
      assertEquals(IOHSPSorting.BYHSPEVALUE, BlastConverter.toExternal(HspSorting.ByExpectValue));
      assertEquals(IOHSPSorting.BYHSPSCORE, BlastConverter.toExternal(HspSorting.ByScore));
      assertEquals(IOHSPSorting.BYHSPQUERYSTART, BlastConverter.toExternal(HspSorting.ByQueryStart));
      assertEquals(IOHSPSorting.BYHSPPERCENTIDENTITY, BlastConverter.toExternal(HspSorting.ByPercentIdentity));
      assertEquals(IOHSPSorting.BYHSPSUBJECTSTART, BlastConverter.toExternal(HspSorting.BySubjectStart));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastConverter.toExternal((HspSorting) null));
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

      assertEquals(in.getDelimiter(), out.getDelim());
      assertEquals(IOBlastFormat.CSV, out.getFormat());

      assertEquals(2, out.getFields().size());
      assertEquals(BlastReportField.SubjectUniqueTaxonomyIDs, out.getFields().get(0));
      assertEquals(BlastReportField.BlastTracebackOps, out.getFields().get(1));
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

      assertEquals(in.getDelimiter(), out.getDelim());
      assertEquals(IOBlastFormat.CSV, out.getFormat());
      assertNull(out.getFields());
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test3() {
      assertNull(BlastConverter.toExternal((BlastReportFormat) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOBlastFormat)")
  class ToInternal1
  {
    @Test
    @DisplayName("Correctly converts IOBlastFormat values.")
    void test1() {
      assertEquals(BlastReportType.Pairwise, BlastConverter.toInternal(IOBlastFormat.PAIRWISE));
      assertEquals(BlastReportType.QueryAnchoredWithIdentities, BlastConverter.toInternal(IOBlastFormat.QUERYANCHOREDWITHIDENTITIES));
      assertEquals(BlastReportType.QueryAnchoredWithoutIdentities, BlastConverter.toInternal(IOBlastFormat.QUERYANCHOREDWITHOUTIDENTITIES));
      assertEquals(BlastReportType.FlatQueryAnchoredWithIdentities, BlastConverter.toInternal(IOBlastFormat.FLATQUERYANCHOREDWITHIDENTITIES));
      assertEquals(BlastReportType.FlatQueryAnchoredWithoutIdentities, BlastConverter.toInternal(IOBlastFormat.FLATQUERYANCHOREDWITHOUTIDENTITIES));
      assertEquals(BlastReportType.XML, BlastConverter.toInternal(IOBlastFormat.XML));
      assertEquals(BlastReportType.Tabular, BlastConverter.toInternal(IOBlastFormat.TABULAR));
      assertEquals(BlastReportType.TabularWithComments, BlastConverter.toInternal(IOBlastFormat.TABULARWITHCOMMENTS));
      assertEquals(BlastReportType.TextASN1, BlastConverter.toInternal(IOBlastFormat.TEXTASN_1));
      assertEquals(BlastReportType.BinaryASN1, BlastConverter.toInternal(IOBlastFormat.BINARYASN_1));
      assertEquals(BlastReportType.CSV, BlastConverter.toInternal(IOBlastFormat.CSV));
      assertEquals(BlastReportType.ArchiveASN1, BlastConverter.toInternal(IOBlastFormat.ARCHIVEASN_1));
      assertEquals(BlastReportType.SeqAlignJSON, BlastConverter.toInternal(IOBlastFormat.SEQALIGNJSON));
      assertEquals(BlastReportType.MultiFileJSON, BlastConverter.toInternal(IOBlastFormat.MULTIFILEJSON));
      assertEquals(BlastReportType.MultiFileXML2, BlastConverter.toInternal(IOBlastFormat.MULTIFILEXML2));
      assertEquals(BlastReportType.SingleFileJSON, BlastConverter.toInternal(IOBlastFormat.SINGLEFILEJSON));
      assertEquals(BlastReportType.SingleFileXML2, BlastConverter.toInternal(IOBlastFormat.SINGLEFILEXML2));
      assertEquals(BlastReportType.SAM, BlastConverter.toInternal(IOBlastFormat.SAM));
      assertEquals(BlastReportType.OrganismReport, BlastConverter.toInternal(IOBlastFormat.ORGANISMREPORT));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastConverter.toInternal((IOBlastFormat) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOHSPSorting)")
  class ToInternal4
  {
    @Test
    @DisplayName("Correctly converts IOHSPSorting values.")
    void test1() {
      assertEquals(HspSorting.ByExpectValue, BlastConverter.toInternal(IOHSPSorting.BYHSPEVALUE));
      assertEquals(HspSorting.ByScore, BlastConverter.toInternal(IOHSPSorting.BYHSPSCORE));
      assertEquals(HspSorting.ByQueryStart, BlastConverter.toInternal(IOHSPSorting.BYHSPQUERYSTART));
      assertEquals(HspSorting.ByPercentIdentity, BlastConverter.toInternal(IOHSPSorting.BYHSPPERCENTIDENTITY));
      assertEquals(HspSorting.BySubjectStart, BlastConverter.toInternal(IOHSPSorting.BYHSPSUBJECTSTART));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastConverter.toInternal((IOHSPSorting) null));
    }
  }
}