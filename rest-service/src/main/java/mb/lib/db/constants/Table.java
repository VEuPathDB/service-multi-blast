package mb.lib.db.constants;

public interface Table
{
  interface MultiBlast
  {
    String Jobs              = "multiblast_jobs";
    String Users             = "multiblast_users";
    String JobToJobs         = "multiblast_job_to_jobs";
    String JobToTargets      = "multiblast_job_to_targets";
  }

  interface UserLogins5
  {
    String Users = "users";
  }
}
