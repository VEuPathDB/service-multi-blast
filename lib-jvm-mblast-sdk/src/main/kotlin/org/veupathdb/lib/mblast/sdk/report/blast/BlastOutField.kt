package org.veupathdb.lib.mblast.sdk.report.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import org.veupathdb.lib.mblast.sdk.util.NonStringEnumException
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException

enum class BlastOutField(
  @get:JsonValue
  val internalValue: String
) {
  QuerySeqID("qseqid"),
  QueryGI("qgi"),
  QueryAccession("qacc"),
  QueryAccessionVersion("qaccver"),
  QuerySequenceLength("qlen"),
  SubjectSeqID("sseqid"),
  AllSubjectSeqIDs("sallseqid"),
  SubjectGI("sgi"),
  AllSubjectGIs("sallgi"),
  SubjectAccession("sacc"),
  SubjectAccessionVersion("saccver"),
  AllSubjectAccessions("sallacc"),
  SubjectSequenceLength("slen"),
  StartOfAlignmentInQuery("qstart"),
  EndOfAlignmentInQuery("qend"),
  StartOfAlignmentInSubject("sstart"),
  EndOfAlignmentInSubject("send"),
  AlignedPartOfQuerySequence("qseq"),
  AlignedPartOfSubjectSequence("sseq"),
  ExpectValue("evalue"),
  BitScore("bitscore"),
  RawScore("score"),
  AlignmentLength("length"),
  PercentageOfIdenticalMatches("pident"),
  NumberOfIdenticalMatches("nident"),
  NumberOfMismatches("mismatch"),
  NumberOfPositiveScoringMatches("positive"),
  NumberOfGapOpenings("gapopen"),
  TotalNumberOfGaps("gaps"),
  PercentageOfPositiveScoringMatches("ppos"),
  QueryAndSubjectFrames("frames"),
  QueryFrame("qframe"),
  SubjectFrame("sframe"),
  BlastTracebackOperations("btop"),
  SubjectTaxonomyID("staxid"),
  SubjectScientificName("ssciname"),
  SubjectCommonName("scomname"),
  SubjectBlastName("sblastname"),
  SubjectSuperKingdom("sskingdom"),
  UniqueSubjectTaxonomyIDs("staxids"),
  UniqueSubjectScientificNames("sscinames"),
  UniqueSubjectCommonNames("scomnames"),
  UniqueSubjectBlastNames("sblastnames"),
  UniqueSubjectSuperKingdoms("sskingdoms"),
  SubjectTitle("stitle"),
  AllSubjectTitles("salltitles"),
  SubjectStrand("sstrand"),
  QueryCoveragePerSubject("qcovs"),
  QueryCoveragePerHSP("qcovhsp"),
  QueryCoveragePerUniqueSubject("qcovus"),
  IncludeSequenceData("SQ"),
  SubjectAsReferenceSeq("SR"),
  StandardFields("std"),
  ;

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastOutField {
      if (!js.isTextual)
        throw NonStringEnumException(BlastOutField::class)

      for (v in values())
        if (js.textValue() == v.internalValue)
          return v

      throw UnrecognizedEnumException(BlastOutField::class, js.textValue())
    }
  }
}