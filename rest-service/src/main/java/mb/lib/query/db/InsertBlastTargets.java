package mb.lib.query.db;

import java.sql.Connection;

import mb.lib.model.HashID;
import mb.lib.query.model.JobTarget;

public class InsertBlastTargets
{
  private static final String Query = """
    INSERT INTO
      userlogins5.multiblast_job_to_targets (job_digest, organism, target_file)
    VALUES
      (?, ?, ?)
    """;

  private final Connection  con;
  private final HashID      jobID;
  private final JobTarget[] dbs;

  public InsertBlastTargets(
    Connection con,
    HashID jobID,
    JobTarget[] dbs
  ) {
    this.con      = con;
    this.jobID    = jobID;
    this.dbs      = dbs;
  }

  public void run() throws Exception {
    try (var ps = con.prepareStatement(Query)) {
      ps.setBytes(1, jobID.bytes());

      for (var db : dbs) {
        ps.setString(2, db.getOrganism());
        ps.setString(3, db.getTarget());
        ps.addBatch();
      }

      ps.executeBatch();
    }
  }
}
