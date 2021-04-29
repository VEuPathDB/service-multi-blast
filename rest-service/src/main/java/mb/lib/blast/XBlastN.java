package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.veupathdb.lib.blast.BlastN;
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

  public void setStrand(JsonNode strand) {
    super.setStrand(Strand.fromString(strand.textValue()));
  }

  public void setTask(JsonNode task) {
    super.setTask(BlastNTask.fromString(task.textValue()));
  }

  public void setWordSize(JsonNode wordSize) {
    super.setWordSize(wordSize.longValue());
  }

  public void setGapOpen(JsonNode gapOpen) {
    super.setGapOpen(gapOpen.intValue());
  }

  public void setGapExtend(JsonNode gapExtend) {
    super.setGapExtend(gapExtend.intValue());
  }

  public void setPenalty(JsonNode penalty) {
    super.setPenalty(penalty.intValue());
  }

  public void setReward(JsonNode reward) {
    super.setReward(reward.longValue());
  }

  public void setUseIndex(JsonNode useIndex) {
    super.setUseIndex(useIndex.booleanValue());
  }

  public void setIndexName(JsonNode indexName) {
    super.setIndexName(indexName.textValue());
  }

  public void setSubjectFile(JsonNode subjectFile) {
    super.setSubjectFile(subjectFile.textValue());
  }

  public void setSubjectLocation(JsonNode subjectLocation) {
    super.setSubjectLocation(Location.fromString(subjectLocation.textValue()));
  }

  public void setDust(JsonNode dust) {
    super.setDust(Dust.fromString(dust.textValue()));
  }

  public void setFilteringDB(JsonNode filteringDB) {
    super.setFilteringDB(filteringDB.textValue());
  }

  public void setWindowMaskerTaxID(JsonNode windowMaskerTaxID) {
    super.setWindowMaskerTaxID(windowMaskerTaxID.intValue());
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
    super.setPercentIdentity(percentIdentity.doubleValue());
  }

  public void setCullingLimit(JsonNode cullingLimit) {
    super.setCullingLimit(cullingLimit.longValue());
  }

  public void setTemplateType(JsonNode templateType) {
    super.setTemplateType(TemplateType.fromString(templateType.textValue()));
  }

  public void setTemplateLength(JsonNode templateLength) {
    super.setTemplateLength(TemplateLength.fromIntValue(templateLength.intValue()));
  }

  public void setSumStats(JsonNode sumStats) {
    super.setSumStats(sumStats.booleanValue());
  }

  public void setExtensionDropoffPrelimGapped(JsonNode extensionDropoffPrelimGapped) {
    super.setExtensionDropoffPrelimGapped(extensionDropoffPrelimGapped.doubleValue());
  }

  public void setExtensionDropoffFinalGapped(JsonNode extensionDropoffFinalGapped) {
    super.setExtensionDropoffFinalGapped(extensionDropoffFinalGapped.doubleValue());
  }

  public void setNonGreedy(JsonNode nonGreedy) {
    super.setNonGreedy(nonGreedy.booleanValue());
  }

  public void setMinRawGappedScore(JsonNode minRawGappedScore) {
    super.setMinRawGappedScore(minRawGappedScore.intValue());
  }

  public void setUngappedAlignmentsOnly(JsonNode ungappedAlignmentsOnly) {
    super.setUngappedAlignmentsOnly(ungappedAlignmentsOnly.booleanValue());
  }

  public void setOffDiagonalRange(JsonNode offDiagonalRange) {
    super.setOffDiagonalRange(offDiagonalRange.longValue());
  }

  public void setNumThreads(JsonNode numThreads) {
    super.setNumThreads(numThreads.shortValue());
  }

  public void setBestHitOverhang(JsonNode bestHitOverhang) {
    super.setBestHitOverhang(bestHitOverhang.doubleValue());
  }

  public void setBestHitScoreEdge(JsonNode bestHitScoreEdge) {
    super.setBestHitScoreEdge(bestHitScoreEdge.doubleValue());
  }

  public void setSubjectBestHit(JsonNode subjectBestHit) {
    super.setSubjectBestHit(subjectBestHit.booleanValue());
  }

  public static BlastN fromLegacyJSON(ArrayNode node) {
    var out = new XBlastN();
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
