package org.veupathdb.service.multiblast.service.conv;

import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.providers.LogProvider;
import org.veupathdb.service.multiblast.generated.model.IOBlastConfig;
import org.veupathdb.service.multiblast.generated.model.IOBlastTool;
import org.veupathdb.service.multiblast.generated.model.IOJobStatus;
import org.veupathdb.service.multiblast.model.blast.BlastTool;
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

  private static final Logger log = LogProvider.logger(JobConverter.class);

  private JobConverter() {
    log.trace("JobConverter#new()");
  }

  public static JobConverter getInstance() {
    log.trace("JobConverter#getInstance()");

    if (instance == null)
      return instance = new JobConverter();

    return instance;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Conversion Methods                                               ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  public static BlastTool toInternal(IOBlastTool val) {
    log.trace("JobConverter#toInternal(IOBlastTool)");

    return switch(val) {
      case BLASTN -> BlastTool.BlastN;
      case BLASTP -> BlastTool.BlastP;
      case BLASTX -> BlastTool.BlastX;
      case TBLASTN -> BlastTool.TBlastN;
      case TBLASTX -> BlastTool.TBlastX;
    };
  }

  public static IOBlastTool toExternal(BlastTool val) {
    log.trace("JobConverter#toExternal(BlastTool)");

    return switch(val) {
      case BlastN -> IOBlastTool.BLASTN;
      case BlastP -> IOBlastTool.BLASTP;
      case BlastX -> IOBlastTool.BLASTX;
      case TBlastN -> IOBlastTool.TBLASTN;
      case TBlastX -> IOBlastTool.TBLASTX;

      default -> throw new IllegalArgumentException(
        String.format("Blast tool \"%s\" is not currently supported.", val)
      );
    };
  }

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

  public static Job toInternal(IOBlastConfig conf) {
    log.trace("JobConverter#toInternal(long, IOBlastConfig)");

    return getInstance().externalToInternal(conf);
  }

  public Job externalToInternal(IOBlastConfig conf) {
    var out = new Job(toInternal(conf.getTool()));
    return out.setJobConfig(BlastConverter.toInternal(conf));
  }

  public static IOBlastConfig toExternal(Job job) {
    return getInstance().internalToExternal(job);
  }

  public IOBlastConfig internalToExternal(Job job) {
    return BlastConverter.toExternal(job.getJobConfig());
  }
}