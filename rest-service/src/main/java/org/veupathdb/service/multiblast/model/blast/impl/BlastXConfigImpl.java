package org.veupathdb.service.multiblast.model.blast.impl;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.blast.impl.trait.*;
import org.veupathdb.service.multiblast.model.blast.x.BlastxConfig;
import org.veupathdb.service.multiblast.model.blast.x.BlastxScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.x.BlastxTask;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class BlastXConfigImpl
  extends BlastConfigImpl<BlastxConfig>
  implements BlastxConfig
{
  private BlastxTask                          task;
  private EStrand                             strand;
  private EQueryGenCode                       queryGenCode;
  private EWordSize                           wordSize;
  private EGapCost                            gapCost;
  private EMaxIntronLength                    maxIntronLength;
  private EScoringMatrix<BlastxScoringMatrix> scoringMatrix;
  private ECompBasedStats                     compBasedStats;
  private ESubject                            subject;
  private ESeg                                seg;
  private EGiList                             giList;
  private ESeqIdList                          seqIdList;
  private ETaxIds                             taxIds;
  private ETaxIdList                          taxIdList;
  private EIpgList                            ipgList;
  private EDbMask                             dbMask;
  private ECullingLimit                       cullingLimit;
  private EBestHit                            bestHit;
  private ESumStats                           sumStats;
  private EGapExtDrop                         xDrop;
  private EUngapped                           ungapped;
  private ESWTback                            sw;

  @Override
  public BlastxTask getTask() {
    return task;
  }

  @Override
  public BlastXConfigImpl setTask(BlastxTask task) {
    this.task = task;
    return this;
  }

  @Override
  public Double getBestHitOverhang() {
    return lazy(bestHit, EBestHit::getBestHitOverhang);
  }

  @Override
  public Double getBestHitScoreEdge() {
    return lazy(bestHit, EBestHit::getBestHitScoreEdge);
  }

  @Override
  public boolean isSubjectBestHitEnabled() {
    return bestHit != null && bestHit.isSubjectBestHitEnabled();
  }

  @Override
  public CompBasedStats getCompBasedStatisticsType() {
    return lazy(compBasedStats, ECompBasedStats::getCompBasedStatisticsType);
  }

  @Override
  public Integer getCullingLimit() {
    return lazy(cullingLimit, ECullingLimit::getCullingLimit);
  }

  @Override
  public String getDbSoftMaskAlgorithmId() {
    return lazy(dbMask, EDbMask::getDbSoftMaskAlgorithmId);
  }

  @Override
  public String getDbHardMaskAlgorithmId() {
    return lazy(dbMask, EDbMask::getDbHardMaskAlgorithmId);
  }

  @Override
  public Integer getGapCostOpen() {
    return lazy(gapCost, EGapCost::getGapCostOpen);
  }

  @Override
  public Integer getGapCostExtend() {
    return lazy(gapCost, EGapCost::getGapCostExtend);
  }

  @Override
  public Double getExtensionDropoffPreliminaryGapped() {
    return lazy(xDrop, EGapExtDrop::getExtensionDropoffPreliminaryGapped);
  }

  @Override
  public Double getExtensionDropoffFinalGapped() {
    return lazy(xDrop, EGapExtDrop::getExtensionDropoffFinalGapped);
  }

  @Override
  public File getGenInfoIdListFile() {
    return lazy(giList, EGiList::getGenInfoIdListFile);
  }

  @Override
  public File getNegativeGenInfoIdListFile() {
    return lazy(giList, EGiList::getNegativeGenInfoIdListFile);
  }

  @Override
  public File getIdenticalProteinGroupListFile() {
    return lazy(ipgList, EIpgList::getIdenticalProteinGroupListFile);
  }

  @Override
  public File getNegativeIdenticalProteinGroupListFile() {
    return lazy(ipgList, EIpgList::getNegativeIdenticalProteinGroupListFile);
  }

  @Override
  public Integer getMaxIntronLength() {
    return lazy(maxIntronLength, EMaxIntronLength::getMaxIntronLength);
  }

  @Override
  public Byte getQueryTranslationGeneticCode() {
    return lazy(queryGenCode, EQueryGenCode::getQueryTranslationGeneticCode);
  }

  @Override
  public BlastxScoringMatrix getScoringMatrix() {
    return lazy(scoringMatrix, EScoringMatrix::getScoringMatrix);
  }

  @Override
  public Double getScoreThreshold() {
    return lazy(scoringMatrix, EScoringMatrix::getScoreThreshold);
  }

  @Override
  public Seg getSeg() {
    return lazy(seg, ESeg::getSeg);
  }

  @Override
  public File getSequenceIdListFile() {
    return lazy(seqIdList, ESeqIdList::getSequenceIdListFile);
  }

  @Override
  public File getNegativeSequenceIdListFile() {
    return lazy(seqIdList, ESeqIdList::getNegativeSequenceIdListFile);
  }

  @Override
  public boolean isSmithWatermanTracebackEnabled() {
    return sw != null && sw.isSmithWatermanTracebackEnabled();
  }

  @Override
  public QueryStrand getStrand() {
    return lazy(strand, EStrand::getStrand);
  }

  @Override
  public File getSubjectFile() {
    return lazy(subject, ESubject::getSubjectFile);
  }

  @Override
  public Location getSubjectLocation() {
    return lazy(subject, ESubject::getSubjectLocation);
  }

  @Override
  public File getTaxIdListFile() {
    return lazy(taxIdList, ETaxIdList::getTaxIdListFile);
  }

  @Override
  public File getNegativeTaxIdListFile() {
    return lazy(taxIdList, ETaxIdList::getNegativeTaxIdListFile);
  }

  @Override
  public int[] getTaxIds() {
    return lazy(taxIds, ETaxIds::getTaxIds);
  }

  @Override
  public int[] getNegativeTaxIds() {
    return lazy(taxIds, ETaxIds::getNegativeTaxIds);
  }

  @Override
  public boolean isUngappedAlignmentOnlyEnabled() {
    return ungapped != null && ungapped.isUngappedAlignmentOnlyEnabled();
  }

  @Override
  public Integer getWordSize() {
    return lazy(wordSize, EWordSize::getWordSize);
  }

  @Override
  public BlastxConfig enableSmithWatermanTraceback(boolean b) {
    (sw = lazy(sw, ESWTback::new)).enableSmithWatermanTraceback(b);
    return this;
  }

  @Override
  public BlastxConfig enableSubjectBestHit(boolean b) {
    (bestHit = lazy(bestHit, EBestHit::new)).enableSubjectBestHit(b);
    return this;
  }

  @Override
  public BlastxConfig enableSumStatistics(Boolean b) {
    (sumStats = lazy(sumStats, ESumStats::new)).enableSumStatistics(b);
    return this;
  }

  @Override
  public BlastxConfig enableUngappedAlignmentOnly(boolean b) {
    (ungapped = lazy(ungapped, EUngapped::new)).enableUngappedAlignmentOnly(b);
    return this;
  }

  @Override
  public Boolean isSumStatisticsEnabled() {
    return lazy(sumStats, ESumStats::isSumStatisticsEnabled);
  }

  @Override
  public BlastxConfig setBestHitOverhang(Double v) {
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitOverhang(v);
    return this;
  }

  @Override
  public BlastxConfig setBestHitScoreEdge(Double v) {
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitScoreEdge(v);
    return this;
  }

  @Override
  public BlastxConfig setCompBasedStatisticsType(CompBasedStats c) {
    (compBasedStats = lazy(compBasedStats, ECompBasedStats::new)).setCompBasedStatisticsType(c);
    return this;
  }

  @Override
  public BlastxConfig setCullingLimit(Integer v) {
    (cullingLimit = lazy(cullingLimit, ECullingLimit::new)).setCullingLimit(v);
    return this;
  }

  @Override
  public BlastxConfig setDbHardMaskAlgorithmId(String id) {
    (dbMask = lazy(dbMask, EDbMask::new)).setDbHardMaskAlgorithmId(id);
    return this;
  }

  @Override
  public BlastxConfig setDbSoftMaskAlgorithmId(String id) {
    (dbMask = lazy(dbMask, EDbMask::new)).setDbSoftMaskAlgorithmId(id);
    return this;
  }

  @Override
  public BlastxConfig setExtensionDropoffFinalGapped(Double d) {
    (xDrop = lazy(xDrop, EGapExtDrop::new)).setExtensionDropoffFinalGapped(d);
    return this;
  }

  @Override
  public BlastxConfig setGapCostExtend(Integer cost) {
    (gapCost = lazy(gapCost, EGapCost::new)).setGapCostExtend(cost);
    return this;
  }

  @Override
  public BlastxConfig setGapCostOpen(Integer cost) {
    (gapCost = lazy(gapCost, EGapCost::new)).setGapCostOpen(cost);
    return this;
  }

  @Override
  public BlastxConfig setGenInfoIdListFile(File f) {
    (giList = lazy(giList, EGiList::new)).setGenInfoIdListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setIdenticalProteinGroupListFile(File ipgList) {
    (this.ipgList = lazy(this.ipgList, EIpgList::new)).setIdenticalProteinGroupListFile(ipgList);
    return this;
  }

  @Override
  public BlastxConfig setMaxIntronLength(Integer len) {
    (maxIntronLength = lazy(maxIntronLength, EMaxIntronLength::new)).setMaxIntronLength(len);
    return this;
  }

  @Override
  public BlastxConfig setNegativeGenInfoIdListFile(File f) {
    (giList = lazy(giList, EGiList::new)).setNegativeGenInfoIdListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setNegativeIdenticalProteinGroupListFile(File negIpgList) {
    (ipgList = lazy(ipgList, EIpgList::new)).setNegativeIdenticalProteinGroupListFile(negIpgList);
    return this;
  }

  @Override
  public BlastxConfig setNegativeSequenceIDListFile(File f) {
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setNegativeSequenceIDListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setNegativeTaxIDListFile(File f) {
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setNegativeTaxIDListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setNegativeTaxIds(int[] negativeTaxIds) {
    (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : Arrays.copyOf(negativeTaxIds, negativeTaxIds.length)
    );
    return this;
  }

  @Override
  public BlastxConfig setNegativeTaxIds(Collection<Integer> negativeTaxIds) {
    (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : new ArrayList<>(negativeTaxIds)
    );
    return this;
  }

  @Override
  public BlastxConfig setExtensionDropoffPreliminaryGapped(Double d) {
    (xDrop = lazy(xDrop, EGapExtDrop::new)).setExtensionDropoffPreliminaryGapped(d);
    return this;
  }

  @Override
  public BlastxConfig setQueryTranslationGeneticCode(Byte i) {
    (queryGenCode = lazy(queryGenCode, EQueryGenCode::new)).setQueryTranslationGeneticCode(i);
    return this;
  }

  @Override
  public BlastxConfig setScoreThreshold(Double d) {
    (scoringMatrix = lazy(scoringMatrix, EScoringMatrix::new)).setScoreThreshold(d);
    return this;
  }

  @Override
  public BlastxConfig setScoringMatrix(BlastxScoringMatrix matrix) {
    (scoringMatrix = lazy(scoringMatrix, EScoringMatrix::new)).setScoringMatrix(matrix);
    return this;
  }

  @Override
  public BlastxConfig setSeg(Seg seg) {
    (this.seg = lazy(this.seg, ESeg::new)).setSeg(seg);
    return this;
  }

  @Override
  public BlastxConfig setSequenceIDListFile(File f) {
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setSequenceIDListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setStrand(QueryStrand s) {
    (strand = lazy(strand, EStrand::new)).setStrand(s);
    return this;
  }

  @Override
  public BlastxConfig setSubjectFile(File f) {
    (subject = lazy(subject, ESubject::new)).setSubjectFile(f);
    return this;
  }

  @Override
  public BlastxConfig setSubjectLocation(Location loc) {
    (subject = lazy(subject, ESubject::new)).setSubjectLocation(loc);
    return this;
  }

  @Override
  public BlastxConfig setTaxIDListFile(File f) {
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setTaxIDListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setTaxIDs(int[] taxIds) {
    (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIDs(
      taxIds == null ? null : Arrays.copyOf(taxIds, taxIds.length)
    );
    return this;
  }

  @Override
  public BlastxConfig setTaxIDs(Collection<Integer> taxIds) {
    (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIDs(
      taxIds == null ? null : new ArrayList<>(taxIds)
    );
    return this;
  }

  @Override
  public BlastxConfig setWordSize(Integer size) {
    (wordSize = lazy(wordSize, EWordSize::new)).setWordSize(size);
    return this;
  }

  @Override
  public void toCli(CliBuilder cli) {
    super.toCli(cli);

    cli.appendNonNull(ToolOption.Task, task);

    lazy(cli,
      strand,
      queryGenCode,
      wordSize,
      gapCost,
      maxIntronLength,
      scoringMatrix,
      compBasedStats,
      subject,
      seg,
      giList,
      seqIdList,
      taxIds,
      taxIdList,
      ipgList,
      dbMask,
      cullingLimit,
      bestHit,
      sumStats,
      xDrop,
      ungapped,
      sw
    );
  }

  public static BlastxConfig fromSerial(ArrayNode node) {
    var out = new BlastXConfigImpl();
    var size = node.size();

    for (var i = 1; i < size; i++) {
      var curr = node.get(i);

      switch (ToolOption.fromString(curr.get(0).asText())) {
        case BestHitOverhang
          -> out.setBestHitOverhang(curr.get(1).asDouble());
        case BestHitScoreEdge
          -> out.setBestHitScoreEdge(curr.get(1).asDouble());
        case BlastDatabase
          -> out.setDatabase(curr.get(1).asText());
        case CompositionBasedStats
          -> out.setCompBasedStatisticsType(CompBasedStats.fromValue(curr.get(1).asText()));
        case CullingLimit
          -> out.setCullingLimit(curr.get(1).asInt());
        case EffectiveDatabaseSize
          -> out.setEffectiveDatabaseSize((byte) curr.get(1).asInt());
        case DatabaseHardMask
          -> out.setDbHardMaskAlgorithmId(curr.get(1).asText());
        case DatabaseSoftMask
          -> out.setDbSoftMaskAlgorithmId(curr.get(1).asText());
        case EntrezQuery
          -> out.setEntrezQuery(curr.get(1).asText());
        case ExpectValue
          -> out.setExpectValue(new BigDecimal(curr.get(1).asText()));
        case ExportSearchStrategy
          -> out.setSearchStrategyExportFile(new File(curr.get(1).asText()));
        case GapCostExtend -> out.setGapCostExtend(curr.get(1).asInt());
        case GapCostOpen -> out.setGapCostOpen(curr.get(1).asInt());
        case Help -> out.enableHelp(curr.size() == 1 || curr.get(1).asBoolean());
        case HTMLOutput -> out.enableHTMLOutput(curr.size() == 1 || curr.get(1).asBoolean());
        case ImportSearchStrategy
          -> out.setSearchStrategyImportFile(new File(curr.get(1).asText()));
        case IdenticalProteinGroupListFile
          -> out.setIdenticalProteinGroupListFile(new File(curr.get(1).asText()));
        case LineLength -> out.setLineLength(curr.get(1).asInt());
        case LowercaseMasking
          -> out.enableLowercaseMasking(curr.size() == 1 || curr.get(1).asBoolean());
        case MaxHSPs -> out.setMaxHSPs(curr.get(1).asInt());
        case MaxTargetSequences -> out.setMaxTargetSequences(curr.get(1).asInt());
        case MaxIntronLength -> out.setMaxIntronLength(curr.get(1).asInt());
        case MultiHitWindowSize -> out.setMultiHitWindowSize(curr.get(1).asInt());
        case NegativeGIListFile -> out.setNegativeGenInfoIdListFile(new File(curr.get(1).asText()));
        case NegativeIdenticalProteinGroupListFile
          -> out.setNegativeIdenticalProteinGroupListFile(new File(curr.get(1).asText()));
        case NegativeSequenceIDListFile
          -> out.setNegativeSequenceIDListFile(new File(curr.get(1).asText()));
        case NegativeTaxonomyIDs
          -> out.setNegativeTaxIds(Arrays.stream(curr.get(1).asText().split(","))
          .mapToInt(Integer::parseInt)
          .toArray());
        case NegativeTaxonomyIDListFile
          -> out.setNegativeTaxIDListFile(new File(curr.get(1).asText()));
        case NumAlignments -> out.setNumAlignments(curr.get(1).asInt());
        case NumberOfThreads -> out.setThreadCount((byte) curr.get(1).asInt());
        case NumDescriptions -> out.setNumDescriptions(curr.get(1).asInt());
        case OutputFile -> out.setOutputFile(new File(curr.get(1).asText()));
        case OutputFormat -> out.setReportFormat(ReportFormatImpl.fromString(curr.get(1).asText()));
        case ParseDefLines -> out.enableDefLineParsing(curr.size() == 1 || curr.get(1).asBoolean());
        case Query -> out.setQuery(curr.get(1).asText());
        case QueryCoveragePercentHSP -> out.setQueryCoveragePercentHSP(curr.get(1).asDouble());
        case QueryGeneticCode -> out.setQueryTranslationGeneticCode((byte) curr.get(1).asInt());
        case QueryLocation -> out.setQueryLocation(LocationImpl.fromString(curr.get(1).asText()));
        case Remote -> out.enableRemoteSearchExecution(curr.size() == 1 || curr.get(1).asBoolean());
        case ScoringMatrix
          -> out.setScoringMatrix(BlastxScoringMatrix.unsafeFromString(curr.get(1).asText()));
        case SearchSpaceEffectiveLength
          -> out.setEffectiveSearchSpaceLength((byte) curr.get(1).asInt());
        case SEGFilter -> out.setSeg(SegImpl.fromString(curr.get(1).asText()));
        case SequenceIDListFile -> out.setSequenceIDListFile(new File(curr.get(1).asText()));
        case ShowNCBIGIs -> out.enableNCBIGenInfoIds(curr.size() == 1 || curr.get(1).asBoolean());
        case SoftMasking -> out.enableSoftMasking(curr.size() == 1 || curr.get(1).asBoolean());
        case HSPSorting -> out.setHitSorting(HitSorting.fromString(curr.get(1).asText()));
        case HitSorting -> out.setHSPSorting(HspSorting.fromString(curr.get(1).asText()));
        case QueryStrand -> out.setStrand(QueryStrand.fromString(curr.get(1).asText()));
        case SubjectBestHit
          -> out.enableSubjectBestHit(curr.size() == 1 || curr.get(1).asBoolean());
        case SubjectFile -> out.setSubjectFile(new File(curr.get(1).asText()));
        case SubjectLocation
          -> out.setSubjectLocation(LocationImpl.fromString(curr.get(1).asText()));
        case SumStats -> out.enableSumStatistics(curr.size() == 1 || curr.get(1).asBoolean());
        case Task -> out.setTask(BlastxTask.fromString(curr.get(1).asText()));
        case TaxonomyIDs
          -> out.setTaxIDs(Arrays.stream(curr.get(1).asText().split(","))
          .mapToInt(Integer::parseInt)
          .toArray());
        case TaxonomyIDListFile -> out.setTaxIDListFile(new File(curr.get(1).asText()));
        case Threshold -> out.setScoreThreshold(curr.get(1).asDouble());
        case UngappedAlignmentOnly
          -> out.enableUngappedAlignmentOnly(curr.size() == 1 || curr.get(1).asBoolean());
        case UseSmithWatermanAlignments
          -> out.enableSmithWatermanTraceback(curr.size() == 1 || curr.get(1).asBoolean());
        case Version -> out.enableVersion(curr.size() == 1 || curr.get(1).asBoolean());
        case WordSize -> out.setWordSize(curr.get(1).asInt());
        case ExtensionDropoffFinalGapped
          -> out.setExtensionDropoffFinalGapped(curr.get(1).asDouble());
        case ExtensionDropoffPrelimGapped
          -> out.setExtensionDropoffPreliminaryGapped(curr.get(1).asDouble());
        case ExtensionDropoffUngapped
          -> out.setExtensionDropoffUngapped(curr.get(1).asDouble());
      }
    }

    return out;
  }
}
