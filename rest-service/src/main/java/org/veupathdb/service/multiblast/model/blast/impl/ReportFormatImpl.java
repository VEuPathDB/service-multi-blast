package org.veupathdb.service.multiblast.model.blast.impl;

import java.util.ArrayList;

import org.veupathdb.service.multiblast.model.blast.BlastReportField;
import org.veupathdb.service.multiblast.model.blast.BlastReportFormat;
import org.veupathdb.service.multiblast.model.blast.BlastReportType;

public class ReportFormatImpl implements BlastReportFormat
{
  public static final String delimiterPrefix = "@delim=";

  private BlastReportType type;
  private String delimiter;
  private BlastReportField[] fields;

  public ReportFormatImpl() {}

  public ReportFormatImpl(
    BlastReportType type,
    String delimiter,
    BlastReportField... fields
  ) {
    this.type      = type;
    this.delimiter = delimiter;
    if (fields == null || fields.length == 0) {
      this.fields = new BlastReportField[0];
    } else {
      this.fields = new BlastReportField[fields.length];
      System.arraycopy(fields, 0, this.fields, 0, fields.length);
    }
  }

  @Override
  public BlastReportType getType() {
    return type;
  }

  @Override
  public String getDelimiter() {
    return delimiter;
  }

  @Override
  public BlastReportField[] getReportFields() {
    return fields;
  }

  @Override
  public String toString() {
    var out = new StringBuilder();

    out.append(type.getValue());

    if (delimiter != null && !delimiter.isEmpty())
      out.append(' ').append(delimiterPrefix).append(delimiter.charAt(0));

    if (fields == null || fields.length == 0)
      out.append(" std");
    else
      for (var field : fields)
        out.append(' ').append(field);

    return out.toString();
  }

  public static BlastReportFormat fromString(String value) {
    var segments = value.split(" ");
    var doInt   = true;
    var doDelim = true;
    var out = new ReportFormatImpl();
    var fields = new ArrayList<BlastReportField>(segments.length);

    for (var seg : segments) {
      if (doInt) {
        try {
          var tmp = Integer.parseInt(seg);
          out.type = BlastReportType.unsafeFromInt(tmp);
          doInt = false;
          continue;
        } catch (NumberFormatException ignored) {}
      }

      if (doDelim && seg.startsWith(delimiterPrefix)) {
        out.delimiter = seg.substring(delimiterPrefix.length());
        doDelim = false;
        continue;
      }

      fields.add(BlastReportField.fromString(seg));
    }

    out.fields = fields.toArray(BlastReportField[]::new);

    return out;
  }
}
