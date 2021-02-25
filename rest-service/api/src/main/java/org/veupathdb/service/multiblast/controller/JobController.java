package org.veupathdb.service.multiblast.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ContainerRequest;
import org.veupathdb.lib.container.jaxrs.model.User;
import org.veupathdb.lib.container.jaxrs.providers.UserProvider;
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated;
import org.veupathdb.service.multiblast.generated.model.IOBlastReportField;
import org.veupathdb.service.multiblast.generated.model.IOJsonJobRequest;
import org.veupathdb.service.multiblast.generated.resources.Jobs;
import org.veupathdb.service.multiblast.model.io.Headers;
import org.veupathdb.service.multiblast.service.http.JobService;
import org.veupathdb.service.multiblast.util.Format;

@Authenticated(allowGuests = true)
public class JobController implements Jobs
{
  private static final String AttachmentPat = "attachment; filename=\"%s.%s\"";

  private final Request request;

  private final JobService svc;

  public JobController(@Context Request request) {
    this.request = request;
    this.svc     = JobService.getInstance();
  }

  /**
   * @return A list of jobs associated with the currently logged in user.
   */
  @Override
  public Response getJobs() {
    return okJSON(svc.getJobs(getUser(request)));
  }

  /**
   * Create a new job by JSON body.
   *
   * @param entity New job request parameters.
   *
   * @return Basic info about the newly created job (such as the job id).
   */
  @Override
  public Response postJobs(IOJsonJobRequest entity) {
    return okJSON(svc.createJob(entity, getUser(request)));
  }

  /**
   * Create a new job with the blast query uploaded as a separate file.
   *
   * @return Basic info about the newly created job (such as the job id).
   */
  @Override
  public Response postJobs(InputStream query, InputStream config) {
    IOJsonJobRequest props;

    try {
      props = Format.Json.readerFor(IOJsonJobRequest.class).readValue(config);
    } catch (IOException e) {
      throw new BadRequestException(e);
    }

    return okJSON(svc.createJob(query, props, getUser(request)));
  }

  /**
   * Attempt to retrieve a specific job associated with the logged in user.
   * <p>
   * If no job with the given ID was found, or the specified job is not
   * associated with the current user, this endpoint returns a 404.
   *
   * @param jobID ID of the job to look up.
   *
   * @return Full details about the specified job.
   */
  @Override
  public Response getJobsByJobId(String jobID) {
    return okJSON(svc.getJob(jobID, getUser(request)));
  }

  /**
   * Retrieve the raw blast query for a specific job.
   *
   * @param jobID    ID of the job whose query should be retrieved.
   * @param download Whether or not the query should be marked as an attachment
   *                 in the HTTP response.
   *
   * @return The query file associated with the specific job, either as a raw
   * text output, or a file attachment.
   */
  @Override
  public Response getJobsQueryByJobId(String jobID, boolean download) {
    var res = Response.status(Response.Status.OK)
      .type(MediaType.TEXT_PLAIN_TYPE);

    if (download)
      res = res.header("Content-Disposition", String.format(AttachmentPat, (jobID + "-query"), "txt"));

    return res.entity(svc.getQuery(jobID)).build();
  }

  @Override
  public Response getJobsReportByJobId(
    String jobID,
    String format,
    boolean zip,
    boolean inline,
    List<IOBlastReportField> fields
  ) {
    var user         = getUser(request);
    var maxDlSizeStr = ((ContainerRequest)request).getHeaderString(Headers.ContentMaxLength);
    var maxDlSize    = maxDlSizeStr == null ? null : Long.parseLong(maxDlSizeStr);
    var wrap         = svc.getReport(jobID, user.getUserID(), format, zip, fields, maxDlSize);
    var resp         = Response.status(200).header("Content-Type", wrap.contentType);

    if (!inline)
      resp.header("Content-Disposition", String.format(AttachmentPat, "report", wrap.ext));

    return resp.entity(wrap.stream).build();
  }

  // //////////////////////////////////////////////////////////////////////////////////////////// //
  // Helper Methods                                                                               //
  // //////////////////////////////////////////////////////////////////////////////////////////// //

  static User getUser(Request req) {
    return UserProvider.lookupUser(req).orElseThrow(Utils::noUserExcept);
  }

  static Response okJSON(Object entity) {
    return Response.status(Response.Status.OK)
      .type(MediaType.APPLICATION_JSON_TYPE)
      .entity(entity)
      .build();
  }
}
