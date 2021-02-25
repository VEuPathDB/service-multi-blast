package org.veupathdb.service.multiblast.service.conv;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    return Arrays.stream(val).mapToObj(Integer.class::cast).collect(Collectors.toList());
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

  static IOBlastCompBasedStats toExternal(CompBasedStats val) {
    if (val == null)
      return null;

    return switch (val) {
      case None -> IOBlastCompBasedStats.NONE;
      case CompBasedStats -> IOBlastCompBasedStats.COMPBASEDSTATS;
      case ConditionalScoreAdjustment -> IOBlastCompBasedStats.CONDITIONALCOMPBASEDSCOREADJUSTMENT;
      case UnconditionalScoreAdjustment -> IOBlastCompBasedStats.UNCONDITIONALCOMPBASEDSCOREADJUSTMENT;
    };
  }

  static IOBlastStrand toExternal(QueryStrand val) {
    if (val == null)
      return null;

    return switch (val) {
      case Both -> IOBlastStrand.BOTH;
      case Minus -> IOBlastStrand.MINUS;
      case Plus -> IOBlastStrand.PLUS;
    };
  }

  static CompBasedStats toInternal(IOBlastCompBasedStats val) {
    if (val == null)
      return null;

    return switch (val) {
      case NONE -> CompBasedStats.None;
      case COMPBASEDSTATS -> CompBasedStats.CompBasedStats;
      case CONDITIONALCOMPBASEDSCOREADJUSTMENT -> CompBasedStats.ConditionalScoreAdjustment;
      case UNCONDITIONALCOMPBASEDSCOREADJUSTMENT -> CompBasedStats.UnconditionalScoreAdjustment;
    };
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

  static QueryStrand toInternal(IOBlastStrand val) {
    if (val == null)
      return null;

    return switch (val) {
      case PLUS -> QueryStrand.Plus;
      case MINUS -> QueryStrand.Minus;
      case BOTH -> QueryStrand.Both;
    };
  }
}
