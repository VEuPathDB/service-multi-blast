package org.veupathdb.service.multiblast.service.http;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.StreamingOutput;

import org.gusdb.fgputil.accountdb.UserProfile;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.io.JsonKeys;
import org.veupathdb.service.multiblast.service.repo.SelectJob;
import org.veupathdb.service.multiblast.service.valid.BlastValidator;

import static org.veupathdb.service.multiblast.service.http.Util.*;

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
    if (input == null)
      throw new BadRequestException();
    if (input.getConfig() == null)
      throw new UnprocessableEntityException(
        Collections.singletonMap(JsonKeys.Config, Collections.singletonList("is required"))
      );

    {
      var err = BlastValidator.getInstance().validate(input.getConfig());
      if (!err.isEmpty()) {
        throw new UnprocessableEntityException(err);
      }
    }

    throw new RuntimeException("implement me");
  }

  public NewJobPostResponse createJob(
    NewJobPostRequestMultipart input,
    UserProfile user,
    Request request
  ) {
    if (input == null)
      throw new BadRequestException();
    if (input.getQuery() == null)
      throw new BadRequestException();
    if (input.getProperties() == null)
      throw new BadRequestException();
    if (input.getProperties().getConfig() == null)
      throw new UnprocessableEntityException(
        Collections.singletonMap(JsonKeys.Config, Collections.singletonList("is required"))
      );

    {
      var err = BlastValidator.getInstance().validate(input.getProperties().getConfig());
      if (!err.isEmpty()) {
        throw new UnprocessableEntityException(err);
      }
    }

    throw new RuntimeException("implement me");
  }

  public GetJobResponse getJob(int jobId, UserProfile user, Request request) {

    try {
      var opt = new SelectJob(jobId, user.getUserId()).execute();

      if (opt.isEmpty())
        throw new NotFoundException();

    } catch (Exception e) {
      throw wrapException(e);
    }

    throw new RuntimeException("implement me");
  }

  public ReportWrap getReport(
    int jobId,
    InputBlastFormat format,
    List<InputBlastFmtField> fields,
    UserProfile user,
    Request req
  ) {
    throw new RuntimeException("implement me");
  }

  public StreamingOutput getQuery(
    int jobId,
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
