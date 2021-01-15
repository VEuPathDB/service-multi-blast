package org.veupathdb.service.multiblast.model.blast.impl.trait;

import java.io.File;

import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithSubject;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class ESubject implements WithSubject<Void>
{
  private File subject;
  private Location location;

  @Override
  public File getSubjectFile() {
    return subject;
  }

  @Override
  public Void setSubjectFile(File f) {
    subject = f;
    return null;
  }

  @Override
  public Location getSubjectLocation() {
    return location;
  }

  @Override
  public Void setSubjectLocation(Location loc) {
    location = loc;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.SubjectFile, subject)
      .appendNonNull(ToolOption.SubjectLocation, location);
  }
}
