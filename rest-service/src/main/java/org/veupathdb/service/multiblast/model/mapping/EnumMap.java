package org.veupathdb.service.multiblast.model.mapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.constraints.NotNull;

class EnumMap<I, V> implements EnumMapping<I, V>
{
  private final Map<V, I> by_value;
  private final Map<I, V> by_id;

  protected EnumMap(int size) {
    by_value = Collections.synchronizedMap(new HashMap<>(size));
    by_id    = Collections.synchronizedMap(new HashMap<>(size));
  }

  @Override
  public @NotNull Optional<V> getValue(I id) {
    return Optional.ofNullable(by_id.get(id));
  }

  @Override
  public @NotNull Optional<I> getId(V value) {
    return Optional.ofNullable(by_value.get(value));
  }

  @Override
  public @NotNull V requireValue(@NotNull I id) {
    return getValue(id).orElseThrow();
  }

  @Override
  public @NotNull I requireId(@NotNull V value) {
    return getId(value).orElseThrow();
  }

  @Override
  public V getNullableValue(I id) {
    return by_id.get(id);
  }

  @Override
  public I getNullableId(V value) {
    return by_value.get(value);
  }

  @Override
  public @NotNull EnumMapping<I, V> put(@NotNull I id, @NotNull V value) {
    by_id.put(id, value);
    by_value.put(value, id);
    return this;
  }

  @Override
  public int size() {
    return by_id.size();
  }
}
