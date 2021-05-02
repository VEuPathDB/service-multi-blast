package mb.lib.query;

import java.util.List;

import mb.lib.config.Config;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.query.model.BlastRequest;
import mb.lib.queue.QueueManager;
import mb.lib.queue.consts.URL;
import mb.lib.queue.model.CreateRequest;
import mb.lib.queue.model.FailedJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.blast.BlastConfig;
import org.veupathdb.lib.blast.BlastTool;

class BlastQueueManager extends QueueManager
{

  private static final Logger log = LogManager.getLogger(BlastQueueManager.class);

  private static final Config conf = Config.getInstance();

  private static BlastQueueManager instance;

  private BlastQueueManager() {
    log.trace("JobQueueManager::new()");
  }

  public static BlastQueueManager getInstance() {
    log.trace("::getInstance()");
    if (instance == null)
      return instance = new BlastQueueManager();

    return instance;
  }

  // ------------------------------------------------------------------------------------------ //

  /**
   * Retrieves and returns the status for a given job ID in the blast job queue.
   *
   * @param queueJobID External ID of the job to check.
   *
   * @return Status of the checked job.
   */
  public static JobStatus jobStatus(int queueJobID) throws Exception {
    log.trace("::jobStatus(queueJobID={})", queueJobID);
    return getInstance().getJobStatus(queueJobID);
  }

  /**
   * Retrieves and returns the status for a given job ID in the blast job queue.
   *
   * @param jobID External ID of the job to check.
   *
   * @return Status of the checked job.
   */
  public JobStatus getJobStatus(int jobID) throws Exception {
    log.trace("#getJobStatus(jobID={})", jobID);
    return getJobStatus(conf.getBlastQueueName(), jobID);
  }

  // ------------------------------------------------------------------------------------------ //

  public boolean jobInFailList(int jobID) throws Exception {
    log.trace("#jobInFailList(jobID={})", jobID);
    return jobInFailList(conf.getBlastQueueName(), jobID);
  }

  public List<FailedJob> getFailedJobs() throws Exception {
    log.trace("#getFailedJobs()");
    return getFailedJobs(conf.getBlastQueueName());
  }

  public void deleteJobFailure(FailedJob job) throws Exception {
    log.trace("#deleteJobFailure(job={})", job);
    deleteJobFailure(conf.getBlastQueueName(), job);
  }

  // ------------------------------------------------------------------------------------------ //

  public static int submitJob(HashID jobId, BlastConfig cli) throws Exception {
    return getInstance().submitNewJob(jobId, cli);
  }

  /**
   * Submits a new Blast job to the job queue.
   *
   * @param jobId  Job configuration digest (used as a unique identifier)
   * @param config Blast tool CLI parameters (starting with the blast tool
   *               itself).
   *
   * @return the queue ID for the queued job
   */
  public int submitNewJob(HashID jobId, BlastConfig config) throws Exception {
    log.trace("#submitJob(jobID={}, config={})", jobId, config);

    var tool = config.getTool();

    return submitNewJob(
      conf.getBlastQueueName(),
      new CreateRequest<>(
        URL.prependHTTP(conf.getBlastHost()),
        new BlastRequest(jobId, tool, config)
      )
    );
  }

  // ------------------------------------------------------------------------------------------ //

  public void deleteJob(int jobID) throws Exception {
    log.trace("#deleteJob(jobID={})", jobID);
    deleteJob(conf.getBlastQueueName(), jobID);
  }
}
