package mb.lib.db.model;

import java.util.List;

public interface JobTarget extends Row
{
  String organism();
  String targetFile();
}
