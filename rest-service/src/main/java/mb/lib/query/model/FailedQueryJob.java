package mb.lib.query.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import mb.lib.model.EmptyBlastQueryConfig;
import mb.lib.model.HashID;
import mb.lib.queue.consts.Format;
import mb.lib.queue.consts.JsonKeys;
import mb.lib.queue.model.FailedJob;
import mb.lib.queue.model.JobResult;
import mb.lib.util.BlastConv;
import mb.lib.util.JSON;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.consts.Key;

public class FailedQueryJob extends FailedJob<BlastRequest>
{
  private static final String ConfKey = "config";

  /**
   * Constructs a {@code FailedQueryJob} instance from the given JSON node.
   * <p>
   * We aren't making use of Jackson's reflective unpacking here for
   * compatibility reasons.  Older jobs from the initial beta release store the
   * job CLI config as an array of arguments rather than a structured object.
   * This constructor can unpack either form.
   *
   * @param raw Raw response object from Fireworq.
   */
  @JsonCreator
  public FailedQueryJob(ObjectNode raw) {
    setFailID(raw.get(JsonKeys.ID).asInt());
    setJobID(raw.get(JsonKeys.JobID).asInt());
    setCategory(raw.get(JsonKeys.Category).asText());
    setUrl(raw.get(JsonKeys.URL).asText());
    setResult(JSON.cast(raw.get(JsonKeys.Result), JobResult.class));
    setFailCount(raw.get(JsonKeys.FailCount).asInt());
    setFailedAt(JSON.cast(raw.get(JsonKeys.FailedAt), OffsetDateTime.class));
    setCreatedAt(JSON.cast(raw.get(JsonKeys.CreatedAt), OffsetDateTime.class));

    var config = raw.get(JsonKeys.Payload);

    BlastRequest out;

    if (config.isObject()) {
      var conf = config.path(ConfKey).has(Key.Tool)
        ? BlastConv.convertJobConfig(config.get(ConfKey))
        : new EmptyBlastQueryConfig(config.get(ConfKey).isObject() ? (ObjectNode) config.get(ConfKey) : JSON.object());

      out = new BlastRequest(
        new HashID(config.get(mb.api.model.io.JsonKeys.JobID).asText()),
        BlastTool.fromString(config.get(mb.api.model.io.JsonKeys.Tool).asText()),
        conf
      );
    } else if (config.isArray()) {
      // Split up the URL to get at the tool name and the job ID.
      // (versions <= v0.9.1 did not pass the job ID or tool in the payload)
      //
      // v <= 0.9.1 url format: http://{blast-service-name}/{blast-tool}/{job-id}
      var urlComponents = getUrl().split("/");

      // Expand array to key value pairs
      var parsedPayload = Format.JSON.createArrayNode();

      // Append the tool to the array first (the standard format for <= 0.9.1
      // payloads puts the tool in the array's [0] position.
      parsedPayload.add(Format.JSON.createArrayNode().add(urlComponents[urlComponents.length-2]));

      // Split each cli arg into a key/value pair and append them to the parsed
      // payload array.
      config.forEach(flag -> {
        var tmp   = Format.JSON.createArrayNode();
        var split = flag.textValue().split("=", 2);

        tmp.add(split[0]);

        if (split.length > 1)
          tmp.add(split[1]);

        parsedPayload.add(tmp);
      });

      out = new BlastRequest(
        new HashID(urlComponents[urlComponents.length-1]),
        BlastTool.fromString(urlComponents[urlComponents.length-2]),
        BlastConv.convertJobConfig(parsedPayload)
      );
    } else
      throw new RuntimeException("Unexpected payload format, must be one of object or array");

    setPayload(out);
  }
}
