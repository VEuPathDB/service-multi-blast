package org.veupathdb.service.multiblast.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.veupathdb.lib.container.jaxrs.health.DatabaseDependency;
import org.veupathdb.lib.container.jaxrs.providers.DependencyProvider;
import org.veupathdb.service.multiblast.Config;

public class PgDbMan implements AutoCloseable
{
  private static final String
    ERR_NOT_INIT = "Attempted to use the Postgres db manager before it was initialized.",
    ERR_DBL_INIT = "Attempted to re-initialize the Postgres db manager.";

  private static final String
    JDBC_BASE = "jdbc:postgresql://%s:%d/%s";

  private static PgDbMan instance;

  private HikariDataSource ds;

  public static void initialize(Config config) {
    if (instance != null)
      throw new RuntimeException(ERR_DBL_INIT);

    instance = new PgDbMan();
    instance.init(config);
  }

  public static PgDbMan getInstance() {
    if (instance == null)
      throw new RuntimeException(ERR_NOT_INIT);

    return instance;
  }

  PgDbMan() {}

  public HikariDataSource getDataSource() {
    return ds;
  }

  public void close() {
    ds.close();
  }

  void init(Config config) {
    var dbc = new HikariConfig();
    dbc.setDriverClassName("org.postgresql.Driver");
    dbc.setUsername(config.getServiceDbUser());
    dbc.setPassword(config.getServiceDbPass());
    dbc.setJdbcUrl(makeJdbcUrl(
      config.getServiceDbHost(),
      config.getServiceDbPort(),
      config.getServiceDbName()
    ));

    ds = new HikariDataSource(dbc);

    DependencyProvider.getInstance().register(new DatabaseDependency(
      "Service Database",
      config.getServiceDbHost(),
      config.getServiceDbPort(),
      ds
    ));
  }

  static String makeJdbcUrl(String host, int port, String dbName) {
    return String.format(JDBC_BASE, host, port, dbName);
  }
}
