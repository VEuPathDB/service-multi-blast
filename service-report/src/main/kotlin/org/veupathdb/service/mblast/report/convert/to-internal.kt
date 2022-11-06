package org.veupathdb.service.mblast.report.convert

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.service.mblast.report.generated.model.*

fun BlastFormatConfig?.toInternal() =
  if (this == null)
    Blast.blastFormatter()
  else
    Blast.blastFormatter().also {
      it.outFormat = toOutFormat()

      if (showGIs != null)            it.showGIs(showGIs)
      if (numDescriptions != null)    it.numDescriptions(numDescriptions.toUInt())
      if (numAlignments != null)      it.numAlignments(numAlignments.toUInt())
      if (lineLength != null)         it.lineLength(lineLength.toUInt())
      if (sortHits != null)           it.sortHits(sortHits.toInternal())
      if (sortHSPs != null)           it.sortHSPs(sortHSPs.toInternal())
      if (maxTargetSequences != null) it.maxTargetSeqs(maxTargetSequences.toUInt())
      if (parseDefLines != null)      it.parseDefLines(parseDefLines)
    }

private fun BlastHitSorting.toInternal() =
  when (this) {
    BlastHitSorting.BYEVALUE          -> HitSorting.ByExpectValue
    BlastHitSorting.BYBITSCORE        -> HitSorting.ByBitScore
    BlastHitSorting.BYTOTALSCORE      -> HitSorting.ByTotalScore
    BlastHitSorting.BYPERCENTIDENTITY -> HitSorting.ByPercentIdentity
    BlastHitSorting.BYQUERYCOVERAGE   -> HitSorting.ByQueryCoverage
  }

private fun BlastHSPSorting.toInternal() =
  when (this) {
    BlastHSPSorting.BYHSPEVALUE          -> HSPSorting.ByHSPExpectValue
    BlastHSPSorting.BYHSPSCORE           -> HSPSorting.ByHSPScore
    BlastHSPSorting.BYHSPQUERYSTART      -> HSPSorting.ByHSPQueryStart
    BlastHSPSorting.BYHSPPERCENTIDENTITY -> HSPSorting.ByHSPPercentIdentity
    BlastHSPSorting.BYHSPSUBJECTSTART    -> HSPSorting.ByHSPSubjectStart
  }

private fun BlastFormatConfig.toOutFormat(): OutFormat {
  val type = formatType?.toInternal() ?: FormatType.Pairwise

  if (formatFields.isNullOrEmpty())
    return OutFormat(type)

  val fields = formatFields.toInternal()

  return OutFormat(type = type, fields = fields)
}

private fun List<BlastOutField>.toInternal(): FormatFields =
  FormatFields(map { it.toInternal() })

