package mb.lib.extern;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import mb.lib.extern.consts.Format;
import mb.lib.extern.consts.URL;
import mb.lib.extern.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract class QueueManager
{
  private static final Logger log = LogManager.getLogger(QueueManager.class);

  public abstract List<FailedJob> getFailedJobs() throws Exception;

  protected List<FailedJob> getFailedJobs(String queueName) throws Exception {
    log.trace("#getFailedJobs(queueName={})", queueName);

    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.failedEndpoint(queueName)).GET().build(),
      HttpResponse.BodyHandlers.ofInputStream()
    );

    return Format.JSON.readValue(res.body(), FailedJobResponse.class).getFailedJobs();
  }

  public abstract QueueJobStatus getJobStatus(int jobID) throws Exception;

  protected QueueJobStatus getJobStatus(String queueName, int jobID) throws Exception {
    log.trace("#getJobStatus(queueName={}, jobID={})", queueName, jobID);

    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.queueJobEndpoint(queueName, jobID)).GET().build(),
      HttpResponse.BodyHandlers.ofString()
    );

    if (res.statusCode() == 404) {
      if (jobInFailList(jobID)) {
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

    var body = Util.JSON.readValue(res.body(), JobStatusResponse.class);

    return body.getStatus();
  }

  public abstract boolean jobInFailList(int jobID) throws Exception;

  protected boolean jobInFailList(String queueName, int jobID) throws Exception {
    log.trace("#jobInFailList(queueName={}, jobID={})", queueName, jobID);

    var jobs = getFailedJobs(queueName);

    for (var job : jobs) {
      if (job.getJobID() == jobID) {
        return true;
      }
    }

    return false;
  }

  public abstract void deleteJobFailure(FailedJob job) throws Exception;

  protected void deleteJobFailure(String queueName, FailedJob job) throws Exception {
    log.trace("#deleteJobFailure(queueName={}, job={})", queueName, job);

    HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().
        uri(URL.failedIDEndpoint(queueName, job.getFailID())).
        DELETE().
        build(),
      HttpResponse.BodyHandlers.discarding()
    );
  }

  protected int submitNewJob(String queueName, CreateRequest<?> req) throws Exception {
    log.trace("#submitNewJob(queueName={}, req={})", queueName, req);

    var uri = URL.jobEndpoint();

    var sendBody = Util.JSON.writeValueAsString(req);

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

    var body = Util.JSON.readValue(res.body(), JobCreateResponse.class);

    return body.getId();
  }

  public abstract void deleteJob(int jobID) throws Exception;

  protected void deleteJob(String queueName, int jobID) throws Exception {
    log.trace("#deleteJob(queueName={}, jobID={})", queueName, jobID);
    HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.queueJobEndpoint(queueName, jobID)).DELETE().build(),
      HttpResponse.BodyHandlers.discarding()
    );
  }
}
