package mb.lib.query.db;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Clob;
import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.util.UUID;

import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.query.model.*;
import mb.lib.util.BlastConv;

class Util
{
  static BlastRow parseBlastRow(ResultSet rs) throws Exception {
    return parseBlastRow(rs, new BlastRow());
  }

  static UserBlastRow parseUserBlastRow(ResultSet rs) throws Exception {
    return parseBlastRow(rs, new UserBlastRow())
      .setUserID(rs.getLong(Column.MultiBlastUsers.UserId))
      .setDescription(rs.getString(Column.MultiBlastUsers.Description))
      .setMaxDownloadSize(rs.getLong(Column.MultiBlastUsers.MaxDownloadSize))
      .setRunDirectly(rs.getBoolean(Column.MultiBlastUsers.RunDirectly));
  }

  static BlastTargetLink parseTargetLink(ResultSet rs) throws Exception {
    return new BlastTargetLink()
      .setJobID(new HashID(rs.getBytes(Column.MultiBlastJobToTargets.JobID)))
      .setOrganism(rs.getString(Column.MultiBlastJobToTargets.Organism))
      .setTargetFile(rs.getString(Column.MultiBlastJobToTargets.TargetFile));
  }

  static BlastJobLink parseJobLink(ResultSet rs) throws Exception {
    return new BlastJobLink()
      .setChildJobID(new HashID(rs.getBytes(Column.MultiBlastJobToJobs.JobID)))
      .setParentJobID(new HashID(rs.getBytes(Column.MultiBlastJobToJobs.ParentDigest)))
      .setPosition(rs.getInt(Column.MultiBlastJobToJobs.Position));
  }

  static File queryToFile(Clob queryClob) throws Exception {
    var queryFile = new File("/tmp/" + UUID.randomUUID());

    if (!queryFile.createNewFile()) {
      queryClob.free();
      throw new Exception("Failed to create tmp file for job query.");
    }

    queryFile.deleteOnExit();

    queryClob.getAsciiStream().transferTo(new FileOutputStream(queryFile));
    queryClob.free();

    return queryFile;
  }

  private static <T extends BlastRow> T parseBlastRow(ResultSet rs, T row) throws Exception {
     row.setJobID(new HashID(rs.getBytes(Column.MultiBlastJobs.JobID)))
      .setConfig(BlastConv.convertJobConfig(rs.getString(Column.MultiBlastJobs.JobConfig)))
      .setQuery(rs.getString(Column.MultiBlastJobs.Query))
      .setQueueID(rs.getInt(Column.MultiBlastJobs.QueueID))
      .setProjectID(rs.getString(Column.MultiBlastJobs.ProjectID))
      .setStatus(JobStatus.unsafeFromString(rs.getString(Column.MultiBlastJobs.Status)))
      .setCreatedOn(rs.getObject(Column.MultiBlastJobs.CreatedOn, OffsetDateTime.class))
      .setDeleteOn(rs.getObject(Column.MultiBlastJobs.DeleteOn, OffsetDateTime.class));

     return row;
  }
}
