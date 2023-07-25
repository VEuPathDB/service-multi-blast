package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastOutFormat {
  @JsonProperty("pairwise")
  PAIRWISE("pairwise"),

  @JsonProperty("query-anchored-with-identities")
  QUERYANCHOREDWITHIDENTITIES("query-anchored-with-identities"),

  @JsonProperty("query-anchored-no-identities")
  QUERYANCHOREDNOIDENTITIES("query-anchored-no-identities"),

  @JsonProperty("flat-query-anchored-with-identities")
  FLATQUERYANCHOREDWITHIDENTITIES("flat-query-anchored-with-identities"),

  @JsonProperty("flat-query-anchored-no-identities")
  FLATQUERYANCHOREDNOIDENTITIES("flat-query-anchored-no-identities"),

  @JsonProperty("xml")
  XML("xml"),

  @JsonProperty("tabular")
  TABULAR("tabular"),

  @JsonProperty("tabular-with-comments")
  TABULARWITHCOMMENTS("tabular-with-comments"),

  @JsonProperty("seqalign-text")
  SEQALIGNTEXT("seqalign-text"),

  @JsonProperty("seqalign-binary")
  SEQALIGNBINARY("seqalign-binary"),

  @JsonProperty("csv")
  CSV("csv"),

  @JsonProperty("asn1")
  ASN1("asn1"),

  @JsonProperty("seqalign-json")
  SEQALIGNJSON("seqalign-json"),

  @JsonProperty("multi-file-blast-json")
  MULTIFILEBLASTJSON("multi-file-blast-json"),

  @JsonProperty("multi-file-blast-xml2")
  MULTIFILEBLASTXML2("multi-file-blast-xml2"),

  @JsonProperty("single-file-blast-json")
  SINGLEFILEBLASTJSON("single-file-blast-json"),

  @JsonProperty("single-file-blast-xml2")
  SINGLEFILEBLASTXML2("single-file-blast-xml2"),

  @JsonProperty("sam")
  SAM("sam"),

  @JsonProperty("organism-report")
  ORGANISMREPORT("organism-report");

  public final String value;

  public String getValue() {
    return this.value;
  }

  BlastOutFormat(String name) {
    this.value = name;
  }
}
