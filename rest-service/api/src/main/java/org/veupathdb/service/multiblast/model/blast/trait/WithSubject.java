package org.veupathdb.service.multiblast.model.blast.trait;

import java.io.File;

import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithSubject<T> extends CliOptions
{
  File getSubjectFile();
  T setSubjectFile(File f);

  Location getSubjectLocation();
  T setSubjectLocation(Location loc);
}
