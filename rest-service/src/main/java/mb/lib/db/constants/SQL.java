package mb.lib.db.constants;

/**
 * SQL Query Cache
 * <p>
 * This interface is a container for the cached query strings loaded from the
 * resources directory.
 */
public interface SQL
{
  interface Select
  {
    interface Users
    {
      String IsGuest = Load.select(Schema.MultiBlast, Table.UserLogins5.Users, "is-guest");
    }
  }

  interface Update
  {
    interface MultiBlastUsers
    {
      String Owner       = Load.update(Schema.MultiBlast, Table.MultiBlast.Users, "owner");
    }
  }
}

