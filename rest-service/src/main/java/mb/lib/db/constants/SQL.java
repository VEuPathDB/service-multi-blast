package mb.lib.db.constants;

/**
 * SQL Query Cache
 * <p>
 * This interface is a container for the cached query strings loaded from the
 * resources directory.
 */
public interface SQL
{
  interface Delete
  {
    /**
     * Delete queries for MultiBlast Jobs
     */
    interface MultiBlastJobs
    {
      String ByID = Load.delete(Schema.MultiBlast, Table.MultiBlast.Jobs, "by-id");
    }

    /**
     * Delete queries for MultiBlast user to job links.
     */
    interface MultiBlastUsers
    {
      String ByJobID     = Load.delete(Schema.MultiBlast, Table.MultiBlast.Users, "by-job-id");
      String StaleGuests = Load.delete(Schema.MultiBlast, Table.MultiBlast.Users, "stale-guests");
    }

    /**
     * Delete queries for MultiBlast job to job links.
     */
    interface MultiBlastJobToJobs
    {
      String ByID = Load.delete(Schema.MultiBlast, Table.MultiBlast.JobToJobs, "by-id");
    }

    /**
     * Delete queries for MultiBlast job to target links.
     */
    interface MultiBlastJobToTargets
    {
      String ByID = Load.delete(Schema.MultiBlast, Table.MultiBlast.JobToTargets, "by-id");
    }

    /**
     * Delete queries for MultiBlast format jobs.
     */
    interface MultiBlastFmtJobs
    {
      String ByID = Load.delete(Schema.MultiBlast, Table.MultiBlast.FormatJobs, "by-id");
    }
  }

  interface Insert
  {
    String Job    = Load.insert(Schema.MultiBlast, "job");
    String User   = Load.insert(Schema.MultiBlast, "user");
    String Link   = Load.insert(Schema.MultiBlast, "link");
    String Target = Load.insert(Schema.MultiBlast, "target");
    String FmtJob = Load.insert(Schema.MultiBlast, "fmt-job");
  }

  interface Select
  {
    interface MultiBlastJobs
    {
      String ByParent          = Load.select(Schema.MultiBlast, Table.MultiBlast.Jobs, "by-parent");
      String Stale             = Load.select(Schema.MultiBlast, Table.MultiBlast.Jobs, "stale");
      String Orphaned          = Load.select(Schema.MultiBlast, Table.MultiBlast.Jobs, "orphaned");
      String ShortUserByUserID = Load.select(
        Schema.MultiBlast,
        Table.MultiBlast.Jobs,
        "short-user-by-user-id"
      );
    }

    interface MultiBlastJobToJobs
    {
      String ByParent   = Load.select(Schema.MultiBlast, Table.MultiBlast.JobToJobs, "by-parent");
      String GetParent  = Load.select(Schema.MultiBlast, Table.MultiBlast.JobToJobs, "get-parent");
      String ByUserID   = Load.select(Schema.MultiBlast, Table.MultiBlast.JobToJobs, "by-user-id");
      String LinkExists = Load.select(Schema.MultiBlast, Table.MultiBlast.JobToJobs, "link-exists");
    }

    interface MultiBlastJobToTargets
    {
      String ByJobID  = Load.select(Schema.MultiBlast, Table.MultiBlast.JobToTargets, "by-job-id");
      String ByUserID = Load.select(Schema.MultiBlast, Table.MultiBlast.JobToTargets, "by-user-id");
    }

    interface MultiBlastUsers
    {
      String UserIsLinked = Load.select(
        Schema.MultiBlast,
        Table.MultiBlast.Users,
        "user-is-linked"
      );
      String ByID         = Load.select(Schema.MultiBlast, Table.MultiBlast.Users, "by-id");
    }

    interface MultiBlastFormatJobs
    {
      String ByJobID       = Load.select(
        Schema.MultiBlast,
        Table.MultiBlast.FormatJobs,
        "by-job-id"
      );
      String ByJobReportID = Load.select(
        Schema.MultiBlast,
        Table.MultiBlast.FormatJobs,
        "by-job-report-id.sql"
      );
    }

    interface MultiBlastUserToFormatJobs
    {

    }

    interface MultiBlastMeta
    {
      String ByKey = Load.select(Schema.MultiBlast, Table.MultiBlast.Meta, "value");
    }

    interface Users
    {
      String IsGuest = Load.select(Schema.MultiBlast, Table.UserLogins5.Users, "is-guest");
    }
  }

  interface Update
  {
    interface MultiBlastJobs
    {
      String DeleteDate = Load.update(Schema.MultiBlast, Table.MultiBlast.Jobs, "delete-date");
      String QueueID    = Load.update(Schema.MultiBlast, Table.MultiBlast.Jobs, "queue-id");
      String Status     = Load.update(Schema.MultiBlast, Table.MultiBlast.Jobs, "status");
    }

    interface MultiBlastUsers
    {
      String RunDirectly = Load.update(Schema.MultiBlast, Table.MultiBlast.Users, "run-directly");
      String Owner       = Load.update(Schema.MultiBlast, Table.MultiBlast.Users, "owner");
    }

    interface MultiBlastFmtJobs
    {
      String Status        = Load.update(Schema.MultiBlast, Table.MultiBlast.FormatJobs, "status");
      String StatusQueueID = Load.update(
        Schema.MultiBlast,
        Table.MultiBlast.FormatJobs,
        "status-queue-id"
      );
    }
  }
}

