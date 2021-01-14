package org.veupathdb.service.multiblast.service.conv;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.blast.n.BlastnConfig;
import org.veupathdb.service.multiblast.model.blast.p.BlastpConfig;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastnConfig;
import org.veupathdb.service.multiblast.model.blast.tx.TBlastXConfig;
import org.veupathdb.service.multiblast.model.blast.x.BlastxConfig;
import org.veupathdb.service.multiblast.model.blast.impl.ReportFormatImpl;

public class BlastConverter
{
  private static final Logger log = LogProvider.logger(BlastConverter.class);

  private static BlastConverter instance;

  private BlastConverter() {
    log.trace("#new()");
  }

  public static BlastConverter getInstance() {
    log.trace("#getInstance()");

    if (instance == null)
      return instance = new BlastConverter();

    return instance;
  }

  static BlastConfig<?> toInternal(IOBlastConfig conf) {
    log.trace("#toInternal(IOBlastConfig)");

    return getInstance().externalToInternal(conf);
  }

  public BlastConfig<?> externalToInternal(IOBlastConfig conf) {
    log.trace("#externalToInternal(IOBlastConfig)");

    if (conf == null)
      return null;

    BlastConfig<?> out = switch (conf.getTool()) {
      case BLASTN -> BlastnConverter.toInternal((IOBlastnConfig) conf);
      case BLASTP -> BlastpConverter.toInternal((IOBlastpConfig) conf);
      case BLASTX -> BlastxConverter.toInternal((IOBlastxConfig) conf);
      case TBLASTN -> TBlastnConverter.toInternal((IOTBlastnConfig) conf);
      case TBLASTX -> TBlastxConverter.toInternal((IOTBlastxConfig) conf);
    };

    out.setQueryFile(new File(conf.getQuery()))
      .setQueryLocation(BCC.toInternal(conf.getQueryLoc()))
      .setExpectValue(new BigDecimal(conf.getEValue()))
      .setReportFormat(toInternal(conf.getOutFormat()))
      .setNumDescriptions(conf.getNumDescriptions())
      .setNumAlignments(conf.getNumAlignments())
      .setLineLength(conf.getLineLength())
      .setHitSorting(toInternal(conf.getSortHits()))
      .setHSPSorting(toInternal(conf.getSortHSPs()))
      .enableLowercaseMasking(BCC.nullToFalse(conf.getLcaseMasking()))
      .setQueryCoveragePercentHsp(conf.getQCovHSPPerc())
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
    log.trace("#internalToExternal(BlastConfig)");

    if (conf == null)
      return null;

    var out = newExternal(conf);

    out.setQueryLoc(BCC.toExternal(conf.getQueryLocation()));
    out.setEValue(conf.getExpectValue().toString());
    out.setOutFormat(toExternal(conf.getReportFormat()));
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(toExternal(conf.getHitSorting()));
    out.setSortHSPs(toExternal(conf.getHspSorting()));
    out.setLcaseMasking(conf.isLowercaseMaskingEnabled());
    out.setQCovHSPPerc(conf.getQueryCoverageHspPercent());
    out.setMaxHSPs(conf.getMaxHsps());
    out.setMaxTargetSeqs(conf.getMaxTargetSequences());
    out.setDbSize(conf.getEffectiveDatabaseSize());
    out.setSearchSpace(conf.getEffectiveSearchSpaceLength());
    out.setXDropUngap(conf.getUngappedExtensionDropoff());
    out.setParseDefLines(conf.isDefLineParsingEnabled());

    return switch (out.getTool()) {
      case BLASTN -> BlastnConverter.toExternal((IOBlastnConfig) out, (BlastnConfig) conf);
      case BLASTP -> BlastpConverter.toExternal((IOBlastpConfig) out, (BlastpConfig) conf);
      case BLASTX -> BlastxConverter.toExternal((IOBlastxConfig) out, (BlastxConfig) conf);
      case TBLASTN -> TBlastnConverter.toExternal((IOTBlastnConfig) out, (TBlastnConfig) conf);
      case TBLASTX -> TBlastxConverter.toExternal((IOTBlastxConfig) out, (TBlastXConfig) conf);
    };
  }

