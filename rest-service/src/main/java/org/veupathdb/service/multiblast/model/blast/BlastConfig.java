package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import org.veupathdb.service.multiblast.model.CLISerializable;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.Validatable;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class BlastConfig implements CLISerializable, Validatable
{
  private String        dbName;
  private Byte          dbSize;
  private String        entrezQuery;
  private Double        expectValue;
  private File          exportSearchStrategy;
  private Boolean       html;
  private File          importSearchStrategy;
  private Integer       lineLength;
  private Boolean       lowercaseMasking;
  private Integer       maxHSPs;
  private Integer       maxTargetSequences;
  private Integer       numDescriptions;
  private Integer       numAlignments;
  private Byte          numThreads;
  private File          out;
  private File          query;
  private QueryLocation queryLoc;
  private Boolean       remote;
  private Integer       searchSpace;
  private Boolean       showGIs;
  private Boolean       softMasking;
  private HitSorting    sortHits;
  private HspSorting    sortHsps;
  private Integer       windowSize;
  private Boolean       version;
  private Double        xDropUngap;
  private OutFormat     outFormat;
  private Double        queryCoverageHSPPercent;
  private Boolean       parseDefLines;

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

  public Boolean getHtml() {
    return html;
  }

  public BlastConfig setHtml(Boolean html) {
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

  public Boolean getLowercaseMasking() {
    return lowercaseMasking;
  }

  public BlastConfig setLowercaseMasking(Boolean lowercaseMasking) {
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

  public Boolean getRemote() {
    return remote;
  }

  public BlastConfig setRemote(Boolean remote) {
    this.remote = remote;
    return this;
  }

  public Integer getSearchSpace() {
    return searchSpace;
  }

  public BlastConfig setSearchSpace(Integer searchSpace) {
    this.searchSpace = searchSpace;
    return this;
  }

  public Boolean getShowGIs() {
    return showGIs;
  }

  public BlastConfig setShowGIs(Boolean showGIs) {
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

  public Boolean getVersion() {
    return version;
  }

  public BlastConfig setVersion(Boolean version) {
    this.version = version;
    return this;
  }

  public Double getxDropUngap() {
    return xDropUngap;
  }

  public BlastConfig setxDropUngap(Double xDropUngap) {
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

  public Double getQueryCoverageHSPPercent() {
    return queryCoverageHSPPercent;
  }

  public BlastConfig setQueryCoverageHSPPercent(Double queryCoverageHSPPercent) {
    this.queryCoverageHSPPercent = queryCoverageHSPPercent;
    return this;
  }

  public Boolean getParseDefLines() {
    return parseDefLines;
  }

  public BlastConfig setParseDefLines(Boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
    return this;
  }

  @Override
  public void toArgs(CliBuilder args) {
    args.append(ToolOption.Query, query.getAbsolutePath())
      .appendNonNull(ToolOption.QueryLocation, queryLoc);
  }

  @Override
  public ErrorMap validate() {
    var out = new ErrorMap();


    return out;
  }

}
