package mb.api.service.http.job

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.InternalServerErrorException
import mb.api.model.IOJobTarget
import mb.api.model.IOJsonJobRequest
import mb.api.model.blast.IOBlastConfig
import mb.lib.config.Config
import mb.lib.path.findDBPath
import mb.lib.query.model.BlastTargetLink
import mb.lib.util.then
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException
import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.math.min

fun makeDBPaths(site: String, targets: Collection<IOJobTarget>) =
  StringBuilder().apply { targets.forEach { appendDBPath(this, site, it.organism, it.target) } }.toString()

fun makeDBPaths(site: String, targets: List<BlastTargetLink>) =
  StringBuilder().apply { targets.forEach { appendDBPath(this, site, it.organism, it.targetFile) } }.toString()

internal fun makeOrthoDBPath(site: String) =
  Path(Config.dbMountPath, site, "build-${Config.orthoBuild}", "diamond", Config.orthoDbName)
    .takeIf { it.exists() }
    ?: throw InternalServerErrorException("Target site $site does not have a queryable DIAMOND database.")

@Suppress("NOTHING_TO_INLINE")
private inline fun appendDBPath(builder: StringBuilder, site: String, organism: String, target: String) {
  val path = findDBPath(site, organism, target)
    .orElseThrow { BadRequestException("Query target: (${organism}, ${target}) is invalid or is no longer available.") }

  if (builder.isNotEmpty())
    builder.append(' ')

  builder.append(path)
}

fun verifyQuery(req: Any?) = nullCheck(req, "query cannot be null")

fun verifyBody(req: Any?) = nullCheck(req, "request body cannot be null")

fun verifyProps(req: Any?) = nullCheck(req, "job properties cannot be null")

fun verifyConfig(req: Any?) = nullCheck(req, "blast config cannot be null")

fun nullCheck(req: Any?, msg: String) {
  if (req == null)
    throw BadRequestException(msg)
}

/**
 * Verifies that the given request + query set is not attempting to create a
 * larger result set than the client specified max result limit.
 * <p>
 * If the client did not specify a max result limit, this method does nothing.
 *
 * @throws UnprocessableEntityException if the request configuration could
 * create a result set larger than the client specified limit.
 */
fun verifyResultLimit(req: IOJsonJobRequest, config: IOBlastConfig, numQueries: Int) =
  ResultLimitValidator.validateResultLimits(min(req.maxResults ?: Config.maxResults, Config.maxResults), numQueries + 1, config)
    ?.then { throw UnprocessableEntityException(it); }
