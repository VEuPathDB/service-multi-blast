package mb.lib.db.select

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery
import mb.lib.db.constants.Column
import mb.lib.db.constants.SQL.Select.Users.IsGuest
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

data class SelectUserIsGuest(
  private val con: Connection,
  private val userID: Long,
) {
  fun run() = BasicPreparedReadQuery(IsGuest, con, this::parse, this::prepare).execute().value!!

  fun parse(rs: ResultSet) = if (!rs.next()) false else rs.getBoolean(Column.Users.IsGuest)

  fun prepare(ps: PreparedStatement) = ps.setLong(1, userID)
}
