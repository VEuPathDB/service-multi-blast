package org.veupathdb.service.mblast.report.generated.resources;

import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.report.generated.model.FileEntry;
import org.veupathdb.service.mblast.report.generated.model.ForbiddenError;
import org.veupathdb.service.mblast.report.generated.model.NotFoundError;
import org.veupathdb.service.mblast.report.generated.model.ServerError;
import org.veupathdb.service.mblast.report.generated.model.UnauthorizedError;
import org.veupathdb.service.mblast.report.generated.support.ResponseDelegate;

@Path("/jobs/{job-id}/files")
public interface JobsJobIdFiles {
  @GET
  @Produces("application/json")
  GetJobsFilesByJobIdResponse getJobsFilesByJobId(@PathParam("job-id") String jobId);

  class GetJobsFilesByJobIdResponse extends ResponseDelegate {
    private GetJobsFilesByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsFilesByJobIdResponse(Response response) {
      super(response);
    }

    public static GetJobsFilesByJobIdResponse respond200WithApplicationJson(
        List<FileEntry> entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      GenericEntity<List<FileEntry>> wrappedEntity = new GenericEntity<List<FileEntry>>(entity){};
      responseBuilder.entity(wrappedEntity);
      return new GetJobsFilesByJobIdResponse(responseBuilder.build(), wrappedEntity);
    }

    public static GetJobsFilesByJobIdResponse respond401WithApplicationJson(
        UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsFilesByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsFilesByJobIdResponse respond403WithApplicationJson(ForbiddenError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsFilesByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsFilesByJobIdResponse respond404WithApplicationJson(NotFoundError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsFilesByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsFilesByJobIdResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsFilesByJobIdResponse(responseBuilder.build(), entity);
    }
  }
}
