package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithTask <T, E extends Enum<E>> extends CliOptions
{
  E getTask();
  T setTask(E task);
}
