package mb.lib.config;

import org.veupathdb.lib.container.jaxrs.config.Options;
import picocli.CommandLine.Option;

public class  Config extends Options
{
  private static Config instance;


  // ╔══════════════════════════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                                          ║ //
  // ║    Filesystem Mounts                                                                     ║ //
  // ║                                                                                          ║ //
  // ╚══════════════════════════════════════════════════════════════════════════════════════════╝ //


  @Option(
    names = "--job-mount-path",
    arity = "1",
    required = true,
    defaultValue = "${env:JOB_MOUNT_PATH}",
    description = "Job data workspace mount point."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String jobMountPath = "/out";

  public String getJobMountPath() {
    return jobMountPath;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--db-mount-path",
    arity = "1",
    required = true,
    defaultValue = "${env:DB_MOUNT_PATH}",
    description = "Blast file mount point."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String dbMountPath = "/db";

  public String getDbMountPath() {
    return dbMountPath;
  }


  // ╔════════════════════════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                                        ║ //
  // ║    Job Queue Config                                                                    ║ //
  // ║                                                                                        ║ //
  // ╚════════════════════════════════════════════════════════════════════════════════════════╝ //


  @Option(
    names = "--queue-host",
    arity = "1",
    required = true,
    defaultValue = "${env:QUEUE_HOST}",
    description = "Job queue host address"
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String queueHost = "queue";

  public String getQueueHost() {
    return queueHost;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--blast-queue-name",
    arity = "1",
    required = true,
    defaultValue = "${env:BLAST_QUEUE_NAME}",
    description = "Name of the queue new blast jobs will be added to."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String blastQueueName = "blast";

  public String getBlastQueueName() {
    return blastQueueName;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--blast-job-category",
    arity = "1",
    required = true,
    defaultValue = "${env:BLAST_JOB_CATEGORY}",
    description = "Routing category for blast jobs in the job queue."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String blastJobCategory = "blast";

  public String getBlastJobCategory() {
    return blastJobCategory;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--format-queue-name",
    arity = "1",
    required = true,
    defaultValue = "${env:FORMAT_QUEUE_NAME}",
    description = "Name of the queue new formatter jobs will be added to."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String formatQueueName = "format";

  public String getFormatQueueName() {
    return formatQueueName;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--format-job-category",
    arity = "1",
    required = true,
    defaultValue = "${env:FORMAT_JOB_CATEGORY}",
    description = "Routing category for format jobs in the job queue."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String formatJobCategory = "format";

  public String getFormatJobCategory() {
    return formatJobCategory;
  }


  // ╔════════════════════════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                                        ║ //
  // ║    External Services                                                                   ║ //
  // ║                                                                                        ║ //
  // ╚════════════════════════════════════════════════════════════════════════════════════════╝ //


  @Option(
    names = "--blast-host",
    arity = "1",
    required = true,
    defaultValue = "${env:BLAST_HOST}",
    description = "Blast service host address"
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String blastHost = "blast";

  public String getBlastHost() {
    return blastHost;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--validator-host",
    arity = "1",
    required = true,
    defaultValue = "${env:VALIDATOR_HOST}",
    description = "Host for the Blast+ config validator service"
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String validatorHost = "validator";

  public String getValidatorHost() {
    return validatorHost;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--formatter-host",
    arity = "1",
    required = true,
    defaultValue = "${env:FORMATTER_HOST}",
    description = "Blast formatter service host address"
  )
  @SuppressWarnings("FieldMayBeFinal")
  private String formatterHost = "formatter";

  public String getFormatterHost() {
    return formatterHost;
  }


  // ╔════════════════════════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                                        ║ //
  // ║    WDK/Site Options                                                                    ║ //
  // ║                                                                                        ║ //
  // ╚════════════════════════════════════════════════════════════════════════════════════════╝ //


  @Option(
    names = "--db-build",
    arity = "1",
    required = true,
    defaultValue = "${env:DB_BUILD}",
    description = "Site build number"
  )
  private int buildNum;

  public int getBuildNum() {
    return buildNum;
  }


  // ╔════════════════════════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                                        ║ //
  // ║    Misc Config                                                                         ║ //
  // ║                                                                                        ║ //
  // ╚════════════════════════════════════════════════════════════════════════════════════════╝ //


  @Option(
    names = "--job-timeout",
    arity = "1",
    defaultValue = "${env:JOB_TIMEOUT}",
    description = "Number of days to hold onto a completed job's results."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int jobTimeout = 5;

  public int getJobTimeout() {
    return jobTimeout;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--max-queries-per-request",
    arity = "1",
    defaultValue = "${env:MAX_QUERIES_PER_REQUEST}",
    description = "Max number of sequences a user can submit in a single request."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int maxQueries = 100;

  public int getMaxSeqsPerQuery() {
    return maxQueries;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--max-results-per-query",
    arity = "1",
    defaultValue = "${env:MAX_RESULTS_PER_QUERY}",
    description = "Max number of results a user can request.  This value is calculated as <num_queries> * <max_target_seqs>.\n\nDefaults to 10,000."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int maxResults = 10_000;

  public int getMaxResults() {
    return maxResults;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--max-input-query-size",
    arity = "1",
    defaultValue = "${env:MAX_INPUT_QUERY_SIZE}",
    description = "Max total size for a set of input sequences in bytes."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int maxInputQuerySize = 3_145_728;

  public int getMaxInputQuerySize() {
    return maxInputQuerySize;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--max-na-seq-size",
    arity = "1",
    defaultValue = "${env:MAX_NA_SEQ_SIZE}",
    description = "Max length for an individual nucleotide sequence."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int maxNASeqSize = 1_048_576;

  public int getMaxNASeqSize() {
    return maxNASeqSize;
  }


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = "--max-aa-seq-size",
    arity = "1",
    defaultValue = "${env:MAX_AA_SEQ_SIZE}",
    description = "Max length for an individual protein sequence."
  )
  @SuppressWarnings("FieldMayBeFinal")
  private int maxAASeqSize = 102_400;

  public int getMaxAASeqSize() {
    return maxAASeqSize;
  }


  // ╔════════════════════════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                                        ║ //
  // ║    Class Methods                                                                       ║ //
  // ║                                                                                        ║ //
  // ╚════════════════════════════════════════════════════════════════════════════════════════╝ //


  private Config() {
  }

  public static Config getInstance() {
    if (instance == null)
      return instance = new Config();

    return instance;
  }
}
