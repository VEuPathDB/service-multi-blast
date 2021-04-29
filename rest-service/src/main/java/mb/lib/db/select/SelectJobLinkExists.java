package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.model.HashID;

import static mb.lib.db.constants.SQL.Select.MultiBlastJobToJobs.LinkExists;

public class SelectJobLinkExists
{
  private final Connection con;
  private final byte[]     parent;
  private final byte[]     child;

  public SelectJobLinkExists(Connection con, HashID parent, HashID child) {
    this.con    = con;
    this.parent = parent.bytes();
    this.child  = child.bytes();
  }

  public boolean run() throws Exception {
    return new BasicPreparedReadQuery<>(LinkExists, con, this::parse, this::prepare)
      .execute()
      .getValue();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, parent);
    ps.setBytes(2, child);
  }

  private boolean parse(ResultSet rs) throws Exception {
    return rs.next();
  }
}