package org.veupathdb.service.multiblast.model.blast.impl;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Supplier;

import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.BlastReportFormat;
import org.veupathdb.service.multiblast.model.blast.HitSorting;
import org.veupathdb.service.multiblast.model.blast.HspSorting;
import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;
import org.veupathdb.service.multiblast.service.cli.CliOptions;

/**
 * Represents the CLI config options universal to all NCBI Blast+ tools.
 */
public class BlastConfigImpl<T extends BlastConfig<T>> implements BlastConfig<T>
{
  /**
   * A cached stringified form of this configuration.  This should be wiped out
   * any time the config values change.
   */
  protected String strCache;

  /**
   * {@code -h|-help}
   */
  private boolean help;

  /**
   * {@code -version}
   */
  private boolean version;

  /**
   * {@code -query <string; filename>}
   */
  private File query;

  /**
   * {@code -query_loc <string; range>}
   */
  private Location queryLocation;

  /**
   * {@code -db <string; dir/filename}
   */
  private Path database;

  /**
   * {@code -out <string; filename>}
   */
  private File out;

  /**
   * {@code -evalue <real>}
   */
  private BigDecimal expectValue;

  /**
   * {@code -outfmt <string; format>}
   */
  private BlastReportFormat outFmt;

  /**
   * {@code -show_gis}
   */
  private boolean showGenInfoIds;

  /**
   * {@code -num_descriptions <integer; >=0>}
   */
  private Integer numDescriptions;

  /**
   * {@code -num_alignments <integer; >= 0>}
   */
  private Integer numAlignments;

  /**
   * {@code -line_length <integer; >=1>}
   */
  private Integer lineLength;

  /**
   * {@code -html}
   */
  private boolean outputHtml;

  /**
   * {@code -sorthits <integer; >= 0 and <= 4>}
   */
  private HitSorting sortHits;

  /**
   * {@code -sorthsps <integer; >= 0 and <= 4>}
   */
  private HspSorting sortHsps;

  /**
   * {@code -lcase_masking}
   */
  private boolean lcaseMasking;

  /**
   * {@code -qcov_hsp_perc <real; 0..100>}
   */
  private Double qCovHspPerc;

  /**
   * {@code -max_hsps <integer; >= 1>}
   */
  private Integer maxHsps;

  /**
   * {@code -max_target_seqs <integer; >= 1>}
   */
  private Integer maxTargetSeqs;

  /**
   * {@code -dbsize <int8>}
   */
  private Byte dbSize;

  /**
   * {@code -searchsp <int8; >= 0>}
   */
  private Byte searchSpace;

  /**
   * {@code -import_search_strategy <string; filename>}
   */
  private File importSearchStrategy;

  /**
   * {@code -export_search_strategy <string; filename>}
   */
  private File exportSearchStrategy;

  /**
   * {@code -xdrop_ungap <real>}
   */
  private Double xDropUngap;

  /**
   * {@code -parse_deflines}
   */
  private boolean parseDefLines;

  /**
   * {@code -num_threads <integer; >= 1>}
   */
  private Byte numThreads;

  /**
   * {@code -remote}
   */
  private boolean remote;

  /**
   * {@code -entrez_query <string>}
   */
  private String entrezQuery;

  /**
   * {@code -soft_masking <boolean>}
   */
  private Boolean softMasking;

  /**
   * {@code -window_size <integer; >= 0>}
   */
  private Integer windowSize;

