package org.veupathdb.service.multiblast.service.repo;

import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.util.Optional;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.model.mapping.BlastTools;
import org.veupathdb.service.multiblast.model.mapping.JobStatuses;
import org.veupathdb.service.multiblast.service.repo.Column.Job.Jobs;

public class SelectJob
{
  private final int jobId;
  private final long userId;

  public SelectJob(int jobId, long userId) {
    this.jobId  = jobId;
    this.userId = userId;
  }

  public Optional<Job> execute() throws Exception {
    return new BasicPreparedReadQuery<>(
      SQL.Select.Job.JobByID,
      Util::getPgConnection,
      SelectJob::parseResult,
      ps -> {
        ps.setInt(1, jobId);
        ps.setLong(2, userId);
      }
    ).execute().getValue();
  }

  static Optional<Job> parseResult(ResultSet rs) throws Exception {
    if (!rs.next())
      return Optional.empty();

    return Optional.of(new Job(
      rs.getInt(Jobs.JobID),
      rs.getLong(Jobs.UserID),
      JobStatuses.getInstance().requireValue(rs.getByte(Jobs.StatusID)),
      BlastTools.getInstance().requireValue(rs.getByte(Jobs.ToolID)),
      rs.getObject(Jobs.CreatedOn, OffsetDateTime.class),
      rs.getObject(Jobs.ModifiedOn, OffsetDateTime.class)
    ));
  }
}
