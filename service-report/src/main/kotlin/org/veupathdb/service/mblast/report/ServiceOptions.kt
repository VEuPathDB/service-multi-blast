package org.veupathdb.service.mblast.report

import io.foxcapades.lib.env.NBEnv
import org.veupathdb.lib.container.jaxrs.config.Options

object ServiceOptions : Options() {

  /**
   * Admin auth key.
   */
  val adminAuthKey = NBEnv.require("ADMIN_AUTH_TOKEN")

  /**
   * Hostname of the database backing the job queue.
   */
  val queueDBHost = NBEnv.require("QUEUE_DB_HOST")

  /**
   * Database name of the database backing the job queue.
   */
  val queueDBName = NBEnv.require("QUEUE_DB_NAME")

  /**
   * Host port of the database backing the job queue.
   */
  val queueDBPort = NBEnv.require("QUEUE_DB_PORT") { it.toInt() }

  /**
   * Username used to connect to the database backing the job queue.
   */
  val queueDBUsername = NBEnv.require("QUEUE_DB_USERNAME")

  /**
   * Password used to connect to the database backing the job queue.
   */
  val queueDBPassword = NBEnv.require("QUEUE_DB_PASSWORD")

  /**
   * Connection pool size for connections to the database backing the job queue.
   */
  val queueDBPoolSize = NBEnv.require("QUEUE_DB_POOL_SIZE") { it.toInt() }

  //////////////////////////////////////////////////////////////////////////////

  /**
   * Hostname of the job queue.
   */
  val jobQueueHost = NBEnv.require("JOB_QUEUE_HOST")

  /**
   * Username for the job queue.
   */
  val jobQueueUsername = NBEnv.require("JOB_QUEUE_USERNAME")

  /**
   * Password for the job queue.
   */
  val jobQueuePassword = NBEnv.require("JOB_QUEUE_PASSWORD")

  /**
   * Host port for the job queue.
   */
  val jobQueuePort = NBEnv.require("JOB_QUEUE_PORT") { it.toInt() }

  /**
   * Name of the job queue.
   */
  val jobQueueName = NBEnv.require("JOB_QUEUE_NAME")

  /**
   * Number of worker threads for the job queue.
   */
  val jobQueueWorkers = NBEnv.require("JOB_QUEUE_WORKERS") { it.toInt() }

  //////////////////////////////////////////////////////////////////////////////

  /**
   * Hostname of the S3 instance this service will connect to.
   */
  val s3Host = NBEnv.require("S3_HOST")

  /**
   * Name of the S3 bucket assigned to this service.
   */
  val s3Bucket = NBEnv.require("S3_BUCKET")

  /**
   * Access token for connecting to S3.
   */
  val s3AccessToken = NBEnv.require("S3_ACCESS_TOKEN")

  /**
   * Secret key for connecting to S3.
   */
  val s3SecretKey = NBEnv.require("S3_SECRET_KEY")

  /**
   * Host port for connecting to S3.
   */
  val s3Port = NBEnv.require("S3_PORT") { it.toInt() }

  /**
   * Whether to use HTTPS when connecting to S3.
   */
  val s3UseHttps = NBEnv.require("S3_USE_HTTPS") { it.toBoolean() }

  //////////////////////////////////////////////////////////////////////////////

  /**
   * Number of days a job may live in the cache before being subject to
   * automatic pruning.
   */
  val jobCacheTimeoutDays = NBEnv.require("JOB_CACHE_TIMEOUT_DAYS") { it.toInt() }

  //////////////////////////////////////////////////////////////////////////////

  val queryServiceHost = "query-service"
}