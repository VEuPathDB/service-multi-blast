package org.veupathdb.service.multiblast.model.blast.impl;

import java.math.BigDecimal;
import java.util.Objects;

import org.veupathdb.service.multiblast.model.blast.Seg;

public class SegImpl implements Seg
{
  public static final String stringFormat = "%d %s %s";

  private int        window;
  private BigDecimal locut;
  private BigDecimal hicut;

  public SegImpl(int window, double locut, double hicut) {
    this.window = window;
    this.locut  = new BigDecimal(locut);
    this.hicut  = new BigDecimal(hicut);
  }

  public SegImpl(int window, BigDecimal locut, BigDecimal hicut) {
    this.window = window;
    this.locut  = locut;
    this.hicut  = hicut;
  }

  @Override
  public int getWindow() {
    return window;
  }

  @Override
  public double getLowCut() {
    return locut.doubleValue();
  }

  @Override
  public double getHighCut() {
    return hicut.doubleValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SegImpl seg = (SegImpl) o;
    return window == seg.window &&
      Objects.equals(locut, seg.locut) &&
      Objects.equals(hicut, seg.hicut);
  }

  @Override
  public int hashCode() {
    return Objects.hash(window, locut, hicut);
  }

  @Override
  public String toString() {
    return String.format(stringFormat, window, locut, hicut);
  }

  public static Seg fromString(String value) {
    var split = value.split(" +");
    return new SegImpl(
      Integer.parseInt(split[0]),
      new BigDecimal(split[1]),
      new BigDecimal(split[2])
    );
  }
}
