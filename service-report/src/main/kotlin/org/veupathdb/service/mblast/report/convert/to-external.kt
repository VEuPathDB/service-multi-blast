package org.veupathdb.service.mblast.report.convert

import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.formatter.BlastFormatter
import org.veupathdb.service.mblast.report.generated.model.*

fun BlastFormatter.toExternal(): BlastFormatConfig {
  return BlastFormatConfigImpl().also {
    it.formatType         = outFormat.type.toExternal()
    it.formatFields       = outFormat.fields.toExternal()
    it.showGIs            = showGIs.toExternal()
    it.numDescriptions    = numDescriptions.toExternal()
    it.numAlignments      = numAlignments.toExternal()
    it.lineLength         = lineLength.toExternal()
    it.sortHits           = sortHits.toExternal()
    it.sortHSPs           = sortHSPs.toExternal()
    it.maxTargetSequences = maxTargetSeqs.toExternal()
    it.parseDefLines      = parseDefLines.toExternal()
  }
}

private fun ParseDefLines.toExternal(): Boolean? =
  if (isDefault) null else value

private fun MaxTargetSeqs.toExternal(): Int? =
  if (isDefault) null else value.toInt()

private fun ShowGIs.toExternal(): Boolean? =
  if (isDefault) null else value

private fun NumDescriptions.toExternal(): Int? =
  if (isDefault) null else value.toInt()

private fun NumAlignments.toExternal(): Int? =
  if (isDefault) null else value.toInt()

private fun LineLength.toExternal(): Int? =
  if (isDefault) null else value.toInt()

private fun SortHits.toExternal(): BlastHitSorting? =
  when(value) {
    HitSorting.ByExpectValue     -> BlastHitSorting.BYEVALUE
    HitSorting.ByBitScore        -> BlastHitSorting.BYBITSCORE
    HitSorting.ByTotalScore      -> BlastHitSorting.BYTOTALSCORE
    HitSorting.ByPercentIdentity -> BlastHitSorting.BYPERCENTIDENTITY
    HitSorting.ByQueryCoverage   -> BlastHitSorting.BYQUERYCOVERAGE
    HitSorting.None              -> null
  }

private fun SortHSPs.toExternal(): BlastHSPSorting? =
  when (value) {
    HSPSorting.ByHSPExpectValue     -> BlastHSPSorting.BYHSPEVALUE
    HSPSorting.ByHSPScore           -> BlastHSPSorting.BYHSPSCORE
    HSPSorting.ByHSPQueryStart      -> BlastHSPSorting.BYHSPQUERYSTART
    HSPSorting.ByHSPPercentIdentity -> BlastHSPSorting.BYHSPPERCENTIDENTITY
    HSPSorting.ByHSPSubjectStart    -> BlastHSPSorting.BYHSPSUBJECTSTART
    HSPSorting.None                 -> null
  }

private fun FormatFields.toExternal(): List<BlastOutField>? =
  if (isDefault) null else value.map { it.toExternal() }

