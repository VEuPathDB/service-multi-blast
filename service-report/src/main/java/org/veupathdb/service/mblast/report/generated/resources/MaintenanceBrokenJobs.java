package org.veupathdb.service.mblast.report.generated.resources;

import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.report.generated.model.BrokenJobListEntry;
import org.veupathdb.service.mblast.report.generated.model.ServerError;
import org.veupathdb.service.mblast.report.generated.model.UnauthorizedError;
import org.veupathdb.service.mblast.report.generated.support.ResponseDelegate;

@Path("/maintenance/broken-jobs")
public interface MaintenanceBrokenJobs {
  @GET
  @Produces("application/json")
  GetMaintenanceBrokenJobsResponse getMaintenanceBrokenJobs(
      @HeaderParam("Admin-Token") String adminToken);

  class GetMaintenanceBrokenJobsResponse extends ResponseDelegate {
    private GetMaintenanceBrokenJobsResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetMaintenanceBrokenJobsResponse(Response response) {
      super(response);
    }

    public static GetMaintenanceBrokenJobsResponse respond200WithApplicationJson(
        List<BrokenJobListEntry> entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      GenericEntity<List<BrokenJobListEntry>> wrappedEntity = new GenericEntity<List<BrokenJobListEntry>>(entity){};
      responseBuilder.entity(wrappedEntity);
      return new GetMaintenanceBrokenJobsResponse(responseBuilder.build(), wrappedEntity);
    }

    public static GetMaintenanceBrokenJobsResponse respond401WithApplicationJson(
        UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetMaintenanceBrokenJobsResponse(responseBuilder.build(), entity);
    }

    public static GetMaintenanceBrokenJobsResponse respond500WithApplicationJson(
        ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetMaintenanceBrokenJobsResponse(responseBuilder.build(), entity);
    }
  }
}
