package org.veupathdb.service.multiblast.service.http.job;

import javax.ws.rs.BadRequestException;

import mb.lib.format.FormatType;
import org.veupathdb.service.multiblast.model.blast.BlastReportType;

public class JobReportService
{
  public static FormatType parseFormatString(String format) {
    try {
      return FormatType.fromID(Integer.parseInt(format));
    } catch (NumberFormatException ignored) {}

    return switch(BlastReportType.fromIoName(format)
      .orElseThrow(() -> new BadRequestException("unrecognized report format"))) {
      case Pairwise -> FormatType.Pairwise;
      case QueryAnchoredWithIdentities -> FormatType.QueryAnchoredWithIdentities;
      case QueryAnchoredWithoutIdentities -> FormatType.QueryAnchoredWithoutIdentities;
      case FlatQueryAnchoredWithIdentities -> FormatType.FlatQueryAnchoredWithIdentities;
      case FlatQueryAnchoredWithoutIdentities -> FormatType.FlatQueryAnchoredWithoutIdentities;
      case XML -> FormatType.BlastXML;
      case Tabular -> FormatType.Tabular;
      case TabularWithComments -> FormatType.TabularWithCommentLines;
      case TextASN1 -> FormatType.SeqAlignTextASN1;
      case BinaryASN1 -> FormatType.SeqAlignBinaryASN1;
      case CSV -> FormatType.CommaSeparatedValues;
      case ArchiveASN1 -> FormatType.BlastArchiveASN1;
      case SeqAlignJSON -> FormatType.SeqAlignJSON;
      case MultiFileJSON -> FormatType.MultipleFileBlastJSON;
      case MultiFileXML2 -> FormatType.MultipleFileBlastXML2;
      case SingleFileJSON -> FormatType.SingleFileBlastJSON;
      case SingleFileXML2 -> FormatType.SingleFileBlastXML2;
      case SAM -> FormatType.SequenceAlignmentMap;
      case OrganismReport -> FormatType.OrganismReport;
    };
  }
}
