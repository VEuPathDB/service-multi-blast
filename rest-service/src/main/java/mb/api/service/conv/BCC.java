package mb.api.service.conv;

import java.util.ArrayList;
import java.util.List;

import mb.api.model.*;
import mb.api.model.blast.IOBlastLocation;
import mb.api.model.blast.IOBlastSegMask;
import mb.api.model.blast.impl.IOBlastLocationImpl;
import mb.api.model.blast.impl.IOBlastSegMaskImpl;
import mb.lib.db.model.JobLink;
import mb.lib.db.model.JobTarget;
import mb.lib.blast.model.Location;
import mb.lib.blast.model.Seg;
import mb.lib.blast.model.impl.LocationImpl;
import mb.lib.blast.model.impl.SegImpl;
import mb.api.service.util.Format;

/**
 * Blast Common Converters
 * <p>
 * Conversion methods for translating types between internal and external forms.
 */
public class BCC
{
  public static IOParentJobLink toExternal(JobLink link) {
    return new IOParentJobLinkImpl()
      .setId(Format.toHexString(link.parentID()))
      .setIndex(link.position());
  }

  public static IOJobTarget toExternal(JobTarget jt) {
    return new IOJobTargetImpl()
      .organism(jt.organism())
      .target(jt.targetFile());
  }

  static List<Integer> arrayToList(int[] val) {
    if (val == null)
      return null;

    var out = new ArrayList<Integer>(val.length);

    for (var i : val)
      out.add(i);

    return out;
  }

  static int[] listToArray(List<Integer> ints) {
    if (ints == null)
      return null;

    return ints.stream().mapToInt(Integer::intValue).toArray();
  }

  static boolean nullToFalse(Boolean b) {
    return b != null && b;
  }

  static IOBlastLocation toExternal(Location loc) {
    if (loc == null)
      return null;

    var out = new IOBlastLocationImpl();

    out.setStart(loc.getStart());
    out.setStop(loc.getStop());

    return out;
  }

  static IOBlastSegMask toExternal(Seg val) {
    if (val == null)
      return null;

    var out = new IOBlastSegMaskImpl();

    out.setWindow(val.getWindow());
    out.setLocut(val.getLowCut());
    out.setHicut(val.getHighCut());

    return out;
  }

  static Seg toInternal(IOBlastSegMask val) {
    if (val == null)
      return null;

    return new SegImpl(val.getWindow(), val.getLocut(), val.getHicut());
  }

  static Location toInternal(IOBlastLocation val) {
    if (val == null) {
      return null;
    }

    return new LocationImpl(val.getStart(), val.getStop());
  }
}
