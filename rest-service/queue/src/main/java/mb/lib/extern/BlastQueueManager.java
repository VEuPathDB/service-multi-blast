package mb.lib.extern;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import mb.lib.config.Config;
import mb.lib.extern.consts.URL;
import mb.lib.extern.model.*;
import mb.lib.extern.model.blast.JobCreateRequest;
import mb.lib.extern.model.QueueJobStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlastQueueManager extends QueueManager
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

  /**
   * Retrieves and returns the status for a given job ID in the blast job queue.
   *
   * @param jobID ID of the job to check.
   *
   * @return Status of the checked job.
   */
  public static QueueJobStatus jobStatus(int jobID) throws Exception {
    log.trace("::jobStatus(jobID={})", jobID);
    return getInstance().getJobStatus(jobID);
  }

  /**
   * Retrieves and returns the status for a given job ID in the blast job queue.
   *
   * @param jobID ID of the job to check.
   *
   * @return Status of the checked job.
   */
  public QueueJobStatus getJobStatus(int jobID) throws Exception {
    log.trace("#getJobStatus(jobID={})", jobID);
    return getJobStatus(conf.getBlastQueueName(), jobID);
  }

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

  /**
   * @see #submitJob(String, String, String[])
   */
  public static int submitJob(String jobId, String tool, String[] cli) throws Exception {
    return getInstance().submitNewJob(jobId, tool, cli);
  }

  /**
   * Submits a new Blast job to the job queue.
   *
   * @param jobId Job configuration digest (used as a unique identifier)
   * @param cli   Blast tool CLI parameters (starting with the blast tool
   *              itself).
   *
   * @return the queue ID for the queued job
   */
  public int submitNewJob(String jobId, String tool, String[] cli) throws Exception {
    log.trace("#submitJob(jobID={}, tool={}, cli={})", jobId, tool, cli);

    return submitNewJob(
      conf.getBlastQueueName(),
      new JobCreateRequest(
        String.join("/", URL.prependHTTP(conf.getBlastHost()), tool, jobId),
        cli
      )
    );
  }

  public void deleteJob(int jobID) throws Exception {
    log.trace("#deleteJob(jobID={})", jobID);
    deleteJob(conf.getBlastQueueName(), jobID);
  }
}
