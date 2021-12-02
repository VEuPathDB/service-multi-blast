package mb.lib.queue.consts

import mb.api.service.util.Address
import mb.lib.config.Config
import java.net.URI

object URL
{
  private const val JobEndpoint      = "/job/%s"
  private const val QueueEndpoint    = "/queue"
  private const val QueueIdEndpoint  = "$QueueEndpoint/%s"
  private const val QueueJobEndpoint = "$QueueIdEndpoint/job/%d"
  private const val FailedEndpoint   = "$QueueIdEndpoint/failed"
  private const val FailedIDEndpoint = "$QueueIdEndpoint/failed/%d"

  fun jobSubmissionEndpoint(category: String): URI {
    return URI.create(Address.http(Config.queueHost))
      .resolve(String.format(JobEndpoint, category))
  }

  fun queueJobEndpoint(queueName: String, jobID: Int): URI {
    return URI.create(Address.http(Config.queueHost))
      .resolve(String.format(QueueJobEndpoint, queueName, jobID))
  }

  fun failedEndpoint(queueName: String): URI {
    return URI.create(Address.http(Config.queueHost))
      .resolve(String.format(FailedEndpoint, queueName))
  }

  fun failedIDEndpoint(queueName: String, failID: Int): URI {
    return URI.create(Address.http(Config.queueHost))
      .resolve(String.format(FailedIDEndpoint, queueName, failID))
  }

  @Suppress("HttpUrlsUsage")
  fun prependHTTP(uri: String): String {
    if (uri.startsWith("http://") || uri.startsWith("https://"))
      return uri

    return "http://$uri"
  }
}
