package mb.lib.db.model;

import java.time.OffsetDateTime;

public interface JobRow extends Row
{
  String getConfig();

  OffsetDateTime getCreatedOn();
}
