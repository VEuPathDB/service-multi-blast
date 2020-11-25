package org.veupathdb.service.multiblast;

import org.veupathdb.lib.container.jaxrs.config.Options;
import org.veupathdb.lib.container.jaxrs.health.Dependency;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.lib.container.jaxrs.server.ContainerResources;
import org.veupathdb.lib.container.jaxrs.server.Server;
import org.veupathdb.service.multiblast.db.PgDbMan;
import org.veupathdb.service.multiblast.model.mapping.BlastTools;
import org.veupathdb.service.multiblast.service.repo.SelectTasks;
import org.veupathdb.service.multiblast.service.repo.SelectTools;
import org.veupathdb.service.multiblast.util.QueueDep;

public class Main extends Server {
  private static final Config config = Config.getInstance();

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
    var log = LogProvider.logger(getClass());

    log.info("Initializing service DB connection.");
    PgDbMan.initialize((Config) opts);

    log.info("Populating blast config cache.");
    try {
      new SelectTools().execute();
      new SelectTasks(BlastTools.getInstance()).execute();
    } catch (Exception e) {
      log.fatal("Failed to load blast config cache.", e);
      Runtime.getRuntime().exit(1);
    }
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
