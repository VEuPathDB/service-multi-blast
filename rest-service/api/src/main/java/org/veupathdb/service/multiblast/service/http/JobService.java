package org.veupathdb.service.multiblast.service.http;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import mb.lib.db.JobDBManager;
import mb.lib.extern.JobQueueManager;
import mb.lib.extern.JobStatus;
import mb.lib.format.FormatType;
import mb.lib.format.FormatterManager;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.model.User;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.BlastReportType;
import org.veupathdb.service.multiblast.model.blast.BlastTool;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.service.conv.JobConverter;
import org.veupathdb.service.multiblast.service.http.job.JobCreationService;
import org.veupathdb.service.multiblast.service.http.job.JobReportService;
import org.veupathdb.service.multiblast.util.Format;

import static org.veupathdb.service.multiblast.service.http.Util.wrapException;

// TODO: Job mount path should be the same for both the blast container and the
//       service container.
public class JobService
{
  private static final Logger log      = LogManager.getLogger(JobService.class);
  private static final int    buffSize = 8192;

  private static JobService instance;

  private JobService() {
    log.trace("JobService::new()");
  }

  public static JobService getInstance() {
    log.trace("JobService::getInstance()");

    if (instance == null)
      instance = new JobService();

    return instance;
  }

  public IOLongJobResponse getJob(String rawID, User user) {
    log.trace("JobService#getJob(rawID={}, user={})", rawID, user.getUserID());

    if (!Format.isHex(rawID))
      throw new NotFoundException();

    var jobID = Format.hexToBytes(rawID);

    try {

      var opt = JobDBManager.getUserJob(jobID, user.getUserID());

      if (opt.isEmpty())
        throw new NotFoundException();

      var job = opt.get();

      var out = new IOLongJobResponseImpl()
        .setConfig(JobConverter.toExternal(Job.fromSerial(job.config())));
      out.setId(rawID)
        .setDescription(job.description())
        .setStatus(convStatus(JobQueueManager.jobStatus(job.queueID())))
        .setCreated(Format.DateFormat.format(job.createdOn()))
        .setExpires(Format.DateFormat.format(job.deleteOn()))
        .setMaxResultSize(job.maxDownloadSize())
        .setParentJobs(
          JobDBManager.getParentJobs(jobID, user.getUserID())
            .stream()
            .map(j -> new IOParentJobLinkImpl()
              .setId(Format.toHexString(j.parentHash()))
              .setIndex(j.position()))
            .toArray(IOParentJobLink[]::new)
        );

      if (out.getStatus() == null) {
        if (JobDataManager.reportExists(rawID))
          out.setStatus(IOJobStatus.COMPLETED);
        else
          out.setStatus(IOJobStatus.ERRORED);
      }

      return out;
    } catch (Exception e) {
      throw wrapException(e);
    }
  }

  public List<IOShortJobResponse> getJobs(User user) {
    log.trace("JobService#getJobs(user={})", user.getUserID());

    try {
      var jobs = JobDBManager.getUserJobs(user.getUserID());
      var out  = new ArrayList<IOShortJobResponse>(jobs.size());

      for (var job : jobs) {
        if (job.queueID() == 0)
          throw new InternalServerErrorException("Invalid state, job with queue ID of 0");

        var jobStatus = JobQueueManager.jobStatus(job.queueID());

        // Success and some failures == unknown
        // Need to check the filesystem for the real status.
        if (jobStatus == JobStatus.Unknown) {
          if (JobDataManager.reportExists(Format.toHexString(job.jobHash())))
            jobStatus = JobStatus.Completed;
          else
            jobStatus = JobStatus.Errored;
        }

        var tmp = new IOShortJobResponseImpl()
          .setId(Format.toHexString(job.jobHash()))
          .setDescription(job.description())
          .setStatus(convStatus(jobStatus))
          .setCreated(Format.toString(job.createdOn()))
          .setExpires(Format.toString(job.deleteOn()))
          .setMaxResultSize(job.maxDownloadSize())
          .setIsPrimary(job.runDirectly())
          .setParentJobs(
            JobDBManager.getParentJobs(job.jobHash(), user.getUserID())
              .stream()
              .map(j -> new IOParentJobLinkImpl()
                .setId(Format.toHexString(j.parentHash()))
                .setIndex(j.position()))
              .toArray(IOParentJobLink[]::new)
          );
        out.add(tmp);
      }

      return out;
    } catch (Exception ex) {
      throw new InternalServerErrorException("Failed to retrieve user job list.", ex);
    }
  }

  public StreamingOutput getQuery(String jobID) {
    log.trace("JobService#getQuery(jobID={})", jobID);

    if (!Format.isHex(jobID))
      throw new NotFoundException();

    var rawID = Format.hexToBytes(jobID);

    try {
      var optJob = JobDBManager.getJob(rawID);
      if (optJob.isEmpty())
        throw new NotFoundException();

      if (!JobDataManager.jobDataExists(jobID))
        throw new IllegalStateException("Job exists but has no workspace.");

      var queryFile = JobDataManager.getJobQuery(jobID);

      return out -> {
        var buf = new byte[buffSize];
        var n   = 0;
        try (var in = new BufferedInputStream(new FileInputStream(queryFile))) {
          while ((n = in.read(buf)) > 0) {
            out.write(buf, 0, n);
          }
        }
      };
    } catch (Exception ex) {
      throw wrapException(ex);
    }
  }

