package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputTBlastxScoringMatrix {
  @JsonProperty("BLOSUM45")
  BLOSUM45("BLOSUM45"),

  @JsonProperty("BLOSUM50")
  BLOSUM50("BLOSUM50"),

  @JsonProperty("BLOSUM62")
  BLOSUM62("BLOSUM62"),

  @JsonProperty("BLOSUM80")
  BLOSUM80("BLOSUM80"),

  @JsonProperty("BLOSUM90")
  BLOSUM90("BLOSUM90"),

  @JsonProperty("PAM250")
  PAM250("PAM250"),

  @JsonProperty("PAM30")
  PAM30("PAM30"),

  @JsonProperty("PAM70")
  PAM70("PAM70");

  public final String name;

  InputTBlastxScoringMatrix(String name) {
    this.name = name;
  }
}
