package mb.api.service.http.report

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.StreamingOutput
import mb.api.service.util.RangedStream
import mb.lib.http.Header
import mb.lib.http.MimeType
import java.io.OutputStream

internal data class ReportDownload(
  private val fileName: String,
  private val download: Boolean,
  private val stream:   RangedStream,
): StreamingOutput {

  companion object {
    private const val AttachmentPat = "attachment; filename=\"%s\""
  }

  override fun write(output: OutputStream) {
    stream.write(output)
  }

  fun toResponse(): Response {
    return configureResponse(Response.status(200)).entity(this).build()
  }

  private fun configureResponse(b: Response.ResponseBuilder): Response.ResponseBuilder {
    b.header(Header.ContentType, when {
      fileName.endsWith(".xml")  -> MimeType.ApplicationXML
      fileName.endsWith(".json") -> MimeType.ApplicationJSON
      fileName.endsWith(".zip")  -> MimeType.ApplicationZip
      else                       -> MimeType.TextPlain
    })

    if (download)
      b.header(Header.ContentDisposition, String.format(AttachmentPat, fileName))

    return b
  }
}
