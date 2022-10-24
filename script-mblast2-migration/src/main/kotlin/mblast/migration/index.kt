package mblast.migration

import mblast.migration.db.GetOracleDataSource
import mblast.migration.ldap.LDAP
import mblast.migration.ldap.ParseDNString
import mblast.migration.mig.RunMigration
import org.apache.logging.log4j.LogManager

fun main() {
  println("Connecting to Oracle")
  GetOracleDataSource(
    Config.Oracle.UserDB.username,
    Config.Oracle.UserDB.password,
    LDAP { Config.LDAP.servers.forEach { addHost(it) } }
      .lookupSingleOracleNetDesc {
        appendCommonName(Config.Oracle.UserDB.ldapName)
        appendCommonName("OracleContext")
        append(ParseDNString(Config.Oracle.baseDN))
      }
  ).use {
    println("Starting Migration")
    RunMigration(it)
  }
}
