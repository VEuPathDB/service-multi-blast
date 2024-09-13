package mb.api.service.http.job

import mb.api.model.IOJobPostResponse
import mb.api.model.IOJsonJobRequest
import mb.api.model.blast.IOBlastConfig
import mb.api.model.dmnd.IODiamondConfig
import mb.api.model.dmnd.databaseFile
import mb.api.model.dmnd.toInternal
import mb.api.model.io.JsonKeys
import mb.api.service.model.ErrorMap
import mb.lib.blast.model.BlastQuery
import mb.lib.config.Config
import mb.lib.dmnd.DiamondQuery
import mb.lib.query.BlastManager
import mb.lib.query.model.BlastConfig
import mb.lib.query.model.BlastJob
import mb.lib.query.model.DiamondConfig
import mb.lib.util.convert
import mb.lib.util.toInternal
import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException
import org.veupathdb.lib.container.jaxrs.model.User
import java.io.InputStream
import java.nio.charset.StandardCharsets

object JobService {
  private val Log = LogManager.getLogger(JobService::class.java)

  private const val ErrTooManySeqs = "Too many sequences in input query.  Queries can have at most %d sequences."

  // region NCBI BLAST

  /**
   * Creates a job from the given JSON configuration and user.
   */
  fun createBlastJob(config: IOBlastConfig, req: IOJsonJobRequest, userID: Long): IOJobPostResponse {
    val query = processBlastQuery(
      config.query ?: throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is required.")),
      config,
      req
    )

    config.query = null

    return createBlastJob(query, config, req, userID)
  }

  /**
   * Creates a job from the given JSON configuration, user, and input stream
   * containing query file contents.
   */
  fun createBlastJob(query: InputStream, config: IOBlastConfig, props: IOJsonJobRequest, userID: Long) =
    createBlastJob(processBlastQuery(query.readAllBytes().decodeToString(), config, props), config, props, userID)

  private fun createBlastJob(
    query:   BlastQuery,
    config:  IOBlastConfig,
    request: IOJsonJobRequest,
    userID:  Long,
  ): IOJobPostResponse {
    if (request.targets.isEmpty())
      throw UnprocessableEntityException(ErrorMap("targets", "1 or more targets must be selected."))

    // Sort targets to prevent different hashes with the same targets:
    request.targets = request.targets.sortedBy { it.target }

    val conv = config.toInternal()
    BlastManager.validateConfig(conv)?.also{ e -> throw UnprocessableEntityException(e); }

    val dbPath = makeDBPaths(request.site, request.targets)
    conv.dbFile = dbPath

    val res = BlastManager.submitJob(BlastJob(
      config      = BlastConfig(conv),
      query       = query,
      site        = request.site,
      description = request.description,
      userID      = userID,
      maxDLSize   = request.maxResultSize ?: 0,
      targets     = convert(request.targets),
    ).apply {
      if (request.isPrimary == false)
        isPrimary = false
    })

    return IOJobPostResponse(res.jobID.string)
  }

  private fun processBlastQuery(input: String, config: IOBlastConfig, req: IOJsonJobRequest): BlastQuery {
    if (input.length > Config.maxInputQuerySize)
      throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is too large."))

    val query = BlastQuery.fromString(config.tool, input)

    // Limit input sequence count
    if (query.sequences.size > Config.maxQueries)
      throw UnprocessableEntityException(ErrorMap(
        JsonKeys.Query,
        String.format(ErrTooManySeqs, Config.maxQueries)
      ))

    with(query.validate()) {
      if (this != null)
        throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, message))
    }

    verifyResultLimit(req, query.sequences.size)

    return query
  }

  // endregion NCBI BLAST

  // region DIAMOND

  fun createDiamondJob(config: IODiamondConfig, req: IOJsonJobRequest, userID: Long): IOJobPostResponse {
    val query = processDiamondQuery(
      config.query
        ?: throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is required.")),
      config,
      req
    )

    config.query = null

    return createDiamondJob(query, config, req, userID)
  }

  fun createDiamondJob(query: InputStream, config: IODiamondConfig, req: IOJsonJobRequest, userID: Long) =
    createDiamondJob(processDiamondQuery(query.readAllBytes().decodeToString(), config, req), config, req, userID)

  private fun createDiamondJob(
    query:  DiamondQuery,
    config: IODiamondConfig,
    req:    IOJsonJobRequest,
    userID: Long,
  ): IOJobPostResponse {
    val conv = config.toInternal()
    conv.databaseFile = makeOrthoDBPath(req.site)

    val res = BlastManager.submitJob(BlastJob(
      config      = DiamondConfig(conv),
      query       = query,
      site        = req.site,
      description = req.description,
      userID      = userID,
      maxDLSize   = req.maxResultSize ?: 0,
      targets     = convert(req.targets),
    ).apply {
      if (req.isPrimary == false)
        isPrimary = false
    })

    return IOJobPostResponse(res.jobID.string)
  }

  private fun processDiamondQuery(input: String, config: IODiamondConfig, req: IOJsonJobRequest): DiamondQuery {
    if (input.length > Config.maxInputQuerySize)
      throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is too large."))

    val query = BlastQuery.fromString(req.config.tool, input)

    // Limit input sequence count
    if (query.sequences.size > Config.maxQueries)
      throw UnprocessableEntityException(ErrorMap(
        JsonKeys.Query,
        String.format(ErrTooManySeqs, Config.maxQueries)
      ))

    with(query.validate()) {
      if (this != null)
        throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, message))
    }

    verifyResultLimit(req, query.sequences.size)

    return query
  }

  // endregion DIAMOND
}
