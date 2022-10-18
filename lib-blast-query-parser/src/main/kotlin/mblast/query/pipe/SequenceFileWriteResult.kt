package mblast.query.pipe

import java.io.File

data class SequenceFileWriteResult(
  val overallQueryFile: File,
  val subQueryFiles: List<File>,
) {
  val isSingleQuery
    get() = subQueryFiles.isEmpty()

  fun deleteAll() {
    overallQueryFile.delete()
    subQueryFiles.forEach { it.delete() }
  }
}
