package mb.api.model.blast.impl

import com.fasterxml.jackson.annotation.JsonInclude
import mb.api.model.blast.IOBlastFormat
import mb.api.model.blast.IOBlastReportFormat
import org.veupathdb.lib.blast.field.FormatField

@JsonInclude(JsonInclude.Include.NON_NULL)
data class IOBlastReportFormatImpl(
  override var format: IOBlastFormat? = null,
  override var delim:  String? = null,
  override var fields: List<FormatField>? = null,
) : IOBlastReportFormat