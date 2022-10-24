package mblast.migration.ldap

import java.util.*

sealed interface DistinguishedNameBuilder {
  fun prepend(value: DistinguishedName): DistinguishedNameBuilder
  fun append(value: DistinguishedName): DistinguishedNameBuilder

  fun prepend(value: DNComponent): DistinguishedNameBuilder
  fun append(value: DNComponent): DistinguishedNameBuilder

  fun prependCommonName(value: String): DistinguishedNameBuilder
  fun appendCommonName(value: String): DistinguishedNameBuilder

  fun prependOrganizationalUnit(value: String): DistinguishedNameBuilder
  fun appendOrganizationalUnit(value: String): DistinguishedNameBuilder

  fun prependDomainComponent(value: String): DistinguishedNameBuilder
  fun appendDomainComponent(value: String): DistinguishedNameBuilder

  fun build(): DistinguishedName
}

internal class BaseDNBuilderImpl : DistinguishedNameBuilder {
  val comps = LinkedList<DNComponent>()

  override fun prepend(value: DistinguishedName): DistinguishedNameBuilder {
    for (i in 0 until value.size)
      comps.addFirst(value[i])
    return this
  }

  override fun append(value: DistinguishedName): DistinguishedNameBuilder {
    for (i in 0 until value.size)
      comps.addLast(value[i])
    return this
  }

  override fun prepend(value: DNComponent): DistinguishedNameBuilder {
    comps.addFirst(value)
    return this
  }

  override fun append(value: DNComponent): DistinguishedNameBuilder {
    comps.addLast(value)
    return this
  }

  override fun prependCommonName(value: String): DistinguishedNameBuilder {
    comps.addFirst(CommonName(value))
    return this
  }

  override fun appendCommonName(value: String): DistinguishedNameBuilder {
    comps.addLast(CommonName(value))
    return this
  }

  override fun prependOrganizationalUnit(value: String): DistinguishedNameBuilder {
    comps.addFirst(OrganizationalUnit(value))
    return this
  }

  override fun appendOrganizationalUnit(value: String): DistinguishedNameBuilder {
    comps.add(OrganizationalUnit(value))
    return this
  }

  override fun prependDomainComponent(value: String): DistinguishedNameBuilder {
    comps.add(DomainComponent(value))
    return this
  }

  override fun appendDomainComponent(value: String): DistinguishedNameBuilder {
    comps.add(DomainComponent(value))
    return this
  }

  override fun build(): DistinguishedName {
    return BaseDNImpl(comps.toTypedArray())
  }
}