private fun BlastOutField.toInternal(): FormatField =
  when (this) {
    BlastOutField.QSEQID      -> FormatField.QuerySeqID
    BlastOutField.QGI         -> FormatField.QueryGI
    BlastOutField.QACC        -> FormatField.QueryAccession
    BlastOutField.QACCVER     -> FormatField.QueryAccessionVersion
    BlastOutField.QLEN        -> FormatField.QuerySequenceLength
    BlastOutField.SSEQID      -> FormatField.SubjectSeqID
    BlastOutField.SALLSEQID   -> FormatField.AllSubjectSeqIDs
    BlastOutField.SGI         -> FormatField.SubjectGI
    BlastOutField.SALLGI      -> FormatField.AllSubjectGIs
    BlastOutField.SACC        -> FormatField.SubjectAccession
    BlastOutField.SACCVER     -> FormatField.SubjectAccessionVersion
    BlastOutField.SALLACC     -> FormatField.AllSubjectAccessions
    BlastOutField.SLEN        -> FormatField.SubjectSequenceLength
    BlastOutField.QSTART      -> FormatField.StartOfAlignmentInQuery
    BlastOutField.QEND        -> FormatField.EndOfAlignmentInQuery
    BlastOutField.SSTART      -> FormatField.StartOfAlignmentInSubject
    BlastOutField.SEND        -> FormatField.EndOfAlignmentInSubject
    BlastOutField.QSEQ        -> FormatField.AlignedPartOfQuerySequence
    BlastOutField.SSEQ        -> FormatField.AlignedPartOfSubjectSequence
    BlastOutField.EVALUE      -> FormatField.ExpectValue
    BlastOutField.BITSCORE    -> FormatField.BitScore
    BlastOutField.SCORE       -> FormatField.RawScore
    BlastOutField.LENGTH      -> FormatField.AlignmentLength
    BlastOutField.PIDENT      -> FormatField.PercentageOfIdenticalMatches
    BlastOutField.NIDENT      -> FormatField.NumberOfIdenticalMatches
    BlastOutField.MISMATCH    -> FormatField.NumberOfMismatches
    BlastOutField.POSITIVE    -> FormatField.NumberOfPositiveScoringMatches
    BlastOutField.GAPOPEN     -> FormatField.NumberOfGapOpenings
    BlastOutField.GAPS        -> FormatField.TotalNumberOfGaps
    BlastOutField.PPOS        -> FormatField.PercentageOfPositiveScoringMatches
    BlastOutField.FRAMES      -> FormatField.QueryAndSubjectFrames
    BlastOutField.QFRAME      -> FormatField.QueryFrame
    BlastOutField.SFRAME      -> FormatField.SubjectFrame
    BlastOutField.BTOP        -> FormatField.BlastTracebackOperations
    BlastOutField.STAXID      -> FormatField.SubjectTaxonomyID
    BlastOutField.SSCINAME    -> FormatField.SubjectScientificName
    BlastOutField.SCOMNAME    -> FormatField.SubjectCommonName
    BlastOutField.SBLASTNAME  -> FormatField.SubjectBlastName
    BlastOutField.SSKINGDOM   -> FormatField.SubjectSuperKingdom
    BlastOutField.STAXIDS     -> FormatField.UniqueSubjectTaxonomyIDs
    BlastOutField.SSCINAMES   -> FormatField.UniqueSubjectScientificNames
    BlastOutField.SCOMNAMES   -> FormatField.UniqueSubjectCommonNames
    BlastOutField.SBLASTNAMES -> FormatField.UniqueSubjectBlastNames
    BlastOutField.SSKINGDOMS  -> FormatField.UniqueSubjectSuperKingdoms
    BlastOutField.STITLE      -> FormatField.SubjectTitle
    BlastOutField.SALLTITLES  -> FormatField.AllSubjectTitles
    BlastOutField.SSTRAND     -> FormatField.SubjectStrand
    BlastOutField.QCOVS       -> FormatField.QueryCoveragePerSubject
    BlastOutField.QCOVHSP     -> FormatField.QueryCoveragePerHSP
    BlastOutField.QCOVUS      -> FormatField.QueryCoveragePerUniqueSubject
    BlastOutField.SQ          -> FormatField.IncludeSequenceData
    BlastOutField.SR          -> FormatField.SubjectAsReferenceSeq
    BlastOutField.STD         -> FormatField.StandardFields
  }

private fun BlastOutFormat.toInternal(): FormatType =
  when (this) {
    BlastOutFormat.PAIRWISE                        -> FormatType.Pairwise
    BlastOutFormat.QUERYANCHOREDWITHIDENTITIES     -> FormatType.QueryAnchoredShowingIdentities
    BlastOutFormat.QUERYANCHOREDNOIDENTITIES       -> FormatType.QueryAnchoredNoIdentities
    BlastOutFormat.FLATQUERYANCHOREDWITHIDENTITIES -> FormatType.FlatQueryAnchoredShowingIdentities
    BlastOutFormat.FLATQUERYANCHOREDNOIDENTITIES   -> FormatType.FlatQueryAnchoredNoIdentities
    BlastOutFormat.XML                             -> FormatType.BlastXML
    BlastOutFormat.TABULAR                         -> FormatType.Tabular
    BlastOutFormat.TABULARWITHCOMMENTS             -> FormatType.TabularWithCommentLines
    BlastOutFormat.SEQALIGNTEXT                    -> FormatType.SeqAlignTextASN1
    BlastOutFormat.SEQALIGNBINARY                  -> FormatType.SeqAlignBinaryASN1
    BlastOutFormat.CSV                             -> FormatType.CommaSeparatedValues
    BlastOutFormat.ASN1                            -> FormatType.BlastArchiveASN1
    BlastOutFormat.SEQALIGNJSON                    -> FormatType.SeqAlignJSON
    BlastOutFormat.MULTIFILEBLASTJSON              -> FormatType.MultipleFileBlastJSON
    BlastOutFormat.MULTIFILEBLASTXML2              -> FormatType.MultipleFileBlastXML2
    BlastOutFormat.SINGLEFILEBLASTJSON             -> FormatType.SingleFileBlastJSON
    BlastOutFormat.SINGLEFILEBLASTXML2             -> FormatType.SingleFileBlastXML2
    BlastOutFormat.SAM                             -> FormatType.SequenceAlignmentMap
    BlastOutFormat.ORGANISMREPORT                  -> FormatType.OrganismReport
  }