package mb.api.service.http.job

import jakarta.ws.rs.InternalServerErrorException
import mb.api.model.IOJobPostResponse
import mb.api.model.IOJsonJobRequest
import mb.api.model.blast.IOBlastConfig
import mb.api.model.dmnd.IODiamondConfig
import mb.api.model.dmnd.databaseFile
import mb.api.model.dmnd.maxTargetSeqs
import mb.api.model.dmnd.toInternal
import mb.api.model.io.JsonKeys
import mb.api.service.model.ErrorMap
import mb.lib.blast.model.BlastQuery
import mb.lib.config.Config
import mb.lib.dmnd.DiamondQuery
import mb.lib.query.BlastManager
import mb.lib.query.model.BlastConfig
import mb.lib.query.model.MBlastJob
import mb.lib.query.model.DiamondConfig
import mb.lib.util.convert
import mb.lib.util.then
import mb.lib.util.toInternal
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException
import java.io.InputStream

object JobService {
  private const val ErrTooManySeqs = "Too many sequences in input query.  Queries can have at most %d sequences."

  fun createJob(req: IOJsonJobRequest, userID: Long) =
    when (val config = req.config) {
      is IOBlastConfig   -> createBlastJob(config, req, userID)
      is IODiamondConfig -> createDiamondJob(config, req, userID)
      else               -> throw InternalServerErrorException()
    }

  fun createJob(query: InputStream, req: IOJsonJobRequest, userID: Long) =
    when (val config = req.config) {
      is IOBlastConfig   -> createBlastJob(query, config, req, userID)
      is IODiamondConfig -> createDiamondJob(query, config, req, userID)
      else               -> throw InternalServerErrorException()
    }

  // region NCBI BLAST

  /**
   * Creates a job from the given JSON configuration and user.
   */
  private fun createBlastJob(config: IOBlastConfig, req: IOJsonJobRequest, userID: Long): IOJobPostResponse {
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
  private fun createBlastJob(query: InputStream, config: IOBlastConfig, props: IOJsonJobRequest, userID: Long) =
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

    val res = BlastManager.submitJob(MBlastJob(
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

    return BlastQuery.fromString(config.tool, input).apply {
      if (sequences.size > Config.maxQueries)
        throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, String.format(ErrTooManySeqs, Config.maxQueries)))

      validate()?.then { throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, it.message)) }

      verifyResultLimit(req, config, sequences.size)
    }
  }

  // endregion NCBI BLAST

  // region DIAMOND

  private fun createDiamondJob(config: IODiamondConfig, req: IOJsonJobRequest, userID: Long): IOJobPostResponse {
    val query = processDiamondQuery(
      config.query
        ?: throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is required.")),
      config,
      req
    )

    config.query = null

    return createDiamondJob(query, config, req, userID)
  }

  private fun createDiamondJob(query: InputStream, config: IODiamondConfig, req: IOJsonJobRequest, userID: Long) =
    createDiamondJob(processDiamondQuery(query.readAllBytes().decodeToString(), config, req), config, req, userID)

  private fun createDiamondJob(
    query:  DiamondQuery,
    config: IODiamondConfig,
    req:    IOJsonJobRequest,
    userID: Long,
  ): IOJobPostResponse {
    val conv = config.toInternal()
    conv.databaseFile = makeOrthoDBPath(req.site)
    conv.maxTargetSeqs = 1 // FORCE MAX TARGET SEQUENCES TO 1 FOR PROTEIN MAPPING!

    val res = BlastManager.submitJob(MBlastJob(
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
    // protein mapping queries may be significantly larger than multi-blast
    // queries.
    // if (input.length > Config.maxInputQuerySize)
    //   throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is too large."))

    return DiamondQuery.fromString(config.tool, input).apply {
      // protein mapping queries may have significantly more sequences than
      // multi-blast queries.
      // if (sequences.size > Config.maxQueries)
      //  throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, String.format(ErrTooManySeqs, Config.maxQueries)))

      validate()?.then { throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, it.message)) }

      verifyResultLimit(req, config, sequences.size)
    }
  }

  // endregion DIAMOND
}
