package org.veupathdb.service.multiblast.service.conv;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.*;

public class BlastConverter
{
  private static BlastConverter instance;

  private final Logger log;

  private BlastConverter() {
    log = LogManager.getLogger(getClass());
  }

  public static BlastConverter getInstance() {
    return instance;
  }

  public InputBlastConfig fromInternal(BlastConfig conf) {
    log.trace("#fromInternal(BlastConfig)");

    if (conf == null)
      return null;

    if (conf instanceof BlastNConfig)
      return _fromInternal((BlastNConfig) conf);
    if (conf instanceof BlastPConfig)
      return _fromInternal((BlastPConfig) conf);
    if (conf instanceof BlastXConfig)
      return _fromInternal((BlastXConfig) conf);
    if (conf instanceof TBlastNConfig)
      return _fromInternal((TBlastNConfig) conf);

  }

  InputBlastnConfig _fromInternal(BlastNConfig conf) {
    log.trace("#_fromInternal(BlastNConfig)");

    if (conf == null)
      return null;

    var out = new InputBlastnConfigImpl();

    // out.setQuery(conf.getQuery()); // Intentionally omitted.
    out.setQueryLoc(fromInternal(conf.getQueryLoc()));
    out.setEValue(conf.getExpectValue());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(fromInternal(conf.getSortHits()));
    out.setSeqIdList(conf.getSequenceIdList()); // FIXME: file
    out.setNegativeSeqIdList(conf.getNegativeSequenceIdList()); // FIXME: file
    out.setTaxIds(String.join(",", conf.getTaxIds()));
    out.setNegativeTaxIds(String.join(",", conf.getNegativeTaxIds()));
    out.setTaxIdList(conf.getTaxIdList()); // FIXME: file
    out.setNegativeTaxIdList(conf.getNegativeTaxIdList()); // FIXME: file
    out.setSortHSPs(fromInternal(conf.getSortHSPs()));
    out.setQCovHSPPerc(conf.getQueryCoveragePercentHSP());
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setMaxTargetSeqs(conf.getMaxTargetSequences());
    out.setMaxHSPs(conf.getMaxHSPs());
    out.setCullingLimit(conf.getCullingLimit());
    out.setBestHitOverhang(conf.getBestHitOverhang());
    out.setBestHitScoreEdge(conf.getBestHitScoreEdge());
    out.setDbSize(conf.getDbSize());
    out.setSearchSpace(conf.getSearchSpace());
    out.setParseDefLines(conf.isParseDefLinesEnabled());
    out.setOutFmt(fromInternal(conf.getOutFormat()));
    out.setTask(fromInternal(conf.getTask()));
    out.setWordSize(conf.getWordSize());
    out.setGapOpen(conf.getGapOpen());
    out.setGapExtend(conf.getGapExtend());
    out.setReward(conf.getReward());
    out.setPenalty(conf.getPenalty());
    out.setStrand(fromInternal(conf.getStrand()));
    out.setDust(fromInternal(conf.getDust()));
    out.setFilteringDb(conf.getFilteringDb()); // FIXME: file (is this public?)
    out.setWindowMaskerTaxid(conf.getWindowMaskerTaxID());
    out.setWindowMaskerDb(conf.getWindowMaskerDB()); // FIXME: file (is this public?)
    out.setSoftMasking(conf.getSoftMasking());
    out.setLcaseMasking(conf.isLowercaseMaskingEnabled());
    out.setDbSoftMask(conf.getDbSoftMask());
    out.setDbHardMask(conf.getDbHardMask());
    out.setPercIdentity(conf.getPercIdentity());
    out.setTemplateType(fromInternal(conf.getTemplateType()));
    out.setTemplateLength(conf.getTemplateLength());
    out.setUseIndex(conf.isUseIndexEnabled());
    out.setIndexName(conf.getIndexName());
    out.setXDropUngap(conf.getExtDropoffUngapped());
    out.setXDropGap(conf.getExtDropoffPrelimGapped());
    out.setXDropGapFinal(conf.getExtDropoffFinalGapped());
    out.setNoGreedy(conf.isNonGreedyProgramExtEnabled());
    out.setMinRawGappedScore(conf.getMinRawGappedScore());
    out.setUngapped(conf.isUngappedAlignmentEnabled());
    out.setWindowSize(conf.getWindowSize());

    return out;
  }

