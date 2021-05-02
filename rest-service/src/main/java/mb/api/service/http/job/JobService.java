package mb.api.service.http.job;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.StreamingOutput;

import mb.api.model.IOJobPostResponse;
import mb.api.model.IOJsonJobRequest;
import mb.api.model.IOLongJobResponse;
import mb.api.model.IOShortJobResponse;
import mb.api.service.util.Format;
import mb.lib.db.JobDBManager;
import mb.lib.model.HashID;
import mb.lib.query.BlastManager;
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
    log.trace("::new()");
  }

  public static JobService getInstance() {
    log.trace("::getInstance()");

    if (instance == null)
      instance = new JobService();

    return instance;
  }

  public IOLongJobResponse getJob(HashID jobID, long userID) {
    log.trace("#getJob(jobID={}, userID={})", jobID, userID);

    try {
      return Util.translateLongResponse(BlastManager.getAndLinkUserBlastJob(jobID, userID)
        .orElseThrow(NotFoundException::new));
    } catch (Exception e) {
      throw wrapException(e);
    }
  }

  public List<IOShortJobResponse> getShortJobList(long userID) {
    log.trace("#getJobs(userID={})", userID);

    try {
      return Util.translateShortResponses(BlastManager.getUserBlastJobs(userID));
    } catch (Exception ex) {
      throw wrapException(ex);
    }
  }

  public String getQuery(HashID jobID) {
    log.trace("#getQuery(jobID={})", jobID);

    try {
      return BlastManager.getJobQuery(jobID).orElseThrow(NotFoundException::new);
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
}
