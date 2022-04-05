package mb.lib.workspace

import java.io.File
import java.time.OffsetDateTime
import java.util.*
import java.util.stream.Stream

internal sealed class AbstractWorkspace(final override val directory: File)
  : Workspace
{

  override val exists  get() = directory.exists()
  override val isEmpty get() = directory.list()?.isEmpty() ?: true

  init {
    if (directory.exists() && !directory.isDirectory)
      throw IllegalStateException()
  }

  override fun contains(fileName: String) = File(directory, fileName).exists()

  override fun contents(): Stream<File> = Arrays.stream(directory.listFiles())

  override fun createFile(name: String, contents: String): File {
    val file = File(directory, name)

    if (file.exists())
      throw IllegalStateException("Cannot create file $file, path already exists.")

    file.createNewFile() ||
      throw IllegalStateException("Failed to create file $file.")

    file.writeText(contents)

    return file
  }

  override fun createIfNotExists(): Boolean {
    if (directory.exists())
      return false

    directory.mkdir() ||
      throw IllegalStateException("Failed to create workspace $directory.")

    return true
  }

  override fun delete() {
    val dirs = LinkedList<File>()
    val dels = LinkedList<File>()

    dirs.add(directory)

    while (dirs.isNotEmpty()) {
      val current = dirs.pop()
      dels.push(current)

      for (file in current.listFiles()!!) {
        if (file.isDirectory)
          dirs.push(file)
        else
          file.delete() ||
            throw IllegalStateException("Failed to delete $file.")
      }
    }

    while (dels.isNotEmpty()) {
      dels.pop().delete()
    }
  }

  override fun getFile(name: String) = File(directory, name)

  override fun updateLastModified(time: OffsetDateTime) {
    directory.setLastModified(time.toInstant().toEpochMilli()) ||
      throw IllegalStateException("Failed to update workspace last modified date for $directory")
  }

  override fun hasSuccessFlag(): Boolean = File(directory, completeFlagFile).exists()

  override fun hasFailedFlag(): Boolean = File(directory, failedFlagFile).exists()

  override fun hasCompletionFlag() = hasSuccessFlag() || hasFailedFlag()
}
