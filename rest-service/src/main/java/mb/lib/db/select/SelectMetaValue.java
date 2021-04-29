package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;

import static mb.lib.db.constants.SQL.Select.MultiBlastMeta.ByKey;

public class SelectMetaValue
{
  private final Connection con;
  private final String key;

  public SelectMetaValue(Connection con, String key) {
    this.con = con;
    this.key = key;
  }

  public Optional<String> run() throws Exception {
    return new BasicPreparedReadQuery<>(ByKey, con, this::parse, this::prepare).execute().getValue();
  }

  private Optional<String> parse(ResultSet rs) throws Exception {
    return rs.next() ? Optional.of(rs.getString(1)) : Optional.empty();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setString(1, key);
  }
}
