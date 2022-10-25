package org.veupathdb.service.mblast.query

import org.veupathdb.lib.container.jaxrs.config.Options
import org.veupathdb.lib.container.jaxrs.server.ContainerResources
import org.veupathdb.service.mblast.query.controller.*

class Resources(opts: Options) : ContainerResources(opts) {
  init {
    enableAuth()

    property("jersey.config.server.tracing.type", "ALL")
    property("jersey.config.server.tracing.threshold", "VERBOSE");
  }

  override fun resources() =
    arrayOf<Any>(
      JobsController::class.java,
      JobQueryDownloadController::class.java,
      JobResultDownloadController::class.java,
      JobByIDController::class.java,
      JobStatusController::class.java,
    )
}