  @Override
  public boolean isHelpEnabled() {
    return help;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T enableHelp(boolean b) {
    if (b != help) {
      strCache = null;
      help     = b;
    }

    return (T) this;
  }

  @Override
  public boolean isVersionEnabled() {
    return version;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T enableVersion(boolean b) {
    if (b != version) {
      strCache = null;
      version  = b;
    }

    return (T) this;
  }

  @Override
  public File getQueryFile() {
    return this.query;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setQueryFile(File f) {
    strCache = null;
    query    = f;

    return (T) this;
  }

  @Override
  public Location getQueryLocation() {
    return queryLocation;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setQueryLocation(Location loc) {
    strCache      = null;
    queryLocation = loc;

    return (T) this;
  }

  @Override
  public Path getDatabase() {
    return this.database;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setDatabase(Path db) {
    strCache = null;
    database = db;
    return (T) this;
  }

  @Override
  public File getOutputFile() {
    return this.out;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setOutputFile(File out) {
    strCache = null;
    this.out = out;
    return (T) this;
  }

  @Override
  public BigDecimal getExpectValue() {
    return this.expectValue;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setExpectValue(BigDecimal val) {
    strCache    = null;
    expectValue = val;
    return (T) this;
  }

  @Override
  public BlastReportFormat getReportFormat() {
    return this.outFmt;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setReportFormat(BlastReportFormat format) {
    strCache = null;
    outFmt   = format;
    return (T) this;
  }

  @Override
  public boolean showNCBIGenInfoIds() {
    return this.showGenInfoIds;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T enableNCBIGenInfoIds(boolean b) {
    if (b != showGenInfoIds) {
      strCache       = null;
      showGenInfoIds = b;
    }

    return (T) this;
  }

  @Override
  public Integer getNumDescriptions() {
    return this.numDescriptions;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setNumDescriptions(Integer num) {
    strCache             = null;
    numDescriptions = num;
    return (T) this;
  }

  @Override
  public Integer getNumAlignments() {
    return this.numAlignments;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setNumAlignments(Integer num) {
    strCache           = null;
    numAlignments = num;
    return (T) this;
  }

  @Override
  public Integer getLineLength() {
    return this.lineLength;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setLineLength(Integer len) {
    strCache        = null;
    lineLength = len;
    return (T) this;
  }

  @Override
  public boolean isHtmlOutputEnabled() {
    return this.outputHtml;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T enableHtmlOutput(boolean b) {
    if (b != outputHtml) {
      strCache   = null;
      outputHtml = b;
    }
    return (T) this;
  }

  @Override
  public HitSorting getHitSorting() {
    return this.sortHits;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setHitSorting(HitSorting val) {
    if (val != sortHits) {
      strCache = null;
      sortHits = val;
    }

    return (T) this;
  }

  @Override
  public HspSorting getHspSorting() {
    return this.sortHsps;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setHspSorting(HspSorting val) {
    if (val != sortHsps) {
      strCache = null;
      sortHsps = val;
    }

    return (T) this;
  }

  @Override
  public boolean isLowercaseMaskingEnabled() {
    return this.lcaseMasking;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T enableLowercaseMasking(boolean b) {
    if (b != lcaseMasking) {
      strCache     = null;
      lcaseMasking = b;
    }

    return (T) this;
  }

  @Override
  public Double getQueryCoverageHspPercent() {
    return this.qCovHspPerc;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setQueryCoverageHspPercent(Double v) {
    this.qCovHspPerc = v;
    return (T) this;
  }

  @Override
  public Integer getMaxHsps() {
    return this.maxHsps;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setMaxHsps(Integer v) {
    this.maxHsps = v;
    return (T) this;
  }

  @Override
  public Integer getMaxTargetSequences() {
    return this.maxTargetSeqs;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setMaxTargetSequences(Integer v) {
    this.maxTargetSeqs = v;
    return (T) this;
  }

  @Override
  public Byte getEffectiveDatabaseSize() {
    return this.dbSize;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setEffectiveDatabaseSize(Byte v) {
    this.dbSize = v;
    return (T) this;
  }

  @Override
  public Byte getEffectiveSearchSpaceLength() {
    return this.searchSpace;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setEffectiveSearchSpaceLength(Byte v) {
    this.searchSpace = v;
    return (T) this;
  }

  @Override
  public File getSearchStrategyImportFile() {
    return this.importSearchStrategy;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setSearchStrategyImportFile(File f) {
    this.importSearchStrategy = f;
    return (T) this;
  }

  @Override
  public File getSearchStrategyExportFile() {
    return this.exportSearchStrategy;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setSearchStrategyExportFile(File f) {
    this.exportSearchStrategy = f;
    return (T) this;
  }

  @Override
  public Double getUngappedExtensionDropoff() {
    return this.xDropUngap;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setExtensionDropoffUngapped(Double v) {
    this.xDropUngap = v;
    return (T) this;
  }

  @Override
  public boolean isDefLineParsingEnabled() {
    return this.parseDefLines;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T enableDefLineParsing(boolean b) {
    if (b != parseDefLines) {
      strCache      = null;
      parseDefLines = b;
    }

    return (T) this;
  }

  @Override
  public Byte getThreadCount() {
    return this.numThreads;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setThreadCount(Byte b) {
    this.numThreads = b;
    return (T) this;
  }

  @Override
  public boolean isRemoteSearchExecutionEnabled() {
    return this.remote;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T enableRemoteSearchExecution(boolean b) {
    if (b != remote) {
      strCache = null;
      remote   = b;
    }
    return (T) this;
  }

  @Override
  public String getEntrezQuery() {
    return this.entrezQuery;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setEntrezQuery(String q) {
    this.entrezQuery = q;
    return (T) this;
  }

  @Override
  public Boolean isSoftMaskingEnabled() {
    return this.softMasking;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T enableSoftMasking(Boolean b) {
    this.softMasking = b;
    return (T) this;
  }

  @Override
  public Integer getMultiHitWindowSize() {
    return this.windowSize;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T setMultiHitWindowSize(Integer i) {
    this.windowSize = i;
    return (T) this;
  }

  @Override
  public String toString() {
    var tmp = new CliBuilder();
    toCli(tmp);
    return tmp.toString();
  }

  @Override
  public void toCli(CliBuilder cli) {
    if (help) {
      cli.append(ToolOption.Help);
      return;
    }
    if (version) {
      cli.append(ToolOption.Version);
      return;
    }
    cli.appendNonNull(ToolOption.Query, query)
      .appendNonNull(ToolOption.QueryLocation, queryLocation)
      .appendNonNull(ToolOption.BlastDatabase, database)
      .appendNonNull(ToolOption.OutputFile, out)
      .appendNonNull(ToolOption.ExpectationValue, expectValue)
      .appendNonNull(ToolOption.OutputFormat, outFmt)
      .appendNonNull(ToolOption.NumDescriptions, numDescriptions)
      .appendNonNull(ToolOption.NumAlignments, numAlignments)
      .appendNonNull(ToolOption.LineLength, lineLength)
      .appendNonNull(ToolOption.SortHits, sortHits)
      .appendNonNull(ToolOption.SortHSPs, sortHsps)
      .appendNonNull(ToolOption.QueryCoveragePercentHSP, qCovHspPerc)
      .appendNonNull(ToolOption.MaxHSPs, maxHsps)
      .appendNonNull(ToolOption.MaxTargetSequences, maxTargetSeqs)
      .appendNonNull(ToolOption.DatabaseEffectiveSize, dbSize)
      .appendNonNull(ToolOption.SearchSpaceEffectiveLength, searchSpace)
      .appendNonNull(ToolOption.ImportSearchStrategy, importSearchStrategy)
      .appendNonNull(ToolOption.ExportSearchStrategy, exportSearchStrategy)
      .appendNonNull(ToolOption.XDropoffUngappedExtensions, xDropUngap)
      .appendNonNull(ToolOption.NumberOfThreads, numThreads)
      .appendNonNull(ToolOption.EntrezQuery, entrezQuery)
      .appendNonNull(ToolOption.SoftMasking, softMasking)
      .appendNonNull(ToolOption.MultiHitWindowSize, windowSize);

    if (showGenInfoIds)
      cli.append(ToolOption.ShowNCBIGIs);
    if (outputHtml)
      cli.append(ToolOption.HTMLOutput);
    if (lcaseMasking)
      cli.append(ToolOption.LowercaseMasking);
    if (parseDefLines)
      cli.append(ToolOption.ParseDefLines);
    if (remote)
      cli.append(ToolOption.Remote);

  }

  static <R> R lazy(R field, Supplier<R> init) {
    return field == null ? init.get() : field;
  }

  static <F, R> R lazy(F field, Function<F, R> fn) {
    return field == null ? null : fn.apply(field);
  }

  static void lazy(CliBuilder cli, CliOptions... opts) {
    for (var o : opts)
      if (o != null)
        o.toCli(cli);
  }
}
