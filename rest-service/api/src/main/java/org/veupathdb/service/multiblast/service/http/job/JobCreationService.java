package org.veupathdb.service.multiblast.service.http.job;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import mb.lib.db.JobDBManager;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;
import org.veupathdb.service.multiblast.generated.model.IOJobPostResponse;
import org.veupathdb.service.multiblast.generated.model.IOJobPostResponseImpl;
import org.veupathdb.service.multiblast.generated.model.IOJsonJobRequest;
import org.veupathdb.service.multiblast.generated.model.IOMultipartJobRequest;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;
import org.veupathdb.service.multiblast.service.conv.JobConverter;
import org.veupathdb.service.multiblast.service.http.Util;
import org.veupathdb.service.multiblast.service.valid.BlastValidator;
import org.veupathdb.service.multiblast.util.Format;

public class JobCreationService
{
  private static final Logger log  = LogManager.getLogger(JobCreationService.class);

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
    JobUtil.verifyBody(req);
    JobUtil.verifyConfig(req.getConfig());
    JobUtil.verifyQuery(req.getConfig().getQuery());

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

    JobUtil.verifyBody(req);
    JobUtil.verifyProps(req.getProperties());
    JobUtil.verifyConfig(req.getProperties().getConfig());
    JobUtil.verifyQuery(req.getQuery());

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

    JobUtil.verifyResultLimit(js, queries);

    if (js.getTargets() == null || js.getTargets().length == 0)
      throw new UnprocessableEntityException(new ErrorMap(
        "targets",
        "1 or more targets must be selected."
      ));

    var dbPath = JobUtil.makeDBPaths(js.getSite(), js.getTargets());
    var job = JobConverter.toInternal(js.getConfig());

    job.getJobConfig().setDatabase(dbPath);

    var rootDets = prepJob(js, job, queries.rootQuery, userID, dbPath);
    log.debug("Job Hash: {}", rootDets.id);

    if (handleJob(rootDets) != JobStatus.Linked) {
      var index = 1;

      for (var query : queries.subQueries) {
        log.debug("Handling subquery #{}", index++);
        handleJob(prepJob(js, job, query, userID, dbPath, rootDets.hash));
      }
    } else {
      for (var query : queries.subQueries)
        query.source.delete();
    }

    return new IOJobPostResponseImpl().setJobId(rootDets.id);
  }

  enum JobStatus {
    Linked,
    Created,
    Rerun
  }
  static JobStatus handleJob(JobDetails dets) throws Exception {
    log.trace("#handleJob(dets={})", dets);

    var collision = JobDBManager.getJob(dets.hash);

    // If job already exists link it or rerun and link it
    if (collision.isPresent()) {
      if (JobDataManager.jobDataExists(dets.id)) {
        log.debug("Job already exists and has cached data.  Linking user to job.");
        dets.source.delete();
        new JobCreator().handleLink(dets);
        return JobStatus.Linked;
      } else {
        log.debug("Job already exists but does not have cached data.  Rerunning job.");
        new JobCreator().handleRerun(dets);
        return JobStatus.Rerun;
      }
    }

    log.debug("Job did not already exist. Creating new job.");
    new JobCreator().handleInitialRun(dets);
    return JobStatus.Created;
  }

  static JobDetails prepJob(
    IOJsonJobRequest js,
    Job job,
    QuerySplitRow row,
    long userID,
    String dbPath
  ) {
    return prepJob(js, job, row, userID, dbPath, null);
  }

  static JobDetails prepJob(
    IOJsonJobRequest js,
    Job job,
    QuerySplitRow row,
    long userID,
    String dbPath,
    byte[] parentHash
  ) {
    log.debug("prepJob: query={}", row.source);
    // Overwrite the query value with a hash string containing the sha256
    // checksum of the actual query value.
    // The actual query is stored as a file.
    job.getJobConfig().setQuery(Format.toHexString(row.hash));
    var cli = new CliBuilder();

    // Convert the job config to a CLI format (which will be hashed).
    job.getJobConfig().toCli(cli);

    var dets = new JobDetails();
    dets.source      = row.source;
    dets.hash        = hashJob(
      js.getSite(),
      dbPath,
      js.getConfig().getTool().name,
      cli.toString()
    );
    dets.id          = Format.toHexString(dets.hash);
    dets.job         = job;
    dets.cli         = cli;
    dets.userID      = userID;
    dets.description = js.getDescription();
    dets.maxDlSize   = js.getMaxResultSize();
    dets.parentHash  = parentHash;

    return dets;
  }

  static byte[] hashJob(String site, String dbs, String tool, String cli) {
    return Format.toHash(String.format(hashPattern, site, dbs, tool, cli));
  }
}
