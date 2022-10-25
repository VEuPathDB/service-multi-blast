package api

import java.io.File
import java.util.Properties

object TestProps {
  private val raw = Properties()
    .apply { File("test.properties").bufferedReader().use { load(it) } }

  operator fun get(key: String) = raw[key] as String?

  val authKey
    get() = get("header.auth-key")!!
}