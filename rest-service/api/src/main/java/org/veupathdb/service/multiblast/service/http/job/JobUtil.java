package org.veupathdb.service.multiblast.service.http.job;

import java.util.Collection;
import javax.ws.rs.BadRequestException;

import mb.lib.db.model.ShortJobRow;
import mb.lib.extern.JobQueueManager;
import mb.lib.extern.model.QueueJobStatus;
import mb.lib.jobData.JobDataManager;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;
import org.veupathdb.service.multiblast.generated.model.IOJobTarget;
import org.veupathdb.service.multiblast.generated.model.IOJsonJobRequest;
import org.veupathdb.service.multiblast.util.Format;

class JobUtil
{
  static String makeDBPaths(String site, Collection<IOJobTarget> targets) {
    var dbPath = new StringBuilder();

    for (var db : targets) {
      var path = JobDataManager.makeDBPath(site, db.organism(), db.target());
      if (!JobDataManager.targetDBExists(path))
        throw new BadRequestException("unrecognized query target: (" + db.organism() + ", " + db.target() + ")");
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
  static void verifyResultLimit(IOJsonJobRequest req, QuerySplitResult query) {
    if (req.getMaxResults() != null && req.getMaxResults() > 0)
      ResultLimitValidator.validateResultLimit(
        req.getMaxResults(),
        query.subQueries.size() + 1,
        req.getConfig()
      ).ifPresent(m -> { throw new UnprocessableEntityException(m); });
  }

  static boolean jobIsCached(ShortJobRow job) throws Exception {
    var status = JobQueueManager.jobStatus(job.queueID());
    var jobID  = Format.toHexString(job.jobHash());

    if (status == QueueJobStatus.Completed)
      return JobDataManager.reportExists(jobID);

    return false;
  }
}
