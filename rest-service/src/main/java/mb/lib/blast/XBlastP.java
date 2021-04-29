package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.veupathdb.lib.blast.BlastP;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.BlastPTask;
import org.veupathdb.lib.blast.field.Location;
import org.veupathdb.lib.blast.field.Seg;

/**
 * Beta BlastP compatibility overlay.
 * <p>
 * Contains Jackson annotations mapping the legacy config
 */
public class XBlastP extends BlastP
{
  private static final Map<String, BiConsumer<XBlastP, JsonNode>> map = new HashMap<>(){{
    put(Flag.Task, XBlastP::setTask);
    put(Flag.WordSize, XBlastP::setWordSize);
    put(Flag.GapOpen, XBlastP::setGapOpen);
    put(Flag.GapExtend, XBlastP::setGapExtend);
    put(Flag.Matrix, XBlastP::setMatrix);
    put(Flag.Threshold, XBlastP::setThreshold);
    put(Flag.CompBasedStats, XBlastP::setCompBasedStats);
    put(Flag.SubjectFile, XBlastP::setSubjectFile);
    put(Flag.SubjectLocation, XBlastP::setSubjectLocation);
    put(Flag.Seg, XBlastP::setSeg);
    put(Flag.DBSoftMask, XBlastP::setDBSoftMask);
    put(Flag.DBHardMask, XBlastP::setDBHardMask);
    put(Flag.CullingLimit, XBlastP::setCullingLimit);
    put(Flag.ExtensionDropoffPrelimGapped, XBlastP::setExtensionDropoffPrelimGapped);
    put(Flag.ExtensionDropoffFinalGapped, XBlastP::setExtensionDropoffFinalGapped);
    put(Flag.UngappedAlignmentsOnly, XBlastP::setUngappedAlignmentsOnly);
    put(Flag.NumThreads, XBlastP::setNumThreads);
    put(Flag.UseSmithWatermanTraceback, XBlastP::setUseSmithWatermanTraceback);
    put(Flag.BestHitOverhang, XBlastP::setBestHitOverhang);
    put(Flag.BestHitScoreEdge, XBlastP::setBestHitScoreEdge);
    put(Flag.SubjectBestHit, XBlastP::setSubjectBestHit);
  }};

  public void setTask(JsonNode j) {
    super.setTask(BlastPTask.fromString(j.textValue()));
  }

  public void setWordSize(JsonNode j) {
    super.setWordSize(j.longValue());
  }

  public void setGapOpen(JsonNode j) {
    super.setGapOpen(j.intValue());
  }

  public void setGapExtend(JsonNode j) {
    super.setGapExtend(j.intValue());
  }

  public void setMatrix(JsonNode j) {
    super.setMatrix(j.textValue());
  }

  public void setThreshold(JsonNode j) {
    super.setThreshold(j.doubleValue());
  }

  public void setCompBasedStats(JsonNode j) {
    super.setCompBasedStats(j.textValue());
  }

  public void setSubjectFile(JsonNode j) {
    super.setSubjectFile(j.textValue());
  }

  public void setSubjectLocation(JsonNode j) {
    super.setSubjectLocation(Location.fromString(j.textValue()));
  }

  public void setSeg(JsonNode j) {
    super.setSeg(Seg.fromString(j.textValue()));
  }

  public void setDBSoftMask(JsonNode j) {
    super.setDBSoftMask(j.textValue());
  }

  public void setDBHardMask(JsonNode j) {
    super.setDBHardMask(j.textValue());
  }

  public void setCullingLimit(JsonNode j) {
    super.setCullingLimit(j.longValue());
  }

  public void setExtensionDropoffPrelimGapped(JsonNode j) {
    super.setExtensionDropoffPrelimGapped(j.doubleValue());
  }

  public void setExtensionDropoffFinalGapped(JsonNode j) {
    super.setExtensionDropoffFinalGapped(j.doubleValue());
  }

  public void setUngappedAlignmentsOnly(JsonNode j) {
    super.setUngappedAlignmentsOnly(j.booleanValue());
  }

  public void setNumThreads(JsonNode j) {
    super.setNumThreads(j.shortValue());
  }

  public void setUseSmithWatermanTraceback(JsonNode j) {
    super.setUseSmithWatermanTraceback(j.booleanValue());
  }

  public void setBestHitOverhang(JsonNode j) {
    super.setBestHitOverhang(j.doubleValue());
  }

  public void setBestHitScoreEdge(JsonNode j) {
    super.setBestHitScoreEdge(j.doubleValue());
  }

  public void setSubjectBestHit(JsonNode j) {
    super.setSubjectBestHit(j.booleanValue());
  }

  public static BlastP fromLegacyJSON(ArrayNode node) {
    var out = new XBlastP();
    var it  = node.elements();

    while (it.hasNext()) {
      var arr = it.next();
      var key = arr.get(0).textValue();
      var val = arr.get(1);

      if (XCLIBase.fromLegacyJSON(out, key, val))
        continue;
      if (XBlastBase.fromLegacyJSON(out, key, val))
        continue;
      if (XBlastWithLists.fromLegacyJSON(out, key, val))
        continue;
      if (XBlastWithIPGList.fromLegacyJSON(out, key, val))
        continue;

      map.get(key).accept(out, val);
    }

    return out;
  }
}