  static IOBlastConfig newExternal(BlastConfig<?> conf) {
    log.trace("#newExternal(BlastConfig)");

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
    log.trace("#toExternal(ReportFormatType)");

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
    log.trace("#toExternal(BlastReportField)");

    return switch (field) {
      case QUERY_SEQUENCE_ID -> IOBlastReportField.QSEQID;
      case QUERY_GI -> IOBlastReportField.QGI;
      case QUERY_ACCESSION -> IOBlastReportField.QACC;
      case QUERY_ACCESSION_VERSION -> IOBlastReportField.QACCVER;
      case QUERY_SEQUENCE_LENGTH -> IOBlastReportField.QLEN;
      case SUBJECT_SEQUENCE_ID -> IOBlastReportField.SSEQID;
      case SUBJECT_ALL_SEQUENCE_ID -> IOBlastReportField.SALLSEQID;
      case SUBJECT_GI -> IOBlastReportField.SGI;
      case SUBJECT_ALL_GI -> IOBlastReportField.SALLGI;
      case SUBJECT_ACCESSION -> IOBlastReportField.SACC;
      case SUBJECT_ACCESSION_VERSION -> IOBlastReportField.SACCVER;
      case SUBJECT_ALL_ACCESSION -> IOBlastReportField.SALLACC;
      case SUBJECT_SEQUENCE_LENGTH -> IOBlastReportField.SLEN;
      case QUERY_ALIGNMENT_START -> IOBlastReportField.QSTART;
      case QUERY_ALIGNMENT_END -> IOBlastReportField.QEND;
      case SUBJECT_ALIGNMENT_START -> IOBlastReportField.SSTART;
      case SUBJECT_ALIGNMENT_END -> IOBlastReportField.SEND;
      case QUERY_SEQUENCE -> IOBlastReportField.QSEQ;
      case SUBJECT_SEQUENCE -> IOBlastReportField.SSEQ;
      case EXPECT_VALUE -> IOBlastReportField.EVALUE;
      case BIT_SCORE -> IOBlastReportField.BITSCORE;
      case RAW_SCORE -> IOBlastReportField.SCORE;
      case ALIGNMENT_LENGTH -> IOBlastReportField.LENGTH;
      case PERCENT_IDENTICAL_MATCHES -> IOBlastReportField.PIDENT;
      case NUMBER_IDENTICAL_MATCHES -> IOBlastReportField.NIDENT;
      case NUMBER_MISMATCHES -> IOBlastReportField.MISMATCH;
      case NUMBER_POSITIVE_MATCHES -> IOBlastReportField.POSITIVE;
      case NUMBER_GAP_OPENINGS -> IOBlastReportField.GAPOPEN;
      case NUMBER_GAPS -> IOBlastReportField.GAPS;
      case PERCENT_POSITIVE_MATCHES -> IOBlastReportField.PPOS;
      case FRAMES -> IOBlastReportField.FRAMES;
      case QUERY_FRAME -> IOBlastReportField.QFRAME;
      case SUBJECT_FRAME -> IOBlastReportField.SFRAME;
      case BLAST_TRACEBACK_OPS -> IOBlastReportField.BTOP;
      case SUBJECT_TAXONOMY_ID -> IOBlastReportField.STAXID;
      case SUBJECT_SCIENTIFIC_NAME -> IOBlastReportField.SSCINAME;
      case SUBJECT_COMMON_NAME -> IOBlastReportField.SCOMNAME;
      case SUBJECT_BLAST_NAME -> IOBlastReportField.SBLASTNAME;
      case SUBJECT_SUPER_KINGDOM -> IOBlastReportField.SSKINGDOM;
      case SUBJECT_UNIQUE_TAXONOMY_IDS -> IOBlastReportField.STAXIDS;
      case SUBJECT_SCIENTIFIC_NAMES -> IOBlastReportField.SSCINAMES;
      case SUBJECT_COMMON_NAMES -> IOBlastReportField.SCOMNAMES;
      case SUBJECT_BLAST_NAMES -> IOBlastReportField.SBLASTNAMES;
      case SUBJECT_SUPER_KINGDOMS -> IOBlastReportField.SSKINGDOMS;
      case SUBJECT_TITLE -> IOBlastReportField.STITLE;
      case SUBJECT_ALL_TITLES -> IOBlastReportField.SALLTITLES;
      case SUBJECT_STRAND -> IOBlastReportField.SSTRAND;
      case QUERY_COVERAGE_PER_SUBJECT -> IOBlastReportField.QCOVS;
      case QUERY_COVERAGE_PER_HSP -> IOBlastReportField.QCOVHSP;
      case QUERY_COVERAGE_PER_UNIQUE_SUBJECT -> IOBlastReportField.QCOVUS;
      case SQ -> IOBlastReportField.SQ;
      case SR -> IOBlastReportField.SR;
    };
  }


