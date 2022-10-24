package mblast.migration.model

data class Target(val name: String, val file: String) : Comparable<Target> {
  override fun compareTo(other: Target): Int {
    val n = name.compareTo(other.name)
    when {
      n < 0 -> return -1
      n > 0 -> return 1
    }

    val f = file.compareTo(other.file)
    when {
      f < 0 -> return -1
      f > 0 -> return 1
    }

    return 0
  }
}
