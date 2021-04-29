package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.veupathdb.lib.blast.TBlastX;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.Location;
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

  public void setStrand(JsonNode j) {
    super.setStrand(Strand.fromString(j.textValue()));
  }

  public void setQueryGenCode(JsonNode j) {
    super.setQueryGenCode(j.shortValue());
  }

  public void setWordSize(JsonNode j) {
    super.setWordSize(j.longValue());
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

  public void setDBGenCode(JsonNode j) {
    super.setDBGenCode(j.shortValue());
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

  public void setNumThreads(JsonNode j) {
    super.setNumThreads(j.shortValue());
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

  public static TBlastX fromLegacyJSON(ArrayNode node) {
    var out = new XTBlastX();
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
