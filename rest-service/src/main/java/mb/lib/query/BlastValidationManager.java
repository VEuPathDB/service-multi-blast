package mb.lib.query;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

  public static Optional<ErrorMap> Validate(BlastConfig conf)
  throws Exception {
    Log.trace("::Validate(conf={})", conf);

    var payload = JSON.stringify(conf);
    Log.debug("Sending config to blast validator: {}", payload);

    var res = HttpClient.newHttpClient()
      .send(
        HttpRequest.newBuilder()
          .POST(HttpRequest.BodyPublishers.ofString(payload))
          .uri(URI.create(Address.http(Conf.getValidatorHost())))
          .build(),
        HttpResponse.BodyHandlers.ofString()
      );

    if (res.statusCode() != 200)
      throw new RuntimeException("Unexpected response from validation server: " + res.body());

    var parsed = new HashMap<String, List<String>>();
    JSON.parse(res.body(), parsed);

    if (parsed.isEmpty())
      return Optional.empty();

    var converted = new ErrorMap(parsed.size());

    for (var key : parsed.keySet()) {
      var flag = Field2Flag.Flag2Field.get(key);

      if (flag == null)
        continue;

      converted.put(flag, parsed.get(key));
    }

    return Optional.of(converted);
  }
}
