package org.veupathdb.service.multiblast.model;

import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public interface CLISerializable
{
  void toArgs(CliBuilder args);
}