  static IOHitSorting toExternal(HitSorting val) {
    log.trace("#toExternal(HitSorting)");

    return switch (val) {
      case ByExpectValue -> IOHitSorting.BYEVAL;
      case ByBitScore -> IOHitSorting.BYBITSCORE;
      case ByTotalScore -> IOHitSorting.BYTOTALSCORE;
      case ByPercentIdentity -> IOHitSorting.BYPERCENTIDENTITY;
      case ByQueryCoverage -> IOHitSorting.BYQUERYCOVERAGE;
    };
  }

  static IOHSPSorting toExternal(HspSorting val) {
    log.trace("#toExternal(HspSorting)");

    return switch (val) {
      case ByExpectValue -> IOHSPSorting.BYHSPEVALUE;
      case ByScore -> IOHSPSorting.BYHSPSCORE;
      case ByQueryStart -> IOHSPSorting.BYHSPQUERYSTART;
      case ByPercentIdentity -> IOHSPSorting.BYHSPPERCENTIDENTITY;
      case BySubjectStart -> IOHSPSorting.BYHSPSUBJECTSTART;
    };
  }

  static IOBlastReportFormat toExternal(BlastReportFormat fmt) {
    log.trace("#toExternal(OutFormat)");

    if (fmt == null)
      return null;

    var out = new IOBlastReportFormatImpl();

    out.setDelim(String.valueOf(fmt.getDelimiter()));
    out.setFields(Arrays.stream(fmt.getReportFields())
      .map(BlastConverter::toExternal)
      .collect(Collectors.toList()));
    out.setFormat(toExternal(fmt.getType()));

    return out;
  }

  static BlastReportType toInternal(IOBlastFormat val) {
    log.trace("#toInternal(IOBlastFormat)");

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
    log.trace("#toInternal(List)");

    if (vals == null || vals.isEmpty())
      return null;

    return vals.stream()
      .filter(Objects::nonNull)
      .map(BlastConverter::toInternal)
      .toArray(BlastReportField[]::new);
  }

