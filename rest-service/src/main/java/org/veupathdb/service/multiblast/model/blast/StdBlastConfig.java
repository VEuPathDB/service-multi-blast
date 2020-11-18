package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class StdBlastConfig extends BlastConfig
{
  private String        dbSoftMask;
  private String        dbHardMask;
  private File          giList;
  private File          negativeGiList;
  private File          sequenceIdList;
  private File          negativeSequenceIdList;
  private File          taxIdList;
  private File          negativeTaxIdList;
  private File          subject;
  private QueryLocation subjectLocation;
  private String[]      taxIds;
  private String[]      negativeTaxIds;

  public String getDbSoftMask() {
    return dbSoftMask;
  }

  public StdBlastConfig setDbSoftMask(String dbSoftMask) {
    this.dbSoftMask = dbSoftMask;
    return this;
  }

  public String getDbHardMask() {
    return dbHardMask;
  }

  public StdBlastConfig setDbHardMask(String dbHardMask) {
    this.dbHardMask = dbHardMask;
    return this;
  }

  public File getGiList() {
    return giList;
  }

  public StdBlastConfig setGiList(File giList) {
    this.giList = giList;
    return this;
  }

  public File getNegativeGiList() {
    return negativeGiList;
  }

  public StdBlastConfig setNegativeGiList(File negativeGiList) {
    this.negativeGiList = negativeGiList;
    return this;
  }

  public File getSequenceIdList() {
    return sequenceIdList;
  }

  public StdBlastConfig setSequenceIdList(File sequenceIdList) {
    this.sequenceIdList = sequenceIdList;
    return this;
  }

  public File getNegativeSequenceIdList() {
    return negativeSequenceIdList;
  }

  public StdBlastConfig setNegativeSequenceIdList(File negativeSequenceIdList) {
    this.negativeSequenceIdList = negativeSequenceIdList;
    return this;
  }

  public File getTaxIdList() {
    return taxIdList;
  }

  public StdBlastConfig setTaxIdList(File taxIdList) {
    this.taxIdList = taxIdList;
    return this;
  }

  public File getNegativeTaxIdList() {
    return negativeTaxIdList;
  }

  public StdBlastConfig setNegativeTaxIdList(File negativeTaxIdList) {
    this.negativeTaxIdList = negativeTaxIdList;
    return this;
  }

  public File getSubject() {
    return subject;
  }

  public StdBlastConfig setSubject(File subject) {
    this.subject = subject;
    return this;
  }

  public QueryLocation getSubjectLocation() {
    return subjectLocation;
  }

  public StdBlastConfig setSubjectLocation(QueryLocation subjectLocation) {
    this.subjectLocation = subjectLocation;
    return this;
  }

  public String[] getTaxIds() {
    return taxIds;
  }

  public StdBlastConfig setTaxIds(String[] taxIds) {
    this.taxIds = taxIds;
    return this;
  }

  public String[] getNegativeTaxIds() {
    return negativeTaxIds;
  }

  public StdBlastConfig setNegativeTaxIds(String[] negativeTaxIds) {
    this.negativeTaxIds = negativeTaxIds;
    return this;
  }

  @Override
  public void toArgs(CliBuilder args) {
    super.toArgs(args);

    args.appendNonNull(ToolOption.DatabaseSoftMask, dbSoftMask)
      .appendNonNull(ToolOption.DatabaseHardMask, dbHardMask)
      .appendNonNull(ToolOption.GIListFile, giList)
      .appendNonNull(ToolOption.NegativeGIListFile, negativeGiList)
      .appendNonNull(ToolOption.SequenceIDListFile, sequenceIdList)
      .appendNonNull(ToolOption.NegativeSequenceIDListFile, negativeSequenceIdList)
      .appendNonNull(ToolOption.TaxonomyIDListFile, taxIdList)
      .appendNonNull(ToolOption.NegativeTaxonomyIDListFile, negativeTaxIdList)
      .appendNonNull(ToolOption.SubjectFile, subject)
      .appendNonNull(ToolOption.SubjectLocation, subjectLocation)
      .appendNonNull(ToolOption.WordSize, wordSize)
      .appendNonNull(ToolOption.TaxonomyIDs, str2obj(taxIds))
      .appendNonNull(ToolOption.NegativeTaxonomyIDs, str2obj(negativeTaxIds));
  }

  protected static Object[] str2obj(String[] arr) {
    if (arr == null)
      return null;

    var out = new Object[arr.length];

    System.arraycopy(arr, 0, out, 0, arr.length);

    return out;
  }
}
