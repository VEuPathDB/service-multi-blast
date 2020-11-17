package org.veupathdb.service.multiblast.model.blast;

public class QueryLocation
{
  private final long start;
  private final long stop;

  public QueryLocation(long start, long stop) {
    this.start = start;
    this.stop  = stop;
  }

  public long getStart() {
    return start;
  }

  public long getStop() {
    return stop;
  }

  @Override
  public String toString() {
    return start + "-" + stop;
  }

  public static QueryLocation unsafeFromString(String value) {
    var pos = value.indexOf('-');
    return new QueryLocation(
      Integer.parseInt(value.substring(0, pos)),
      Integer.parseInt(value.substring(pos+1))
    );
  }
}
