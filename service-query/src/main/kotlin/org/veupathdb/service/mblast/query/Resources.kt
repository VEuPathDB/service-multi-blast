package org.veupathdb.service.mblast.query

import org.veupathdb.lib.container.jaxrs.config.Options
import org.veupathdb.lib.container.jaxrs.server.ContainerResources
import org.veupathdb.service.mblast.query.controller.JobByIDController
import org.veupathdb.service.mblast.query.controller.JobQueryDownloadController
import org.veupathdb.service.mblast.query.controller.JobResultDownloadController
import org.veupathdb.service.mblast.query.controller.JobsController

class Resources(opts: Options) : ContainerResources(opts) {
  init {
    enableAuth()
  }

  override fun resources() =
    arrayOf<Any>(
      JobsController::class.java,
      JobQueryDownloadController::class.java,
      JobResultDownloadController::class.java,
      JobByIDController::class.java,
    )
}