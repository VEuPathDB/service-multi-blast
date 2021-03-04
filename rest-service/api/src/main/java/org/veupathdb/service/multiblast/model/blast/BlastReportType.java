package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum BlastReportType
{
  Pairwise                           (0, "pairwise"),
  QueryAnchoredWithIdentities        (1, "query-anchored-with-identities"),
  QueryAnchoredWithoutIdentities     (2, "query-anchored-without-identities"),
  FlatQueryAnchoredWithIdentities    (3, "flat-query-anchored-with-identities"),
  FlatQueryAnchoredWithoutIdentities (4, "flat-query-anchored-without-identities"),
  XML                                (5, "xml"),
  Tabular                            (6, "tabular"),
  TabularWithComments                (7, "tabular-with-comments"),
  TextASN1                           (8, "text-asn-1"),
  BinaryASN1                         (9, "binary-asn-1"),
  CSV                                (10, "csv"),
  ArchiveASN1                        (11, "archive-asn-1"),
  SeqAlignJSON                       (12, "seqalign-json"),
  MultiFileJSON                      (13, "multi-file-json"),
  MultiFileXML2                      (14, "multi-file-xml2"),
  SingleFileJSON                     (15, "single-file-json"),
  SingleFileXML2                     (16, "single-file-xml2"),
  SAM                                (17, "sam"),
  OrganismReport                     (18, "organism-report");

  private final byte   value;
  private final String ioName;

  BlastReportType(int value, String name) {
    this.value  = (byte) value;
    this.ioName = name;
  }

  public String getIoName() {
    return ioName;
  }

  public byte getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static Optional<BlastReportType> fromInt(int value) {
    for (var e : values())
      if (e.value == value)
        return Optional.of(e);

    return Optional.empty();
  }

  public static BlastReportType unsafeFromInt(int value) {
    return fromInt(value)
      .orElseThrow(() -> new IllegalArgumentException("Unrecognized BlastReportType: " + value));
  }

  public static Optional<BlastReportType> fromIoName(String value) {
    for (var e : values())
      if (e.ioName.equals(value))
        return Optional.of(e);

    return Optional.empty();
  }
}
