package mb;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import mb.api.service.util.QueueDep;
import mb.lib.clean.JobCleanup;
import mb.lib.config.Config;
import org.veupathdb.lib.container.jaxrs.config.Options;
import org.veupathdb.lib.container.jaxrs.health.Dependency;
import org.veupathdb.lib.container.jaxrs.server.ContainerResources;
import org.veupathdb.lib.container.jaxrs.server.Server;

public class Main extends Server {
  private static final Config config = Config.getInstance();
  private static ScheduledExecutorService bgTasks;

  public static void main(String[] args) {
    var server = new Main();

    bgTasks = Executors.newSingleThreadScheduledExecutor();

    server.enableAccountDB();
    server.enableUserDB();
    server.start(args);

    bgTasks.scheduleAtFixedRate(new JobCleanup(), 0, 24, TimeUnit.HOURS);
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
