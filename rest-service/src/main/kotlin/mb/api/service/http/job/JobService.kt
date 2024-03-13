package mb.api.service.http.job

import mb.api.model.IOJobPostResponse
import mb.api.model.IOJsonJobRequest
import mb.api.model.io.JsonKeys
import mb.api.service.model.ErrorMap
import mb.lib.blast.model.BlastQuery
import mb.lib.config.Config
import mb.lib.query.BlastManager
import mb.lib.query.model.BlastJob
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

  /**
   * Creates a job from the given JSON configuration and user.
   */
  fun createJob(input: IOJsonJobRequest, userID: Long): IOJobPostResponse {
    val query = processQuery(
      input.config.query
        ?: throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is required.")),
      input
    )

    input.config.query = null

    return createJob(input, userID, query)
  }

  /**
   * Creates a job from the given JSON configuration, user, and input stream
   * containing query file contents.
   */
  fun createJob(query: InputStream, props: IOJsonJobRequest, user: User) =
    query.use {
      Log.trace("#createJob(query={}, props={}, user={})", query, props, user.userId)

      createJob(
        props,
        user.userId,
        processQuery(String(query.readAllBytes(), StandardCharsets.UTF_8), props)
      )
    }

  private fun createJob(
    request: IOJsonJobRequest,
    userID:  Long,
    query:   BlastQuery,
  ): IOJobPostResponse {
    if (request.targets.isEmpty())
      throw UnprocessableEntityException(ErrorMap(
        "targets",
        "1 or more targets must be selected."
      ))

    // Sort targets to prevent different hashes with the same targets:
    request.targets = request.targets.sortedBy { it.target }

    val conv = request.config.toInternal()
    BlastManager.validateConfig(conv)?.also{ e -> throw UnprocessableEntityException(e); }

    val dbPath = makeDBPaths(request.site, request.targets)
    conv.dbFile = dbPath

    val res = BlastManager.submitJob(BlastJob(
      config      = conv,
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

  private fun processQuery(input: String, req: IOJsonJobRequest): BlastQuery {
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
}
