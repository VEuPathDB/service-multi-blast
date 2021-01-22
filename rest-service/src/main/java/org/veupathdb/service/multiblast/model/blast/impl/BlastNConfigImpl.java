package org.veupathdb.service.multiblast.model.blast.impl;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.blast.impl.trait.*;
import org.veupathdb.service.multiblast.model.blast.n.BlastnConfig;
import org.veupathdb.service.multiblast.model.blast.n.BlastNTask;
import org.veupathdb.service.multiblast.model.blast.n.DcTemplateType;
import org.veupathdb.service.multiblast.model.blast.n.Dust;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

/**
 * Represents the CLI configuration specifically for the {@code blastn} NCBI
 * Blast+ tool.
 */
public class BlastNConfigImpl
  extends BlastConfigImpl<BlastnConfig>
  implements BlastnConfig
{
  private static final Logger log = LogManager.getLogger(BlastNConfigImpl.class);

  // Wrapped Options

  /**
   * <pre>
   *  -best_hit_overhang <Real, (>0 and <0.5)>
   *    Best Hit algorithm overhang value (recommended value: 0.1)
   *     * Incompatible with:  culling_limit
   * </pre>
   */
  private EBestHit eBestHit;

  /**
   * <pre>
   *  -culling_limit <Integer, >=0>
   *    If the query range of a hit is enveloped by that of at least this many
   *    higher-scoring hits, delete the hit
   *     * Incompatible with:  best_hit_overhang, best_hit_score_edge
   * </pre>
   */
  private ECullingLimit eCullingLimit;

  /**
   * <pre>
   *  -db_soft_mask <String>
   *    Filtering algorithm ID to apply to the BLAST database as soft masking
   *     * Incompatible with:  db_hard_mask, subject, subject_loc
   *  -db_hard_mask <String>
   *    Filtering algorithm ID to apply to the BLAST database as hard masking
   *     * Incompatible with:  db_soft_mask, subject, subject_loc
   * </pre>
   */
  private EDbMask eDbMask;

  /**
   * <pre>
   *  -gapopen <Integer>
   *    Cost to open a gap
   *  -gapextend <Integer>
   *    Cost to extend a gap
   * </pre>
   */
  private EGapCost eGapCost;

  /**
   * <pre>
   *  -xdrop_gap <Real>
   *    X-dropoff value (in bits) for preliminary gapped extensions
   *  -xdrop_gap_final <Real>
   *    X-dropoff value (in bits) for final gapped alignment
   * </pre>
   */
  private EGapExtDrop eXGap;

  /**
   * <pre>
   *  -taxids <String>
   *    Restrict search of database to include only the specified taxonomy IDs
   *    (multiple IDs delimited by ',')
   *     * Incompatible with:  gilist, seqidlist, taxidlist, negative_gilist,
   *    negative_seqidlist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   *  -negative_taxids <String>
   *    Restrict search of database to everything except the specified taxonomy IDs
   *    (multiple IDs delimited by ',')
   *     * Incompatible with:  gilist, seqidlist, taxids, taxidlist,
   *    negative_gilist, negative_seqidlist, negative_taxidlist, remote, subject,
   *    subject_loc
   * </pre>
   */
  private ETaxIds eTaxIds;

  /**
   * <pre>
   *  -ungapped
   *    Perform ungapped alignment only?
   * </pre>
   */
  private EUngapped eUngap;

  /**
   * <pre>
   *  -subject <File_In>
   *    Subject sequence(s) to search
   *     * Incompatible with:  db, gilist, seqidlist, negative_gilist,
   *    negative_seqidlist, taxids, taxidlist, negative_taxids, negative_taxidlist,
   *    db_soft_mask, db_hard_mask
   *  -subject_loc <String>
   *    Location on the subject sequence in 1-based offsets (Format: start-stop)
   *     * Incompatible with:  db, gilist, seqidlist, negative_gilist,
   *    negative_seqidlist, taxids, taxidlist, negative_taxids, negative_taxidlist,
   *    db_soft_mask, db_hard_mask, remote
   * </pre>
   */
  private ESubject eSubject;

  /**
   * <pre>
   *  -word_size <Integer, >=4>
   *    Word size for wordfinder algorithm (length of best perfect match)
   * </pre>
   */
  private EWordSize eWordSize;

  /**
   * <pre>
   *  -seqidlist <String>
   *    Restrict search of database to list of SeqIDs
   *     * Incompatible with:  gilist, taxids, taxidlist, negative_gilist,
   *    negative_seqidlist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   *  -negative_seqidlist <String>
   *    Restrict search of database to everything except the specified SeqIDs
   *     * Incompatible with:  gilist, seqidlist, taxids, taxidlist,
   *    negative_gilist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   * </pre>
   */
  private ESeqIdList eSeqIdList;

  /**
   * <pre>
   *  -taxidlist <String>
   *    Restrict search of database to include only the specified taxonomy IDs
   *     * Incompatible with:  gilist, seqidlist, taxids, negative_gilist,
   *    negative_seqidlist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   *  -negative_taxidlist <String>
   *    Restrict search of database to everything except the specified taxonomy IDs
   *     * Incompatible with:  gilist, seqidlist, taxids, taxidlist,
   *    negative_gilist, negative_seqidlist, negative_taxids, remote, subject,
   *    subject_loc
   * </pre>
   */
  private ETaxIdList eTaxIdList;

  /**
   * <pre>
   *  -gilist <String>
   *    Restrict search of database to list of GIs
   *     * Incompatible with:  seqidlist, taxids, taxidlist, negative_gilist,
   *    negative_seqidlist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   *  -negative_gilist <String>
   *    Restrict search of database to everything except the specified GIs
   *     * Incompatible with:  gilist, seqidlist, taxids, taxidlist,
   *    negative_seqidlist, negative_taxids, negative_taxidlist, remote, subject,
   *    subject_loc
   * </pre>
   */
  private EGiList eGiList;

  /**
   * <pre>
   *  -strand <String, `both', `minus', `plus'>
   *    Query strand(s) to search against database/subject
   *    Default = `both'
   * </pre>
   */
  private EStrand eStrand;

  /**
   * <pre>
   *  -sum_stats <Boolean>
   *    Use sum statistics
   * </pre>
   */
  private ESumStats eSumStats;

  // Exported options

  /**
   * <pre>
   *  -task <String, Permissible values: 'blastn' 'blastn-short' 'dc-megablast'
   *                 'megablast' 'rmblastn' >
   *    Task to execute
   *    Default = `megablast'
   * </pre>
   */
  private BlastNTask task;

  /**
   * <pre>
   *  -reward <Integer, >=0>
   *    Reward for a nucleotide match
   * </pre>
   */
  private Integer reward;

  /**
   * <pre>
   *  -penalty <Integer, <=0>
   *    Penalty for a nucleotide mismatch
   * </pre>
   */
  private Integer penalty;

  /**
   * <pre>
   *  -use_index <Boolean>
   *    Use MegaBLAST database index
   *    Default = `false'
   * </pre>
   */
  private Boolean useIndex;

  /**
   * <pre>
   *  -index_name <String>
   *    MegaBLAST database index name (deprecated; use only for old style indices)
   * </pre>
   */
  private String indexName;

  /**
   * <pre>
   *  -dust <String>
   *    Filter query sequence with DUST (Format: 'yes', 'level window linker', or
   *    'no' to disable)
   *    Default = `20 64 1'
   * </pre>
   */
  private Dust dust;

  /**
   * <pre>
   *  -filtering_db <String>
   *    BLAST database containing filtering elements (i.e.: repeats)
   * </pre>
   */
  private Path filteringDb;

  /**
   * <pre>
   *  -window_masker_taxid <Integer>
   *    Enable WindowMasker filtering using a Taxonomic ID
   * </pre>
   */
  private Integer windowMaskerTaxId;

  /**
   * <pre>
   *  -window_masker_db <String>
   *    Enable WindowMasker filtering using this repeats database.
   * </pre>
   */
  private Path windowMaskerDbPath;

  /**
   * <pre>
   *  -template_type <String, `coding', `coding_and_optimal', `optimal'>
   *    Discontiguous MegaBLAST template type
   *     * Requires:  template_length
   * </pre>
   */
  private DcTemplateType dcTemplateType;

  /**
   * <pre>
   *  -template_length <Integer, Permissible values: '16' '18' '21' >
   *    Discontiguous MegaBLAST template length
   *     * Requires:  template_type
   * </pre>
   */
  private Byte dcTemplateLength;

  /**
   * <pre>
   *  -no_greedy
   *    Use non-greedy dynamic programming extension
   * </pre>
   */
  private boolean noGreedy;

  /**
   * <pre>
   *  -perc_identity <Real, 0..100>
   *    Percent identity
   * </pre>
   */
  private Double percentIdentity;

  /**
   * <pre>
   *  -min_raw_gapped_score <Integer>
   *    Minimum raw gapped score to keep an alignment in the preliminary gapped and
   *    traceback stages
   * </pre>
   */
  private Integer minRawGappedScore;

  /**
   * <pre>
   *  -off_diagonal_range <Integer, >=0>
   *    Number of off-diagonals to search for the 2nd hit, use 0 to turn off
   *    Default = `0'
   * </pre>
   */
  private Integer offDiagonalRange;

  @Override
  public Integer getNucleotideMatchReward() {
    return this.reward;
  }

  @Override
  public Integer getNucleotideMismatchPenalty() {
    return this.penalty;
  }

  @Override
  public Integer getCullingLimit() {
    return lazy(eCullingLimit, ECullingLimit::getCullingLimit);
  }

  @Override
  public Double getBestHitOverhang() {
    return lazy(eBestHit, EBestHit::getBestHitOverhang);
  }

  @Override
  public Double getBestHitScoreEdge() {
    return lazy(eBestHit, EBestHit::getBestHitScoreEdge);
  }

  @Override
  public boolean isSubjectBestHitEnabled() {
    return eBestHit != null && eBestHit.isSubjectBestHitEnabled();
  }

  @Override
  public String getDbSoftMaskAlgorithmId() {
    return lazy(eDbMask, EDbMask::getDbSoftMaskAlgorithmId);
  }

  @Override
  public String getDbHardMaskAlgorithmId() {
    return lazy(eDbMask, EDbMask::getDbHardMaskAlgorithmId);
  }

  @Override
  public Integer getGapCostOpen() {
    return lazy(eGapCost, EGapCost::getGapCostOpen);
  }

  @Override
  public Integer getGapCostExtend() {
    return lazy(eGapCost, EGapCost::getGapCostExtend);
  }

  @Override
  public Double getExtensionDropoffPreliminaryGapped() {
    return lazy(eXGap, EGapExtDrop::getExtensionDropoffPreliminaryGapped);
  }

  @Override
  public Double getExtensionDropoffFinalGapped() {
    return lazy(eXGap, EGapExtDrop::getExtensionDropoffFinalGapped);
  }

  @Override
  public File getGenInfoIdListFile() {
    return lazy(eGiList, EGiList::getGenInfoIdListFile);
  }

  @Override
  public File getNegativeGenInfoIdListFile() {
    return lazy(eGiList, EGiList::getNegativeGenInfoIdListFile);
  }

  @Override
  public File getSequenceIdListFile() {
    return lazy(eSeqIdList, ESeqIdList::getSequenceIdListFile);
  }

  @Override
  public File getNegativeSequenceIdListFile() {
    return lazy(eSeqIdList, ESeqIdList::getNegativeSequenceIdListFile);
  }

  @Override
  public QueryStrand getStrand() {
    return lazy(eStrand, EStrand::getStrand);
  }

  @Override
  public File getSubjectFile() {
    return lazy(eSubject, ESubject::getSubjectFile);
  }

  @Override
  public Location getSubjectLocation() {
    return lazy(eSubject, ESubject::getSubjectLocation);
  }

  @Override
  public File getTaxIdListFile() {
    return lazy(eTaxIdList, ETaxIdList::getTaxIdListFile);
  }

  @Override
  public File getNegativeTaxIdListFile() {
    return lazy(eTaxIdList, ETaxIdList::getNegativeTaxIdListFile);
  }

  @Override
  public BlastNTask getTask() {
    return task;
  }

  @Override
  public int[] getTaxIds() {
    return lazy(eTaxIds, ETaxIds::getTaxIds);
  }

  @Override
  public int[] getNegativeTaxIds() {
    return lazy(eTaxIds, ETaxIds::getNegativeTaxIds);
  }

  @Override
  public Integer getWordSize() {
    return lazy(eWordSize, EWordSize::getWordSize);
  }

  @Override
  public boolean isUngappedAlignmentOnlyEnabled() {
    return eUngap != null && eUngap.isUngappedAlignmentOnlyEnabled();
  }

  @Override
  public String getMegablastDbIndexName() {
    return indexName;
  }

  @Override
  public Dust getDust() {
    return dust;
  }

  @Override
  public Path getFilteringDbPath() {
    return filteringDb;
  }

  @Override
  public Integer getWindowMaskerTaxId() {
    return windowMaskerTaxId;
  }

  @Override
  public Path getWindowMaskerDbPath() {
    return windowMaskerDbPath;
  }

  @Override
  public DcTemplateType getDiscontiguousMegablastTemplateType() {
    return dcTemplateType;
  }

  @Override
  public Byte getDiscontiguousMegablastTemplateLength() {
    return dcTemplateLength;
  }

  @Override
  public boolean isNonGreedyDynamicProgramExtensionEnabled() {
    return noGreedy;
  }

  @Override
  public Double getPercentIdentity() {
    return percentIdentity;
  }

  @Override
  public Integer getMinRawGappedScore() {
    return minRawGappedScore;
  }

  @Override
  public Integer getOffDiagonalRange() {
    return offDiagonalRange;
  }

  @Override
  public BlastnConfig enableMegablastDbIndexUsage(Boolean b) {
    useIndex = b;
    return this;
  }

  @Override
  public BlastnConfig enableNonGreedyDynamicProgramExtension(boolean b) {
    noGreedy = b;
    return this;
  }

  @Override
  public BlastnConfig enableSubjectBestHit(boolean b) {
    (eBestHit = lazy(eBestHit, EBestHit::new)).enableSubjectBestHit(b);
    return this;
  }

  @Override
  public BlastnConfig enableSumStatistics(Boolean b) {
    (eSumStats = lazy(eSumStats, ESumStats::new)).enableSumStatistics(b);
    return this;
  }

  @Override
  public BlastnConfig enableUngappedAlignmentOnly(boolean b) {
    (eUngap = lazy(eUngap, EUngapped::new)).enableUngappedAlignmentOnly(b);
    return this;
  }

  @Override
  public Boolean isMegablastDbIndexUsageEnabled() {
    return useIndex;
  }

  @Override
  public Boolean isSumStatisticsEnabled() {
    return lazy(eSumStats, ESumStats::isSumStatisticsEnabled);
  }

  @Override
  public BlastnConfig setBestHitOverhang(Double v) {
    (eBestHit = lazy(eBestHit, EBestHit::new)).setBestHitOverhang(v);
    return this;
  }

  @Override
  public BlastnConfig setBestHitScoreEdge(Double v) {
    (eBestHit = lazy(eBestHit, EBestHit::new)).setBestHitScoreEdge(v);
    return this;
  }

  @Override
  public BlastnConfig setCullingLimit(Integer v) {
    (eCullingLimit = lazy(eCullingLimit, ECullingLimit::new)).setCullingLimit(v);
    return this;
  }

  @Override
  public BlastnConfig setDbHardMaskAlgorithmId(String id) {
    (eDbMask = lazy(eDbMask, EDbMask::new)).setDbHardMaskAlgorithmId(id);
    return this;
  }

  @Override
  public BlastnConfig setDbSoftMaskAlgorithmId(String id) {
    (eDbMask = lazy(eDbMask, EDbMask::new)).setDbSoftMaskAlgorithmId(id);
    return this;
  }

  @Override
  public BlastnConfig setDiscontiguousMegablastTemplateLength(Byte len) {
    dcTemplateLength = len;
    return this;
  }

  @Override
  public BlastnConfig setDiscontiguousMegablastTemplateType(DcTemplateType type) {
    if (type != dcTemplateType) {
      dcTemplateType = type;
    }
    return this;
  }

  @Override
  public BlastnConfig setDust(Dust d) {
    dust     = d;
    return this;
  }

  @Override
  public BlastnConfig setFilteringDbPath(Path p) {
    filteringDb = p;
    return this;
  }

  @Override
  public BlastnConfig setExtensionDropoffFinalGapped(Double d) {
    (eXGap = lazy(eXGap, EGapExtDrop::new)).setExtensionDropoffFinalGapped(d);
    return this;
  }

  @Override
  public BlastnConfig setGapCostExtend(Integer cost) {
    (eGapCost = lazy(eGapCost, EGapCost::new)).setGapCostExtend(cost);
    return this;
  }

  @Override
  public BlastnConfig setGapCostOpen(Integer cost) {
    (eGapCost = lazy(eGapCost, EGapCost::new)).setGapCostOpen(cost);
    return this;
  }

  @Override
  public BlastnConfig setGenInfoIdListFile(File f) {
    (eGiList = lazy(eGiList, EGiList::new)).setGenInfoIdListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setMegablastDbIndexName(String name) {
    indexName = name;
    return this;
  }

  @Override
  public BlastnConfig setMinRawGappedScore(Integer i) {
    minRawGappedScore = i;
    return this;
  }

  @Override
  public BlastnConfig setNegativeGenInfoIdListFile(File f) {
    (eGiList = lazy(eGiList, EGiList::new)).setNegativeGenInfoIdListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setNegativeSequenceIDListFile(File f) {
    (eSeqIdList = lazy(eSeqIdList, ESeqIdList::new)).setNegativeSequenceIDListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setNegativeTaxIDListFile(File f) {
    (eTaxIdList = lazy(eTaxIdList, ETaxIdList::new)).setNegativeTaxIDListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setNegativeTaxIds(int[] negativeTaxIds) {
    (eTaxIds = lazy(eTaxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : Arrays.copyOf(negativeTaxIds, negativeTaxIds.length)
    );
    return this;
  }

  @Override
  public BlastnConfig setNegativeTaxIds(Collection<Integer> negativeTaxIds) {
    (eTaxIds = lazy(eTaxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : new ArrayList<>(negativeTaxIds)
    );
    return this;
  }

  @Override
  public BlastnConfig setNucleotideMatchReward(Integer i) {
    reward   = i;
    return this;
  }

  @Override
  public BlastnConfig setNucleotideMismatchPenalty(Integer i) {
    penalty  = i;
    return this;
  }

  @Override
  public BlastnConfig setOffDiagonalRange(Integer val) {
    offDiagonalRange = val;
    return this;
  }

  @Override
  public BlastnConfig setPercentIdentity(Double d) {
    percentIdentity = d;
    return this;
  }

  @Override
  public BlastnConfig setExtensionDropoffPreliminaryGapped(Double d) {
    (eXGap = lazy(eXGap, EGapExtDrop::new)).setExtensionDropoffPreliminaryGapped(d);
    return this;
  }

  @Override
  public BlastnConfig setSequenceIDListFile(File f) {
    (eSeqIdList = lazy(eSeqIdList, ESeqIdList::new)).setSequenceIDListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setStrand(QueryStrand s) {
    (eStrand = lazy(eStrand, EStrand::new)).setStrand(s);
    return this;
  }

  @Override
  public BlastnConfig setSubjectFile(File f) {
    (eSubject = lazy(eSubject, ESubject::new)).setSubjectFile(f);
    return this;
  }

  @Override
  public BlastnConfig setSubjectLocation(Location loc) {
    (eSubject = lazy(eSubject, ESubject::new)).setSubjectLocation(loc);
    return this;
  }

  @Override
  public BlastnConfig setTask(BlastNTask task) {
    this.task = task;
    return this;
  }

  @Override
  public BlastnConfig setTaxIDListFile(File f) {
    (eTaxIdList = lazy(eTaxIdList, ETaxIdList::new)).setTaxIDListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setTaxIDs(int[] taxIds) {
    (eTaxIds = lazy(eTaxIds, ETaxIds::new)).setTaxIDs(
      taxIds == null ? null : Arrays.copyOf(taxIds, taxIds.length)
    );
    return this;
  }

  @Override
  public BlastnConfig setTaxIDs(Collection<Integer> taxIds) {
    (eTaxIds = lazy(eTaxIds, ETaxIds::new)).setTaxIDs(
      taxIds == null ? null : new ArrayList<>(taxIds)
    );
    return this;
  }

  @Override
  public BlastnConfig setWindowMaskerDbPath(Path p) {
    windowMaskerDbPath = p;
    return this;
  }

  @Override
  public BlastnConfig setWindowMaskerTaxId(Integer id) {
    windowMaskerTaxId = id;
    return this;
  }

  @Override
  public BlastnConfig setWordSize(Integer size) {
    (eWordSize = lazy(eWordSize, EWordSize::new)).setWordSize(size);
    return this;
  }

  @Override
  public void toCli(CliBuilder cli) {
    super.toCli(cli);

    if (this.isHelpEnabled() || this.isVersionEnabled())
      return;

    cli.appendNonNull(ToolOption.Task, task)
      .appendNonNull(ToolOption.MatchReward, reward)
      .appendNonNull(ToolOption.MismatchPenalty, penalty)
      .appendNonNull(ToolOption.UseMegablastIndex, useIndex)
      .appendNonNull(ToolOption.MegablastIndexName, indexName)
      .appendNonNull(ToolOption.Dust, dust)
      .appendNonNull(ToolOption.FilteringDatabasePath, filteringDb)
      .appendNonNull(ToolOption.WindowMaskerTaxonomicID, windowMaskerTaxId)
      .appendNonNull(ToolOption.WindowMaskerDatabasePath, windowMaskerDbPath)
      .appendNonNull(ToolOption.DiscontiguousMegablastTemplateType, dcTemplateType)
      .appendNonNull(ToolOption.DiscontiguousMegablastTemplateLength, dcTemplateLength)
      .appendNonNull(ToolOption.PercentIdentity, percentIdentity)
      .appendNonNull(ToolOption.MinRawGappedScore, minRawGappedScore)
      .appendNonNull(ToolOption.OffDiagonalRange, offDiagonalRange);

    if (noGreedy)
      cli.append(ToolOption.NonGreedyExtension);

    lazy(
      cli,
      eBestHit,
      eCullingLimit,
      eDbMask,
      eGapCost,
      eXGap,
      eTaxIds,
      eUngap,
      eSubject,
      eWordSize,
      eSeqIdList,
      eTaxIdList,
      eGiList,
      eStrand,
      eSumStats
    );
  }

  public static BlastnConfig fromSerial(ArrayNode node) {
    log.trace("BlastNConfigImpl#fromSerial(ArrayNode)");

    var out  = new BlastNConfigImpl();
    var size = node.size();

    for (var i = 1; i < size; i++) {
      var curr = node.get(i);
      log.debug("Deserializing option: {}", curr.get(0).asText().substring(1));
      switch (ToolOption.optionsByName.get(curr.get(0).asText().substring(1))) {
        case BestHitOverhang -> out.setBestHitOverhang(curr.get(1).asDouble());
        case BestHitScoreEdge -> out.setBestHitScoreEdge(curr.get(1).asDouble());
        case BlastDatabase -> out.setDatabase(Path.of(curr.get(1).asText()));
        case CullingLimit -> out.setCullingLimit(curr.get(1).asInt());
        case DatabaseHardMask -> out.setDbHardMaskAlgorithmId(curr.get(1).asText());
        case DatabaseSoftMask -> out.setDbSoftMaskAlgorithmId(curr.get(1).asText());
        case Dust -> out.setDust(DustImpl.fromString(curr.get(1).asText()));
        case EntrezQuery -> out.setEntrezQuery(curr.get(1).asText());
        case ExpectValue -> out.setExpectValue(new BigDecimal(curr.get(1).asText()));
        case ExportSearchStrategy -> out.setSearchStrategyExportFile(new File(curr.get(1).asText()));
        case FilteringDatabasePath -> out.setFilteringDbPath(Path.of(curr.get(1).asText()));
        case GapCostExtend -> out.setGapCostExtend(curr.get(1).asInt());
        case GapCostOpen -> out.setGapCostOpen(curr.get(1).asInt());
        case GIListFile -> out.setGenInfoIdListFile(new File(curr.get(1).asText()));
        case Help -> out.enableHelp(curr.size() == 1 || curr.get(1).asBoolean());
        case HTMLOutput -> out.enableHTMLOutput(curr.size() == 1 || curr.get(1).asBoolean());
        case ImportSearchStrategy -> out.setSearchStrategyImportFile(new File(curr.get(1).asText()));
        case LineLength -> out.setLineLength(curr.get(1).asInt());
        case LowercaseMasking -> out.enableLowercaseMasking(curr.size() == 1 || curr.get(1).asBoolean());
        case MatchReward -> out.setNucleotideMatchReward(curr.get(1).asInt());
        case MaxHSPs -> out.setMaxHSPs(curr.get(1).asInt());
        case MaxTargetSequences -> out.setMaxTargetSequences(curr.get(1).asInt());
        case MegablastIndexName -> out.setMegablastDbIndexName(curr.get(1).asText());
        case DiscontiguousMegablastTemplateLength -> out.setDiscontiguousMegablastTemplateLength((byte) curr.get(1).asInt());
        case DiscontiguousMegablastTemplateType -> out.setDiscontiguousMegablastTemplateType(DcTemplateType.unsafeFromString(curr.get(1).asText()));
        case MinRawGappedScore -> out.setMinRawGappedScore(curr.get(1).asInt());
        case MismatchPenalty -> out.setNucleotideMismatchPenalty(curr.get(1).asInt());
        case MultiHitWindowSize -> out.setMultiHitWindowSize(curr.get(1).asInt());
        case NegativeGIListFile -> out.setNegativeGenInfoIdListFile(new File(curr.get(1).asText()));
        case NegativeSequenceIDListFile -> out.setNegativeSequenceIDListFile(new File(curr.get(1).asText()));
        case NegativeTaxonomyIDs -> out.setNegativeTaxIds(Arrays.stream(curr.get(1).asText().split(",")).mapToInt(Integer::valueOf).toArray());
        case NegativeTaxonomyIDListFile -> out.setNegativeTaxIDListFile(new File(curr.get(1).asText()));
        case NonGreedyExtension -> out.enableNonGreedyDynamicProgramExtension(curr.size() == 1 || curr.get(1).asBoolean());
        case NumAlignments -> out.setNumAlignments(curr.get(1).asInt());
        case NumberOfThreads -> out.setThreadCount((byte) curr.get(1).asInt());
        case NumDescriptions -> out.setNumDescriptions(curr.get(1).asInt());
        case OffDiagonalRange -> out.setOffDiagonalRange(curr.get(1).asInt());
        case OutputFile -> out.setOutputFile(new File(curr.get(1).asText()));
        case OutputFormat -> out.setReportFormat(ReportFormatImpl.fromString(curr.get(1).asText()));
        case ParseDefLines -> out.enableDefLineParsing(curr.size() == 1 || curr.get(1).asBoolean());
        case PercentIdentity -> out.setPercentIdentity(curr.get(1).asDouble());
        case Query -> out.setQuery(curr.get(1).asText());
        case QueryCoveragePercentHSP -> out.setQueryCoveragePercentHSP(curr.get(1).asDouble());
        case QueryLocation -> out.setQueryLocation(LocationImpl.fromString(curr.get(1).asText()));
        case Remote -> out.enableRemoteSearchExecution(curr.size() == 1 || curr.get(1).asBoolean());
        case SearchSpaceEffectiveLength -> out.setEffectiveSearchSpaceLength((byte) curr.get(1).asInt());
        case SequenceIDListFile -> out.setSequenceIDListFile(new File(curr.get(1).asText()));
        case ShowNCBIGIs -> out.enableNCBIGenInfoIds(curr.size() == 1 || curr.get(1).asBoolean());
        case SoftMasking -> out.enableSoftMasking(curr.size() == 1 || curr.get(1).asBoolean());
        case HSPSorting -> out.setHitSorting(HitSorting.fromString(curr.get(1).asText()));
        case HitSorting -> out.setHSPSorting(HspSorting.fromString(curr.get(1).asText()));
        case QueryStrand -> out.setStrand(QueryStrand.fromString(curr.get(1).asText()));
        case SubjectBestHit -> out.enableSubjectBestHit(curr.size() == 1 || curr.get(1).asBoolean());
        case SubjectFile -> out.setSubjectFile(new File(curr.get(1).asText()));
        case SubjectLocation -> out.setSubjectLocation(LocationImpl.fromString(curr.get(1).asText()));
        case SumStats -> out.enableSumStatistics(curr.size() == 1 || curr.get(1).asBoolean());
        case Task -> out.setTask(BlastNTask.fromString(curr.get(1).asText()));
        case TaxonomyIDs -> out.setTaxIDs(Arrays.stream(curr.get(1).asText().split(",")).mapToInt(Integer::parseInt).toArray());
        case TaxonomyIDListFile -> out.setTaxIDListFile(new File(curr.get(1).asText()));
        case UngappedAlignmentOnly -> out.enableUngappedAlignmentOnly(curr.size() == 1 || curr.get(1).asBoolean());
        case UseMegablastIndex -> out.enableMegablastDbIndexUsage(curr.size() == 1 || curr.get(1).asBoolean());
        case Version -> out.enableVersion(curr.size() == 1 || curr.get(1).asBoolean());
        case WindowMaskerDatabasePath -> out.setWindowMaskerDbPath(Path.of(curr.get(1).asText()));
        case WindowMaskerTaxonomicID -> out.setWindowMaskerTaxId(curr.get(1).asInt());
        case WordSize -> out.setWordSize(curr.get(1).asInt());
        case ExtensionDropoffFinalGapped -> out.setExtensionDropoffFinalGapped(curr.get(1).asDouble());
        case ExtensionDropoffPrelimGapped -> out.setExtensionDropoffPreliminaryGapped(curr.get(1).asDouble());
        case ExtensionDropoffUngapped -> out.setExtensionDropoffUngapped(curr.get(1).asDouble());
      }
    }

    return out;
  }
}
