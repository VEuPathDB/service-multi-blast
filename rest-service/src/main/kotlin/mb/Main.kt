package mb

import mb.api.service.util.QueueDep
import mb.lib.clean.JobCleanup
import mb.lib.config.Config
import org.veupathdb.lib.container.jaxrs.config.Options
import org.veupathdb.lib.container.jaxrs.health.Dependency
import org.veupathdb.lib.container.jaxrs.server.ContainerResources
import org.veupathdb.lib.container.jaxrs.server.Server
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


object Main : Server() {
  @JvmStatic
  val bgTasks: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

  init {
    enableAccountDB()
    enableUserDB()
    bgTasks.scheduleAtFixedRate(JobCleanup, 0, 24, TimeUnit.HOURS)
  }

  @JvmStatic
  fun main(args: Array<String>) = start(args)

  fun startServer(args: Array<String>) {
    start(args)
  }

  override fun onShutdown() {
    super.onShutdown()
    bgTasks.shutdown()
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

}