package mb;

import mb.api.controller.GuestLinkController;
import mb.api.controller.JobController;
import mb.api.controller.MetaController;
import mb.api.controller.ReportController;
import mb.api.controller.resources.Reports;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.veupathdb.lib.container.jaxrs.config.Options;
import org.veupathdb.lib.container.jaxrs.server.ContainerResources;

/**
 * Service Resource Registration.
 *
 * This is where all the individual service specific resources and middleware
 * should be registered.
 */
public class Resources extends ContainerResources {
  public Resources(Options opts) {
    super(opts);
  }

  /**
   * Returns an array of JaxRS endpoints, providers, and contexts.
   *
   * Entries in the array can be either classes or instances.
   */
  @Override
  protected Object[] resources() {
    return new Object[] {
      MultiPartFeature.class,

      JobController.class,
      MetaController.class,
//      DebugController.class,
      GuestLinkController.class,
      ReportController.class,
    };
  }
}
