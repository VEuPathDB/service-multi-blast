package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import org.veupathdb.service.multiblast.model.CLISerializable;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.Validatable;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class BlastConfig implements CLISerializable, Validatable
{
  private boolean       help;
  private boolean       version;
  private String        dbName;
  private Byte          dbSize;
  private String        entrezQuery;
  private Double        expectValue;
  private File          exportSearchStrategy;
  private boolean       html;
  private File          importSearchStrategy;
  private Integer       lineLength;
  private boolean       lowercaseMasking;
  private Integer       maxHSPs;
  private Integer       maxTargetSequences;
  private Integer       numDescriptions;
  private Integer       numAlignments;
  private Byte          numThreads;
  private File          out;
  private File          query;
  private QueryLocation queryLoc;
  private boolean       remote;
  private Byte          searchSpace;
  private boolean       showGIs;
  private Boolean       softMasking;
  private HitSorting    sortHits;
  private HspSorting    sortHsps;
  private Integer       windowSize;
  private Double        xDropUngap;
  private OutFormat     outFormat;
  private Double        queryCoveragePercentHSP;
  private boolean       parseDefLines;

  public String getDbName() {
    return dbName;
  }

  public BlastConfig setDbName(String dbName) {
    this.dbName = dbName;
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

  public boolean getHtml() {
    return html;
  }

  public BlastConfig setHtml(boolean html) {
    this.html = html;
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

  public boolean getLowercaseMasking() {
    return lowercaseMasking;
  }

  public BlastConfig setLowercaseMasking(boolean lowercaseMasking) {
    this.lowercaseMasking = lowercaseMasking;
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

  public Integer getNumDescriptions() {
    return numDescriptions;
  }

  public BlastConfig setNumDescriptions(Integer numDescriptions) {
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

  public boolean getRemote() {
    return remote;
  }

  public BlastConfig setRemote(boolean remote) {
    this.remote = remote;
    return this;
  }

  public Byte getSearchSpace() {
    return searchSpace;
  }

  public BlastConfig setSearchSpace(Byte searchSpace) {
    this.searchSpace = searchSpace;
    return this;
  }

  public boolean getShowGIs() {
    return showGIs;
  }

  public BlastConfig setShowGIs(boolean showGIs) {
    this.showGIs = showGIs;
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

  public boolean getVersion() {
    return version;
  }

  public BlastConfig setVersion(boolean version) {
    this.version = version;
    return this;
  }

  public Double getXDropUngap() {
    return xDropUngap;
  }

  public BlastConfig setXDropUngap(Double xDropUngap) {
    this.xDropUngap = xDropUngap;
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

  public boolean getParseDefLines() {
    return parseDefLines;
  }

  public BlastConfig setParseDefLines(boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
    return this;
  }

  public boolean getHelp() {
    return help;
  }

  public BlastConfig setHelp(boolean help) {
    this.help = help;
    return this;
  }

  @Override
  public void toArgs(CliBuilder args) {
    args.appendNonNull(ToolOption.BlastDatabase, dbName)
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
      .appendNonNull(ToolOption.XDropoffUngappedExtensions, xDropUngap)
      .appendNonNull(ToolOption.OutputFormat, outFormat)
      .appendNonNull(ToolOption.QueryCoveragePercentHSP, queryCoveragePercentHSP)
      .appendNonNull(ToolOption.SoftMasking, softMasking)
    ;

    if (html)
      args.append(ToolOption.HTMLOutput);
    if (lowercaseMasking)
      args.append(ToolOption.LowercaseMasking);
    if (version)
      args.append(ToolOption.Version);
    if (help)
      args.append(ToolOption.Help);
    if (showGIs)
      args.append(ToolOption.ShowNCBIGIs);
    if (parseDefLines)
      args.append(ToolOption.ParseDefLines);
    if (remote)
      args.append(ToolOption.Remote);
  }

  @Override
  public String toString() {
    var args = new CliBuilder();

    toArgs(args);

    return args.toString();
  }

  @Override
  public ErrorMap validate() {
    var out = new ErrorMap();

    return out;
  }
}
