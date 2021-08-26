package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import mb.api.model.io.JsonKeys;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.TBlastN;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.Location;
import org.veupathdb.lib.blast.field.ScoringMatrix;
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

  @JsonGetter(JsonKeys.Tool)
  public BlastTool tool() {
    return super.getTool();
  }

  // Added to avoid having to set up a custom serialization config for this class.
  public void setTool(BlastTool ignored) {}

  public void setTask(JsonNode j) {
    super.setTask(TBlastNTask.fromString(j.asText()));
  }

  public void setWordSize(JsonNode j) {
    super.setWordSize(j.asLong());
  }

  public void setGapOpen(JsonNode j) {
    super.setGapOpen(j.asInt());
  }

  public void setGapExtend(JsonNode j) {
    super.setGapExtend(j.asInt());
  }

  public void setDBGenCode(JsonNode j) {
    super.setDBGenCode((short) j.asInt());
  }

  public void setMaxIntronLength(JsonNode j) {
    super.setMaxIntronLength(j.asLong());
  }

  public void setMatrix(JsonNode j) {
    super.setMatrix(ScoringMatrix.fromString(j.asText()));
  }

  public void setThreshold(JsonNode j) {
    super.setThreshold(j.asDouble());
  }

  public void setCompBasedStats(JsonNode j) {
    super.setCompBasedStats(j.asText());
  }

  public void setSubjectFile(JsonNode j) {
    super.setSubjectFile(j.asText());
  }

  public void setSubjectLocation(JsonNode j) {
    super.setSubjectLocation(Location.fromString(j.asText()));
  }

  public void setSeg(JsonNode j) {
    super.setSeg(Seg.fromString(j.asText()));
  }

  public void setDBSoftMask(JsonNode j) {
    super.setDBSoftMask(j.asText());
  }

  public void setDBHardMask(JsonNode j) {
    super.setDBHardMask(j.asText());
  }

  public void setCullingLimit(JsonNode j) {
    super.setCullingLimit(j.asLong());
  }

  public void setSumStats(JsonNode j) {
    super.setSumStats(j.asBoolean());
  }

  public void setExtensionDropoffPrelimGapped(JsonNode j) {
    super.setExtensionDropoffPrelimGapped(j.asDouble());
  }

  public void setExtensionDropoffFinalGapped(JsonNode j) {
    super.setExtensionDropoffFinalGapped(j.asDouble());
  }

  public void setUngappedAlignmentsOnly(JsonNode j) {
    super.setUngappedAlignmentsOnly(j.asBoolean());
  }

  public void setNumThreads(JsonNode j) {
    super.setNumThreads((short) j.asInt());
  }

  public void setUseSmithWatermanTraceback(JsonNode j) {
    super.setUseSmithWatermanTraceback(j.asBoolean());
  }

  public void setBestHitOverhang(JsonNode j) {
    super.setBestHitOverhang(j.asDouble());
  }

  public void setBestHitScoreEdge(JsonNode j) {
    super.setBestHitScoreEdge(j.asDouble());
  }

  public void setSubjectBestHit(JsonNode j) {
    super.setSubjectBestHit(j.asBoolean());
  }

  public void setInPSSMFile(JsonNode j) {
    super.setInPSSMFile(j.asText());
  }

  public static TBlastN fromLegacyJSON(ArrayNode node) {
    var out = new XTBlastN();
    var it  = node.elements();

    // Skip the first entry as it is just the blast tool name
    it.next();

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
