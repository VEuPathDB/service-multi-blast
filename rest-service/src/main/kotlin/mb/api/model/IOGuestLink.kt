package mb.api.model

import com.fasterxml.jackson.annotation.JsonProperty

data class IOGuestLink(@JsonProperty("guestID") var guestID: Long)