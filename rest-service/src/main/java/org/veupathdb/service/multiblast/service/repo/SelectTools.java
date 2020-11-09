package org.veupathdb.service.multiblast.service.repo;

import io.vulpine.lib.query.util.basic.BasicStatementListReadQuery;
import org.veupathdb.service.multiblast.model.mapping.BlastTools;

public class SelectTools
{
  public void execute() throws Exception {
    var tools = BlastTools.getInstance();

    new BasicStatementListReadQuery<Void>(
      SQL.Select.Blast.Tools,
      Util::getPgConnection,
      rs -> {
        tools.putRaw(rs.getByte(Column.Blast.Tools.ToolID), rs.getString(Column.Blast.Tools.Name));
        return null;
      }
    ).execute();
  }
}
