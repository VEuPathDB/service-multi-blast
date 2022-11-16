package org.veupathdb.lib.mblast.utils.tmp

import org.slf4j.LoggerFactory
import java.io.File
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * Base implementation of the `TempFileFactory` interface.
 *
 * @see TempFiles
 * @see CustomTempFileFactory
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  1.0.0
 */
sealed class TempFileFactoryBase(
  override val rootDir: File,
  override val defaultLifetime: Long,
  override val cleanupInterval: Long,
) : TempFileFactory {

  private val logger = LoggerFactory.getLogger(javaClass)

  private val lock = ReentrantLock()

  private val deleteQueue = LinkedList<ExpiringFile>()

  private var started = false

  private var running = true

  private val cleanupThread = Thread {
    while (running) {
      cleanup()
      Thread.sleep(cleanupInterval)
    }
  }

  override fun create(): File = create(defaultLifetime)

  override fun create(lifeTime: Long): File {
    if (!running)
      throw IllegalStateException("Attempted to create an expiring temp file after shutting down the TempFileFactory.")

    maybeStartCleanupThread()

    return lock.withLock {
      var file = File(rootDir, UUID.randomUUID().toString())

      while (file.exists())
        file = File(rootDir, UUID.randomUUID().toString())

      logger.debug("creating temp file {}", file)

      if (!file.createNewFile())
        throw IllegalStateException("Failed to create temp file $file.")

      deleteQueue.addLast(ExpiringFile(System.currentTimeMillis() + lifeTime, file))

      file
    }
  }

  override fun cleanup() {
    logger.debug("starting temp file cleanup")

    var deletedFiles = 0

    val tmp = LinkedList<ExpiringFile>()
    val now = System.currentTimeMillis()

    lock.withLock {
      tmp.forEach {
        if (it.expiration < now) {
          try {
            it.file.delete()
          } catch (e: Throwable) {
            logger.warn("failed to delete temp file {}", it.file)
            logger.error("exception thrown:", e)
          }

          deletedFiles++
        } else {
          tmp.addLast(it)
        }
      }

      deleteQueue.clear()
      deleteQueue.addAll(tmp)
    }

    logger.debug("temp file cleanup completed.  deleted {} files", deletedFiles)
  }

  override fun shutDown() {
    running = false
  }

  private fun maybeStartCleanupThread() {
    if (!started) {
      cleanupThread.start()
      started = true
    }
  }
}

private data class ExpiringFile(val expiration: Long, val file: File)