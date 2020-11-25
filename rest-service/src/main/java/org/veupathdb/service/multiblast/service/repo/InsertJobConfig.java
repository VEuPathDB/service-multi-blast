package org.veupathdb.service.multiblast.service.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.stream.Collectors;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import io.vulpine.lib.query.util.query.PreparedVoidQuery;
import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class InsertJobConfig
{
  private final Job job;

  private final Connection con;

  public InsertJobConfig(Connection con, Job job) {
    this.job  = job;
    this.con  = con;
  }

  PreparedVoidQuery getQuery() {
    return new BasicPreparedVoidQuery(SQL.Insert.JobConfig, con, this::prepareStmt);
  }

  public void execute() throws Exception {
    getQuery().execute();
  }

  void prepareStmt(PreparedStatement ps) throws Exception {
    var cli = new CliBuilder();
    job.getConfig().toCli(cli);
    ps.setInt(1, job.getJobId());
    ps.setString(2, cli.toComponentStream()
      .map(e -> '{' + String.join(",", e) + '}')
      .collect(Collectors.joining(",", "{", "}"))
    );
  }
}
