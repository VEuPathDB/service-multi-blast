package mb.lib.blast.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mb.api.service.util.ErrorText;

@SuppressWarnings("SpellCheckingInspection")
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
  SubjectAllGenInfo("sallgi"),

  /**
   * Subject accession
   */
  SubjectAccession("sacc"),

  /**
   * Subject accession.version
   */
  SubjectAccessionVersion("saccver"),

  /**
   * All subject accessions
   */
  SubjectAllAccession("sallacc"),

  /**
   * Subject sequence length
   */
  SubjectSequenceLength("slen"),

  /**
   * Start of alignment in query
   */
  QueryAlignmentStart("qstart"),

  /**
   * End of alignment in query
   */
  QueryAlignmentEnd("qend"),

  /**
   * Start of alignment in subject
   */
  SubjectAlignmentStart("sstart"),

  /**
   * End of alignment in subject
   */
  SubjectAlignmentEnd("send"),

  /**
   * Aligned part of query sequence
   */
  QuerySequence("qseq"),

  /**
   * Aligned part of subject sequence
   */
  SubjectSequence("sseq"),

  /**
   * Expect value
   */
  ExpectValue("evalue"),

  /**
   * Bit score
   */
  BitScore("bitscore"),

  /**
   * Raw score
   */
  RawScore("score"),

  /**
   * Alignment length
   */
  AlignmentLength("length"),

  /**
   * Percentage of identical matches
   */
  PercentIdenticalMatches("pident"),

  /**
   * Number of identical matches
   */
  NumberIdenticalMatches("nident"),

  /**
   * Number of mismatches
   */
  NumberMismatches("mismatch"),

  /**
   * Number of positive-scoring matches
   */
  NumberPositiveMatches("positive"),

  /**
   * Number of gap openings
   */
  NumberGapOpenings("gapopen"),

  /**
   * Total number of gaps
   */
  NumberGaps("gaps"),

  /**
   * Percentage of positive-scoring matches
   */
  PercentPositiveMatches("ppos"),

  /**
   * Query and subject frames separated by a '/'
   */
  Frames("frames"),

  /**
   * Query frame
   */
  QueryFrame("qframe"),

  /**
   * Subject frame
   */
  SubjectFrame("sframe"),

  /**
   * Blast traceback operations (BTOP)
   */
  BlastTracebackOps("btop"),

  /**
   * Subject Taxonomy ID
   */
  SubjectTaxonomyID("staxid"),

  /**
   * Subject Scientific Name
   */
  SubjectScientificName("ssciname"),

  /**
   * Subject Common Name
   */
  SubjectCommonName("scomname"),

  /**
   * Subject Blast Name
   */
  SubjectBlastName("sblastname"),

  /**
   * Subject Super Kingdom
   */
  SubjectSuperKingdom("sskingdom"),

  /**
   * unique Subject Taxonomy ID(s), separated by a ';' (in numerical order)
   */
  SubjectUniqueTaxonomyIDs("staxids"),

  /**
   * unique Subject Scientific Name(s), separated by a ';'
   */
  SubjectScientificNames("sscinames"),

  /**
   * unique Subject Common Name(s), separated by a ';'
   */
  SubjectCommonNames("scomnames"),

  /**
   * unique Subject Blast Name(s), separated by a ';' (in alphabetical order)
   */
  SubjectBlastNames("sblastnames"),

  /**
   * unique Subject Super Kingdom(s), separated by a ';' (in alphabetical
   * order)
   */
  SubjectSuperKingdoms("sskingdoms"),

  /**
   * Subject Title
   */
  SubjectTitle("stitle"),

  /**
   * All Subject Title(s), separated by a '<>'
   */
  SubjectAllTitles("salltitles"),

  /**
   * Subject Strand
   */
  SubjectStrand("sstrand"),

  /**
   * Query Coverage Per Subject
   */
  QueryCoveragePerSubject("qcovs"),

  /**
   * Query Coverage Per HSP
   */
  QueryCoveragePerHSP("qcovhsp"),

  /**
   * Query Coverage Per Unique Subject (blastn only)
   */
  QueryCoveragePerUniqueSubject("qcovus"),

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

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  @JsonCreator
  public static BlastReportField fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return e;

    throw new IllegalArgumentException(String.format(
      ErrorText.InvalidEnumValue,
      value,
      BlastReportField.class.getSimpleName()
    ));
  }
}
