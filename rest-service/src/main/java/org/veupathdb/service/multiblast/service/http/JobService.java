package org.veupathdb.service.multiblast.service.http;

import java.io.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import mb.lib.config.Config;
import mb.lib.db.JobDBManager;
import mb.lib.db.model.impl.FullJobRowImpl;
import mb.lib.db.model.impl.UserRowImpl;
import mb.lib.extern.JobQueueManager;
import mb.lib.extern.JobStatus;
import mb.lib.format.FormatType;
import mb.lib.format.FormatterManager;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gusdb.fgputil.accountdb.UserProfile;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.model.io.JsonKeys;
import org.veupathdb.service.multiblast.service.JobCleanup;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;
import org.veupathdb.service.multiblast.service.conv.JobConverter;
import org.veupathdb.service.multiblast.service.valid.BlastValidator;
import org.veupathdb.service.multiblast.util.Format;

import static org.veupathdb.service.multiblast.service.http.Util.wrapException;

// TODO: Job mount path should be the same for both the blast container and the
//       service container.
public class JobService
{
  private static final Config conf = Config.getInstance();
  private static final Logger log = LogManager.getLogger(JobService.class);
  private static final int buffSize = 8192;

  private static JobService instance;

  private JobService() {
    log.trace("JobService::new");
  }

  public static JobService getInstance() {
    log.trace("JobService::getInstance");
    if (instance == null)
      instance = new JobService();

    return instance;
  }

  public LongJobResponse getJob(String rawID, UserProfile user) {
    if (!Format.isHex(rawID))
      throw new NotFoundException();

    var jobID = Format.hexToBytes(rawID);

    try {

      var opt = JobDBManager.getUserJob(jobID, user.getUserId());

      if (opt.isEmpty())
        throw new NotFoundException();

      var job = opt.get();

      var out = new LongJobResponseImpl();
      out.setId(rawID);
      out.setDescription(job.description());
      out.setStatus(convStatus(JobQueueManager.jobStatus(job.queueID())));
      out.setConfig(JobConverter.toExternal(Job.fromSerial(job.config())));

      return out;
    } catch (Exception e) {
      throw wrapException(e);
    }
  }

  public List<ShortJobResponse> getJobs(UserProfile user) {
    try {
      var jobs = JobDBManager.getUserJobs(user.getUserId());
      var out = new ArrayList<ShortJobResponse>(jobs.size());

      for (var job : jobs) {
        if (job.queueID() == 0)
          throw new InternalServerErrorException("Invalid state, job with queue ID of 0");

        var tmp = new ShortJobResponseImpl();
        tmp.setId(Format.toHexString(job.jobHash()));
        tmp.setDescription(job.description());
        tmp.setStatus(convStatus(JobQueueManager.jobStatus(job.queueID())));
        out.add(tmp);
      }

      return out;
    } catch (Exception ex) {
      throw new InternalServerErrorException("Failed to retrieve user job list.", ex);
    }
  }

  public StreamingOutput getQuery(String jobID) {
    if (!Format.isHex(jobID))
      throw new NotFoundException();

    var rawID = Format.hexToBytes(jobID);

    try {
      var optJob = JobDBManager.getJob(rawID);
      if (optJob.isEmpty())
        throw new NotFoundException();

      if (!JobDataManager.jobDataExists(jobID))
        throw new IllegalStateException("Job exists but has no workspace.");

      var jobConfig = Job.fromSerial(optJob.get().config());
      var queryFile = JobDataManager.getJobFile(jobID, jobConfig.getJobConfig().getQuery());

      return out -> {
        var buf = new byte[buffSize];
        var n = 0;
        try(var in = new BufferedInputStream(new FileInputStream(queryFile))) {
          while ((n = in.read(buf)) > 0) {
            out.write(buf, 0, n);
          }
        }
      };
    } catch (Exception ex) {
      throw wrapException(ex);
    }
  }

