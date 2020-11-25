package org.veupathdb.service.multiblast.model.blast;

public interface Location
{
  long getStart();
  Location setStart(long start);

  long getStop();
  Location setStop(long stop);
}
