package org.veupathdb.service.multiblast.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ErrorMap extends HashMap<String, List<String>>
{
  public void putError(String key, String error) {
    computeIfAbsent(key, this::newList).add(error);
  }

  private List<String> newList(String ignored) {
    return new ArrayList<>();
  }
}
