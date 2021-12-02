package mb.lib.db.model

@Deprecated("")
enum class DBJobStatus(val value: String) {
  Completed("completed"), Running("running"), Errored("errored"), Queued("queued");

  override fun toString(): String {
    return name
  }

  companion object {
    fun fromString(value: String): DBJobStatus {
      for (v in values()) if (v.value == value) return v
      throw IllegalArgumentException(String.format("Invalid JobStatus value \"%s\"", value))
    }
  }
}