package org.veupathdb.service.multiblast.service.conv;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.blast.impl.ReportFormatImpl;
import org.veupathdb.service.multiblast.model.blast.n.BlastnConfig;
import org.veupathdb.service.multiblast.model.blast.p.BlastpConfig;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastnConfig;
import org.veupathdb.service.multiblast.model.blast.tx.TBlastXConfig;
import org.veupathdb.service.multiblast.model.blast.x.BlastxConfig;

public class BlastConverter
{
  private static BlastConverter instance;

  public static BlastConverter getInstance() {
    if (instance == null)
      return instance = new BlastConverter();

    return instance;
  }

  static BlastConfig<?> toInternal(IOBlastConfig conf) {
    return getInstance().externalToInternal(conf);
  }

  public BlastConfig<?> externalToInternal(IOBlastConfig conf) {
    if (conf == null)
      return null;

    BlastConfig<?> out = switch (conf.getTool()) {
      case BLASTN -> BlastnConverter.toInternal((IOBlastnConfig) conf);
      case BLASTP -> BlastpConverter.toInternal((IOBlastpConfig) conf);
      case BLASTX -> BlastxConverter.toInternal((IOBlastxConfig) conf);
      case TBLASTN -> TBlastnConverter.toInternal((IOTBlastnConfig) conf);
      case TBLASTX -> TBlastxConverter.toInternal((IOTBlastxConfig) conf);
    };

    out.setQuery(conf.getQuery())
      .setQueryLocation(BCC.toInternal(conf.getQueryLoc()))
      .setExpectValue(new BigDecimal(conf.getEValue()))
      .setReportFormat(toInternal(conf.getOutFormat()))
      .setNumDescriptions(conf.getNumDescriptions())
      .setNumAlignments(conf.getNumAlignments())
      .setLineLength(conf.getLineLength())
      .setHitSorting(conf.getSortHits())
      .setHSPSorting(toInternal(conf.getSortHSPs()))
      .enableLowercaseMasking(BCC.nullToFalse(conf.getLcaseMasking()))
      .setQueryCoveragePercentHSP(conf.getQCovHSPPerc())
      .setMaxHSPs(conf.getMaxHSPs())
      .setMaxTargetSequences(conf.getMaxTargetSeqs())
      .setEffectiveDatabaseSize(conf.getDbSize())
      .setEffectiveSearchSpaceLength(conf.getSearchSpace())
      .setExtensionDropoffUngapped(conf.getXDropUngap())
      .enableDefLineParsing(BCC.nullToFalse(conf.getParseDefLines()));

    return out;
  }

  public static IOBlastConfig toExternal(BlastConfig<?> conf) {
    return getInstance().internalToExternal(conf);
  }

  public IOBlastConfig internalToExternal(BlastConfig<?> conf) {
    if (conf == null)
      return null;

    var out = newExternal(conf);

    out.setQueryLoc(BCC.toExternal(conf.getQueryLocation()));
    out.setEValue(conf.getExpectValue().toString());
    out.setOutFormat(toExternal(conf.getReportFormat()));
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(conf.getHitSorting());
    out.setSortHSPs(toExternal(conf.getHspSorting()));
    out.setLcaseMasking(conf.isLowercaseMaskingEnabled() ? true : null);
    out.setQCovHSPPerc(conf.getQueryCoverageHspPercent());
    out.setMaxHSPs(conf.getMaxHsps());
    out.setMaxTargetSeqs(conf.getMaxTargetSequences());
    out.setDbSize(conf.getEffectiveDatabaseSize());
    out.setSearchSpace(conf.getEffectiveSearchSpaceLength());
    out.setXDropUngap(conf.getUngappedExtensionDropoff());
    out.setParseDefLines(conf.isDefLineParsingEnabled() ? true : null);

    return switch (out.getTool()) {
      case BLASTN -> BlastnConverter.toExternal((IOBlastnConfig) out, (BlastnConfig) conf);
      case BLASTP -> BlastpConverter.toExternal((IOBlastpConfig) out, (BlastpConfig) conf);
      case BLASTX -> BlastxConverter.toExternal((IOBlastxConfig) out, (BlastxConfig) conf);
      case TBLASTN -> TBlastnConverter.toExternal((IOTBlastnConfig) out, (TBlastnConfig) conf);
      case TBLASTX -> TBlastxConverter.toExternal((IOTBlastxConfig) out, (TBlastXConfig) conf);
    };
  }

