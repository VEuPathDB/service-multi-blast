package mb.lib.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.veupathdb.lib.blast.BlastQueryConfig;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.ExpectValue;
import org.veupathdb.lib.blast.field.Location;
import org.veupathdb.lib.blast.field.QueryFile;

public class EmptyBlastQueryConfig extends EmptyBlastConfig implements BlastQueryConfig {

  public EmptyBlastQueryConfig(ObjectNode raw) {
    super(raw);
  }

  @Override
  public QueryFile getQueryFile() {
    return fromString(Flag.QueryFile, QueryFile::new);
  }

  @Override
  public void setQueryFile(String s) {
    raw.put(Flag.QueryFile, s);
  }

  @Override
  public Location getQueryLocation() {
    return get(Flag.QueryLocation, Location::fromJSON);
  }

  @Override
  public void setQueryLocation(Location location) {
    raw.set(Flag.QueryLocation, location.toJSON());
  }

  @Override
  public String getDBFile() {
    return getString(Flag.DBFile);
  }

  @Override
  public void setDBFile(String s) {
    raw.put(Flag.DBFile, s);
  }

  @Override
  public ExpectValue getExpectValue() {
    return fromString(Flag.ExpectValue, ExpectValue::new);
  }

  @Override
  public void setExpectValue(String s) {
    raw.put(Flag.ExpectValue, s);
  }

  @Override
  public Boolean getSoftMasking() {
    return getBoolean(Flag.SoftMasking);
  }

  @Override
  public void setSoftMasking(Boolean aBoolean) {
    raw.put(Flag.SoftMasking, aBoolean);
  }

  @Override
  public Boolean getLowercaseMasking() {
    return getBoolean(Flag.LowercaseMasking);
  }

  @Override
  public void setLowercaseMasking(Boolean aBoolean) {
    raw.put(Flag.LowercaseMasking, aBoolean);
  }

  @Override
  public String getEntrezQuery() {
    return getString(Flag.EntrezQuery);
  }

  @Override
  public void setEntrezQuery(String s) {
    raw.put(Flag.EntrezQuery, s);
  }

  @Override
  public Double getQueryCoverageHSPPercent() {
    return get(Flag.QueryCoverageHSPPercent, JsonNode::doubleValue);
  }

  @Override
  public void setQueryCoverageHSPPercent(Double aDouble) {
    raw.put(Flag.QueryCoverageHSPPercent, aDouble);
  }

  @Override
  public Long getMaxHSPs() {
    return getLong(Flag.MaxHSPs);
  }

  @Override
  public void setMaxHSPs(Long aLong) {
    raw.put(Flag.MaxHSPs, aLong);
  }

  @Override
  public Byte getDBSize() {
    return get(Flag.DBSize, s -> (byte) s.shortValue());
  }

  @Override
  public void setDBSize(Byte aByte) {
    raw.put(Flag.DBSize, aByte);
  }

  @Override
  public Short getSearchSpace() {
    return get(Flag.SearchSpace, JsonNode::shortValue);
  }

  @Override
  public void setSearchSpace(Short aShort) {
    raw.put(Flag.SearchSpace, aShort);
  }

  @Override
  public String getImportSearchStrategy() {
    return getString(Flag.ImportSearchStrategy);
  }

  @Override
  public void setImportSearchStrategy(String s) {
    raw.put(Flag.ImportSearchStrategy, s);
  }

  @Override
  public String getExportSearchStrategy() {
    return getString(Flag.ExportSearchStrategy);
  }

  @Override
  public void setExportSearchStrategy(String s) {
    raw.put(Flag.ExportSearchStrategy, s);
  }

  @Override
  public Double getExtensionDropoffUngapped() {
    return get(Flag.ExtensionDropoffUngapped, JsonNode::doubleValue);
  }

  @Override
  public void setExtensionDropoffUngapped(Double aDouble) {
    raw.put(Flag.ExtensionDropoffUngapped, aDouble);
  }

  @Override
  public Long getWindowSize() {
    return getLong(Flag.WindowSize);
  }

  @Override
  public void setWindowSize(Long aLong) {
    raw.put(Flag.WindowSize, aLong);
  }

  @Override
  public Boolean getRemote() {
    return getBoolean(Flag.Remote);
  }

  @Override
  public void setRemote(Boolean aBoolean) {
    raw.put(Flag.Remote, aBoolean);
  }
}
