package mb.lib.blast.model.trait;

import java.io.File;

import mb.lib.blast.model.Location;
import mb.api.service.cli.CliOptions;

public interface WithSubject<T> extends CliOptions
{
  File getSubjectFile();
  T setSubjectFile(File f);

  Location getSubjectLocation();
  T setSubjectLocation(Location loc);
}
