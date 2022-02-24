package mb

import mb.api.controller.GuestLinkController
import mb.api.controller.JobController
import mb.api.controller.QueueController
import mb.api.controller.ReportController
import org.glassfish.jersey.media.multipart.MultiPartFeature
import org.veupathdb.lib.container.jaxrs.config.Options
import org.veupathdb.lib.container.jaxrs.server.ContainerResources

/**
 * Service Resource Registration.
 *
 * This is where all the individual service specific resources and middleware
 * should be registered.
 */
class Resources(opts: Options): ContainerResources(opts) {
  /**
   * Returns an array of JaxRS endpoints, providers, and contexts.
   *
   * Entries in the array can be either classes or instances.
   */
  override fun resources(): Array<Any> {
    return arrayOf(
      MultiPartFeature::class.java,

      JobController::class.java,
//      DebugController.class,
      GuestLinkController::class.java,
      ReportController::class.java,
      QueueController::class.java,
    )
  }
}
