package mb.lib.config;

import org.veupathdb.lib.container.jaxrs.config.Options;
import picocli.CommandLine.Option;

public class Config extends Options
{
  private static Config instance;

  // ╔══════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                      ║ //
  // ║    Postgres Service Database                                         ║ //
  // ║                                                                      ║ //
  // ╚══════════════════════════════════════════════════════════════════════╝ //

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
    required = true,
    defaultValue = "${env:SVC_DB_HOST}",
    description = "Postgres service database hostname.  Defaults to \"service-db\""
  )
  private String serviceDbHost;

  @Option(
    names = "--svc-db-port",
    arity = "1",
    required = true,
    defaultValue = "${env:SVC_DB_PORT}",
    description = "Postgres service database port.  Defaults to 5432"
  )
  private int serviceDbPort;

  @Option(
    names = "--svc-db-name",
    arity = "1",
    required = true,
    defaultValue = "${env:SVC_DB_NAME}",
    description = "Postgres database name."
  )
  private String serviceDbName;

  // ╔══════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                      ║ //
  // ║    WDK User Database                                                 ║ //
  // ║                                                                      ║ //
  // ╚══════════════════════════════════════════════════════════════════════╝ //

  @Option(
    names = "--multiblast-schema",
    arity = "1",
    required = true,
    defaultValue = "${env:MULTIBLAST_SCHEMA}",
    description = "User DB schema where the multiblast tables reside."
  )
  private String multiBlastSchema;

  // ╔══════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                      ║ //
  // ║    Job Running                                                       ║ //
  // ║                                                                      ║ //
  // ╚══════════════════════════════════════════════════════════════════════╝ //

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
    required = true,
    defaultValue = "${env:QUEUE_HOST}",
    description = "Job queue host address"
  )
  private String queueHost;

  @Option(
    names = "--blast-host",
    arity = "1",
    required = true,
    defaultValue = "${env:BLAST_HOST}",
    description = "Blast service host address"
  )
  private String blastHost;

  @Option(
    names = "--queue-name",
    arity = "1",
    required = true,
    defaultValue = "${env:FIREWORQ_QUEUE_DEFAULT}",
    description = "Name of the queue new blast jobs will be added to."
  )
  private String queueName;

  @Option(
    names = "--queue-route",
    arity = "1",
    required = true,
    defaultValue = "${env:QUEUE_ROUTE}",
    description = "Name of the queue new blast jobs will be added to."
  )
  private String queueRoute;

  // ╔══════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                      ║ //
  // ║    Misc Config                                                       ║ //
  // ║                                                                      ║ //
  // ╚══════════════════════════════════════════════════════════════════════╝ //

  @Option(
    names = "--job-timeout",
    arity = "1",
    description = "Number of days to hold onto a completed job's results.\n\nDefaults to 30."
  )
  private int jobTimeout = 30;

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

  public int getJobTimeout() {
    return jobTimeout;
  }
}
