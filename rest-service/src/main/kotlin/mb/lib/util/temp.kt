package mb.lib.util

import jakarta.ws.rs.InternalServerErrorException
import java.io.File
import java.io.InputStream
import java.util.UUID


inline fun <T> withTempFile(content: InputStream, fn: (File) -> T): T {
  val tempFile = File("/tmp/${UUID.randomUUID()}")
  tempFile.createNewFile() || throw InternalServerErrorException("failed to create temp file for query upload")
  try {
    tempFile.outputStream().buffered().use { content.buffered().transferTo(it) }
    return fn(tempFile)
  } finally {
    tempFile.delete()
  }
}
