package org.veupathdb.service.multiblast.service.valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.IOBlastSegMask;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

class SegValidator implements ConfigValidator<IOBlastSegMask>
{
  private static SegValidator instance;

  private final Logger log = LogManager.getLogger(getClass());

  private SegValidator() {
    log.trace("SegValidator#new()");
  }

  public static SegValidator getInstance() {
    if (instance == null) return instance = new SegValidator();
    return instance;
  }

  @Override
  public ErrorMap validate(IOBlastSegMask conf) {
    log.trace("SegValidator#validate(InputBlastSegMask)");

    var err = new ErrorMap();

    if (conf != null) {
      Int.optGtEq(err, conf.getWindow(), 1, JsonKeys.Window);
      Dec.optGtEq(err, conf.getLocut(), 0, JsonKeys.LowCut);
      Dec.optGtEq(err, conf.getHicut(), 0, JsonKeys.HighCut);
    }

    return err;
  }
}
