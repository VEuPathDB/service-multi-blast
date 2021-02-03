package org.veupathdb.service.multiblast.model.blast.impl;

import java.util.Objects;

import org.veupathdb.service.multiblast.model.blast.n.Dust;

public class DustImpl implements Dust
{
  public static final String stringFormat = "%d %d %d";

  private final int level;
  private final int window;
  private final int linker;

  public DustImpl(int level, int window, int linker) {
    this.level  = level;
    this.window = window;
    this.linker = linker;
  }

  @Override
  public int getLevel() {
    return level;
  }

  @Override
  public int getWindow() {
    return window;
  }

  @Override
  public int getLinker() {
    return linker;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DustImpl dust = (DustImpl) o;
    return level == dust.level &&
      window == dust.window &&
      linker == dust.linker;
  }

  @Override
  public int hashCode() {
    return Objects.hash(level, window, linker);
  }

  @Override
  public String toString() {
    return String.format(stringFormat, level, window, linker);
  }

  public static Dust fromString(String value) {
    var split = value.split(" +");
    return new DustImpl(
      Integer.parseInt(split[0]),
      Integer.parseInt(split[1]),
      Integer.parseInt(split[2])
    );
  }
}