  static BlastReportField toInternal(IOBlastReportField val) {
    log.trace("#toInternal(IOBlastReportField)");

    if (val == null)
      return null;

    return switch (val) {
      case BITSCORE -> BlastReportField.BIT_SCORE;
      case BTOP -> BlastReportField.BLAST_TRACEBACK_OPS;
      case EVALUE -> BlastReportField.EXPECT_VALUE;
      case FRAMES -> BlastReportField.FRAMES;
      case GAPOPEN -> BlastReportField.NUMBER_GAP_OPENINGS;
      case GAPS -> BlastReportField.NUMBER_GAPS;
      case LENGTH -> BlastReportField.ALIGNMENT_LENGTH;
      case MISMATCH -> BlastReportField.NUMBER_MISMATCHES;
      case NIDENT -> BlastReportField.NUMBER_IDENTICAL_MATCHES;
      case PIDENT -> BlastReportField.PERCENT_IDENTICAL_MATCHES;
      case POSITIVE -> BlastReportField.NUMBER_POSITIVE_MATCHES;
      case PPOS -> BlastReportField.PERCENT_POSITIVE_MATCHES;
      case QACC -> BlastReportField.QUERY_ACCESSION;
      case QACCVER -> BlastReportField.QUERY_ACCESSION_VERSION;
      case QCOVHSP -> BlastReportField.QUERY_COVERAGE_PER_HSP;
      case QCOVS -> BlastReportField.QUERY_COVERAGE_PER_SUBJECT;
      case QCOVUS -> BlastReportField.QUERY_COVERAGE_PER_UNIQUE_SUBJECT;
      case QEND -> BlastReportField.QUERY_ALIGNMENT_END;
      case QFRAME -> BlastReportField.QUERY_FRAME;
      case QGI -> BlastReportField.QUERY_GI;
      case QLEN -> BlastReportField.QUERY_SEQUENCE_LENGTH;
      case QSEQ -> BlastReportField.QUERY_SEQUENCE;
      case QSEQID -> BlastReportField.QUERY_SEQUENCE_ID;
      case QSTART -> BlastReportField.QUERY_ALIGNMENT_START;
      case SACC -> BlastReportField.SUBJECT_ACCESSION;
      case SACCVER -> BlastReportField.SUBJECT_ACCESSION_VERSION;
      case SALLACC -> BlastReportField.SUBJECT_ALL_ACCESSION;
      case SALLGI -> BlastReportField.SUBJECT_ALL_GI;
      case SALLSEQID -> BlastReportField.SUBJECT_ALL_SEQUENCE_ID;
      case SALLTITLES -> BlastReportField.SUBJECT_ALL_TITLES;
      case SBLASTNAME -> BlastReportField.SUBJECT_BLAST_NAME;
      case SBLASTNAMES -> BlastReportField.SUBJECT_BLAST_NAMES;
      case SCOMNAME -> BlastReportField.SUBJECT_COMMON_NAME;
      case SCOMNAMES -> BlastReportField.SUBJECT_COMMON_NAMES;
      case SCORE -> BlastReportField.RAW_SCORE;
      case SEND -> BlastReportField.SUBJECT_ALIGNMENT_END;
      case SFRAME -> BlastReportField.SUBJECT_FRAME;
      case SGI -> BlastReportField.SUBJECT_GI;
      case SLEN -> BlastReportField.SUBJECT_SEQUENCE_LENGTH;
      case SQ -> BlastReportField.SQ;
      case SR -> BlastReportField.SR;
      case SSCINAME -> BlastReportField.SUBJECT_SCIENTIFIC_NAME;
      case SSCINAMES -> BlastReportField.SUBJECT_SCIENTIFIC_NAMES;
      case SSEQ -> BlastReportField.SUBJECT_SEQUENCE;
      case SSEQID -> BlastReportField.SUBJECT_SEQUENCE_ID;
      case SSKINGDOM -> BlastReportField.SUBJECT_SUPER_KINGDOM;
      case SSKINGDOMS -> BlastReportField.SUBJECT_SUPER_KINGDOMS;
      case SSTART -> BlastReportField.SUBJECT_ALIGNMENT_START;
      case SSTRAND -> BlastReportField.SUBJECT_STRAND;
      case STAXID -> BlastReportField.SUBJECT_TAXONOMY_ID;
      case STAXIDS -> BlastReportField.SUBJECT_UNIQUE_TAXONOMY_IDS;
      case STITLE -> BlastReportField.SUBJECT_TITLE;
    };
  }

  static BlastReportFormat toInternal(IOBlastReportFormat fmt) {
    log.trace("#toInternal(IOBlastReportFormat)");

    if (fmt == null)
      return null;

    return new ReportFormatImpl(
      toInternal(fmt.getFormat()),
      fmt.getDelim(),
      toInternal(fmt.getFields())
    );
  }

  static HitSorting toInternal(IOHitSorting val) {
    log.trace("#toInternal(IOHitSorting)");

    if (val == null)
      return null;

    return switch(val) {
      case BYEVAL -> HitSorting.ByExpectValue;
      case BYBITSCORE -> HitSorting.ByBitScore;
      case BYTOTALSCORE -> HitSorting.ByTotalScore;
      case BYPERCENTIDENTITY -> HitSorting.ByPercentIdentity;
      case BYQUERYCOVERAGE -> HitSorting.ByQueryCoverage;
    };
  }

  static HspSorting toInternal(IOHSPSorting val) {
    log.trace("#toInternal(IOHSPSorting)");

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
