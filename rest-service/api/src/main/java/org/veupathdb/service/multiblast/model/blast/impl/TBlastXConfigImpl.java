package org.veupathdb.service.multiblast.model.blast.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.blast.impl.trait.*;
import org.veupathdb.service.multiblast.model.blast.tx.TBlastXConfig;
import org.veupathdb.service.multiblast.model.blast.tx.TBlastxScoringMatrix;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class TBlastXConfigImpl
  extends BlastConfigImpl<TBlastXConfig>
  implements TBlastXConfig
{
  private EStrand                              strand;
  private EQueryGenCode                        queryGenCode;
  private EWordSize                            wordSize;
  private EMaxIntronLength                     maxIntronLength;
  private EScoringMatrix<TBlastxScoringMatrix> matrix;
  private EDbGenCode                           dbGenCode;
  private ESubject                             subject;
  private ESeg                                 seg;
  private EGiList                              giList;
  private ESeqIdList                           seqIdList;
  private ETaxIds                              taxIds;
  private ETaxIdList                           taxIdList;
  private EDbMask                              dbMask;
  private ECullingLimit                        cullingLimit;
  private EBestHit                             bestHit;
  private ESumStats                            sumStats;

  @Override
  public Double getBestHitOverhang() {
    return lazy(bestHit, EBestHit::getBestHitOverhang);
  }

  @Override
  public Integer getCullingLimit() {
    return lazy(cullingLimit, ECullingLimit::getCullingLimit);
  }

  @Override
  public Byte getDbTranslationGeneticCode() {
    return lazy(dbGenCode, EDbGenCode::getDbTranslationGeneticCode);
  }

  @Override
  public String getDbSoftMaskAlgorithmId() {
    return lazy(dbMask, EDbMask::getDbSoftMaskAlgorithmId);
  }

  @Override
  public File getGenInfoIdListFile() {
    return lazy(giList, EGiList::getGenInfoIdListFile);
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
  public TBlastxScoringMatrix getScoringMatrix() {
    return lazy(matrix, EScoringMatrix::getScoringMatrix);
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
  public QueryStrand getStrand() {
    return lazy(strand, EStrand::getStrand);
  }

  @Override
  public File getSubjectFile() {
    return lazy(subject, ESubject::getSubjectFile);
  }

  @Override
  public File getTaxIdListFile() {
    return lazy(taxIdList, ETaxIdList::getTaxIdListFile);
  }

  @Override
  public int[] getTaxIds() {
    return lazy(taxIds, ETaxIds::getTaxIds);
  }

  @Override
  public Integer getWordSize() {
    return lazy(wordSize, EWordSize::getWordSize);
  }

  @Override
  public int[] getNegativeTaxIds() {
    return lazy(taxIds, ETaxIds::getNegativeTaxIds);
  }

  @Override
  public File getNegativeTaxIdListFile() {
    return lazy(taxIdList, ETaxIdList::getNegativeTaxIdListFile);
  }

  @Override
  public Location getSubjectLocation() {
    return lazy(subject, ESubject::getSubjectLocation);
  }

  @Override
  public File getNegativeSequenceIdListFile() {
    return lazy(seqIdList, ESeqIdList::getNegativeSequenceIdListFile);
  }

  @Override
  public Double getScoreThreshold() {
    return lazy(matrix, EScoringMatrix::getScoreThreshold);
  }

  @Override
  public File getNegativeGenInfoIdListFile() {
    return lazy(giList, EGiList::getNegativeGenInfoIdListFile);
  }

  @Override
  public String getDbHardMaskAlgorithmId() {
    return lazy(dbMask, EDbMask::getDbHardMaskAlgorithmId);
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
  public TBlastXConfig enableSubjectBestHit(boolean b) {
      (bestHit = lazy(bestHit, EBestHit::new)).enableSubjectBestHit(b);
    return this;
  }

  @Override
  public TBlastXConfig enableSumStatistics(Boolean b) {
    (sumStats = lazy(sumStats, ESumStats::new)).enableSumStatistics(b);
    return this;
  }

  @Override
  public Boolean isSumStatisticsEnabled() {
    return lazy(sumStats, ESumStats::isSumStatisticsEnabled);
  }

  @Override
  public TBlastXConfig setBestHitOverhang(Double v) {
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitOverhang(v);
    return this;
  }

  @Override
  public TBlastXConfig setBestHitScoreEdge(Double v) {
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitScoreEdge(v);
    return this;
  }

  @Override
  public TBlastXConfig setCullingLimit(Integer v) {
    (cullingLimit = lazy(cullingLimit, ECullingLimit::new)).setCullingLimit(v);
    return this;
  }

  @Override
  public TBlastXConfig setDbHardMaskAlgorithmId(String id) {
    (dbMask = lazy(dbMask, EDbMask::new)).setDbHardMaskAlgorithmId(id);
    return this;
  }

  @Override
  public TBlastXConfig setDbSoftMaskAlgorithmId(String id) {
    (dbMask = lazy(dbMask, EDbMask::new)).setDbSoftMaskAlgorithmId(id);
    return this;
  }

  @Override
  public TBlastXConfig setDbTranslationGeneticCode(Byte i) {
    (dbGenCode = lazy(dbGenCode, EDbGenCode::new)).setDbTranslationGeneticCode(i);
    return this;
  }

  @Override
  public TBlastXConfig setGenInfoIdListFile(File f) {
    (giList = lazy(giList, EGiList::new)).setGenInfoIdListFile(f);
    return this;
  }

  @Override
  public TBlastXConfig setMaxIntronLength(Integer len) {
    (maxIntronLength = lazy(maxIntronLength, EMaxIntronLength::new)).setMaxIntronLength(len);
    return this;
  }

  @Override
  public TBlastXConfig setNegativeGenInfoIdListFile(File f) {
    (giList = lazy(giList, EGiList::new)).setNegativeGenInfoIdListFile(f);
    return this;
  }

  @Override
  public TBlastXConfig setNegativeSequenceIDListFile(File f) {
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setNegativeSequenceIDListFile(f);
    return this;
  }

  @Override
  public TBlastXConfig setNegativeTaxIDListFile(File f) {
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setNegativeTaxIDListFile(f);
    return this;
  }

  @Override
  public TBlastXConfig setNegativeTaxIds(int[] negativeTaxIds) {

      (taxIds = lazy(taxIds, ETaxIds::new)).
        setNegativeTaxIds(Arrays.copyOf(negativeTaxIds, negativeTaxIds.length));

    return this;
  }

  @Override
  public TBlastXConfig setNegativeTaxIds(Collection<Integer> negativeTaxIds) {
      (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(new ArrayList<>(negativeTaxIds));
    return this;
  }

  @Override
  public TBlastXConfig setQueryTranslationGeneticCode(Byte i) {
    (queryGenCode = lazy(queryGenCode, EQueryGenCode::new)).setQueryTranslationGeneticCode(i);
    return this;
  }

  @Override
  public TBlastXConfig setScoreThreshold(Double d) {
    (matrix = lazy(matrix, EScoringMatrix::new)).setScoreThreshold(d);
    return this;
  }

  @Override
  public TBlastXConfig setScoringMatrix(TBlastxScoringMatrix matrix) {
    (this.matrix = lazy(this.matrix, EScoringMatrix::new)).setScoringMatrix(matrix);
    return this;
  }

  @Override
  public TBlastXConfig setSeg(Seg seg) {
    (this.seg = lazy(this.seg, ESeg::new)).setSeg(seg);
    return this;
  }

  @Override
  public TBlastXConfig setSequenceIDListFile(File f) {
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setSequenceIDListFile(f);
    return this;
  }

  @Override
  public TBlastXConfig setStrand(QueryStrand s) {
    (strand = lazy(strand, EStrand::new)).setStrand(s);
    return this;
  }

  @Override
  public TBlastXConfig setSubjectFile(File f) {
    (subject = lazy(subject, ESubject::new)).setSubjectFile(f);
    return this;
  }

  @Override
  public TBlastXConfig setSubjectLocation(Location loc) {
    (subject = lazy(subject, ESubject::new)).setSubjectLocation(loc);
    return this;
  }

  @Override
  public TBlastXConfig setTaxIDListFile(File f) {
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setTaxIDListFile(f);
    return this;
  }

  @Override
  public TBlastXConfig setTaxIDs(int[] taxIds) {
      (this.taxIds = lazy(this.taxIds, ETaxIds::new)).
        setTaxIDs(Arrays.copyOf(taxIds, taxIds.length));
    return this;
  }

  @Override
  public TBlastXConfig setTaxIDs(Collection<Integer> taxIds) {
      (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIDs(new ArrayList<>(taxIds));
    return this;
  }

  @Override
  public TBlastXConfig setWordSize(Integer size) {
      (wordSize = lazy(wordSize, EWordSize::new)).setWordSize(size);
    return this;
  }

  @Override
  public void toCli(CliBuilder cli) {
    super.toCli(cli);

    lazy(
      cli,
      strand,
      queryGenCode,
      wordSize,
      maxIntronLength,
      matrix,
      dbGenCode,
      subject,
      seg,
      giList,
      seqIdList,
      taxIds,
      taxIdList,
      dbMask,
      cullingLimit,
      bestHit,
      sumStats
    );
  }

  public static TBlastXConfig fromSerial(ArrayNode node) {
    var out  = new TBlastXConfigImpl();
    var size = node.size();

    for (var i = 1; i < size; i++) {
      var curr = node.get(i);

      switch (ToolOption.fromString(curr.get(0).asText())) {
        case BestHitOverhang -> out.setBestHitOverhang(curr.get(1).asDouble());
        case BestHitScoreEdge -> out.setBestHitScoreEdge(curr.get(1).asDouble());
        case BlastDatabase -> out.setDatabase(curr.get(1).asText());
        case CullingLimit -> out.setCullingLimit(curr.get(1).asInt());
        case EffectiveDatabaseSize -> out.setEffectiveDatabaseSize((byte) curr.get(1).asInt());
        case DatabaseHardMask -> out.setDbHardMaskAlgorithmId(curr.get(1).asText());
        case DatabaseSoftMask -> out.setDbSoftMaskAlgorithmId(curr.get(1).asText());
        case DatabaseTranslationGenCode -> out.setDbTranslationGeneticCode((byte) curr.get(1).asInt());
        case EntrezQuery -> out.setEntrezQuery(curr.get(1).asText());
        case ExpectValue -> out.setExpectValue(new BigDecimal(curr.get(1).asText()));
        case ExportSearchStrategy -> out.setSearchStrategyExportFile(new File(curr.get(1).asText()));
        case GIListFile -> out.setGenInfoIdListFile(new File(curr.get(1).asText()));
        case HTMLOutput -> out.enableHTMLOutput(curr.size() == 1 || curr.get(1).asBoolean());
        case Help -> out.enableHelp(curr.size() == 1 || curr.get(1).asBoolean());
        case ImportSearchStrategy -> out.setSearchStrategyImportFile(new File(curr.get(1).asText()));
        case LineLength -> out.setLineLength(curr.get(1).asInt());
        case LowercaseMasking -> out.enableLowercaseMasking(curr.size() == 1
          || curr.get(1).asBoolean());
        case MaxHSPs -> out.setMaxHSPs(curr.get(1).asInt());
        case MaxIntronLength -> out.setMaxIntronLength(curr.get(1).asInt());
        case MaxTargetSequences -> out.setMaxTargetSequences(curr.get(1).asInt());
        case MultiHitWindowSize -> out.setMultiHitWindowSize(curr.get(1).asInt());
        case NegativeGIListFile -> out.setNegativeGenInfoIdListFile(new File(curr.get(1).asText()));
        case NegativeSequenceIDListFile -> out.setNegativeSequenceIDListFile(new File(curr.get(1).asText()));
        case NegativeTaxonomyIDListFile -> out.setNegativeTaxIDListFile(new File(curr.get(1).asText()));
        case NegativeTaxonomyIDs -> out.setNegativeTaxIds(
          Arrays.stream(curr.get(1).asText().split(","))
            .mapToInt(Integer::parseInt)
            .toArray());
        case NumAlignments -> out.setNumAlignments(curr.get(1).asInt());
        case NumDescriptions -> out.setNumDescriptions(curr.get(1).asInt());
        case NumberOfThreads -> out.setThreadCount((byte) curr.get(1).asInt());
        case OutputFile -> out.setOutputFile(new File(curr.get(1).asText()));
        case OutputFormat -> out.setReportFormat(ReportFormatImpl.fromString(curr.get(1).asText()));
        case ParseDefLines -> out.enableDefLineParsing(curr.size() == 1 || curr.get(1).asBoolean());
        case Query -> out.setQuery(curr.get(1).asText());
        case QueryCoveragePercentHSP -> out.setQueryCoveragePercentHSP(curr.get(1).asDouble());
        case QueryGeneticCode -> out.setQueryTranslationGeneticCode((byte) curr.get(1).asInt());
        case QueryLocation -> out.setQueryLocation(LocationImpl.fromString(curr.get(1).asText()));
        case QueryStrand -> out.setStrand(QueryStrand.fromString(curr.get(1).asText()));
        case Remote -> out.enableRemoteSearchExecution(curr.size() == 1 || curr.get(1).asBoolean());
        case SEGFilter -> out.setSeg(SegImpl.fromString(curr.get(1).asText()));
        case ScoringMatrix -> out.setScoringMatrix(TBlastxScoringMatrix.unsafeFromString(curr.get(1).asText()));
        case SearchSpaceEffectiveLength -> out.setEffectiveSearchSpaceLength((byte) curr.get(1).asInt());
        case SequenceIDListFile -> out.setSequenceIDListFile(new File(curr.get(1).asText()));
        case ShowNCBIGIs -> out.enableNCBIGenInfoIds(curr.size() == 1 || curr.get(1).asBoolean());
        case SoftMasking -> out.enableSoftMasking(curr.size() == 1 || curr.get(1).asBoolean());
        case HitSorting -> out.setHitSorting(HitSorting.fromString(curr.get(1).asText()));
        case HSPSorting -> out.setHSPSorting(HspSorting.fromString(curr.get(1).asText()));
        case SubjectBestHit -> out.enableSubjectBestHit(curr.size() == 1
          || curr.get(1).asBoolean());
        case SubjectFile -> out.setSubjectFile(new File(curr.get(1).asText()));
        case SubjectLocation -> out.setSubjectLocation(LocationImpl.fromString(curr.get(1).asText()));
        case SumStats -> out.enableSumStatistics(curr.size() == 1 || curr.get(1).asBoolean());
        case TaxonomyIDListFile -> out.setTaxIDListFile(new File(curr.get(1).asText()));
        case TaxonomyIDs -> out.setTaxIDs(
          Arrays.stream(curr.get(1).asText().split(","))
            .mapToInt(Integer::parseInt)
            .toArray());
        case Threshold -> out.setScoreThreshold(curr.get(1).asDouble());
        case Version -> out.enableVersion(curr.size() == 1 || curr.get(1).asBoolean());
        case WordSize -> out.setWordSize(curr.get(1).asInt());
        case ExtensionDropoffUngapped -> out.setExtensionDropoffUngapped(curr.get(1).asDouble());
      }
    }

    return out;
  }
}
