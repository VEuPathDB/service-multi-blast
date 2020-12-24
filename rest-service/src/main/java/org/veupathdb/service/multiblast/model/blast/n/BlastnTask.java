package org.veupathdb.service.multiblast.model.blast.n;

import java.util.Optional;

public enum BlastnTask
{
  BlastN("blastn"),
  BlastNShort("blastn-short"),
  DiscontiguousMegablast("dc-megablast"),
  Megablast("megablast"),
  RMBlastN("rmblastn");

  private final String value;

  BlastnTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static BlastnTask fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return e;

    throw new IllegalArgumentException(); // TODO: print actual value
  }

  @Override
  public String toString() {
    return value;
  }
}
