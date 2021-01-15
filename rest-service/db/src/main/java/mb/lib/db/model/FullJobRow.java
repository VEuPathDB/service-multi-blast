package mb.lib.db.model;

public interface FullJobRow extends ShortJobRow
{
  /**
   * @return the blast tool configuration submitted for this job.
   */
  String config();
}
