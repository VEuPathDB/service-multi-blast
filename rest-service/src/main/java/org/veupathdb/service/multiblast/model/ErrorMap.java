package org.veupathdb.service.multiblast.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.veupathdb.service.multiblast.model.blast.ToolOption;

public final class ErrorMap extends HashMap<String, List<String>>
{
  public void putError(ToolOption key, String error) {
    putError(key.toString(), error);
  }

  public void putError(String key, String error) {
    computeIfAbsent(key, this::newList).add(error);
  }

  public void putError(String prefix, String key, String error) {
    computeIfAbsent(prefix + "." + key, this::newList).add(error);
  }

  public void putError(String prefix, String key, List<String> errors) {
    computeIfAbsent(prefix + "." + key, this::newList).addAll(errors);
  }

  private List<String> newList(String ignored) {
    return new ArrayList<>();
  }
}
