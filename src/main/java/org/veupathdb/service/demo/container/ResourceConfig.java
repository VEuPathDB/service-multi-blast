package org.veupathdb.service.demo.container;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.veupathdb.service.demo.config.Options;
import org.veupathdb.service.demo.container.middleware.AuthFilter;
import org.veupathdb.service.demo.container.middleware.Log4JFilter;
import org.veupathdb.service.demo.container.middleware.RequestIdFilter;
import org.veupathdb.service.demo.container.utils.DbManager;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ResourceConfig extends org.glassfish.jersey.server.ResourceConfig {
  private static final Class<?>[] DEFAULT_CLASSES = {
    JacksonFeature.class,
    RequestIdFilter.class,
    Log4JFilter.class,
  };

  public ResourceConfig(Options opts, Class<?>... classes) {
    var providers = Arrays.stream(classes).collect(Collectors.toSet());
    providers.addAll(Arrays.asList(DEFAULT_CLASSES));

    registerClasses(providers)
      .register(new AuthFilter(opts, DbManager.getAccountDatabase()));
  }
}