  public ReportWrap getReport(
    String jobID,
    long   userId,
    String format,
    boolean zip,
    List<IOBlastReportField> fields,
    Long maxDlSize
  ) {
    log.trace("JobService#getReport(jobID={}, format={}, zip={}, fields={}, maxDlSize={})", jobID, format, zip, fields, maxDlSize);

    try {
      if (!Format.isHex(jobID))
        throw new NotFoundException();

      var job = JobDBManager.getUserJob(Format.hexToBytes(jobID), userId)
        .orElseThrow(NotFoundException::new);

      FormatType pFormat;

      var config = Job.fromSerial(job.config());

      if (format == null) {
        pFormat = FormatType.fromID(config.getJobConfig().getReportFormat().getType().getValue());
      } else {
        pFormat = JobReportService.parseFormatString(format);
      }

      if (pFormat == FormatType.SequenceAlignmentMap && config.getTool() != BlastTool.BlastN)
        throw new BadRequestException("The SAM report format is only available for jobs run with blastn");

      if (pFormat == FormatType.MultipleFileBlastJSON || pFormat == FormatType.MultipleFileBlastXML2)
        zip = true;

      var out = new ReportWrap();
      out.zipped = zip;
      var tmp = FormatterManager.formatAs(
        jobID,
        pFormat,
        zip,
        maxDlSize == null ? job.maxDownloadSize() : maxDlSize, // If the client provided a max size header, prefer that value.
        fields.stream().map(f -> f.name).toArray(String[]::new)
      );

      if (tmp.isRight())
        throw new BadRequestException(tmp.rightOrThrow().getMessage());

      out.stream = tmp.leftOrThrow();

      setContentType(out, pFormat);

      return out;
    } catch (Exception e) {
      var eOut = wrapException(e);

      if (eOut instanceof InternalServerErrorException) {
        log.error(String.format("Failed to get report for job %s.", jobID), e);
      } else {
        log.info(String.format("Failed to get report for job %s.", jobID), e);
      }

      throw eOut;
    }
  }

  public IOJobPostResponse createJob(IOJsonJobRequest input, User user) {
    log.trace("JobService#createJob(input={}, user={})", input, user.getUserID());

    return JobCreationService.createJobs(input, user.getUserID());
  }

  public IOJobPostResponse createJob(InputStream query, IOJsonJobRequest props, User user) {
    log.trace("JobService#createJob(query={}, props={}, user={})", query, props, user.getUserID());

    try {
      if (query == null)
        return JobCreationService.createJobs(props, user.getUserID());

      return JobCreationService.createJobs(query, props, user.getUserID());
    } catch (Exception e) {
      throw wrapException(e);
    }
  }

  static IOJobStatus convStatus(JobStatus stat) {
    log.trace("JobService#convStatus({})", stat);
    return switch (stat) {
      case Completed -> IOJobStatus.COMPLETED;
      case Errored -> IOJobStatus.ERRORED;
      case Queued -> IOJobStatus.QUEUED;
      case InProgress -> IOJobStatus.INPROGRESS;
      case Unknown -> null;
    };
  }

  static void setContentType(ReportWrap wrap, FormatType type) {
    log.trace("JobService#setContentType(ReportWrap, {})", type);
    switch (type) {
      case BlastXML, SingleFileBlastXML2, MultipleFileBlastXML2 -> {
        wrap.contentType = "application/xml";
        wrap.ext = "xml";
      }
      case Tabular -> {
        wrap.contentType = "text/plain";
        wrap.ext = "tsv";
      }
      case SeqAlignTextASN1, SeqAlignBinaryASN1, BlastArchiveASN1 -> {
        wrap.contentType = "text/plain";
        wrap.ext = "asn";
      }
      case CommaSeparatedValues -> {
        wrap.contentType = "text/plain";
        wrap.ext = "csv";
      }
      case SeqAlignJSON, SingleFileBlastJSON, MultipleFileBlastJSON -> {
        wrap.contentType = "application/json";
        wrap.ext = "json";
      }
      default -> {
        wrap.contentType = "text/plain";
        wrap.ext = "txt";
      }
    }
  }

  public static class ReportWrap implements StreamingOutput
  {
    public String      ext;
    public String      contentType;
    public boolean     zipped;
    public InputStream stream;

    @Override
    public void write(OutputStream output) throws IOException, WebApplicationException {
      try (var b = new BufferedInputStream(stream)) {
        var n = 0;
        var a = new byte[8192];

        while ((n = b.read(a)) > 0)
          output.write(a, 0, n);
      }
    }
  }
}
