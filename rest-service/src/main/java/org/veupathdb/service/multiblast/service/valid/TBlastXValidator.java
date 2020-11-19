package org.veupathdb.service.multiblast.service.valid;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.InputTBlastxConfig;
import org.veupathdb.service.multiblast.model.ErrorMap;

class TBlastXValidator implements ConfigValidator<InputTBlastxConfig>
{
  private static TBlastXValidator instance;

  private final Logger log = LogProvider.logger(getClass());

  private TBlastXValidator() {
    log.trace("#new()");
  }

  public static TBlastXValidator getInstance() {
    if (instance == null) return instance = new TBlastXValidator();
    return instance;
  }

  @Override
  public ErrorMap validate(InputTBlastxConfig conf) {
    log.trace("#validate(InputTBlastxConfig)");

    var err = new ErrorMap();

    return err;
  }
}
