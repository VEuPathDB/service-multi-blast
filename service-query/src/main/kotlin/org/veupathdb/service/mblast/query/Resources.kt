package org.veupathdb.service.mblast.query

import org.veupathdb.lib.container.jaxrs.config.Options
import org.veupathdb.lib.container.jaxrs.server.ContainerResources
import org.veupathdb.service.mblast.query.controller.*

class Resources(opts: Options) : ContainerResources(opts) {
  init {
    enableAuth()
  }

  override fun resources() =
    arrayOf(
      JobsController::class.java,
      JobQueryDownloadController::class.java,
      JobResultDownloadController::class.java,
      JobByIDController::class.java,
      JobStatusController::class.java,
      JobsStdErrController::class.java,
      TargetsController::class.java,
      GuestLinkController::class.java,
    )
}