private fun FormatField.toExternal(): BlastOutField =
  when (this) {
    FormatField.QuerySeqID                         -> BlastOutField.QSEQID
    FormatField.QueryGI                            -> BlastOutField.QGI
    FormatField.QueryAccession                     -> BlastOutField.QACC
    FormatField.QueryAccessionVersion              -> BlastOutField.QACCVER
    FormatField.QuerySequenceLength                -> BlastOutField.QLEN
    FormatField.SubjectSeqID                       -> BlastOutField.SSEQID
    FormatField.AllSubjectSeqIDs                   -> BlastOutField.SALLSEQID
    FormatField.SubjectGI                          -> BlastOutField.SGI
    FormatField.AllSubjectGIs                      -> BlastOutField.SALLGI
    FormatField.SubjectAccession                   -> BlastOutField.SACC
    FormatField.SubjectAccessionVersion            -> BlastOutField.SACCVER
    FormatField.AllSubjectAccessions               -> BlastOutField.SALLACC
    FormatField.SubjectSequenceLength              -> BlastOutField.SLEN
    FormatField.StartOfAlignmentInQuery            -> BlastOutField.QSTART
    FormatField.EndOfAlignmentInQuery              -> BlastOutField.QEND
    FormatField.StartOfAlignmentInSubject          -> BlastOutField.SSTART
    FormatField.EndOfAlignmentInSubject            -> BlastOutField.SEND
    FormatField.AlignedPartOfQuerySequence         -> BlastOutField.QSEQ
    FormatField.AlignedPartOfSubjectSequence       -> BlastOutField.SSEQ
    FormatField.ExpectValue                        -> BlastOutField.EVALUE
    FormatField.BitScore                           -> BlastOutField.BITSCORE
    FormatField.RawScore                           -> BlastOutField.SCORE
    FormatField.AlignmentLength                    -> BlastOutField.LENGTH
    FormatField.PercentageOfIdenticalMatches       -> BlastOutField.PIDENT
    FormatField.NumberOfIdenticalMatches           -> BlastOutField.NIDENT
    FormatField.NumberOfMismatches                 -> BlastOutField.MISMATCH
    FormatField.NumberOfPositiveScoringMatches     -> BlastOutField.POSITIVE
    FormatField.NumberOfGapOpenings                -> BlastOutField.GAPOPEN
    FormatField.TotalNumberOfGaps                  -> BlastOutField.GAPS
    FormatField.PercentageOfPositiveScoringMatches -> BlastOutField.PPOS
    FormatField.QueryAndSubjectFrames              -> BlastOutField.FRAMES
    FormatField.QueryFrame                         -> BlastOutField.QFRAME
    FormatField.SubjectFrame                       -> BlastOutField.SFRAME
    FormatField.BlastTracebackOperations           -> BlastOutField.BTOP
    FormatField.SubjectTaxonomyID                  -> BlastOutField.STAXID
    FormatField.SubjectScientificName              -> BlastOutField.SSCINAME
    FormatField.SubjectCommonName                  -> BlastOutField.SCOMNAME
    FormatField.SubjectBlastName                   -> BlastOutField.SBLASTNAME
    FormatField.SubjectSuperKingdom                -> BlastOutField.SSKINGDOM
    FormatField.UniqueSubjectTaxonomyIDs           -> BlastOutField.STAXIDS
    FormatField.UniqueSubjectScientificNames       -> BlastOutField.SSCINAMES
    FormatField.UniqueSubjectCommonNames           -> BlastOutField.SCOMNAMES
    FormatField.UniqueSubjectBlastNames            -> BlastOutField.SBLASTNAMES
    FormatField.UniqueSubjectSuperKingdoms         -> BlastOutField.SSKINGDOMS
    FormatField.SubjectTitle                       -> BlastOutField.STITLE
    FormatField.AllSubjectTitles                   -> BlastOutField.SALLTITLES
    FormatField.SubjectStrand                      -> BlastOutField.SSTRAND
    FormatField.QueryCoveragePerSubject            -> BlastOutField.QCOVS
    FormatField.QueryCoveragePerHSP                -> BlastOutField.QCOVHSP
    FormatField.QueryCoveragePerUniqueSubject      -> BlastOutField.QCOVUS
    FormatField.IncludeSequenceData                -> BlastOutField.SQ
    FormatField.SubjectAsReferenceSeq              -> BlastOutField.SR
    FormatField.StandardFields                     -> BlastOutField.STD
  }

fun FormatType.toExternal(): BlastOutFormat =
  when (this) {
    FormatType.Pairwise                           -> BlastOutFormat.PAIRWISE
    FormatType.QueryAnchoredShowingIdentities     -> BlastOutFormat.QUERYANCHOREDWITHIDENTITIES
    FormatType.QueryAnchoredNoIdentities          -> BlastOutFormat.QUERYANCHOREDNOIDENTITIES
    FormatType.FlatQueryAnchoredShowingIdentities -> BlastOutFormat.FLATQUERYANCHOREDWITHIDENTITIES
    FormatType.FlatQueryAnchoredNoIdentities      -> BlastOutFormat.FLATQUERYANCHOREDNOIDENTITIES
    FormatType.BlastXML                           -> BlastOutFormat.XML
    FormatType.Tabular                            -> BlastOutFormat.TABULAR
    FormatType.TabularWithCommentLines            -> BlastOutFormat.TABULARWITHCOMMENTS
    FormatType.SeqAlignTextASN1                   -> BlastOutFormat.SEQALIGNTEXT
    FormatType.SeqAlignBinaryASN1                 -> BlastOutFormat.SEQALIGNBINARY
    FormatType.CommaSeparatedValues               -> BlastOutFormat.CSV
    FormatType.BlastArchiveASN1                   -> BlastOutFormat.ASN1
    FormatType.SeqAlignJSON                       -> BlastOutFormat.SEQALIGNJSON
    FormatType.MultipleFileBlastJSON              -> BlastOutFormat.MULTIFILEBLASTJSON
    FormatType.MultipleFileBlastXML2              -> BlastOutFormat.MULTIFILEBLASTXML2
    FormatType.SingleFileBlastJSON                -> BlastOutFormat.SINGLEFILEBLASTJSON
    FormatType.SingleFileBlastXML2                -> BlastOutFormat.SINGLEFILEBLASTXML2
    FormatType.SequenceAlignmentMap               -> BlastOutFormat.SAM
    FormatType.OrganismReport                     -> BlastOutFormat.ORGANISMREPORT
  }