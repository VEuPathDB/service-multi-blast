package org.veupathdb.service.multiblast.service.conv;

import org.veupathdb.service.multiblast.generated.model.IOBlastConfig;
import org.veupathdb.service.multiblast.generated.model.IOBlastTool;
import org.veupathdb.service.multiblast.model.blast.BlastTool;
import org.veupathdb.service.multiblast.model.internal.Job;

public class JobConverter
{
  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Class Instance Management                                        ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private static JobConverter instance;

  public static JobConverter getInstance() {
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
    return switch(val) {
      case BLASTN -> BlastTool.BlastN;
      case BLASTP -> BlastTool.BlastP;
      case BLASTX -> BlastTool.BlastX;
      case TBLASTN -> BlastTool.TBlastN;
      case TBLASTX -> BlastTool.TBlastX;
    };
  }

  public static Job toInternal(IOBlastConfig conf) {
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
