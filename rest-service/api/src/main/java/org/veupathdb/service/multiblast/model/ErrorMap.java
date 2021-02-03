package org.veupathdb.service.multiblast.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.veupathdb.service.multiblast.model.blast.ToolOption;

public final class ErrorMap extends HashMap<String, List<String>>
{
  public ErrorMap() {}

  public ErrorMap(String key, String value) {
    putError(key, value);
  }

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
