package org.veupathdb.service.multiblast;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import mb.lib.config.Config;
import org.veupathdb.lib.container.jaxrs.config.Options;
import org.veupathdb.lib.container.jaxrs.health.Dependency;
import org.veupathdb.lib.container.jaxrs.server.ContainerResources;
import org.veupathdb.lib.container.jaxrs.server.Server;
import org.veupathdb.service.multiblast.service.JobCleanup;
import org.veupathdb.service.multiblast.util.QueueDep;

public class Main extends Server {
  private static final Config config = Config.getInstance();
  private static ScheduledExecutorService bgTasks;

  public static void main(String[] args) {
    var server = new Main();

    bgTasks = Executors.newSingleThreadScheduledExecutor();
    bgTasks.scheduleWithFixedDelay(new JobCleanup(), 4, 12, TimeUnit.HOURS);

    server.enableAccountDB();
    server.enableUserDB();
    server.start(args);
  }

  @Override
  protected void onShutdown() {
    super.onShutdown();

    bgTasks.shutdown();
  }

  @Override
  protected ContainerResources newResourceConfig(Options options) {
    final var out =  new Resources(options);
    out.enableAuth();

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
