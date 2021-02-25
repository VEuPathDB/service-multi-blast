package mb.lib.extern;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import mb.lib.config.Config;
import mb.lib.extern.consts.Format;
import mb.lib.extern.consts.URL;
import mb.lib.extern.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobQueueManager
{

  private static final Logger log = LogManager.getLogger(JobQueueManager.class);

  private static final ObjectMapper json = new ObjectMapper();

  private static final Config conf = Config.getInstance();

  private static JobQueueManager instance;

  private JobQueueManager() {
    log.trace("JobQueueManager::new()");
  }

  public static JobQueueManager getInstance() {
    log.trace("::getInstance()");
    if (instance == null)
      return instance = new JobQueueManager();

    return instance;
  }

  public static QueueJobStatus jobStatus(int queueId) throws Exception {
    return getInstance().getJobStatus(queueId);
  }

  public boolean jobInFailList(int queueID) throws Exception {
    log.debug("Checking failed job list for queue ID {}", queueID);

    var jobs = getFailedJobs();

    for (var job : jobs) {
      if (job.getJobID() == queueID) {
        return true;
      }
    }

    return false;
  }

  public List<FailedJob> getFailedJobs() throws Exception {
    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.failedEndpoint()).GET().build(),
      HttpResponse.BodyHandlers.ofInputStream()
    );

    return Format.JSON.readValue(res.body(), FailedJobResponse.class).getFailedJobs();
  }

  public void deleteJobFailure(FailedJob job) throws Exception {
    log.debug("Attempting to clear job failure for job {}", job.getJobID());

    HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.failedIDEndpoint(job.getFailID())).DELETE().build(),
      HttpResponse.BodyHandlers.discarding()
    );
  }

  public QueueJobStatus getJobStatus(int queueID) throws Exception {
    log.trace("#getJobStatus(queueID={})", queueID);

    log.debug("Looking up job status for queue ID {}", queueID);
    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.queueJobEndpoint(queueID)).GET().build(),
      HttpResponse.BodyHandlers.ofString()
    );

    if (res.statusCode() == 404) {
      if (jobInFailList(queueID)) {
        return QueueJobStatus.Errored;
      } else {
        return QueueJobStatus.Completed;
      }
    }

    if (res.statusCode() != 200) {
      log.error(
        "Queue server returned an unexpected response.  Code: {}.  Output: {}",
        res.statusCode(),
        res.body()
      );
      throw new Exception("Queue server returned an unexpected response.");
    }

    var body = json.readValue(res.body(), JobStatusResponse.class);

    return body.getStatus();
  }

  /**
   * @see #submitJob(String, String, String[])
   */
  public static int submitJob(String jobId, String tool, String[] cli) throws Exception {
    return getInstance().submitNewJob(jobId, tool, cli);
  }

  /**
   * Submits a new Blast job to the job queue.
   *
   * @param jobId Job configuration digest (used as a unique identifier)
   * @param cli   Blast tool CLI parameters (starting with the blast tool
   *              itself).
   *
   * @return the queue ID for the queued job
   */
  public int submitNewJob(String jobId, String tool, String[] cli) throws Exception {
    log.trace("#submitJob(jobID={}, tool={}, cli={})", jobId, tool, cli);

    var uri = URL.jobEndpoint();

    var sendBody = json.writeValueAsString(new JobCreateRequest(
      String.join("/", URL.prependHTTP(conf.getBlastHost()), tool, jobId),
      cli
    ));

    log.debug("Attempting to queue job {} at {}", jobId, uri);
    log.debug(sendBody);
    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder()
        .uri(uri)
        .POST(HttpRequest.BodyPublishers.ofString(sendBody))
        .build(),
      HttpResponse.BodyHandlers.ofString()
    );

    if (res.statusCode() != 200) {
      log.error(
        "Queue server returned an unexpected response.  Code: {}.  Output: {}",
        res.statusCode(),
        res.body()
      );
      throw new RuntimeException("Queue server returned an unexpected response.");
    }

    var body = json.readValue(res.body(), JobCreateResponse.class);
    log.debug("Job {} successfully queued", jobId);

    return body.getId();
  }

  public static void deleteJob(int queueID) throws Exception {
    log.debug("Attempting to delete queue entry for job {}", queueID);
    HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.queueJobEndpoint(queueID)).DELETE().build(),
      HttpResponse.BodyHandlers.discarding()
    );
  }
}
