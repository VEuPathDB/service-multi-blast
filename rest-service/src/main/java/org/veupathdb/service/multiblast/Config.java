package org.veupathdb.service.multiblast;

import picocli.CommandLine.Option;
import org.veupathdb.lib.container.jaxrs.config.Options;

public class Config extends Options
{
  private static final String DefaultDbHost = "service-db";
  private static final String DefaultDbName = "postgres";
  private static final int    DefaultDbPort = 5432;

  @Option(
    names = "--svc-db-user",
    required = true,
    arity = "1",
    defaultValue = "${env:SVC_DB_USER}",
    description = "Postgres service database username"
  )
  private String serviceDbUser;

  @Option(
    names = "--svc-db-pass",
    required = true,
    arity = "1",
    defaultValue = "${env:SVC_DB_PASS}",
    description = "Postgres service database password."
  )
  private String serviceDbPass;

  @Option(
    names = "--svc-db-host",
    arity = "1",
    defaultValue = "${env:SVC_DB_HOST}",
    description = "Postgres service database hostname.  Defaults to \"service-db\""
  )
  private String serviceDbHost = DefaultDbHost;

  @Option(
    names = "--svc-db-port",
    arity = "1",
    defaultValue = "${env:SVC_DB_PORT}",
    description = "Postgres service database port.  Defaults to 5432"
  )
  private int serviceDbPort = DefaultDbPort;

  @Option(
    names = "--svc-db-name",
    arity = "1",
    defaultValue = "${env:SVC_DB_NAME}",
    description = "Postgres database name.  Defaults to \"postgres\""
  )
  private String serviceDbName = DefaultDbName;


  public String getServiceDbUser() {
    return serviceDbUser;
  }

  public String getServiceDbPass() {
    return serviceDbPass;
  }

  public String getServiceDbHost() {
    return serviceDbHost;
  }

  public int getServiceDbPort() {
    return serviceDbPort;
  }

  public String getServiceDbName() {
    return serviceDbName;
  }
}
