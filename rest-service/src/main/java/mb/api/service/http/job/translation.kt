package mb.api.service.http.job

import mb.api.model.IOJobLink
import mb.api.model.IOJobTarget
import mb.api.model.IOLongJobResponse
import mb.api.model.IOShortJobResponse
import mb.api.service.util.Format
import mb.lib.query.model.BlastJobLink
import mb.lib.query.model.BlastTargetLink
import mb.lib.query.model.FullUserBlastRow
import mb.lib.util.convert

fun translateLongResponse(row: FullUserBlastRow) = IOLongJobResponse(
  id            = row.jobID.string,
  status        = row.status!!,
  isPrimary     = row.runDirectly,
  site          = row.projectID!!,
  maxResultSize = row.maxDownloadSize,
  description   = row.description,
  created       = row.createdOn?.format(Format.DateFormat),
  parentJobs    = translateParentLinks(row.parentJobs),
  childJobs     = translateChildLinks(row.childJobs),
  targets       = translateTargets(row.targetDBs),
  config        = convert(row.config!!)
)

fun translateShortResponses(rows: Collection<FullUserBlastRow>) = rows.map(::translateShortResponse)

fun translateShortResponse(row: FullUserBlastRow): IOShortJobResponse = IOShortJobResponse(
  id            = row.jobID.string,
  status        = row.status!!,
  isPrimary     = row.runDirectly,
  site          = row.projectID!!,
  maxResultSize = row.maxDownloadSize,
  description   = row.description,
  created       = row.createdOn?.format(Format.DateFormat),
  parentJobs    = translateParentLinks(row.parentJobs),
  childJobs     = translateChildLinks(row.childJobs),
  targets       = translateTargets(row.targetDBs),
)

fun translateTargets(links: List<BlastTargetLink>?) = links?.map(::translateTarget)?.toTypedArray()

fun translateTarget(link: BlastTargetLink): IOJobTarget = IOJobTarget(link.organism, link.targetFile)

fun translateParentLinks(links: List<BlastJobLink>?) = links?.map(::translateParentLink)?.toTypedArray()

fun translateParentLink(link: BlastJobLink): IOJobLink = IOJobLink(link.parentJobID.string, link.position)

fun translateChildLinks(links: List<BlastJobLink>?) = links?.map(::translateChildLink)?.toTypedArray()

fun translateChildLink(link: BlastJobLink): IOJobLink = IOJobLink(link.childJobID.string, link.position)
