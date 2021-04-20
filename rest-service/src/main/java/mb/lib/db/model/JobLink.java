package mb.lib.db.model;

import mb.lib.model.HashID;

public interface JobLink
{
  HashID childID();
  HashID parentID();
  int position();
}
