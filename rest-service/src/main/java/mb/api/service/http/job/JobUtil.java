package mb.api.service.http.job;

import java.util.Collection;
import javax.ws.rs.BadRequestException;

import mb.api.model.IOJobTarget;
import mb.api.model.IOJsonJobRequest;
import mb.lib.config.Config;
import mb.lib.data.JobDataManager;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;

public class JobUtil
{
  private static final Config Conf = Config.getInstance();

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
   * @throws UnprocessableEntityException if the request configuration could
   * create a result set larger than the client specified limit.
   */
  public static void verifyResultLimit(IOJsonJobRequest req, int numQueries) {
    if (req.getMaxResults() != null && req.getMaxResults() > 0)
      ResultLimitValidator.validateResultLimit(
        req.getMaxResults() == null
          ? Conf.getMaxResults()
          : Math.min(req.getMaxResults(), Conf.getMaxResults()),
        numQueries + 1,
        req.getConfig()
      ).ifPresent(m -> { throw new UnprocessableEntityException(m); });
  }
}
