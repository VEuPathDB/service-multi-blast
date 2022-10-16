package org.veupathdb.service.mblast.query

import io.foxcapades.lib.env.NBEnv
import org.veupathdb.lib.container.jaxrs.config.Options

object ServiceOptions : Options() {

  val queueDBHost     = NBEnv.require("QUEUE_DB_HOST")
  val queueDBName     = NBEnv.require("QUEUE_DB_NAME")
  val queueDBPort     = NBEnv.require("QUEUE_DB_PORT") { it.toInt() }
  val queueDBUsername = NBEnv.require("QUEUE_DB_USERNAME")
  val queueDBPassword = NBEnv.require("QUEUE_DB_PASSWORD")
  val queueDBPoolSize = NBEnv.require("QUEUE_DB_POOL_SIZE") { it.toInt() }

  val jobQueueHost     = NBEnv.require("JOB_QUEUE_HOST")
  val jobQueueUsername = NBEnv.require("JOB_QUEUE_USERNAME")
  val jobQueuePassword = NBEnv.require("JOB_QUEUE_PASSWORD")
  val jobQueuePort     = NBEnv.require("JOB_QUEUE_PORT") { it.toInt() }
  val jobQueueWorkers  = NBEnv.require("JOB_QUEUE_WORKERS") { it.toInt() }

  val s3Host        = NBEnv.require("S3_HOST")
  val s3Bucket      = NBEnv.require("S3_BUCKET")
  val s3AccessToken = NBEnv.require("S3_ACCESS_TOKEN")
  val s3SecretKey   = NBEnv.require("S3_SECRET_KEY")
  val s3Port        = NBEnv.require("S3_PORT") { it.toInt() }

  val jobCacheTimeoutDays = NBEnv.require("JOB_CACHE_TIMEOUT_DAYS") { it.toInt() }

  val siteBuild = NBEnv.require("SITE_BUILD")

  val maxQueriesPerJob   = NBEnv.require("MAX_QUERIES_PER_JOB") { it.toInt() }
  val maxResultsPerQuery = NBEnv.require("MAX_RESULTS_PER_QUERY") { it.toInt() }
  val maxInputQuerySize  = NBEnv.require("MAX_INPUT_QUERY_SIZE") { it.toInt() }
  val maxNASeqSize       = NBEnv.require("MAX_NA_SEQ_SIZE") { it.toInt() }
  val maxAASeqSize       = NBEnv.require("MAX_AA_SEQ_SIZE") { it.toInt() }

  val queueNamePrimary   = NBEnv.require("QUEUES_QUERY_NAME_1")
  val queueNameSecondary = NBEnv.require("QUEUES_QUERY_NAME_2")

}