package mb.lib.db

import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager
import java.io.Closeable
import java.sql.Connection

open class MBlastDBManager(
  protected val connection: Connection = DbManager.userDatabase().dataSource.connection
): AutoCloseable, Closeable {

  private val Log = LogManager.getLogger(this::class.java)

  init {
    Log.debug("Opening connection {}", connection)
  }

  override fun close() {
    Log.debug("Closing connection {}", connection)
    connection.close()
  }
}
