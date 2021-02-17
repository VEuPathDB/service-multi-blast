package org.veupathdb.service.multiblast.service.http.job;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import javax.ws.rs.BadRequestException;

import mb.lib.config.Config;
import mb.lib.db.JobDBManager;
import mb.lib.db.model.impl.FullJobRowImpl;
import mb.lib.db.model.impl.UserRowImpl;
import mb.lib.extern.JobQueueManager;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.service.JobCleanup;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;
import org.veupathdb.service.multiblast.service.conv.JobConverter;
import org.veupathdb.service.multiblast.service.http.Util;
import org.veupathdb.service.multiblast.service.valid.BlastValidator;
import org.veupathdb.service.multiblast.util.Format;

public class JobCreationService
{
  private static final Logger log  = LogManager.getLogger(JobCreationService.class);
  private static final Config conf = Config.getInstance();

  /**
   * Params:
   * 1 - Site
   * 2 - Target string (space separated list of DBs)
   * 3 - Tool
   * 4 - CLI Args
   */
  private static final String hashPattern = "%s-%s-%s-%s";

  public static IOJobPostResponse createJobs(IOJsonJobRequest req, long userID) {
    log.trace("::createJob(req={}, userID={})", req, userID);
    verifyBody(req);
    verifyConfig(req.getConfig());
    verifyQuery(req.getConfig().getQuery());

    try {
      var queryHandle = new QuerySplitter().splitQueries(
        new ByteArrayInputStream(req.getConfig().getQuery().getBytes(StandardCharsets.UTF_8))
      );
      return createJobs(queryHandle, req, userID);
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }

  // TODO: if the base request query is null, pass the props to the createJob
  //       method above.
  public static IOJobPostResponse createJobs(IOMultipartJobRequest req, long userID) {
    log.trace("::createJob(req={}, userID={})", req, userID);

    if (req.getQuery() == null) {
      log.debug("query file is null");
      return createJobs(req.getProperties(), userID);
    }

    verifyBody(req);
    verifyProps(req.getProperties());
    verifyConfig(req.getProperties().getConfig());
    verifyQuery(req.getQuery());

    try {
      var queryHandle = new QuerySplitter().splitQueries(new FileInputStream(req.getQuery()));
      //noinspection ResultOfMethodCallIgnored
      req.getQuery().delete();

      return createJobs(queryHandle, req.getProperties(), userID);
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }

  static IOJobPostResponse createJobs(QuerySplitResult queries, IOJsonJobRequest js, long userID)
  throws Exception {
    log.trace("::createJob(query={}, js={}, userID={})", queries, js, userID);
    {
      var errs = BlastValidator.getInstance().validate(js.getConfig());
      if (!errs.isEmpty())
        throw new UnprocessableEntityException(errs);
    }

    verifyResultLimit(js, queries);

    if (js.getTargets() == null || js.getTargets().length == 0)
      throw new UnprocessableEntityException(new ErrorMap(
        "targets",
        "1 or more targets must be selected."
      ));

    var jobInfo = new JobInfo();
    var usrInfo = new UserInfo();

    usrInfo.userID      = userID;
    usrInfo.maxDlSize   = js.getMaxResultSize() == null ? 0 : js.getMaxResultSize();
    usrInfo.description = js.getDescription();

    jobInfo.dbPath = makeDBPaths(js.getSite(), js.getTargets());
    jobInfo.query  = queries;
    jobInfo.job    = JobConverter.toInternal(js.getConfig());
    jobInfo.cli    = new CliBuilder();
    jobInfo.job.getJobConfig().setDatabase(jobInfo.dbPath);

    var rootQueryHashString = Format.toHexString(queries.rootQuery.hash);
    log.debug("Root Query Hash: {}", rootQueryHashString);

    // Overwrite the query value with a hash string containing the sha256
    // checksum of the actual query value.
    // The actual query is stored as a file.
    jobInfo.job.getJobConfig().setQuery(rootQueryHashString);

    // Convert the job config to a CLI format (which will be hashed).
    jobInfo.job.getJobConfig().toCli(jobInfo.cli);

    // Hash the root job
    jobInfo.jobHash = hashJob(
      js.getSite(),
      jobInfo.dbPath,
      js.getConfig().getTool().name,
      jobInfo.cli.toString()
    );
    log.debug("Job Hash: {}", () -> Format.toHexString(jobInfo.jobHash));

    {
      jobInfo.job.getJobConfig().setQuery(Format.toHexString(queries.rootQuery.hash));
      var cli = new CliBuilder();

      jobInfo.job.getJobConfig().toCli(cli);

      var dets = new JobDetails();
      dets.source      = queries.rootQuery.source;
      dets.hash        = hashJob(
        js.getSite(),
        jobInfo.dbPath,
        js.getConfig().getTool().name,
        cli.toString()
      );
      dets.id          = Format.toHexString(dets.hash);
      dets.job         = jobInfo.job;
      dets.cli         = cli;
      dets.userID      = userID;
      dets.description = js.getDescription();
      dets.maxDlSize   = js.getMaxResultSize();
      dets.parentHash  = null;
    }

    // Check if the hash for the root job collides with a pre-existing job.
    var collide = JobDBManager.getJob(jobInfo.jobHash);

    if (collide.isEmpty())
      return createNew(jobInfo, usrInfo);

    // If we've hit a job that is dead but has not yet been cleaned up
    // automatically, then do it manually before rerunning.
    if (OffsetDateTime.now().isAfter(collide.get().deleteOn())) {
      new JobCleanup().runChecked();

      return rerunJob(jobInfo, usrInfo);
    }

    var jobID = Format.toHexString(jobInfo.jobHash);

    if (!JobDataManager.jobDataExists(jobID))
      throw new IllegalStateException("Job " + jobID + " has no workspace");

    return handleCollision(jobInfo.jobHash, usrInfo);

    // For the root query:
    //   - check for full job collision (if collides link user and be done)
    //   - insert root query
    //   - run root query
    // For sub queries
    //   - check for sub-query collision (if collides link single result to existing job)
    //   - insert sub-query
    //   - run sub-query
  }

  static JobDetails prepJob(JobInfo ji, IOJsonJobRequest js, QuerySplitRow row, UserInfo ui)
  throws Exception {
    ji.job.getJobConfig().setQuery(Format.toHexString(row.hash));
    var cli = new CliBuilder();

    ji.job.getJobConfig().toCli(cli);

    var dets = new JobDetails();
    dets.source      = row.source;
    dets.hash        = hashJob(
      js.getSite(),
      ji.dbPath,
      js.getConfig().getTool().name,
      cli.toString()
    );
    dets.id          = Format.toHexString(dets.hash);
    dets.job         = ji.job;
    dets.cli         = cli;
    dets.userID      = userID;
    dets.description = js.getDescription();
    dets.maxDlSize   = js.getMaxResultSize();
    dets.parentHash  = null;
  }

  static IOJobPostResponse rerunJob(JobInfo job, UserInfo user) throws Exception {
    log.trace("::rerunJob(job={}, user={}", job, user);

    var jobIDString = Format.toHexString(job.jobHash);
    var jobPath     = JobDataManager.createJobWorkspace(jobIDString);
    var queueID = JobQueueManager.submitJob(
      jobIDString,
      job.job.getTool().value(),
      job.cli.toArgArray(false)
    );

    Files.move(job.query.tmpFile.toPath(), jobPath.resolve("query.txt"));

    JobDBManager.updateJobQueueID(job.jobHash, queueID);
    JobDBManager.updateJobDeleteTimer(
      job.jobHash,
      OffsetDateTime.now().plusDays(conf.getJobTimeout())
    );

    return new IOJobPostResponseImpl().setJobId(jobIDString);
  }

  static IOJobPostResponse createNew(JobInfo job, QuerySplitRow row, UserInfo user)
  throws Exception {
    log.trace("::createNew(job={}, user={})", job, user.userID);
    var jobIDString = Format.toHexString(job.jobHash);
    var jobPath     = JobDataManager.createJobWorkspace(jobIDString);
    var queueID = JobQueueManager.submitJob(
      jobIDString,
      job.job.getTool().value(),
      job.cli.toArgArray(false)
    );
    var now = OffsetDateTime.now();

    Files.move(job.query.tmpFile.toPath(), jobPath.resolve("query.txt"));

    JobDBManager.registerJob(
      new FullJobRowImpl(
        job.jobHash,
        queueID,
        now,
        now.plusDays(conf.getJobTimeout()),
        job.job.toSerial()
      ),
      new UserRowImpl(job.jobHash, user.userID, user.description, user.maxDlSize)
    );

    return new IOJobPostResponseImpl().setJobId(jobIDString);
  }

  static void createJobEntry(int queryNum, QuerySplitRow row) throws Exception {
    Files.copy(row.source, )
  }

  /**
   * Links the requesting user to the existing job (if that user is not already
   * linked).
   *
   * @param jobHash Job to link the user to
   * @param userID  ID of the User to link
   * @param usrInfo User specific info about the job
   *
   * @return Job creation response
   */
  static IOJobPostResponse handleCollision(byte[] jobHash, UserInfo usrInfo)
  throws Exception {
    log.trace("::handleCollision(jobHash={}, usrInfo={})", jobHash, usrInfo);

    // TODO: what about if a user submits the same job 2x with different descriptions?
    if (JobDBManager.getUserJob(jobHash, usrInfo.userID).isEmpty()) {
      JobDBManager.linkUserToJob(new UserRowImpl(
        jobHash,
        usrInfo.userID,
        usrInfo.description,
        usrInfo.maxDlSize
      ));
      JobDBManager.updateJobDeleteTimer(
        jobHash,
        OffsetDateTime.now().plusDays(conf.getJobTimeout())
      );
    }

    var out = new IOJobPostResponseImpl();
    out.setJobId(Format.toHexString(jobHash));
    return out;
  }

  static String makeDBPaths(String site, IOJobTarget[] targets) {
    var dbPath = new StringBuilder();

    for (var db : targets) {
      var path = JobDataManager.makeDBPath(site, db.organism(), db.target());
      if (!JobDataManager.targetDBExists(path))
        throw new BadRequestException("unrecognized query target");
      if (!dbPath.isEmpty())
        dbPath.append(' ');
      dbPath.append(path);
    }

    return dbPath.toString();
  }

  static void verifyQuery(Object req) {
    nullCheck(req, "query cannot be null");
  }

  static void verifyBody(Object req) {
    nullCheck(req, "request body cannot be null");
  }

  static void verifyProps(Object req) {
    nullCheck(req, "job properties cannot be null");
  }

  static void verifyConfig(Object req) {
    nullCheck(req, "blast config cannot be null");
  }

  static void nullCheck(Object req, String msg) {
    if (req == null)
      throw new BadRequestException(msg);
  }

  static void verifyResultLimit(IOJsonJobRequest req, QuerySplitResult query) {
    if (req.getMaxResults() != null && req.getMaxResults() > 0)
      ResultLimitValidator.validateResultLimit(
        req.getMaxResults(),
        query.subQueries.size() + 1,
        req.getConfig()
      ).ifPresent(m -> { throw new UnprocessableEntityException(m); });
  }

  static byte[] hashJob(String site, String dbs, String tool, String cli) {
    return Format.toHash(String.format(hashPattern, site, dbs, tool, cli));
  }
}

class JobInfo
{
  String           dbPath;
  byte[]           jobHash;
  Job              job;
  QuerySplitResult query;
  CliBuilder       cli;
}

class UserInfo
{
  long   userID;
  String description;
  long   maxDlSize;
}
