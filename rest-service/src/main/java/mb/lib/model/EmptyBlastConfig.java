package mb.lib.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.veupathdb.lib.blast.BlastConfig;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.consts.Key;
import org.veupathdb.lib.blast.field.*;

import java.util.function.Function;

/**
 * Empty Blast Config
 * <p>
 * Represents an invalid blast configuration that may be left over in various
 * places if there is an issue with the config serialization.
 */
public class EmptyBlastConfig implements BlastConfig {
  protected final ObjectNode raw;

  protected JsonNode tmp;

  public EmptyBlastConfig(ObjectNode raw) {
    this.raw = raw;
  }

  @Override
  public BlastTool getTool() {
    return get(Key.Tool, BlastTool::fromJSON);
  }

  @Override
  public Boolean getShortHelp() {
    return getBoolean(Flag.ShortHelp);
  }

  @Override
  public void setShortHelp(Boolean aBoolean) {
    raw.put(Flag.ShortHelp, aBoolean);
  }

  @Override
  public Boolean getLongHelp() {
    return getBoolean(Flag.LongHelp);
  }

  @Override
  public void setLongHelp(Boolean aBoolean) {
    raw.put(Flag.LongHelp, aBoolean);
  }

  @Override
  public Boolean getVersion() {
    return getBoolean(Flag.Version);
  }

  @Override
  public void setVersion(Boolean aBoolean) {
    raw.put(Flag.Version, aBoolean);
  }

  @Override
  public OutFile getOutFile() {
    return fromString(Flag.OutFile, OutFile::new);
  }

  @Override
  public void setOutFile(String s) {
    raw.set(Flag.OutFile, new OutFile(s).toJSON());
  }

  @Override
  public OutFormat getOutFormat() {
    return get(Flag.OutFormat, OutFormat::fromJSON);
  }

  @Override
  public void setOutFormat(OutFormat outFormat) {
    raw.set(Flag.OutFormat, outFormat.toJSON());
  }

  @Override
  public Boolean getShowGIs() {
    return getBoolean(Flag.ShowGIs);
  }

  @Override
  public void setShowGIs(Boolean aBoolean) {
    raw.put(Flag.ShowGIs, aBoolean);
  }

  @Override
  public NumDescriptions getNumDescriptions() {
    return fromLong(Flag.NumDescriptions, NumDescriptions::new);
  }

  @Override
  public void setNumDescriptions(Long aLong) {
    raw.put(Flag.NumDescriptions, aLong);
  }

  @Override
  public NumAlignments getNumAlignments() {
    return fromLong(Flag.NumAlignments, NumAlignments::new);
  }

  @Override
  public void setNumAlignments(Long aLong) {
    raw.put(Flag.NumAlignments, aLong);
  }

  @Override
  public LineLength getLineLength() {
    return get(Flag.LineLength, JsonNode::intValue, LineLength::new);
  }

  @Override
  public void setLineLength(Integer integer) {
    raw.put(Flag.LineLength, integer);
  }

  @Override
  public Boolean getHTML() {
    return getBoolean(Flag.HTML);
  }

  @Override
  public void setHTML(Boolean aBoolean) {
    raw.put(Flag.HTML, aBoolean);
  }

  @Override
  public HitSorting getSortHits() {
    return get(Flag.SortHits, HitSorting::fromJSON);
  }

  @Override
  public void setSortHits(HitSorting hitSorting) {
    raw.set(Flag.SortHits, hitSorting.toJSON());
  }

  @Override
  public HSPSorting getSortHSPs() {
    return get(Flag.SortHSPs, HSPSorting::fromJSON);
  }

  @Override
  public void setSortHSPs(HSPSorting hspSorting) {
    raw.set(Flag.SortHSPs, hspSorting.toJSON());
  }

  @Override
  public MaxTargetSeqs getMaxTargetSequences() {
    return fromLong(Flag.MaxTargetSequences, MaxTargetSeqs::new);
  }

  @Override
  public void setMaxTargetSequences(Long aLong) {
    raw.put(Flag.MaxTargetSequences, aLong);
  }

  @Override
  public Boolean getParseDefLines() {
    return getBoolean(Flag.ParseDefLines);
  }

  @Override
  public void setParseDefLines(Boolean aBoolean) {
    raw.put(Flag.ParseDefLines, aBoolean);
  }

  //
  //
  // Internal
  //
  //

  protected <T> T fromLong(String key, Function<Long, T> fn) {
    return toTmp(key) && !tmp.isNull() ? fn.apply(tmp.longValue()) : null;
  }

  protected <T> T fromString(String key, Function<String, T> fn) {
    return toTmp(key) && !tmp.isNull() ? fn.apply(tmp.textValue()) : null;
  }

  protected Long getLong(String key) {
    return toTmp(key) && !tmp.isNull() ? tmp.longValue() : null;
  }

  protected Boolean getBoolean(String key) {
    return toTmp(key) && !tmp.isNull() ? tmp.booleanValue() : null;
  }

  protected String getString(String key) {
    return toTmp(key) && !tmp.isNull() ? tmp.textValue() : null;
  }

  protected <T> T get(String key, Function<JsonNode, T> fn) {
    return toTmp(key) && !tmp.isNull() ? fn.apply(tmp) : null;
  }

  protected <T, I> T get(String key, Function<JsonNode, I> g, Function<I, T> m) {
    return toTmp(key) && !tmp.isNull() ? m.apply(g.apply(tmp)) : null;
  }

  protected boolean toTmp(String key) {
    return (tmp = raw.get(key)) != null;
  }
}
