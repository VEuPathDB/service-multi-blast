package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import mb.api.model.io.JsonKeys;
import org.veupathdb.lib.blast.BlastN;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.*;

/**
 * Beta BlastN compatibility overlay.
 * <p>
 * Contains Jackson annotations mapping the legacy config
 */
public class XBlastN extends BlastN
{
  private static final Map<String, BiConsumer<XBlastN, JsonNode>> map = new HashMap<>(){{
    put(Flag.Strand, XBlastN::setStrand);
    put(Flag.Task, XBlastN::setTask);
    put(Flag.WordSize, XBlastN::setWordSize);
    put(Flag.GapOpen, XBlastN::setGapOpen);
    put(Flag.GapExtend, XBlastN::setGapExtend);
    put(Flag.Penalty, XBlastN::setPenalty);
    put(Flag.Reward, XBlastN::setReward);
    put(Flag.UseIndex, XBlastN::setUseIndex);
    put(Flag.IndexName, XBlastN::setIndexName);
    put(Flag.SubjectFile, XBlastN::setSubjectFile);
    put(Flag.SubjectLocation, XBlastN::setSubjectLocation);
    put(Flag.Dust, XBlastN::setDust);
    put(Flag.FilteringDB, XBlastN::setFilteringDB);
    put(Flag.WindowMaskerTaxID, XBlastN::setWindowMaskerTaxID);
    put(Flag.WindowMaskerDB, XBlastN::setWindowMaskerDB);
    put(Flag.DBSoftMask, XBlastN::setDBSoftMask);
    put(Flag.DBHardMask, XBlastN::setDBHardMask);
    put(Flag.PercentIdentity, XBlastN::setPercentIdentity);
    put(Flag.CullingLimit, XBlastN::setCullingLimit);
    put(Flag.TemplateType, XBlastN::setTemplateType);
    put(Flag.TemplateLength, XBlastN::setTemplateLength);
    put(Flag.SumStats, XBlastN::setSumStats);
    put(Flag.ExtensionDropoffPrelimGapped, XBlastN::setExtensionDropoffPrelimGapped);
    put(Flag.ExtensionDropoffFinalGapped, XBlastN::setExtensionDropoffFinalGapped);
    put(Flag.NonGreedy, XBlastN::setNonGreedy);
    put(Flag.MinRawGappedScore, XBlastN::setMinRawGappedScore);
    put(Flag.UngappedAlignmentsOnly, XBlastN::setUngappedAlignmentsOnly);
    put(Flag.OffDiagonalRange, XBlastN::setOffDiagonalRange);
    put(Flag.NumThreads, XBlastN::setNumThreads);
    put(Flag.BestHitOverhang, XBlastN::setBestHitOverhang);
    put(Flag.BestHitScoreEdge, XBlastN::setBestHitScoreEdge);
    put(Flag.SubjectBestHit, XBlastN::setSubjectBestHit);
  }};

  @JsonGetter(JsonKeys.Tool)
  public BlastTool tool() {
    return super.getTool();
  }

  // Added to avoid having to set up a custom serialization config for this class.
  public void setTool(BlastTool ignored) {}

  public void setStrand(JsonNode strand) {
    super.setStrand(Strand.fromString(strand.asText()));
  }

  public void setTask(JsonNode task) {
    super.setTask(BlastNTask.fromString(task.asText()));
  }

  public void setWordSize(JsonNode wordSize) {
    super.setWordSize(wordSize.asLong());
  }

  public void setGapOpen(JsonNode gapOpen) {
    super.setGapOpen(gapOpen.asInt());
  }

  public void setGapExtend(JsonNode gapExtend) {
    super.setGapExtend(gapExtend.asInt());
  }

  public void setPenalty(JsonNode penalty) {
    super.setPenalty(penalty.asInt());
  }

  public void setReward(JsonNode reward) {
    super.setReward(reward.asLong());
  }

