package mb.lib.config;

import org.veupathdb.lib.container.jaxrs.config.Options;
import picocli.CommandLine.Option;

public class  Config extends Options
{
  private static Config instance;

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
    names = "--job-mount-path",
    arity = "1",
    required = true,
    defaultValue = "${env:JOB_MOUNT_PATH}",
    description = "Job data workspace mount point."
  )
  private String jobMountPath;

  @Option(
    names = "--db-mount-path",
    arity = "1",
    required = true,
    defaultValue = "${env:DB_MOUNT_PATH}",
    description = "Blast file mount point."
  )
  private String dbMountPath;

  @Option(
    names = "--db-build",
    arity = "1",
    required = true,
    defaultValue = "${env:DB_BUILD}",
    description = "Site build number"
  )
  private int buildNum;

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
    names = "--job-category",
    arity = "1",
    required = true,
    defaultValue = "${env:JOB_CATEGORY}",
    description = "Endpoint (including category"
  )
  private String jobCategory;

  // ╔══════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                      ║ //
  // ║    Formatter Config                                                  ║ //
  // ║                                                                      ║ //
  // ╚══════════════════════════════════════════════════════════════════════╝ //

  @Option(
    names = "--formatter-host",
    arity = "1",
    required = true,
    defaultValue = "${env:FORMATTER_HOST}",
    description = "Host for the Blast+ formatter service"
  )
  private String formatterURI;

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

  @Option(
    names = "--max-queries-per-request",
    arity = "1",
    description = "Max number of queries a user can submit in a single request.\n\nDefaults to 100."
  )
  private int maxQueries = 100;

  @Option(
    names = "--max-results-per-query",
    arity = "1",
    description = "Max number of results a user can request.  This value is calculated as <num_queries> * <max_target_seqs>.\n\nDefaults to 10,000."
  )
  private int maxResults = 10_000;

  private Config() {
  }

  public static Config getInstance() {
    if (instance == null)
      return instance = new Config();

    return instance;
  }

  public String getJobMountPath() {
    return jobMountPath;
  }

  public String getDbMountPath() {
    return dbMountPath;
  }

  public String getMultiBlastSchema() {
    return multiBlastSchema;
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

  public String getJobCategory() {
    return jobCategory;
  }

  public int getJobTimeout() {
    return jobTimeout;
  }

  public String getFormatterURI() {
    return formatterURI;
  }

  public int getBuildNum() {
    return buildNum;
  }

  public int getMaxQueries() {
    return maxQueries;
  }

  public int getMaxResults() {
    return maxResults;
  }
}
