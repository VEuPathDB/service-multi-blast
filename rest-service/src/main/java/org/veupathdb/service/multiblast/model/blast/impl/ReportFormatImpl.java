package org.veupathdb.service.multiblast.model.blast.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.veupathdb.service.multiblast.model.blast.BlastReportField;
import org.veupathdb.service.multiblast.model.blast.BlastReportFormat;
import org.veupathdb.service.multiblast.model.blast.BlastReportType;

public class ReportFormatImpl implements BlastReportFormat
{
  public static final String delimiterPrefix = "delim=";

  private BlastReportType type;
  private String delimiter;
  private BlastReportField[] fields;

  @Override
  public BlastReportType getType() {
    return type;
  }

  @Override
  public BlastReportFormat setType(BlastReportType type) {
    this.type = type;
    return this;
  }

  @Override
  public String getDelimiter() {
    return delimiter;
  }

  @Override
  public BlastReportFormat setDelimiter(String del) {
    this.delimiter = del;
    return this;
  }

  @Override
  public BlastReportField[] getReportFields() {
    return fields;
  }

  @Override
  public BlastReportFormat setReportFields(BlastReportField[] fields) {
    this.fields = fields;
    return this;
  }

  @Override
  public BlastReportFormat setReportFields(Collection<BlastReportField> fields) {
    this.fields = fields.toArray(BlastReportField[]::new);
    return this;
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
          out.setType(BlastReportType.unsafeFromInt(tmp));
          doInt = false;
          continue;
        } catch (NumberFormatException ignored) {}
      }

      if (doDelim && seg.startsWith(delimiterPrefix)) {
        out.setDelimiter(seg.substring(delimiterPrefix.length()));
        doDelim = false;
        continue;
      }

      fields.add(BlastReportField.fromString(seg));
    }

    return out.setReportFields(fields);
  }
}
