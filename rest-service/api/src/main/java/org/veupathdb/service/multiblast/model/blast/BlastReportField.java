package org.veupathdb.service.multiblast.model.blast;

public enum BlastReportField
{
  /**
   * Query Seq-id
   */
  QuerySequenceID("qseqid"),

  /**
   * Query GI
   */
  QueryGenInfo("qgi"),

  /**
   * Query accesion
   */
  QueryAccession("qacc"),

  /**
   * Query accesion.version
   */
  QueryAccessionVersion("qaccver"),

  /**
   * Query sequence length
   */
  QuerySequenceLength("qlen"),

  /**
   * Subject Seq-id
   */
  SubjectSequenceID("sseqid"),

  /**
   * All subject Seq-id(s), separated by a ';'
   */
  SubjectAllSequenceID("sallseqid"),

  /**
   * Subject GI
   */
  SubjectGenInfo("sgi"),

  /**
   * All subject GIs
   */
  SUBJECT_ALL_GI("sallgi"),

  /**
   * Subject accession
   */
  SUBJECT_ACCESSION("sacc"),

  /**
   * Subject accession.version
   */
  SUBJECT_ACCESSION_VERSION("saccver"),

  /**
   * All subject accessions
   */
  SUBJECT_ALL_ACCESSION("sallacc"),

  /**
   * Subject sequence length
   */
  SUBJECT_SEQUENCE_LENGTH("slen"),

  /**
   * Start of alignment in query
   */
  QUERY_ALIGNMENT_START("qstart"),

  /**
   * End of alignment in query
   */
  QUERY_ALIGNMENT_END("qend"),

  /**
   * Start of alignment in subject
   */
  SUBJECT_ALIGNMENT_START("sstart"),

  /**
   * End of alignment in subject
   */
  SUBJECT_ALIGNMENT_END("send"),

  /**
   * Aligned part of query sequence
   */
  QUERY_SEQUENCE("qseq"),

  /**
   * Aligned part of subject sequence
   */
  SUBJECT_SEQUENCE("sseq"),

  /**
   * Expect value
   */
  EXPECT_VALUE("evalue"),

  /**
   * Bit score
   */
  BIT_SCORE("bitscore"),

  /**
   * Raw score
   */
  RAW_SCORE("score"),

  /**
   * Alignment length
   */
  ALIGNMENT_LENGTH("length"),

  /**
   * Percentage of identical matches
   */
  PERCENT_IDENTICAL_MATCHES("pident"),

  /**
   * Number of identical matches
   */
  NUMBER_IDENTICAL_MATCHES("nident"),

  /**
   * Number of mismatches
   */
  NUMBER_MISMATCHES("mismatch"),

  /**
   * Number of positive-scoring matches
   */
  NUMBER_POSITIVE_MATCHES("positive"),

  /**
   * Number of gap openings
   */
  NUMBER_GAP_OPENINGS("gapopen"),

  /**
   * Total number of gaps
   */
  NUMBER_GAPS("gaps"),

  /**
   * Percentage of positive-scoring matches
   */
  PERCENT_POSITIVE_MATCHES("ppos"),

  /**
   * Query and subject frames separated by a '/'
   */
  FRAMES("frames"),

  /**
   * Query frame
   */
  QUERY_FRAME("qframe"),

  /**
   * Subject frame
   */
  SUBJECT_FRAME("sframe"),

  /**
   * Blast traceback operations (BTOP)
   */
  BLAST_TRACEBACK_OPS("btop"),

  /**
   * Subject Taxonomy ID
   */
  SUBJECT_TAXONOMY_ID("staxid"),

  /**
   * Subject Scientific Name
   */
  SUBJECT_SCIENTIFIC_NAME("ssciname"),

  /**
   * Subject Common Name
   */
  SUBJECT_COMMON_NAME("scomname"),

  /**
   * Subject Blast Name
   */
  SUBJECT_BLAST_NAME("sblastname"),

  /**
   * Subject Super Kingdom
   */
  SUBJECT_SUPER_KINGDOM("sskingdom"),

  /**
   * unique Subject Taxonomy ID(s), separated by a ';' (in numerical order)
   */
  SUBJECT_UNIQUE_TAXONOMY_IDS("staxids"),

  /**
   * unique Subject Scientific Name(s), separated by a ';'
   */
  SUBJECT_SCIENTIFIC_NAMES("sscinames"),

  /**
   * unique Subject Common Name(s), separated by a ';'
   */
  SUBJECT_COMMON_NAMES("scomnames"),

  /**
   * unique Subject Blast Name(s), separated by a ';' (in alphabetical order)
   */
  SUBJECT_BLAST_NAMES("sblastnames"),

  /**
   * unique Subject Super Kingdom(s), separated by a ';' (in alphabetical
   * order)
   */
  SUBJECT_SUPER_KINGDOMS("sskingdoms"),

  /**
   * Subject Title
   */
  SUBJECT_TITLE("stitle"),

  /**
   * All Subject Title(s), separated by a '<>'
   */
  SUBJECT_ALL_TITLES("salltitles"),

  /**
   * Subject Strand
   */
  SUBJECT_STRAND("sstrand"),

  /**
   * Query Coverage Per Subject
   */
  QUERY_COVERAGE_PER_SUBJECT("qcovs"),

  /**
   * Query Coverage Per HSP
   */
  QUERY_COVERAGE_PER_HSP("qcovhsp"),

  /**
   * Query Coverage Per Unique Subject (blastn only)
   */
  QUERY_COVERAGE_PER_UNIQUE_SUBJECT("qcovus"),

  /**
   * Include Sequence Data
   */
  SQ("SQ"),

  /**
   * Subject as Reference Seq
   */
  SR("SR"),

  /**
   * Standard (default) field set specifier.
   */
  Standard("std");

  private final String value;

  BlastReportField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  public static BlastReportField fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return e;

    throw new IllegalArgumentException("Unrecognized BlastReportField value: " + value);
  }
}
