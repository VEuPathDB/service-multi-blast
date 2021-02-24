package mb.lib.db.model;

import java.io.File;

public interface FullJobRow extends ShortJobRow
{
  /**
   * @return the blast tool configuration submitted for this job.
   */
  String config();

  /**
   * @return the raw query submitted for this job.
   */
  File query();
}
