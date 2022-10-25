package mblast.migration.ldap

import com.unboundid.ldap.sdk.LDAPConnection
import com.unboundid.ldap.sdk.SearchScope
import mblast.migration.db.OracleNetDescription
import org.apache.logging.log4j.LogManager

private val HostMatcherWithProtocol    = Regex("^[^:]*://([^:]+):(\\d+)$")
private val HostMatcherWithoutProtocol = Regex("^([^:]+):(\\d+)$")

class LDAP {
  private val logger = LogManager.getLogger(javaClass)

  private val servers: Array<Pair<String, Int>>

  constructor(fn: LDAPBuilder.() -> Unit) {
    val builder = LDAPBuilderImpl()
    builder.fn()
    servers = builder.hosts

    if (servers.isEmpty())
      throw IllegalStateException("Cannot construct an LDAP instance with no target host URLs.")
  }

  fun lookupSingleOracleNetDesc(fn: DistinguishedNameBuilder.() -> Unit): OracleNetDescription {
    return lookupSingleOracleNetDescFor(BaseDNBuilderImpl().apply(fn).build())
  }

  fun lookupSingleOracleNetDescFor(dn: DistinguishedName): OracleNetDescription {
    logger.info("Looking up orclNetDescString {}", dn)
    val results = getConnection().use {
      it.search(dn.toString(), SearchScope.SUB, "objectclass=orclNetService", "orclNetDescString")
        .searchEntries
    }

    if (results.isEmpty())
      throw IllegalStateException("No orclNetDescString found.")
    else if (results.size > 1)
      throw IllegalStateException("More than one orclNetDescString found.")

    return results[0].getAttribute("orclNetDescString").value
      .let { OracleNetDescription(it) }
  }

  private fun getConnection(): LDAPConnection {
    logger.info("Attempting to establish LDAP connection.")

    for (server in servers) {
      val (url, port) = server
      val connection  = LDAPConnection()

      logger.debug("Trying {}:{}", url, port)

      try {
        connection.connect(url, port, 1000)
        return connection
      } catch (e: Throwable) {
        logger.warn("Failed to connect to {}:{}", url, port)
      }
    }

    throw IllegalStateException("Failed to connect to LDAP")
  }
}

sealed interface LDAPBuilder {
  fun addHost(url: String): LDAPBuilder
  fun addHost(url: String, port: Int): LDAPBuilder
}

private class LDAPBuilderImpl : LDAPBuilder {
  var hosts = arrayOf<Pair<String, Int>>()

  override fun addHost(url: String): LDAPBuilder {
    val match = HostMatcherWithProtocol.matchEntire(url)
      ?: HostMatcherWithoutProtocol.matchEntire(url)
      ?: throw IllegalArgumentException()

    val (_, url, port) = match.groupValues

    hosts = arrayOf(*hosts, url to port.toInt())

    return this
  }

  override fun addHost(url: String, port: Int): LDAPBuilder {
    hosts = arrayOf(*hosts, url to port)
    return this
  }
}

