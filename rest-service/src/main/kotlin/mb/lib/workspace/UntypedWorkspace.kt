package mb.lib.workspace

import org.veupathdb.lib.hash_id.HashID
import java.io.File
import java.time.OffsetDateTime
import java.util.stream.Stream

internal class UntypedWorkspace(jobID: HashID, directory: File) : MBlastWorkspace, AbstractWorkspace(directory) {
  override val jobID = jobID

  override val hasQuery: Boolean
    get() = TODO("Not yet implemented")

  override val queryFile: File
    get() = TODO("Not yet implemented")

  override val hasResult: Boolean
    get() = TODO("Not yet implemented")

  override val resultFile: File
    get() = TODO("Not yet implemented")

  override val hasReports: Boolean
    get() = TODO("Not yet implemented")

  override val reports: List<HashID>
    get() = TODO("Not yet implemented")

  override val hasErrorFile: Boolean
    get() = TODO("Not yet implemented")

  override val errorFile: File
    get() = TODO("Not yet implemented")

  override val hasLogFile: Boolean
    get() = TODO("Not yet implemented")

  override val logFile: File
    get() = TODO("Not yet implemented")

  override fun createQueryFile(query: String): File {
    TODO("Not yet implemented")
  }

  override fun reportWorkspace(reportID: HashID): ReportWorkspace {
    TODO("Not yet implemented")
  }
}
