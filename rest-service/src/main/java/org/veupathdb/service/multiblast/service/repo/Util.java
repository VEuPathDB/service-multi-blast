package org.veupathdb.service.multiblast.service.repo;

import java.sql.Connection;
import java.sql.SQLException;

import org.veupathdb.service.multiblast.db.PgDbMan;

public class Util
{
  public static Connection getPgConnection() throws SQLException {
    return PgDbMan.getInstance().getDataSource().getConnection();
  }
}
