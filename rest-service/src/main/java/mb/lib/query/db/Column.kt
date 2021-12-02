package mb.lib.query.db

interface Column {
  interface MultiBlastJobs {
    companion object {
      const val JobID = "job_digest"
      const val JobConfig = "job_config"
      const val Query = "query"
      const val QueueID = "queue_id"
      const val ProjectID = "project_id"
      const val Status = "status"
      const val CreatedOn = "created_on"
      const val DeleteOn = "delete_on"
    }
  }

  interface MultiBlastUsers {
    companion object {
      const val JobID = "job_digest"
      const val UserId = "user_id"
      const val Description = "description"
      const val MaxDownloadSize = "max_download_size"
      const val RunDirectly = "run_directly"
    }
  }

  interface MultiBlastJobToJobs {
    companion object {
      const val JobID = "job_digest"
      const val ParentDigest = "parent_digest"
      const val Position = "position"
    }
  }

  interface MultiBlastJobToTargets {
    companion object {
      const val JobID = "job_digest"
      const val Organism = "organism"
      const val TargetFile = "target_file"
    }
  }
}