  public ReportWrap getReport(String jobId, IOBlastFormat format, List<IOBlastReportField> fields) {
    try {
      var pFormat = switch(format) {
        case PAIRWISE -> FormatType.Pairwise;
        case QUERYANCHOREDWITHIDENTITIES -> FormatType.QueryAnchoredWithIdentities;
        case QUERYANCHOREDWITHOUTIDENTITIES -> FormatType.QueryAnchoredWithoutIdentities;
        case FLATQUERYANCHOREDWITHIDENTITIES -> FormatType.FlatQueryAnchoredWithIdentities;
        case FLATQUERYANCHOREDWITHOUTIDENTITIES -> FormatType.FlatQueryAnchoredWithoutIdentities;
        case XML -> FormatType.BlastXML;
        case TABULAR -> FormatType.Tabular;
        case TABULARWITHCOMMENTS -> FormatType.TabularWithCommentLines;
        case TEXTASN_1 -> FormatType.SeqAlignTextASN1;
        case BINARYASN_1 -> FormatType.SeqAlignBinaryASN1;
        case CSV -> FormatType.CommaSeparatedValues;
        case ARCHIVEASN_1 -> FormatType.BlastArchiveASN1;
        case SEQALIGNJSON -> FormatType.SeqAlignJSON;
        case MULTIFILEJSON -> FormatType.MultipleFileBlastJSON;
        case MULTIFILEXML2 -> FormatType.MultipleFileBlastXML2;
        case SINGLEFILEJSON -> FormatType.SingleFileBlastJSON;
        case SINGLEFILEXML2 -> FormatType.SingleFileBlastXML2;
        case SAM -> FormatType.SequenceAlignmentMap;
        case ORGANISMREPORT -> FormatType.OrganismReport;
      };

      return new ReportWrap(
        jobId + "_fmt_" + pFormat.id(),
        FormatterManager.formatAs(pFormat, fields.stream().map(f -> f.name).toArray(String[]::new))
      );
    } catch (Exception e) {
      var eOut = wrapException(e);

      if (eOut instanceof InternalServerErrorException) {
        log.error(String.format("Failed to get report for job %s.", jobId), e);
      } else {
        log.info(String.format("Failed to get report for job %s.", jobId), e);
      }

      throw eOut;
    }
  }

  public NewJobPostResponse createJob(NewJobPostRequestJSON input, UserProfile user) {
    log.trace("JobService.createJob(NewJobPostRequestJSON, UserProfile, Request)");
    if (input == null) {
      log.debug("Rejecting request for null body.");
      throw new BadRequestException();
    }

    if (input.getConfig() == null) {
      log.debug("Rejecting request for null job configuration.");
      throw new UnprocessableEntityException(
        Collections.singletonMap(JsonKeys.Config, Collections.singletonList("is required"))
      );
    }

    {
      var err = BlastValidator.getInstance().validate(input.getConfig());
      if (!err.isEmpty()) {
        log.debug("Rejecting request for validation errors.");
        throw new UnprocessableEntityException(err);
      }
    }

    if (input.getConfig() == null
      || input.getConfig().getQuery() == null
      || input.getConfig().getQuery().isBlank()
    ) {
      log.debug("Rejecting request for missing query value.");
      throw new UnprocessableEntityException(Collections.singletonMap(
        JsonKeys.Query,
        Collections.singletonList(
          BlastValidator.errRequired)
      ));
    }

    try {
      var job = JobConverter.toInternal(input.getConfig());
      var cli = new CliBuilder(job.getTool());
      job.getJobConfig().toCli(cli);

      var id = createJob(new SimpleJobRequest(
        user.getUserId(),
        input.getDescription(),
        job,
        cli
      ));

      var out = new NewJobPostResponseImpl();
      out.setJobId(id);

      return out;
    } catch (Exception e) {
      throw wrapException(e);
    }
  }

