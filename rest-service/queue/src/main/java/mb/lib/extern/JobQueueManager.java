package mb.lib.extern;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import mb.lib.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobQueueManager
{
  public static final String
    JobEndpoint      = "job/%s",
    QueueEndpoint    = "queue",
    QueueIdEndpoint  = QueueEndpoint + "/%s",
    QueueJobEndpoint = QueueIdEndpoint + "/job/%d";

  private static final Logger log = LogManager.getLogger(JobQueueManager.class);

  private static final ObjectMapper json = new ObjectMapper();

  private static final Config conf = Config.getInstance();

  private static JobQueueManager instance;

  private JobQueueManager() {
    log.trace("JobQueueManager::new()");
  }

  public static JobQueueManager getInstance() {
    log.trace("JobQueueManager::getInstance()");
    if (instance == null)
      return instance = new JobQueueManager();

    return instance;
  }

  public static JobStatus jobStatus(int queueId) throws Exception {
    return getInstance().getJobStatus(queueId);
  }

  public JobStatus getJobStatus(int queueId) throws Exception {
    log.trace("JobQueueManager#getJobStatus(int)");

    var con = Config.getInstance();
    var uri = URI.create(Config.getInstance().getQueueHost()).
      resolve(String.format(QueueJobEndpoint, con.getQueueName(), queueId));

    log.debug("Attempting to look up job status for queue element {} at {}", queueId, uri);
    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder()
        .uri(uri)
        .GET()
        .build(),
      HttpResponse.BodyHandlers.ofString()
    );

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
   * @see #submitJob(String, String[])
   */
  public static int submitJob(String jobId, String[] cli) throws Exception {
    return getInstance().submitNewJob(jobId, cli);
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
  public int submitNewJob(String jobId, String[] cli) throws Exception {
    log.trace("JobQueueManager#submitJob(String, String[])");

    var uri = URI.create(Config.getInstance().getQueueHost())
      .resolve(String.format(JobEndpoint, Config.getInstance().getJobCategory()));

    log.debug("Attempting to queue job {} at {}", jobId, uri);
    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder()
        .uri(uri)
        .POST(HttpRequest.BodyPublishers.ofString(
          json.writeValueAsString(new JobCreateRequest(Config.getInstance().getBlastHost(), jobId, cli))
        ))
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
    log.trace("JobQueueManager#deleteJob(String)");

    var uri = URI.create(conf.getQueueHost())
      .resolve(String.format(QueueJobEndpoint, conf.getQueueName(), queueID));

    log.debug("Attempting to delete queue entry for job {}", queueID);
    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(uri).DELETE().build(),
      HttpResponse.BodyHandlers.discarding()
    );
  }
}
