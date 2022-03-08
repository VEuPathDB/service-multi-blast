package mb.api.service.http.job

import mb.api.model.IOJobPostResponse
import mb.api.model.IOJsonJobRequest
import mb.api.model.io.JsonKeys
import mb.api.service.model.ErrorMap
import mb.api.service.valid.Sequences
import mb.lib.blast.model.parseAsQuery
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

class JobService {
  companion object {
    private val Log = LogManager.getLogger(JobService::class.java)

    @JvmStatic
    val instance by lazy(::JobService)

    private const val ErrTooManySeqs = "Too many sequences in input query.  Queries can have at most %d sequences."
  }

  fun createJob(input: IOJsonJobRequest, userID: Long): IOJobPostResponse {
    Log.trace("#createJob(input={}, userID={})", input, userID)

    var rawQuery = Sequences.standardize(
      input.config.query
        ?: throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is required."))
    )

    // We are abusing the config query file field, null it out so it doesn't
    // get stored or sent anywhere with the query in this field.
    input.config.query = null

    rawQuery = rawQuery.trim()

    if (rawQuery.length > Config.maxInputQuerySize)
      throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, "Query is too large."))

    val query = rawQuery.parseAsQuery(input.config.tool)

    // Limit input sequence count
    if (query.sequenceCount > Config.maxQueries)
      throw UnprocessableEntityException(ErrorMap(
        JsonKeys.Query,
        String.format(ErrTooManySeqs, Config.maxQueries)
      ))

    with(query.validate()) {
      if (this != null)
        throw UnprocessableEntityException(ErrorMap(JsonKeys.Query, message))
    }

    verifyResultLimit(input, rawQuery.codePoints()
      .filter { c -> c == '>'.code }
      .count().toInt())

    if (input.targets.isEmpty())
      throw UnprocessableEntityException(ErrorMap(
        "targets",
        "1 or more targets must be selected."
      ))

    // Sort targets to prevent different hashes with the same targets:
    input.targets = input.targets.sortedBy { it.target }

    val conv = input.config.toInternal()
    BlastManager.validateConfig(conv)?.also{ e -> throw UnprocessableEntityException(e); }

    val dbPath = makeDBPaths(input.site, input.targets)
    conv.dbFile = dbPath

    val res = BlastManager.submitJob(BlastJob(
      config = conv,
      query = query,
      site = input.site,
      description = input.description,
      userID = userID,
      maxDLSize = input.maxResultSize ?: 0,
      targets = convert(input.targets),
    ).apply {
      if (input.isPrimary == false)
        isPrimary = false
    })


    return IOJobPostResponse(res.jobID.string)
  }

  fun createJob(query: InputStream, props: IOJsonJobRequest, user: User) = query.use {
    Log.trace("#createJob(query={}, props={}, user={})", query, props, user.userID)

    props.config.query = String(query.readAllBytes(), StandardCharsets.UTF_8)
    createJob(props, user.userID)
  }
}
