package org.veupathdb.service.multiblast.service.http;

import java.io.*;
import java.util.*;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import mb.lib.db.JobDBManager;
import mb.lib.db.model.*;
import mb.lib.extern.JobQueueManager;
import mb.lib.extern.model.QueueJobStatus;
import mb.lib.format.FormatType;
import mb.lib.format.FormatterManager;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.model.User;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.BlastTool;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.service.conv.BCC;
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
    log.trace("#getJob(rawID={}, user={})", rawID, user.getUserID());

    if (!Format.isHex(rawID))
      throw new NotFoundException();

    var jobID = Format.hexToBytes(rawID);

    try (var db = new JobDBManager()) {

      var opt = db.getUserJob(jobID, user.getUserID());

      if (opt.isEmpty())
        throw new NotFoundException();

      var job = opt.get();

      var out = new IOLongJobResponseImpl()
        .setConfig(JobConverter.toExternal(Job.fromSerial(job.config())));
      out.setId(rawID)
        .setDescription(job.description())
        .setStatus(Util.convStatus(syncJobStatus(job)))
        .setCreated(Format.DateFormat.format(job.createdOn()))
        .setExpires(Format.DateFormat.format(job.deleteOn()))
        .setMaxResultSize(job.maxDownloadSize())
        .setParentJobs(
          db.getParentJobs(jobID, user.getUserID())
            .stream()
            .map(j -> new IOParentJobLinkImpl()
              .setId(Format.toHexString(j.parentHash()))
              .setIndex(j.position()))
            .toArray(IOParentJobLink[]::new)
        )
        .setSite(job.projectID())
        .setTargets(
          db.getJobTargetsFor(job.jobHash())
            .stream()
            .map(BCC::toExternal)
            .toArray(IOJobTarget[]::new)
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
    log.trace("#getJobs(user={})", user.getUserID());

    try {
      Collection<ShortUserJobRow>  jobs;
      Map<String, List<JobTarget>> tgts;
      Map<String, List<JobLink>>   pars;

      try (var db = new JobDBManager()) {
        jobs = db.getUserJobs(user.getUserID());
        tgts = db.getJobTargetsFor(user.getUserID());
        pars = db.getAllParentJobs(user.getUserID());
      }

      var out  = new ArrayList<IOShortJobResponse>(jobs.size());

      for (var job : jobs) {
        if (job.queueID() == 0)
          throw new InternalServerErrorException("Invalid state, job with queue ID of 0");

        var jobID = job.printID();
        var tmp = new IOShortJobResponseImpl()
          .setId(Format.toHexString(job.jobHash()))
          .setDescription(job.description())
          .setStatus(Util.convStatus(syncJobStatus(job)))
          .setCreated(Format.toString(job.createdOn()))
          .setExpires(Format.toString(job.deleteOn()))
          .setMaxResultSize(job.maxDownloadSize())
          .setIsPrimary(job.runDirectly())
          .setParentJobs(
            pars.getOrDefault(jobID, Collections.emptyList())
              .stream()
              .map(BCC::toExternal)
              .toArray(IOParentJobLink[]::new)
          )
          .setSite(job.projectID())
          .setTargets(
            tgts.getOrDefault(jobID, Collections.emptyList())
              .stream()
              .map(BCC::toExternal)
              .toArray(IOJobTarget[]::new)
          );
        out.add(tmp);
      }

      return out;
    } catch (Exception ex) {
      throw new InternalServerErrorException("Failed to retrieve user job list.", ex);
    }
  }

  public StreamingOutput getQuery(String jobID) {
    log.trace("#getQuery(jobID={})", jobID);

    if (!Format.isHex(jobID))
      throw new NotFoundException();

    var rawID = Format.hexToBytes(jobID);

    try {
      try (var db = new JobDBManager()) {
        var optJob = db.getJob(rawID);
        if (optJob.isEmpty())
          throw new NotFoundException();
      }

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
    log.trace("#getReport(jobID={}, format={}, zip={}, fields={}, maxDlSize={})", jobID, format, zip, fields, maxDlSize);

    try {
      if (!Format.isHex(jobID))
        throw new NotFoundException();

      FullUserJobRow job;
      try (var db = new JobDBManager()) {
        job = db.getUserJob(Format.hexToBytes(jobID), userId).orElseThrow(NotFoundException::new);
      }

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

      JobReportService.ensureJobCache(job, userId);

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
    log.trace("#createJob(input={}, user={})", input, user.getUserID());

    return JobCreationService.createJobs(input, user.getUserID());
  }

  public IOJobPostResponse createJob(InputStream query, IOJsonJobRequest props, User user) {
    log.trace("#createJob(query={}, props={}, user={})", query, props, user.getUserID());

    try {
      if (query == null)
        return JobCreationService.createJobs(props, user.getUserID());

      return JobCreationService.createJobs(query, props, user.getUserID());
    } catch (Exception e) {
      throw wrapException(e);
    }
  }


  static void setContentType(ReportWrap wrap, FormatType type) {
    log.trace("#setContentType(ReportWrap, {})", type);
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

  static QueueJobStatus syncJobStatus(ShortJobRow job) throws Exception {
    var inStatus = Util.convert(job.status());

    if (inStatus == QueueJobStatus.Completed || inStatus == QueueJobStatus.Errored) {
      return inStatus;
    }

    // Since we have a queued or in-progress status, hit the queue to see if the
    // status has changed.
    var status = JobQueueManager.jobStatus(job.queueID());

    // If the status _has_ changed, then insert the new status into the database
    if (status != inStatus) {
      log.debug("Updating db status from {} to {}", status, inStatus);
      try (var db = new JobDBManager()) {
        db.updateJobStatus(job.jobHash(), Util.convert(status));
      }
    }

    return status;
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
