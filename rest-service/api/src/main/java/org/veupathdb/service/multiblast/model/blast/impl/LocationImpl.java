package org.veupathdb.service.multiblast.model.blast.impl;

import java.util.Objects;

import org.veupathdb.service.multiblast.model.blast.Location;

public class LocationImpl implements Location
{
  public static final char delim = '-';

  private final long start;
  private final long stop;

  public LocationImpl(long start, long stop) {
    this.start = start;
    this.stop  = stop;
  }

  @Override
  public long getStart() {
    return start;
  }

  @Override
  public long getStop() {
    return stop;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LocationImpl location = (LocationImpl) o;
    return start == location.start &&
      stop == location.stop;
  }

  @Override
  public String toString() {
    return String.format("%d%s%d", start, delim, stop);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, stop);
  }

  public static Location fromString(String value) {
    var pos = value.indexOf(delim);
    return new LocationImpl(
      Long.parseLong(value.substring(0, pos)),
      Long.parseLong(value.substring(pos + 1))
    );
  }
}
