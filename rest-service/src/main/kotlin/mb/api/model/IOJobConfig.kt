package mb.api.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = IOJobConfigDeserializer::class)
interface IOJobConfig
