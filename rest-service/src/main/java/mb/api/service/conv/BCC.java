package mb.api.service.conv;

import mb.api.model.IOJobTarget;
import mb.api.model.IOJobTargetImpl;
import mb.api.model.IOParentJobLink;
import mb.api.model.IOParentJobLinkImpl;
import mb.lib.db.model.JobLink;
import mb.lib.db.model.JobTarget;

/**
 * Blast Common Converters
 * <p>
 * Conversion methods for translating types between internal and external forms.
 */
public class BCC
{
  public static IOParentJobLink toExternal(JobLink link) {
    return new IOParentJobLinkImpl()
      .setId(link.parentID().string())
      .setIndex(link.position());
  }

  public static IOJobTarget toExternal(JobTarget jt) {
    return new IOJobTargetImpl()
      .organism(jt.organism())
      .target(jt.targetFile());
  }

}
