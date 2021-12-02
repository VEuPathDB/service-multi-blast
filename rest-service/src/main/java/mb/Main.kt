package mb

import mb.api.service.util.QueueDep
import mb.lib.clean.JobCleanup
import mb.lib.config.Config
import org.veupathdb.lib.container.jaxrs.config.Options
import org.veupathdb.lib.container.jaxrs.health.Dependency
import org.veupathdb.lib.container.jaxrs.server.ContainerResources
import org.veupathdb.lib.container.jaxrs.server.Server
import org.veupathdb.lib.container.jaxrs.server.middleware.JacksonFilter
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class Main : Server() {
  override fun onShutdown() {
    super.onShutdown()
    bgTasks!!.shutdown()
  }

  override fun newResourceConfig(options: Options): ContainerResources {
    val out = Resources(options)
    out.enableAuth()
    return out
  }

  override fun dependencies(): Array<Dependency> {
    return arrayOf(QueueDep)
  }

  override fun newOptions(): Options {
    return Config
  }

  companion object {
    private var bgTasks: ScheduledExecutorService? = null
    @JvmStatic
    fun main(args: Array<String>) {
      val server = Main()
      bgTasks = Executors.newSingleThreadScheduledExecutor()
      server.enableAccountDB()
      server.enableUserDB()
      server.start(args)
      bgTasks!!.scheduleAtFixedRate(JobCleanup, 0, 24, TimeUnit.HOURS)
    }
  }
}