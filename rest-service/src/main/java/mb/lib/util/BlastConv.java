package mb.lib.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import mb.api.model.IOJobTarget;
import mb.api.model.IOJobTargetImpl;
import mb.api.model.blast.*;
import mb.api.model.blast.impl.*;
import mb.api.model.io.JsonKeys;
import mb.lib.blast.*;
import mb.lib.blast.model.CompBasedStats;
import mb.lib.blast.model.IOHSPSorting;
import mb.lib.blast.model.IOHitSorting;
import mb.lib.model.EmptyBlastConfig;
import mb.lib.query.model.JobTarget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.blast.*;
import org.veupathdb.lib.blast.field.HSPSorting;
import org.veupathdb.lib.blast.field.HitSorting;
import org.veupathdb.lib.blast.field.OutFormat;
import org.veupathdb.lib.blast.util.JSONObjectDecoder;

public class BlastConv
{
  private static final Logger Log = LogManager.getLogger(BlastConv.class);

  public static BlastFormatter convertReportConfig(String json) {
    Log.trace("::convertReportConfig(json={})", json);
    try {
      return JSON.parse(json, BlastFormatter.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static BlastConfig convertJobConfig(String json) {
    Log.trace("::convertJobConfig(json={})", json);
    try {
      return convertJobConfig(JSON.parse(json, JsonNode.class));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static BlastConfig convertJobConfig(JsonNode json) {
    Log.trace("::convertJobConfig(json={})", json);
    if (json.isArray())
      return convertLegacy((ArrayNode) json);
    if (!json.isObject())
      throw new RuntimeException("Invalid record JSON configuration.");
    if (!json.has(JsonKeys.Tool))
      throw new RuntimeException("Invalid record JSON configuration.  Tool field is missing.");

    return switch(BlastTool.fromString(json.get(JsonKeys.Tool).textValue())) {
      case BlastN -> convertNJSON((ObjectNode) json);
      case BlastP -> convertPJSON((ObjectNode) json);
      case BlastX -> convertXJSON((ObjectNode) json);
      case TBlastN -> convertTNJSON((ObjectNode) json);
      case TBlastX -> convertTXJSON((ObjectNode) json);
      default -> throw new RuntimeException("Unsupported blast tool.");
    };
  }

  public static IOJobTarget convert(JobTarget tgt) {
    Log.trace("::convert(tgt={})", tgt);
    return new IOJobTargetImpl()
      .organism(tgt.getOrganism())
      .target(tgt.getTarget());
  }

  public static JobTarget[] convert(Collection<IOJobTarget> tgts) {
    Log.trace("::convert(tgts={})", tgts);
    return tgts.stream()
      .map(BlastConv::convert)
      .toArray(JobTarget[]::new);
  }

  public static JobTarget convert(IOJobTarget tgt) {
    Log.trace("::convert(tgt={})", tgt);
    return new JobTarget()
      .setOrganism(tgt.organism())
      .setTarget(tgt.target());
  }

  public static IOBlastConfig convert(BlastConfig val) {
    Log.trace("::convert(val={})", val);

    if (val instanceof EmptyBlastConfig)
      return JSON.cast(val, IOBlastConfigImpl.class);

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
    Log.trace("::convertLegacy(json={})", json);
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
    Log.trace("::convertNJSON(json=...)");
    return BlastN.fromJSON(new JSONObjectDecoder(json));
  }

  static BlastConfig convertPJSON(ObjectNode json) {
    Log.trace("::convertPJSON(json=...)");
    return BlastP.fromJSON(new JSONObjectDecoder(json));
  }

  static BlastConfig convertXJSON(ObjectNode json) {
    Log.trace("::convertXJSON(json=...)");
    return BlastX.fromJSON(new JSONObjectDecoder(json));
  }

  static BlastConfig convertTNJSON(ObjectNode json) {
    Log.trace("::convertTNJSON(json=...)");
    return TBlastN.fromJSON(new JSONObjectDecoder(json));
  }

  static BlastConfig convertTXJSON(ObjectNode json) {
    Log.trace("::convertTXJSON(json=...)");
    return TBlastX.fromJSON(new JSONObjectDecoder(json));
  }

  public static BlastQueryConfig convert(IOBlastConfig conf) {
    Log.trace("::convert(conf={})", conf);
    return switch (conf.getTool()) {
      case BlastN -> convert((IOBlastnConfig) conf);
      case BlastP -> convert((IOBlastpConfig) conf);
      case BlastX -> convert((IOBlastxConfig) conf);
      case TBlastN -> convert((IOTBlastnConfig) conf);
      case TBlastX -> convert((IOTBlastxConfig) conf);
      case DeltaBlast, PSIBlast, RPSBlast, RPSTBlastN, BlastFormatter -> throw new IllegalArgumentException();
    };
  }

  /**
   * Converts an external blast config form to the internal type.
   *
   * @param bn External blast config to convert.
   *
   * @return Converted internal blast config.
   */
  static BlastN convert(IOBlastnConfig bn) {
    Log.trace("::convert(bn=...)");

    var out = new XBlastN();

    out.setQueryFile(bn.getQuery());
    out.setQueryLocation(bn.getQueryLoc());
    out.setExpectValue(bn.getEValue());
    out.setOutFormat(convert(bn.getOutFormat()));
    out.setNumDescriptions(bn.getNumDescriptions());
    out.setNumAlignments(bn.getNumAlignments());
    out.setLineLength(bn.getLineLength());
    out.setSortHits(convert(bn.getSortHits()));
    out.setSortHSPs(convert(bn.getSortHSPs()));
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
    out.setTaxIDs(convertTaxIDsToInternal(bn.getTaxIds()));
    out.setNegativeTaxIDs(convertTaxIDsToInternal(bn.getNegativeTaxIds()));
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

  static IOBlastnConfig convert(BlastN bn) {
    Log.trace("::convert(bn=...)");
    var out = new IOBlastnConfigImpl();

    out.setQueryLoc(bn.getQueryLocation());
    out.setEValue(bn.getExpectValue() == null ? null : bn.getExpectValue().value());
    out.setOutFormat(convert(bn.getOutFormat()));
    out.setNumDescriptions(bn.getNumDescriptions());
    out.setNumAlignments(bn.getNumAlignments());
    out.setLineLength(bn.getLineLength());
    out.setSortHits(convert(bn.getSortHits()));
    out.setSortHSPs(convert(bn.getSortHSPs()));
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
    out.setTaxIds(convertTaxIDsToExternal(bn.getTaxIDs()));
    out.setNegativeTaxIds(convertTaxIDsToExternal(bn.getNegativeTaxIDs()));
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

  static BlastP convert(IOBlastpConfig conf) {
    Log.trace("::convert(conf=...)");
    var out = new XBlastP();

    out.setQueryFile(conf.getQuery());
    out.setQueryLocation(conf.getQueryLoc());
    out.setExpectValue(conf.getEValue());
    out.setOutFormat(convert(conf.getOutFormat()));
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(convert(conf.getSortHits()));
    out.setSortHSPs(convert(conf.getSortHSPs()));
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
    out.setCompBasedStats(convertCBS(conf.getCompBasedStats()));
    out.setSeg(conf.getSeg());
    out.setSoftMasking(conf.getSoftMasking());
    out.setTaxIDs(convertTaxIDsToInternal(conf.getTaxIds()));
    out.setNegativeTaxIDs(convertTaxIDsToInternal(conf.getNegativeTaxIds()));
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

  static IOBlastpConfig convert(BlastP bp) {
    Log.trace("::convert(bp=...)");
    var out = new IOBlastpConfigImpl();

    out.setQueryLoc(bp.getQueryLocation());
    out.setEValue(bp.getExpectValue());
    out.setOutFormat(convert(bp.getOutFormat()));
    out.setNumDescriptions(bp.getNumDescriptions());
    out.setNumAlignments(bp.getNumAlignments());
    out.setLineLength(bp.getLineLength());
    out.setSortHits(convert(bp.getSortHits()));
    out.setSortHSPs(convert(bp.getSortHSPs()));
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
    out.setCompBasedStats(convertCBS(bp.getCompBasedStats()));
    out.setSeg(bp.getSeg());
    out.setSoftMasking(bp.getSoftMasking());
    out.setTaxIds(convertTaxIDsToExternal(bp.getTaxIDs()));
    out.setNegativeTaxIds(convertTaxIDsToExternal(bp.getNegativeTaxIDs()));
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

  static BlastX convert(IOBlastxConfig conf) {
    Log.trace("::convert(conf=...)");
    var out = new XBlastX();

    out.setQueryFile(conf.getQuery());
    out.setQueryLocation(conf.getQueryLoc());
    out.setExpectValue(conf.getEValue());
    out.setOutFormat(convert(conf.getOutFormat()));
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(convert(conf.getSortHits()));
    out.setSortHSPs(convert(conf.getSortHSPs()));
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
    out.setCompBasedStats(convertCBS(conf.getCompBasedStats()));
    out.setSeg(conf.getSeg());
    out.setSoftMasking(conf.getSoftMasking());
    out.setTaxIDs(convertTaxIDsToInternal(conf.getTaxIds()));
    out.setNegativeTaxIDs(convertTaxIDsToInternal(conf.getNegativeTaxIds()));
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

  static IOBlastxConfig convert(BlastX bx) {
    Log.trace("::convert(bx=...)");
    var out = new IOBlastxConfigImpl();

    out.setQueryLoc(bx.getQueryLocation());
    out.setEValue(bx.getExpectValue());
    out.setOutFormat(convert(bx.getOutFormat()));
    out.setNumDescriptions(bx.getNumDescriptions());
    out.setNumAlignments(bx.getNumAlignments());
    out.setLineLength(bx.getLineLength());
    out.setSortHits(convert(bx.getSortHits()));
    out.setSortHSPs(convert(bx.getSortHSPs()));
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
    out.setCompBasedStats(convertCBS(bx.getCompBasedStats()));
    out.setSeg(bx.getSeg());
    out.setSoftMasking(bx.getSoftMasking());
    out.setTaxIds(convertTaxIDsToExternal(bx.getTaxIDs()));
    out.setNegativeTaxIds(convertTaxIDsToExternal(bx.getNegativeTaxIDs()));
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

  static TBlastN convert(IOTBlastnConfig conf) {
    Log.trace("::convert(conf=...)");
    var out = new XTBlastN();

    out.setQueryFile(conf.getQuery());
    out.setQueryLocation(conf.getQueryLoc());
    out.setExpectValue(conf.getEValue());
    out.setOutFormat(convert(conf.getOutFormat()));
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(convert(conf.getSortHits()));
    out.setSortHSPs(convert(conf.getSortHSPs()));
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
    out.setCompBasedStats(convertCBS(conf.getCompBasedStats()));
    out.setSeg(conf.getSeg());
    out.setSoftMasking(conf.getSoftMasking());
    out.setTaxIDs(convertTaxIDsToInternal(conf.getTaxIds()));
    out.setNegativeTaxIDs(convertTaxIDsToInternal(conf.getNegativeTaxIds()));
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

  static IOTBlastnConfig convert(TBlastN b) {
    Log.trace("::convert(b=...)");
    var out = new IOTBlastnConfigImpl();

    out.setQueryLoc(b.getQueryLocation());
    out.setEValue(b.getExpectValue());
    out.setOutFormat(convert(b.getOutFormat()));
    out.setNumDescriptions(b.getNumDescriptions());
    out.setNumAlignments(b.getNumAlignments());
    out.setLineLength(b.getLineLength());
    out.setSortHits(convert(b.getSortHits()));
    out.setSortHSPs(convert(b.getSortHSPs()));
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
    out.setCompBasedStats(convertCBS(b.getCompBasedStats()));
    out.setSeg(b.getSeg());
    out.setSoftMasking(b.getSoftMasking());
    out.setTaxIds(convertTaxIDsToExternal(b.getTaxIDs()));
    out.setNegativeTaxIds(convertTaxIDsToExternal(b.getNegativeTaxIDs()));
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

  static TBlastX convert(IOTBlastxConfig conf) {
    Log.trace("::convert(conf=...)");
    var out = new XTBlastX();

    out.setQueryFile(conf.getQuery());
    out.setQueryLocation(conf.getQueryLoc());
    out.setExpectValue(conf.getEValue());
    out.setOutFormat(convert(conf.getOutFormat()));
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(convert(conf.getSortHits()));
    out.setSortHSPs(convert(conf.getSortHSPs()));
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
    out.setTaxIDs(convertTaxIDsToInternal(conf.getTaxIds()));
    out.setNegativeTaxIDs(convertTaxIDsToInternal(conf.getNegativeTaxIds()));
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

  static IOTBlastxConfig convert(TBlastX b) {
    Log.trace("::convert(b=...)");
    var out = new IOTBlastxConfigImpl();

    out.setQueryLoc(b.getQueryLocation());
    out.setEValue(b.getExpectValue());
    out.setOutFormat(convert(b.getOutFormat()));
    out.setNumDescriptions(b.getNumDescriptions());
    out.setNumAlignments(b.getNumAlignments());
    out.setLineLength(b.getLineLength());
    out.setSortHits(convert(b.getSortHits()));
    out.setSortHSPs(convert(b.getSortHSPs()));
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
    out.setTaxIds(convertTaxIDsToExternal(b.getTaxIDs()));
    out.setNegativeTaxIds(convertTaxIDsToExternal(b.getNegativeTaxIDs()));
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

  static CompBasedStats convertCBS(String in) { return in == null ? null : CompBasedStats.fromValue(in); }
  static String convertCBS(CompBasedStats in) { return in == null ? null : in.toString(); }

  static IOBlastReportFormat convert(OutFormat in) { return in == null ? null : IOBlastReportFormat.fromInternalValue(in); }
  static OutFormat convert(IOBlastReportFormat in) { return in == null ? null : in.toInternalValue(); }

  static IOHitSorting convert(HitSorting in) { return in == null ? null : IOHitSorting.fromInternalValue(in); }
  static HitSorting convert(IOHitSorting in) { return in == null ? null : in.toInternalValue(); }

  static IOHSPSorting convert(HSPSorting in) { return in == null ? null : IOHSPSorting.fromInternalValue(in); }
  static HSPSorting convert(IOHSPSorting in) { return in == null ? null : in.toInternalValue(); }

  private static List<Integer> convertTaxIDsToExternal(List<String> in) {
    return null2List(in).stream()
      .map(Integer::parseInt)
      .collect(Collectors.toList());
  }

  private static List<String> convertTaxIDsToInternal(List<Integer> in) {
    return null2List(in).stream()
      .map(String::valueOf)
      .collect(Collectors.toList());
  }

  private static Long i2l(Integer in) {
    Log.trace("::i2l(in={})", in);
    return in == null ? null : in.longValue();
  }

  private static Short b2s(Byte b) {
    Log.trace("::b2s(b={})", b);
    return b == null ? null : b.shortValue();
  }

  private static Integer l2i(Long l) {
    Log.trace("::l2i(l={})", l);
    return l == null ? null : l.intValue();
  }

  private static Byte s2b(Short v) {
    Log.trace("::s2b(v={})", v);
    return v == null ? null : v.byteValue();
  }

  private static <T> List<T> null2List(List<T> val) {
    Log.trace("::null2List(val={})", val);
    return val == null ? Collections.emptyList() : val;
  }
}
