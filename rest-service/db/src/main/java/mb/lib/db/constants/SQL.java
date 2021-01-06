package mb.lib.db.constants;


public interface SQL
{
  interface Delete
  {

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
      String ById = Load.select(Schema.MultiBlast, Table.MultiBlast.MultiBlastJobs, "by-id");
    }

    interface MultiBlastUsers
    {
      String ById = Load.select(Schema.MultiBlast, Table.MultiBlast.MultiBlastUsers, "by-id");
    }
  }
}

