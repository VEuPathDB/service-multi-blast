package mb.lib.db

import org.veupathdb.lib.container.jaxrs.utils.db.DbManager
import java.io.Closeable
import java.sql.Connection

open class MBlastDBManager(
  protected val connection: Connection = DbManager.userDatabase().dataSource.connection
): AutoCloseable, Closeable {
  override fun close() {
    connection.close()
  }
}
