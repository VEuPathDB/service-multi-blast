package mb.api.model.internal;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.databind.node.ArrayNode;
import mb.lib.blast.model.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import mb.lib.blast.model.BlastConfig;
import mb.lib.blast.model.BlastTool;
import mb.api.service.cli.CliBuilder;
import mb.api.service.util.Format;

public class Job
{
  private static final Logger log = LogManager.getLogger(Job.class);

  private final BlastTool tool;

  private OffsetDateTime createdOn;

  private BlastConfig<?> config;

  public Job(BlastTool tool) {
    this.tool = tool;
  }

  public OffsetDateTime createdOn() {
    return createdOn;
  }

  public Job setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  public boolean hasConfig() {
    return config != null;
  }

  public BlastConfig<?> getJobConfig() {
    return config;
  }

  public Job setJobConfig(BlastConfig<?> config) {
    this.config = config;
    return this;
  }

  public BlastTool getTool() {
    return tool;
  }

  @Override
  public String toString() {
    var str = new StringBuilder(tool.value()).append(' ');
    var cli = new CliBuilder();

    config.toCli(cli);

    cli.toString(str);
    return str.toString();
  }

  public String toSerial() {
    log.trace("Job#toSerial()");
    var cli = new CliBuilder();

    config.toCli(cli);

    var out = Format.Json.createArrayNode();
    out.add(Format.Json.createArrayNode().add(tool.value()));

    return cli.toComponentStream().collect(
      () -> out,
      (a, v) -> {
        if (v[1] == null)
          a.add(Format.Json.createArrayNode().add(v[0]));
        else
          a.add(Format.Json.createArrayNode().add(v[0]).add(v[1]));
      },
      (a, b) -> {}
    ).toString();
  }

  // TODO: this is a bad place for this
  public static Job fromSerial(String cli) throws Exception {
    log.trace("Job#fromSerial(String)");
    log.debug("Deserializing from {}", cli);
    var node = (ArrayNode) Format.Json.readTree(cli);
    var tool = BlastTool.fromString(node.get(0).get(0).asText())
      .orElseThrow(() -> new IllegalStateException("Unrecognized blast tool in serialized job: " + node.get(0).get(0).asText()));
    var out  = new Job(tool);

    switch (tool) {
      case BlastN -> out.setJobConfig(BlastNConfigImpl.fromSerial(node));
      case BlastP -> out.setJobConfig(BlastPConfigImpl.fromSerial(node));
      case BlastX -> out.setJobConfig(BlastXConfigImpl.fromSerial(node));
      case TBlastN -> out.setJobConfig(TBlastNConfigImpl.fromSerial(node));
      case TBlastX -> out.setJobConfig(TBlastXConfigImpl.fromSerial(node));
      case PSIBlast, RPSBlast, RPSTBlastN -> throw new IllegalStateException(
        "The psiblast, rpsblast, and rpstblastn tools are not currently supported."
      );
    }

    return out;
  }
}
