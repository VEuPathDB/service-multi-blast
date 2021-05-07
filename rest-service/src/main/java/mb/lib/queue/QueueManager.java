package mb.lib.queue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import mb.lib.model.JobStatus;
import mb.lib.queue.consts.URL;
import mb.lib.queue.model.*;
import mb.lib.util.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class QueueManager
{
  private static final Logger log = LogManager.getLogger(QueueManager.class);

  public abstract List<? extends FailedJob<?>> getFailedJobs() throws Exception;

  public abstract JobStatus getJobStatus(int jobID) throws Exception;

  public abstract boolean jobInFailList(int jobID) throws Exception;

  public abstract void deleteJobFailure(int failID) throws Exception;

  public abstract void deleteJob(int jobID) throws Exception;

  /**
   * @return The URL to use when submitting jobs to the managed queue.
   */
  protected abstract URI submissionURL();

  protected <
    T extends FailedJobResponse<U>,
    U extends FailedJob<V>,
    V
  > FailedJobResponse<U> getFailedJobs(
    String queueName,
    Class<T> type
  ) throws Exception {
    log.trace("#getFailedJobs(queueName={})", queueName);

    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.failedEndpoint(queueName)).GET().build(),
      HttpResponse.BodyHandlers.ofInputStream()
    );

    return JSON.parse(res.body(), type);
  }

  protected JobStatus getJobStatus(String queueName, int jobID) throws Exception {
    log.trace("#getJobStatus(queueName={}, jobID={})", queueName, jobID);

    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.queueJobEndpoint(queueName, jobID)).GET().build(),
      HttpResponse.BodyHandlers.ofString()
    );

    if (res.statusCode() == 404) {
      if (jobInFailList(jobID)) {
        return JobStatus.Errored;
      } else {
        return JobStatus.Completed;
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

    var body = JSON.parse(res.body(), JobStatusResponse.class);

    return body.getStatus();
  }

  protected <
    T extends FailedJobResponse<U>,
    U extends FailedJob<V>,
    V
  > boolean jobInFailList(String queueName, int jobID, Class<T> type) throws Exception {
    log.trace("#jobInFailList(queueName={}, jobID={})", queueName, jobID);

    var jobs = getFailedJobs(queueName, type).getFailedJobs();

    for (var job : jobs) {
      if (job.getJobID() == jobID) {
        return true;
      }
    }

    return false;
  }

  protected void deleteJobFailure(String queueName, int failID) throws Exception {
    log.trace("#deleteJobFailure(queueName={}, failID={})", queueName, failID);

    HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().
        uri(URL.failedIDEndpoint(queueName, failID)).
        DELETE().
        build(),
      HttpResponse.BodyHandlers.discarding()
    );
  }

  protected int submitNewJob(String queueName, CreateRequest<?> req) throws Exception {
    log.trace("#submitNewJob(queueName={}, req={})", queueName, req);

    var sendBody = JSON.stringify(req);

    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder()
        .uri(submissionURL())
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

    var body = JSON.parse(res.body(), JobCreateResponse.class);

    return body.getId();
  }

  protected void deleteJob(String queueName, int jobID) throws Exception {
    log.trace("#deleteJob(queueName={}, jobID={})", queueName, jobID);
    HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.queueJobEndpoint(queueName, jobID)).DELETE().build(),
      HttpResponse.BodyHandlers.discarding()
    );
  }
}
