package mblast.migration.mig

import javax.sql.DataSource

fun RunMigration(dataSource: DataSource) {
  dataSource.connection.use { con ->
    con.autoCommit = false

    _migrateQueryJobs(con)
    con.commit()

    _migrateQueryTargets(con)
    _migrateQueryUserLinks(con)
    _migrateQuerySubJobs(con)

    println("committing")
    con.commit()
  }
}
