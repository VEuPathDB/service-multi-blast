package mb.lib.queue.model

import com.fasterxml.jackson.annotation.JsonCreator
import java.util.*

enum class QueueJobStatus {
  Completed, Errored, Queued, InProgress;

  override fun toString() = name

  companion object {
    @JsonCreator
    fun fromString(value: String): QueueJobStatus {
      val status = value.lowercase(Locale.getDefault())
      return when {
        "completed" == status                      -> Completed
        status.contains("fail")              -> Errored
        "grabbed" == status || "claimed" == status -> InProgress
        "waiting" == status                        -> Queued
        else                                       -> throw IllegalArgumentException("Invalid JobStatus value \"$value\".")
      }
    }
  }
}