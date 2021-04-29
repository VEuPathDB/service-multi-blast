package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.databind.JsonNode;
import org.veupathdb.lib.blast.BlastBase;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.Location;

public class XBlastBase
{
  private static final Map<String, BiConsumer<BlastBase, JsonNode>> map = new HashMap<>(){{
    put(Flag.QueryFile, XBlastBase::setQueryFile);
    put(Flag.QueryLocation, XBlastBase::setQueryLocation);
    put(Flag.DBFile, XBlastBase::setDBFile);
    put(Flag.ExpectValue, XBlastBase::setExpectValue);
    put(Flag.SoftMasking, XBlastBase::setSoftMasking);
    put(Flag.LowercaseMasking, XBlastBase::setLowercaseMasking);
    put(Flag.EntrezQuery, XBlastBase::setEntrezQuery);
    put(Flag.QueryCoverageHSPPercent, XBlastBase::setQueryCoverageHSPPercent);
    put(Flag.MaxHSPs, XBlastBase::setMaxHSPs);
    put(Flag.DBSize, XBlastBase::setDBSize);
    put(Flag.SearchSpace, XBlastBase::setSearchSpace);
    put(Flag.ImportSearchStrategy, XBlastBase::setImportSearchStrategy);
    put(Flag.ExportSearchStrategy, XBlastBase::setExportSearchStrategy);
    put(Flag.ExtensionDropoffUngapped, XBlastBase::setExtensionDropoffUngapped);
    put(Flag.WindowSize, XBlastBase::setWindowSize);
    put(Flag.Remote, XBlastBase::setRemote);
  }};

  static boolean fromLegacyJSON(BlastBase base, String key, JsonNode val) {
    var fn = map.get(key);

    if (fn != null) {
      fn.accept(base, val);
      return true;
    }

    return false;
  }

  public static void setQueryFile(BlastBase b, JsonNode queryFile) {
    b.setQueryFile(queryFile.textValue());
  }

  public static void setQueryLocation(BlastBase b, JsonNode queryLocation) {
    b.setQueryLocation(Location.fromString(queryLocation.textValue()));
  }

  public static void setDBFile(BlastBase b, JsonNode dbFile) {
    b.setDBFile(dbFile.textValue());
  }

  public static void setExpectValue(BlastBase b, JsonNode expectValue) {
    b.setExpectValue(expectValue.textValue());
  }

  public static void setSoftMasking(BlastBase b, JsonNode softMasking) {
    b.setSoftMasking(softMasking.booleanValue());
  }

  public static void setLowercaseMasking(BlastBase b, JsonNode lowercaseMasking) {
    b.setLowercaseMasking(lowercaseMasking.booleanValue());
  }

  public static void setEntrezQuery(BlastBase b, JsonNode entrezQuery) {
    b.setEntrezQuery(entrezQuery.textValue());
  }

  public static void setQueryCoverageHSPPercent(BlastBase b, JsonNode queryCoverageHSPPercent) {
    b.setQueryCoverageHSPPercent(queryCoverageHSPPercent.doubleValue());
  }

  public static void setMaxHSPs(BlastBase b, JsonNode maxHSPs) {
    b.setMaxHSPs(maxHSPs.longValue());
  }

  public static void setDBSize(BlastBase b, JsonNode dbSize) {
    b.setDBSize((byte) dbSize.intValue());
  }

  public static void setSearchSpace(BlastBase b, JsonNode searchSpace) {
    b.setSearchSpace(searchSpace.shortValue());
  }

  public static void setImportSearchStrategy(BlastBase b, JsonNode importSearchStrategy) {
    b.setImportSearchStrategy(importSearchStrategy.textValue());
  }

  public static void setExportSearchStrategy(BlastBase b, JsonNode exportSearchStrategy) {
    b.setExportSearchStrategy(exportSearchStrategy.textValue());
  }

  public static void setExtensionDropoffUngapped(BlastBase b, JsonNode extensionDropoffUngapped) {
    b.setExtensionDropoffUngapped(extensionDropoffUngapped.doubleValue());
  }

  public static void setWindowSize(BlastBase b, JsonNode windowSize) {
    b.setWindowSize(windowSize.longValue());
  }

  public static void setRemote(BlastBase b, JsonNode remote) {
    b.setRemote(remote.booleanValue());
  }
}
