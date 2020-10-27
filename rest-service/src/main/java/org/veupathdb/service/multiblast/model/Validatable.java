package org.veupathdb.service.multiblast.model;

import javax.validation.constraints.NotNull;

public interface Validatable
{
  @NotNull
  ErrorMap validate();

}
