package mb.api.service.util

import mb.lib.config.Config
import org.veupathdb.lib.container.jaxrs.health.Dependency
import org.veupathdb.lib.container.jaxrs.health.ServiceDependency
import org.veupathdb.lib.container.jaxrs.providers.LogProvider
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration


object QueueDep: ServiceDependency("Job Queue", Config.queueHost, 80)
{
  val log = LogProvider.logger(QueueDep::class.java)

  override fun serviceTest() = try {
    HttpClient.newBuilder()
      .connectTimeout(Duration.ofSeconds(2))
      .build()
      .send(
        HttpRequest.newBuilder().uri(URI.create(Address.http(url))).GET().build(),
        HttpResponse.BodyHandlers.discarding()
      )

    Dependency.TestResult(this, true, Dependency.Status.ONLINE)
  } catch (e: Exception) {
    log.warn("Dependency test failed", e)
    Dependency.TestResult(this, true, Dependency.Status.UNKNOWN)
  }
}