  static IOBlastConfig newExternal(BlastConfig<?> conf) {
    if (conf instanceof BlastnConfig)
      return new IOBlastnConfigImpl();
    if (conf instanceof BlastpConfig)
      return new IOBlastpConfigImpl();
    if (conf instanceof BlastxConfig)
      return new IOBlastxConfigImpl();
    if (conf instanceof TBlastnConfig)
      return new IOTBlastnConfigImpl();
    if (conf instanceof TBlastXConfig)
      return new IOTBlastxConfigImpl();

    throw new IllegalArgumentException("unrecognized blast config type");
  }

  static IOBlastFormat toExternal(BlastReportType val) {
    if (val == null)
      return null;

    return switch (val) {
      case Pairwise -> IOBlastFormat.PAIRWISE;
      case QueryAnchoredWithIdentities -> IOBlastFormat.QUERYANCHOREDWITHIDENTITIES;
      case QueryAnchoredWithoutIdentities -> IOBlastFormat.QUERYANCHOREDWITHOUTIDENTITIES;
      case FlatQueryAnchoredWithIdentities -> IOBlastFormat.FLATQUERYANCHOREDWITHIDENTITIES;
      case FlatQueryAnchoredWithoutIdentities -> IOBlastFormat.FLATQUERYANCHOREDWITHOUTIDENTITIES;
      case XML -> IOBlastFormat.XML;
      case Tabular -> IOBlastFormat.TABULAR;
      case TabularWithComments -> IOBlastFormat.TABULARWITHCOMMENTS;
      case TextASN1 -> IOBlastFormat.TEXTASN_1;
      case BinaryASN1 -> IOBlastFormat.BINARYASN_1;
      case CSV -> IOBlastFormat.CSV;
      case ArchiveASN1 -> IOBlastFormat.ARCHIVEASN_1;
      case SeqAlignJSON -> IOBlastFormat.SEQALIGNJSON;
      case MultiFileJSON -> IOBlastFormat.MULTIFILEJSON;
      case MultiFileXML2 -> IOBlastFormat.MULTIFILEXML2;
      case SingleFileJSON -> IOBlastFormat.SINGLEFILEJSON;
      case SingleFileXML2 -> IOBlastFormat.SINGLEFILEXML2;
      case SAM -> IOBlastFormat.SAM;
      case OrganismReport -> IOBlastFormat.ORGANISMREPORT;
    };
  }

  static IOBlastReportField toExternal(BlastReportField field) {
    return switch (field) {
      case QuerySequenceID -> IOBlastReportField.QSEQID;
      case QueryGenInfo -> IOBlastReportField.QGI;
      case QueryAccession -> IOBlastReportField.QACC;
      case QueryAccessionVersion -> IOBlastReportField.QACCVER;
      case QuerySequenceLength -> IOBlastReportField.QLEN;
      case SubjectSequenceID -> IOBlastReportField.SSEQID;
      case SubjectAllSequenceID -> IOBlastReportField.SALLSEQID;
      case SubjectGenInfo -> IOBlastReportField.SGI;
      case SubjectAllGenInfo -> IOBlastReportField.SALLGI;
      case SubjectAccession -> IOBlastReportField.SACC;
      case SubjectAccessionVersion -> IOBlastReportField.SACCVER;
      case SubjectAllAccession -> IOBlastReportField.SALLACC;
      case SubjectSequenceLength -> IOBlastReportField.SLEN;
      case QueryAlignmentStart -> IOBlastReportField.QSTART;
      case QueryAlignmentEnd -> IOBlastReportField.QEND;
      case SubjectAlignmentStart -> IOBlastReportField.SSTART;
      case SubjectAlignmentEnd -> IOBlastReportField.SEND;
      case QuerySequence -> IOBlastReportField.QSEQ;
      case SubjectSequence -> IOBlastReportField.SSEQ;
      case ExpectValue -> IOBlastReportField.EVALUE;
      case BitScore -> IOBlastReportField.BITSCORE;
      case RawScore -> IOBlastReportField.SCORE;
      case AlignmentLength -> IOBlastReportField.LENGTH;
      case PercentIdenticalMatches -> IOBlastReportField.PIDENT;
      case NumberIdenticalMatches -> IOBlastReportField.NIDENT;
      case NumberMismatches -> IOBlastReportField.MISMATCH;
      case NumberPositiveMatches -> IOBlastReportField.POSITIVE;
      case NumberGapOpenings -> IOBlastReportField.GAPOPEN;
      case NumberGaps -> IOBlastReportField.GAPS;
      case PercentPositiveMatches -> IOBlastReportField.PPOS;
      case Frames -> IOBlastReportField.FRAMES;
      case QueryFrame -> IOBlastReportField.QFRAME;
      case SubjectFrame -> IOBlastReportField.SFRAME;
      case BlastTracebackOps -> IOBlastReportField.BTOP;
      case SubjectTaxonomyID -> IOBlastReportField.STAXID;
      case SubjectScientificName -> IOBlastReportField.SSCINAME;
      case SubjectCommonName -> IOBlastReportField.SCOMNAME;
      case SubjectBlastName -> IOBlastReportField.SBLASTNAME;
      case SubjectSuperKingdom -> IOBlastReportField.SSKINGDOM;
      case SubjectUniqueTaxonomyIDs -> IOBlastReportField.STAXIDS;
      case SubjectScientificNames -> IOBlastReportField.SSCINAMES;
      case SubjectCommonNames -> IOBlastReportField.SCOMNAMES;
      case SubjectBlastNames -> IOBlastReportField.SBLASTNAMES;
      case SubjectSuperKingdoms -> IOBlastReportField.SSKINGDOMS;
      case SubjectTitle -> IOBlastReportField.STITLE;
      case SubjectAllTitles -> IOBlastReportField.SALLTITLES;
      case SubjectStrand -> IOBlastReportField.SSTRAND;
      case QueryCoveragePerSubject -> IOBlastReportField.QCOVS;
      case QueryCoveragePerHSP -> IOBlastReportField.QCOVHSP;
      case QueryCoveragePerUniqueSubject -> IOBlastReportField.QCOVUS;
      case SQ -> IOBlastReportField.SQ;
      case SR -> IOBlastReportField.SR;
      case Standard -> null;
    };
  }

