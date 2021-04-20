package mb.api.service.http;

import java.io.*;
import java.time.OffsetDateTime;
import java.util.*;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import mb.api.model.*;
import mb.lib.db.JobDBManager;
import mb.lib.db.model.*;
import mb.lib.db.model.impl.UserRowImpl;
import mb.lib.querier.BlastQueueManager;
import mb.lib.queue.model.QueueJobStatus;
import mb.lib.blast.model.BlastReportType;
import mb.lib.data.JobDataManager;
import mb.lib.model.HashID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.model.User;
import mb.lib.blast.model.BlastReportField;
import mb.lib.blast.model.BlastTool;
import mb.api.model.internal.Job;
import mb.lib.model.JobStatus;
import mb.api.service.conv.BCC;
import mb.api.service.conv.JobConverter;
import mb.api.service.http.job.JobCreationService;
import mb.api.service.http.job.JobReportService;
import mb.api.service.util.Format;

import static mb.api.service.http.Util.wrapException;

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

    var jobID = new HashID(rawID);

    try (var db  = new JobDBManager()) {
      var job = db.getQueryJob(jobID).orElseThrow(NotFoundException::new);
      UserRow userRow;

      if (db.userIsLinkedToJob(user.getUserID(), jobID)) {
        userRow = db.getUser(user.getUserID(), jobID).orElseThrow(InternalServerErrorException::new);
      } else {
        userRow = new UserRowImpl(jobID, user.getUserID(), null, null, true);
        db.linkUserToJob(userRow);

        for (var tJob : db.getChildJobsFor(jobID)) {
          var tUser = new UserRowImpl(tJob.jobID(), user.getUserID(), null, null, false);
          db.linkUserToJob(tUser);
        }
      }

      var out = new IOLongJobResponseImpl()
        .setConfig(JobConverter.toExternal(Job.fromSerial(job.config())));

      // Update the modified date to avoid deleting actively used jobs in the
      // job cleanup.
      var workspace = JobDataManager.jobWorkspace(jobID);
      workspace.updateLastModified(OffsetDateTime.now());

      out.setId(rawID)
        .setDescription(userRow.description())
        .setStatus(Util.convStatus(syncJobStatus(job)))
        .setCreated(Format.DateFormat.format(job.createdOn()))
        .setExpires(Format.DateFormat.format(job.deleteOn()))
        .setMaxResultSize(userRow.maxDownloadSize())
        .setParentJobs(
          db.getParentJobs(jobID, user.getUserID())
            .stream()
            .map(j -> new IOParentJobLinkImpl()
              .setId(j.parentID().string())
              .setIndex(j.position()))
            .toArray(IOParentJobLink[]::new)
        )
        .setSite(job.projectID())
        .setTargets(
          db.getJobTargetsFor(job.jobID())
            .stream()
            .map(BCC::toExternal)
            .toArray(IOJobTarget[]::new)
        )
        .setIsCached(workspace.resultExists());

      if (out.getStatus() == null) {
        if (workspace.resultExists())
          out.setStatus(JobStatus.Completed);
        else
          out.setStatus(JobStatus.Errored);
      }

      if (out.getStatus() == JobStatus.Completed && !out.isCached())
        out.setStatus(JobStatus.Expired);

      return out;
    } catch (Exception e) {
      throw wrapException(e);
    }
  }

  public List<IOShortJobResponse> getJobs(User user) {
    log.trace("#getJobs(user={})", user.getUserID());

    try {
      Collection<ShortUserJobRow>  jobs;
      Map<HashID, List<JobTarget>> tgts;
      Map<HashID, List<JobLink>>   pars;

      try (var db = new JobDBManager()) {
        jobs = db.getUserJobs(user.getUserID());
        tgts = db.getJobTargetsFor(user.getUserID());
        pars = db.getAllParentJobs(user.getUserID());
      }

      var out  = new ArrayList<IOShortJobResponse>(jobs.size());

      for (var job : jobs) {
        if (job.queueID() == 0)
          throw new InternalServerErrorException("Invalid state, job with queue ID of 0");

        var workspace = JobDataManager.jobWorkspace(job.jobID());

        var tmp = new IOShortJobResponseImpl()
          .setId(job.jobID().string())
          .setDescription(job.description())
          .setStatus(Util.convStatus(syncJobStatus(job)))
          .setCreated(Format.toString(job.createdOn()))
          .setExpires(Format.toString(job.deleteOn()))
          .setMaxResultSize(job.maxDownloadSize())
          .setIsPrimary(job.runDirectly())
          .setParentJobs(
            pars.getOrDefault(job.jobID(), Collections.emptyList())
              .stream()
              .map(BCC::toExternal)
              .toArray(IOParentJobLink[]::new)
          )
          .setSite(job.projectID())
          .setTargets(
            tgts.getOrDefault(job.jobID(), Collections.emptyList())
              .stream()
              .map(BCC::toExternal)
              .toArray(IOJobTarget[]::new)
          )
          .setIsCached(workspace.resultExists());
        out.add(tmp);
      }

      return out;
    } catch (Exception ex) {
      throw new InternalServerErrorException("Failed to retrieve user job list.", ex);
    }
  }

  public StreamingOutput getQuery(String rawID) {
    log.trace("#getQuery(rawID={})", rawID);

    if (!Format.isHex(rawID))
      throw new NotFoundException();

    var jobID = new HashID(rawID);

    try (var db  = new JobDBManager()) {
      var job = db.getQueryJob(jobID).orElseThrow(NotFoundException::new);
      var queryFile = job.query();

      return out -> {
        var buf = new byte[buffSize];
        var n   = 0;
        try (var in = new BufferedInputStream(new FileInputStream(queryFile))) {
          while ((n = in.read(buf)) > 0) {
            out.write(buf, 0, n);
          }
        } finally {
          try {
            job.close();
          } catch(Exception e) {
            log.error("Failed to remove temporary query file.", e);
          }
        }
      };
    } catch (Exception ex) {
      throw wrapException(ex);
    }
  }

  public ReportWrap getReport(
    String rawID,
    long   userId,
    String format,
    boolean zip,
    List<BlastReportField> fields,
    Long maxDlSize
  ) {
    log.trace("#getReport(rawID={}, format={}, zip={}, fields={}, maxDlSize={})", rawID, format, zip, fields, maxDlSize);

    if (!Format.isHex(rawID))
      throw new NotFoundException();

    var jobID = new HashID(rawID);

    try {
      FullJobRow job;
      UserRow    user;
      try (var db = new JobDBManager()) {
        job = db.getQueryJob(jobID).orElseThrow(NotFoundException::new);

        if (db.userIsLinkedToJob(userId, jobID)) {
          user = db.getUser(userId, jobID).orElseThrow(InternalServerErrorException::new);
        } else {
          user = new UserRowImpl(jobID, userId, null, null, true);
          db.linkUserToJob(user);

          for (var tJob : db.getChildJobsFor(jobID)) {
            var tUser = new UserRowImpl(tJob.jobID(), userId, null, null, false);
            db.linkUserToJob(tUser);
          }
        }
      }

      BlastReportType pFormat;

      var config = Job.fromSerial(job.config());

      if (format == null) {
        pFormat = config.getJobConfig().getReportFormat().getType();
      } else {
        pFormat = JobReportService.parseFormatString(format);
      }

      if (pFormat == BlastReportType.SequenceAlignmentMap && config.getTool() != BlastTool.BlastN)
        throw new BadRequestException("The SAM report format is only available for jobs run with blastn");

      if (pFormat == BlastReportType.MultipleFileBlastJSON || pFormat == BlastReportType.MultipleFileBlastXML2)
        zip = true;

      JobReportService.ensureJobCache(job, userId);
      job.close(); // Release file resource(s)

      var out = new ReportWrap();
      out.zipped = zip;
      var tmp = FormatterManager.formatAs(
        rawID,
        pFormat,
        zip,
        maxDlSize == null ? user.maxDownloadSize() : maxDlSize, // If the client provided a max size header, prefer that value.
        fields.stream().map(BlastReportField::getValue).toArray(String[]::new)
      );

      if (tmp.isRight())
        throw new BadRequestException(tmp.rightOrThrow().getMessage());

      out.stream = tmp.leftOrThrow();

      setContentType(out, pFormat);

      return out;
    } catch (Exception e) {
      var eOut = wrapException(e);

      if (eOut instanceof InternalServerErrorException) {
        log.error(String.format("Failed to get report for job %s.", rawID), e);
      } else {
        log.info(String.format("Failed to get report for job %s.", rawID), e);
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

  static void setContentType(ReportWrap wrap, BlastReportType type) {
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
    var status = BlastQueueManager.jobStatus(job.queueID());

    // If the status _has_ changed, then insert the new status into the database
    if (status != inStatus) {
      log.debug("Updating db status from {} to {}", status, inStatus);
      try (var db = new JobDBManager()) {
        db.updateJobStatus(job.jobID(), Util.convert(status));
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
