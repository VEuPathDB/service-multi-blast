package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

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
  private Integer       wordSize;
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

  public Integer getWordSize() {
    return wordSize;
  }

  public StdBlastConfig setWordSize(Integer wordSize) {
    this.wordSize = wordSize;
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
}
