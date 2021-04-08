package org.veupathdb.service.multiblast.service.conv;

import java.util.ArrayList;
import java.util.List;

import mb.lib.db.model.JobLink;
import mb.lib.db.model.JobTarget;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.CompBasedStats;
import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.model.blast.QueryStrand;
import org.veupathdb.service.multiblast.model.blast.Seg;
import org.veupathdb.service.multiblast.model.blast.impl.LocationImpl;
import org.veupathdb.service.multiblast.model.blast.impl.SegImpl;
import org.veupathdb.service.multiblast.util.Format;

/**
 * Blast Common Converters
 * <p>
 * Conversion methods for translating types between internal and external forms.
 */
public class BCC
{
  public static IOParentJobLink toExternal(JobLink link) {
    return new IOParentJobLinkImpl()
      .setId(Format.toHexString(link.parentHash()))
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
