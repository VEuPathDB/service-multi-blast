package mb.api.service.http.job;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import javax.ws.rs.BadRequestException;

import mb.lib.db.JobDBManager;
import mb.lib.db.model.DBJobStatus;
import mb.lib.db.model.FullJobRow;
import mb.lib.db.model.FullUserJobRow;
import mb.lib.db.model.ShortJobRow;
import mb.lib.db.model.impl.FullJobRowImpl;
import mb.lib.db.model.impl.FullUserJobRowImpl;
import mb.lib.db.model.impl.ShortJobRowImpl;
import mb.lib.model.JobStatus;
import mb.lib.querier.BlastQueueManager;
import mb.lib.queue.model.QueueJobStatus;
import mb.lib.data.JobDataManager;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;
import mb.api.model.IOJobTarget;
import mb.api.model.IOJsonJobRequest;

public class JobUtil
{
  public static String makeDBPaths(String site, Collection<IOJobTarget> targets) {
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

  public static void verifyQuery(Object req) {
    nullCheck(req, "query cannot be null");
  }

  public static void verifyBody(Object req) {
    nullCheck(req, "request body cannot be null");
  }

  public static void verifyProps(Object req) {
    nullCheck(req, "job properties cannot be null");
  }

  public static void verifyConfig(Object req) {
    nullCheck(req, "blast config cannot be null");
  }

  public static void nullCheck(Object req, String msg) {
    if (req == null)
      throw new BadRequestException(msg);
  }

  /**
   * Verifies that the given request + query set is not attempting to create a
   * larger result set than the client specified max result limit.
   * <p>
   * If the client did not specify a max result limit, this method does nothing.
   *
   * @param req   Client request job configuration.
   * @param query Split set of queries.
   *
   * @throws UnprocessableEntityException if the request configuration could
   * create a result set larger than the client specified limit.
   */
  public static void verifyResultLimit(IOJsonJobRequest req, QuerySplitResult query) {
    if (req.getMaxResults() != null && req.getMaxResults() > 0)
      ResultLimitValidator.validateResultLimit(
        req.getMaxResults(),
        query.subQueries.size() + 1,
        req.getConfig()
      ).ifPresent(m -> { throw new UnprocessableEntityException(m); });
  }

  public static boolean jobIsCached(ShortJobRow job) throws Exception {
    if (BlastQueueManager.jobStatus(job.queueID()) == QueueJobStatus.Completed)
      return JobDataManager.jobWorkspace(job.jobID()).resultExists();

    return false;
  }

  public static ShortJobRow updateJobStatus(ShortJobRow row, JobDBManager db) throws Exception {
    return updateJobStatus(db, row, (old, status) -> new ShortJobRowImpl(
      old.jobID(),
      old.queueID(),
      old.createdOn(),
      old.deleteOn(),
      old.projectID(),
      status
    ));
  }

  public static FullJobRow updateJobStatus(FullJobRow row, JobDBManager db) throws Exception {
    return updateJobStatus(db, row, (old, status) -> new FullJobRowImpl(
      old.jobID(),
      old.queueID(),
      old.createdOn(),
      old.deleteOn(),
      old.config(),
      old.query(),
      old.projectID(),
      status
    ));
  }

  public static FullUserJobRow updateJobStatus(FullUserJobRow row, JobDBManager db) throws Exception {
    return updateJobStatus(db, row, (old, status) -> new FullUserJobRowImpl(
      old.jobID(),
      old.queueID(),
      old.createdOn(),
      old.deleteOn(),
      old.config(),
      old.query(),
      old.projectID(),
      status,
      old.userID(),
      old.description(),
      old.maxDownloadSize(),
      old.runDirectly()
    ));
  }

  private static <T extends ShortJobRow> T updateJobStatus(
    JobDBManager db,
    T val,
    BiFunction<T, JobStatus, T> fn
  ) throws Exception {
    if (val.status() == JobStatus.Errored || val.status() == JobStatus.Expired)
      return val;

    if (val.status() == JobStatus.Completed) {
      if (!JobDataManager.workspaceExists(val.jobID())) {
        db.updateJobStatus(val.jobID(), JobStatus.Expired);
        return fn.apply(val, JobStatus.Expired);
      }
    }

    // If we've made it here, then one of the following is true:
    //   - the job is "completed" and has a workspace
    //   - the job is "queued"
    //   - the job is "in-progress"
    var queueStatus = convert(BlastQueueManager.jobStatus(val.queueID()));

    if (queueStatus == val.status())
      return val;

    // If the queue returned a status "completed" and the previous status was
    // not completed, then make sure the workspace actually exists,
    if (queueStatus == JobStatus.Completed && !JobDataManager.workspaceExists(val.jobID())) {
      queueStatus = JobStatus.Expired;
    }

    db.updateJobStatus(val.jobID(), queueStatus);
    return fn.apply(val, queueStatus);
  }

  private static JobStatus convert(QueueJobStatus stat) {
    return switch (stat) {
      case Completed -> JobStatus.Completed;
      case Errored -> JobStatus.Errored;
      case Queued -> JobStatus.Queued;
      case InProgress -> JobStatus.InProgress;
    };
  }
}
