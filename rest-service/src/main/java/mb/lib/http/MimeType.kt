package mb.lib.http

interface MimeType
{
  companion object {
    const val ApplicationBinary = "application/octet-stream"
    const val ApplicationJSON   = "application/json"
    const val ApplicationXML    = "application/xml"
    const val ApplicationZip    = "application/zip"
    const val TextPlain         = "text/plain"
  }
}
