package mb.lib.format;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import mb.lib.config.Config;

public class FormatterManager
{
  private static FormatterManager instance;

  private static final Config conf = Config.getInstance();

  private static final ObjectMapper mapper = new ObjectMapper();

  private final URI uri;

  public FormatterManager() {
    this.uri = URI.create("http://" + conf.getFormatterURI());
  }

  public static InputStream formatAs(FormatType type, String... fields) throws Exception {
    return getInstance().formatReportAs(type, null, fields);
  }

  public static InputStream formatAs(FormatType type, char delim, String... fields)
  throws Exception {
    return getInstance().formatReportAs(type, delim, fields);
  }

  public InputStream formatReportAs(FormatType type, Character delim, String... fields)
  throws Exception {
    if (!type.isDelimiterAllowed() && delim != null)
      throw new Exception(String.format(
        "Attempted to provide a custom delimiter for non-tabular format type %s.",
        type
      ));
    if (!type.isParameterized() && fields != null && fields.length > 0)
      throw new Exception(String.format(
        "Attempted to provide a custom fieldset for non-parameterized format type %s",
        type
      ));

    var res = HttpClient.newHttpClient()
      .send(
        HttpRequest.newBuilder(uri.resolve(String.valueOf(type.id())))
          .PUT(HttpRequest.BodyPublishers
            .ofString(mapper.writeValueAsString(new PutRequest(delim, fields))))
          .header("Content-Type", "application/json")
          .build(),
        HttpResponse.BodyHandlers.ofInputStream()
    );

    if (res.statusCode() != 200) {
      var error = mapper.readValue(res.body(), ErrorResponse.class);

      throw new Exception(String.format(
        "Report format request failed with code %d.  Message: %s",
        res.statusCode(),
        error.message
      ));
    }

    return res.body();
  }

  public static FormatterManager getInstance() {
    if (instance == null)
      return instance = new FormatterManager();

    return instance;
  }
}
