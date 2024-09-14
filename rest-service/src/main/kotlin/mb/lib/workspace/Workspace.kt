package mb.lib.workspace

import java.io.File
import java.time.OffsetDateTime
import java.util.stream.Stream

internal const val diamondFlagFile  = ".diamond"
internal const val completeFlagFile = ".complete"
internal const val failedFlagFile   = ".failed"

sealed interface Workspace {

  /**
   * Whether this workspace exists in the filesystem.
   */
  val exists: Boolean

  /**
   * Whether this workspace directory has no contents.
   */
  val isEmpty: Boolean

  /**
   * Handle on the workspace directory.
   */
  val directory: File

  /**
   * Tests whether this workspace contains a file or directory with the given
   * name.
   *
   * @param fileName Name of the file to test for.
   *
   * @return `true` if a file or directory with the given name exists within
   * this workspace, otherwise `false`.
   */
  fun contains(fileName: String): Boolean

  /**
   * Creates a new [Stream] over the contents of this workspace.
   *
   * @return A stream over the contents of this workspace.
   */
  fun contents(): Stream<File>

  /**
   * Creates a new file with the given [name] in this workspace and writes the
   * given [contents] to that file.
   *
   * This method throws if the file could not be created, or if the file already
   * existed.
   *
   * @param name Name of the file to create.
   *
   * @param contents Contents to write to the file.
   */
  fun createFile(name: String, contents: String): File

  /**
   * Creates this workspace directory if it does not already exist.
   *
   * @return `true` if the workspace directory was created, `false` if it
   * already existed.
   */
  fun createIfNotExists(): Boolean

  /**
   * Deletes this workspace and all its contents.
   */
  fun delete()

  /**
   * Returns a handle on a [File] in this workspace with the given name.
   *
   * The file handle returned by this method may not point to a file that
   * actually exists in the filesystem.
   *
   * @return A file handle to a file with the given [name] in this workspace.
   */
  fun getFile(name: String): File

  /**
   * Updates the last modified timestamp on this workspace directory to the time
   * provided.
   *
   * @param time New timestamp for this workspace directories last modified
   * time.
   */
  fun updateLastModified(time: OffsetDateTime)

  /**
   * Tests whether the workspace contains a success flag.
   *
   * @return `true` if the workspace contains a success flag, otherwise `false`.
   */
  fun hasSuccessFlag(): Boolean

  /**
   * Tests whether the workspace contains a failed flag.
   *
   * @return `true` if the workspace contains a failed flag, otherwise `false`.
   */
  fun hasFailedFlag(): Boolean

  /**
   * Tests whether the workspace contains either a success or failed flag.
   *
   * @return `true` if the workspace contains a completion flag of either
   * success or failure, otherwise `false`.
   */
  fun hasCompletionFlag(): Boolean
}
