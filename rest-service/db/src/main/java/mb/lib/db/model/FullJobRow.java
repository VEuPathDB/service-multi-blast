package mb.lib.db.model;

public interface FullJobRow extends ShortJobRow
{
  /**
   * @return the blast tool configuration submitted for this job.
   */
  String config();

  /**
   * @return the raw query submitted for this job.
   */
  String query();
}
