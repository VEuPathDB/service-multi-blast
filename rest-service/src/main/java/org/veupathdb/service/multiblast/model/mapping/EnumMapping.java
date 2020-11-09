package org.veupathdb.service.multiblast.model.mapping;

import java.util.Optional;
import java.util.function.BiConsumer;
import javax.validation.constraints.NotNull;

public interface EnumMapping<I, V extends Enum<V>>
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

  @NotNull
  EnumMapping<I, V> putRaw(@NotNull I id, @NotNull String value);

  int size();

  void forEach(@NotNull BiConsumer<I,V> fn);
}
