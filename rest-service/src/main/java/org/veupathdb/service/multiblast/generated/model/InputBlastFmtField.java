package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputBlastFmtField {
  @JsonProperty("bitscore")
  BITSCORE("bitscore"),

  @JsonProperty("btop")
  BTOP("btop"),

  @JsonProperty("evalue")
  EVALUE("evalue"),

  @JsonProperty("frames")
  FRAMES("frames"),

  @JsonProperty("gapopen")
  GAPOPEN("gapopen"),

  @JsonProperty("gaps")
  GAPS("gaps"),

  @JsonProperty("length")
  LENGTH("length"),

  @JsonProperty("mismatch")
  MISMATCH("mismatch"),

  @JsonProperty("nident")
  NIDENT("nident"),

  @JsonProperty("pident")
  PIDENT("pident"),

  @JsonProperty("positive")
  POSITIVE("positive"),

  @JsonProperty("ppos")
  PPOS("ppos"),

  @JsonProperty("qacc")
  QACC("qacc"),

  @JsonProperty("qaccver")
  QACCVER("qaccver"),

  @JsonProperty("qcovhsp")
  QCOVHSP("qcovhsp"),

  @JsonProperty("qcovs")
  QCOVS("qcovs"),

  @JsonProperty("qcovus")
  QCOVUS("qcovus"),

  @JsonProperty("qend")
  QEND("qend"),

  @JsonProperty("qframe")
  QFRAME("qframe"),

  @JsonProperty("qgi")
  QGI("qgi"),

  @JsonProperty("qlen")
  QLEN("qlen"),

  @JsonProperty("qseq")
  QSEQ("qseq"),

  @JsonProperty("qseqid")
  QSEQID("qseqid"),

  @JsonProperty("qstart")
  QSTART("qstart"),

  @JsonProperty("sacc")
  SACC("sacc"),

  @JsonProperty("saccver")
  SACCVER("saccver"),

  @JsonProperty("sallacc")
  SALLACC("sallacc"),

  @JsonProperty("sallgi")
  SALLGI("sallgi"),

  @JsonProperty("sallseqid")
  SALLSEQID("sallseqid"),

  @JsonProperty("salltitles")
  SALLTITLES("salltitles"),

  @JsonProperty("sblastname")
  SBLASTNAME("sblastname"),

  @JsonProperty("sblastnames")
  SBLASTNAMES("sblastnames"),

  @JsonProperty("scomname")
  SCOMNAME("scomname"),

  @JsonProperty("scomnames")
  SCOMNAMES("scomnames"),

  @JsonProperty("score")
  SCORE("score"),

  @JsonProperty("send")
  SEND("send"),

  @JsonProperty("sframe")
  SFRAME("sframe"),

  @JsonProperty("sgi")
  SGI("sgi"),

  @JsonProperty("slen")
  SLEN("slen"),

  @JsonProperty("sq")
  SQ("sq"),

  @JsonProperty("sr")
  SR("sr"),

  @JsonProperty("ssciname")
  SSCINAME("ssciname"),

  @JsonProperty("sscinames")
  SSCINAMES("sscinames"),

  @JsonProperty("sseq")
  SSEQ("sseq"),

  @JsonProperty("sseqid")
  SSEQID("sseqid"),

  @JsonProperty("sskingdom")
  SSKINGDOM("sskingdom"),

  @JsonProperty("sskingdoms")
  SSKINGDOMS("sskingdoms"),

  @JsonProperty("sstart")
  SSTART("sstart"),

  @JsonProperty("sstrand")
  SSTRAND("sstrand"),

  @JsonProperty("staxid")
  STAXID("staxid"),

  @JsonProperty("staxids")
  STAXIDS("staxids"),

  @JsonProperty("stitle")
  STITLE("stitle");

  private String name;

  InputBlastFmtField(String name) {
    this.name = name;
  }
}
