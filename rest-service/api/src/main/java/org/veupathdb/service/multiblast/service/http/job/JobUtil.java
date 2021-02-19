package org.veupathdb.service.multiblast.service.http.job;

import javax.ws.rs.BadRequestException;

import mb.lib.jobData.JobDataManager;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;
import org.veupathdb.service.multiblast.generated.model.IOJobTarget;
import org.veupathdb.service.multiblast.generated.model.IOJsonJobRequest;

class JobUtil
{
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

}
