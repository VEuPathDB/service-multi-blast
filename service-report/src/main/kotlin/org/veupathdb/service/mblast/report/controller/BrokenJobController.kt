package org.veupathdb.service.mblast.report.controller

import org.veupathdb.service.mblast.report.ServiceOptions
import org.veupathdb.service.mblast.report.generated.model.UnauthorizedErrorImpl
import org.veupathdb.service.mblast.report.generated.resources.MaintenanceBrokenJobs
import org.veupathdb.service.mblast.report.service.jobs.list.ListBrokenJobs

class BrokenJobController : MaintenanceBrokenJobs {
  override fun getMaintenanceBrokenJobs(adminToken: String?): MaintenanceBrokenJobs.GetMaintenanceBrokenJobsResponse {
    if (ServiceOptions.adminAuthToken.isEmpty || adminToken != ServiceOptions.adminAuthToken.get())
      return MaintenanceBrokenJobs.GetMaintenanceBrokenJobsResponse
        .respond401WithApplicationJson(UnauthorizedErrorImpl().also { it.message = "unauthorized" })

    return MaintenanceBrokenJobs.GetMaintenanceBrokenJobsResponse.respond200WithApplicationJson(ListBrokenJobs())
  }
}