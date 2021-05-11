package mb.lib.query.db;

interface Column
{
  interface MultiBlastJobs
  {
    String JobID     = "job_digest";
    String JobConfig = "job_config";
    String Query     = "query";
    String QueueID   = "queue_id";
    String ProjectID = "project_id";
    String Status    = "status";
    String CreatedOn = "created_on";
    String DeleteOn  = "delete_on";
  }

  interface MultiBlastUsers
  {
    String JobID           = "job_digest";
    String UserId          = "user_id";
    String Description     = "description";
    String MaxDownloadSize = "max_download_size";
    String RunDirectly     = "run_directly";
  }

  interface MultiBlastJobToJobs
  {
    String JobID        = "job_digest";
    String ParentDigest = "parent_digest";
    String Position     = "position";
  }

  interface MultiBlastJobToTargets
  {
    String JobID      = "job_digest";
    String Organism   = "organism";
    String TargetFile = "target_file";
  }
}
