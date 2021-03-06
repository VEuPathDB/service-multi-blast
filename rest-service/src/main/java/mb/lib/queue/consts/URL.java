package mb.lib.queue.consts;

import java.net.URI;

import mb.api.service.util.Address;
import mb.lib.config.Config;

public class URL
{
  private static final Config conf = Config.getInstance();

  private static final String
    JobEndpoint      = "/job/%s",
    QueueEndpoint    = "/queue",
    QueueIdEndpoint  = QueueEndpoint + "/%s",
    QueueJobEndpoint = QueueIdEndpoint + "/job/%d",
    FailedEndpoint   = QueueIdEndpoint + "/failed",
    FailedIDEndpoint = QueueIdEndpoint + "/failed/%d";

  public static URI jobSubmissionEndpoint(String category) {
    return URI.create(Address.http(conf.getQueueHost()))
      .resolve(String.format(JobEndpoint, category));
  }

  public static URI queueJobEndpoint(String queueName, int jobID) {
    return URI.create(Address.http(conf.getQueueHost()))
      .resolve(String.format(QueueJobEndpoint, queueName, jobID));
  }

  public static URI failedEndpoint(String queueName) {
    return URI.create(Address.http(conf.getQueueHost()))
      .resolve(String.format(FailedEndpoint, queueName));
  }

  public static URI failedIDEndpoint(String queueName, int failID) {
    return URI.create(Address.http(conf.getQueueHost()))
      .resolve(String.format(FailedIDEndpoint, queueName, failID));
  }

  public static String prependHTTP(String uri) {
    if (uri.startsWith("http://") || uri.startsWith("https://"))
      return uri;

    return "http://" + uri;
  }
}
