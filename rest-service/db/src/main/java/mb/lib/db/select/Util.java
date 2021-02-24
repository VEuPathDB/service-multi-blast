package mb.lib.db.select;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.UUID;

import mb.lib.db.constants.Column;

public class Util
{
  static File queryToFile(ResultSet rs) throws Exception {
    var queryClob = rs.getClob(Column.MultiBlastJobs.Query);
    var queryFile = new File("/tmp/" + UUID.randomUUID().toString());

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
