package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import mb.api.model.io.JsonKeys;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.TBlastX;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.Location;
import org.veupathdb.lib.blast.field.ScoringMatrix;
import org.veupathdb.lib.blast.field.Seg;
import org.veupathdb.lib.blast.field.Strand;

public class XTBlastX extends TBlastX
{
  private static final Map<String, BiConsumer<XTBlastX, JsonNode>> map = new HashMap<>(){{
    put(Flag.Strand, XTBlastX::setStrand);
    put(Flag.QueryGenCode, XTBlastX::setQueryGenCode);
    put(Flag.WordSize, XTBlastX::setWordSize);
    put(Flag.MaxIntronLength, XTBlastX::setMaxIntronLength);
    put(Flag.Matrix, XTBlastX::setMatrix);
    put(Flag.Threshold, XTBlastX::setThreshold);
    put(Flag.DBGenCode, XTBlastX::setDBGenCode);
    put(Flag.SubjectFile, XTBlastX::setSubjectFile);
    put(Flag.SubjectLocation, XTBlastX::setSubjectLocation);
    put(Flag.Seg, XTBlastX::setSeg);
    put(Flag.DBSoftMask, XTBlastX::setDBSoftMask);
    put(Flag.DBHardMask, XTBlastX::setDBHardMask);
    put(Flag.CullingLimit, XTBlastX::setCullingLimit);
    put(Flag.SumStats, XTBlastX::setSumStats);
    put(Flag.NumThreads, XTBlastX::setNumThreads);
    put(Flag.BestHitOverhang, XTBlastX::setBestHitOverhang);
    put(Flag.BestHitScoreEdge, XTBlastX::setBestHitScoreEdge);
    put(Flag.SubjectBestHit, XTBlastX::setSubjectBestHit);
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

  public void setWordSize(JsonNode j) {
    super.setWordSize(j.asLong());
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

  public void setDBGenCode(JsonNode j) {
    super.setDBGenCode((short) j.asInt());
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

  public void setNumThreads(JsonNode j) {
    super.setNumThreads((short) j.asInt());
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

  public static TBlastX fromLegacyJSON(ArrayNode node) {
    var out = new XTBlastX();
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
