package org.veupathdb.service.multiblast.controller;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;

import org.veupathdb.service.multiblast.generated.model.InputBlastFmtField;
import org.veupathdb.service.multiblast.generated.model.InputBlastFormat;
import org.veupathdb.service.multiblast.generated.model.NewJobPostRequestJSON;
import org.veupathdb.service.multiblast.generated.model.NewJobPostRequestMultipart;
import org.veupathdb.service.multiblast.generated.resources.Jobs;

public class JobController implements Jobs
{
  private final Request request;

  public JobController(@Context Request request) {
    this.request = request;
  }

  @Override
  public PostJobsResponse postJobs(NewJobPostRequestJSON entity) {
    return null;
  }

  @Override
  public PostJobsResponse postJobs(NewJobPostRequestMultipart entity) {
    return null;
  }

  @Override
  public GetJobsByJobIdResponse getJobsByJobId(String jobId) {
    return null;
  }

  @Override
  public GetJobsReportByJobIdResponse getJobsReportByJobId(
    String jobId, InputBlastFormat format, List<InputBlastFmtField> fields
  ) {
    return null;
  }
}