  public NewJobPostResponse createJob(NewJobPostRequestMultipart input, UserProfile user) {
    log.trace("JobService.createJob(NewJobPostRequestMultipart, UserProfile, Request)");

    if (input == null) {
      log.debug("Rejecting request for null body.");
      throw new BadRequestException();
    }

    if (input.getQuery() == null) {
      log.debug("Rejecting request for missing query upload.");
      throw new BadRequestException();
    }

    if (input.getProperties() == null) {
      log.debug("Rejecting request for null properties.");
      throw new BadRequestException();
    }

    if (input.getProperties().getConfig() == null) {
      log.debug("Rejecting request for null job config.");
      throw new UnprocessableEntityException(
        Collections.singletonMap(JsonKeys.Config, Collections.singletonList("is required"))
      );
    }

    {
      var err = BlastValidator.getInstance().validate(input.getProperties().getConfig());
      if (!err.isEmpty()) {
        log.debug("Rejecting request for validation errors.");
        throw new UnprocessableEntityException(err);
      }
    }

    try {
      var qString = new StringBuilder((int) input.getQuery().length());
      try (var read = new BufferedReader(new FileReader(input.getQuery()))) {
        var n = 0;
        var b = new char[buffSize];
        while ((n = read.read(b)) > 0)
          qString.append(b, 0, n);
        input.getProperties().getConfig().setQuery(qString.toString());
      } finally {
        //noinspection ResultOfMethodCallIgnored
        input.getQuery().delete();
      }

      var job = JobConverter.toInternal(input.getProperties().getConfig());
      var cli = new CliBuilder(job.getTool());

      job.getJobConfig().toCli(cli);

      var id = createJob(new SimpleJobRequest(
        user.getUserId(),
        input.getProperties().getDescription(),
        job,
        cli
      ));

      var out = new NewJobPostResponseImpl();
      out.setJobId(id);

      return out;
    } catch (Exception e) {
      throw wrapException(e);
    } finally {
      if (!input.getQuery().delete())
        log.warn("Could not delete ");
    }
  }

  String createJob(SimpleJobRequest req) throws Exception {
    log.trace("JobService.createJob(SimpleJobRequest)");

    var hash   = Format.toSHA256(req.builder.toString());
    var optJob = JobDBManager.getJob(hash);

    if (optJob.isEmpty()) {
      createJobNew(hash, req);
      return Format.toHexString(hash);
    }

    var job   = optJob.get();
    var jobID = Format.toHexString(hash);

    // If we've hit a job that is dead but has not yet been cleaned up
    // automatically, then do it manually before proceeding.
    if (OffsetDateTime.now().isAfter(job.deleteOn())) {
      new JobCleanup().runChecked();

      createJobNew(hash, req);
      return Format.toHexString(hash);
    }

    if (!JobDataManager.jobDataExists(jobID))
      throw new IllegalStateException("Job " + jobID + " has no workspace");

    createJobCollision(hash, req);
    return Format.toHexString(hash);
  }

  void createJobCollision(byte[] hash, SimpleJobRequest req) throws Exception {
    JobDBManager.linkUserToJob(new UserRowImpl(hash, req.userID, req.description));
    JobDBManager.updateJobDeleteTimer(hash, OffsetDateTime.now().plusDays(conf.getJobTimeout()));
  }

  void createJobNew(byte[] hash, SimpleJobRequest req) throws Exception {
    log.trace("JobService.createJobNew(byte[], SimpleJobRequest)");

    var jobIDString = Format.toHexString(hash);
    var jobPath     = JobDataManager.createJobWorkspace(jobIDString);
    var queueID     = JobQueueManager.submitJob(Format.toHexString(hash), req.builder.toArgArray());
    var now         = OffsetDateTime.now();

    var qFile = jobPath.resolve("query.txt").toFile();
    //noinspection ResultOfMethodCallIgnored
    qFile.createNewFile();
    try (var write = new BufferedWriter(new FileWriter(qFile))) {
      write.write(req.job.getJobConfig().getQuery());
    }

    JobDBManager.registerJob(
      new FullJobRowImpl(
        hash,
        queueID,
        now,
        now.plusDays(conf.getJobTimeout()),
        req.job.toString()
      ),
      new UserRowImpl(hash, req.userID, req.description)
    );
  }

  private static IOJobStatus convStatus(JobStatus stat) {
    return switch(stat) {
      case Completed -> IOJobStatus.COMPLETED;
      case Errored -> IOJobStatus.ERRORED;
      case Queued -> IOJobStatus.QUEUED;
      case InProgress -> IOJobStatus.INPROGRESS;
    };
  }

  public static class ReportWrap implements StreamingOutput
  {
    public final String name;
    public final InputStream stream;

    public ReportWrap(String name, InputStream stream) {
      this.name   = name;
      this.stream = stream;
    }

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

class SimpleJobRequest
{
  final long       userID;
  final String     description;
  final Job        job;
  final CliBuilder builder;

  SimpleJobRequest(
    long userID,
    String description,
    Job job,
    CliBuilder builder
  ) {
    this.userID      = userID;
    this.description = description;
    this.job         = job;
    this.builder     = builder;
  }
}
