package org.veupathdb.service.multiblast.service.http;

import java.io.FileWriter;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.StreamingOutput;

import org.gusdb.fgputil.accountdb.UserProfile;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;
import org.veupathdb.service.multiblast.extern.JobQueueMan;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.io.JsonKeys;
import org.veupathdb.service.multiblast.service.conv.JobConverter;
import mb.lib.db.select.SelectJob;
import org.veupathdb.service.multiblast.service.valid.BlastValidator;

import static org.veupathdb.service.multiblast.service.http.Util.parseUrlJobID;
import static org.veupathdb.service.multiblast.service.http.Util.wrapException;

public class JobService
{
  private static JobService instance;

  private JobService() {
  }

  public static JobService getInstance() {
    if (instance == null)
      instance = new JobService();

    return instance;
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

    if (input.getConfig() == null
      || input.getConfig().getQuery() == null
      || input.getConfig().getQuery().isBlank())
      throw new UnprocessableEntityException(Collections.singletonMap(
        JsonKeys.Query,
        Collections.singletonList(
          BlastValidator.errRequired)
      ));

    try {
      var job = JobConverter.toInternal(user.getUserId(), input.getConfig());

      try (var con = PgDbMan.getInstance().getDataSource().getConnection()) {
        con.setAutoCommit(false);

        try {
          new InsertJob(con, job).execute();

          var path = Util.createJobDir(job.getJobId());
          var file = path.resolve("query.txt").toFile();

          if (!file.createNewFile())
            throw new InternalServerErrorException("failed to create job query file");

          try (var fw = new FileWriter(file)) {
            fw.write(input.getConfig().getQuery());
          }

          new InsertJobConfig(con, job).execute();

          JobQueueMan.submitJob(job);

        } catch (Exception e) {
          con.rollback();
          throw e;
        }

        con.commit();
      }
    } catch (Exception e) {
      throw Util.wrapException(e);
    }

    // TODO: insert job
    // TODO: construct output

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

  public GetJobResponse getJob(String rawID, UserProfile user, Request request) {
    var jobID = parseUrlJobID(rawID);

    try {
      var opt = new SelectJob(jobID).
        execute(DbManager.userDatabase().getDataSource()::getConnection);

      if (opt.isEmpty())
        throw new NotFoundException();



    } catch (Exception e) {
      throw wrapException(e);
    }

    throw new RuntimeException("implement me");
  }

  public List<GetJobResponse> getJobs(UserProfile user, Request request) {

    throw new RuntimeException("implement me");
  }

  public StreamingOutput getQuery(
    String jobId,
    UserProfile user,
    Request req
  ) {
    throw new RuntimeException("implement me");
  }

  public ReportWrap getReport(
    String jobId,
    IOBlastFormat format,
    List<IOBlastReportField> fields,
    UserProfile user,
    Request req
  ) {
    throw new RuntimeException("implement me");
  }

  public static class ReportWrap
  {
    public final IOBlastFormat format;
    public final StreamingOutput stream;

    public ReportWrap(IOBlastFormat format, StreamingOutput stream) {
      this.format = format;
      this.stream = stream;
    }
  }
}
