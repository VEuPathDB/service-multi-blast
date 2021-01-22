package mb.lib.db.constants;


public interface SQL
{
  interface Delete
  {
    interface MultiBlastJobs
    {
      String ByID = Load.delete(Schema.MultiBlast, Table.MultiBlast.Jobs, "by-id");
    }

    interface MultiBlastUsers
    {
      String ByJobID = Load.delete(Schema.MultiBlast, Table.MultiBlast.Users, "by-job-id");
    }
  }

  interface Insert
  {
    String Job  = Load.insert(Schema.MultiBlast, "job");
    String User = Load.insert(Schema.MultiBlast, "user");
  }

  interface Select
  {
    interface MultiBlastJobs
    {
      String ById   = Load.select(Schema.MultiBlast, Table.MultiBlast.Jobs, "by-id");
      String ByUser = Load.select(Schema.MultiBlast, Table.MultiBlast.Jobs, "by-user");
      String Stale  = Load.select(Schema.MultiBlast, Table.MultiBlast.Jobs, "stale");

      String ShortById         = Load.select(
        Schema.MultiBlast,
        Table.MultiBlast.Jobs,
        "short-by-id"
      );
      String FullUserRow       = Load.select(Schema.MultiBlast, Table.MultiBlast.Jobs, "long-user");
      String ShortUserByUserID = Load.select(
        Schema.MultiBlast,
        Table.MultiBlast.Jobs,
        "short-user-by-user-id"
      );
    }
  }

  interface Update
  {
    interface MultiBlastJobs
    {
      String DeleteDate = Load.update(Schema.MultiBlast, Table.MultiBlast.Jobs, "delete-date");
      String QueueID    = Load.update(Schema.MultiBlast, Table.MultiBlast.Jobs, "queue-id");
    }
  }
}

