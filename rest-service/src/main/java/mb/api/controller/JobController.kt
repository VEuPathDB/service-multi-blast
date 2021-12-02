package mb.api.controller


import mb.api.controller.resources.Paths
import mb.api.controller.resources.Vars
import mb.api.model.IOJsonJobRequest
import mb.api.service.http.job.JobService
import mb.api.service.http.job.translateLongResponse
import mb.api.service.http.job.translateShortResponses
import mb.lib.http.MimeType
import mb.lib.model.HashID
import mb.lib.query.BlastManager
import mb.lib.util.Mapper
import org.glassfish.jersey.media.multipart.FormDataParam
import org.veupathdb.lib.container.jaxrs.providers.UserProvider
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import java.io.IOException
import java.io.InputStream
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Request
import javax.ws.rs.core.Response


private const val ParamQuery      = "query"
private const val ParamProperties = "properties"
private const val ParamDownload   = "download"
private const val AttachmentPat = "attachment; filename=\"%s.%s\""


private fun getUser(req: Request) = UserProvider.lookupUser(req).orElseThrow(::noUserExcept)!!

private fun Any.okJSON() = Response.status(Response.Status.OK)
  .type(MediaType.APPLICATION_JSON_TYPE)
  .entity(this)
  .build()!!

@Path(Paths.Jobs)
@Authenticated(allowGuests = true)
class JobController(@Context private val request: Request) {
  private val svc = JobService.instance

  /**
   * @return A list of jobs associated with the currently logged-in user.
   */
  @GET
  @Produces(MimeType.ApplicationJSON)
  fun getJobs() = errorWrap { translateShortResponses(BlastManager.getUserBlastJobs((getUser(request).userID))).okJSON() }


  /**
   * Create a new job by JSON body.
   *
   * @param entity New job request parameters.
   *
   * @return Basic info about the newly created job (such as the job id).
   */
  @POST
  @Produces(MimeType.ApplicationJSON)
  @Consumes(MimeType.ApplicationJSON)
  fun postJob(entity: IOJsonJobRequest?) = errorWrap {
    svc.createJob(enforceBody(entity), getUser(request).userID).okJSON()
  }


  /**
   * Create a new job with the blast query uploaded as a separate file.
   *
   * @return Basic info about the newly created job (such as the job id).
   */
  @POST
  @Produces(MimeType.ApplicationJSON)
  @Consumes("multipart/form-data")
  fun postJob(
    @FormDataParam(ParamQuery)
    query: InputStream?,

    @FormDataParam(ParamProperties)
    config: InputStream?
  ): Response = errorWrap {
    val props: IOJsonJobRequest

    try {
      props = Mapper.readerFor(IOJsonJobRequest::class.java).readValue(config)
    } catch (e: IOException) {
      throw BadRequestException(e)
    }

    if (query == null)
      postJob(props)
    else
      svc.createJob(query, props, getUser(request)).okJSON()
  }


  /**
   * Attempt to retrieve a specific job associated with the logged-in user.
   * <p>
   * If no job with the given ID was found, or the specified job is not
   * associated with the current user, this endpoint returns a 404.
   *
   * @param jobID ID of the job to look up.
   *
   * @return Full details about the specified job.
   */
  @GET
  @Path(Paths.JobByID)
  @Produces(MimeType.ApplicationJSON)
  fun getJob(@PathParam(Vars.JobID) jobID: String) = errorWrap {
    translateLongResponse(BlastManager.getAndLinkUserBlastJob(
      HashID.parseOrThrow(jobID, ::NotFoundException),
      getUser(request).userID) ?: throw NotFoundException()
    ).okJSON()
  }


  /**
   * Retrieve the raw blast query for a specific job.
   *
   * @param jobID
   * ID of the job whose query should be retrieved.
   *
   * @param download
   * Whether the query should be marked as an attachment in the HTTP response.
   *
   * @return The query file associated with the specific job, either as a raw
   * text output, or a file attachment.
   */
  @GET
  @Path(Paths.JobQuery)
  @Produces(value = [MimeType.ApplicationJSON, MimeType.TextPlain])
  fun getQuery(
    @PathParam(Vars.JobID)
    jobID: String,

    @QueryParam(ParamDownload)
    @DefaultValue("false")
    download: Boolean,
  ): Response = errorWrap {
    val res = Response.status(Response.Status.OK).type(MediaType.TEXT_PLAIN_TYPE)

    if (download)
      res.header("Content-Disposition", String.format(AttachmentPat, "$jobID-query", "txt"))

    res.entity(BlastManager.getJobQuery(HashID.parseOrThrow(jobID, ::NotFoundException)).orElseThrow(::NotFoundException)).build()
  }


  @POST
  @Path(Paths.JobByID)
  @Produces(MimeType.ApplicationJSON)
  fun rerunJob(@PathParam(Vars.JobID) jobID: String) = errorWrap {
    BlastManager.rerunJob(HashID.parseOrThrow(jobID, ::NotFoundException), getUser(request).userID)
    val foo = java.lang.String("")
  }

  @GET
  @Path(Paths.JobError)
  @Produces(MimeType.TextPlain)
  fun getJobError(@PathParam(Vars.JobID) jobID: String): Response = errorWrap {
    when (val err = HashID.parseOrThrow(jobID, ::NotFoundException).let(BlastManager::getJobError)) {
      null -> Response.status(404).build()
      else -> Response.status(200).entity(err).build()
    }
  }
}