package org.veupathdb.service.multiblast;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.veupathdb.lib.container.jaxrs.config.Options;
import org.veupathdb.lib.container.jaxrs.server.ContainerResources;
import org.veupathdb.service.multiblast.controller.DebugController;
import org.veupathdb.service.multiblast.controller.GuestLinkController;
import org.veupathdb.service.multiblast.controller.JobController;
import org.veupathdb.service.multiblast.controller.MetaController;

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
      DebugController.class,
      GuestLinkController.class,
    };
  }
}
