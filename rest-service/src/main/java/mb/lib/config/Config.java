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
    names = "--formatter-host",
    arity = "1",
    required = true,
    defaultValue = "${env:FORMATTER_HOST}",
    description = "Blast formatter service host address"
  )
  private String formatterHost;

  @Option(
    names = "--blast-queue-name",
    arity = "1",
    required = true,
    description = "Name of the queue new blast jobs will be added to."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String blastQueueName = "blast";

  @Option(
    names = "--format-queue-name",
    arity = "1",
    required = true,
    description = "Name of the queue new formatter jobs will be added to."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String formatQueueName = "format";

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
    defaultValue = "${env:JOB_TIMEOUT}",
    description = "Number of days to hold onto a completed job's results."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int jobTimeout = 30;

  @Option(
    names = "--max-queries-per-request",
    arity = "1",
    defaultValue = "${env:MAX_QUERIES_PER_REQUEST}",
    description = "Max number of sequences a user can submit in a single request."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int maxQueries = 100;

  @Option(
    names = "--max-results-per-query",
    arity = "1",
    defaultValue = "${env:MAX_RESULTS_PER_QUERY}",
    description = "Max number of results a user can request.  This value is calculated as <num_queries> * <max_target_seqs>.\n\nDefaults to 10,000."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int maxResults = 10_000;

  @Option(
    names = "--max-input-query-size",
    arity = "1",
    defaultValue = "${env:MAX_INPUT_QUERY_SIZE}",
    description = "Max total size for a set of input sequences in bytes."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int maxInputQuerySize = 3_145_728;

  @Option(
    names = "--max-na-seq-size",
    arity = "1",
    defaultValue = "${env:MAX_NA_SEQ_SIZE}",
    description = "Max length for an individual nucleotide sequence."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int maxNASeqSize = 1_048_576;

  @Option(
    names = "--max-aa-seq-size",
    arity = "1",
    defaultValue = "${env:MAX_AA_SEQ_SIZE}",
    description = "Max length for an individual protein sequence."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int maxAASeqSize = 102_400;

  // ╔══════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                      ║ //
  // ║    Class Methods                                                     ║ //
  // ║                                                                      ║ //
  // ╚══════════════════════════════════════════════════════════════════════╝ //

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

  public String getBlastQueueName() {
    return blastQueueName;
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

  public int getMaxSeqsPerQuery() {
    return maxQueries;
  }

  public int getMaxResults() {
    return maxResults;
  }

  public int getMaxInputQuerySize() {
    return maxInputQuerySize;
  }

  public int getMaxNASeqSize() {
    return maxNASeqSize;
  }

  public int getMaxAASeqSize() {
    return maxAASeqSize;
  }

  public String getFormatQueueName() {
    return formatQueueName;
  }

  public String getFormatterHost() {
    return formatterHost;
  }
}