  static IOHSPSorting toExternal(HspSorting val) {
    if (val == null)
      return null;

    return switch (val) {
      case ByExpectValue -> IOHSPSorting.BYHSPEVALUE;
      case ByScore -> IOHSPSorting.BYHSPSCORE;
      case ByQueryStart -> IOHSPSorting.BYHSPQUERYSTART;
      case ByPercentIdentity -> IOHSPSorting.BYHSPPERCENTIDENTITY;
      case BySubjectStart -> IOHSPSorting.BYHSPSUBJECTSTART;
    };
  }

  static IOBlastReportFormat toExternal(BlastReportFormat fmt) {
    if (fmt == null)
      return null;

    var out = new IOBlastReportFormatImpl();

    out.setDelim(fmt.getDelimiter() == null ? null : fmt.getDelimiter());
    out.setFields(
      fmt.getReportFields() == null
        || fmt.getReportFields().length == 1
        && fmt.getReportFields()[0] == BlastReportField.Standard
      ? null
      : Arrays.stream(fmt.getReportFields())
        .map(BlastConverter::toExternal)
        .collect(Collectors.toList()));
    out.setFormat(toExternal(fmt.getType()));

    return out;
  }

  static BlastReportType toInternal(IOBlastFormat val) {
    if (val == null)
      return null;

    return switch (val) {
      case PAIRWISE -> BlastReportType.Pairwise;
      case QUERYANCHOREDWITHIDENTITIES -> BlastReportType.QueryAnchoredWithIdentities;
      case QUERYANCHOREDWITHOUTIDENTITIES -> BlastReportType.QueryAnchoredWithoutIdentities;
      case FLATQUERYANCHOREDWITHIDENTITIES -> BlastReportType.FlatQueryAnchoredWithIdentities;
      case FLATQUERYANCHOREDWITHOUTIDENTITIES -> BlastReportType.FlatQueryAnchoredWithoutIdentities;
      case XML -> BlastReportType.XML;
      case TABULAR -> BlastReportType.Tabular;
      case TABULARWITHCOMMENTS -> BlastReportType.TabularWithComments;
      case TEXTASN_1 -> BlastReportType.TextASN1;
      case BINARYASN_1 -> BlastReportType.BinaryASN1;
      case CSV -> BlastReportType.CSV;
      case ARCHIVEASN_1 -> BlastReportType.ArchiveASN1;
      case SEQALIGNJSON -> BlastReportType.SeqAlignJSON;
      case MULTIFILEJSON -> BlastReportType.MultiFileJSON;
      case MULTIFILEXML2 -> BlastReportType.MultiFileXML2;
      case SINGLEFILEJSON -> BlastReportType.SingleFileJSON;
      case SINGLEFILEXML2 -> BlastReportType.SingleFileXML2;
      case SAM -> BlastReportType.SAM;
      case ORGANISMREPORT -> BlastReportType.OrganismReport;
    };
  }

  static BlastReportField[] toInternal(List<IOBlastReportField> vals) {
    if (vals == null || vals.isEmpty())
      return null;

    return vals.stream()
      .filter(Objects::nonNull)
      .map(BlastConverter::toInternal)
      .toArray(BlastReportField[]::new);
  }

