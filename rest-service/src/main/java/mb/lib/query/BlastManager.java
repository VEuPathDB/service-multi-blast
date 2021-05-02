package mb.lib.query;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import io.vulpine.lib.iffy.Either;
import mb.api.service.model.ErrorMap;
import mb.lib.config.Config;
import mb.lib.data.JobDataManager;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.query.model.BlastJob;
import mb.lib.query.model.BlastRow;
import mb.lib.query.model.FullUserBlastRow;
import mb.lib.query.model.UserBlastRow;
import mb.lib.util.MD5;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlastManager
{
  private static final Logger Log        = LogManager.getLogger(BlastManager.class);
  private static final int    MaxWorkers = 5;
  private static final Config Conf       = Config.getInstance();

  /**
   * Retrieves the job identified by {@code jobID} and links the given user ID
   * to that job if that link did not already exist.
   *
   * @param jobID  ID of the job to look up.
   * @param userID ID of the user to optionally link to the job.
   *
   * @return An option that will contain a {@link FullUserBlastRow} if a job
   * exists with the id {@code jobID}.  If no such job exists, an empty option
   * will be returned.
   */
  public static Optional<FullUserBlastRow> getAndLinkUserBlastJob(HashID jobID, long userID)
  throws Exception {
    Log.trace("::getUserBlastJob(jobID={}, userID={})", jobID, userID);
    try (var db = new BlastDBManager()) {
      // See if the user is already linked to the job.
      var try1 = db.getUserBlastRow(jobID, userID)
        .map(FullUserBlastRow::new);

      // They are linked, fill out the rest of the fields and return.
      if (try1.isPresent()) {
        var tmp = try1.get();

        tmp.setTargetDBs(db.getTargetLinks(jobID));
        tmp.setChildJobs(db.getChildJobLinks(jobID));
        tmp.setParentJobs(db.getParentJobLinks(jobID));

        refreshJobStatus(db, tmp);

        return try1;
      }

      // The user is not linked to the target job.  Check to see if the job even
      // exists.
      var try2 = db.getBlastRow(jobID);

      // If the job doesn't exist, then bail here as there is nothing to do.
      if (try2.isEmpty())
        return Optional.empty();

      // The job does exist, so link the user.
      var tmp = new FullUserBlastRow(try2.get())
        .setUserID(userID)
        .setRunDirectly(true);
      db.linkUser(tmp);

      // Populate the rest of the fields.
      tmp.setTargetDBs(db.getTargetLinks(jobID));
      tmp.setChildJobs(db.getChildJobLinks(jobID));
      tmp.setParentJobs(db.getParentJobLinks(jobID));

      refreshJobStatus(db, tmp);

      // Link the user to all the child jobs.
      for (var job : tmp.getChildJobs()) {
        if (db.getUserBlastRow(job.getChildJobID(), userID).isEmpty()) {
          db.linkUser(new UserBlastRow()
            .setJobID(job.getChildJobID())
            .setUserID(userID)
            .setRunDirectly(false));
        }
      }

      return Optional.of(tmp);
    }
  }

  public static List<FullUserBlastRow> getUserBlastJobs(long userID) throws Exception {
    Log.trace("::getUserBlastJobs(userID={})", userID);

    try (var db = new BlastDBManager()) {
      var results = db.getUserJobs(userID);

      if (results.isEmpty())
        return Collections.emptyList();

      var jobLinks = db.getUserJobLinks(userID);
      var tgtLinks = db.getUserTargetLinks(userID);
      var out = results.stream()
        .map(FullUserBlastRow::new)
        .peek(r -> r.setChildJobs(jobLinks.byParentID.get(r.getJobID())))
        .peek(r -> r.setParentJobs(jobLinks.byChildID.get(r.getJobID())))
        .peek(r -> r.setTargetDBs(tgtLinks.get(r.getJobID())))
        .collect(Collectors.toList());

      if (out.size() == 1) {
        refreshJobStatus(db, out.get(0));
      }

      asyncRefreshJobStatuses(db, out);

      return out;
    }
  }

  public static Optional<String> getJobQuery(HashID jobID) throws Exception {
    Log.trace("::getJobQuery(jobID={})", jobID);
    try (var db = new BlastDBManager()) {
      return db.getBlastQuery(jobID);
    }
  }

  public static Either<FullUserBlastRow, ErrorMap> createJob(BlastJob conf) throws Exception {
    // Overwrite the query field with the query's hash.
    // This is done so the config hash doesn't collide with other jobs that have
    // the same config but have a different query.
    conf.getConfig().setQueryFile(MD5.hash(conf.getQuery()));

    // getJobID will generates a hash on first call.
    var jobID = conf.getJobID();

    try (var db = new BlastDBManager()) {

      // First lets see if the user is resubmitting a job they already have.
      {
        var optJob = db.getUserBlastRow(jobID, conf.getUserID());

        // They already have a job matching this one.  Refresh it's status,
        // populate it's list fields, and return it.
        if (optJob.isPresent()) {
          return submitExistingUserJob(db, new FullUserBlastRow(optJob.get()));
        }
      }

      // The user is not attached to the job, but it may still exist.
      {
        var optJob = db.getBlastRow(jobID);

        // If the job exists.
        if (optJob.isPresent()) {
          return submitExistingJob(db, conf, optJob.get());
        }
      }

      // If we've made it this far, the job doesn't exist.

      // Since the job didn't previously exist, we need to validate it.
      var err = BlastValidationManager.Validate(conf.getConfig());

      // If there are validation errors, return them, otherwise continue.
      if (err.isPresent())
        return Either.ofRight(err.get());

      var queueID = BlastQueueManager.submitJob(jobID, conf.getConfig());

      var time     = OffsetDateTime.now();
      var shortRow = new BlastRow()
        .setJobID(jobID)
        .setConfig(conf.getConfig())
        .setQuery(conf.getQuery())
        .setQueueID(queueID)
        .setProjectID(conf.getProjectID());
      db.insertJob(new B);
    }

  }

  private static Either<FullUserBlastRow, ErrorMap> submitExistingJob(
    BlastDBManager db,
    BlastJob conf,
    BlastRow row
  ) throws Exception {
    var maxDL = 0L;

    if (conf.getMaxDownloadSize() == 0) {
      maxDL = Conf.getMaxNASeqSize();
    } else {
      maxDL = Math.min(conf.getMaxDownloadSize(), Conf.getMaxNASeqSize());
    }

    // Wrap it for linking.
    var job = new FullUserBlastRow(row);
    job.setUserID(conf.getUserID())
      .setDescription(conf.getDescription())
      .setMaxDownloadSize(maxDL)
      .setRunDirectly(true);

    // Update it's status
    refreshJobStatus(db, job);

    // Load extra record details.
    job.setChildJobs(db.getChildJobLinks(conf.getJobID()));
    job.setParentJobs(db.getParentJobLinks(conf.getJobID()));
    job.setTargetDBs(db.getTargetLinks(conf.getJobID()));

    // Link the current user to the job.
    db.linkUser(job);

    // Create a temporary row to use for dealing with child jobs.
    var tmp = new FullUserBlastRow()
      .setUserID(conf.getUserID())
      .setMaxDownloadSize(maxDL)
      .setDescription(conf.getDescription())
      .setRunDirectly(false);

    for (var child : job.getChildJobs()) {
      tmp.setJobID(child.getChildJobID());

      // Link user to this child job.
      db.linkUser(tmp);

      // Load the rest of the child job's info.
      var fullChild = db.getBlastRow(child.getChildJobID()).get();

      // Refresh the child job to see if it's expired.
      refreshJobStatus(db, fullChild);

      // If it _is_ expired, resubmit it.
      if (fullChild.getStatus() == JobStatus.Expired) {
        var queueID = BlastQueueManager.submitJob(child.getChildJobID(), fullChild.getConfig());
        fullChild.setQueueID(queueID);
        db.updateJobQueue(child.getChildJobID(), queueID, JobStatus.Queued);
      }
    }

    return Either.ofLeft(job);
  }

  private static Either<FullUserBlastRow, ErrorMap> submitExistingUserJob(
    BlastDBManager db,
    FullUserBlastRow job
  ) throws Exception {
    refreshJobStatus(db, job);

    job.setChildJobs(db.getChildJobLinks(job.getJobID()));
    job.setParentJobs(db.getParentJobLinks(job.getJobID()));
    job.setTargetDBs(db.getTargetLinks(job.getJobID()));

    // If the job has expired, rerun it.
    if (job.getStatus() == JobStatus.Expired) {
      var queueID = BlastQueueManager.submitJob(job.getJobID(), job.getConfig());
      job.setStatus(JobStatus.Queued);
      db.updateJobQueue(job.getJobID(), queueID, JobStatus.Queued);
    }

    return Either.ofLeft(job);
  }

  private static void asyncRefreshJobStatuses(
    BlastDBManager db,
    Collection<? extends BlastRow> rows
  ) throws Exception {
    var pool = Executors.newFixedThreadPool(Math.min(rows.size(), MaxWorkers));

    var counter = new CountDownLatch(rows.size());
    for (var row : rows) {
      pool.execute(asyncRefreshJobStatus(db, row, counter));
    }

    counter.await();
    pool.shutdownNow();
  }

  private static Runnable asyncRefreshJobStatus(
    BlastDBManager db,
    BlastRow row,
    CountDownLatch counter
  ) {
    return () -> {
      try {
        refreshJobStatus(db, row);
        counter.countDown();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  private static void refreshJobStatus(BlastDBManager db, BlastRow row) throws Exception {
    Log.trace("::refreshJobStatus(db={}, row={})", db, row);

    switch (row.getStatus()) {
      // If the job is marked as expired or errored there's nothing to do.
      case Errored, Expired:
        return;

      // If the job is marked as queued or in progress, see if the status has
      // changed.
      case Queued, InProgress: {
        var status = BlastQueueManager.jobStatus(row.getQueueID());

        if (status == row.getStatus())
          return;

        // To handle the case where a user fires off a job and then doesn't come
        // back to view it in the expiration period, check the filesystem to
        // confirm the job workspace still exists.
        if (status == JobStatus.Completed && !JobDataManager.workspaceExists(row.getJobID())) {
          status = JobStatus.Expired;
        }

        db.updateJobQueue(row.getJobID(), status);

        return;
      }
      //
      case Completed:
        if (!JobDataManager.workspaceExists(row.getJobID())) {
          db.updateJobQueue(row.getJobID(), JobStatus.Expired);
        }
    }
  }
}
