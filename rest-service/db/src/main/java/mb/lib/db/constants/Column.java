package mb.lib.db.constants;

public interface Column
{
  interface MultiBlastJobs
  {
    String JobDigest = "job_digest";
    String JobConfig = "job_config";
    String Query     = "query";
    String QueueID   = "queue_id";
    String CreatedOn = "created_on";
    String DeleteOn  = "delete_on";
  }

  interface MultiBlastUsers
  {
    String JobDigest       = "job_digest";
    String UserId          = "user_id";
    String Description     = "description";
    String ParentJobID     = "parent_job";
    String MaxDownloadSize = "max_download_size";
  }
}
