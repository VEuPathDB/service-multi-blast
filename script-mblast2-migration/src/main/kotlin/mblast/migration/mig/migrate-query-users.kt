package mblast.migration.mig

import mblast.migration.db.sql.insertQueryUserLinks
import java.sql.Connection

fun _migrateQueryUserLinks(con: Connection) {
  println("Migrating query user links.")
  con.insertQueryUserLinks()
}