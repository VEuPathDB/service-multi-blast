package org.veupathdb.service.multiblast.model.blast;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;

import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public interface BlastConfig <T extends BlastConfig<T>>
{
  boolean isHelpEnabled();
  T enableHelp(boolean b);

  boolean isVersionEnabled();
  T enableVersion(boolean b);

  String getQuery();
  T setQuery(String f);

  Location getQueryLocation();
  T setQueryLocation(Location loc);

  Path getDatabase();
  T setDatabase(Path db);

  File getOutputFile();
  T setOutputFile(File out);

  BigDecimal getExpectValue();
  T setExpectValue(BigDecimal val);

  BlastReportFormat getReportFormat();
  T setReportFormat(BlastReportFormat format);

  boolean showNCBIGenInfoIds();
  T enableNCBIGenInfoIds(boolean b);

  /**
   * Gets the currently configured number of database sequences to show one-line
   * descriptions for.
   * <p>
   * If unset, this method returns null.
   */
  Integer getNumDescriptions();

  /**
   * Sets the currently configured number of database sequences to show one-line
   * descriptions for.
   * <p>
   * To unset, pass a null value.
   */
  T setNumDescriptions(Integer num);

  /**
   * Gets the currently configured number of database sequences to show
   * alignments for.
   * <p>
   * If unset, this method returns null.
   */
  Integer getNumAlignments();

  /**
   * Sets the currently configured number of database sequences to show
   * alignments for.
   * <p>
   * To unset, pass a null value.
   */
  T setNumAlignments(Integer num);

  Integer getLineLength();
  T setLineLength(Integer len);

  boolean isHtmlOutputEnabled();
  T enableHtmlOutput(boolean b);

  HitSorting getHitSorting();
  T setHitSorting(HitSorting val);

  HspSorting getHspSorting();
  T setHSPSorting(HspSorting val);

  boolean isLowercaseMaskingEnabled();
  T enableLowercaseMasking(boolean b);

  Double getQueryCoverageHspPercent();
  T setQueryCoveragePercentHsp(Double v);

  Integer getMaxHsps();
  T setMaxHSPs(Integer v);

  Integer getMaxTargetSequences();
  T setMaxTargetSequences(Integer v);

  Byte getEffectiveDatabaseSize();
  T setEffectiveDatabaseSize(Byte v);

  Byte getEffectiveSearchSpaceLength();
  T setEffectiveSearchSpaceLength(Byte v);

  File getSearchStrategyImportFile();
  T setSearchStrategyImportFile(File f);

  File getSearchStrategyExportFile();
  T setSearchStrategyExportFile(File f);

  Double getUngappedExtensionDropoff();
  T setExtensionDropoffUngapped(Double v);

  boolean isDefLineParsingEnabled();
  T enableDefLineParsing(boolean b);

  Byte getThreadCount();
  T setThreadCount(Byte b);

  boolean isRemoteSearchExecutionEnabled();
  T enableRemoteSearchExecution(boolean b);

  String getEntrezQuery();
  T setEntrezQuery(String q);

  Boolean isSoftMaskingEnabled();
  T enableSoftMasking(Boolean b);

  /**
   * Gets the currently configured multi-hit window size.
   * <p>
   * If this config option is not currently set, this method will return null.
   */
  Integer getMultiHitWindowSize();

  /**
   * Sets a new multi-hit window size.
   * <p>
   * Use 0 to specify 1-hit algorithm.
   * <p>
   * To unset this config option, call this method with a null value.
   */
  T setMultiHitWindowSize(Integer i);

  void toCli(CliBuilder cli);
}
