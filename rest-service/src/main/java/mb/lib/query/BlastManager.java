package mb.lib.query;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import mb.api.service.model.ErrorMap;
import mb.lib.data.JobDataManager;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.query.model.BlastJob;
import mb.lib.query.model.BlastRow;
import mb.lib.query.model.FullUserBlastRow;
import mb.lib.query.model.UserBlastRow;
import mb.lib.queue.model.FailedJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.blast.BlastQueryConfig;

public class BlastManager
{
  private static final Logger Log        = LogManager.getLogger(BlastManager.class);
  private static final int    MaxWorkers = 5;

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
        updateLastModified(jobID);

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
      updateLastModified(jobID);

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
        updateLastModified(out.get(0).getJobID());
      } else {
        asyncRefreshJobStatuses(db, out);
      }

      return out;
    }
  }

  public static Optional<String> getJobQuery(HashID jobID) throws Exception {
    Log.trace("::getJobQuery(jobID={})", jobID);
    try (var db = new BlastDBManager()) {
      updateLastModified(jobID);
      return db.getBlastQuery(jobID);
    }
  }

  private static FullUserBlastRow populateLinks(BlastDBManager db, UserBlastRow row) throws Exception {
    var tmp = new FullUserBlastRow(row);

    tmp.setChildJobs(db.getChildJobLinks(row.getJobID()));
    tmp.setParentJobs(db.getParentJobLinks(row.getJobID()));
    tmp.setTargetDBs(db.getTargetLinks(row.getJobID()));

    return tmp;
  }

  private static void submitToQueueIfExpired(BlastDBManager db, BlastRow row) throws Exception {
    if (row.getStatus() == JobStatus.Expired) {
      JobDataManager.createJobWorkspace(row.getJobID());
      JobDataManager.createQueryFile(row.getJobID(), row.getQuery());

      var queueID = BlastQueueManager.submitJob(row.getJobID(), row.getConfig());

      row.setQueueID(queueID);
      row.setStatus(JobStatus.Queued);

      db.updateJobQueue(row.getJobID(), queueID, JobStatus.Queued);
    }
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
        updateLastModified(row.getJobID());
        counter.countDown();
      } catch (Exception e) {
        counter.countDown();
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

        db.updateJobQueue(row.getJobID(), row.getQueueID(), status);
        row.setStatus(status);

        if (status == JobStatus.Errored) {
          BlastQueueManager.getInstance()
            .getFailedJobs()
            .stream()
            .filter(r -> r.getJobID() == row.getQueueID())
            .map(FailedJob::getFailID)
            .forEach(i -> {
              try {
                BlastQueueManager.getInstance().deleteJobFailure(i);
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            });
        }

        return;
      }
      //
      case Completed:
        if (!JobDataManager.workspaceExists(row.getJobID())) {
          db.updateJobQueue(row.getJobID(), row.getQueueID(), JobStatus.Expired);
          row.setStatus(JobStatus.Expired);
        }
    }
  }

  public static Optional<ErrorMap> validateConfig(BlastQueryConfig conf) throws Exception{
    return BlastValidationManager.Validate(conf);
  }

  /**
   * Updates the last modified date on a job workspace to delay it's expiration
   * as it is still being actively used.
   *
   * @param jobID ID of the job which will have it's workspace last modified
   *              date updated.
   */
  public static void updateLastModified(HashID jobID) {
    Log.trace("::updateLastModified(jobID={})", jobID);

    var wd = JobDataManager.jobWorkspace(jobID);

    if (!wd.exists())
      return;

    wd.updateLastModified();
  }

  // ╔══════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                      ║ //
  // ║    Job Creation                                                      ║ //
  // ║                                                                      ║ //
  // ╚══════════════════════════════════════════════════════════════════════╝ //

  public static FullUserBlastRow submitJob(BlastJob job) throws Exception {
    try (var db = new BlastDBManager()) {
      return handleJobs(db, job);
    }
  }

  static FullUserBlastRow handleJobs(BlastDBManager db, BlastJob job) throws Exception {
    var root = handleJob(db, job, job.getQuery().getFullQuery());

    // If size is 1 then it's the whole query, which we've already handled.
    if (job.getQuery().getSequenceCount() > 1) {
      job.setParentID(root.getJobID());
      for (var query : job.getQuery().getSubQueries()) {
        handleJob(db, job, query.toString());
      }
    }

    return populateLinks(db, root);
  }

  static UserBlastRow handleJob(BlastDBManager db, BlastJob job, String query) throws Exception {
    var jobID = job.digest(query);

    var optRow1 = db.getUserBlastRow(jobID, job.getUserID());
    if (optRow1.isPresent()) {
      var row = optRow1.get();

      refreshJobStatus(db, row);
      submitToQueueIfExpired(db, row);

      return row;
    }

    var optJob2 = db.getBlastRow(jobID);
    if (optJob2.isPresent()) {
      var row = new UserBlastRow(optJob2.get())
        .setUserID(job.getUserID())
        .setDescription(job.getDescription())
        .setMaxDownloadSize(job.getMaxDLSize())
        .setRunDirectly(job.isPrimary());

      db.linkUser(row);
      refreshJobStatus(db, row);
      submitToQueueIfExpired(db, row);

      return row;
    }

    var tim = OffsetDateTime.now();
    var row = new UserBlastRow()
      .setJobID(jobID)
      .setConfig(job.getConfig())
      .setQuery(query)
      .setStatus(JobStatus.Expired)
      .setProjectID(job.getSite())
      .setCreatedOn(tim)
      .setDeleteOn(tim)
      .setUserID(job.getUserID())
      .setDescription(job.getDescription())
      .setMaxDownloadSize(job.getMaxDLSize())
      .setRunDirectly(job.isPrimary());

    submitToQueueIfExpired(db, row);

    db.insertJob(row);
    db.linkUser(row);
    db.linkTargets(jobID, job.getTargets());
    if (!job.isPrimary())
      db.linkJobs(jobID, job.getParentID());

    return row;
  }
}
