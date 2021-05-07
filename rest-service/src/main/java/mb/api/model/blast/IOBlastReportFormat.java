package mb.api.model.blast;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.blast.impl.IOBlastReportFormatImpl;
import mb.api.model.io.JsonKeys;
import org.veupathdb.lib.blast.field.FormatField;
import org.veupathdb.lib.blast.field.OutFormat;

@JsonDeserialize(as = IOBlastReportFormatImpl.class)
public interface IOBlastReportFormat {
  @JsonProperty(JsonKeys.Format)
  IOBlastFormat getFormat();

  @JsonProperty(JsonKeys.Format)
  void setFormat(IOBlastFormat format);

  @JsonProperty(JsonKeys.Delimiter)
  String getDelim();

  @JsonProperty(JsonKeys.Delimiter)
  void setDelim(String delim);

  @JsonProperty(JsonKeys.Fields)
  List<FormatField> getFields();

  @JsonProperty(JsonKeys.Fields)
  void setFields(List<FormatField> fields);

  default OutFormat toInternalValue() {
    return new OutFormat().
      setType(getFormat().toInternalValue()).
      setDelimiter(getDelim()).
      setFields(getFields() == null ? new ArrayList<>() : new ArrayList<>(getFields()));
  }

  static IOBlastReportFormat fromInternalValue(OutFormat fmt) {
    var out = new IOBlastReportFormatImpl();

    out.setFormat(IOBlastFormat.fromInternalValue(fmt.getType()));
    out.setDelim(fmt.getDelimiter());
    out.setFields(fmt.getFields() == null ? new ArrayList<>() : new ArrayList<>(fmt.getFields()));

    return out;
  }
}
