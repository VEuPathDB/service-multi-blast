package org.veupathdb.service.multiblast.model.blast;

public class Dust
{
  private final short level;
  private final short window;
  private final short linker;

  public Dust(int level, int window, int linker) {
    this.level  = (short) level;
    this.window = (short) window;
    this.linker = (short) linker;
  }

  public short getLevel() {
    return level;
  }

  public short getWindow() {
    return window;
  }

  public short getLinker() {
    return linker;
  }

  /**
   * This method parses the input value, performing no validation checks.
   *
   * @param value Value to parse
   *
   * @return New Dust instance.
   */
  public static Dust unsafeFromString(String value) {
    var split = value.split(" +");

    return new Dust(
      Short.parseShort(split[0]),
      Short.parseShort(split[1]),
      Short.parseShort(split[2])
    );
  }
}