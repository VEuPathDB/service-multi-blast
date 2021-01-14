package mb.lib.db.model;

import java.io.InputStream;

public interface FullJobRow extends ShortJobRow
{
  /**
   * @return the blast query submitted for this job.
   */
  String query();

  /**
   * @return the blast tool configuration submitted for this job.
   */
  String config();
}
