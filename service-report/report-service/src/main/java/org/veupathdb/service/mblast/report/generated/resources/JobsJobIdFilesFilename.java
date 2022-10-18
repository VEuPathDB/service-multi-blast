package org.veupathdb.service.mblast.report.generated.resources;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.report.generated.model.ForbiddenError;
import org.veupathdb.service.mblast.report.generated.model.NotFoundError;
import org.veupathdb.service.mblast.report.generated.model.ServerError;
import org.veupathdb.service.mblast.report.generated.model.UnauthorizedError;
import org.veupathdb.service.mblast.report.generated.support.ResponseDelegate;

@Path("/jobs/{job-id}/files/{filename}")
public interface JobsJobIdFilesFilename {
  @GET
  @Produces({
      "application/json",
      "*/*"
  })
  GetJobsFilesByJobIdAndFilenameResponse getJobsFilesByJobIdAndFilename(
      @PathParam("job-id") String jobId, @PathParam("filename") String filename,
      @QueryParam("download") @DefaultValue("false") boolean download);

  class GetJobsFilesByJobIdAndFilenameResponse extends ResponseDelegate {
    private GetJobsFilesByJobIdAndFilenameResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsFilesByJobIdAndFilenameResponse(Response response) {
      super(response);
    }

    public static HeadersFor200 headersFor200() {
      return new HeadersFor200();
    }

    public static GetJobsFilesByJobIdAndFilenameResponse respond200With(Object entity,
        HeadersFor200 headers) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "*/*");
      responseBuilder.entity(entity);
      headers.toResponseBuilder(responseBuilder);
      return new GetJobsFilesByJobIdAndFilenameResponse(responseBuilder.build(), entity);
    }

    public static GetJobsFilesByJobIdAndFilenameResponse respond401WithApplicationJson(
        UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsFilesByJobIdAndFilenameResponse(responseBuilder.build(), entity);
    }

    public static GetJobsFilesByJobIdAndFilenameResponse respond403WithApplicationJson(
        ForbiddenError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsFilesByJobIdAndFilenameResponse(responseBuilder.build(), entity);
    }

    public static GetJobsFilesByJobIdAndFilenameResponse respond404WithApplicationJson(
        NotFoundError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsFilesByJobIdAndFilenameResponse(responseBuilder.build(), entity);
    }

    public static GetJobsFilesByJobIdAndFilenameResponse respond500WithApplicationJson(
        ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsFilesByJobIdAndFilenameResponse(responseBuilder.build(), entity);
    }

    public static class HeadersFor200 extends HeaderBuilderBase {
      private HeadersFor200() {
      }

      public HeadersFor200 withContentDisposition(final String p) {
        headerMap.put("Content-Disposition", String.valueOf(p));;
        return this;
      }
    }
  }
}
