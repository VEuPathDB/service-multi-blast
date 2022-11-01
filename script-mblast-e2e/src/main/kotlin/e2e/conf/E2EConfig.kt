package e2e.conf

import java.util.*

const val E2E_CONFIG_PROP_CONTENTS = """
service.query.host=localhost
service.query.port=8080

service.report.host=localhost
service.report.port=8081

auth.token=
"""

data class E2EConfig(
  val queryServiceHost: String,
  val queryServicePort: Int,
  val reportServiceHost: String,
  val reportServicePort: Int,
  val authToken: String,
)

fun Properties.toE2EConfig(): E2EConfig {
  return E2EConfig(
    require("service.query.host"),
    require("service.query.port").toInt(),
    require("service.report.host"),
    require("service.report.port").toInt(),
    require("auth.token")
  )
}

fun Properties.require(key: String): String {
  val value = getProperty(key)

  if (value.isNullOrBlank())
    throw IllegalStateException("Required property $key is absent or blank.")

  return value
}
