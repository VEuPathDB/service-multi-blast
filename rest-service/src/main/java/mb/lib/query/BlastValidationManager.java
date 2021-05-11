package mb.lib.query;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import mb.api.service.model.ErrorMap;
import mb.api.service.util.Address;
import mb.lib.config.Config;
import mb.lib.util.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.blast.BlastConfig;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;

class BlastValidationManager
{
  private static final Logger Log  = LogManager.getLogger(BlastValidationManager.class);
  private static final Config Conf = Config.getInstance();
  private static final String Path = "validate";

  public static Optional<ErrorMap> Validate(BlastConfig conf)
  throws Exception {
    Log.trace("::Validate(conf={})", conf);

    var payload = JSON.stringify(conf);
    var uri     = Address.http(String.join("/", Conf.getValidatorHost(), Path, conf.getTool().getValue()));
    Log.debug("Sending config to {}: {}", uri, payload);

    var res = HttpClient.newHttpClient()
      .send(
        HttpRequest.newBuilder()
          .POST(HttpRequest.BodyPublishers.ofString(payload))
          .uri(URI.create(uri))
          .build(),
        HttpResponse.BodyHandlers.ofString()
      );

    if (res.statusCode() != 200)
      throw new RuntimeException("Unexpected response from validation server: " + res.body());

    var parsed = JSON.parse(res.body(), ValidationResponse.class);

    Log.debug("JSON Reply: {}", res.body());
    if (parsed.payload.isEmpty())
      return Optional.empty();

    var converted = new ErrorMap(parsed.payload.size());

    for (var key : parsed.payload.keySet()) {
      var flag = Field2Flag.Flag2Field.get(key);

      if (flag == null)
        continue;

      converted.put(flag, parsed.payload.get(key));
    }

    return Optional.of(converted);
  }
}

class ValidationResponse {
  final int status;
  final Map<String, List<String>> payload;

  @JsonCreator
  public ValidationResponse(
    @JsonProperty("status")  int                       status,
    @JsonProperty("payload") Map<String, List<String>> payload
  ) {
    this.status  = status;
    this.payload = payload == null ? Collections.emptyMap() : payload;
  }
}
