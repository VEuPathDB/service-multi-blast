package org.veupathdb.service.multiblast.model;

import javax.validation.constraints.NotNull;

public interface CLISerializable
{
  void toArgs(@NotNull StringBuilder args);
}
