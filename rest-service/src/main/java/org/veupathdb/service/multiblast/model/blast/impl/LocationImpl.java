package org.veupathdb.service.multiblast.model.blast.impl;

import java.util.Objects;

import org.veupathdb.service.multiblast.model.blast.Location;

public class LocationImpl implements Location
{
  public static final char delim = '-';

  private long start;
  private long stop;

  public LocationImpl() {
  }

  public LocationImpl(long start, long stop) {
    this.start = start;
    this.stop  = stop;
  }

  @Override
  public long getStart() {
    return start;
  }

  @Override
  public Location setStart(long start) {
    this.start = start;
    return this;
  }

  @Override
  public long getStop() {
    return stop;
  }

  @Override
  public Location setStop(long stop) {
    this.stop = stop;
    return this;
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
  public int hashCode() {
    return Objects.hash(start, stop);
  }

  public static Location fromString(String value) {
    var pos = value.indexOf(delim);
    return new LocationImpl()
      .setStart(Long.parseLong(value.substring(0, pos)))
      .setStop(Long.parseLong(value.substring(pos + 1)));
  }
}
