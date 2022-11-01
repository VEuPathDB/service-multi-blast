package e2e.conf

import java.io.File
import java.util.*

private val configFile = File("test-config.properties")


fun RequireConfiguration(): E2EConfig {
  if (!configFile.exists())
    createConfigAndError()

  val props = Properties()
  configFile.reader().use { props.load(it) }

  return props.toE2EConfig()
}


private fun createConfigAndError(): Nothing {
  configFile.createNewFile()
  configFile.bufferedWriter().use {
    it.write(E2E_CONFIG_PROP_CONTENTS)
    it.newLine()
    it.flush()
  }

  println("===================================================================")
  println()
  println("Cannot run end to end tests as the test configuration file was not")
  println("present.")
  println()
  println("This file has been created.  Please edit the file and re-run this")
  println("script.")
  println()
  println("===================================================================")

  throw RuntimeException()
}
