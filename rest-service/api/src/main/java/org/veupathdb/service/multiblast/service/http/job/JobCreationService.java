package org.veupathdb.service.multiblast.service.http.job;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.time.OffsetDateTime;
import java.util.*;
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
   *   1 - Site
   *   2 - Target string (space separated list of DBs)
   *   3 - Tool
   *   4 - CLI Args
   */
  private static final String hashPattern = "%s-%s-%s-%s";

  public static NewJobPostResponse createJob(IOJsonJobRequest req, long userID) {
    log.trace("JobCreationService::createJob(req={}, userID={})", req, userID);
    verifyBody(req);
    verifyConfig(req.getConfig());
    verifyQuery(req.getConfig().getQuery());

    try {
      var queryHandle = writeQueryToTmp(
        new ByteArrayInputStream(req.getConfig().getQuery().getBytes(StandardCharsets.UTF_8))
      );
      return createJob(queryHandle, req, userID);
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }

  public static NewJobPostResponse createJob(IOMultipartJobRequest req, long userID) {
    log.trace("JobCreationService::createJob(req={}, userID={})", req, userID);
    verifyBody(req);
    verifyProps(req.getProperties());
    verifyConfig(req.getProperties().getConfig());
    verifyQuery(req.getQuery());

    try {
      var queryHandle = writeQueryToTmp(new FileInputStream(req.getQuery()));
      //noinspection ResultOfMethodCallIgnored
      req.getQuery().delete();

      return createJob(queryHandle, req.getProperties(), userID);
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }

  static NewJobPostResponse createJob(QueryInfo query, IOJsonJobRequest js, long userID)
  throws Exception {
    log.trace("JobCreationService::createJob(query={}, js={}, userID={})", query, js, userID);
    {
      var errs = BlastValidator.getInstance().validate(js.getConfig());
      if (!errs.isEmpty())
        throw new UnprocessableEntityException(errs);
    }

    verifyResultLimit(js, query);

    if (js.getTargets() == null || js.getTargets().length == 0)
      throw new UnprocessableEntityException(Collections.singletonMap("targets", Collections.singletonList("1 or more targets must be selected.")));

    var jobInfo = new JobInfo();
    var dbPath  = new StringBuilder();
    var usrInfo = new UserInfo();

    for (var db : js.getTargets()) {
      var path = JobDataManager.makeDBPath(js.getSite(), db.organism(), db.target());
      if (!JobDataManager.targetDBExists(path))
        throw new BadRequestException("unrecognized query target");
      if (!dbPath.isEmpty())
        dbPath.append(' ');
      dbPath.append(path);
    }

    usrInfo.userID = userID;
    usrInfo.maxDlSize = js.getMaxResultSize() == null ? 0 : js.getMaxResultSize();
    usrInfo.description = js.getDescription();

    jobInfo.query = query;
    jobInfo.job = JobConverter.toInternal(js.getConfig());
    jobInfo.cli = new CliBuilder();
    jobInfo.job.getJobConfig().setDatabase(dbPath.toString());

    var queryHashString = Format.toHexString(query.queryHash);
    log.debug("Query Hash: {}", queryHashString);

    // Overwrite the query value with a hash string containing the sha256
    // checksum of the actual query value.
    // The actual query is stored as a file.
    jobInfo.job.getJobConfig().setQuery(queryHashString);

    // Convert the job config to a CLI format (which will be hashed).
    jobInfo.job.getJobConfig().toCli(jobInfo.cli);

    jobInfo.jobHash = hashJob(
      js.getSite(),
      dbPath.toString(),
      js.getConfig().getTool().name,
      jobInfo.cli.toString()
    );

    log.debug("Job Hash: {}", () -> Format.toHexString(jobInfo.jobHash));

    // Check if the hash for the current job collides with a pre-existing job.
    var collide = JobDBManager.getJob(jobInfo.jobHash);

    if (collide.isEmpty())
      return createNew(jobInfo, usrInfo);

    // If we've hit a job that is dead but has not yet been cleaned up
    // automatically, then do it manually before proceeding.
    if (OffsetDateTime.now().isAfter(collide.get().deleteOn())) {
      new JobCleanup().runChecked();

      return createNew(jobInfo, usrInfo);
    }

    var jobID = Format.toHexString(jobInfo.jobHash);

    if (!JobDataManager.jobDataExists(jobID))
      throw new IllegalStateException("Job " + jobID + " has no workspace");

    return handleCollision(jobInfo.jobHash, userID, usrInfo);
  }

  static NewJobPostResponse createNew(JobInfo job, UserInfo user) throws Exception {
    log.trace("JobCreationService::createNew(job={}, user={})", job, user.userID);

    var jobIDString = Format.toHexString(job.jobHash);
    var jobPath     = JobDataManager.createJobWorkspace(jobIDString);
    var queueID     = JobQueueManager.submitJob(
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

    var out = new NewJobPostResponseImpl();
    out.setJobId(jobIDString);
    return out;
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
  static NewJobPostResponse handleCollision(byte[] jobHash, long userID, UserInfo usrInfo)
  throws Exception {
    log.trace("JobCreationService::handleCollision(jobHash={}, userID={}, usrInfo={})", jobHash, userID, usrInfo);

    // TODO: what about if a user submits the same job 2x with different descriptions?
    if (JobDBManager.getUserJob(jobHash, userID).isEmpty()) {
      JobDBManager.linkUserToJob(new UserRowImpl(jobHash, userID, usrInfo.description, usrInfo.maxDlSize));
      JobDBManager.updateJobDeleteTimer(
        jobHash,
        OffsetDateTime.now().plusDays(conf.getJobTimeout())
      );
    }

    var out = new NewJobPostResponseImpl();
    out.setJobId(Format.toHexString(jobHash));
    return out;
  }

  /**
   * Writes the contents of the request's query to a tmp file on disk.
   *
   * @param query Stream containing query contents to write.
   *
   * @return Info about the temp file that was written.
   */
  static QueryInfo writeQueryToTmp(InputStream query) throws Exception {
    log.trace("JobCreationService::writeQueryToTmp(query={})", query);
    var tmp = new File("/tmp/" + UUID.randomUUID().toString());

    if (!tmp.createNewFile())
      throw new RuntimeException("Failed to create temp file for query.");

    try (
      var write = new BufferedWriter(new FileWriter(tmp));
      var read  = new Scanner(query)
    ) {
      var dig     = MessageDigest.getInstance(Format.HASH_TYPE);
      var queries = 0;

      read.useDelimiter("\r\n|\n|\r");

      while (read.hasNext()) {
        var line = read.next();

        write.write(line);
        write.write('\n');

        if (line.startsWith(">"))
          queries++;

        dig.update(line.getBytes(StandardCharsets.UTF_8));
      }

      return new QueryInfo(tmp, dig.digest(), queries);
    }
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

  static void verifyResultLimit(IOJsonJobRequest req, QueryInfo query) {
    if (req.getMaxResults() != null && req.getMaxResults() > 0)
      ResultLimitValidator.validateResultLimit(
        req.getMaxResults(),
        query.numQueries,
        req.getConfig()
      ).ifPresent(m -> { throw new UnprocessableEntityException(m); });
  }

  static byte[] hashJob(String site, String dbs, String tool, String cli) {
    return Format.toSHA256(String.format(hashPattern, site, dbs, tool, cli));
  }
}

class QueryInfo
{
  final File   tmpFile;
  final byte[] queryHash;
  final int    numQueries;

  QueryInfo(File a, byte[] b, int c) {
    tmpFile    = a;
    queryHash  = b;
    numQueries = c;
  }
}

class JobInfo
{
  byte[]     jobHash;
  Job        job;
  QueryInfo  query;
  CliBuilder cli;
}

class UserInfo
{
  long   userID;
  String description;
  long   maxDlSize;
}
