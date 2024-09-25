package mb.lib.dmnd


import mb.lib.query.MBlastQuery
import mb.lib.query.MBlastSubQuery
import org.veupathdb.lib.cli.diamond.DiamondCommand
import java.io.File

internal data class DiamondQuery(val tool: DiamondCommand, private val tmpFile: File) : MBlastQuery {
  override val sequences: List<MBlastSubQuery>
    get() = throw UnsupportedOperationException()

  // validation is done before the DiamondQuery instance is created.
  override fun validate() = null

  override fun getFullQuery() = tmpFile.inputStream()
}
