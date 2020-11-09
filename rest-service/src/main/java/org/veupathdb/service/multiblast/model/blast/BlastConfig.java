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
  private Double        eValue;
  private File          exportSearchStrategy;
  private Boolean       html;
  private File          importSearchStrategy;
  private Integer       lineLength;
  private Boolean       lowerCaseMasking;
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
  private Integer       windowsSize;
  private Boolean       version;
  private Double        xDropUngap;
  private OutFormat     outFormat;
  private Double        queryCoverageHSPPercent;
  private Boolean       parseDefLines;

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public Byte getDbSize() {
    return dbSize;
  }

  public void setDbSize(Byte dbSize) {
    this.dbSize = dbSize;
  }

  public String getEntrezQuery() {
    return entrezQuery;
  }

  public void setEntrezQuery(String entrezQuery) {
    this.entrezQuery = entrezQuery;
  }

  public Double geteValue() {
    return eValue;
  }

  public void seteValue(Double eValue) {
    this.eValue = eValue;
  }

  public File getExportSearchStrategy() {
    return exportSearchStrategy;
  }

  public void setExportSearchStrategy(File exportSearchStrategy) {
    this.exportSearchStrategy = exportSearchStrategy;
  }

  public Boolean getHtml() {
    return html;
  }

  public void setHtml(Boolean html) {
    this.html = html;
  }

  public File getImportSearchStrategy() {
    return importSearchStrategy;
  }

  public void setImportSearchStrategy(File importSearchStrategy) {
    this.importSearchStrategy = importSearchStrategy;
  }

  public Integer getLineLength() {
    return lineLength;
  }

  public void setLineLength(Integer lineLength) {
    this.lineLength = lineLength;
  }

  public Boolean getLowerCaseMasking() {
    return lowerCaseMasking;
  }

  public void setLowerCaseMasking(Boolean lowerCaseMasking) {
    this.lowerCaseMasking = lowerCaseMasking;
  }

  public Integer getMaxHSPs() {
    return maxHSPs;
  }

  public void setMaxHSPs(Integer maxHSPs) {
    this.maxHSPs = maxHSPs;
  }

  public Integer getMaxTargetSequences() {
    return maxTargetSequences;
  }

  public void setMaxTargetSequences(Integer maxTargetSequences) {
    this.maxTargetSequences = maxTargetSequences;
  }

  public Integer getNumDescriptions() {
    return numDescriptions;
  }

  public void setNumDescriptions(Integer numDescriptions) {
    this.numDescriptions = numDescriptions;
  }

  public Integer getNumAlignments() {
    return numAlignments;
  }

  public void setNumAlignments(Integer numAlignments) {
    this.numAlignments = numAlignments;
  }

  public Byte getNumThreads() {
    return numThreads;
  }

  public void setNumThreads(Byte numThreads) {
    this.numThreads = numThreads;
  }

  public File getOut() {
    return out;
  }

  public void setOut(File out) {
    this.out = out;
  }

  public File getQuery() {
    return query;
  }

  public void setQuery(File query) {
    this.query = query;
  }

  public QueryLocation getQueryLoc() {
    return queryLoc;
  }

  public void setQueryLoc(QueryLocation queryLoc) {
    this.queryLoc = queryLoc;
  }

  public Boolean getRemote() {
    return remote;
  }

  public void setRemote(Boolean remote) {
    this.remote = remote;
  }

  public Integer getSearchSpace() {
    return searchSpace;
  }

  public void setSearchSpace(Integer searchSpace) {
    this.searchSpace = searchSpace;
  }

  public Boolean getShowGIs() {
    return showGIs;
  }

  public void setShowGIs(Boolean showGIs) {
    this.showGIs = showGIs;
  }

  public Boolean getSoftMasking() {
    return softMasking;
  }

  public void setSoftMasking(Boolean softMasking) {
    this.softMasking = softMasking;
  }

  public HitSorting getSortHits() {
    return sortHits;
  }

  public void setSortHits(HitSorting sortHits) {
    this.sortHits = sortHits;
  }

  public HspSorting getSortHsps() {
    return sortHsps;
  }

  public void setSortHsps(HspSorting sortHsps) {
    this.sortHsps = sortHsps;
  }

  public Integer getWindowsSize() {
    return windowsSize;
  }

  public void setWindowsSize(Integer windowsSize) {
    this.windowsSize = windowsSize;
  }

  public Boolean getVersion() {
    return version;
  }

  public void setVersion(Boolean version) {
    this.version = version;
  }

  public Double getxDropUngap() {
    return xDropUngap;
  }

  public void setxDropUngap(Double xDropUngap) {
    this.xDropUngap = xDropUngap;
  }

  public OutFormat getOutFormat() {
    return outFormat;
  }

  public void setOutFormat(OutFormat outFormat) {
    this.outFormat = outFormat;
  }

  public Double getQueryCoverageHSPPercent() {
    return queryCoverageHSPPercent;
  }

  public void setQueryCoverageHSPPercent(Double queryCoverageHSPPercent) {
    this.queryCoverageHSPPercent = queryCoverageHSPPercent;
  }

  public Boolean getParseDefLines() {
    return parseDefLines;
  }

  public void setParseDefLines(Boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
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
