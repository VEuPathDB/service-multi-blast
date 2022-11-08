package org.veupathdb.service.mblast.query.sql.queries

// ORA-00001
const val ORA_CODE_UNIQUE_VIOLATION = 1

object Schema {
  const val MBlast = "mblast"
  const val Users  = "userlogins5"
}

object Table {
  const val QueryConfigs   = "query_configs"
  const val QueryToQueries = "query_to_subqueries"
  const val QueryToTargets = "query_to_targets"
  const val QueryToUsers   = "query_to_users"
  const val Users          = "users"
}

object Column {
  const val ChildJobID  = "child_job_id"
  const val Config      = "config"
  const val CreatedOn   = "created_on"
  const val Description = "description"
  const val IsGuest     = "is_guest"
  const val ParentJobID = "parent_job_id"
  const val Position    = "position"
  const val ProjectID   = "project_id"
  const val Query       = "query"
  const val QueryJobID  = "query_job_id"
  const val Summary     = "summary"
  const val TargetFile  = "target_file"
  const val TargetName  = "target_name"
  const val UserID      = "user_id"
}
