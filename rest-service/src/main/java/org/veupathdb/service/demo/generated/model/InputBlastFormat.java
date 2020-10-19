package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputBlastFormat {
  @JsonProperty("pairwise")
  PAIRWISE("pairwise"),

  @JsonProperty("query-anchored-with-identities")
  QUERYANCHOREDWITHIDENTITIES("query-anchored-with-identities"),

  @JsonProperty("query-anchored-without-identities")
  QUERYANCHOREDWITHOUTIDENTITIES("query-anchored-without-identities"),

  @JsonProperty("flat-query-anchored-with-identities")
  FLATQUERYANCHOREDWITHIDENTITIES("flat-query-anchored-with-identities"),

  @JsonProperty("flat-query-anchored-without-identities")
  FLATQUERYANCHOREDWITHOUTIDENTITIES("flat-query-anchored-without-identities"),

  @JsonProperty("xml")
  XML("xml"),

  @JsonProperty("tabular")
  TABULAR("tabular"),

  @JsonProperty("tabular-with-comments")
  TABULARWITHCOMMENTS("tabular-with-comments"),

  @JsonProperty("text-asn-1")
  TEXTASN_1("text-asn-1"),

  @JsonProperty("binary-asn-1")
  BINARYASN_1("binary-asn-1"),

  @JsonProperty("csv")
  CSV("csv"),

  @JsonProperty("archive-asn-1")
  ARCHIVEASN_1("archive-asn-1"),

  @JsonProperty("seqalign-json")
  SEQALIGNJSON("seqalign-json"),

  @JsonProperty("multi-file-json")
  MULTIFILEJSON("multi-file-json"),

  @JsonProperty("multi-file-xml2")
  MULTIFILEXML2("multi-file-xml2"),

  @JsonProperty("single-file-json")
  SINGLEFILEJSON("single-file-json"),

  @JsonProperty("single-file-xml2")
  SINGLEFILEXML2("single-file-xml2"),

  @JsonProperty("sam")
  SAM("sam"),

  @JsonProperty("organism-report")
  ORGANISMREPORT("organism-report");

  private String name;

  InputBlastFormat(String name) {
    this.name = name;
  }
}
