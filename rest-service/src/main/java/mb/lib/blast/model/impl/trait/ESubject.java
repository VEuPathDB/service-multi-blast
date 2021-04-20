package mb.lib.blast.model.impl.trait;

import java.io.File;

import mb.lib.blast.model.Location;
import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithSubject;
import mb.api.service.cli.CliBuilder;

public class ESubject implements WithSubject<Void>
{
  private File     subject;
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
