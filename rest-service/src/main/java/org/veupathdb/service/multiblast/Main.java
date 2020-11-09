package org.veupathdb.service.multiblast;

import org.veupathdb.lib.container.jaxrs.config.Options;
import org.veupathdb.lib.container.jaxrs.server.ContainerResources;
import org.veupathdb.lib.container.jaxrs.server.Server;
import org.veupathdb.service.multiblast.db.PgDbMan;

public class Main extends Server {
  public static final Config config = new Config();

  public static void main(String[] args) {
    var server = new Main();

    server.enableAccountDB();
    server.start(args);
  }

  @Override
  protected ContainerResources newResourceConfig(Options options) {
    final var out =  new Resources(options);

    // Enabled by default for debugging purposes, this should be removed when
    // production ready.
    out.property("jersey.config.server.tracing.type", "ALL")
      .property("jersey.config.server.tracing.threshold", "VERBOSE");

    return out;
  }

  @Override
  protected void postCliParse(Options opts) {
    PgDbMan.initialize((Config) opts);
  }

  @Override
  protected Options newOptions() {
    return config;
  }
}
