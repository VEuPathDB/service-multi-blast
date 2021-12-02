package mb.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.io.JsonKeys

@JsonInclude(NON_NULL)
data class IOJobPostResponse(@JsonProperty(JsonKeys.JobID) val jobId: String?)