package mb.lib.util;

import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import mb.api.model.blast.*;
import mb.api.model.blast.impl.*;
import mb.api.model.io.JsonKeys;
import mb.lib.blast.*;
import mb.lib.blast.model.CompBasedStats;
import mb.lib.blast.model.IOHitSorting;
import org.veupathdb.lib.blast.*;

public class BlastConv
{
  public static BlastFormatter convertReportConfig(String json) {
    try {
      return JSON.parse(json, BlastFormatter.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static BlastConfig convertJobConfig(String json) {
    try {
      var tmp = JSON.parse(json, JsonNode.class);

      if (tmp.isArray())
        return convertLegacy((ArrayNode) tmp);
      if (!tmp.isObject())
        throw new RuntimeException("Invalid record JSON configuration.");
      if (!tmp.has(JsonKeys.Tool))
        throw new RuntimeException("Invalid record JSON configuration.  Tool field is missing.");

      return switch(BlastTool.fromString(tmp.get(JsonKeys.Tool).textValue())) {
        case BlastN -> convertNJSON((ObjectNode) tmp);
        case BlastP -> convertPJSON((ObjectNode) tmp);
        case BlastX -> convertXJSON((ObjectNode) tmp);
        case TBlastN -> convertTNJSON((ObjectNode) tmp);
        case TBlastX -> convertTXJSON((ObjectNode) tmp);
        default -> throw new RuntimeException("Unsupported blast tool.");
      };

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static IOBlastConfig convert(BlastConfig val) {
    return switch (val.getTool()) {
      case BlastN -> convert((BlastN) val);
      case BlastP -> convert((BlastP) val);
      case BlastX -> convert((BlastX) val);
      case TBlastN -> convert((TBlastN) val);
      case TBlastX -> convert((TBlastX) val);
      case DeltaBlast, PSIBlast, RPSBlast, RPSTBlastN, BlastFormatter -> throw new IllegalArgumentException();
    };
  }

  static BlastConfig convertLegacy(ArrayNode json) {
    return switch (BlastTool.fromString(json.get(0).get(0).textValue())) {
      case BlastN -> XBlastN.fromLegacyJSON(json);
      case BlastP -> XBlastP.fromLegacyJSON(json);
      case BlastX -> XBlastX.fromLegacyJSON(json);
      case TBlastN -> XTBlastN.fromLegacyJSON(json);
      case TBlastX -> XTBlastX.fromLegacyJSON(json);
      case DeltaBlast, PSIBlast, RPSBlast, RPSTBlastN, BlastFormatter -> throw new IllegalArgumentException();
    };
  }

  static BlastConfig convertNJSON(ObjectNode json) {
    return JSON.cast(json, BlastN.class);
  }

  static BlastConfig convertPJSON(ObjectNode json) {
    return JSON.cast(json, BlastP.class);
  }

  static BlastConfig convertXJSON(ObjectNode json) {
    return JSON.cast(json, BlastX.class);
  }

  static BlastConfig convertTNJSON(ObjectNode json) {
    return JSON.cast(json, TBlastN.class);
  }

  static BlastConfig convertTXJSON(ObjectNode json) {
    return JSON.cast(json, TBlastX.class);
  }

  /**
   * Converts an external blast config form to the internal type.
   *
   * @param bn External blast config to convert.
   *
   * @return Converted internal blast config.
   */
  public static BlastN convert(IOBlastnConfig bn) {
    var out = new BlastN();

    out.setQueryFile(bn.getQuery());
    out.setQueryLocation(bn.getQueryLoc());
    out.setExpectValue(bn.getEValue());
    out.setOutFormat(bn.getOutFormat().toInternalValue());
    out.setNumDescriptions(bn.getNumDescriptions());
    out.setNumAlignments(bn.getNumAlignments());
    out.setLineLength(bn.getLineLength());
    out.setSortHits(bn.getSortHits().toInternalValue());
    out.setSortHSPs(bn.getSortHSPs().toInternalValue());
    out.setLowercaseMasking(bn.getLcaseMasking());
    out.setQueryCoverageHSPPercent(bn.getQCovHSPPerc());
    out.setMaxHSPs(bn.getMaxHSPs());
    out.setMaxTargetSequences(bn.getMaxTargetSeqs());
    out.setDBSize(bn.getDbSize());
    out.setSearchSpace(bn.getSearchSpace());
    out.setExtensionDropoffUngapped(bn.getXDropUngap());
    out.setParseDefLines(bn.getParseDefLines());

    out.setStrand(bn.getStrand());
    out.setTask(bn.getTask());
    out.setWordSize(bn.getWordSize());
    out.setGapOpen(bn.getGapOpen());
    out.setGapExtend(bn.getGapExtend());
    out.setPenalty(bn.getPenalty());
    out.setReward(bn.getReward());
    out.setUseIndex(bn.getUseIndex());
    out.setIndexName(bn.getIndexName());
    out.setDust(bn.getDust());
    out.setWindowMaskerTaxID(bn.getWindowMaskerTaxid());
    out.setSoftMasking(bn.getSoftMasking());
    out.setTaxIDs(bn.getTaxIds().stream().map(String::valueOf).collect(Collectors.toList()));
    out.setNegativeTaxIDs(bn.getNegativeTaxIds().stream().map(String::valueOf).collect(Collectors.toList()));
    out.setDBSoftMask(bn.getDbSoftMask());
    out.setDBHardMask(bn.getDbHardMask());
    out.setPercentIdentity(bn.getPercIdentity());
    out.setCullingLimit(bn.getCullingLimit());
    out.setBestHitOverhang(bn.getBestHitOverhang());
    out.setBestHitScoreEdge(bn.getBestHitScoreEdge());
    out.setSubjectBestHit(bn.getSubjectBestHit());
    out.setTemplateType(bn.getTemplateType());
    out.setTemplateLength(bn.getTemplateLength());
    out.setSumStats(bn.getSumStats());
    out.setExtensionDropoffPrelimGapped(bn.getXDropGap());
    out.setExtensionDropoffFinalGapped(bn.getXDropGapFinal());
    out.setNonGreedy(bn.getNoGreedy());
    out.setMinRawGappedScore(bn.getMinRawGappedScore());
    out.setUngappedAlignmentsOnly(bn.getUngapped());
    out.setWindowSize(bn.getWindowSize());
    out.setOffDiagonalRange(bn.getOffDiagonalRange());

    return out;
  }

  public static IOBlastnConfig convert(BlastN bn) {
    var out = new IOBlastnConfigImpl();

    out.setQueryLoc(bn.getQueryLocation());
    out.setEValue(bn.getExpectValue());
    out.setOutFormat(IOBlastReportFormat.fromInternalValue(bn.getOutFormat()));
    out.setNumDescriptions(bn.getNumDescriptions());
    out.setNumAlignments(bn.getNumAlignments());
    out.setLineLength(bn.getLineLength());
    out.setSortHits(IOHitSorting.fromInternalValue(bn.getSortHits()));
    out.setSortHSPs(IOHSPSorting.fromInternalValue(bn.getSortHSPs()));
    out.setLcaseMasking(bn.getLowercaseMasking());
    out.setQCovHSPPerc(bn.getQueryCoverageHSPPercent());
    out.setMaxHSPs(bn.getMaxHSPs());
    out.setMaxTargetSeqs(bn.getMaxTargetSequences());
    out.setDbSize(bn.getDBSize());
    out.setSearchSpace(bn.getSearchSpace());
    out.setXDropUngap(bn.getExtensionDropoffUngapped());
    out.setParseDefLines(bn.getParseDefLines());
    out.setStrand(bn.getStrand());
    out.setTask(bn.getTask());
    out.setWordSize(bn.getWordSize());
    out.setGapOpen(bn.getGapOpen());
    out.setGapExtend(bn.getGapExtend());
    out.setPenalty(bn.getPenalty());
    out.setReward(bn.getReward());
    out.setUseIndex(bn.getUseIndex());
    out.setIndexName(bn.getIndexName());
    out.setDust(bn.getDust());
    out.setWindowMaskerTaxid(bn.getWindowMaskerTaxID());
    out.setSoftMasking(bn.getSoftMasking());
    out.setTaxIds(bn.getTaxIDs().stream().map(Integer::parseInt).collect(Collectors.toList()));
    out.setNegativeTaxIds(bn.getNegativeTaxIDs().stream().map(Integer::parseInt).collect(Collectors.toList()));
    out.setDbSoftMask(bn.getDBSoftMask());
    out.setDbHardMask(bn.getDBHardMask());
    out.setPercIdentity(bn.getPercentIdentity());
    out.setCullingLimit(bn.getCullingLimit());
    out.setBestHitOverhang(bn.getBestHitOverhang());
    out.setBestHitScoreEdge(bn.getBestHitScoreEdge());
    out.setSubjectBestHit(bn.getSubjectBestHit());
    out.setTemplateType(bn.getTemplateType());
    out.setTemplateLength(bn.getTemplateLength());
    out.setSumStats(bn.getSumStats());
    out.setXDropGap(bn.getExtensionDropoffPrelimGapped());
    out.setXDropGapFinal(bn.getExtensionDropoffFinalGapped());
    out.setNoGreedy(bn.getNonGreedy());
    out.setMinRawGappedScore(bn.getMinRawGappedScore());
    out.setUngapped(bn.getUngappedAlignmentsOnly());
    out.setWindowSize(bn.getWindowSize());
    out.setOffDiagonalRange(bn.getOffDiagonalRange());

    return out;
  }

  // ---------------------------------------------------------------------- //

  public static BlastP convert(IOBlastpConfig conf) {
    var out = new BlastP();

    out.setQueryFile(conf.getQuery());
    out.setQueryLocation(conf.getQueryLoc());
    out.setExpectValue(conf.getEValue());
    out.setOutFormat(conf.getOutFormat().toInternalValue());
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(conf.getSortHits().toInternalValue());
    out.setSortHSPs(conf.getSortHSPs().toInternalValue());
    out.setLowercaseMasking(conf.getLcaseMasking());
    out.setQueryCoverageHSPPercent(conf.getQCovHSPPerc());
    out.setMaxHSPs(conf.getMaxHSPs());
    out.setMaxTargetSequences(conf.getMaxTargetSeqs());
    out.setDBSize(conf.getDbSize());
    out.setSearchSpace(conf.getSearchSpace());
    out.setExtensionDropoffUngapped(conf.getXDropUngap());
    out.setParseDefLines(conf.getParseDefLines());

    out.setTask(conf.getTask());
    out.setWordSize(i2l(conf.getWordSize()));
    out.setGapOpen(conf.getGapOpen());
    out.setGapExtend(conf.getGapExtend());
    out.setMatrix(conf.getMatrix());
    out.setThreshold(conf.getThreshold());
    out.setCompBasedStats(String.valueOf(conf.getCompBasedStats().ordinal()));
    out.setSeg(conf.getSeg());
    out.setSoftMasking(conf.getSoftMasking());
    out.setTaxIDs(conf.getTaxIds().stream().map(String::valueOf).collect(Collectors.toList()));
    out.setNegativeTaxIDs(conf.getNegativeTaxIds().stream().map(String::valueOf).collect(Collectors.toList()));
    out.setDBSoftMask(conf.getDbSoftMask());
    out.setDBHardMask(conf.getDbHardMask());
    out.setCullingLimit(i2l(conf.getCullingLimit()));
    out.setBestHitOverhang(conf.getBestHitOverhang());
    out.setBestHitScoreEdge(conf.getBestHitScoreEdge());
    out.setSubjectBestHit(conf.getSubjectBestHit());
    out.setExtensionDropoffPrelimGapped(conf.getXDropGap());
    out.setExtensionDropoffFinalGapped(conf.getXDropGapFinal());
    out.setWindowSize(i2l(conf.getWindowSize()));
    out.setUngappedAlignmentsOnly(conf.getUngapped());
    out.setUseSmithWatermanTraceback(conf.getUseSWTraceback());

    return out;
  }

  public static IOBlastpConfig convert(BlastP bp) {
    var out = new IOBlastpConfigImpl();

    out.setQueryLoc(bp.getQueryLocation());
    out.setEValue(bp.getExpectValue());
    out.setOutFormat(IOBlastReportFormat.fromInternalValue(bp.getOutFormat()));
    out.setNumDescriptions(bp.getNumDescriptions());
    out.setNumAlignments(bp.getNumAlignments());
    out.setLineLength(bp.getLineLength());
    out.setSortHits(IOHitSorting.fromInternalValue(bp.getSortHits()));
    out.setSortHSPs(IOHSPSorting.fromInternalValue(bp.getSortHSPs()));
    out.setLcaseMasking(bp.getLowercaseMasking());
    out.setQCovHSPPerc(bp.getQueryCoverageHSPPercent());
    out.setMaxHSPs(bp.getMaxHSPs());
    out.setMaxTargetSeqs(bp.getMaxTargetSequences());
    out.setDbSize(bp.getDBSize());
    out.setSearchSpace(bp.getSearchSpace());
    out.setXDropUngap(bp.getExtensionDropoffUngapped());
    out.setParseDefLines(bp.getParseDefLines());
    out.setTask(bp.getTask());
    out.setWordSize(l2i(bp.getWordSize()));
    out.setGapOpen(bp.getGapOpen());
    out.setGapExtend(bp.getGapExtend());
    out.setMatrix(bp.getMatrix());
    out.setThreshold(bp.getThreshold());
    out.setCompBasedStats(CompBasedStats.fromValue(bp.getCompBasedStats()));
    out.setSeg(bp.getSeg());
    out.setSoftMasking(bp.getSoftMasking());
    out.setTaxIds(bp.getTaxIDs().stream().map(Integer::parseInt).collect(Collectors.toList()));
    out.setNegativeTaxIds(bp.getNegativeTaxIDs().stream().map(Integer::parseInt).collect(Collectors.toList()));
    out.setDbSoftMask(bp.getDBSoftMask());
    out.setDbHardMask(bp.getDBHardMask());
    out.setCullingLimit(l2i(bp.getCullingLimit()));
    out.setBestHitOverhang(bp.getBestHitOverhang());
    out.setBestHitScoreEdge(bp.getBestHitScoreEdge());
    out.setSubjectBestHit(bp.getSubjectBestHit());
    out.setXDropGap(bp.getExtensionDropoffPrelimGapped());
    out.setXDropGapFinal(bp.getExtensionDropoffFinalGapped());
    out.setUngapped(bp.getUngappedAlignmentsOnly());
    out.setUseSWTraceback(bp.getUseSmithWatermanTraceback());

    return out;
  }

  // ---------------------------------------------------------------------- //

  public static BlastX convert(IOBlastxConfig conf) {
    var out = new BlastX();

    out.setQueryFile(conf.getQuery());
    out.setQueryLocation(conf.getQueryLoc());
    out.setExpectValue(conf.getEValue());
    out.setOutFormat(conf.getOutFormat().toInternalValue());
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(conf.getSortHits().toInternalValue());
    out.setSortHSPs(conf.getSortHSPs().toInternalValue());
    out.setLowercaseMasking(conf.getLcaseMasking());
    out.setQueryCoverageHSPPercent(conf.getQCovHSPPerc());
    out.setMaxHSPs(conf.getMaxHSPs());
    out.setMaxTargetSequences(conf.getMaxTargetSeqs());
    out.setDBSize(conf.getDbSize());
    out.setSearchSpace(conf.getSearchSpace());
    out.setExtensionDropoffUngapped(conf.getXDropUngap());
    out.setParseDefLines(conf.getParseDefLines());

    out.setStrand(conf.getStrand());
    out.setQueryGenCode(conf.getQueryGeneticCode());
    out.setTask(conf.getTask());
    out.setWordSize(i2l(conf.getWordSize()));
    out.setGapOpen(conf.getGapOpen());
    out.setGapExtend(conf.getGapExtend());
    out.setMaxIntronLength(i2l(conf.getMaxIntronLength()));
    out.setMatrix(conf.getMatrix());
    out.setThreshold(conf.getThreshold());
    out.setCompBasedStats(String.valueOf(conf.getCompBasedStats().ordinal()));
    out.setSeg(conf.getSeg());
    out.setSoftMasking(conf.getSoftMasking());
    out.setTaxIDs(conf.getTaxIds().stream().map(String::valueOf).collect(Collectors.toList()));
    out.setNegativeTaxIDs(conf.getNegativeTaxIds().stream().map(String::valueOf).collect(Collectors.toList()));
    out.setDBSoftMask(conf.getDbSoftMask());
    out.setDBHardMask(conf.getDbHardMask());
    out.setCullingLimit(i2l(conf.getCullingLimit()));
    out.setBestHitOverhang(conf.getBestHitOverhang());
    out.setBestHitScoreEdge(conf.getBestHitScoreEdge());
    out.setSubjectBestHit(conf.getSubjectBestHit());
    out.setSumStats(conf.getSumStats());
    out.setExtensionDropoffPrelimGapped(conf.getXDropGap());
    out.setExtensionDropoffFinalGapped(conf.getXDropGapFinal());
    out.setWindowSize(i2l(conf.getWindowSize()));
    out.setUngappedAlignmentsOnly(conf.getUngapped());
    out.setUseSmithWatermanTraceback(conf.getUseSWTraceback());

    return out;
  }

  public static IOBlastxConfig convert(BlastX bx) {
    var out = new IOBlastxConfigImpl();

    out.setQueryLoc(bx.getQueryLocation());
    out.setEValue(bx.getExpectValue().toString());
    out.setOutFormat(IOBlastReportFormat.fromInternalValue(bx.getOutFormat()));
    out.setNumDescriptions(bx.getNumDescriptions());
    out.setNumAlignments(bx.getNumAlignments());
    out.setLineLength(bx.getLineLength());
    out.setSortHits(IOHitSorting.fromInternalValue(bx.getSortHits()));
    out.setSortHSPs(IOHSPSorting.fromInternalValue(bx.getSortHSPs()));
    out.setLcaseMasking(bx.getLowercaseMasking());
    out.setQCovHSPPerc(bx.getQueryCoverageHSPPercent());
    out.setMaxHSPs(bx.getMaxHSPs());
    out.setMaxTargetSeqs(bx.getMaxTargetSequences());
    out.setDbSize(bx.getDBSize());
    out.setSearchSpace(bx.getSearchSpace());
    out.setXDropUngap(bx.getExtensionDropoffUngapped());
    out.setParseDefLines(bx.getParseDefLines());
    out.setStrand(bx.getStrand());
    out.setQueryGeneticCode(bx.getQueryGenCode());
    out.setTask(bx.getTask());
    out.setWordSize(l2i(bx.getWordSize()));
    out.setGapOpen(bx.getGapOpen());
    out.setGapExtend(bx.getGapExtend());
    out.setMaxIntronLength(l2i(bx.getMaxIntronLength()));
    out.setMatrix(bx.getMatrix());
    out.setThreshold(bx.getThreshold());
    out.setCompBasedStats(CompBasedStats.fromValue(bx.getCompBasedStats()));
    out.setSeg(bx.getSeg());
    out.setSoftMasking(bx.getSoftMasking());
    out.setTaxIds(bx.getTaxIDs().stream().map(Integer::parseInt).collect(Collectors.toList()));
    out.setNegativeTaxIds(bx.getNegativeTaxIDs().stream().map(Integer::parseInt).collect(Collectors.toList()));
    out.setDbSoftMask(bx.getDBSoftMask());
    out.setDbHardMask(bx.getDBHardMask());
    out.setCullingLimit(l2i(bx.getCullingLimit()));
    out.setBestHitOverhang(bx.getBestHitOverhang());
    out.setBestHitScoreEdge(bx.getBestHitScoreEdge());
    out.setSubjectBestHit(bx.getSubjectBestHit());
    out.setSumStats(bx.getSumStats());
    out.setXDropGap(bx.getExtensionDropoffPrelimGapped());
    out.setXDropGapFinal(bx.getExtensionDropoffFinalGapped());
    out.setWindowSize(l2i(bx.getWindowSize()));
    out.setUngapped(bx.getUngappedAlignmentsOnly());
    out.setUseSWTraceback(bx.getUseSmithWatermanTraceback());

    return out;
  }

  // ---------------------------------------------------------------------- //

  public static TBlastN convert(IOTBlastnConfig conf) {
    var out = new TBlastN();

    out.setQueryFile(conf.getQuery());
    out.setQueryLocation(conf.getQueryLoc());
    out.setExpectValue(conf.getEValue());
    out.setOutFormat(conf.getOutFormat().toInternalValue());
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(conf.getSortHits().toInternalValue());
    out.setSortHSPs(conf.getSortHSPs().toInternalValue());
    out.setLowercaseMasking(conf.getLcaseMasking());
    out.setQueryCoverageHSPPercent(conf.getQCovHSPPerc());
    out.setMaxHSPs(conf.getMaxHSPs());
    out.setMaxTargetSequences(conf.getMaxTargetSeqs());
    out.setDBSize(conf.getDbSize());
    out.setSearchSpace(conf.getSearchSpace());
    out.setExtensionDropoffUngapped(conf.getXDropUngap());
    out.setParseDefLines(conf.getParseDefLines());

    out.setTask(conf.getTask());
    out.setWordSize(i2l(conf.getWordSize()));
    out.setGapOpen(conf.getGapOpen());
    out.setGapExtend(conf.getGapExtend());
    out.setDBGenCode(b2s(conf.getDbGencode()));
    out.setMaxIntronLength(i2l(conf.getMaxIntronLength()));
    out.setMatrix(conf.getMatrix());
    out.setThreshold(conf.getThreshold());
    out.setCompBasedStats(String.valueOf(conf.getCompBasedStats().ordinal()));
    out.setSeg(conf.getSeg());
    out.setSoftMasking(conf.getSoftMasking());
    out.setTaxIDs(conf.getTaxIds().stream().map(String::valueOf).collect(Collectors.toList()));
    out.setNegativeTaxIDs(conf.getNegativeTaxIds().stream().map(String::valueOf).collect(Collectors.toList()));
    out.setDBSoftMask(conf.getDbSoftMask());
    out.setDBHardMask(conf.getDbHardMask());
    out.setCullingLimit(i2l(conf.getCullingLimit()));
    out.setBestHitOverhang(conf.getBestHitOverhang());
    out.setBestHitScoreEdge(conf.getBestHitScoreEdge());
    out.setSubjectBestHit(conf.getSubjectBestHit());
    out.setSumStats(conf.getSumStats());
    out.setExtensionDropoffPrelimGapped(conf.getXDropGap());
    out.setExtensionDropoffFinalGapped(conf.getXDropGapFinal());
    out.setUngappedAlignmentsOnly(conf.getUngapped());
    out.setWindowSize(i2l(conf.getWindowSize()));
    out.setUseSmithWatermanTraceback(conf.getUseSWTraceback());

    return out;
  }

  public static IOTBlastnConfig convert(TBlastN b) {
    var out = new IOTBlastnConfigImpl();

    out.setQueryLoc(b.getQueryLocation());
    out.setEValue(b.getExpectValue());
    out.setOutFormat(IOBlastReportFormat.fromInternalValue(b.getOutFormat()));
    out.setNumDescriptions(b.getNumDescriptions());
    out.setNumAlignments(b.getNumAlignments());
    out.setLineLength(b.getLineLength());
    out.setSortHits(IOHitSorting.fromInternalValue(b.getSortHits()));
    out.setSortHSPs(IOHSPSorting.fromInternalValue(b.getSortHSPs()));
    out.setLcaseMasking(b.getLowercaseMasking());
    out.setQCovHSPPerc(b.getQueryCoverageHSPPercent());
    out.setMaxHSPs(b.getMaxHSPs());
    out.setMaxTargetSeqs(b.getMaxTargetSequences());
    out.setDbSize(b.getDBSize());
    out.setSearchSpace(b.getSearchSpace());
    out.setXDropUngap(b.getExtensionDropoffUngapped());
    out.setParseDefLines(b.getParseDefLines());
    out.setTask(b.getTask());
    out.setWordSize(l2i(b.getWordSize()));
    out.setGapOpen(b.getGapOpen());
    out.setGapExtend(b.getGapExtend());
    out.setDbGencode(s2b(b.getDBGenCode()));
    out.setMaxIntronLength(l2i(b.getMaxIntronLength()));
    out.setMatrix(b.getMatrix());
    out.setThreshold(b.getThreshold());
    out.setCompBasedStats(CompBasedStats.fromValue(b.getCompBasedStats()));
    out.setSeg(b.getSeg());
    out.setSoftMasking(b.getSoftMasking());
    out.setTaxIds(b.getTaxIDs().stream().map(Integer::parseInt).collect(Collectors.toList()));
    out.setNegativeTaxIds(b.getNegativeTaxIDs().stream().map(Integer::parseInt).collect(Collectors.toList()));
    out.setDbSoftMask(b.getDBSoftMask());
    out.setDbHardMask(b.getDBHardMask());
    out.setCullingLimit(l2i(b.getCullingLimit()));
    out.setBestHitOverhang(b.getBestHitOverhang());
    out.setBestHitScoreEdge(b.getBestHitScoreEdge());
    out.setSubjectBestHit(b.getSubjectBestHit());
    out.setSumStats(b.getSumStats());
    out.setXDropGap(b.getExtensionDropoffPrelimGapped());
    out.setXDropGapFinal(b.getExtensionDropoffFinalGapped());
    out.setUngapped(b.getUngappedAlignmentsOnly());
    out.setWindowSize(l2i(b.getWindowSize()));
    out.setUseSWTraceback(b.getUseSmithWatermanTraceback());

    return out;
  }

  // ---------------------------------------------------------------------- //

  public static TBlastX convert(IOTBlastxConfig conf) {
    var out = new TBlastX();

    out.setQueryFile(conf.getQuery());
    out.setQueryLocation(conf.getQueryLoc());
    out.setExpectValue(conf.getEValue());
    out.setOutFormat(conf.getOutFormat().toInternalValue());
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(conf.getSortHits().toInternalValue());
    out.setSortHSPs(conf.getSortHSPs().toInternalValue());
    out.setLowercaseMasking(conf.getLcaseMasking());
    out.setQueryCoverageHSPPercent(conf.getQCovHSPPerc());
    out.setMaxHSPs(conf.getMaxHSPs());
    out.setMaxTargetSequences(conf.getMaxTargetSeqs());
    out.setDBSize(conf.getDbSize());
    out.setSearchSpace(conf.getSearchSpace());
    out.setExtensionDropoffUngapped(conf.getXDropUngap());
    out.setParseDefLines(conf.getParseDefLines());
    out.setStrand(conf.getStrand());
    out.setQueryGenCode(b2s(conf.getQueryGeneticCode()));
    out.setWordSize(i2l(conf.getWordSize()));
    out.setMaxIntronLength(i2l(conf.getMaxIntronLength()));
    out.setMatrix(conf.getMatrix());
    out.setThreshold(conf.getThreshold());
    out.setDBGenCode(b2s(conf.getDbGencode()));
    out.setSeg(conf.getSeg());
    out.setSoftMasking(conf.getSoftMasking());
    out.setTaxIDs(conf.getTaxIds().stream().map(String::valueOf).collect(Collectors.toList()));
    out.setNegativeTaxIDs(conf.getNegativeTaxIds().stream().map(String::valueOf).collect(Collectors.toList()));
    out.setDBSoftMask(conf.getDbSoftMask());
    out.setDBHardMask(conf.getDbHardMask());
    out.setCullingLimit(i2l(conf.getCullingLimit()));
    out.setBestHitOverhang(conf.getBestHitOverhang());
    out.setBestHitScoreEdge(conf.getBestHitScoreEdge());
    out.setSubjectBestHit(conf.getSubjectBestHit());
    out.setSumStats(conf.getSumStats());
    out.setWindowSize(i2l(conf.getWindowSize()));

    return out;
  }

  public static IOTBlastxConfig convert(TBlastX b) {
    var out = new IOTBlastxConfigImpl();

    out.setQueryLoc(b.getQueryLocation());
    out.setEValue(b.getExpectValue());
    out.setOutFormat(IOBlastReportFormat.fromInternalValue(b.getOutFormat()));
    out.setNumDescriptions(b.getNumDescriptions());
    out.setNumAlignments(b.getNumAlignments());
    out.setLineLength(b.getLineLength());
    out.setSortHits(IOHitSorting.fromInternalValue(b.getSortHits()));
    out.setSortHSPs(IOHSPSorting.fromInternalValue(b.getSortHSPs()));
    out.setLcaseMasking(b.getLowercaseMasking());
    out.setQCovHSPPerc(b.getQueryCoverageHSPPercent());
    out.setMaxHSPs(b.getMaxHSPs());
    out.setMaxTargetSeqs(b.getMaxTargetSequences());
    out.setDbSize(b.getDBSize());
    out.setSearchSpace(b.getSearchSpace());
    out.setXDropUngap(b.getExtensionDropoffUngapped());
    out.setParseDefLines(b.getParseDefLines());
    out.setStrand(b.getStrand());
    out.setQueryGeneticCode(s2b(b.getQueryGenCode()));
    out.setWordSize(l2i(b.getWordSize()));
    out.setMaxIntronLength(l2i(b.getMaxIntronLength()));
    out.setMatrix(b.getMatrix());
    out.setThreshold(b.getThreshold());
    out.setDbGencode(s2b(b.getDBGenCode()));
    out.setSeg(b.getSeg());
    out.setSoftMasking(b.getSoftMasking());
    out.setTaxIds(b.getTaxIDs().stream().map(Integer::parseInt).collect(Collectors.toList()));
    out.setNegativeTaxIds(b.getNegativeTaxIDs().stream().map(Integer::parseInt).collect(Collectors.toList()));
    out.setDbSoftMask(b.getDBSoftMask());
    out.setDbHardMask(b.getDBHardMask());
    out.setCullingLimit(l2i(b.getCullingLimit()));
    out.setBestHitOverhang(b.getBestHitOverhang());
    out.setBestHitScoreEdge(b.getBestHitScoreEdge());
    out.setSubjectBestHit(b.getSubjectBestHit());
    out.setSumStats(b.getSumStats());
    out.setWindowSize(l2i(b.getWindowSize()));

    return out;
  }

  // ---------------------------------------------------------------------- //

  public static Long i2l(Integer in) {
    return in == null ? null : in.longValue();
  }

  public static Short b2s(Byte b) {
    return b == null ? null : b.shortValue();
  }

  public static Integer l2i(Long l) {
    return l == null ? null : l.intValue();
  }

  public static Byte s2b(Short v) {
    return v == null ? null : v.byteValue();
  }
}
