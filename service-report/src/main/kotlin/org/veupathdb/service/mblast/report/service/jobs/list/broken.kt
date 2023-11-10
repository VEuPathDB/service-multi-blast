package org.veupathdb.service.mblast.report.service.jobs.list

import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.compute.platform.job.AsyncJob
import org.veupathdb.service.mblast.report.generated.model.BrokenJobListEntry
import org.veupathdb.service.mblast.report.generated.model.BrokenJobListEntryImpl

internal fun ListBrokenJobs(): List<BrokenJobListEntry> =
  AsyncPlatform.getOwnedBrokenJobs().map { it.toBrokenJobListEntry() }

private fun AsyncJob.toBrokenJobListEntry(): BrokenJobListEntry =
  BrokenJobListEntryImpl().also {
    it.reportJobID = jobID.string
    it.createdOn   = created
    it.failedOn    = finished
    it.rawConfig   = config
  }
