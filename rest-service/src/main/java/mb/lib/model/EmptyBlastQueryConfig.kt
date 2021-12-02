package mb.lib.model

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastQueryConfig
import org.veupathdb.lib.blast.consts.Flag
import org.veupathdb.lib.blast.field.ExpectValue
import org.veupathdb.lib.blast.field.Location
import org.veupathdb.lib.blast.field.QueryFile

class EmptyBlastQueryConfig(raw: ObjectNode): EmptyBlastConfig(raw), BlastQueryConfig {

  override fun getQueryFile() = fromString(Flag.QueryFile, ::QueryFile)

  override fun setQueryFile(s: String?) {
    raw.put(Flag.QueryFile, s)
  }

  override fun getQueryLocation() = get(Flag.QueryLocation, Location::fromJSON)

  override fun setQueryLocation(location: Location) {
    raw.set<JsonNode>(Flag.QueryLocation, location.toJSON())
  }

  override fun getDBFile() = getString(Flag.DBFile)

  override fun setDBFile(s: String?) {
    raw.put(Flag.DBFile, s)
  }

  override fun getExpectValue() = fromString(Flag.ExpectValue, ::ExpectValue)

  override fun setExpectValue(s: String?) {
    raw.put(Flag.ExpectValue, s)
  }

  override fun getSoftMasking() = getBoolean(Flag.SoftMasking)

  override fun setSoftMasking(aBoolean: Boolean?) {
    raw.put(Flag.SoftMasking, aBoolean)
  }

  override fun getLowercaseMasking() = getBoolean(Flag.LowercaseMasking)

  override fun setLowercaseMasking(aBoolean: Boolean?) {
    raw.put(Flag.LowercaseMasking, aBoolean)
  }

  override fun getEntrezQuery() = getString(Flag.EntrezQuery)

  override fun setEntrezQuery(s: String?) {
    raw.put(Flag.EntrezQuery, s)
  }

  override fun getQueryCoverageHSPPercent() = get(Flag.QueryCoverageHSPPercent, JsonNode::doubleValue)

  override fun setQueryCoverageHSPPercent(aDouble: Double?) {
    raw.put(Flag.QueryCoverageHSPPercent, aDouble)
  }

  override fun getMaxHSPs() = getLong(Flag.MaxHSPs)

  override fun setMaxHSPs(aLong: Long?) {
    raw.put(Flag.MaxHSPs, aLong)
  }

  override fun getDBSize() = get(Flag.DBSize) { s -> s.shortValue().toByte() }

  override fun setDBSize(aByte: Byte?) {
    raw.put(Flag.DBSize, aByte?.toShort())
  }

  override fun getSearchSpace() = get(Flag.SearchSpace, JsonNode::shortValue)

  override fun setSearchSpace(aShort: Short?) {
    raw.put(Flag.SearchSpace, aShort)
  }

  override fun getImportSearchStrategy() = getString(Flag.ImportSearchStrategy)

  override fun setImportSearchStrategy(s: String?) {
    raw.put(Flag.ImportSearchStrategy, s)
  }

  override fun getExportSearchStrategy() = getString(Flag.ExportSearchStrategy)

  override fun setExportSearchStrategy(s: String?) {
    raw.put(Flag.ExportSearchStrategy, s)
  }

  override fun getExtensionDropoffUngapped() = get(Flag.ExtensionDropoffUngapped, JsonNode::doubleValue)

  override fun setExtensionDropoffUngapped(aDouble: Double?) {
    raw.put(Flag.ExtensionDropoffUngapped, aDouble)
  }

  override fun getWindowSize() = getLong(Flag.WindowSize)

  override fun setWindowSize(aLong: Long?) {
    raw.put(Flag.WindowSize, aLong)
  }

  override fun getRemote() = getBoolean(Flag.Remote)

  override fun setRemote(aBoolean: Boolean?) {
    raw.put(Flag.Remote, aBoolean)
  }
}
