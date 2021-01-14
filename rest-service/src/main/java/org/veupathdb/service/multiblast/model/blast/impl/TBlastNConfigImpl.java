package org.veupathdb.service.multiblast.model.blast.impl;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastnConfig;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastNScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastNTask;
import org.veupathdb.service.multiblast.model.blast.impl.trait.*;

/**
 * Represents the CLI configuration specifically for the {@code tblastn} NCBI
 * Blast+ tool.
 */
public class TBlastNConfigImpl
  extends BlastConfigImpl<TBlastnConfig>
  implements TBlastnConfig
{
  /**
   * <pre>
   * -task <String, Permissible values: 'tblastn' 'tblastn-fast' >
   *    Task to execute
   *    Default = `tblastn'
   * </pre>
   */
  private TBlastNTask task;

  /**
   * <pre>
   * -word_size <Integer, >=2>
   *    Word size for wordfinder algorithm
   * </pre>
   */
  private EWordSize wordSize;

  /**
   * <pre>
   * -gapopen <Integer>
   *    Cost to open a gap
   * -gapextend <Integer>
   *    Cost to extend a gap
   * </pre>
   */
  private EGapCost gapCost;

  /**
   * <pre>
   * -db_gencode <Integer, values between: 1-6, 9-16, 21-31, 33>
   *    Genetic code to use to translate database/subjects (see user manual for
   *    details)
   *    Default = `1'
   * </pre>
   */
  private EDbGenCode dbGenCode;

  /**
   * <pre>
   * -max_intron_length <Integer, >=0>
   *    Length of the largest intron allowed in a translated nucleotide sequence
   *    when linking multiple distinct alignments
   *    Default = `0'
   * </pre>
   */
  private EMaxIntronLength maxIntronLength;

  /**
   * <pre>
   * -matrix <String>
   *    Scoring matrix name (normally BLOSUM62)
   * -threshold <Real, >=0>
   *    Minimum word score such that the word is added to the BLAST lookup table
   * </pre>
   */
  private EScoringMatrix<TBlastNScoringMatrix> matrix;

  /**
   * <pre>
   * -comp_based_stats <String>
   *    Use composition-based statistics:
   *        D or d: default (equivalent to 2 )
   *        0 or F or f: No composition-based statistics
   *        1: Composition-based statistics as in NAR 29:2994-3005, 2001
   *        2 or T or t : Composition-based score adjustment as in Bioinformatics
   *    21:902-911,
   *        2005, conditioned on sequence properties
   *        3: Composition-based score adjustment as in Bioinformatics 21:902-911,
   *        2005, unconditionally
   *    Default = `2'
   * </pre>
   */
  private ECompBasedStats compBasedStats;

  /**
   * <pre>
   * -subject <File_In>
   *    Subject sequence(s) to search
   *     * Incompatible with:  db, gilist, seqidlist, negative_gilist,
   *    negative_seqidlist, taxids, taxidlist, negative_taxids, negative_taxidlist,
   *    db_soft_mask, db_hard_mask
   * -subject_loc <String>
   *    Location on the subject sequence in 1-based offsets (Format: start-stop)
   *     * Incompatible with:  db, gilist, seqidlist, negative_gilist,
   *    negative_seqidlist, taxids, taxidlist, negative_taxids, negative_taxidlist,
   *    db_soft_mask, db_hard_mask, remote
   * </pre>
   */
  private ESubject subject;

  /**
   * <pre>
   * -seg <String>
   *    Filter query sequence with SEG (Format: 'yes', 'window locut hicut', or
   *    'no' to disable)
   *    Default = `12 2.2 2.5'
   * </pre>
   */
  private ESeg seg;

  /**
   * <pre>
   * -gilist <String>
   *    Restrict search of database to list of GIs
   *     * Incompatible with:  seqidlist, taxids, taxidlist, negative_gilist,
   *    negative_seqidlist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   * -negative_gilist <String>
   *    Restrict search of database to everything except the specified GIs
   *     * Incompatible with:  gilist, seqidlist, taxids, taxidlist,
   *    negative_seqidlist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   * </pre>
   */
  private EGiList giList;

  /**
   * <pre>
   * -seqidlist <String>
   *    Restrict search of database to list of SeqIDs
   *     * Incompatible with:  gilist, taxids, taxidlist, negative_gilist,
   *    negative_seqidlist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   * -negative_seqidlist <String>
   *    Restrict search of database to everything except the specified SeqIDs
   *     * Incompatible with:  gilist, seqidlist, taxids, taxidlist,
   *    negative_gilist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   * </pre>
   */
  private ESeqIdList seqIdList;

  /**
   * <pre>
   * -taxids <String>
   *    Restrict search of database to include only the specified taxonomy IDs
   *    (multiple IDs delimited by ',')
   *     * Incompatible with:  gilist, seqidlist, taxidlist, negative_gilist,
   *    negative_seqidlist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   * -negative_taxids <String>
   *    Restrict search of database to everything except the specified taxonomy IDs
   *    (multiple IDs delimited by ',')
   *     * Incompatible with:  gilist, seqidlist, taxids, taxidlist,
   *    negative_gilist, negative_seqidlist, negative_taxidlist, remote, subject,
   *    subject_loc
   * </pre>
   */
  private ETaxIds taxIds;

  /**
   * <pre>
   * -taxidlist <String>
   *    Restrict search of database to include only the specified taxonomy IDs
   *     * Incompatible with:  gilist, seqidlist, taxids, negative_gilist,
   *    negative_seqidlist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   * -negative_taxidlist <String>
   *    Restrict search of database to everything except the specified taxonomy IDs
   *     * Incompatible with:  gilist, seqidlist, taxids, taxidlist,
   *    negative_gilist, negative_seqidlist, negative_taxids, remote, subject,
   *    subject_loc
   * </pre>
   */
  private ETaxIdList taxIdList;

  /**
   * <pre>
   * -db_soft_mask <String>
   *    Filtering algorithm ID to apply to the BLAST database as soft masking
   *     * Incompatible with:  db_hard_mask, subject, subject_loc
   * -db_hard_mask <String>
   *    Filtering algorithm ID to apply to the BLAST database as hard masking
   *     * Incompatible with:  db_soft_mask, subject, subject_loc
   * </pre>
   */
  private EDbMask dbMask;

  /**
   * <pre>
   * -culling_limit <Integer, >=0>
   *    If the query range of a hit is enveloped by that of at least this many
   *    higher-scoring hits, delete the hit
   *     * Incompatible with:  best_hit_overhang, best_hit_score_edge
   * </pre>
   */
  private ECullingLimit cullingLimit;

  /**
   * <pre>
   * -best_hit_overhang <Real, (>0 and <0.5)>
   *    Best Hit algorithm overhang value (recommended value: 0.1)
   *     * Incompatible with:  culling_limit
   * -best_hit_score_edge <Real, (>0 and <0.5)>
   *    Best Hit algorithm score edge value (recommended value: 0.1)
   *     * Incompatible with:  culling_limit
   * -subject_besthit
   *    Turn on best hit per subject sequence
   * </pre>
   */
  private EBestHit bestHit;

  /**
   * <pre>
   * -sum_stats <Boolean>
   *    Use sum statistics
   * </pre>
   */
  private ESumStats sumStats;

  /**
   * <pre>
   * -xdrop_gap <Real>
   *    X-dropoff value (in bits) for preliminary gapped extensions
   * -xdrop_gap_final <Real>
   *    X-dropoff value (in bits) for final gapped alignment
   * </pre>
   */
  private EGapExtDrop xDrop;

  /**
   * <pre>
   * -ungapped
   *    Perform ungapped alignment only?
   * </pre>
   */
  private EUngapped ungapped;

  /**
   * <pre>
   * -use_sw_tback
   *    Compute locally optimal Smith-Waterman alignments?
   * </pre>
   */
  private ESWTback sw;

  /**
   * <pre>
   * -in_pssm <File_In>
   *    PSI-TBLASTN checkpoint file
   *     * Incompatible with:  remote, query, query_loc
   * </pre>
   */
  private EPssm pssm;

  @Override
  public TBlastNTask getTask() {
    return task;
  }

  @Override
  public TBlastNConfigImpl setTask(TBlastNTask task) {
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
    return bestHit != null && isSubjectBestHitEnabled();
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
  public Byte getDbTranslationGeneticCode() {
    return lazy(dbGenCode, EDbGenCode::getDbTranslationGeneticCode);
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
  public Integer getMaxIntronLength() {
    return lazy(maxIntronLength, EMaxIntronLength::getMaxIntronLength);
  }

  @Override
  public File getPositionSpecificScoringMatrixFile() {
    return lazy(pssm, EPssm::getPositionSpecificScoringMatrixFile);
  }

  @Override
  public TBlastNScoringMatrix getScoringMatrix() {
    return lazy(matrix, EScoringMatrix::getScoringMatrix);
  }

  @Override
  public Double getScoreThreshold() {
    return lazy(matrix, EScoringMatrix::getScoreThreshold);
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
  public TBlastnConfig enableSmithWatermanTraceback(boolean b) {
    (sw = lazy(sw, ESWTback::new)).enableSmithWatermanTraceback(b);
    return this;
  }

  @Override
  public TBlastnConfig enableSubjectBestHit(boolean b) {
    (bestHit = lazy(bestHit, EBestHit::new)).enableSubjectBestHit(b);
    return this;
  }

  @Override
  public TBlastnConfig enableSumStatistics(Boolean b) {
    (sumStats = lazy(sumStats, ESumStats::new)).enableSumStatistics(b);
    return this;
  }

  @Override
  public TBlastnConfig enableUngappedAlignmentOnly(boolean b) {
    (ungapped = lazy(ungapped, EUngapped::new)).enableUngappedAlignmentOnly(b);
    return this;
  }

  @Override
  public Boolean isSumStatisticsEnabled() {
    return lazy(sumStats, ESumStats::isSumStatisticsEnabled);
  }

  @Override
  public TBlastnConfig setBestHitOverhang(Double v) {
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitOverhang(v);
    return this;
  }

  @Override
  public TBlastnConfig setBestHitScoreEdge(Double v) {
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitScoreEdge(v);
    return this;
  }

  @Override
  public TBlastnConfig setCompBasedStatisticsType(CompBasedStats c) {
    (compBasedStats = lazy(compBasedStats, ECompBasedStats::new)).setCompBasedStatisticsType(c);
    return this;
  }

  @Override
  public TBlastnConfig setCullingLimit(Integer v) {
    (cullingLimit = lazy(cullingLimit, ECullingLimit::new)).setCullingLimit(v);
    return this;
  }

  @Override
  public TBlastnConfig setDbHardMaskAlgorithmId(String id) {
    (dbMask = lazy(dbMask, EDbMask::new)).setDbHardMaskAlgorithmId(id);
    return this;
  }

  @Override
  public TBlastnConfig setDbSoftMaskAlgorithmId(String id) {
    (dbMask = lazy(dbMask, EDbMask::new)).setDbSoftMaskAlgorithmId(id);
    return this;
  }

  @Override
  public TBlastnConfig setDbTranslationGeneticCode(Byte i) {
    (dbGenCode = lazy(dbGenCode, EDbGenCode::new)).setDbTranslationGeneticCode(i);
    return this;
  }

  @Override
  public TBlastnConfig setExtensionDropoffFinalGapped(Double d) {
    (xDrop = lazy(xDrop, EGapExtDrop::new)).setExtensionDropoffFinalGapped(d);
    return this;
  }

  @Override
  public TBlastnConfig setGapCostExtend(Integer cost) {
    (gapCost = lazy(gapCost, EGapCost::new)).setGapCostExtend(cost);
    return this;
  }

  @Override
  public TBlastnConfig setGapCostOpen(Integer cost) {
    (gapCost = lazy(gapCost, EGapCost::new)).setGapCostOpen(cost);
    return this;
  }

  @Override
  public TBlastnConfig setGenInfoIdListFile(File f) {
    (giList = lazy(giList, EGiList::new)).setGenInfoIdListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setMaxIntronLength(Integer len) {
    (maxIntronLength = lazy(maxIntronLength, EMaxIntronLength::new)).setMaxIntronLength(len);
    return this;
  }

  @Override
  public TBlastnConfig setNegativeGenInfoIdListFile(File f) {
    (giList = lazy(giList, EGiList::new)).setNegativeGenInfoIdListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setNegativeSequenceIDListFile(File f) {
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setNegativeSequenceIDListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setNegativeTaxIDListFile(File f) {
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setNegativeTaxIDListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setNegativeTaxIds(int[] negativeTaxIds) {
    (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : Arrays.copyOf(negativeTaxIds, negativeTaxIds.length)
    );
    return this;
  }

  @Override
  public TBlastnConfig setNegativeTaxIds(Collection<Integer> negativeTaxIds) {
    (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : new ArrayList<>(negativeTaxIds)
    );
    return this;
  }

  @Override
  public TBlastnConfig setPositionSpecificScoringMatrixFile(File f) {
    (pssm = lazy(pssm, EPssm::new)).setPositionSpecificScoringMatrixFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setExtensionDropoffPreliminaryGapped(Double d) {
    (xDrop = lazy(xDrop, EGapExtDrop::new)).setExtensionDropoffPreliminaryGapped(d);
    return this;
  }

  @Override
  public TBlastnConfig setScoreThreshold(Double d) {
    (matrix = lazy(matrix, EScoringMatrix::new)).setScoreThreshold(d);
    return this;
  }

  @Override
  public TBlastnConfig setScoringMatrix(TBlastNScoringMatrix matrix) {
    (this.matrix = lazy(this.matrix, EScoringMatrix::new)).setScoringMatrix(matrix);
    return this;
  }

  @Override
  public TBlastnConfig setSeg(Seg seg) {
    (this.seg = lazy(this.seg, ESeg::new)).setSeg(seg);
    return this;
  }

  @Override
  public TBlastnConfig setSequenceIDListFile(File f) {
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setSequenceIDListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setSubjectFile(File f) {
    (subject = lazy(subject, ESubject::new)).setSubjectFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setSubjectLocation(Location loc) {
    (subject = lazy(subject, ESubject::new)).setSubjectLocation(loc);
    return this;
  }

  @Override
  public TBlastnConfig setTaxIDListFile(File f) {
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setTaxIDListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setTaxIDs(int[] taxIds) {
    (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIDs(
      taxIds == null ? null : Arrays.copyOf(taxIds, taxIds.length)
    );
    return this;
  }

  @Override
  public TBlastnConfig setTaxIDs(Collection<Integer> taxIds) {
    (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIDs(
      taxIds == null ? null : new ArrayList<>(taxIds)
    );
    return this;
  }

  @Override
  public TBlastnConfig setWordSize(Integer size) {
    (wordSize = lazy(wordSize, EWordSize::new)).setWordSize(size);
    return this;
  }

  public static TBlastnConfig fromSerial(ArrayNode node) {
    var out  = new TBlastNConfigImpl();
    var size = node.size();

    for (var i = 1; i < size; i++) {
      var curr = node.get(i);

      switch (ToolOption.fromString(curr.get(0).asText())) {
        case BestHitOverhang:
          out.setBestHitOverhang(curr.get(1).asDouble());
          break;
        case BestHitScoreEdge:
          out.setBestHitScoreEdge(curr.get(1).asDouble());
          break;
        case BlastDatabase:
          out.setDatabase(Path.of(curr.get(1).asText()));
          break;
        case CompositionBasedStats:
          out.setCompBasedStatisticsType(CompBasedStats.fromValue(curr.get(1).asText()));
          break;
        case CullingLimit:
          out.setCullingLimit(curr.get(1).asInt());
          break;
        case EffectiveDatabaseSize:
          out.setEffectiveDatabaseSize((byte) curr.get(1).asInt());
          break;
        case DatabaseHardMask:
          out.setDbHardMaskAlgorithmId(curr.get(1).asText());
          break;
        case DatabaseSoftMask:
          out.setDbSoftMaskAlgorithmId(curr.get(1).asText());
          break;
        case DatabaseTranslationGenCode:
          out.setDbTranslationGeneticCode((byte) curr.get(1).asInt());
          break;
        case EntrezQuery:
          out.setEntrezQuery(curr.get(1).asText());
          break;
        case ExpectationValue:
          out.setExpectValue(new BigDecimal(curr.get(1).asText()));
          break;
        case ExportSearchStrategy:
          out.setSearchStrategyExportFile(new File(curr.get(1).asText()));
          break;
        case GapCostExtend:
          out.setGapCostExtend(curr.get(1).asInt());
          break;
        case GapCostOpen:
          out.setGapCostOpen(curr.get(1).asInt());
          break;
        case GIListFile:
          out.setGenInfoIdListFile(new File(curr.get(1).asText()));
          break;
        case Help:
          out.enableHelp(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case HTMLOutput:
          out.enableHtmlOutput(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case ImportSearchStrategy:
          out.setSearchStrategyImportFile(new File(curr.get(1).asText()));
          break;
        case InputPsiBlastCheckpointFile:
          out.setPositionSpecificScoringMatrixFile(new File(curr.get(1).asText()));
          break;
        case LineLength:
          out.setLineLength(curr.get(1).asInt());
          break;
        case LowercaseMasking:
          out.enableLowercaseMasking(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case MaxHSPs:
          out.setMaxHSPs(curr.get(1).asInt());
          break;
        case MaxTargetSequences:
          out.setMaxTargetSequences(curr.get(1).asInt());
          break;
        case MaxIntronLength:
          out.setMaxIntronLength(curr.get(1).asInt());
          break;
        case MultiHitWindowSize:
          out.setMultiHitWindowSize(curr.get(1).asInt());
          break;
        case NegativeGIListFile:
          out.setNegativeGenInfoIdListFile(new File(curr.get(1).asText()));
          break;
        case NegativeSequenceIDListFile:
          out.setNegativeSequenceIDListFile(new File(curr.get(1).asText()));
          break;
        case NegativeTaxonomyIDs:
          out.setNegativeTaxIds(Arrays.stream(curr.get(1).asText().split(","))
            .mapToInt(Integer::parseInt)
            .toArray());
          break;
        case NegativeTaxonomyIDListFile:
          out.setNegativeTaxIDListFile(new File(curr.get(1).asText()));
          break;
        case NumAlignments:
          out.setNumAlignments(curr.get(1).asInt());
          break;
        case NumberOfThreads:
          out.setThreadCount((byte) curr.get(1).asInt());
          break;
        case NumDescriptions:
          out.setNumDescriptions(curr.get(1).asInt());
          break;
        case OutputFile:
          out.setOutputFile(new File(curr.get(1).asText()));
          break;
        case OutputFormat:
          out.setReportFormat(ReportFormatImpl.fromString(curr.get(1).asText()));
          break;
        case ParseDefLines:
          out.enableDefLineParsing(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case Query:
          out.setQuery(curr.get(1).asText());
          break;
        case QueryCoveragePercentHSP:
          out.setQueryCoveragePercentHsp(curr.get(1).asDouble());
          break;
        case QueryLocation:
          out.setQueryLocation(LocationImpl.fromString(curr.get(1).asText()));
          break;
        case Remote:
          out.enableRemoteSearchExecution(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case ScoringMatrix:
          out.setScoringMatrix(TBlastNScoringMatrix.unsafeFromString(curr.get(1).asText()));
          break;
        case SearchSpaceEffectiveLength:
          out.setEffectiveSearchSpaceLength((byte) curr.get(1).asInt());
          break;
        case SEGFilter:
          out.setSeg(SegImpl.fromString(curr.get(1).asText()));
          break;
        case SequenceIDListFile:
          out.setSequenceIDListFile(new File(curr.get(1).asText()));
          break;
        case ShowNCBIGIs:
          out.enableNCBIGenInfoIds(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case SoftMasking:
          out.enableSoftMasking(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case HSPSorting:
          out.setHitSorting(HitSorting.fromString(curr.get(1).asText()));
          break;
        case HitSorting:
          out.setHSPSorting(HspSorting.fromString(curr.get(1).asText()));
          break;
        case SubjectBestHit:
          out.enableSubjectBestHit(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case SubjectFile:
          out.setSubjectFile(new File(curr.get(1).asText()));
          break;
        case SubjectLocation:
          out.setSubjectLocation(LocationImpl.fromString(curr.get(1).asText()));
          break;
        case SumStats:
          out.enableSumStatistics(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case Task:
          out.setTask(TBlastNTask.fromString(curr.get(1).asText()));
          break;
        case TaxonomyIDs:
          out.setTaxIDs(Arrays.stream(curr.get(1).asText().split(","))
            .mapToInt(Integer::parseInt)
            .toArray());
          break;
        case TaxonomyIDListFile:
          out.setTaxIDListFile(new File(curr.get(1).asText()));
          break;
        case Threshold:
          out.setScoreThreshold(curr.get(1).asDouble());
          break;
        case UngappedAlignmentOnly:
          out.enableUngappedAlignmentOnly(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case UseSmithWatermanAlignments:
          out.enableSmithWatermanTraceback(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case Version:
          out.enableVersion(curr.size() == 1 || curr.get(1).asBoolean());
          break;
        case WordSize:
          out.setWordSize(curr.get(1).asInt());
          break;
        case ExtensionDropoffFinalGapped:
          out.setExtensionDropoffFinalGapped(curr.get(1).asDouble());
          break;
        case ExtensionDropoffPrelimGapped:
          out.setExtensionDropoffPreliminaryGapped(curr.get(1).asDouble());
          break;
        case ExtensionDropoffUngapped:
          out.setExtensionDropoffUngapped(curr.get(1).asDouble());
          break;
      }
    }

    return out;
  }
}
