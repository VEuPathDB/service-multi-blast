package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagOutFormat
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseOutFormat(js: ObjectNode) =
  js.optObject(FlagOutFormat) {
    OutFormat(
      parseFormatType(it),
      parseFormatDelimiter(it),
      parseFormatFields(it)
    )
  } ?: OutFormat()


/**
 * -outfmt `<String>`
 *
 * Alignment view options:
 * ```
 * 0  = Pairwise
 * 1  = Query-anchored showing identities
 * 2  = Query-anchored no identities
 * 3  = Flat query-anchored showing identities
 * 4  = Flat query-anchored no identities
 * 5  = BLAST XML
 * 6  = Tabular
 * 7  = Tabular with comment lines
 * 8  = Seqalign (Text ASN.1)
 * 9  = Seqalign (Binary ASN.1)
 * 10 = Comma-separated values
 * 11 = BLAST archive (ASN.1)
 * 12 = Seqalign (JSON)
 * 13 = Multiple-file BLAST JSON
 * 14 = Multiple-file BLAST XML2
 * 15 = Single-file BLAST JSON
 * 16 = Single-file BLAST XML2
 * 17 = Sequence Alignment/Map (SAM)
 * 18 = Organism Report
 * ```
 *
 * Options 6, 7, 10 and 17 can be additionally configured to produce a custom
 * format specified by space delimited format specifiers, or in the case of
 * options 6, 7, and 10, by a token specified by the delim keyword.
 * E.g.: "17 delim=@ qacc sacc score".
 *
 * The delim keyword must appear after the numeric output format specification.
 *
 * The supported format specifiers for options 6, 7 and 10 are:
 *
 * ```
 * qseqid     means Query Seq-id
 * qgi        means Query GI
 * qacc       means Query accesion
 * qaccver    means Query accesion.version
 * qlen       means Query sequence length
 * sseqid     means Subject Seq-id
 * sallseqid  means All subject Seq-id(s), separated by a ';'
 * sgi        means Subject GI
 * sallgi     means All subject GIs
 * sacc       means Subject accession
 * saccver    means Subject accession.version
 * sallacc    means All subject accessions
 * slen       means Subject sequence length
 * qstart     means Start of alignment in query
 * qend       means End of alignment in query
 * sstart     means Start of alignment in subject
 * send       means End of alignment in subject
 * qseq       means Aligned part of query sequence
 * sseq       means Aligned part of subject sequence
 * evalue     means Expect value
 * bitscore   means Bit score
 * score      means Raw score
 * length     means Alignment length
 * pident     means Percentage of identical matches
 * nident     means Number of identical matches
 * mismatch   means Number of mismatches
 * positive   means Number of positive-scoring matches
 * gapopen    means Number of gap openings
 * gaps       means Total number of gaps
 * ppos       means Percentage of positive-scoring matches
 * frames     means Query and subject frames separated by a '/'
 * qframe     means Query frame
 * sframe     means Subject frame
 * btop       means Blast traceback operations (BTOP)
 * staxid     means Subject Taxonomy ID
 * ssciname   means Subject Scientific Name
 * scomname   means Subject Common Name
 * sblastname means Subject Blast Name
 * sskingdom  means Subject Super Kingdom
 * staxids    means unique Subject Taxonomy ID(s), separated by a ';'
 *                  (in numerical order)
 * sscinames  means unique Subject Scientific Name(s), separated by a ';'
 * scomnames  means unique Subject Common Name(s), separated by a ';'
 * sblastnamesmeans unique Subject Blast Name(s), separated by a ';'
 *                  (in alphabetical order)
 * sskingdoms means unique Subject Super Kingdom(s), separated by a ';'
 *                  (in alphabetical order)
 * stitle     means Subject Title
 * salltitles means All Subject Title(s), separated by a '<>'
 * sstrand    means Subject Strand
 * qcovs      means Query Coverage Per Subject
 * qcovhsp    means Query Coverage Per HSP
 * qcovus     means Query Coverage Per Unique Subject (blastn only)
 * ```
 *
 * When not provided, the default value is:
 * ```
 * qaccver saccver pident length mismatch gapopen qstart qend sstart send evalue bitscore
 * ```
 * which is equivalent to the keyword `std`
 *
 * The supported format specifier for option 17 is:
 * ```
 * SQ means Include Sequence Data
 * SR means Subject as Reference Seq
 * ```
 * Default = `0`
 */
