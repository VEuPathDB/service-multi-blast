package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import mb.api.model.io.JsonKeys;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.BlastX;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.*;

/**
 * Beta BlastX compatibility overlay.
 * <p>
 * Contains Jackson annotations mapping the legacy config
 */
public class XBlastX extends BlastX
{
  private static final Map<String, BiConsumer<XBlastX, JsonNode>> map = new HashMap<>(){{
    put(Flag.Strand, XBlastX::setStrand);
    put(Flag.QueryGenCode, XBlastX::setQueryGenCode);
    put(Flag.Task, XBlastX::setTask);
    put(Flag.WordSize, XBlastX::setWordSize);
    put(Flag.GapOpen, XBlastX::setGapOpen);
    put(Flag.GapExtend, XBlastX::setGapExtend);
    put(Flag.MaxIntronLength, XBlastX::setMaxIntronLength);
    put(Flag.Matrix, XBlastX::setMatrix);
    put(Flag.Threshold, XBlastX::setThreshold);
    put(Flag.CompBasedStats, XBlastX::setCompBasedStats);
    put(Flag.SubjectFile, XBlastX::setSubjectFile);
    put(Flag.SubjectLocation, XBlastX::setSubjectLocation);
    put(Flag.Seg, XBlastX::setSeg);
    put(Flag.DBSoftMask, XBlastX::setDBSoftMask);
    put(Flag.DBHardMask, XBlastX::setDBHardMask);
    put(Flag.CullingLimit, XBlastX::setCullingLimit);
    put(Flag.SumStats, XBlastX::setSumStats);
    put(Flag.ExtensionDropoffPrelimGapped, XBlastX::setExtensionDropoffPrelimGapped);
    put(Flag.ExtensionDropoffFinalGapped, XBlastX::setExtensionDropoffFinalGapped);
    put(Flag.UngappedAlignmentsOnly, XBlastX::setUngappedAlignmentsOnly);
    put(Flag.NumThreads, XBlastX::setNumThreads);
    put(Flag.UseSmithWatermanTraceback, XBlastX::setUseSmithWatermanTraceback);
    put(Flag.BestHitOverhang, XBlastX::setBestHitOverhang);
    put(Flag.BestHitScoreEdge, XBlastX::setBestHitScoreEdge);
    put(Flag.SubjectBestHit, XBlastX::setSubjectBestHit);
  }};

  @JsonGetter(JsonKeys.Tool)
  public BlastTool tool() {
    return super.getTool();
  }

  public void setStrand(JsonNode j) {
    super.setStrand(Strand.fromString(j.asText()));
  }

  public void setQueryGenCode(JsonNode j) {
    super.setQueryGenCode((short) j.asInt());
  }

  public void setTask(JsonNode j) {
    super.setTask(BlastXTask.fromString(j.asText()));
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

  public static BlastX fromLegacyJSON(ArrayNode node) {
    var out = new XBlastX();
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
      if (XBlastWithIPGList.fromLegacyJSON(out, key, val))
        continue;

      map.get(key).accept(out, val);
    }

    return out;
  }
}
