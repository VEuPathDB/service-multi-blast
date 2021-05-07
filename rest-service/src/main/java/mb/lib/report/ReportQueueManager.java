package mb.lib.report;

import java.net.URI;
import java.util.List;

import mb.lib.config.Config;
import mb.lib.report.model.FailedReportJobResponse;
import mb.lib.report.model.ReportPayload;
import mb.lib.report.model.Request;
import mb.lib.model.JobStatus;
import mb.lib.queue.QueueManager;
import mb.lib.queue.consts.URL;
import mb.lib.queue.model.FailedJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ReportQueueManager extends QueueManager
{
  private static final Logger log  = LogManager.getLogger(ReportQueueManager.class);
  private static final Config conf = Config.getInstance();

  /**
   * Report endpoint path segment.  There should be no slashes in this value as
   * they are inserted when the full path is built.
   */
  private static final String reportEndpoint = "report";

  private static ReportQueueManager instance;

  private ReportQueueManager() {
    log.trace("::FormatQueueManager()");
  }

  public static ReportQueueManager getInstance() {
    log.trace("::getInstance()");

    if (instance == null)
      return instance = new ReportQueueManager();

    return instance;
  }

  @Override
  protected URI submissionURL() {
    return URL.jobSubmissionEndpoint(conf.getFormatJobCategory());
  }

  /**
   * Retrieves and returns the status for a given job ID in the blast formatter
   * job queue.
   *
   * @param jobID ID of the job to check.
   *
   * @return Status of the checked job.
   */
  public static JobStatus jobStatus(int jobID) throws Exception {
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
  public JobStatus getJobStatus(int jobID) throws Exception {
    log.trace("#getJobStatus(jobID={})", jobID);
    return getJobStatus(conf.getFormatQueueName(), jobID);
  }

  /**
   * Retrieves and returns a list of failed jobs from the blast formatter job
   * queue.
   *
   * @return A list of zero or more failed jobs.
   */
  public static List<? extends FailedJob<?>> failedJobs() throws Exception {
    log.trace("::failedJobs()");
    return getInstance().getFailedJobs();
  }

  /**
   * Retrieves and returns a list of failed jobs from the blast formatter job
   * queue.
   *
   * @return A list of zero or more failed jobs.
   */
  public List<? extends FailedJob<?>> getFailedJobs() throws Exception {
    log.trace("#getFailedJobs()");
    return getFailedJobs(conf.getFormatQueueName(), FailedReportJobResponse.class).getFailedJobs();
  }

  public static boolean jobIsFailed(int jobID) throws Exception {
    log.trace("::jobIsFailed(jobID={})", jobID);
    return getInstance().jobInFailList(jobID);
  }

  public boolean jobInFailList(int jobID) throws Exception {
    log.trace("#jobInFailList(jobID={})", jobID);
    return jobInFailList(conf.getFormatQueueName(), jobID, FailedReportJobResponse.class);
  }

  public void deleteJobFailure(int failID) throws Exception {
    log.trace("#deleteJobFailure(failID={})", failID);
    deleteJobFailure(conf.getFormatQueueName(), failID);
  }

  public void deleteJob(int jobID) throws Exception {
    log.trace("#deleteJob(jobID={})", jobID);
    deleteJob(conf.getFormatQueueName(), jobID);
  }

  public static int submitJob(ReportPayload payload) throws Exception {
    log.trace("::submitJob(payload={})", payload);
    return getInstance().submitNewJob(payload);
  }

  public int submitNewJob(ReportPayload payload) throws Exception {
    log.trace("#submitNewJob(payload={})", payload);
    return submitNewJob(conf.getFormatQueueName(), new Request(
      String.join("/", URL.prependHTTP(conf.getFormatterHost()), reportEndpoint),
      payload
    ));
  }
}
