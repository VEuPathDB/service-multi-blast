package org.veupathdb.service.multiblast.model.blast;

import java.util.Objects;

public enum ReportFormatType
{
  PAIRWISE(0),
  QUERY_ANCHORED_WITH_IDENTITIES(1),
  QUERY_ANCHORED_WITHOUT_IDENTITIES(2),
  FLAT_QUERY_ANCHORED_WITH_IDENTITIES(3),
  FLAT_QUERY_ANCHORED_WITHOUT_IDENTITIES(4),
  XML(5),
  TABULAR(6),
  TABULAR_WITH_COMMENTS(7),
  TEXT_ASN_1(8),
  BINARY_ASN_1(9),
  CSV(10),
  ARCHIVE_ASN_1(11),
  SEQALIGN_JSON(12),
  MULTI_FILE_JSON(13),
  MULTI_FILE_XML2(14),
  SINGLE_FILE_JSON(15),
  SINGLE_FILE_XML2(16),
  SAM(17),
  ORGANISM_REPORT(18);

  private static final ReportFormatType def = PAIRWISE;

  private final byte value;

  ReportFormatType(int value) {
    this.value = (byte) value;
  }

  public static ReportFormatType getDefault() {
    return def;
  }

  public byte getValue() {
    return value;
  }

  public boolean isValidFor(BlastTool tool) {
    Objects.requireNonNull(tool);

    return this != SAM || tool == BlastTool.BLASTN;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public String ioName() {
    return name().toLowerCase().replaceAll("-", "-");
  }
}
