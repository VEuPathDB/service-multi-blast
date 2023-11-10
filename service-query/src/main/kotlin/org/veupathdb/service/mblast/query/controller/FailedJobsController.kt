package org.veupathdb.service.mblast.query.controller

import org.veupathdb.service.mblast.query.ServiceOptions
import org.veupathdb.service.mblast.query.generated.model.UnauthorizedErrorImpl
import org.veupathdb.service.mblast.query.generated.resources.MaintenanceBrokenJobs
import org.veupathdb.service.mblast.query.service.jobs.list.ListBrokenJobs

class FailedJobsController : MaintenanceBrokenJobs {
  override fun getMaintenanceBrokenJobs(adminToken: String?): MaintenanceBrokenJobs.GetMaintenanceBrokenJobsResponse {
    if (ServiceOptions.adminAuthToken.isEmpty || adminToken != ServiceOptions.adminAuthToken.get())
      return MaintenanceBrokenJobs.GetMaintenanceBrokenJobsResponse
        .respond401WithApplicationJson(UnauthorizedErrorImpl().also { it.message = "unauthorized" })

    return MaintenanceBrokenJobs.GetMaintenanceBrokenJobsResponse.respond200WithApplicationJson(ListBrokenJobs())
  }
}