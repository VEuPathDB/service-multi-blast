package mb.lib.db.model;

public interface ShortUserJobRow extends ShortJobRow
{
  long userID();
  String description();
}