package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithTask <T, E extends Enum<E>> extends CliOptions
{
  E getTask();
  T setTask(E task);
}
