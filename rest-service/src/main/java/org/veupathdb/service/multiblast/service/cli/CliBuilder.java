package org.veupathdb.service.multiblast.service.cli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.veupathdb.service.multiblast.model.blast.ToolOption;

public class CliBuilder
{
  private final Map<ToolOption, Object[]> params;

  public CliBuilder() {
    params = new HashMap<>();
  }

  public CliBuilder set(ToolOption key, Object value) {
    params.put(key, new Object[]{value});
    return this;
  }

  public CliBuilder append(ToolOption key, Object value) {
    var tmp = params.get(key);

    if (tmp != null) {
      var t2 = Arrays.copyOf(tmp, tmp.length + 1);
      t2[tmp.length] = value;
      params.put(key, t2);
    } else {
      params.put(key, new Object[]{value});
    }

    return this;
  }

  public CliBuilder setNonNull(ToolOption key, Object value) {
    if (value != null)
      params.put(key, new Object[]{value});

    return this;
  }

  public CliBuilder appendNonNull(ToolOption key, Object value) {
    if (value != null)
      return this.append(key, value);

    return this;
  }
}
