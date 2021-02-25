package mb.lib.db.model;

import java.time.OffsetDateTime;

public interface ShortJobRow extends Row
{
  int queueID();

  OffsetDateTime createdOn();

  OffsetDateTime deleteOn();

  DBJobStatus status();

  String projectID();
}