data class OutFormat(
  var type: FormatType = DefaultFormatType,
  var delimiter: FormatDelimiter = FormatDelimiter(),
  val fields: FormatFields = FormatFields(),
) : BlastField {
  override val isDefault: Boolean
    get() = type.isDefault && delimiter.isDefault && fields.isDefault

  override val name: String
    get() = FlagOutFormat

  override fun appendJson(js: ObjectNode) {
    if (!isDefault)
      with(js.putObject(FlagOutFormat)) {
        type.appendJson(this)
        delimiter.appendJson(this)
        fields.appendJson(this)
      }
  }

  override fun appendCliSegment(cli: StringBuilder) {
    if (isDefault)
      return

    // Always insert the format type if we are adding this flag to the CLI call.
    cli.append(' ')
      .append(FlagOutFormat)
      .append(" '")
      .append(type.value)

    delimiter.appendCliSegment(cli)
    fields.appendCliSegment(cli)

    cli.append('\'')
  }

  override fun appendCliParts(cli: MutableList<String>) {
    if (!isDefault) {
      cli.add(FlagOutFormat)

      val tmp = StringBuilder(256)

      tmp.append(type.value)
      delimiter.appendCliSegment(tmp)
      fields.appendCliSegment(tmp)

      cli.add(tmp.toString())
    }
  }
}


////////////////////////////////////////////////////////////////////////////////


private const val KeyDelimiter = "delimiter"


private fun parseFormatDelimiter(js: ObjectNode) =
  js[KeyDelimiter]?.let {
    FormatDelimiter(it.reqString { "$FlagOutFormat.$KeyDelimiter" })
  } ?: FormatDelimiter()


@JvmInline
value class FormatDelimiter(val value: String = "") {
  val isDefault get() = value == ""

  fun appendJson(json: ObjectNode) {
    if (!isDefault)
      json.put(KeyDelimiter, value)
  }

  fun appendCliSegment(cli: StringBuilder) {
    if (!isDefault)
      cli.append(" delim=").append(value)
  }
}


////////////////////////////////////////////////////////////////////////////////


private const val KeyFormatFields = "fields"


private fun parseFormatFields(js: ObjectNode): FormatFields {
  // Require node is a JSON array (or absent)
  val arr = js[KeyFormatFields]?.reqArray {
    "$FlagOutFormat.$KeyFormatFields"
  }
    ?: return FormatFields()

  // If array is empty, return default
  arr.size() > 0 || return FormatFields()

  val tmp = ArrayList<FormatField>(arr.size())

  arr.forEach {
    tmp.add(parseField(it.reqString { "$FlagOutFormat.$KeyFormatFields" }))
  }

  return FormatFields(tmp)
}


@JvmInline
value class FormatFields(
  val value: List<FormatField> = listOf(DefaultFormatField),
) {
  val isDefault
    get() = value.isEmpty() || (value.size == 1 && value[0].isDefault)

  fun appendJson(json: ObjectNode) {
    if (!isDefault)
      with(json.putArray(KeyFormatFields)) {
        value.forEach {
          add(it.value)
        }
      }
  }

  fun appendCliSegment(cli: StringBuilder) {
    if (!isDefault)
      value.forEach { cli.append(' ').append(it.value) }
  }
}


////////////////////////////////////////////////////////////////////////////////


private val DefaultFormatType = FormatType.Pairwise

private const val KeyFormatType = "type"


private fun parseFormatType(js: ObjectNode): FormatType {
  val tmp = js[KeyFormatType]?.reqInt { "$FlagOutFormat.$KeyFormatType" }
    ?: return DefaultFormatType

  val vals = FormatType.values()

  if (tmp < 0 || tmp >= vals.size)
    throw IllegalArgumentException("$FlagOutFormat.$KeyFormatType must be an int value > 0 and < ${vals.size}")

  return vals[tmp]
}


enum class FormatType {
  Pairwise,
  QueryAnchoredShowingIdentities,
  QueryAnchoredNoIdentities,
  FlatQueryAnchoredShowingIdentities,
  FlatQueryAnchoredNoIdentities,
  BlastXML,
  Tabular,
  TabularWithCommentLines,
  SeqAlignTextASN1,
  SeqAlignBinaryASN1,
  CommaSeparatedValues,
  BlastArchiveASN1,
  SeqAlignJSON,
  MultipleFileBlastJSON,
  MultipleFileBlastXML2,
  SingleFileBlastJSON,
  SingleFileBlastXML2,
  SequenceAlignmentMap,
  OrganismReport;

  val value = ordinal

  val isDefault get() = this == DefaultFormatType

  fun appendJson(json: ObjectNode) {
    if (!isDefault)
      json.put(KeyFormatType, value)
  }

  fun appendCliSegment(cli: StringBuilder) {
    if (!isDefault)
      cli.append(value)
  }
}


////////////////////////////////////////////////////////////////////////////////


private val DefaultFormatField = FormatField.StandardFields


private fun parseField(value: String): FormatField {
  for (v in FormatField.values())
    if (v.value == value)
      return v

  throw IllegalArgumentException("Invalid value for $FlagOutFormat.$KeyFormatFields: $value")
}


enum class FormatField(val value: String) {
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
  StandardFields("std");

  val isDefault: Boolean get() = this == DefaultFormatField
}
