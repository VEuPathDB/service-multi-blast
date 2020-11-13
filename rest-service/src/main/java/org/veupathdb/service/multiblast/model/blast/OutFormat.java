package org.veupathdb.service.multiblast.model.blast;

import java.util.*;

import org.veupathdb.service.multiblast.model.io.JsonKeys;

public class OutFormat
{
  private static final String DELIM_PREFIX = "delim=";

  private ReportFormatType format;

  private Character delim;

  private final List<BlastReportField> fields;

  public OutFormat() {
    fields = new ArrayList<>();
  }

  public OutFormat(ReportFormatType format, Character delim, List<BlastReportField> fields) {
    this.format = format;
    this.delim  = delim;
    this.fields = fields;
  }

  public ReportFormatType getFormat() {
    return format;
  }

  public boolean hasFormat() {
    return format != null;
  }

  public OutFormat setFormat(ReportFormatType format) {
    this.format = format;
    return this;
  }

  public Character getDelim() {
    return delim;
  }

  public boolean hasDelim() {
    return delim != null;
  }

  public OutFormat setDelim(Character delim) {
    this.delim = delim;
    return this;
  }

  public List<BlastReportField> getFields() {
    return fields;
  }

  public boolean hasFields() {
    return !fields.isEmpty();
  }

  public OutFormat setFields(List<BlastReportField> fields) {
    this.fields.clear();
    this.fields.addAll(fields);
    return this;
  }

  public static OutFormat fromString(String value) {
    throw new RuntimeException("lol");
  }

  @Override
  public String toString() {
    var out = new StringBuilder();
    out.append(format);

    if (delim != null)
      out.append(' ').append(DELIM_PREFIX).append(delim);
    for (var f : fields)
      out.append(' ').append(f);

    return out.toString();
  }

  public boolean isCustomizableFormat() {
    return switch (format) {
      case TABULAR, TABULAR_WITH_COMMENTS, CSV, SAM -> true;
      default -> false;
    };
  }

  public boolean allowsDelimiters() {
    return switch (format) {
      case TABULAR, TABULAR_WITH_COMMENTS, CSV -> true;
      default -> false;
    };
  }

  private String jsonKey(String field) {
    return JsonKeys.OUT_FMT + "." + field;
  }

}
