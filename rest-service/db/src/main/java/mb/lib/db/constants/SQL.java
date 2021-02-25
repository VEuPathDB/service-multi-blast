package mb.lib.db.constants;


public interface SQL
{
  interface Delete
  {
    interface MultiBlastJobs
    {
      String ByID     = Load.delete(Schema.MultiBlast, Table.MultiBlast.Jobs, "by-id");
      String Orphaned = Load.delete(Schema.MultiBlast, Table.MultiBlast.Jobs, "with-no-users");
    }

    interface MultiBlastUsers
    {
      String ByJobID     = Load.delete(Schema.MultiBlast, Table.MultiBlast.Users, "by-job-id");
      String StaleGuests = Load.delete(Schema.MultiBlast, Table.MultiBlast.Users, "stale-guests");
    }
  }

  interface Insert
  {
    String Job    = Load.insert(Schema.MultiBlast, "job");
    String User   = Load.insert(Schema.MultiBlast, "user");
    String Link   = Load.insert(Schema.MultiBlast, "link");
    String Target = Load.insert(Schema.MultiBlast, "target");
  }

  interface Select
  {
    interface MultiBlastJobs
    {
      String ById  = Load.select(Schema.MultiBlast, Table.MultiBlast.Jobs, "by-id");
      String Stale = Load.select(Schema.MultiBlast, Table.MultiBlast.Jobs, "stale");

      String FullUserRow       = Load.select(Schema.MultiBlast, Table.MultiBlast.Jobs, "long-user");
      String ShortUserByUserID = Load.select(
        Schema.MultiBlast,
        Table.MultiBlast.Jobs,
        "short-user-by-user-id"
      );
    }

    interface MultiBlastJobToJobs
    {
      String ByParent  = Load.select(Schema.MultiBlast, Table.MultiBlast.JobToJobs, "by-parent");
      String GetParent = Load.select(Schema.MultiBlast, Table.MultiBlast.JobToJobs, "get-parent");
      String ByUserID  = Load.select(Schema.MultiBlast, Table.MultiBlast.JobToJobs, "by-user-id");
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
    }
  }
}

