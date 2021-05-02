package mb.lib.db.select;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Clob;
import java.sql.ResultSet;
import java.util.UUID;

import mb.lib.db.constants.Column;

public class Util
{
  @Deprecated
  public static File queryToFile(ResultSet rs) throws Exception {
    return queryToFile(rs.getClob(Column.MultiBlastJobs.Query));
  }
  public static File queryToFile(Clob queryClob) throws Exception {
    var queryFile = new File("/tmp/" + UUID.randomUUID());

    if (!queryFile.createNewFile()) {
      queryClob.free();
      throw new Exception("Failed to create tmp file for job query.");
    }

    queryFile.deleteOnExit();

    queryClob.getAsciiStream().transferTo(new FileOutputStream(queryFile));
    queryClob.free();

    return queryFile;
  }
}
