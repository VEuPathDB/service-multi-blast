package org.veupathdb.service.multiblast.service.conv;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.CompBasedStats;
import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.model.blast.QueryStrand;
import org.veupathdb.service.multiblast.model.blast.Seg;
import org.veupathdb.service.multiblast.model.blast.impl.LocationImpl;
import org.veupathdb.service.multiblast.model.blast.impl.SegImpl;

class BCC
{
  private static final Logger log = LogProvider.logger(BCC.class);

  static List<Integer> arrayToList(int[] val) {
    log.trace("BCC#arrayToList(val={})", val);

    if (val == null)
      return null;

    return Arrays.stream(val).mapToObj(Integer.class::cast).collect(Collectors.toList());
  }

  static int[] listToArray(List<Integer> ints) {
    log.trace("BCC#listToArray(ints={})", ints);

    if (ints == null)
      return null;

    return ints.stream().mapToInt(Integer::intValue).toArray();
  }

  static boolean nullToFalse(Boolean b) {
    log.trace("BCC#nullToFalse(b={})", b);
    return b != null && b;
  }

  static IOBlastLocation toExternal(Location loc) {
    log.trace("BCC#toExternal(loc={})", loc);

    if (loc == null)
      return null;

    var out = new IOBlastLocationImpl();

    out.setStart(loc.getStart());
    out.setStop(loc.getStop());

    return out;
  }

  static IOBlastSegMask toExternal(Seg val) {
    log.trace("BCC#toExternal(val={})", val);

    if (val == null)
      return null;

    var out = new IOBlastSegMaskImpl();

    out.setWindow(val.getWindow());
    out.setLocut(val.getLowCut());
    out.setHicut(val.getHighCut());

    return out;
  }

  static IOBlastCompBasedStats toExternal(CompBasedStats val) {
    log.trace("BCC#toExternal(val={})", val);

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
    log.trace("BCC#toExternal(val={})", val);

    if (val == null)
      return null;

    return switch (val) {
      case Both -> IOBlastStrand.BOTH;
      case Minus -> IOBlastStrand.MINUS;
      case Plus -> IOBlastStrand.PLUS;
    };
  }

  static CompBasedStats toInternal(IOBlastCompBasedStats val) {
    log.trace("BCC#toInternal(val={})", val);

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
    log.trace("BCC#toInternal(val={})", val);

    if (val == null)
      return null;

    return new SegImpl(val.getWindow(), val.getLocut(), val.getHicut());
  }

  static Location toInternal(IOBlastLocation val) {
    log.trace("BCC#toInternal(val={})", val);

    if (val == null) {
      return null;
    }

    return new LocationImpl(val.getStart(), val.getStop());
  }

  static QueryStrand toInternal(IOBlastStrand val) {
    log.trace("BCC#toInternal(val={})", val);

    if (val == null)
      return null;

    return switch (val) {
      case PLUS -> QueryStrand.Plus;
      case MINUS -> QueryStrand.Minus;
      case BOTH -> QueryStrand.Both;
    };
  }
}
