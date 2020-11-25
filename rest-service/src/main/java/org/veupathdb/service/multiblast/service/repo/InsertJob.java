package org.veupathdb.service.multiblast.service.repo;

import java.sql.Connection;
import java.time.OffsetDateTime;

import io.vulpine.lib.query.util.RowParser;
import io.vulpine.lib.query.util.StatementPreparer;
import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.model.mapping.BlastTools;
import org.veupathdb.service.multiblast.model.mapping.JobStatuses;

public class InsertJob
{
  private final Job        job;
  private final Connection connection;

  public InsertJob(Connection con, Job job) {
    this.connection = con;
    this.job = job;
  }

  public void execute() throws Exception {
    prepareQuery(connection, prepParser(job), prepStmt(job)).execute();
  }

  public Connection getConnection() {
    return connection;
  }

  static BasicPreparedReadQuery<Void> prepareQuery(
    Connection con,
    RowParser<Void> parse,
    StatementPreparer prep
  ) {
    return new BasicPreparedReadQuery<>(SQL.Insert.Job, con, parse, prep);
  }

  static RowParser<Void> prepParser(Job job) {
    return rs -> {
      job.setJobId(rs.getInt(Column.Job.Jobs.JobID));
      job.setCreatedOn(rs.getObject(Column.Job.Jobs.CreatedOn, OffsetDateTime.class));
      job.setModifiedOn(rs.getObject(Column.Job.Jobs.ModifiedOn, OffsetDateTime.class));

      return null;
    };
  }

  static StatementPreparer prepStmt(Job job) {
    return ps -> {
      ps.setLong(1, job.getUserId());
      ps.setByte(2, JobStatuses.getInstance().requireId(job.getStatus()));
      ps.setByte(3, BlastTools.getInstance().requireId(job.getTool()));
    };
  }
}
