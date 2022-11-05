package e2e.conf

import java.util.*

const val E2E_CONFIG_PROP_CONTENTS = """
service.host=http://mblast.local.apidb.org
service.port=80

auth.token=
"""

data class E2EConfig(
  val serviceHost: String,
  val servicePort: Int,
  val authToken: String,
)

fun Properties.toE2EConfig(): E2EConfig {
  return E2EConfig(
    require("service.host"),
    require("service.port").toInt(),
    require("auth.token")
  )
}

fun Properties.require(key: String): String {
  val value = getProperty(key)

  if (value.isNullOrBlank())
    throw IllegalStateException("Required property $key is absent or blank.")

  return value
}
