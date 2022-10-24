package mblast.migration.util

import mblast.migration.db.ResultSetIterator
import mblast.migration.db.model.MultiBlastJobToTargetsRow
import mblast.migration.db.model.MultiBlastJobsRow

class JobTargetMergingIterator(
  val jobStream: ResultSetIterator<MultiBlastJobsRow>,
  val tgtStream: ResultSetIterator<MultiBlastJobToTargetsRow>
) : Iterator<Pair<MultiBlastJobsRow, List<MultiBlastJobToTargetsRow>>> {
  private var started = false

  override fun hasNext() = jobStream.hasNext()

  override fun next(): Pair<MultiBlastJobsRow, List<MultiBlastJobToTargetsRow>> {
    val job  = jobStream.next()
    val tgts = ArrayList<MultiBlastJobToTargetsRow>(32)
    if (!started) {
      started = true
    } else {
      tgts.add(tgtStream.current())
    }

    while (tgtStream.hasNext()) {
      val tgt = tgtStream.next()

      if (tgt.jobDigest != job.jobDigest)
        break

      tgts.add(tgt)
    }

    return job to tgts
  }
}
