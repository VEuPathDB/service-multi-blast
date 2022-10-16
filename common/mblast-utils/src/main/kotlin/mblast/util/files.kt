package mblast.util

import java.io.File
import java.io.Reader
import java.lang.StringBuilder
import java.security.DigestInputStream
import java.security.MessageDigest

inline fun <T> File.useThenDelete(fn: (file: File) -> T): T {
  try {
    return fn(this)
  } finally {
    this.delete()
  }
}

fun MakeTempFile(prefix: String = "", suffix: String = ""): File {
  val sb = StringBuilder(128)

  sb.append("/tmp/")

  if (prefix.isNotBlank())
    sb.append(prefix).append('-')

  sb.append(Thread.currentThread().id)
    .append('-')
    .append(System.nanoTime())

  if (suffix.isNotBlank())
    sb.append('-').append(suffix)

  return File(sb.toString())
}

fun WriteTempFile(content: String, prefix: String = "tmp") =
  MakeTempFile(prefix).also { file ->
    file.bufferedWriter().use {
      it.write(content)
      it.flush()
    }
  }

fun WriteTempFile(content: Reader, prefix: String = "tmp") =
  MakeTempFile(prefix).also { file ->
    file.bufferedWriter().use {
      content.transferTo(it)
      it.flush()
    }
  }