  InputBlastpConfig _fromInternal(BlastPConfig conf) {
    log.trace("#_fromInternal(BlastPConfig)");

    if (conf == null)
      return null;

    var out = new InputBlastpConfigImpl();

    // out.setQuery(conf.getQuery()); // Intentionally omitted.
    out.setQueryLoc(fromInternal(conf.getQueryLoc()));
    out.setTask(fromInternal(conf.getTask()));
    out.setEValue(conf.getExpectValue());
    out.setWordSize(conf.getWordSize());
    out.setGapOpen(conf.getGapOpen());
    out.setGapExtend(conf.getGapExtend());
    out.setMatrix(fromInternal(conf.getMatrix()));
    out.setThreshold(conf.getThreshold());
    out.setCompBasedStats(fromInternal(conf.getCompBasedStats()));

    out.setLineLength(conf.getLineLength());
    out.setSortHits(fromInternal(conf.getSortHits()));
    out.setSeqIdList(conf.getSequenceIdList()); // FIXME: file
    out.setNegativeSeqIdList(conf.getNegativeSequenceIdList()); // FIXME: file
    out.setTaxIds(String.join(",", conf.getTaxIds()));
    out.setNegativeTaxIds(String.join(",", conf.getNegativeTaxIds()));
    out.setTaxIdList(conf.getTaxIdList()); // FIXME: file
    out.setNegativeTaxIdList(conf.getNegativeTaxIdList()); // FIXME: file
    out.setSortHSPs(fromInternal(conf.getSortHSPs()));
    out.setQCovHSPPerc(conf.getQueryCoveragePercentHSP());
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setMaxTargetSeqs(conf.getMaxTargetSequences());
    out.setMaxHSPs(conf.getMaxHSPs());
    out.setCullingLimit(conf.getCullingLimit());
    out.setBestHitOverhang(conf.getBestHitOverhang());
    out.setBestHitScoreEdge(conf.getBestHitScoreEdge());
    out.setDbSize(conf.getDbSize());
    out.setSearchSpace(conf.getSearchSpace());
    out.setParseDefLines(conf.isParseDefLinesEnabled());
    out.setOutFmt(fromInternal(conf.getOutFormat()));
    out.setSeg(fromInternal(conf.getSeg()));
    out.setSoftMasking(conf.getSoftMasking());
    out.setLcaseMasking(conf.isLowercaseMaskingEnabled());
    out.setDbSoftMask(conf.getDbSoftMask());
    out.setDbHardMask(conf.getDbHardMask());
    out.setXDropGap(conf.getExtDropoffPrelimGap());
    out.setXDropGapFinal(conf.getExtDropoffFinalGap());
    out.setXDropUngap(conf.getExtDropoffUngapped());
    out.setWindowSize(conf.getWindowSize());
    out.setUseSwTBack(conf.getUseSmithWatermanAlignments());

    return out;
  }

  InputBlastxConfig _fromInternal(BlastXConfig conf) {
    var out = new InputBlastxConfigImpl();
  }

  InputTBlastnConfig _fromInternal(TBlastNConfig conf) {
    var out = new InputTBlastnConfigImpl();
  }

  InputBlastSegMask fromInternal(Seg val) {
    if (val == null)
      return null;

    var out = new InputBlastSegMaskImpl();

    out.setEnabled(true);
    out.setWindow(val.getWindow());
    out.setLocut(val.getLoCut());
    out.setHicut(val.getHiCut());

    return out;
  }

