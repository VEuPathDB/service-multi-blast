package mb.lib.query.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mb.lib.model.HashID;

public class JobLinkCollection
{
  public final Map<HashID, List<BlastJobLink>> byParentID = new HashMap<>();
  public final Map<HashID, List<BlastJobLink>> byChildID  = new HashMap<>();
}
