package org.veupathdb.service.multiblast.model.blast;

public class QueryLocation
{
  private final int start;
  private final int stop;

  public QueryLocation(int start, int stop) {
    this.start = start;
    this.stop  = stop;
  }

  public int getStart() {
    return start;
  }

  public int getStop() {
    return stop;
  }

  @Override
  public String toString() {
    return start + "-" + stop;
  }

  public static QueryLocation fromString(String value) {
    var pos = value.indexOf('-');
    return new QueryLocation(
      Integer.parseInt(value.substring(0, pos)),
      Integer.parseInt(value.substring(pos+1))
    );
  }
}
