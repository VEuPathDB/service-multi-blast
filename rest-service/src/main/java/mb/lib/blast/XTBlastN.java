package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.veupathdb.lib.blast.TBlastN;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.Location;
import org.veupathdb.lib.blast.field.Seg;
import org.veupathdb.lib.blast.field.TBlastNTask;

public class XTBlastN extends TBlastN
{
  private static final Map<String, BiConsumer<XTBlastN, JsonNode>> map = new HashMap<>(){{
    put(Flag.Task, XTBlastN::setTask);
    put(Flag.WordSize, XTBlastN::setWordSize);
    put(Flag.GapOpen, XTBlastN::setGapOpen);
    put(Flag.GapExtend, XTBlastN::setGapExtend);
    put(Flag.DBGenCode, XTBlastN::setDBGenCode);
    put(Flag.MaxIntronLength, XTBlastN::setMaxIntronLength);
    put(Flag.Matrix, XTBlastN::setMatrix);
    put(Flag.Threshold, XTBlastN::setThreshold);
    put(Flag.CompBasedStats, XTBlastN::setCompBasedStats);
    put(Flag.SubjectFile, XTBlastN::setSubjectFile);
    put(Flag.SubjectLocation, XTBlastN::setSubjectLocation);
    put(Flag.Seg, XTBlastN::setSeg);
    put(Flag.DBSoftMask, XTBlastN::setDBSoftMask);
    put(Flag.DBHardMask, XTBlastN::setDBHardMask);
    put(Flag.CullingLimit, XTBlastN::setCullingLimit);
    put(Flag.SumStats, XTBlastN::setSumStats);
    put(Flag.ExtensionDropoffPrelimGapped, XTBlastN::setExtensionDropoffPrelimGapped);
    put(Flag.ExtensionDropoffFinalGapped, XTBlastN::setExtensionDropoffFinalGapped);
    put(Flag.UngappedAlignmentsOnly, XTBlastN::setUngappedAlignmentsOnly);
    put(Flag.NumThreads, XTBlastN::setNumThreads);
    put(Flag.UseSmithWatermanTraceback, XTBlastN::setUseSmithWatermanTraceback);
    put(Flag.BestHitOverhang, XTBlastN::setBestHitOverhang);
    put(Flag.BestHitScoreEdge, XTBlastN::setBestHitScoreEdge);
    put(Flag.SubjectBestHit, XTBlastN::setSubjectBestHit);
    put(Flag.InPSSMFile, XTBlastN::setInPSSMFile);
  }};

  public void setTask(JsonNode j) {
    super.setTask(TBlastNTask.fromString(j.textValue()));
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

  public void setDBGenCode(JsonNode j) {
    super.setDBGenCode(j.shortValue());
  }

  public void setMaxIntronLength(JsonNode j) {
    super.setMaxIntronLength(j.longValue());
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

  public void setSumStats(JsonNode j) {
    super.setSumStats(j.booleanValue());
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

  public void setInPSSMFile(JsonNode j) {
    super.setInPSSMFile(j.textValue());
  }

  public static TBlastN fromLegacyJSON(ArrayNode node) {
    var out = new XTBlastN();
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

      map.get(key).accept(out, val);
    }

    return out;
  }
}
