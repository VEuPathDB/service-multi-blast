package e2e

import e2e.conf.RequireConfiguration
import e2e.test1.RunEndToEndTest1

fun main() {
  println("loading configuration")
  val config = RequireConfiguration()

  RunEndToEndTest1(config)
}
