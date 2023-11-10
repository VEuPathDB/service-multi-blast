package org.veupathdb.service.mblast.report

import org.veupathdb.lib.container.jaxrs.config.Options
import org.veupathdb.lib.container.jaxrs.server.ContainerResources
import org.veupathdb.service.mblast.report.controller.*

class Resources(opts: Options) : ContainerResources(opts) {
  init {
    enableAuth()
  }

  override fun resources() =
    arrayOf(
      JobByIDController::class.java,
      JobsController::class.java,
      JobsStdErrController::class.java,
      JobFileListController::class.java,
      JobFileDownloadController::class.java,
      GuestLinkController::class.java,
      CacheMaintenanceController::class.java,
      BrokenJobController::class.java,
    )
}