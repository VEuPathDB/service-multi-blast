package org.veupathdb.service.mblast.report

import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.compute.platform.config.*
import org.veupathdb.lib.container.jaxrs.config.Options
import org.veupathdb.lib.container.jaxrs.server.Server
import org.veupathdb.lib.container.jaxrs.server.middleware.PrometheusFilter
import org.veupathdb.service.mblast.report.jobs.ExecutorFactory

class Main : Server() {

  override fun newResourceConfig(opts: Options) = Resources(opts)

  override fun newOptions() = ServiceOptions

  override fun postCliParse(opts: Options) {
    AsyncPlatform.init(AsyncPlatformConfig.builder()
      .dbConfig(AsyncDBConfig.builder()
        .dbName(ServiceOptions.queueDBName)
        .host(ServiceOptions.queueDBHost)
        .port(ServiceOptions.queueDBPort)
        .username(ServiceOptions.queueDBUsername)
        .password(ServiceOptions.queueDBPassword)
        .poolSize(ServiceOptions.queueDBPoolSize)
        .build())
      .jobConfig(AsyncJobConfig.builder()
        .executorFactory(ExecutorFactory())
        .expirationDays(ServiceOptions.jobCacheTimeoutDays)
        .build())
      .s3Config(AsyncS3Config.builder()
        .host(ServiceOptions.s3Host)
        .bucket(ServiceOptions.s3Bucket)
        .accessToken(ServiceOptions.s3AccessToken)
        .secretKey(ServiceOptions.s3SecretKey)
        .port(ServiceOptions.s3Port)
        .https(ServiceOptions.s3UseHttps)
        .build())
      .addQueue(AsyncQueueConfig.builder()
        .id(ServiceOptions.jobQueueName)
        .username(ServiceOptions.jobQueueUsername)
        .password(ServiceOptions.jobQueuePassword)
        .host(ServiceOptions.jobQueueHost)
        .port(ServiceOptions.jobQueuePort)
        .workers(ServiceOptions.jobQueueWorkers)
        .build())
      .build())
  }

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      val server = Main()

      server.enableAccountDB()
      server.enableUserDB()

      PrometheusFilter.setPathTransform { it.replace(Regex("[0-9A-Fa-f]{32}"), "{id}") }

      server.start(args)
    }
  }
}