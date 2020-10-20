package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputBlastFmtField {
  @JsonProperty("qseqid")
  QSEQID("qseqid"),

  @JsonProperty("qgi")
  QGI("qgi"),

  @JsonProperty("qacc")
  QACC("qacc"),

  @JsonProperty("sseqid")
  SSEQID("sseqid"),

  @JsonProperty("sallseqid")
  SALLSEQID("sallseqid"),

  @JsonProperty("sgi")
  SGI("sgi"),

  @JsonProperty("sallgi")
  SALLGI("sallgi"),

  @JsonProperty("sacc")
  SACC("sacc"),

  @JsonProperty("sallacc")
  SALLACC("sallacc"),

  @JsonProperty("qstart")
  QSTART("qstart"),

  @JsonProperty("qend")
  QEND("qend"),

  @JsonProperty("sstart")
  SSTART("sstart"),

  @JsonProperty("send")
  SEND("send"),

  @JsonProperty("qseq")
  QSEQ("qseq"),

  @JsonProperty("sseq")
  SSEQ("sseq"),

  @JsonProperty("evalue")
  EVALUE("evalue"),

  @JsonProperty("bitscore")
  BITSCORE("bitscore"),

  @JsonProperty("score")
  SCORE("score"),

  @JsonProperty("length")
  LENGTH("length"),

  @JsonProperty("pident")
  PIDENT("pident"),

  @JsonProperty("nident")
  NIDENT("nident"),

  @JsonProperty("mismatch")
  MISMATCH("mismatch"),

  @JsonProperty("positive")
  POSITIVE("positive"),

  @JsonProperty("gapopen")
  GAPOPEN("gapopen"),

  @JsonProperty("gaps")
  GAPS("gaps"),

  @JsonProperty("ppos")
  PPOS("ppos"),

  @JsonProperty("frames")
  FRAMES("frames"),

  @JsonProperty("qframe")
  QFRAME("qframe"),

  @JsonProperty("sframe")
  SFRAME("sframe"),

  @JsonProperty("btop")
  BTOP("btop"),

  @JsonProperty("staxids")
  STAXIDS("staxids"),

  @JsonProperty("sscinames")
  SSCINAMES("sscinames"),

  @JsonProperty("scomnames")
  SCOMNAMES("scomnames"),

  @JsonProperty("sblastnames")
  SBLASTNAMES("sblastnames"),

  @JsonProperty("sskingdoms")
  SSKINGDOMS("sskingdoms"),

  @JsonProperty("stitle")
  STITLE("stitle"),

  @JsonProperty("salltitles")
  SALLTITLES("salltitles"),

  @JsonProperty("sstrand")
  SSTRAND("sstrand"),

  @JsonProperty("qcovs")
  QCOVS("qcovs"),

  @JsonProperty("qcovhsp")
  QCOVHSP("qcovhsp"),

  @JsonProperty("qcovus")
  QCOVUS("qcovus");

  private String name;

  InputBlastFmtField(String name) {
    this.name = name;
  }
}
