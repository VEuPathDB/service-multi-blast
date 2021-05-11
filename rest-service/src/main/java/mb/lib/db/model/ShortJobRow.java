package mb.lib.db.model;

import java.time.OffsetDateTime;

import mb.lib.model.JobStatus;

public interface ShortJobRow extends Row
{
  int queueID();

  OffsetDateTime createdOn();

  OffsetDateTime deleteOn();

  JobStatus status();

  String projectID();
}