  public void setUseIndex(JsonNode useIndex) {
    super.setUseIndex(useIndex.asBoolean());
  }

  public void setIndexName(JsonNode indexName) {
    super.setIndexName(indexName.asText());
  }

  public void setSubjectFile(JsonNode subjectFile) {
    super.setSubjectFile(subjectFile.asText());
  }

  public void setSubjectLocation(JsonNode subjectLocation) {
    super.setSubjectLocation(Location.fromString(subjectLocation.asText()));
  }

  public void setDust(JsonNode dust) {
    super.setDust(Dust.fromString(dust.asText()));
  }

  public void setFilteringDB(JsonNode filteringDB) {
    super.setFilteringDB(filteringDB.asText());
  }

  public void setWindowMaskerTaxID(JsonNode windowMaskerTaxID) {
    super.setWindowMaskerTaxID(windowMaskerTaxID.asInt());
  }

  public void setWindowMaskerDB(JsonNode windowMaskerDB) {
    super.setWindowMaskerDB(windowMaskerDB.asText());
  }

  public void setDBSoftMask(JsonNode dbSoftMask) {
    super.setDBSoftMask(dbSoftMask.asText());
  }

  public void setDBHardMask(JsonNode dbHardMask) {
    super.setDBHardMask(dbHardMask.asText());
  }

  public void setPercentIdentity(JsonNode percentIdentity) {
    super.setPercentIdentity(percentIdentity.asDouble());
  }

  public void setCullingLimit(JsonNode cullingLimit) {
    super.setCullingLimit(cullingLimit.asLong());
  }

  public void setTemplateType(JsonNode templateType) {
    super.setTemplateType(TemplateType.fromString(templateType.asText()));
  }

  public void setTemplateLength(JsonNode templateLength) {
    super.setTemplateLength(TemplateLength.fromIntValue(templateLength.asInt()));
  }

  public void setSumStats(JsonNode sumStats) {
    super.setSumStats(sumStats.asBoolean());
  }

  public void setExtensionDropoffPrelimGapped(JsonNode extensionDropoffPrelimGapped) {
    super.setExtensionDropoffPrelimGapped(extensionDropoffPrelimGapped.asDouble());
  }

  public void setExtensionDropoffFinalGapped(JsonNode extensionDropoffFinalGapped) {
    super.setExtensionDropoffFinalGapped(extensionDropoffFinalGapped.asDouble());
  }

  public void setNonGreedy(JsonNode nonGreedy) {
    super.setNonGreedy(nonGreedy.asBoolean());
  }

  public void setMinRawGappedScore(JsonNode minRawGappedScore) {
    super.setMinRawGappedScore(minRawGappedScore.asInt());
  }

  public void setUngappedAlignmentsOnly(JsonNode ungappedAlignmentsOnly) {
    super.setUngappedAlignmentsOnly(ungappedAlignmentsOnly.asBoolean());
  }

  public void setOffDiagonalRange(JsonNode offDiagonalRange) {
    super.setOffDiagonalRange(offDiagonalRange.asLong());
  }

  public void setNumThreads(JsonNode numThreads) {
    super.setNumThreads((short) numThreads.asInt());
  }

  public void setBestHitOverhang(JsonNode bestHitOverhang) {
    super.setBestHitOverhang(bestHitOverhang.asDouble());
  }

  public void setBestHitScoreEdge(JsonNode bestHitScoreEdge) {
    super.setBestHitScoreEdge(bestHitScoreEdge.asDouble());
  }

  public void setSubjectBestHit(JsonNode subjectBestHit) {
    super.setSubjectBestHit(subjectBestHit.asBoolean());
  }

  public static BlastN fromLegacyJSON(ArrayNode node) {
    var out = new XBlastN();
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

      var fn = map.get(key);
      if (fn == null)
        throw new RuntimeException("Invalid key: " + key);
      fn.accept(out, val);
    }

    return out;
  }
}
