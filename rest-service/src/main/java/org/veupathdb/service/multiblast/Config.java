package org.veupathdb.service.multiblast;

import org.veupathdb.lib.container.jaxrs.config.Options;
import picocli.CommandLine.Option;

public class Config extends Options
{
  private static final String DefaultDbHost       = "service-db";
  private static final String DefaultDbName       = "postgres";
  private static final String DefaultJobQueueHost = "queue";
  private static final String DefaultBlastHost    = "blast";
  private static final String DefaultQueueName    = "blast";
  private static final String DefaultQueueRoute   = "blast";
  private static final int    DefaultDbPort       = 5432;

  private static Config instance;

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

  @Option(
    names = "--mount-path",
    arity = "1",
    required = true,
    defaultValue = "${env:JOB_MOUNT_PATH}",
    description = "Job data directory path."
  )
  private String mountPath;

  @Option(
    names = "--queue-host",
    arity = "1",
    defaultValue = "${env:JOB_QUEUE_HOST}",
    description = "Job queue host address"
  )
  private String queueHost = DefaultJobQueueHost;

  @Option(
    names = "--blast-host",
    arity = "1",
    required = true,
    description = "Blast service host address"
  )
  private String blastHost = DefaultBlastHost;

  @Option(
    names = "--queue-name",
    arity = "1",
    defaultValue = "${env:FIREWORQ_QUEUE_DEFAULT}",
    description = "Name of the queue new blast jobs will be added to."
  )
  private String queueName = DefaultQueueName;

  @Option(
    names = "--queue-route",
    arity = "1",
    defaultValue = "${env:QUEUE_ROUTE}",
    description = "Name of the queue new blast jobs will be added to."
  )
  private String queueRoute = DefaultQueueRoute;

  private Config() {
  }

  public static Config getInstance() {
    if (instance == null)
      return instance = new Config();

    return instance;
  }

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

  public String getMountPath() {
    return mountPath;
  }

  public String getQueueHost() {
    return queueHost;
  }

  public String getBlastHost() {
    return blastHost;
  }

  public String getQueueName() {
    return queueName;
  }

  public String getQueueRoute() {
    return queueRoute;
  }
}
