package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import org.veupathdb.service.multiblast.model.CLISerializable;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

/**
 * Base blast configuration.
 * <p>
 * The fields in this class are common to all blast tools available in the NCBI
 * blast toolset.
 */
public class BlastConfig implements CLISerializable
{
  private boolean       helpEnabled;
  private boolean       versionEnabled;
  private String        blastDatabase;
  private Byte          dbSize;
  private String        entrezQuery;
  private Double        expectValue;
  private File          exportSearchStrategy;
  private boolean       htmlOutputEnabled;
  private File          importSearchStrategy;
  private Integer       lineLength;
  private boolean       lowercaseMaskingEnabled;
  private Integer       maxHSPs;
  private Integer       maxTargetSequences;
  private Short         numDescriptions;
  private Integer       numAlignments;
  private Byte          numThreads;
  private File          out;
  private File          query;
  private QueryLocation queryLoc;
  private boolean       remoteEnabled;
  private Byte          searchSpace;
  private boolean       showGIsEnabled;
  private Boolean       softMasking;
  private HitSorting    sortHits;
  private HspSorting    sortHsps;
  private Integer       windowSize;
  private Double        extDropoffUngapped;
  private OutFormat     outFormat;
  private Double        queryCoveragePercentHSP;
  private boolean       parseDefLinesEnabled;

  public String getBlastDatabase() {
    return blastDatabase;
  }

  public BlastConfig setBlastDatabase(String blastDatabase) {
    this.blastDatabase = blastDatabase;
    return this;
  }

  public Byte getDbSize() {
    return dbSize;
  }

  public BlastConfig setDbSize(Byte dbSize) {
    this.dbSize = dbSize;
    return this;
  }

  public String getEntrezQuery() {
    return entrezQuery;
  }

  public BlastConfig setEntrezQuery(String entrezQuery) {
    this.entrezQuery = entrezQuery;
    return this;
  }

  public Double getExpectValue() {
    return expectValue;
  }

  public BlastConfig setExpectValue(Double expectValue) {
    this.expectValue = expectValue;
    return this;
  }

  public File getExportSearchStrategy() {
    return exportSearchStrategy;
  }

  public BlastConfig setExportSearchStrategy(File exportSearchStrategy) {
    this.exportSearchStrategy = exportSearchStrategy;
    return this;
  }

  public boolean isHtmlOutputEnabled() {
    return htmlOutputEnabled;
  }

  public BlastConfig setHtmlOutputEnabled(boolean htmlOutputEnabled) {
    this.htmlOutputEnabled = htmlOutputEnabled;
    return this;
  }

  public File getImportSearchStrategy() {
    return importSearchStrategy;
  }

  public BlastConfig setImportSearchStrategy(File importSearchStrategy) {
    this.importSearchStrategy = importSearchStrategy;
    return this;
  }

  public Integer getLineLength() {
    return lineLength;
  }

  public BlastConfig setLineLength(Integer lineLength) {
    this.lineLength = lineLength;
    return this;
  }

  public boolean isLowercaseMaskingEnabled() {
    return lowercaseMaskingEnabled;
  }

  public BlastConfig setLowercaseMaskingEnabled(boolean lowercaseMaskingEnabled) {
    this.lowercaseMaskingEnabled = lowercaseMaskingEnabled;
    return this;
  }

  public Integer getMaxHSPs() {
    return maxHSPs;
  }

  public BlastConfig setMaxHSPs(Integer maxHSPs) {
    this.maxHSPs = maxHSPs;
    return this;
  }

  public Integer getMaxTargetSequences() {
    return maxTargetSequences;
  }

  public BlastConfig setMaxTargetSequences(Integer maxTargetSequences) {
    this.maxTargetSequences = maxTargetSequences;
    return this;
  }

  public Short getNumDescriptions() {
    return numDescriptions;
  }

  public BlastConfig setNumDescriptions(Short numDescriptions) {
    this.numDescriptions = numDescriptions;
    return this;
  }

  public Integer getNumAlignments() {
    return numAlignments;
  }

  public BlastConfig setNumAlignments(Integer numAlignments) {
    this.numAlignments = numAlignments;
    return this;
  }

  public Byte getNumThreads() {
    return numThreads;
  }

  public BlastConfig setNumThreads(Byte numThreads) {
    this.numThreads = numThreads;
    return this;
  }

  public File getOut() {
    return out;
  }

  public BlastConfig setOut(File out) {
    this.out = out;
    return this;
  }

  public File getQuery() {
    return query;
  }

  public BlastConfig setQuery(File query) {
    this.query = query;
    return this;
  }

  public QueryLocation getQueryLoc() {
    return queryLoc;
  }

  public BlastConfig setQueryLoc(QueryLocation queryLoc) {
    this.queryLoc = queryLoc;
    return this;
  }

  public boolean isRemoteEnabled() {
    return remoteEnabled;
  }

  public BlastConfig setRemoteEnabled(boolean remoteEnabled) {
    this.remoteEnabled = remoteEnabled;
    return this;
  }

  public Byte getSearchSpace() {
    return searchSpace;
  }

