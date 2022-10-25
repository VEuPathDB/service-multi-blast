package mblast.migration

import java.io.File
import java.util.*

object Config {

  private val ENV = Properties()

  init {
    getEnvFile().bufferedReader().use { ENV.load(it) }
  }

  object Oracle {

    val baseDN = ENV.require("ORACLE_BASE_DN")

    object UserDB {
      val username = ENV.require("USER_DB_USER")
      val password = ENV.require("USER_DB_PASS")
      val ldapName = ENV.require("USER_DB_TNS_NAME")
    }
  }

  object LDAP {
    val servers = ENV.require("LDAP_SERVERS").split(',').toTypedArray()
  }


  private fun Properties.require(name: String) =
    getProperty(name) ?: throw RuntimeException("Missing required value $name")

  private fun getEnvFile(): File {
    val a = File("migration.env")
    if (a.exists())
      return a

    val b = File("script-mblast2-migration/migration.env")
    if (b.exists())
      return b

    throw RuntimeException("Could not find migration.env file.")
  }
}