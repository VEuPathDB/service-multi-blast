package org.veupathdb.service.multiblast.model.internal;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.BlastTool;
import org.veupathdb.service.multiblast.model.blast.impl.*;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;
import org.veupathdb.service.multiblast.util.Format;

public class Job
{
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
    var cli = new CliBuilder(tool);

    config.toCli(cli);

    return cli.toString();
  }

  public String toSerial() {
    var cli = new CliBuilder(tool);

    config.toCli(cli);

    return cli.toComponentStream().collect(
      Format.Json::createArrayNode,
      (a, v) -> {
        if (v[1] == null)
          a.add(Format.Json.createArrayNode().add(v[0]));
        else
          a.add(Format.Json.createArrayNode().add(v[0]).add(v[1]));
      },
      ArrayNode::addAll
    ).toString();
  }

  // TODO: this is a bad place for this
  public static Job fromSerial(String cli) throws Exception {
    var node = (ArrayNode) Format.Json.readTree(cli);
    var tool = BlastTool.fromString(node.get(0).asText()).orElseThrow();
    var out  = new Job(tool);

    switch (tool) {
      case BlastN -> out.setJobConfig(BlastNConfigImpl.fromSerial(node));
      case BlastP -> out.setJobConfig(BlastPConfigImpl.fromSerial(node));
      case BlastX -> out.setJobConfig(BlastXConfigImpl.fromSerial(node));
      case TBlastN -> out.setJobConfig(TBlastNConfigImpl.fromSerial(node));
      case TBlastX -> out.setJobConfig(TBlastXConfigImpl.fromSerial(node));
      case PSIBLAST, RPSBLAST, RPSTBLASTN -> throw new IllegalStateException(
        "The psiblast, rpsblast, and rpstblastn tools are not currently supported."
      );
    }

    return out;
  }
}
