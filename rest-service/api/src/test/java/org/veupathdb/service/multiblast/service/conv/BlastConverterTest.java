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
  @DisplayName("::toExternal(BlastReportField)")
  class ToExternal2
  {
    @Test
    @DisplayName("Correctly converts BlastReportField values.")
    void test1() {
      assertEquals(IOBlastReportField.QSEQID, BlastConverter.toExternal(BlastReportField.QuerySequenceID));
      assertEquals(IOBlastReportField.QGI, BlastConverter.toExternal(BlastReportField.QueryGenInfo));
      assertEquals(IOBlastReportField.QACC, BlastConverter.toExternal(BlastReportField.QueryAccession));
      assertEquals(IOBlastReportField.QACCVER, BlastConverter.toExternal(BlastReportField.QueryAccessionVersion));
      assertEquals(IOBlastReportField.QLEN, BlastConverter.toExternal(BlastReportField.QuerySequenceLength));
      assertEquals(IOBlastReportField.SSEQID, BlastConverter.toExternal(BlastReportField.SubjectSequenceID));
      assertEquals(IOBlastReportField.SALLSEQID, BlastConverter.toExternal(BlastReportField.SubjectAllSequenceID));
      assertEquals(IOBlastReportField.SGI, BlastConverter.toExternal(BlastReportField.SubjectGenInfo));
      assertEquals(IOBlastReportField.SALLGI, BlastConverter.toExternal(BlastReportField.SubjectAllGenInfo));
      assertEquals(IOBlastReportField.SACC, BlastConverter.toExternal(BlastReportField.SubjectAccession));
      assertEquals(IOBlastReportField.SACCVER, BlastConverter.toExternal(BlastReportField.SubjectAccessionVersion));
      assertEquals(IOBlastReportField.SALLACC, BlastConverter.toExternal(BlastReportField.SubjectAllAccession));
      assertEquals(IOBlastReportField.SLEN, BlastConverter.toExternal(BlastReportField.SubjectSequenceLength));
      assertEquals(IOBlastReportField.QSTART, BlastConverter.toExternal(BlastReportField.QueryAlignmentStart));
      assertEquals(IOBlastReportField.QEND, BlastConverter.toExternal(BlastReportField.QueryAlignmentEnd));
      assertEquals(IOBlastReportField.SSTART, BlastConverter.toExternal(BlastReportField.SubjectAlignmentStart));
      assertEquals(IOBlastReportField.SEND, BlastConverter.toExternal(BlastReportField.SubjectAlignmentEnd));
      assertEquals(IOBlastReportField.QSEQ, BlastConverter.toExternal(BlastReportField.QuerySequence));
      assertEquals(IOBlastReportField.SSEQ, BlastConverter.toExternal(BlastReportField.SubjectSequence));
      assertEquals(IOBlastReportField.EVALUE, BlastConverter.toExternal(BlastReportField.ExpectValue));
      assertEquals(IOBlastReportField.BITSCORE, BlastConverter.toExternal(BlastReportField.BitScore));
      assertEquals(IOBlastReportField.SCORE, BlastConverter.toExternal(BlastReportField.RawScore));
      assertEquals(IOBlastReportField.LENGTH, BlastConverter.toExternal(BlastReportField.AlignmentLength));
      assertEquals(IOBlastReportField.PIDENT, BlastConverter.toExternal(BlastReportField.PercentIdenticalMatches));
      assertEquals(IOBlastReportField.NIDENT, BlastConverter.toExternal(BlastReportField.NumberIdenticalMatches));
      assertEquals(IOBlastReportField.MISMATCH, BlastConverter.toExternal(BlastReportField.NumberMismatches));
      assertEquals(IOBlastReportField.POSITIVE, BlastConverter.toExternal(BlastReportField.NumberPositiveMatches));
      assertEquals(IOBlastReportField.GAPOPEN, BlastConverter.toExternal(BlastReportField.NumberGapOpenings));
      assertEquals(IOBlastReportField.GAPS, BlastConverter.toExternal(BlastReportField.NumberGaps));
      assertEquals(IOBlastReportField.PPOS, BlastConverter.toExternal(BlastReportField.PercentPositiveMatches));
      assertEquals(IOBlastReportField.FRAMES, BlastConverter.toExternal(BlastReportField.Frames));
      assertEquals(IOBlastReportField.QFRAME, BlastConverter.toExternal(BlastReportField.QueryFrame));
      assertEquals(IOBlastReportField.SFRAME, BlastConverter.toExternal(BlastReportField.SubjectFrame));
      assertEquals(IOBlastReportField.BTOP, BlastConverter.toExternal(BlastReportField.BlastTracebackOps));
      assertEquals(IOBlastReportField.STAXID, BlastConverter.toExternal(BlastReportField.SubjectTaxonomyID));
      assertEquals(IOBlastReportField.SSCINAME, BlastConverter.toExternal(BlastReportField.SubjectScientificName));
      assertEquals(IOBlastReportField.SCOMNAME, BlastConverter.toExternal(BlastReportField.SubjectCommonName));
      assertEquals(IOBlastReportField.SBLASTNAME, BlastConverter.toExternal(BlastReportField.SubjectBlastName));
      assertEquals(IOBlastReportField.SSKINGDOM, BlastConverter.toExternal(BlastReportField.SubjectSuperKingdom));
      assertEquals(IOBlastReportField.STAXIDS, BlastConverter.toExternal(BlastReportField.SubjectUniqueTaxonomyIDs));
      assertEquals(IOBlastReportField.SSCINAMES, BlastConverter.toExternal(BlastReportField.SubjectScientificNames));
      assertEquals(IOBlastReportField.SCOMNAMES, BlastConverter.toExternal(BlastReportField.SubjectCommonNames));
      assertEquals(IOBlastReportField.SBLASTNAMES, BlastConverter.toExternal(BlastReportField.SubjectBlastNames));
      assertEquals(IOBlastReportField.SSKINGDOMS, BlastConverter.toExternal(BlastReportField.SubjectSuperKingdoms));
      assertEquals(IOBlastReportField.STITLE, BlastConverter.toExternal(BlastReportField.SubjectTitle));
      assertEquals(IOBlastReportField.SALLTITLES, BlastConverter.toExternal(BlastReportField.SubjectAllTitles));
      assertEquals(IOBlastReportField.SSTRAND, BlastConverter.toExternal(BlastReportField.SubjectStrand));
      assertEquals(IOBlastReportField.QCOVS, BlastConverter.toExternal(BlastReportField.QueryCoveragePerSubject));
      assertEquals(IOBlastReportField.QCOVHSP, BlastConverter.toExternal(BlastReportField.QueryCoveragePerHSP));
      assertEquals(IOBlastReportField.QCOVUS, BlastConverter.toExternal(BlastReportField.QueryCoveragePerUniqueSubject));
      assertEquals(IOBlastReportField.SQ, BlastConverter.toExternal(BlastReportField.SQ));
      assertEquals(IOBlastReportField.SR, BlastConverter.toExternal(BlastReportField.SR));
      assertNull(BlastConverter.toExternal(BlastReportField.Standard));
    }
  }

  @Nested
  @DisplayName("::toExternal(HitSorting)")
  class ToExternal3
  {
    @Test
    @DisplayName("Correctly converts HitSorting values.")
    void test1() {
      assertEquals(IOHitSorting.BYEVAL, BlastConverter.toExternal(HitSorting.ByExpectValue));
      assertEquals(IOHitSorting.BYBITSCORE, BlastConverter.toExternal(HitSorting.ByBitScore));
      assertEquals(IOHitSorting.BYTOTALSCORE, BlastConverter.toExternal(HitSorting.ByTotalScore));
      assertEquals(IOHitSorting.BYPERCENTIDENTITY, BlastConverter.toExternal(HitSorting.ByPercentIdentity));
      assertEquals(IOHitSorting.BYQUERYCOVERAGE, BlastConverter.toExternal(HitSorting.ByQueryCoverage));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastConverter.toExternal((HitSorting) null));
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
      assertEquals(IOBlastReportField.STAXIDS, out.getFields().get(0));
      assertEquals(IOBlastReportField.BTOP, out.getFields().get(1));
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
  @DisplayName("::toInternal(IOBlastReportField)")
  class ToInternal2
  {
    @Test
    @DisplayName("Correctly converts IOBlastReportField values.")
    void test1() {
      assertEquals(BlastReportField.BitScore, BlastConverter.toInternal(IOBlastReportField.BITSCORE));
      assertEquals(BlastReportField.BlastTracebackOps, BlastConverter.toInternal(IOBlastReportField.BTOP));
      assertEquals(BlastReportField.ExpectValue, BlastConverter.toInternal(IOBlastReportField.EVALUE));
      assertEquals(BlastReportField.Frames, BlastConverter.toInternal(IOBlastReportField.FRAMES));
      assertEquals(BlastReportField.NumberGapOpenings, BlastConverter.toInternal(IOBlastReportField.GAPOPEN));
      assertEquals(BlastReportField.NumberGaps, BlastConverter.toInternal(IOBlastReportField.GAPS));
      assertEquals(BlastReportField.AlignmentLength, BlastConverter.toInternal(IOBlastReportField.LENGTH));
      assertEquals(BlastReportField.NumberMismatches, BlastConverter.toInternal(IOBlastReportField.MISMATCH));
      assertEquals(BlastReportField.NumberIdenticalMatches, BlastConverter.toInternal(IOBlastReportField.NIDENT));
      assertEquals(BlastReportField.PercentIdenticalMatches, BlastConverter.toInternal(IOBlastReportField.PIDENT));
      assertEquals(BlastReportField.NumberPositiveMatches, BlastConverter.toInternal(IOBlastReportField.POSITIVE));
      assertEquals(BlastReportField.PercentPositiveMatches, BlastConverter.toInternal(IOBlastReportField.PPOS));
      assertEquals(BlastReportField.QueryAccession, BlastConverter.toInternal(IOBlastReportField.QACC));
      assertEquals(BlastReportField.QueryAccessionVersion, BlastConverter.toInternal(IOBlastReportField.QACCVER));
      assertEquals(BlastReportField.QueryCoveragePerHSP, BlastConverter.toInternal(IOBlastReportField.QCOVHSP));
      assertEquals(BlastReportField.QueryCoveragePerSubject, BlastConverter.toInternal(IOBlastReportField.QCOVS));
      assertEquals(BlastReportField.QueryCoveragePerUniqueSubject, BlastConverter.toInternal(IOBlastReportField.QCOVUS));
      assertEquals(BlastReportField.QueryAlignmentEnd, BlastConverter.toInternal(IOBlastReportField.QEND));
      assertEquals(BlastReportField.QueryFrame, BlastConverter.toInternal(IOBlastReportField.QFRAME));
      assertEquals(BlastReportField.QueryGenInfo, BlastConverter.toInternal(IOBlastReportField.QGI));
      assertEquals(BlastReportField.QuerySequenceLength, BlastConverter.toInternal(IOBlastReportField.QLEN));
      assertEquals(BlastReportField.QuerySequence, BlastConverter.toInternal(IOBlastReportField.QSEQ));
      assertEquals(BlastReportField.QuerySequenceID, BlastConverter.toInternal(IOBlastReportField.QSEQID));
      assertEquals(BlastReportField.QueryAlignmentStart, BlastConverter.toInternal(IOBlastReportField.QSTART));
      assertEquals(BlastReportField.SubjectAccession, BlastConverter.toInternal(IOBlastReportField.SACC));
      assertEquals(BlastReportField.SubjectAccessionVersion, BlastConverter.toInternal(IOBlastReportField.SACCVER));
      assertEquals(BlastReportField.SubjectAllAccession, BlastConverter.toInternal(IOBlastReportField.SALLACC));
      assertEquals(BlastReportField.SubjectAllGenInfo, BlastConverter.toInternal(IOBlastReportField.SALLGI));
      assertEquals(BlastReportField.SubjectAllSequenceID, BlastConverter.toInternal(IOBlastReportField.SALLSEQID));
      assertEquals(BlastReportField.SubjectAllTitles, BlastConverter.toInternal(IOBlastReportField.SALLTITLES));
      assertEquals(BlastReportField.SubjectBlastName, BlastConverter.toInternal(IOBlastReportField.SBLASTNAME));
      assertEquals(BlastReportField.SubjectBlastNames, BlastConverter.toInternal(IOBlastReportField.SBLASTNAMES));
      assertEquals(BlastReportField.SubjectCommonName, BlastConverter.toInternal(IOBlastReportField.SCOMNAME));
      assertEquals(BlastReportField.SubjectCommonNames, BlastConverter.toInternal(IOBlastReportField.SCOMNAMES));
      assertEquals(BlastReportField.RawScore, BlastConverter.toInternal(IOBlastReportField.SCORE));
      assertEquals(BlastReportField.SubjectAlignmentEnd, BlastConverter.toInternal(IOBlastReportField.SEND));
      assertEquals(BlastReportField.SubjectFrame, BlastConverter.toInternal(IOBlastReportField.SFRAME));
      assertEquals(BlastReportField.SubjectGenInfo, BlastConverter.toInternal(IOBlastReportField.SGI));
      assertEquals(BlastReportField.SubjectSequenceLength, BlastConverter.toInternal(IOBlastReportField.SLEN));
      assertEquals(BlastReportField.SQ, BlastConverter.toInternal(IOBlastReportField.SQ));
      assertEquals(BlastReportField.SR, BlastConverter.toInternal(IOBlastReportField.SR));
      assertEquals(BlastReportField.SubjectScientificName, BlastConverter.toInternal(IOBlastReportField.SSCINAME));
      assertEquals(BlastReportField.SubjectScientificNames, BlastConverter.toInternal(IOBlastReportField.SSCINAMES));
      assertEquals(BlastReportField.SubjectSequence, BlastConverter.toInternal(IOBlastReportField.SSEQ));
      assertEquals(BlastReportField.SubjectSequenceID, BlastConverter.toInternal(IOBlastReportField.SSEQID));
      assertEquals(BlastReportField.SubjectSuperKingdom, BlastConverter.toInternal(IOBlastReportField.SSKINGDOM));
      assertEquals(BlastReportField.SubjectSuperKingdoms, BlastConverter.toInternal(IOBlastReportField.SSKINGDOMS));
      assertEquals(BlastReportField.SubjectAlignmentStart, BlastConverter.toInternal(IOBlastReportField.SSTART));
      assertEquals(BlastReportField.SubjectStrand, BlastConverter.toInternal(IOBlastReportField.SSTRAND));
      assertEquals(BlastReportField.SubjectTaxonomyID, BlastConverter.toInternal(IOBlastReportField.STAXID));
      assertEquals(BlastReportField.SubjectUniqueTaxonomyIDs, BlastConverter.toInternal(IOBlastReportField.STAXIDS));
      assertEquals(BlastReportField.SubjectTitle, BlastConverter.toInternal(IOBlastReportField.STITLE));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastConverter.toInternal((IOBlastReportField) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOHitSorting)")
  class ToInternal3
  {
    @Test
    @DisplayName("Correctly converts IOHitSorting values.")
    void test1() {
      assertEquals(HitSorting.ByExpectValue, BlastConverter.toInternal(IOHitSorting.BYEVAL));
      assertEquals(HitSorting.ByBitScore, BlastConverter.toInternal(IOHitSorting.BYBITSCORE));
      assertEquals(HitSorting.ByTotalScore, BlastConverter.toInternal(IOHitSorting.BYTOTALSCORE));
      assertEquals(HitSorting.ByPercentIdentity, BlastConverter.toInternal(IOHitSorting.BYPERCENTIDENTITY));
      assertEquals(HitSorting.ByQueryCoverage, BlastConverter.toInternal(IOHitSorting.BYQUERYCOVERAGE));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastConverter.toInternal((IOHitSorting) null));
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