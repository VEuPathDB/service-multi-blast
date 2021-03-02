package org.veupathdb.service.multiblast.service.http.job;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import mb.lib.db.JobDBManager;
import mb.lib.db.model.JobTarget;
import mb.lib.db.model.impl.JobTargetImpl;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;
import org.veupathdb.service.multiblast.generated.model.IOBlastConfig;
import org.veupathdb.service.multiblast.generated.model.IOJobPostResponse;
import org.veupathdb.service.multiblast.generated.model.IOJobPostResponseImpl;
import org.veupathdb.service.multiblast.generated.model.IOJsonJobRequest;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.model.io.JsonKeys;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;
import org.veupathdb.service.multiblast.service.conv.JobConverter;
import org.veupathdb.service.multiblast.service.http.Util;
import org.veupathdb.service.multiblast.service.valid.*;
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

    {
      var errs = JobPropsValidator.validate(req);
      if (!errs.isEmpty())
        throw new UnprocessableEntityException(errs);
    }

    try {
      var queryHandle = new QuerySplitter(newSequenceValidator(req.getConfig()), req.getMaxSequences())
        .splitQueries(
          new ByteArrayInputStream(req.getConfig().getQuery().getBytes(StandardCharsets.UTF_8))
        );
      if (!queryHandle.errors.isEmpty()) {
        queryHandle.release();
        throw new UnprocessableEntityException(Collections.singletonMap(JsonKeys.Query, queryHandle.errors));
      }
      return createJobs(queryHandle, req, userID);
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }

  public static IOJobPostResponse createJobs(InputStream query, IOJsonJobRequest props, long userID) {
    log.trace("::createJob(query={}, props={}, userID={})", query, props, userID);

    JobUtil.verifyBody(props);
    JobUtil.verifyProps(props);
    JobUtil.verifyConfig(props.getConfig());

    {
      var errs = JobPropsValidator.validate(props);
      if (!errs.isEmpty())
        throw new UnprocessableEntityException(errs);
    }

    try {
      var result = new QuerySplitter(newSequenceValidator(props.getConfig()), props.getMaxSequences())
        .splitQueries(query);

      if (!result.errors.isEmpty()) {
        result.release();
        throw new UnprocessableEntityException(Collections.singletonMap(JsonKeys.Query, result.errors));
      }

      return createJobs(result, props, userID);
    } catch (Exception e) {
      throw Util.wrapException(e);
    } finally {
      try {
        query.close();
      } catch (Exception e) {
        //noinspection ThrowFromFinallyBlock
        throw Util.wrapException(e);
      }
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

    if (js.getTargets() == null || js.getTargets().size() == 0)
      throw new UnprocessableEntityException(new ErrorMap(
        "targets",
        "1 or more targets must be selected."
      ));

    var dbPath = JobUtil.makeDBPaths(js.getSite(), js.getTargets());
    var job = JobConverter.toInternal(js.getConfig());

    job.getJobConfig().setDatabase(dbPath);

    var rootDets = prepJob(js, job, queries.rootQuery, userID, dbPath, null, true);
    log.debug("Job Hash: {}", rootDets.id);

    try(var db = new JobDBManager()) {
      if (handleJob(db, rootDets) != JobStatus.Linked) {
        var index = 1;

        for (var query : queries.subQueries) {
          log.debug("Handling subquery #{}", index++);
          handleJob(db, prepJob(js, job, query, userID, dbPath, rootDets.hash, false));
        }
      } else {
        for (var query : queries.subQueries)
          //noinspection ResultOfMethodCallIgnored
          query.source.delete();
      }
    }

    return new IOJobPostResponseImpl().setJobId(rootDets.id);
  }

  enum JobStatus {
    Linked,
    Created,
    Rerun
  }
  static JobStatus handleJob(JobDBManager db, JobDetails dets) throws Exception {
    log.trace("#handleJob(dets={})", dets);

    var collision = db.getJob(dets.hash);

    // If job already exists link it or rerun and link it
    if (collision.isPresent()) {
      if (JobDataManager.jobDataExists(dets.id)) {
        log.debug("Job already exists and has cached data.  Linking user to job.");
        //noinspection ResultOfMethodCallIgnored
        dets.query.delete();
        new JobCreator(db).handleLink(dets);
        return JobStatus.Linked;
      } else {
        log.debug("Job already exists but does not have cached data.  Rerunning job.");
        new JobCreator(db).handleRerun(dets);
        return JobStatus.Rerun;
      }
    }

    log.debug("Job did not already exist. Creating new job.");
    new JobCreator(db).handleInitialRun(dets);
    return JobStatus.Created;
  }

  static JobDetails prepJob(
    IOJsonJobRequest js,
    Job              job,
    QuerySplitRow    row,
    long             userID,
    String           dbPath,
    byte[]           parentHash,
    boolean          isPrimary
  ) {
    log.debug("prepJob: query={}", row.source);
    // Overwrite the query value with a hash string containing the sha256
    // checksum of the actual query value.
    // The actual query is stored as a file.
    job.getJobConfig().setQuery(Format.toHexString(row.hash));
    var cli = new CliBuilder();

    // Convert the job config to a CLI format (which will be hashed).
    job.getJobConfig().toCli(cli);

    var tgts = js.getTargets();
    var dets = new JobDetails();
    dets.query = row.source;
    dets.hash  = hashJob(
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
    dets.isPrimary   = isPrimary;
    dets.projectID   = js.getSite();
    dets.targets     = new JobTarget[tgts.size()];

    int i = 0;
    for (var tgt : tgts) {
      dets.targets[i] = new JobTargetImpl(dets.hash, tgt.organism(), tgt.target());
      i++;
    }

    return dets;
  }

  static byte[] hashJob(String site, String dbs, String tool, String cli) {
    return Format.toHash(String.format(hashPattern, site, dbs, tool, cli));
  }

  // TODO: this doesn't really belong here.
  static SequenceValidator newSequenceValidator(IOBlastConfig conf) {
    if (conf.getTool() == null) // Assume blastn
      return new NucleotideSequenceValidator();

    return switch (conf.getTool()) {
      case BLASTP, TBLASTN -> new ProteinSequenceValidator();
      default -> new NucleotideSequenceValidator();
    };
  }
}
