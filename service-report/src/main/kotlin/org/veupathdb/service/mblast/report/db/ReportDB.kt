package org.veupathdb.service.mblast.report.db

import org.veupathdb.lib.container.jaxrs.utils.db.DbManager
import org.veupathdb.lib.hash_id.HashID

object ReportDB {
  fun selectReportJobs(userID: Long) =
    withTransaction { it.selectReportJobs(userID) }

  fun selectReportJobs(queryJobID: HashID, userID: Long) =
    withTransaction { it.selectReportJobs(queryJobID, userID) }

  fun selectReportJob(reportJobID: HashID, userID: Long) =
    withTransaction { it.selectReportJob(reportJobID, userID) }

  fun selectReportJob(reportJobID: HashID) =
    withTransaction { it.selectReportJob(reportJobID) }

  fun <R> withTransaction(fn: (tx: ReportDBTX) -> R): R {
    val tx = ReportDBTX(DbManager.userDatabase().dataSource.connection)
    try {
      return fn(tx).also { tx.commit() }
    } catch (e: Throwable) {
      tx.rollback()
      throw e
    } finally {
      tx.close()
    }
  }
}