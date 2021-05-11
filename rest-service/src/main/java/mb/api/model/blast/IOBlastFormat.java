package mb.api.model.blast;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.veupathdb.lib.blast.field.FormatType;

public enum IOBlastFormat {
  PAIRWISE("pairwise"),
  QUERYANCHOREDWITHIDENTITIES("query-anchored-with-identities"),
  QUERYANCHOREDWITHOUTIDENTITIES("query-anchored-without-identities"),
  FLATQUERYANCHOREDWITHIDENTITIES("flat-query-anchored-with-identities"),
  FLATQUERYANCHOREDWITHOUTIDENTITIES("flat-query-anchored-without-identities"),
  XML("xml"),
  TABULAR("tabular"),
  TABULARWITHCOMMENTS("tabular-with-comments"),
  TEXTASN_1("text-asn-1"),
  BINARYASN_1("binary-asn-1"),
  CSV("csv"),
  ARCHIVEASN_1("archive-asn-1"),
  SEQALIGNJSON("seqalign-json"),
  MULTIFILEJSON("multi-file-json"),
  MULTIFILEXML2("multi-file-xml2"),
  SINGLEFILEJSON("single-file-json"),
  SINGLEFILEXML2("single-file-xml2"),
  SAM("sam"),
  ORGANISMREPORT("organism-report");

  public final String name;

  IOBlastFormat(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }

  public FormatType toInternalValue() {
    return FormatType.values()[ordinal()];
  }

  @JsonCreator
  public static IOBlastFormat fromString(String value) {
    for (var val : values())
      if (val.name.equals(value))
        return val;

    throw new IllegalArgumentException();
  }

  public static IOBlastFormat fromInternalValue(FormatType value) {
    return value == null ? null : IOBlastFormat.values()[value.ordinal()];
  }
}
