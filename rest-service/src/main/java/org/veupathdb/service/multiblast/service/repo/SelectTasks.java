package org.veupathdb.service.multiblast.service.repo;

import java.util.HashMap;
import java.util.Map;

import io.vulpine.lib.query.util.basic.BasicStatementListReadQuery;
import org.veupathdb.service.multiblast.model.blast.BlastTool;
import org.veupathdb.service.multiblast.model.mapping.*;

public class SelectTasks
{
  /**
   * Index of Tool ID to relevant task mapping.
   */
  private final Map<Byte, EnumMapping<Byte, ? extends Enum<?>>> maps;

  public SelectTasks(BlastTools tools) {
    maps = new HashMap<>(tools.size());
    tools.forEach(this::sortTool);
  }

  public void execute() throws Exception {
    new BasicStatementListReadQuery<Void>(
      SQL.Select.Blast.Tasks,
      Util::getPgConnection,
      rs -> {
        maps.get(rs.getByte(Column.Blast.Tasks.ToolID))
          .putRaw(
            rs.getByte(Column.Blast.Tasks.TaskID),
            rs.getString(Column.Blast.Tasks.Name)
          );
        return null;
      }
    ).execute();
  }

  /**
   * Populates the {@link #maps} field with an index of task enum containers by
   * tool id.
   *
   * @param id   Tool ID
   * @param tool Tool Value
   */
  private void sortTool(byte id, BlastTool tool) {
    switch (tool) {
      case BLASTN -> maps.putIfAbsent(id, BlastnTasks.getInstance());
      case BLASTP -> maps.putIfAbsent(id, BlastpTasks.getInstance());
      case BLASTX -> maps.putIfAbsent(id, BlastxTasks.getInstance());
      case TBLASTN -> maps.putIfAbsent(id, TblastnTasks.getInstance());

      case PSIBLAST, RPSBLAST, RPSTBLASTN, TBLASTX -> { /* there should be no tasks for these */ }
    }
  }
}
