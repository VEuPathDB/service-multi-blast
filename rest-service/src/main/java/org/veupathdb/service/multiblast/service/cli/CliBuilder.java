package org.veupathdb.service.multiblast.service.cli;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.veupathdb.service.multiblast.model.blast.ToolOption;

public class CliBuilder
{
  private static final Pattern SingleQuotes = Pattern.compile("('+)");

  private final Map<ToolOption, Object[]> params;

  public CliBuilder() {
    params = new LinkedHashMap<>();
  }

  public CliBuilder set(ToolOption key, Object... values) {
    params.put(key, values);
    return this;
  }

  public CliBuilder append(ToolOption key, Object... values) {
    var tmp = params.get(key);

    if (tmp != null) {
      var t2 = new Object[tmp.length + values.length];
      System.arraycopy(tmp, 0, t2, 0, tmp.length);
      System.arraycopy(values, 0, t2, tmp.length, values.length);
      params.put(key, t2);
    } else {
      params.put(key, values);
    }

    return this;
  }

  @SuppressWarnings("UnusedReturnValue")
  public CliBuilder setNonNull(ToolOption key, Object value) {
    if (value != null)
      params.put(key, new Object[]{value});

    return this;
  }

  @SuppressWarnings("UnusedReturnValue")
  public CliBuilder appendNonNull(ToolOption key, Object value) {
    if (value != null)
      return this.append(key, value);

    return this;
  }

  @Override
  public String toString() {
    return toComponentStream().collect(Collectors.joining(" "));
  }

  public String[] toArgArray() {
    return toComponentStream().toArray(String[]::new);
  }

  public Stream<String> toComponentStream() {
    return params.entrySet()
      .stream()
      .map(e -> e.getKey().getFlag() + '=' + Arrays.stream(e.getValue())
        .map(Object::toString)
        .map(CliBuilder::escape)
        .collect(Collectors.joining(",", "'", "'")));
  }

  public static String escape(String in) {
    if (in == null || in.isBlank())
      return "";

    var out   = new StringBuilder();
    var match = SingleQuotes.matcher(in);
    var last  = 0;

    while (match.find()) {
      out.append(in, last, match.start());
      last = match.end();
      out.append("'\"").append(match.group(1)).append("\"'");
    }
    out.append(in.substring(last));

    return out.toString();
  }

  public static String joinArgs(Object[] values) {
    if (values == null || values.length == 0)
      return "";

    if (values.length == 1 && values[0] == null)
      return "";

    var out = new StringBuilder();
    var started = false;

    for (var i = 0; i < values.length; i++) {
      if (values[i] == null)
        continue;

      if (out.length() == 0)
        out.append("='");

      if (i > 0 && started)
        out.append(',');

      out.append(escape(values[i].toString().trim()));
      started = true;
    }

    if (out.length() > 0)
      out.append('\'');

    return out.toString();
  }
}
