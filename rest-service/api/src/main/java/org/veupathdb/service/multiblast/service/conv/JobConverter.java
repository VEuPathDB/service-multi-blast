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

  /**
   * Translates the given external blast tool enum into the internal
   * representation.
   *
   * @param val Enum value to translate.
   *
   * @return Translated enum value.
   */
  public static BlastTool toInternal(IOBlastTool val) {
    return switch(val) {
      case BLASTN  -> BlastTool.BlastN;
      case BLASTP  -> BlastTool.BlastP;
      case BLASTX  -> BlastTool.BlastX;
      case TBLASTN -> BlastTool.TBlastN;
      case TBLASTX -> BlastTool.TBlastX;
    };
  }

  /**
   * @see #externalToInternal(IOBlastConfig)
   */
  public static Job toInternal(IOBlastConfig conf) {
    return getInstance().externalToInternal(conf);
  }

  /**
   * Translates the given external blast configuration into the internal
   * representation.
   *
   * @param conf Configuration to convert.
   *
   * @return Translated blast config.
   */
  public Job externalToInternal(IOBlastConfig conf) {
    var out = new Job(toInternal(conf.getTool()));

    return out.setJobConfig(BlastConverter.toInternal(conf));
  }

  /**
   * @see #internalToExternal(Job)
   */
  public static IOBlastConfig toExternal(Job job) {
    return getInstance().internalToExternal(job);
  }

  /**
   * Translates the given internal blast configuration into the external
   * representation.
   *
   * @param job Configuration to convert.
   *
   * @return Translated blast config.
   */
  public IOBlastConfig internalToExternal(Job job) {
    return BlastConverter.toExternal(job.getJobConfig());
  }
}
