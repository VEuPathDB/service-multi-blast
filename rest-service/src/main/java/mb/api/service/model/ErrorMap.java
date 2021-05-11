package mb.api.service.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mb.lib.blast.model.ToolOption;

/**
 * ErrorMap is a convenience implementation of the map structure used by
 * {@code UnprocessableEntityException}s.
 */
public final class ErrorMap extends HashMap<String, List<String>>
{
  /**
   * Constructs an empty {@code ErrorMap}.
   */
  public ErrorMap() {}

  public ErrorMap(int size) {
    super(size);
  }

  /**
   * Constructs an {@code ErrorMap} containing the given key mapped to a list
   * containing the given value.
   *
   * @param key   Error key.
   * @param value Error value.
   */
  public ErrorMap(String key, String value) {
    putError(key, value);
  }

  /**
   * Constructs an {@code ErrorMap} containing the given key mapped to a list
   * containing the given value.
   *
   * @param key   Error key.
   * @param value Error value.
   */
  public ErrorMap(ToolOption key, String value) {
    putError(key, value);
  }

  public ErrorMap putError(ToolOption key, String error) {
    putError(key.toString(), error);
    return this;
  }

  public ErrorMap putError(String key, String error) {
    computeIfAbsent(key, this::newList).add(error);
    return this;
  }

  public ErrorMap putError(String prefix, String key, String error) {
    computeIfAbsent(prefix + "." + key, this::newList).add(error);
    return this;
  }

  public ErrorMap putError(String prefix, String key, List<String> errors) {
    computeIfAbsent(prefix + "." + key, this::newList).addAll(errors);
    return this;
  }

  private List<String> newList(String ignored) {
    return new ArrayList<>();
  }
}
