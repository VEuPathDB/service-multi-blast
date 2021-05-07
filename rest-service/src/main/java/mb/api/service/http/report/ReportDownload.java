package mb.api.service.http.report;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import mb.lib.http.Header;
import mb.lib.http.MimeType;
import org.veupathdb.lib.blast.field.FormatType;

public class ReportDownload implements StreamingOutput
{
  private static final String AttachmentPat = "attachment; filename=\"%s\"";

  private final String      fileName;
  private final boolean     download;
  private final InputStream stream;

  public ReportDownload(
    String      fileName,
    boolean     download,
    InputStream stream
  ) {
    this.fileName = fileName;
    this.download = download;
    this.stream   = stream;
  }

  @Override
  public void write(OutputStream output) throws IOException, WebApplicationException {
    try (var b = new BufferedInputStream(stream)) {
      var n = 0;
      var a = new byte[8192];

      while ((n = b.read(a)) > 0)
        output.write(a, 0, n);
    }
  }

  public Response toResponse() {
    return configureResponse(Response.status(200)).entity(this).build();
  }

  private Response.ResponseBuilder configureResponse(Response.ResponseBuilder b) {
    String ct;

    if (fileName.endsWith(".xml")) {
      ct = MimeType.ApplicationXML;
    } else if (fileName.endsWith(".json")) {
      ct = MimeType.ApplicationJSON;
    } else if (fileName.endsWith(".zip")) {
      ct = MimeType.ApplicationZip;
    } else {
      ct = MimeType.TextPlain;
    }

    b = b.header(Header.ContentType, ct);

    if (download)
      b = b.header(Header.ContentDisposition, String.format(AttachmentPat, fileName));

    return b;
  }
}
