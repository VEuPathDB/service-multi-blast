package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TargetSite {
  @JsonProperty("AmoebaDB")
  AMOEBADB("AmoebaDB"),

  @JsonProperty("CryptoDB")
  CRYPTODB("CryptoDB"),

  @JsonProperty("FungiDB")
  FUNGIDB("FungiDB"),

  @JsonProperty("GiardiaDB")
  GIARDIADB("GiardiaDB"),

  @JsonProperty("HostDB")
  HOSTDB("HostDB"),

  @JsonProperty("MicrosporidiaDB")
  MICROSPORIDIADB("MicrosporidiaDB"),

  @JsonProperty("PiroplasmaDB")
  PIROPLASMADB("PiroplasmaDB"),

  @JsonProperty("PlasmoDB")
  PLASMODB("PlasmoDB"),

  @JsonProperty("ToxoDB")
  TOXODB("ToxoDB"),

  @JsonProperty("TrichDB")
  TRICHDB("TrichDB"),

  @JsonProperty("TriTrypDB")
  TRITRYPDB("TriTrypDB"),

  @JsonProperty("VectorBase")
  VECTORBASE("VectorBase"),

  @JsonProperty("VEuPathDB")
  VEUPATHDB("VEuPathDB");

  private final String value;

  public String getValue() {
    return this.value;
  }

  TargetSite(String name) {
    this.value = name;
  }
}
