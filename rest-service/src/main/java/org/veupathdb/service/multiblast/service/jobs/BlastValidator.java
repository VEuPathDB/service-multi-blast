package org.veupathdb.service.multiblast.service.jobs;

import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.BlastnConfig;
import org.veupathdb.service.multiblast.model.blast.OptionName;

public class BlastValidator
{
  private static final String
    ErrNoQuery = "is required.";

  private static BlastValidator instance;

  public ErrorMap validateConfig(BlastConfig config) {
    var out = new ErrorMap();

    if (config.getQuery() == null)
      out.putError(OptionName.QUERY, ErrNoQuery);

    return out;
  }

  public static BlastValidator getInstance() {
    if (instance == null)
      instance = new BlastValidator();

    return instance;
  }

  public static ErrorMap validate(BlastConfig config) {
    return getInstance().validateConfig(config);
  }
}
