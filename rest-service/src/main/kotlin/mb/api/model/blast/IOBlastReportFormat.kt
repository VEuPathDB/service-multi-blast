package mb.api.model.blast

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import mb.api.model.blast.IOBlastFormat.Companion.fromInternalValue
import mb.api.model.blast.impl.IOBlastReportFormatImpl
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.blast.field.FormatField
import org.veupathdb.lib.blast.field.OutFormat

@JsonDeserialize(`as` = IOBlastReportFormatImpl::class)
@JsonInclude(NON_DEFAULT)
interface IOBlastReportFormat {
  @get:JsonProperty(JsonKeys.Format)
  @set:JsonProperty(JsonKeys.Format)
  var format: IOBlastFormat?

  @get:JsonProperty(JsonKeys.Delimiter)
  @set:JsonProperty(JsonKeys.Delimiter)
  var delim: String?

  @get:JsonProperty(JsonKeys.Fields)
  @set:JsonProperty(JsonKeys.Fields)
  var fields: List<FormatField>?


  val toInternalValue: OutFormat?
    get() {
      return OutFormat(format!!.internalValue, delim, fields?.toMutableList() ?: mutableListOf())
    }

  companion object {
    fun fromInternalValue(fmt: OutFormat): IOBlastReportFormat {
      val out = IOBlastReportFormatImpl()
      out.format = fromInternalValue(fmt.type)
      out.delim = fmt.delimiter
      out.fields = ArrayList(fmt.fields)
      return out
    }
  }
}