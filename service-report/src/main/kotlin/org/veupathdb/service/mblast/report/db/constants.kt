package org.veupathdb.service.mblast.report.db

// ORA-00001
const val ORA_CODE_UNIQUE_VIOLATION = 1

object Schema {
  const val MBlast = "mblast"
  const val Users  = "userlogins5"
}

object Table {
  const val ReportConfigs = "report_configs"
  const val ReportToUsers = "report_to_users"
  const val Users         = "users"
}

object Column {
  const val Config      = "config"
  const val CreatedOn   = "created_on"
  const val Description = "description"
  const val IsGuest     = "is_guest"
  const val QueryJobID  = "query_job_id"
  const val ReportJobID = "report_job_id"
  const val Summary     = "summary"
  const val UserID      = "user_id"
}