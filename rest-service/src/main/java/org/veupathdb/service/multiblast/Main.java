package org.veupathdb.service.multiblast;

import mb.lib.config.Config;
import org.veupathdb.lib.container.jaxrs.config.Options;
import org.veupathdb.lib.container.jaxrs.health.Dependency;
import org.veupathdb.lib.container.jaxrs.server.ContainerResources;
import org.veupathdb.lib.container.jaxrs.server.Server;
import org.veupathdb.service.multiblast.util.QueueDep;

public class Main extends Server {
  private static final Config config = Config.getInstance();

  public static void main(String[] args) {
    var server = new Main();

    server.enableAccountDB();
    server.enableUserDB();
    server.start(args);
  }

  @Override
  protected ContainerResources newResourceConfig(Options options) {
    final var out =  new Resources(options);
    // FIXME: ENABLE THIS!!!
//    out.enableAuth();

    // Enabled by default for debugging purposes, this should be removed when
    // production ready.
    out.property("jersey.config.server.tracing.type", "ALL")
      .property("jersey.config.server.tracing.threshold", "VERBOSE");

    return out;
  }

  @Override
  protected Dependency[] dependencies() {
    return new Dependency[]{
      new QueueDep(config),
    };
  }

  @Override
  protected Options newOptions() {
    return config;
  }
}
