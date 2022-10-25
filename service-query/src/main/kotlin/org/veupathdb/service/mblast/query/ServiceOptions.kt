package org.veupathdb.service.mblast.query

import io.foxcapades.lib.env.NBEnv
import org.veupathdb.lib.container.jaxrs.config.Options

object ServiceOptions : Options() {

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
   * Name of the parent job queue.
   */
  val parentJobQueueName = NBEnv.require("JOB_QUEUE_1_NAME")

  /**
   * Number of worker threads for the parent job queue.
   */
  val parentJobQueueWorkers = NBEnv.require("JOB_QUEUE_1_WORKERS") { it.toInt() }

  /**
   * Name of the child job queue.
   */
  val childJobQueueName = NBEnv.require("JOB_QUEUE_2_NAME")

  /**
   * Number of worker threads for the child job queue.
   */
  val childJobQueueWorkers = NBEnv.require("JOB_QUEUE_2_WORKERS") { it.toInt() }

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

  /**
   * Name of the site build directory (i.e. `build-56`)
   */
  val siteBuildDir = NBEnv.require("SITE_BUILD_DIR")

  /**
   * Root directory of the blast DB directory structure.
   */
  val blastDBRootDir = NBEnv.require("BLAST_DB_ROOT_DIR")

  /**
   * Maximum number of sequences in a single mblast job.
   */
  val maxQueriesPerJob = NBEnv.require("MAX_SEQUENCES_PER_JOB") { it.toInt() }

  /**
   * Maximum number of results allowed per job.
   */
  val maxResultsPerQuery = NBEnv.require("MAX_RESULTS_PER_QUERY") { it.toInt() }

  /**
   * Maximum size (in bytes) of an uploaded query.
   */
  val maxInputQuerySize = NBEnv.require("MAX_INPUT_QUERY_SIZE") { it.toInt() }

  /**
   * Maximum size (in bytes) of a single nucleotide sequence.
   */
  val maxNASeqSize = NBEnv.require("MAX_NA_SEQ_SIZE") { it.toInt() }

  /**
   * Maximum size (in bytes) of a single protein sequence.
   */
  val maxAASeqSize = NBEnv.require("MAX_AA_SEQ_SIZE") { it.toInt() }


}