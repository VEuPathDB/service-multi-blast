package org.veupathdb.service.multiblast.model.mapping;

import java.util.Optional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public interface EnumMapping<I, V>
{
  @NotNull
  Optional<V> getValue(I id);

  @NotNull
  Optional<I> getId(V value);

  @NotNull
  V requireValue(@NotNull I id);

  @NotNull
  I requireId(@NotNull V value);

  V getNullableValue(I id);

  I getNullableId(V value);

  @NotNull
  EnumMapping<I, V> put(@NotNull I id, @NotNull V value);

  int size();
}
