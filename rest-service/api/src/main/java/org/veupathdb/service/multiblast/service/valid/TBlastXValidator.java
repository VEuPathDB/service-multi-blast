package org.veupathdb.service.multiblast.service.valid;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.IOTBlastxConfig;
import org.veupathdb.service.multiblast.model.ErrorMap;

import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

/**
 * Validator for {@code tblastx} CLI arguments.
 */
class TBlastXValidator implements ConfigValidator<IOTBlastxConfig>
{
  private static TBlastXValidator instance;

  private final Logger log = LogProvider.logger(getClass());

  private TBlastXValidator() {
    log.trace("TBlastXValidator#new()");
  }

  public static TBlastXValidator getInstance() {
    if (instance == null) return instance = new TBlastXValidator();
    return instance;
  }

  @Override
  public ErrorMap validate(IOTBlastxConfig conf) {
    log.trace("TBlastXValidator#validate(IOTBlastxConfig)");

    var err = new ErrorMap();

    BlastValidator.validateGenCode(err, conf.getQueryGeneticCode(), QueryGeneticCode);
    BlastValidator.validateGenCode(err, conf.getDbGencode(), DBGeneticCode);
    Int.optGtEq(err, conf.getWordSize(), (byte) 2, WordSize);
    Int.optGtEq(err, conf.getMaxIntronLength(), (byte) 0, MaxIntronLength);
    Dec.optGtEq(err, conf.getThreshold(), 0, Threshold);
    SegValidator.getInstance()
      .validate(conf.getSeg())
      .forEach((k, v) -> err.putError(Seg, k, v));
    validateDbSoftMask(err, conf);
    validateDbHardMask(err, conf);
    validateCullingLimit(err, conf);
    validateBestHitOverhang(err, conf);
    validateBestHitScoreEdge(err, conf);
    Int.optGtEq(err, conf.getWindowSize(), 0, MultiHitWindowSize);

    return err;
  }

  static void validateBestHitScoreEdge(ErrorMap err, IOTBlastxConfig conf) {
    if (conf.getBestHitScoreEdge() != null) {
      Dec.betweenExc(err, conf.getBestHitScoreEdge(), 0, 0.5, BestHitScoreEdge);
      Obj.incompat(err, conf.getCullingLimit(), BestHitScoreEdge, CullingLimit);
    }
  }

  static void validateBestHitOverhang(ErrorMap err, IOTBlastxConfig conf) {
    if (conf.getBestHitOverhang() != null) {
      Dec.betweenExc(err, conf.getBestHitOverhang(), 0, 0.5, BestHitOverhang);
      Obj.incompat(err, conf.getCullingLimit(), BestHitOverhang, CullingLimit);
    }
  }

  static void validateCullingLimit(ErrorMap err, IOTBlastxConfig conf) {
    if (conf.getCullingLimit() != null) {
      Int.gtEq(err, conf.getCullingLimit(), 0, CullingLimit);
      Obj.incompat(err, conf.getBestHitOverhang(), CullingLimit, BestHitOverhang);
      Obj.incompat(err, conf.getBestHitScoreEdge(), CullingLimit, BestHitScoreEdge);
    }
  }

  static void validateDbHardMask(ErrorMap err, IOTBlastxConfig conf) {
    if (conf.getDbHardMask() != null) {
      Obj.incompat(err, conf.getDbSoftMask(), DBHardMask, DBSoftMask);
    }
  }

  static void validateDbSoftMask(ErrorMap err, IOTBlastxConfig conf) {
    if (conf.getDbSoftMask() != null) {
      Obj.incompat(err, conf.getDbHardMask(), DBSoftMask, DBHardMask);
    }
  }
}
