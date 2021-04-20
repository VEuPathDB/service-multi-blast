package mb.api.service.conv;

import mb.api.model.blast.IOBlastConfig;
import mb.api.model.internal.Job;

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
    var out = new Job(conf.getTool());

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
