package org.veupathdb.service.multiblast.service.http;

import java.util.List;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.StreamingOutput;

import org.gusdb.fgputil.accountdb.UserProfile;
import org.veupathdb.service.multiblast.generated.model.*;

public class JobService
{
  private static JobService instance;

  private JobService() {}

  public static JobService getInstance() {
    if (instance == null)
      instance = new JobService();

    return instance;
  }

  public List<GetJobResponse> getJobs(UserProfile user, Request request) {
    throw new RuntimeException("implement me");
  }

  public NewJobPostResponse createJob(
    NewJobPostRequestJSON input,
    UserProfile user,
    Request request
  ) {
    throw new RuntimeException("implement me");
  }

  public NewJobPostResponse createJob(
    NewJobPostRequestMultipart input,
    UserProfile user,
    Request request
  ) {
    throw new RuntimeException("implement me");
  }

  public GetJobResponse getJob(String jobId, UserProfile user, Request request) {
    throw new RuntimeException("implement me");
  }

  public ReportWrap getReport(
    String jobId,
    InputBlastFormat format,
    List<InputBlastFmtField> fields,
    UserProfile user,
    Request req
  ) {
    throw new RuntimeException("implement me");
  }

  public static class ReportWrap {
    public final InputBlastFormat format;
    public final StreamingOutput stream;

    public ReportWrap(InputBlastFormat format, StreamingOutput stream) {
      this.format = format;
      this.stream = stream;
    }
  }
}
