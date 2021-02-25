package org.veupathdb.service.multiblast.service.http.job;

import javax.ws.rs.BadRequestException;

import mb.lib.db.model.FullUserJobRow;
import mb.lib.extern.JobQueueManager;
import mb.lib.extern.model.QueueJobStatus;
import mb.lib.format.FormatType;
import mb.lib.jobData.JobDataManager;
import org.veupathdb.service.multiblast.model.blast.BlastReportType;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;
import org.veupathdb.service.multiblast.util.Format;

public class JobReportService
{
  public static void ensureJobCache(FullUserJobRow job, long userID) throws Exception {
    if (JobUtil.jobIsCached(job))
      return;

    var dets = new JobDetails();
    dets.hash   = job.jobHash();
    dets.query  = job.query();
    dets.id     = Format.toHexString(dets.hash);
    dets.job    = Job.fromSerial(job.config());
    dets.cli    = new CliBuilder();
    dets.userID = userID;

    dets.job.getJobConfig().toCli(dets.cli);

    var queueID = new JobCreator().handleRerun(dets);

    // Wait for job to complete
    QueueJobStatus stat;
    for (
      stat = JobQueueManager.jobStatus(queueID);
      stat == QueueJobStatus.Queued || stat == QueueJobStatus.InProgress;
      stat = JobQueueManager.jobStatus(queueID)
    ) {
      Thread.sleep(250);
    }

    if (stat == QueueJobStatus.Errored) {
      throw new Exception(JobDataManager.getJobError(dets.id)
        .orElse("An unknown error occurred"));
    }
  }

  public static FormatType parseFormatString(String format) {
    try {
      return FormatType.fromID(Integer.parseInt(format));
    } catch (NumberFormatException ignored) {
    }

    return switch (BlastReportType.fromIoName(format)
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
