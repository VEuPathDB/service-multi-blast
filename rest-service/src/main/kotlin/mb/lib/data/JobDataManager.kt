package mb.lib.data

import mb.lib.config.Config
import mb.lib.model.HashID
import org.apache.logging.log4j.LogManager
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.time.OffsetDateTime

object JobDataManager
{
  private val Log     = LogManager.getLogger(JobDataManager::class.java)
  private val JobRoot = Path.of(Config.jobMountPath)

  fun createQueryFile(jobID: HashID, query: String) {
    val queryFile = Path.of(Config.jobMountPath, jobID.string, "query.txt").toFile()

    queryFile.createNewFile()
    val writer = FileWriter(queryFile)

    writer.write(query)
    writer.flush()
    writer.close()
  }

  /**
   * Returns whether a DB exists at the target path.
   * <p>
   * Confirms that either a *.pin or *.nin file exists for the given target DB
   * path.
   *
   * @param tgt Target path.
   *
   * @return Whether a DB file exists for the target path.
   */
  fun targetDBExists(tgt: Path): Boolean {
    Log.trace("::targetDBExists(tgt={})", tgt)

    if (!tgt.parent.toFile().exists())
      return false

    val base = tgt.toString()

    // Check for n-index
    if (File("$base.nin").exists())
      return true

    // Check for p-index
    return File("$base.pin").exists()
  }

  /**
   * Creates a workspace for the given job ID.
   *
   * @param jobID ID of the job for which a workspace should be created.
   *
   * @return The path to the newly created workspace.
   */
  fun createJobWorkspace(jobID: HashID): JobWorkspace {
    Log.trace("::createJobWorkspace(jobID={})", jobID)
    return JobWorkspace(jobID, Files.createDirectories(Path.of(Config.jobMountPath, jobID.string)))
  }

  /**
   * Returns a list of paths that were last modified before the given timestamp.
   *
   * @param time Timestamp threshold.
   *
   * @return A list of paths that were last modified before the given timestamp.
   */
  fun getPathsModifiedBefore(time: OffsetDateTime): List<Path> {
    // 50 paths ~= 10 jobs
    val out  = ArrayList<Path>(50)
    val root = Path.of(Config.jobMountPath)


    Files.walkFileTree(root, object : SimpleFileVisitor<Path>()
    {
      // Used to keep the directory entries _after_ the file entries per
      // directory.
      val dirs = HashSet<Path>()

      override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
        if (attrs.lastModifiedTime().toInstant().isBefore(time.toInstant()))
          out.add(file)
        return FileVisitResult.CONTINUE
      }

      override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
        if (attrs.lastModifiedTime().toInstant().isBefore(time.toInstant()))
          if (dir != root)
            dirs.add(dir)
        return FileVisitResult.CONTINUE
      }

      @Override
      override fun postVisitDirectory(dir: Path, exc: IOException): FileVisitResult {
        if (dirs.contains(dir))
          out.add(dir)
        return FileVisitResult.CONTINUE
      }
    })

    return out
  }

  fun jobFilePath(file: String) = JobRoot.resolve(file)

  fun jobFileExists(file: String) = jobFilePath(file).toFile().exists()

  fun workspacePath(jobID: HashID) = jobFilePath(jobID.string)

  fun workspaceExists(jobID: HashID) = jobFileExists(jobID.string)

  fun jobWorkspace(jobID: HashID) = JobWorkspace(jobID, workspacePath(jobID))

  /**
   * Wipe out a job directory and it's contents.
   *
   * @param jobID ID of the job whose directory should be erased.
   */
  fun deleteWorkspace(jobID: HashID) {
    Log.trace("::deleteJobData(jobID={})", jobID)

    val path = Path.of(Config.jobMountPath, jobID.string)

    // If the path doesn't exist, nothing to do.
    if (!path.toFile().exists())
      return

    Files.walk(path)
      .sorted(Comparator.reverseOrder())
      .map(Path::toFile)
      .forEach(File::delete)
  }
}
