package org.veupathdb.service.multiblast.model.blast;

import java.util.*;

import javax.validation.constraints.NotNull;

import org.veupathdb.service.multiblast.model.CLISerializable;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.Validatable;
import org.veupathdb.service.multiblast.model.io.JsonKeys;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

import static java.util.Collections.singletonList;

public class OutFormat implements Validatable, CLISerializable
{
  private static final String
    DELIM_PREFIX = "delim=",
    DEFAULT_FIELDS = "std";

  private static final String
    ERR_FIELDS_WRONG_FORMAT = "the selected " + JsonKeys.FORMAT + " does not allow defining custom"
      + " report fields.",
    ERR_DELIM_FORBIDDEN = "the selected " + JsonKeys.FORMAT + " does not allow custom delimiters.";

  private ReportFormatType       format;
  private Character              delim;
  private final List<BlastReportField> fields;

  public OutFormat() {
    fields = new ArrayList<>();
  }

  public OutFormat(
    ReportFormatType format,
    Character delim,
    @NotNull List<BlastReportField> fields
  ) {
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

  @NotNull
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

  @NotNull
  public OutFormat setDelim(Character delim) {
    this.delim = delim;
    return this;
  }

  @NotNull
  public List<BlastReportField> getFields() {
    return fields;
  }

  public boolean hasFields() {
    return !fields.isEmpty();
  }

  @NotNull
  public OutFormat setFields(@NotNull List<BlastReportField> fields) {
    this.fields.clear();
    this.fields.addAll(fields);
    return this;
  }

  @NotNull
  public ErrorMap validate() {
    var errors = new ErrorMap();

    if (format != null && !fields.isEmpty() && !isCustomizableFormat())
      errors.put(jsonKey(JsonKeys.FORMAT), singletonList(ERR_FIELDS_WRONG_FORMAT));
    if (delim != null && !allowsDelimiters())
      errors.put(jsonKey(JsonKeys.DELIMITER), singletonList(ERR_DELIM_FORBIDDEN));

    return errors;
  }

  public void toArgs(CliBuilder args) {
//    var hasFmt = format != null;
//    var hasDel = delim != null;
//    var hasFel = !fields.isEmpty();
//
//    if (!hasFmt && !hasDel && !hasFel)
//      return;
//
//    args.setNonNull(ToolOption.OutputFormat, format)
//      .setNonNull(ToolOption);
//
//    if (hasDel)
//      args.append(DELIM_PREFIX).append(delim).append(' ');
//    if (hasFel)
//      for (var field : fields)
//        args.append(field.getValue()).append(' ');
//    else
//      args.append(DEFAULT_FIELDS).append(' ');
  }

  private boolean isCustomizableFormat() {
    return switch (format) {
      case TABULAR, TABULAR_WITH_COMMENTS, CSV, SAM -> true;
      default -> false;
    };
  }

  private boolean allowsDelimiters() {
    return switch (format) {
      case TABULAR, TABULAR_WITH_COMMENTS, CSV -> true;
      default -> false;
    };
  }

  private String jsonKey(String field) {
    return JsonKeys.OUT_FMT + "." + field;
  }
}