  static BlastReportField toInternal(IOBlastReportField val) {
    if (val == null)
      return null;

    return switch (val) {
      case BITSCORE -> BlastReportField.BitScore;
      case BTOP -> BlastReportField.BlastTracebackOps;
      case EVALUE -> BlastReportField.ExpectValue;
      case FRAMES -> BlastReportField.Frames;
      case GAPOPEN -> BlastReportField.NumberGapOpenings;
      case GAPS -> BlastReportField.NumberGaps;
      case LENGTH -> BlastReportField.AlignmentLength;
      case MISMATCH -> BlastReportField.NumberMismatches;
      case NIDENT -> BlastReportField.NumberIdenticalMatches;
      case PIDENT -> BlastReportField.PercentIdenticalMatches;
      case POSITIVE -> BlastReportField.NumberPositiveMatches;
      case PPOS -> BlastReportField.PercentPositiveMatches;
      case QACC -> BlastReportField.QueryAccession;
      case QACCVER -> BlastReportField.QueryAccessionVersion;
      case QCOVHSP -> BlastReportField.QueryCoveragePerHSP;
      case QCOVS -> BlastReportField.QueryCoveragePerSubject;
      case QCOVUS -> BlastReportField.QueryCoveragePerUniqueSubject;
      case QEND -> BlastReportField.QueryAlignmentEnd;
      case QFRAME -> BlastReportField.QueryFrame;
      case QGI -> BlastReportField.QueryGenInfo;
      case QLEN -> BlastReportField.QuerySequenceLength;
      case QSEQ -> BlastReportField.QuerySequence;
      case QSEQID -> BlastReportField.QuerySequenceID;
      case QSTART -> BlastReportField.QueryAlignmentStart;
      case SACC -> BlastReportField.SubjectAccession;
      case SACCVER -> BlastReportField.SubjectAccessionVersion;
      case SALLACC -> BlastReportField.SubjectAllAccession;
      case SALLGI -> BlastReportField.SubjectAllGenInfo;
      case SALLSEQID -> BlastReportField.SubjectAllSequenceID;
      case SALLTITLES -> BlastReportField.SubjectAllTitles;
      case SBLASTNAME -> BlastReportField.SubjectBlastName;
      case SBLASTNAMES -> BlastReportField.SubjectBlastNames;
      case SCOMNAME -> BlastReportField.SubjectCommonName;
      case SCOMNAMES -> BlastReportField.SubjectCommonNames;
      case SCORE -> BlastReportField.RawScore;
      case SEND -> BlastReportField.SubjectAlignmentEnd;
      case SFRAME -> BlastReportField.SubjectFrame;
      case SGI -> BlastReportField.SubjectGenInfo;
      case SLEN -> BlastReportField.SubjectSequenceLength;
      case SQ -> BlastReportField.SQ;
      case SR -> BlastReportField.SR;
      case SSCINAME -> BlastReportField.SubjectScientificName;
      case SSCINAMES -> BlastReportField.SubjectScientificNames;
      case SSEQ -> BlastReportField.SubjectSequence;
      case SSEQID -> BlastReportField.SubjectSequenceID;
      case SSKINGDOM -> BlastReportField.SubjectSuperKingdom;
      case SSKINGDOMS -> BlastReportField.SubjectSuperKingdoms;
      case SSTART -> BlastReportField.SubjectAlignmentStart;
      case SSTRAND -> BlastReportField.SubjectStrand;
      case STAXID -> BlastReportField.SubjectTaxonomyID;
      case STAXIDS -> BlastReportField.SubjectUniqueTaxonomyIDs;
      case STITLE -> BlastReportField.SubjectTitle;
    };
  }

  static BlastReportFormat toInternal(IOBlastReportFormat fmt) {
    if (fmt == null)
      return null;

    return new ReportFormatImpl(
      toInternal(fmt.getFormat()),
      fmt.getDelim(),
      toInternal(fmt.getFields())
    );
  }

  static HspSorting toInternal(IOHSPSorting val) {
    if (val == null)
      return null;

    return switch(val) {
      case BYHSPEVALUE -> HspSorting.ByExpectValue;
      case BYHSPSCORE -> HspSorting.ByScore;
      case BYHSPQUERYSTART -> HspSorting.ByQueryStart;
      case BYHSPPERCENTIDENTITY -> HspSorting.ByPercentIdentity;
      case BYHSPSUBJECTSTART -> HspSorting.BySubjectStart;
    };
  }
}
