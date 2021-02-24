package org.veupathdb.service.multiblast.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import mb.lib.config.Config;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.health.ServiceDependency;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;

public class QueueDep extends ServiceDependency
{
  private static final Logger log = LogProvider.logger(QueueDep.class);

  public static final String Name = "Job Queue";

  public QueueDep(Config conf) {
    super(Name, conf.getQueueHost(), 80);
  }

  @Override
  protected TestResult serviceTest() {
    try {
      var res = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(2))
        .build()
        .send(
          HttpRequest.newBuilder().uri(URI.create(Address.http(getUrl()))).GET().build(),
          HttpResponse.BodyHandlers.discarding()
        );

      return new TestResult(this, true, Status.ONLINE);
    } catch (InterruptedException | IOException e) {
      log.warn("Dependency test failed", e);
      return new TestResult(this, true, Status.UNKNOWN);
    }
  }
}
