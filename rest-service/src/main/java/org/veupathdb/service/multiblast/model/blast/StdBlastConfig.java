package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

class StdBlastConfig extends BlastConfig
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

  public void setDbSoftMask(String dbSoftMask) {
    this.dbSoftMask = dbSoftMask;
  }

  public String getDbHardMask() {
    return dbHardMask;
  }

  public void setDbHardMask(String dbHardMask) {
    this.dbHardMask = dbHardMask;
  }

  public File getGiList() {
    return giList;
  }

  public void setGiList(File giList) {
    this.giList = giList;
  }

  public File getNegativeGiList() {
    return negativeGiList;
  }

  public void setNegativeGiList(File negativeGiList) {
    this.negativeGiList = negativeGiList;
  }

  public File getSequenceIdList() {
    return sequenceIdList;
  }

  public void setSequenceIdList(File sequenceIdList) {
    this.sequenceIdList = sequenceIdList;
  }

  public File getNegativeSequenceIdList() {
    return negativeSequenceIdList;
  }

  public void setNegativeSequenceIdList(File negativeSequenceIdList) {
    this.negativeSequenceIdList = negativeSequenceIdList;
  }

  public File getTaxIdList() {
    return taxIdList;
  }

  public void setTaxIdList(File taxIdList) {
    this.taxIdList = taxIdList;
  }

  public File getNegativeTaxIdList() {
    return negativeTaxIdList;
  }

  public void setNegativeTaxIdList(File negativeTaxIdList) {
    this.negativeTaxIdList = negativeTaxIdList;
  }

  public File getSubject() {
    return subject;
  }

  public void setSubject(File subject) {
    this.subject = subject;
  }

  public QueryLocation getSubjectLocation() {
    return subjectLocation;
  }

  public void setSubjectLocation(QueryLocation subjectLocation) {
    this.subjectLocation = subjectLocation;
  }

  public Integer getWordSize() {
    return wordSize;
  }

  public void setWordSize(Integer wordSize) {
    this.wordSize = wordSize;
  }

  public String[] getTaxIds() {
    return taxIds;
  }

  public void setTaxIds(String[] taxIds) {
    this.taxIds = taxIds;
  }

  public String[] getNegativeTaxIds() {
    return negativeTaxIds;
  }

  public void setNegativeTaxIds(String[] negativeTaxIds) {
    this.negativeTaxIds = negativeTaxIds;
  }
}
