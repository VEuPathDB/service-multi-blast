package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import javax.validation.constraints.NotNull;

import org.veupathdb.service.multiblast.model.CLISerializable;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.Validatable;

public class BlastConfig implements CLISerializable, Validatable
{

  private File query;
  private QueryLocation queryLoc;

  @Override
  public void toArgs(@NotNull StringBuilder args) {
    args.append(OptionName.QUERY).append('=').append(query.getAbsolutePath()).append(' ');

    if (queryLoc != null)
      args.append(OptionName.QUERY_LOCATION).append('=').append(queryLoc.toString()).append(' ');

  }

  @Override
  public @NotNull ErrorMap validate() {
    var out = new ErrorMap();


    return out;
  }

}
