package mb.lib.model

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastQueryConfig
import org.veupathdb.lib.blast.consts.Flag
import org.veupathdb.lib.blast.field.ExpectValue
import org.veupathdb.lib.blast.field.Location
import org.veupathdb.lib.blast.field.QueryFile

class EmptyBlastQueryConfig(raw: ObjectNode): EmptyBlastConfig(raw), BlastQueryConfig {
  override var queryFile: QueryFile?
    get() = fromString(Flag.QueryFile, ::QueryFile)
    set(v) {
      raw.put(Flag.QueryFile, v?.value)
    }

  override var queryLocation: Location?
    get() = TODO("Not yet implemented")
    set(v) {
      raw.set<JsonNode>(Flag.QueryLocation, v?.toJSON())
    }

  override var dbFile
    get() = getString(Flag.DBFile)
    set(s) {
      raw.put(Flag.DBFile, s)
    }

  override var expectValue
    get() = fromString(Flag.ExpectValue, ::ExpectValue)
    set(s) {
      raw.put(Flag.ExpectValue, s?.value)
    }

  override var softMasking
    get() = getBoolean(Flag.SoftMasking)
    set(v) {
      raw.put(Flag.SoftMasking, v)
    }

  override var lowercaseMasking
    get() = getBoolean(Flag.LowercaseMasking)
    set(v) {
      raw.put(Flag.LowercaseMasking, v)
    }

  override var entrezQuery
    get() = getString(Flag.EntrezQuery)
    set(v) {
      raw.put(Flag.EntrezQuery, v)
    }

  override var queryCoverageHSPPercent
    get() = get(Flag.QueryCoverageHSPPercent, JsonNode::doubleValue)
    set(v) {
      raw.put(Flag.QueryCoverageHSPPercent, v)
    }

  override var maxHSPs
    get() = getLong(Flag.MaxHSPs)
    set(v) {
      raw.put(Flag.MaxHSPs, v)
    }

  override var dbSize
    get() = get(Flag.DBSize) { s -> s.shortValue().toByte() }
    set(v) {
      raw.put(Flag.DBSize, v?.toShort())
    }

  override var searchSpace: Short?
    get() = get(Flag.SearchSpace, JsonNode::shortValue)
    set(v) {
      raw.put(Flag.SearchSpace, v)
    }

  override var importSearchStrategy: String?
    get() = getString(Flag.ImportSearchStrategy)
    set(v) {
      raw.put(Flag.ImportSearchStrategy, v)
    }

  override var exportSearchStrategy: String?
    get() = getString(Flag.ExportSearchStrategy)
    set(v) {
      raw.put(Flag.ExportSearchStrategy, v)
    }

  override var extensionDropoffUngapped: Double?
    get() = get(Flag.ExtensionDropoffUngapped, JsonNode::doubleValue)
    set(v) {
      raw.put(Flag.ExtensionDropoffUngapped, v)
    }

  override var windowSize: Long?
    get() = getLong(Flag.WindowSize)
    set(v) {
      raw.put(Flag.WindowSize, v)
    }

  override var remote: Boolean?
    get() = getBoolean(Flag.Remote)
    set(v) {
      raw.put(Flag.Remote, v)
    }
}
