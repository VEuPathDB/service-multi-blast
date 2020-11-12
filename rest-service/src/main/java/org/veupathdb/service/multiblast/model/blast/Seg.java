package org.veupathdb.service.multiblast.model.blast;

public class Seg
{
  private final int    window;
  private final double loCut;
  private final double hiCut;

  public Seg(int window, double loCut, double hiCut) {
    this.window = window;
    this.loCut  = loCut;
    this.hiCut  = hiCut;
  }

  public int getWindow() {
    return window;
  }

  public double getLoCut() {
    return loCut;
  }

  public double getHiCut() {
    return hiCut;
  }

  public static Seg unsafeFromString(String value) {
    var split = value.split(" +");
    return new Seg(
      Integer.parseInt(split[0]),
      Double.parseDouble(split[1]),
      Double.parseDouble(split[2])
    );
  }
}
