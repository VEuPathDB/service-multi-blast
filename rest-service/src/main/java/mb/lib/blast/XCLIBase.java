package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.veupathdb.lib.blast.CLIBase;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.HSPSorting;
import org.veupathdb.lib.blast.field.HitSorting;
import org.veupathdb.lib.blast.field.OutFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class XCLIBase
{
  interface Appender {
    void append(CLIBase b, JsonNode j);
  }

  private static final Map<String, Appender> map = new HashMap<>(){{
    put(Flag.ShortHelp, XCLIBase::setShortHelp);
    put(Flag.LongHelp, XCLIBase::setLongHelp);
    put(Flag.Version, XCLIBase::setVersion);
    put(Flag.OutFile, XCLIBase::setOutFile);
    put(Flag.OutFormat, XCLIBase::setOutFormat);
    put(Flag.ShowGIs, XCLIBase::setShowGIs);
    put(Flag.NumDescriptions, XCLIBase::setNumDescriptions);
    put(Flag.NumAlignments, XCLIBase::setNumAlignments);
    put(Flag.LineLength, XCLIBase::setLineLength);
    put(Flag.HTML, XCLIBase::setHTML);
    put(Flag.SortHits, XCLIBase::setSortHits);
    put(Flag.SortHSPs, XCLIBase::setSortHSPs);
    put(Flag.MaxTargetSequences, XCLIBase::setMaxTargetSequences);
    put(Flag.ParseDefLines, XCLIBase::setParseDefLines);
  }};

  static boolean fromLegacyJSON(CLIBase base, String key, JsonNode val) {
    var fn = map.get(key);

    if (fn != null) {
      fn.append(base, val);
      return true;
    }

    return false;
  }

  private static void setShortHelp(CLIBase b, JsonNode j) {
    b.setShortHelp(j.asBoolean());
  }

  private static void setLongHelp(CLIBase b, JsonNode j) {
    b.setLongHelp(j.asBoolean());
  }

  private static void setVersion(CLIBase b, JsonNode j) {
    b.setVersion(j.asBoolean());
  }

  private static void setOutFile(CLIBase b, JsonNode j) {
    b.setOutFile(j.asText());
  }

  private static void setOutFormat(CLIBase b, JsonNode j) {
    b.setOutFormat(OutFormat.fromString(j.asText()));
  }

  private static void setShowGIs(CLIBase b, JsonNode j) {
    b.setShowGIs(j.asBoolean());
  }

  private static void setNumDescriptions(CLIBase b, JsonNode j) {
    b.setNumDescriptions(j.asLong());
  }

  private static void setNumAlignments(CLIBase b, JsonNode j) {
    b.setNumAlignments(j.asLong());
  }

  private static void setLineLength(CLIBase b, JsonNode j) {
    b.setLineLength(j.asInt());
  }

  private static void setHTML(CLIBase b, JsonNode j) {
    b.setHTML(j.asBoolean());
  }

  private static void setSortHits(CLIBase b, JsonNode j) {
    b.setSortHits(HitSorting.fromIntValue(j.asInt()));
  }

  private static void setSortHSPs(CLIBase b, JsonNode j) {
    b.setSortHSPs(HSPSorting.fromIntValue(j.asInt()));
  }

  private static void setMaxTargetSequences(CLIBase b, JsonNode j) {
    b.setMaxTargetSequences(j.asLong());
  }

  private static void setParseDefLines(CLIBase b, JsonNode j) {
    b.setParseDefLines(j.asBoolean());
  }
}
