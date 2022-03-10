package mb.lib.report.model

import mb.lib.util.jsonStringify
import mb.lib.util.md5
import org.veupathdb.lib.blast.BlastFormatter
import org.veupathdb.lib.hash_id.HashID

data class ReportJob(
  val jobID: HashID,
  val userID: Long,
  val config: BlastFormatter,
  val description: String?,
) {
  private var repID: HashID? = null

  fun getReportID(): HashID {
    if (repID == null)
      repID = generateReportID()

    return repID!!
  }

  private fun generateReportID(): HashID {
    try {
      return HashID(md5(HashWrapper(jobID, config).jsonStringify()))
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }

  private data class HashWrapper(val jobID: HashID, val config: BlastFormatter)
}
