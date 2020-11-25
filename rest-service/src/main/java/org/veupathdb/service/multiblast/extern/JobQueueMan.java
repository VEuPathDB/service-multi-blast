package org.veupathdb.service.multiblast.extern;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.Config;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.model.internal.JobStatus;
import org.veupathdb.service.multiblast.util.Address;
import org.veupathdb.service.multiblast.util.Format;

public class JobQueueMan
{
  public static final String
    JobEndpoint      = "job/%s",
    QueueEndpoint    = "queue",
    QueueIdEndpoint  = QueueEndpoint + "/%s",
    QueueJobEndpoint = QueueIdEndpoint + "/job/%d";
  private static final Logger log = LogProvider.logger(JobQueueMan.class);
  private static JobQueueMan instance;

  private JobQueueMan() {
  }

  public static JobQueueMan getInstance() {
    if (instance == null)
      return instance = new JobQueueMan();

    return instance;
  }

  public static JobStatus jobStatus(int queueId) throws Exception {
    return getInstance().getJobStatus(queueId);
  }

  public JobStatus getJobStatus(int queueId) throws Exception {
    log.trace("#getJobStatus(int)");

    var con = Config.getInstance();
    var uri = URI.create(Address.http(Config.getInstance().getQueueHost())).
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
      throw new RuntimeException("Queue server returned an unexpected response.");
    }

    var body = Format.Json.readValue(res.body(), JobStatusResponse.class);

    return body.getStatus();
  }

  public static int submitJob(Job job) throws Exception {
    return getInstance().submitNewJob(job);
  }

  public int submitNewJob(Job job) throws Exception {
    log.trace("#submitJob(Job)");

    var uri = URI.create(Address.http(Config.getInstance().getQueueHost()))
      .resolve(String.format(JobEndpoint, Config.getInstance().getQueueName()));

    log.debug("Attempting to queue job {} at {}", job.getJobId(), uri);
    var res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder()
        .uri(uri)
        .POST(HttpRequest.BodyPublishers.ofString(
          Format.Json.writeValueAsString(new JobCreateRequest(job))
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

    var body = Format.Json.readValue(res.body(), JobCreateResponse.class);
    log.debug("Job {} successfully queued", job.getJobId());

    return body.getId();
  }
}
