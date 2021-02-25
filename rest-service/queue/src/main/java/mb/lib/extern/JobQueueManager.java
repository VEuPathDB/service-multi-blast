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
    JobEndpoint      = "/job/%s",
    QueueEndpoint    = "/queue",
    QueueIdEndpoint  = QueueEndpoint + "/%s",
    QueueJobEndpoint = QueueIdEndpoint + "/job/%d",
    FailedEndpoint   = QueueIdEndpoint + "/failed/%d";

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

  public static JobStatus jobStatus(int queueId) throws Exception {
    return getInstance().getJobStatus(queueId);
  }

  public boolean jobInFailList(int queueID) throws Exception {
    log.trace("#jobInFailList(queueID={})", queueID);

    log.debug("Checking failed job list for queue ID {}", queueID);
    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(makeURI(FailedEndpoint, queueID)).GET().build(),
      HttpResponse.BodyHandlers.discarding()
    );

    return res.statusCode() == 200;
  }

  public JobStatus getJobStatus(int queueID) throws Exception {
    log.trace("#getJobStatus(queueID={})", queueID);

    log.debug("Looking up job status for queue ID {}", queueID);
    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder()
        .uri(makeURI(QueueJobEndpoint, queueID))
        .GET()
        .build(),
      HttpResponse.BodyHandlers.ofString()
    );

    if (res.statusCode() == 404) {
      if (jobInFailList(queueID))
        return JobStatus.Errored;
      else
        return JobStatus.Completed;
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

    var uri = URI.create(prependHTTP(conf.getQueueHost()))
      .resolve(String.format(JobEndpoint, conf.getJobCategory()));

    var sendBody = json.writeValueAsString(new JobCreateRequest(
      String.join("/", prependHTTP(conf.getBlastHost()), tool, jobId),
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
    log.trace("#deleteJob(queueID={})", queueID);

    var uri = URI.create(prependHTTP(conf.getQueueHost()))
      .resolve(String.format(QueueJobEndpoint, conf.getQueueName(), queueID));

    log.debug("Attempting to delete queue entry for job {}", queueID);
    HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(uri).DELETE().build(),
      HttpResponse.BodyHandlers.discarding()
    );
  }

  static String prependHTTP(String uri) {
    if (uri.startsWith("http://") || uri.startsWith("https://"))
      return uri;

    return "http://" + uri;
  }

  static URI makeURI(String pat, int queueID) {
    return URI.create(prependHTTP(conf.getQueueHost())).
      resolve(String.format(pat, conf.getQueueName(), queueID));
  }
}
