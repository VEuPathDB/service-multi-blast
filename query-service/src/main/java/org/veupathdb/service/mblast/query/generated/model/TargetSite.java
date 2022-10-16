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

  @JsonProperty("Vectorbase")
  VECTORBASE("Vectorbase"),

  @JsonProperty("VEuPathDB")
  VEUPATHDB("VEuPathDB");

  public final String name;

  TargetSite(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
