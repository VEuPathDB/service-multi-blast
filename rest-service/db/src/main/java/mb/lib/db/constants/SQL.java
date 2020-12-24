package mb.lib.db.constants;


public interface SQL
{
  interface Delete
  {

  }

  interface Insert
  {
    String Job       = Load.insert("job");
    String JobConfig = Load.insert("config");
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

