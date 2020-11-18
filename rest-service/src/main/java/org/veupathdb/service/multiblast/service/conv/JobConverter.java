package org.veupathdb.service.multiblast.service.conv;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.model.internal.JobStatus;

public class JobConverter
{
  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Class Instance Management                                        ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private static JobConverter instance;

  private final Logger log;

  private JobConverter() {
    log = LogManager.getLogger(getClass());
  }

  public JobConverter getInstance() {
    if (instance == null)
      return instance = new JobConverter();

    return instance;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Conversion Methods                                               ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  public IOJobStatus fromInternal(JobStatus val) {
    return switch(val) {
      case Queued -> IOJobStatus.QUEUED;
      case InProgress -> IOJobStatus.INPROGRESS;
      case Errored -> IOJobStatus.ERRORED;
      case Completed -> IOJobStatus.COMPLETED;
    };
  }

  public JobStatus toInternal(IOJobStatus val) {
    return switch(val) {
      case QUEUED -> JobStatus.Queued;
      case INPROGRESS -> JobStatus.InProgress;
      case ERRORED -> JobStatus.Errored;
      case COMPLETED -> JobStatus.Completed;
    };
  }

  public GetJobResponse fromInternal(Job job) {
    var out = new GetJobResponseImpl();

    out.setId(job.getJobId());
    out.setStatus(fromInternal(job.getStatus()));
    out.setConfig(BlastConverter.getInstance().fromInternal(job.getConfig()));

    return out;
  }
}