  InputBlastCompBasedStats fromInternal(CompBasedStats val) {
    if (val == null)
      return null;

    return switch (val) {
      case None -> InputBlastCompBasedStats.NONE;
      case CompBasedStats -> InputBlastCompBasedStats.COMPBASEDSTATS;
      case ConditionalScoreAdjustment -> InputBlastCompBasedStats.CONDITIONALCOMPBASEDSCOREADJUSTMENT;
      case UnconditionalScoreAdjustment -> InputBlastCompBasedStats.UNCONDITIONALCOMPBASEDSCOREADJUSTMENT;
    };
  }

  InputBlastnDust fromInternal(Dust val) {
    if (val == null)
      return null;

    var out = new InputBlastnDustImpl();

    out.setEnable(true);
    out.setLevel(val.getLevel());
    out.setLinker(val.getLinker());
    out.setWindow(val.getWindow());

    return out;
  }

  InputBlastOutFmt fromInternal(OutFormat fmt) {
    if (fmt == null)
      return null;

    var out = new InputBlastOutFmtImpl();

    out.setDelim(String.valueOf(fmt.getDelim()));
    out.setFields(fmt.getFields().stream().map(this::fromInternal).collect(Collectors.toList()));
    out.setFormat(fromInternal(fmt.getFormat()));

    return out;
  }

  InputBlastFormat fromInternal(ReportFormatType val) {
    if (val == null)
      return null;

    return switch (val) {
      case Pairwise -> InputBlastFormat.PAIRWISE;
      case QueryAnchoredWithIdentities -> InputBlastFormat.QUERYANCHOREDWITHIDENTITIES;
      case QueryAnchoredWithoutIdentities -> InputBlastFormat.QUERYANCHOREDWITHOUTIDENTITIES;
      case FlatQueryAnchoredWithIdentities -> InputBlastFormat.FLATQUERYANCHOREDWITHIDENTITIES;
      case FlatQueryAnchoredWithoutIdentities -> InputBlastFormat.FLATQUERYANCHOREDWITHOUTIDENTITIES;
      case XML -> InputBlastFormat.XML;
      case Tabular -> InputBlastFormat.TABULAR;
      case TabularWithComments -> InputBlastFormat.TABULARWITHCOMMENTS;
      case TextASN1 -> InputBlastFormat.TEXTASN_1;
      case BinaryASN1 -> InputBlastFormat.BINARYASN_1;
      case CSV -> InputBlastFormat.CSV;
      case ArchiveASN1 -> InputBlastFormat.ARCHIVEASN_1;
      case SeqAlignJSON -> InputBlastFormat.SEQALIGNJSON;
      case MultiFileJSON -> InputBlastFormat.MULTIFILEJSON;
      case MultiFileXML2 -> InputBlastFormat.MULTIFILEXML2;
      case SingleFileJSON -> InputBlastFormat.SINGLEFILEJSON;
      case SingleFileXML2 -> InputBlastFormat.SINGLEFILEXML2;
      case SAM -> InputBlastFormat.SAM;
      case OrganismReport -> InputBlastFormat.ORGANISMREPORT;
    };
  }

