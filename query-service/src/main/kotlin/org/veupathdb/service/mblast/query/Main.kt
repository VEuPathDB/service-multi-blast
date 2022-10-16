@file:JvmName("Main")

package org.veupathdb.service.mblast.query

import org.veupathdb.service.mblast.query.ExecutorFactory
import org.veupathdb.service.mblast.query.Resources
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.compute.platform.config.*
import org.veupathdb.lib.container.jaxrs.config.Options
import org.veupathdb.lib.container.jaxrs.server.Server

class Main : Server() {

  override fun newResourceConfig(opts: Options) = Resources(opts)

  override fun newOptions(): Options = ServiceOptions

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
        .build())
      .addQueue(AsyncQueueConfig.builder()
        .id(ServiceOptions.queueNamePrimary)
        .username(ServiceOptions.jobQueueUsername)
        .password(ServiceOptions.jobQueuePassword)
        .host(ServiceOptions.jobQueueHost)
        .port(ServiceOptions.jobQueuePort)
        .workers(ServiceOptions.jobQueueWorkers)
        .build())
      .addQueue(AsyncQueueConfig.builder()
        .id(ServiceOptions.queueNameSecondary)
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

      server.enableUserDB()

      server.start(args)
    }
  }
}