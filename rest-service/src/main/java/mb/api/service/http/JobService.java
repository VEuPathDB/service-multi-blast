package mb.api.service.http;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.*;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.StreamingOutput;

import mb.api.model.*;
import mb.api.service.conv.BCC;
import mb.api.service.http.job.JobCreationService;
import mb.api.service.util.Format;
import mb.lib.data.JobDataManager;
import mb.lib.db.JobDBManager;
import mb.lib.db.model.*;
import mb.lib.db.model.impl.UserRowImpl;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.querier.BlastQueueManager;
import mb.lib.util.BlastConv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.model.User;

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

      var out = new IOLongJobResponseImpl();
      out.setConfig(BlastConv.convert(BlastConv.convertJobConfig(job.config())));

      // Update the modified date to avoid deleting actively used jobs in the
      // job cleanup.
      var workspace = JobDataManager.jobWorkspace(jobID);
      workspace.updateLastModified(OffsetDateTime.now());

      out.setId(rawID)
        .setDescription(userRow.description())
        .setStatus(syncJobStatus(job))
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

  public List<IOShortJobResponse> getShortJobList(User user) {
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
          .setStatus(syncJobStatus(job))
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

  static JobStatus syncJobStatus(ShortJobRow job) throws Exception {
    var inStatus = job.status();

    if (inStatus == JobStatus.Completed || inStatus == JobStatus.Errored) {
      return inStatus;
    }

    // Since we have a queued or in-progress status, hit the queue to see if the
    // status has changed.
    var status = BlastQueueManager.jobStatus(job.queueID());

    // If the status _has_ changed, then insert the new status into the database
    if (status != inStatus) {
      log.debug("Updating db status from {} to {}", status, inStatus);
      try (var db = new JobDBManager()) {
        db.updateJobStatus(job.jobID(), status);
      }
    }

    return status;
  }
}
