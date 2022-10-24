package mblast.migration.mig

import mblast.migration.db.sql.insertQueryTargets
import java.sql.Connection

fun _migrateQueryTargets(con: Connection) {
  println("Migrating query job targets.")
  con.insertQueryTargets()
}