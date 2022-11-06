package org.veupathdb.service.mblast.report.ext

import org.veupathdb.lib.blast.common.fields.FormatType
import org.veupathdb.lib.blast.formatter.BlastFormatter
import org.veupathdb.service.mblast.report.Const

fun BlastFormatter.makeOutFileName(): String =
  when (outFormat.type) {
    FormatType.Pairwise,
    FormatType.QueryAnchoredShowingIdentities,
    FormatType.QueryAnchoredNoIdentities,
    FormatType.FlatQueryAnchoredShowingIdentities,
    FormatType.FlatQueryAnchoredNoIdentities,
    FormatType.SequenceAlignmentMap,
    FormatType.OrganismReport,
    -> "${Const.OUTPUT_FILE_PREFIX}.txt"

    FormatType.BlastXML,
    FormatType.MultipleFileBlastXML2,
    FormatType.SingleFileBlastXML2,
    -> "${Const.OUTPUT_FILE_PREFIX}.xml"

    FormatType.Tabular,
    FormatType.TabularWithCommentLines,
    -> "${Const.OUTPUT_FILE_PREFIX}.tsv"

    FormatType.SeqAlignTextASN1,
    FormatType.SeqAlignBinaryASN1,
    FormatType.BlastArchiveASN1,
    -> "${Const.OUTPUT_FILE_PREFIX}.asn1"

    FormatType.CommaSeparatedValues,
    -> "${Const.OUTPUT_FILE_PREFIX}.csv"

    FormatType.SeqAlignJSON,
    FormatType.MultipleFileBlastJSON,
    FormatType.SingleFileBlastJSON,
    -> "${Const.OUTPUT_FILE_PREFIX}.json"
  }
