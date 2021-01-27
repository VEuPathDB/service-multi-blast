package mb.lib.format;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import mb.lib.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FormatterManager
{
  private static final Logger log = LogManager.getLogger(FormatterManager.class);

  private static final Config conf = Config.getInstance();

  private static final ObjectMapper mapper = new ObjectMapper();

  private static final String translateEndpoint = "/translate";

  private static FormatterManager instance;

  private final String baseURI;

  public FormatterManager() {
    this.baseURI = "http://" + conf.getFormatterURI();
  }

  public static InputStream formatAs(
    String jobID,
    FormatType type,
    boolean zip,
    String... fields
  ) throws Exception {
    return getInstance().formatReportAs(jobID, type, null, zip, fields);
  }

  public InputStream formatReportAs(
    String jobID,
    FormatType type,
    Character delim,
    boolean zip,
    String... fields
  ) throws Exception {
    log.trace("FormatterManager#formatReportAs(String, FormatType, Character, boolean, String...)");

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

    var outBody = mapper.writeValueAsString(new PostRequest(delim, fields));
    var address = baseURI + (translateEndpoint + "/" + jobID + "/" + type.id());

    if (!zip)
      address += "?zip=false";

    log.debug("Sending request to formatter " + address + ": " + outBody);

    var res = HttpClient.newHttpClient()
      .send(
        HttpRequest.newBuilder(URI.create(address))
          .POST(HttpRequest.BodyPublishers
            .ofString(outBody))
          .header("Content-Type", "application/json")
          .build(),
        HttpResponse.BodyHandlers.ofInputStream()
    );

    if (res.statusCode() != 200) {
      var tmp = new String(res.body().readAllBytes(), StandardCharsets.UTF_8);
      log.debug("Formatter returned non-OK staus code " + res.statusCode() + " with body " + tmp);

      var error = mapper.readValue(tmp, ErrorResponse.class);

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