  InputBlastFmtField fromInternal(BlastReportField field) {
    return switch (field) {
      case QUERY_SEQUENCE_ID -> InputBlastFmtField.QSEQID;
      case QUERY_GI -> InputBlastFmtField.QGI;
      case QUERY_ACCESSION -> InputBlastFmtField.QACC;
      case QUERY_ACCESSION_VERSION -> InputBlastFmtField.QACCVER;
      case QUERY_SEQUENCE_LENGTH -> InputBlastFmtField.QLEN;
      case SUBJECT_SEQUENCE_ID -> InputBlastFmtField.SSEQID;
      case SUBJECT_ALL_SEQUENCE_ID -> InputBlastFmtField.SALLSEQID;
      case SUBJECT_GI -> InputBlastFmtField.SGI;
      case SUBJECT_ALL_GI -> InputBlastFmtField.SALLGI;
      case SUBJECT_ACCESSION -> InputBlastFmtField.SACC;
      case SUBJECT_ACCESSION_VERSION -> InputBlastFmtField.SACCVER;
      case SUBJECT_ALL_ACCESSION -> InputBlastFmtField.SALLACC;
      case SUBJECT_SEQUENCE_LENGTH -> InputBlastFmtField.SLEN;
      case QUERY_ALIGNMENT_START -> InputBlastFmtField.QSTART;
      case QUERY_ALIGNMENT_END -> InputBlastFmtField.QEND;
      case SUBJECT_ALIGNMENT_START -> InputBlastFmtField.SSTART;
      case SUBJECT_ALIGNMENT_END -> InputBlastFmtField.SEND;
      case QUERY_SEQUENCE -> InputBlastFmtField.QSEQ;
      case SUBJECT_SEQUENCE -> InputBlastFmtField.SSEQ;
      case EXPECT_VALUE -> InputBlastFmtField.EVALUE;
      case BIT_SCORE -> InputBlastFmtField.BITSCORE;
      case RAW_SCORE -> InputBlastFmtField.SCORE;
      case ALIGNMENT_LENGTH -> InputBlastFmtField.LENGTH;
      case PERCENT_IDENTICAL_MATCHES -> InputBlastFmtField.PIDENT;
      case NUMBER_IDENTICAL_MATCHES -> InputBlastFmtField.NIDENT;
      case NUMBER_MISMATCHES -> InputBlastFmtField.MISMATCH;
      case NUMBER_POSITIVE_MATCHES -> InputBlastFmtField.POSITIVE;
      case NUMBER_GAP_OPENINGS -> InputBlastFmtField.GAPOPEN;
      case NUMBER_GAPS -> InputBlastFmtField.GAPS;
      case PERCENT_POSITIVE_MATCHES -> InputBlastFmtField.PPOS;
      case FRAMES -> InputBlastFmtField.FRAMES;
      case QUERY_FRAME -> InputBlastFmtField.QFRAME;
      case SUBJECT_FRAME -> InputBlastFmtField.SFRAME;
      case BLAST_TRACEBACK_OPS -> InputBlastFmtField.BTOP;
      case SUBJECT_TAXONOMY_ID -> InputBlastFmtField.STAXID;
      case SUBJECT_SCIENTIFIC_NAME -> InputBlastFmtField.SSCINAME;
      case SUBJECT_COMMON_NAME -> InputBlastFmtField.SCOMNAME;
      case SUBJECT_BLAST_NAME -> InputBlastFmtField.SBLASTNAME;
      case SUBJECT_SUPER_KINGDOM -> InputBlastFmtField.SSKINGDOM;
      case SUBJECT_UNIQUE_TAXONOMY_IDS -> InputBlastFmtField.STAXIDS;
      case SUBJECT_SCIENTIFIC_NAMES -> InputBlastFmtField.SSCINAMES;
      case SUBJECT_COMMON_NAMES -> InputBlastFmtField.SCOMNAMES;
      case SUBJECT_BLAST_NAMES -> InputBlastFmtField.SBLASTNAMES;
      case SUBJECT_SUPER_KINGDOMS -> InputBlastFmtField.SSKINGDOMS;
      case SUBJECT_TITLE -> InputBlastFmtField.STITLE;
      case SUBJECT_ALL_TITLES -> InputBlastFmtField.SALLTITLES;
      case SUBJECT_STRAND -> InputBlastFmtField.SSTRAND;
      case QUERY_COVERAGE_PER_SUBJECT -> InputBlastFmtField.QCOVS;
      case QUERY_COVERAGE_PER_HSP -> InputBlastFmtField.QCOVHSP;
      case QUERY_COVERAGE_PER_UNIQUE_SUBJECT -> InputBlastFmtField.QCOVUS;
      case SQ -> InputBlastFmtField.SQ;
      case SR -> InputBlastFmtField.SR;
    };
  }

