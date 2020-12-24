package mb.lib.db.constants;

public interface Column
{
  interface MultiBlastJobs
  {
    String JobDigest = "job_digest";
    String JobConfig = "job_config";
  }

  interface MultiBlastUsers
  {
    String JobDigest   = "job_digest";
    String UserId      = "user_id";
    String Description = "description";
  }
}
