package mblast.migration.mig

import mblast.migration.db.sql.insertQueryToSubQueryLinks
import java.sql.Connection

fun _migrateQuerySubJobs(con: Connection) {
  println("Migrating query sub-job links")
  con.insertQueryToSubQueryLinks()
}