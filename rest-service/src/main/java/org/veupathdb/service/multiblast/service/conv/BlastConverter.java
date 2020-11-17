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

    out.setCullingLimit(conf.getCullingLimit());
    out.setBestHitOverhang(conf.getBestHitOverhang());
    out.setBestHitScoreEdge(conf.getBestHitScoreEdge());
    out.setTask(fromInternal(conf.getTask()));
    out.setGapOpen(conf.getGapOpen());
    out.setGapExtend(conf.getGapExtend());
    out.setReward(conf.getReward());
    out.setPenalty(conf.getPenalty());
    out.setStrand(fromInternal(conf.getStrand()));
    out.setDust(fromInternal(conf.getDust()));
    out.setFilteringDb(conf.getFilteringDb()); // FIXME: file
    out.setWindowMaskerTaxid(conf.getWindowMaskerTaxID());
    out.setWindowMaskerDb(conf.getWindowMaskerDB()); // FIXME: file
    out.setPercIdentity(conf.getPercIdentity());
    out.setTemplateType(fromInternal(conf.getTemplateType()));
    out.setTemplateLength(conf.getTemplateLength());
    out.setUseIndex(conf.isUseIndexEnabled());
    out.setIndexName(conf.getIndexName());
    out.setXdropGap(conf.getExtDropoffPrelimGapped());
    out.setXdropGapFinal(conf.getExtDropoffFinalGapped());
    out.setNoGreedy(conf.isNonGreedyProgramExtEnabled());
    out.setMinRawGappedScore(conf.getMinRawGappedScore());
    out.setUngapped(conf.isUngappedAlignmentEnabled());

    reflectiveSet(out, conf);

    return out;
  }

  InputBlastpConfig _fromInternal(BlastPConfig conf) {
    var out = new InputBlastpConfigImpl();

    out.setTask(fromInternal(conf.getTask()));
    out.setIpgList(conf.getIpgList()); // FIXME: file, missing prop
    out.setNegativeIpgList(conf.getNegativeIpgList()); // FIXME: file, missing prop
    out.setGapOpen(conf.getGapOpen());
    out.setGapExtend(conf.getGapExtend());
    out.setXdropGap(conf.getxDropGap()); // FIXME: missing prop
    out.setXdropGapFinal(conf.getxDropGapFinal());
    out.setSeg(fromInternal(conf.getSeg()));
    out.setMatrix(fromInternal(conf.getMatrix()));
    out.setThreshold(conf.getThreshold());
    out.setCullingLimit(conf.getCullingLimit());
    out.setBestHitOverhang(conf.getBestHitOverhang());
    out.setBestHitScoreEdge(conf.getBestHitScoreEdge());
    out.setSubjectBestHit(conf.getSubjectBestHit());
    out.setUngapped(conf.getUngapped());
    out.setCompBasedStats(fromInternal(conf.getCompBasedStats()));
    out.setUseSwTBack(conf.getUseSmithWatermanAlignments());

    reflectiveSet(out, conf);

    return out;
  }

  InputBlastxConfig _fromInternal(BlastXConfig conf) {
    var out = new InputBlastxConfigImpl();
  }

  InputTBlastnConfig _fromInternal(TBlastNConfig conf) {
    var out = new InputTBlastnConfigImpl();
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
    return switch(val) {
      case BlastP -> InputBlastpTask.BLASTP;
      case BlastPFast -> InputBlastpTask.BLASTPFAST;
      case BlastPShort -> InputBlastpTask.BLASTPSHORT;
    };
  }

  InputHitSorting fromInternal(HitSorting val) {
    return switch(val) {
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

  void reflectiveSet(Object obj, BlastConfig conf) {
    try {
      var meth = Arrays.stream(obj.getClass().getDeclaredMethods())
        .collect(Collectors.toMap(
          Method::getName,
          Function.identity()
        ));

      if (conf instanceof StdBlastConfig)
        reflectiveSetStd(obj, meth, (StdBlastConfig) conf);
      else
        reflectiveSetBase(obj, meth, conf);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void reflectiveSetStd(Object obj, Map<String, Method> methods, StdBlastConfig conf)
  throws Exception {

  }

  void reflectiveSetBase(Object obj, Map<String, Method> methods, BlastConfig conf) throws Exception {
    // methods.get("setQuery").invoke(obj, conf.getQuery()); // Intentionally skipped.
    methods.get("setQueryLoc").invoke(obj, fromInternal(conf.getQueryLoc()));
    methods.get("setEValue").invoke(obj, conf.getExpectValue());
    methods.get("setLineLength").invoke(obj, conf.getLineLength());
    methods.get("setSortHits").invoke(obj, fromInternal(conf.getSortHits()));
    methods.get("setSortHSPs").invoke(obj, fromInternal(conf.getSortHsps()));
    methods.get("setQCovHSPPerc").invoke(obj, conf.getQueryCoveragePercentHSP());
    methods.get("setNumDescriptions").invoke(obj, conf.getNumDescriptions());
    methods.get("setNumAlignments").invoke(obj, conf.getNumAlignments());
    methods.get("setMaxTargetSeqs").invoke(obj, conf.getMaxTargetSequences());
    methods.get("setMaxHSPs").invoke(obj, conf.getMaxHSPs());
    methods.get("setMaxHSPs").invoke(obj, conf.getMaxHSPs());
    methods.get("setDbSize").invoke(obj, conf.getDbSize());
    methods.get("setSearchSpace").invoke(obj, conf.getSearchSpace());
    methods.get("setParseDefLines").invoke(obj, conf.isParseDefLinesEnabled());
    methods.get("setOutFmt").invoke(obj, fromInternal(conf.getOutFormat()));
    methods.get("setSoftMasking").invoke(obj, conf.getSoftMasking());
    methods.get("setLcaseMasking").invoke(obj, conf.isLowercaseMaskingEnabled());
    methods.get("setXdropUngap").invoke(obj, conf.getXDropUngap());
    methods.get("setWindowSize()").invoke(obj, conf.getWindowSize());
  }
}
