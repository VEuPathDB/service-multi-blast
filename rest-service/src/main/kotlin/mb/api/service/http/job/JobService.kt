package mb.api.service.http.job

import jakarta.ws.rs.InternalServerErrorException
import mb.api.model.IOJobPostResponse
import mb.api.model.IOJsonJobRequest
import mb.api.model.blast.IOBlastConfig
import mb.api.model.dmnd.*
import mb.api.model.io.JsonKeys
import mb.api.service.model.ErrorMap
import mb.lib.blast.model.BlastQuery
import mb.lib.config.Config
import mb.lib.dmnd.DiamondQuery
import mb.lib.dmnd.ProteinSequenceValidationStream
import mb.lib.dmnd.toIOStream
import mb.lib.dmnd.withTempFile
import mb.lib.query.BlastManager
import mb.lib.query.model.BlastConfig
import mb.lib.query.model.MBlastJob
import mb.lib.query.model.DiamondConfig
import mb.lib.util.convert
import mb.lib.util.then
import mb.lib.util.toInternal
import mb.lib.util.withTempFile
import mb.lib.workspace.AbstractWorkspace
import mb.lib.workspace.DiamondWorkspace
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException
import java.io.File
import kotlin.io.path.Path

object JobService {
  private const val ErrTooManySeqs = "Too many sequences in input query.  Queries can have at most %d sequences."

  fun createJob(req: IOJsonJobRequest, userID: Long) =
    when (val config = req.config.typedConfig) {
      is IOBlastConfig   -> createBlastJob(config, req, userID)
      is IODiamondConfig -> createDiamondJob(config, req, userID)
      else               -> throw InternalServerErrorException()
    }

  fun createJob(query: File, req: IOJsonJobRequest, userID: Long) =
    when (val config = req.config.typedConfig) {
      is IOBlastConfig   -> createBlastJob(query, config, req, userID)
      is IODiamondConfig -> createDiamondJob(query, config, req, userID)
      else               -> throw InternalServerErrorException()
    }

  // region NCBI BLAST

  /**
   * Creates a job from the given JSON configuration and user.
   */
  private fun createBlastJob(config: IOBlastConfig, req: IOJsonJobRequest, userID: Long): IOJobPostResponse {
    return withTempFile((config.query ?: throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is required."))).byteInputStream()) {
      config.query = null
      createBlastJob(processBlastQuery(it, config, req), config, req, userID)
    }
  }

  /**
   * Creates a job from the given JSON configuration, user, and input stream
   * containing query file contents.
   */
  private fun createBlastJob(query: File, config: IOBlastConfig, props: IOJsonJobRequest, userID: Long) =
    createBlastJob(processBlastQuery(query, config, props), config, props, userID)

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

  private fun processBlastQuery(input: File, config: IOBlastConfig, req: IOJsonJobRequest): BlastQuery {
    if (input.length() > Config.maxInputQuerySize)
      throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is too large."))

    return BlastQuery.fromString(config.tool, input.readText()).apply {
      if (sequences.size > Config.maxQueries)
        throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, String.format(ErrTooManySeqs, Config.maxQueries)))

      validate()?.then { throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, it.message)) }

      verifyResultLimit(req, config, sequences.size)
    }
  }

  // endregion NCBI BLAST

  // region DIAMOND

  private const val MaxDiamondQuerySize = 31457280L

  private fun createDiamondJob(config: IODiamondConfig, req: IOJsonJobRequest, userID: Long): IOJobPostResponse {
    return withTempFile(
      (config.query ?: throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is required.")))
        .byteInputStream()
        .let { ProteinSequenceValidationStream(MaxDiamondQuerySize, it.toIOStream()) }
    ) {
      config.query = null
      createDiamondJob(DiamondQuery(config.tool, it), config, req, userID)
    }
  }

  private fun createDiamondJob(query: File, config: IODiamondConfig, req: IOJsonJobRequest, userID: Long) =
    query.inputStream().buffered().use {
      withTempFile(ProteinSequenceValidationStream(MaxDiamondQuerySize, it.toIOStream())) {
        query.delete()
        createDiamondJob(DiamondQuery(config.tool, it), config, req, userID)
      }
    }


  private fun createDiamondJob(
    query:  DiamondQuery,
    config: IODiamondConfig,
    req:    IOJsonJobRequest,
    userID: Long,
  ): IOJobPostResponse {
    val conv = config.toInternal()
    conv.databaseFile = makeOrthoDBPath(req.site)
    conv.maxTargetSeqs = 1 // FORCE MAX TARGET SEQUENCES TO 1 FOR PROTEIN MAPPING!
    conv.quiet = true
    conv.outputFile = Path(DiamondWorkspace.ResultFile)
    conv.query = Path(AbstractWorkspace.QueryFile)

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

  // endregion DIAMOND
}
