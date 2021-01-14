package mb.lib.db.model;

import java.io.InputStream;

public interface FullJobRow extends ShortJobRow
{
  /**
   * @return the blast tool configuration submitted for this job.
   */
  String config();
}