  public BlastConfig setSearchSpace(Byte searchSpace) {
    this.searchSpace = searchSpace;
    return this;
  }

  public boolean isShowGIsEnabled() {
    return showGIsEnabled;
  }

  public BlastConfig setShowGIsEnabled(boolean showGIsEnabled) {
    this.showGIsEnabled = showGIsEnabled;
    return this;
  }

  public Boolean getSoftMasking() {
    return softMasking;
  }

  public BlastConfig setSoftMasking(Boolean softMasking) {
    this.softMasking = softMasking;
    return this;
  }

  public HitSorting getSortHits() {
    return sortHits;
  }

  public BlastConfig setSortHits(HitSorting sortHits) {
    this.sortHits = sortHits;
    return this;
  }

  public HspSorting getSortHsps() {
    return sortHsps;
  }

  public BlastConfig setSortHsps(HspSorting sortHsps) {
    this.sortHsps = sortHsps;
    return this;
  }

  public Integer getWindowSize() {
    return windowSize;
  }

  public BlastConfig setWindowSize(Integer windowSize) {
    this.windowSize = windowSize;
    return this;
  }

  public boolean isVersionEnabled() {
    return versionEnabled;
  }

  public BlastConfig setVersionEnabled(boolean versionEnabled) {
    this.versionEnabled = versionEnabled;
    return this;
  }

  public Double getExtDropoffUngapped() {
    return extDropoffUngapped;
  }

  public BlastConfig setExtDropoffUngapped(Double xDropUngap) {
    this.extDropoffUngapped = xDropUngap;
    return this;
  }

  public OutFormat getOutFormat() {
    return outFormat;
  }

  public BlastConfig setOutFormat(OutFormat outFormat) {
    this.outFormat = outFormat;
    return this;
  }

  public Double getQueryCoveragePercentHSP() {
    return queryCoveragePercentHSP;
  }

  public BlastConfig setQueryCoveragePercentHSP(Double queryCoveragePercentHSP) {
    this.queryCoveragePercentHSP = queryCoveragePercentHSP;
    return this;
  }

  public boolean isParseDefLinesEnabled() {
    return parseDefLinesEnabled;
  }

  public BlastConfig setParseDefLinesEnabled(boolean parseDefLinesEnabled) {
    this.parseDefLinesEnabled = parseDefLinesEnabled;
    return this;
  }

  public boolean isHelpEnabled() {
    return helpEnabled;
  }

  public BlastConfig setHelpEnabled(boolean helpEnabled) {
    this.helpEnabled = helpEnabled;
    return this;
  }

  @Override
  public void toArgs(CliBuilder args) {
    args.appendNonNull(ToolOption.BlastDatabase, blastDatabase)
      .appendNonNull(ToolOption.DatabaseEffectiveSize, dbSize)
      .appendNonNull(ToolOption.EntrezQuery, entrezQuery)
      .appendNonNull(ToolOption.ExpectationValue, expectValue)
      .appendNonNull(ToolOption.ExportSearchStrategy, exportSearchStrategy)
      .appendNonNull(ToolOption.ImportSearchStrategy, importSearchStrategy)
      .appendNonNull(ToolOption.LineLength, lineLength)
      .appendNonNull(ToolOption.MaxHSPs, maxHSPs)
      .appendNonNull(ToolOption.MaxTargetSequences, maxTargetSequences)
      .appendNonNull(ToolOption.NumDescriptions, numDescriptions)
      .appendNonNull(ToolOption.NumAlignments, numAlignments)
      .appendNonNull(ToolOption.NumberOfThreads, numThreads)
      .appendNonNull(ToolOption.OutputFile, out)
      .appendNonNull(ToolOption.Query, query)
      .appendNonNull(ToolOption.QueryLocation, queryLoc)
      .appendNonNull(ToolOption.SearchSpaceEffectiveLength, searchSpace)
      .appendNonNull(ToolOption.SortHits, sortHits)
      .appendNonNull(ToolOption.SortHSPs, sortHsps)
      .appendNonNull(ToolOption.MultiHitWindowSize, windowSize)
      .appendNonNull(ToolOption.XDropoffUngappedExtensions, extDropoffUngapped)
      .appendNonNull(ToolOption.OutputFormat, outFormat)
      .appendNonNull(ToolOption.QueryCoveragePercentHSP, queryCoveragePercentHSP)
      .appendNonNull(ToolOption.SoftMasking, softMasking)
    ;

    if (htmlOutputEnabled)
      args.append(ToolOption.HTMLOutput);
    if (lowercaseMaskingEnabled)
      args.append(ToolOption.LowercaseMasking);
    if (versionEnabled)
      args.append(ToolOption.Version);
    if (helpEnabled)
      args.append(ToolOption.Help);
    if (showGIsEnabled)
      args.append(ToolOption.ShowNCBIGIs);
    if (parseDefLinesEnabled)
      args.append(ToolOption.ParseDefLines);
    if (remoteEnabled)
      args.append(ToolOption.Remote);
  }

  @Override
  public String toString() {
    var args = new CliBuilder();

    toArgs(args);

    return args.toString();
  }
}