  InputBlastLocation fromInternal(QueryLocation loc) {
    var out = new InputBlastLocationImpl();
    out.setStart(loc.getStart());
    out.setStop(loc.getStop());
    return out;
  }

  InputBlastnTask fromInternal(BlastnTask val) {
    return switch (val) {
      case BlastN -> InputBlastnTask.BLASTN;
      case BlastNShort -> InputBlastnTask.BLASTNSHORT;
      case DiscontiguousMegablast -> InputBlastnTask.DCMEGABLAST;
      case Megablast -> InputBlastnTask.MEGABLAST;
      case RMBlastN -> throw new RuntimeException("rmblastn is currently disallowed.");
    };
  }

  InputBlastpTask fromInternal(BlastpTask val) {
    return switch (val) {
      case BlastP -> InputBlastpTask.BLASTP;
      case BlastPFast -> InputBlastpTask.BLASTPFAST;
      case BlastPShort -> InputBlastpTask.BLASTPSHORT;
    };
  }

  InputHitSorting fromInternal(HitSorting val) {
    return switch (val) {
      case BY_EXPECT_VALUE -> InputHitSorting.BYEVAL;
      case BY_BIT_SCORE -> InputHitSorting.BYBITSCORE;
      case BY_TOTAL_SCORE -> InputHitSorting.BYTOTALSCORE;
      case BY_PERCENT_IDENTITY -> InputHitSorting.BYPERCENTIDENTITY;
      case BY_QUERY_COVERAGE -> InputHitSorting.BYQUERYCOVERAGE;
    };
  }

  InputHSPSorting fromInternal(HspSorting val) {
    return switch (val) {
      case BY_EXPECT_VALUE -> InputHSPSorting.BYHSPEVALUE;
      case BY_SCORE -> InputHSPSorting.BYHSPSCORE;
      case BY_QUERY_START -> InputHSPSorting.BYHSPQUERYSTART;
      case BY_PERCENT_IDENTITY -> InputHSPSorting.BYHSPPERCENTIDENTITY;
      case BY_SUBJECT_START -> InputHSPSorting.BYHSPSUBJECTSTART;
    };
  }

  InputBlastStrand fromInternal(QueryStrand val) {
    if (val == null)
      return null;

    return switch (val) {
      case BOTH -> InputBlastStrand.BOTH;
      case MINUS -> InputBlastStrand.MINUS;
      case PLUS -> InputBlastStrand.PLUS;
    };
  }

  InputBlastDcTemplateType fromInternal(TemplateType val) {
    if (val == null)
      return null;

    return switch (val) {
      case Coding -> InputBlastDcTemplateType.CODING;
      case Optimal -> InputBlastDcTemplateType.OPTIMAL;
      case Both -> InputBlastDcTemplateType.BOTH;
    };
  }

  InputBlastpScoringMatrix fromInternal(BlastpScoringMatrix val) {
    if (val == null)
      return null;

    return switch (val) {
      case Blosum45 -> InputBlastpScoringMatrix.BLOSUM45;
      case Blosum50 -> InputBlastpScoringMatrix.BLOSUM50;
      case Blosum62 -> InputBlastpScoringMatrix.BLOSUM62;
      case Blosum80 -> InputBlastpScoringMatrix.BLOSUM80;
      case Blosum90 -> InputBlastpScoringMatrix.BLOSUM90;
      case Pam30 -> InputBlastpScoringMatrix.PAM30;
      case Pam70 -> InputBlastpScoringMatrix.PAM70;
      case Pam250 -> InputBlastpScoringMatrix.PAM250;
      case Identity -> InputBlastpScoringMatrix.IDENTITY;
    };
  }
}
