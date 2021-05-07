package mb.lib.query.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import mb.lib.model.HashID;
import mb.lib.queue.consts.JsonKeys;
import mb.lib.queue.model.FailedJob;
import mb.lib.queue.model.JobResult;
import mb.lib.util.BlastConv;
import mb.lib.util.JSON;
import org.veupathdb.lib.blast.BlastTool;

public class FailedQueryJob extends FailedJob<BlastRequest>
{
  /**
   * Constructs a {@code FailedQueryJob} instance from the given JSON node.
   * <p>
   * We aren't making use of Jackson's reflective unpacking here for
   * compatibility reasons.  Older jobs from the initial beta release store the
   * job CLI config as an array of arguments rather than a structured object.
   * This constructor can unpack either form.
   * @param raw
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

    var config = (ObjectNode) raw.get(JsonKeys.Payload);

    var out = new BlastRequest(
      new HashID(config.get(mb.api.model.io.JsonKeys.JobID).asText()),
      BlastTool.fromString(config.get(mb.api.model.io.JsonKeys.Tool).asText()),
      BlastConv.convertJobConfig(config.get("config"))
    );

    setPayload(out);
  }
}
