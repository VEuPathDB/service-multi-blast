package mb.lib.db.constants

/**
 * SQL Query Cache
 *
 *
 * This interface is a container for the cached query strings loaded from the
 * resources directory.
 */
object SQL {
  object Select {
    object Users {
      val IsGuest = Load.select(Schema.MultiBlast, Table.UserLogins5.Users, "is-guest")
    }
  }

  object Update {
    object MultiBlastUsers {
      val Owner = Load.update(Schema.MultiBlast, Table.MultiBlast.Users, "owner")
    }
  }
}