package mb.lib.extern.consts;

import java.net.URI;

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

  public static URI jobEndpoint() {
    return URI.create(prependHTTP(conf.getQueueHost()))
      .resolve(String.format(JobEndpoint, conf.getJobCategory()));
  }

  public static URI queueEndpoint() {
    return URI.create(prependHTTP(conf.getQueueHost())).resolve(QueueEndpoint);
  }

  public static URI queueIDEndpoint() {
    return URI.create(prependHTTP(conf.getQueueHost()))
      .resolve(String.format(QueueIdEndpoint, conf.getQueueName()));
  }

  public static URI queueJobEndpoint(int jobID) {
    return URI.create(prependHTTP(conf.getQueueHost()))
      .resolve(String.format(QueueJobEndpoint, conf.getQueueName(), jobID));
  }

  public static URI failedEndpoint() {
    return URI.create(prependHTTP(conf.getQueueHost()))
      .resolve(String.format(FailedEndpoint, conf.getQueueName()));
  }

  public static URI failedIDEndpoint(int failID) {
    return URI.create(prependHTTP(conf.getQueueHost()))
      .resolve(String.format(FailedIDEndpoint, conf.getQueueName(), failID));
  }

  public static String prependHTTP(String uri) {
    if (uri.startsWith("http://") || uri.startsWith("https://"))
      return uri;

    return "http://" + uri;
  }
}
