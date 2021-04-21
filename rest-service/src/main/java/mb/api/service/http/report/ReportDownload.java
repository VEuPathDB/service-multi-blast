package mb.api.service.http.report;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import mb.lib.blast.model.BlastReportType;
import mb.lib.http.Header;
import mb.lib.http.MimeType;

public class ReportDownload implements StreamingOutput
{
  private static final String AttachmentPat = "attachment; filename=\"report.%s\"";

  private final BlastReportType format;
  private final boolean         zipped;
  private final boolean         download;
  private final InputStream     stream;

  public ReportDownload(
    BlastReportType format,
    boolean zipped,
    boolean download,
    InputStream stream
  ) {
    this.format   = format;
    this.zipped   = zipped;
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

  public Response.ResponseBuilder configureResponse(Response.ResponseBuilder b) {
    String ct, ext;

    if (zipped) {
      ct = MimeType.ApplicationZip;
      ext = "zip";
    } else {
      switch (format) {
        case BlastXML, SingleFileBlastXML2, MultipleFileBlastXML2 -> {
          ct  = MimeType.ApplicationXML;
          ext = "xml";
        }
        case Tabular -> {
          ct  = MimeType.TextPlain;
          ext = "tsv";
        }
        case SeqAlignTextASN1, SeqAlignBinaryASN1, BlastArchiveASN1 -> {
          ct  = MimeType.TextPlain;
          ext = "asn";
        }
        case CommaSeparatedValues -> {
          ct  = MimeType.TextPlain;
          ext = "csv";
        }
        case SeqAlignJSON, SingleFileBlastJSON, MultipleFileBlastJSON -> {
          ct  = MimeType.ApplicationJSON;
          ext = "json";
        }
        default -> {
          ct  = MimeType.TextPlain;
          ext = "txt";
        }
      }
    }

    b = b.header(Header.ContentType, ct);

    if (download)
      b = b.header(Header.ContentDisposition, String.format(AttachmentPat, ext));

    return b;
  }
}
