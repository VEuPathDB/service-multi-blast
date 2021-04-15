package mb.lib.extern;

import java.util.List;

import mb.lib.config.Config;
import mb.lib.extern.consts.URL;
import mb.lib.extern.model.FailedJob;
import mb.lib.extern.model.QueueJobStatus;
import mb.lib.extern.model.format.JobCreateRequest;
import mb.lib.extern.model.format.JobPayload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FormatQueueManager extends QueueManager
{
  private static final Logger log  = LogManager.getLogger(FormatQueueManager.class);
  private static final Config conf = Config.getInstance();

  private static final String reportEndpoint = "/report";

  private static FormatQueueManager instance;

  private FormatQueueManager() {
    log.trace("::FormatQueueManager()");
  }

  public static FormatQueueManager getInstance() {
    log.trace("::getInstance()");

    if (instance == null)
      return instance = new FormatQueueManager();

    return instance;
  }

  /**
   * Retrieves and returns the status for a given job ID in the blast formatter
   * job queue.
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
   * Retrieves and returns the status for a given job ID in the blast formatter
   * job queue.
   *
   * @param jobID ID of the job to check.
   *
   * @return Status of the checked job.
   */
  public QueueJobStatus getJobStatus(int jobID) throws Exception {
    log.trace("#getJobStatus(jobID={})", jobID);
    return getJobStatus(conf.getFormatQueueName(), jobID);
  }

  /**
   * Retrieves and returns a list of failed jobs from the blast formatter job
   * queue.
   *
   * @return A list of zero or more failed jobs.
   */
  public static List<FailedJob> failedJobs() throws Exception {
    log.trace("::failedJobs()");
    return getInstance().getFailedJobs();
  }

  /**
   * Retrieves and returns a list of failed jobs from the blast formatter job
   * queue.
   *
   * @return A list of zero or more failed jobs.
   */
  public List<FailedJob> getFailedJobs() throws Exception {
    log.trace("#getFailedJobs()");
    return getFailedJobs(conf.getFormatQueueName());
  }

  public static boolean jobIsFailed(int jobID) throws Exception {
    log.trace("::jobIsFailed(jobID={})", jobID);
    return getInstance().jobInFailList(jobID);
  }

  public boolean jobInFailList(int jobID) throws Exception {
    log.trace("#jobInFailList(jobID={})", jobID);
    return jobInFailList(conf.getFormatQueueName(), jobID);
  }

  public void deleteJobFailure(FailedJob job) throws Exception {
    log.trace("#deleteJobFailure(job={})", job);
    deleteJobFailure(conf.getFormatQueueName(), job);
  }

  public void deleteJob(int jobID) throws Exception {
    log.trace("#deleteJob(jobID={})", jobID);
    deleteJob(conf.getFormatQueueName(), jobID);
  }

  public static int submitJob(JobPayload payload) throws Exception {
    log.trace("::submitJob(payload={})", payload);
    return getInstance().submitNewJob(payload);
  }

  public int submitNewJob(JobPayload payload) throws Exception {
    log.trace("#submitNewJob(payload={})", payload);
    return submitNewJob(conf.getFormatQueueName(), new JobCreateRequest(
      String.join("/", URL.prependHTTP(conf.getFormatterHost()), reportEndpoint),
      payload
    ));
  }
}
