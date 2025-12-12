package mb.lib.config

import org.veupathdb.lib.container.jaxrs.config.Options
import picocli.CommandLine.Option

object Config : Options() {

  // ╔══════════════════════════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                                          ║ //
  // ║    Filesystem Mounts                                                                     ║ //
  // ║                                                                                          ║ //
  // ╚══════════════════════════════════════════════════════════════════════════════════════════╝ //

  @Option(
    names = ["--job-mount-path"],
    arity = "1",
    required = true,
    defaultValue = "\${env:JOB_MOUNT_PATH}",
    description = ["Job data workspace mount point."]
  )
  var jobMountPath = "/out"
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--db-mount-path"],
    arity = "1",
    required = true,
    defaultValue = "\${env:DB_MOUNT_PATH}",
    description = ["Blast file mount point."]
  )
  var dbMountPath = "/db"
    private set

  // ╔════════════════════════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                                        ║ //
  // ║    Job Queue Config                                                                    ║ //
  // ║                                                                                        ║ //
  // ╚════════════════════════════════════════════════════════════════════════════════════════╝ //

  @Option(
    names = ["--queue-host"],
    arity = "1",
    required = true,
    defaultValue = "\${env:QUEUE_HOST}",
    description = ["Job queue host address"]
  )
  var queueHost = "queue"
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--blast-queue-name"],
    arity = "1",
    required = true,
    defaultValue = "\${env:BLAST_QUEUE_NAME}",
    description = ["Name of the queue new blast jobs will be added to."]
  )
  var blastQueueName = "blast"
    private set


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = ["--blast-job-category"],
    arity = "1",
    required = true,
    defaultValue = "\${env:BLAST_JOB_CATEGORY}",
    description = ["Routing category for blast jobs in the job queue."]
  )
  var blastJobCategory = "blast"
    private set


  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //


  @Option(
    names = ["--format-queue-name"],
    arity = "1",
    required = true,
    defaultValue = "\${env:FORMAT_QUEUE_NAME}",
    description = ["Name of the queue new formatter jobs will be added to."]
  )
  @SuppressWarnings("FieldMayBeFinal")
  var formatQueueName = "format"
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--format-job-category"],
    arity = "1",
    required = true,
    defaultValue = "\${env:FORMAT_JOB_CATEGORY}",
    description = ["Routing category for format jobs in the job queue."]
  )
  var formatJobCategory = "format"
    private set

  // ╔════════════════════════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                                        ║ //
  // ║    External Services                                                                   ║ //
  // ║                                                                                        ║ //
  // ╚════════════════════════════════════════════════════════════════════════════════════════╝ //

  @Option(
    names = ["--blast-host"],
    arity = "1",
    required = true,
    defaultValue = "\${env:BLAST_HOST}",
    description = ["Blast service host address"]
  )
  var blastHost = "blast"
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--validator-host"],
    arity = "1",
    required = true,
    defaultValue = "\${env:VALIDATOR_HOST}",
    description = ["Host for the Blast+ config validator service"]
  )
  var validatorHost = "validator"
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--formatter-host"],
    arity = "1",
    required = true,
    defaultValue = "\${env:FORMATTER_HOST}",
    description = ["Blast formatter service host address"]
  )
  var formatterHost = "formatter"
    private set

  // ╔════════════════════════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                                        ║ //
  // ║    Misc Config                                                                         ║ //
  // ║                                                                                        ║ //
  // ╚════════════════════════════════════════════════════════════════════════════════════════╝ //

  @Option(
    names = ["--job-timeout"],
    arity = "1",
    defaultValue = "\${env:JOB_TIMEOUT}",
    description = ["Number of days to hold onto a completed job's results."]
  )
  var jobTimeout = 5
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--max-queries-per-request"],
    arity = "1",
    defaultValue = "\${env:MAX_QUERIES_PER_REQUEST}",
    description = ["Max number of sequences a user can submit in a single request."]
  )
  var maxQueries = 100
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--max-results-per-query"],
    arity = "1",
    defaultValue = "\${env:MAX_RESULTS_PER_QUERY}",
    description = ["Max number of results a user can request.  This value is calculated as <num_queries> * <max_target_seqs>.\n\nDefaults to 10,000."]
  )
  var maxResults = 10_000
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--max-input-query-size"],
    arity = "1",
    defaultValue = "\${env:MAX_INPUT_QUERY_SIZE}",
    description = ["Max total size for a set of input sequences in bytes."]
  )
  var maxInputQuerySize = 3_145_728
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--max-na-seq-size"],
    arity = "1",
    defaultValue = "\${env:MAX_NA_SEQ_SIZE}",
    description = ["Max length for an individual nucleotide sequence."]
  )
  var maxNASeqSize = 1_048_576
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--max-aa-seq-size"],
    arity = "1",
    defaultValue = "\${env:MAX_AA_SEQ_SIZE}",
    description = ["Max length for an individual protein sequence."]
  )
  var maxAASeqSize = 102_400
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--db-build"],
    arity = "1",
    defaultValue = "\${env:DB_BUILD}",
    description = ["Site DB build number"],
    required = true,
  )
  var dbBuild = "60"
    private set

  // ╠════════════════════════════════════════════════════════════════════════════════════════╣ //

  @Option(
    names = ["--ortho-build"],
    arity = "1",
    defaultValue = "\${env:ORTHO_BUILD}",
    description = ["OrthoMCL build number"],
    required = true,
  )
  var orthoBuild = ""
    private set

  @Option(
    names = ["--ortho-db-name"],
    arity = "1",
    defaultValue = "\${env:ORTHO_DB_NAME}",
    description = ["OrthoMCL db name"],
    required = true,
  )
  var orthoDbName = ""
    private set

  @Option(
    names = ["--max-diamond-query-size"],
    arity = "1",
    defaultValue = "\${env:MAX_DMND_QUERY_SIZE}",
    description = ["Max size for diamond queries in bytes."],
    required = false
  )
  var maxDiamondQuerySize = 31457280L // 30MiB
    private set

  @Option(
    names = ["--diamond-queue-name"],
    arity = "1",
    required = false,
    defaultValue = "\${env:DMND_QUEUE_NAME}",
    description = ["Name of the queue new diamond jobs will be added to."]
  )
  var diamondQueueName = "diamond"
    private set

  @Option(
    names = ["--diamond-cpu-limit"],
    arity = "1",
    required = false,
    defaultValue = "\${env:DMND_CPU_LIMIT}",
    description = ["Max number of cpu threads the diamond process may use"]
  )
  var diamondCpuLimit = 8
    private set
}
