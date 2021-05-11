package mb.lib.blast.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BlastReportType
{
  Pairwise(0, false, false, "pairwise"),
  QueryAnchoredWithIdentities(1, false, false, "query-anchored-with-identities"),
  QueryAnchoredWithoutIdentities(2, false, false, "query-anchored-without-identities"),
  FlatQueryAnchoredWithIdentities(3, false, false, "flat-query-anchored-with-identities"),
  FlatQueryAnchoredWithoutIdentities(4, false, false, "flat-query-anchored-without-identities"),
  BlastXML(5, false, false, "xml"),
  Tabular(6, true, true, "tabular"),
  TabularWithCommentLines(7, true, true, "tabular-with-comments"),
  SeqAlignTextASN1(8, false, false, "text-asn-1"),
  SeqAlignBinaryASN1(9, false, false, "binary-asn-1"),
  CommaSeparatedValues(10, true, true, "csv"),
  BlastArchiveASN1(11, false, false, "archive-asn-1"),
  SeqAlignJSON(12, false, false, "seqalign-json"),
  MultipleFileBlastJSON(13, false, false, "multi-file-json"),
  MultipleFileBlastXML2(14, false, false, "multi-file-xml2"),
  SingleFileBlastJSON(15, false, false, "single-file-json"),
  SingleFileBlastXML2(16, false, false, "single-file-xml2"),
  SequenceAlignmentMap(17, true, false, "sam"),
  OrganismReport(18, false, false, "organism-report"),
  ;

  private final int     id;
  private final boolean parameterized;
  private final boolean delimiterAllowed;
  private final String  name;

  BlastReportType(int id, boolean parameterized, boolean delimiterAllowed, String name) {
    this.id               = id;
    this.parameterized    = parameterized;
    this.delimiterAllowed = delimiterAllowed;
    this.name             = name;
  }

  public int id() {
    return id;
  }

  public boolean isParameterized() {
    return parameterized;
  }

  public boolean isDelimiterAllowed() {
    return delimiterAllowed;
  }

  @JsonValue
  public String externalName() {
    return this.name;
  }

  @Override
  public String toString() {
    return name();
  }

  public static BlastReportType fromID(int id) {
    for (var v : values())
      if (v.id == id)
        return v;

    throw new IllegalArgumentException("Unrecognized format id: " + id);
  }

  @JsonCreator
  public static BlastReportType fromName(String name) {
    for (var v : values())
      if (v.name.equals(name))
        return v;
    throw new IllegalArgumentException("Unrecognized format name: " + name);
  }
}
