package org.veupathdb.service.multiblast.service.cli;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.model.blast.ToolOption;

public class CliBuilder
{
  private static final Logger log = LogManager.getLogger(CliBuilder.class);

  private static final Pattern SingleQuotes = Pattern.compile("('+)");

  private final Map<ToolOption, Object[]> params;

  public CliBuilder() {
    log.trace("CliBuilder::new()");
    this.params = new LinkedHashMap<>();
  }

  public CliBuilder set(ToolOption key, String... values) {
    log.trace("CliBuilder#set(key={}, values={})", key, values);
    return setRaw(key, values);
  }

  public CliBuilder set(ToolOption key, Integer... values) {
    log.trace("CliBuilder#set(key={}, values={})", key, values);
    return setRaw(key, values);
  }

  public CliBuilder set(ToolOption key, Double... values) {
    log.trace("CliBuilder#set(key={}, values={})", key, values);
    return setRaw(key, values);
  }

  public CliBuilder set(ToolOption key, Long... values) {
    log.trace("CliBuilder#set(key={}, values={})", key, values);
    return setRaw(key, values);
  }

  public CliBuilder setRaw(ToolOption key, Object[] values) {
    log.trace("CliBuilder#setRaw(key={}, values={})", key, values);

    params.put(key, values);

    return this;
  }

  public CliBuilder append(ToolOption key, Object... values) {
    log.trace("CliBuilder#append(key={}, values={})", key, values);

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
  public CliBuilder setNonNull(ToolOption key, Object... values) {
    log.trace("CliBuilder#setNonNull(key={}, values={})", key, values);

    if (hasValues(values))
      params.put(key, values);

    return this;
  }

  @SuppressWarnings("UnusedReturnValue")
  public CliBuilder appendNonNull(ToolOption key, Object... values) {
    log.trace("CliBuilder#appendNonNull(key={}, values={})", key, values);

    if (hasValues(values))
      return this.append(key, values);

    return this;
  }

  @Override
  public String toString() {
    log.trace("CliBuilder#toString()");

    var out = new StringBuilder();

    toString(out);

    log.debug("CliBuilder output: {}", out::toString);
    return out.toString();
  }

  public void toString(StringBuilder out) {
    log.trace("CliBuilder#toString(out={})", out);

    var it = toComponentStream().iterator();

    if (!it.hasNext())
      return;

    var arr = it.next();
    out.append(arr[0]);
    if (arr[1] != null)
      out.append("='").append(escape(arr[1])).append('\'');

    while (it.hasNext()) {
      arr = it.next();
      out.append(' ').append(arr[0]);
      if (arr[1] != null)
        out.append("='").append(escape(arr[1])).append('\'');
    }
  }

  public String[][] toArgPairs() {
    log.trace("CliBuilder#toArgPairs()");

    return toComponentStream().toArray(String[][]::new);
  }

  public String[] toArgArray(boolean quoted) {
    log.trace("CliBuilder#toArgArray(quoted={})", quoted);

    return toJoinedStream(quoted).toArray(String[]::new);
  }

  public Stream<String[]> toComponentStream() {
    log.trace("CliBuilder#toComponentStream()");

    return params.entrySet()
        .stream()
        .map(e -> new String[]{
          e.getKey().getFlag(),
          e.getValue() == null || e.getValue().length == 0
            ? null
            : Arrays.stream(e.getValue())
              .map(Object::toString)
              .collect(Collectors.joining(","))
        });
  }

  public Stream<String> toJoinedStream(boolean quoted) {
    log.trace("CliBuilder#toJoinedStream(quoted={})", quoted);

    return quoted
      ? toComponentStream().map(e -> e[0] + (e[1] == null ? "" : "='" + escape(e[1]) + '\''))
      : toComponentStream().map(e -> e[0] + (e[1] == null ? "" : "=" + escape(e[1])));
  }

  public static String escape(String in) {
    log.trace("CliBuilder::escape(in=\"{}\")", in);

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
    log.trace("CliBuilder::joinArgs(values={})", values);

    if (values == null || values.length == 0)
      return "";

    if (values.length == 1 && values[0] == null)
      return "";

    var out     = new StringBuilder();
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

  protected static boolean hasValues(Object[] values) {
    log.trace("CliBuilder::hasValues(values={})", values);

    if (values == null || values.length == 0)
      return false;

    for (var value : values) {
      if (value != null)
        return true;
    }

    return false;
  }
}
