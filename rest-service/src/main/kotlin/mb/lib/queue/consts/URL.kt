package mb.lib.queue.consts

object URL
{
  @Suppress("HttpUrlsUsage")
  fun prependHTTP(uri: String): String {
    if (uri.startsWith("http://") || uri.startsWith("https://"))
      return uri

    return "http://$uri"
  }
}
