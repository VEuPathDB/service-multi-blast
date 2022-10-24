package mblast.migration.ldap


fun ParseDNString(raw: String): DistinguishedName =
  raw.splitToSequence(',')
    .map { ParseDNComponent(it) }
    .toList()
    .toTypedArray()
    .let { BaseDNImpl(it) }

fun ParseDNComponent(raw: String) =
  when {
    raw.startsWith("cn=") || raw.startsWith("CN=") -> CommonName(raw.substring(3))
    raw.startsWith("dc=") || raw.startsWith("DC=") -> DomainComponent(raw.substring(3))
    raw.startsWith("ou=") || raw.startsWith("OU=") -> OrganizationalUnit(raw.substring(3))
    else                                           -> throw IllegalArgumentException()
  }


sealed interface DistinguishedName {
  val size: Int

  operator fun get(i: Int): DNComponent
}

@JvmInline
value class BaseDNImpl(val value: Array<DNComponent>) : DistinguishedName {

  override val size: Int
    get() = value.size

  override fun get(i: Int) = value[i]

  override fun toString(): String {
    if (value.isEmpty())
      return ""

    val out = StringBuilder(512)
    out.append(value[0])

    for (i in 1 until value.size) {
      out.append(',')
      out.append(value[i])
    }

    return out.toString()
  }
}


sealed interface DNComponent

@JvmInline
value class CommonName(val value: String) : DNComponent {
  override fun toString() = "cn=$value"
}

@JvmInline
value class OrganizationalUnit(val value: String) : DNComponent {
  override fun toString() = "ou=$value"
}

@JvmInline
value class DomainComponent(val value: String) : DNComponent {
  override fun toString() = "dc=$value"
}

