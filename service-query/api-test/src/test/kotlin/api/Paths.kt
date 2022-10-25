package api

import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.isDirectory

object Paths {
  const val Query = "/query"

  const val QueryJobs = "/jobs"
  const val QueryStatuses = "$Query/statuses"

  fun JobByID(jobID: String) = "/jobs/$jobID"
  fun JobQueryByID(jobID: String) = "/jobs/$jobID/query"
  fun QueryJobResultByID(jobID: String) = "/jobs/$jobID/result"

  @JvmStatic
  fun main(args: Array<String>) {
    Files.list(Path.of("blastdb"))
      .filter { it.isDirectory() }
      .flatMap { Files.list(it) }
      .filter { it.isDirectory() }
      .flatMap { Files.list(it) }
      .filter { it.isDirectory() }
      .map { it.resolve("blast") }
      .flatMap { Files.list(it) }
      .map { it.toString() }
      .map { it.substring(0, it.length - 4) }
      .sorted()
      .distinct()
      .map { it.split('/') }
      .forEach { println(it) }

  }
}