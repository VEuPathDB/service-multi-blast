package mb.lib.report.model;

import com.fasterxml.jackson.annotation.JsonProperty

data class ReportMeta(@JsonProperty("files") val files: List<String>)
