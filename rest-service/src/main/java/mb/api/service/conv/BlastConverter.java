package mb.api.service.conv;

import mb.api.model.blast.*;
import mb.api.model.blast.impl.*;
import mb.lib.util.BlastConv;
import org.veupathdb.lib.blast.*;

public class BlastConverter
{
  private static BlastConverter instance;

  public static BlastConverter getInstance() {
    if (instance == null)
      return instance = new BlastConverter();

    return instance;
  }

  static BlastConfig toInternal(IOBlastConfig conf) {
    return getInstance().externalToInternal(conf);
  }

  public BlastConfig externalToInternal(IOBlastConfig conf) {
    if (conf == null)
      return null;

    return switch (conf.getTool()) {
      case BlastN -> BlastConv.convert((IOBlastnConfig) conf);
      case BlastP -> BlastConv.convert((IOBlastpConfig) conf);
      case BlastX -> BlastConv.convert((IOBlastxConfig) conf);
      case TBlastN -> BlastConv.convert((IOTBlastnConfig) conf);
      case TBlastX -> BlastConv.convert((IOTBlastxConfig) conf);
      case PSIBlast -> throw new RuntimeException("psiblast is currently disallowed");
      case RPSBlast -> throw new RuntimeException("rpsblast is currently disallowed");
      case RPSTBlastN -> throw new RuntimeException("rpstblastn is currently disallowed");
      case DeltaBlast -> throw new RuntimeException("deltablast is currently disallowed");
      case BlastFormatter -> throw new RuntimeException("blast_formatter is currently disallowed");
    };
  }

  public static IOBlastConfig toExternal(BlastConfig conf) {
    return getInstance().internalToExternal(conf);
  }

  public IOBlastConfig internalToExternal(BlastConfig conf) {
    if (conf == null)
      return null;

    var out = newExternal(conf);

    return switch (out.getTool()) {
      case BlastN -> BlastConv.convert((BlastN) conf);
      case BlastP -> BlastConv.convert((BlastP) conf);
      case BlastX -> BlastConv.convert((BlastX) conf);
      case TBlastN -> BlastConv.convert((TBlastN) conf);
      case TBlastX -> BlastConv.convert((TBlastX) conf);
      case PSIBlast -> throw new RuntimeException("psiblast is currently disallowed");
      case RPSBlast -> throw new RuntimeException("rpsblast is currently disallowed");
      case RPSTBlastN -> throw new RuntimeException("rpstblastn is currently disallowed");
      case DeltaBlast -> throw new RuntimeException("deltablast is currently disallowed");
      case BlastFormatter -> throw new RuntimeException("blast_formatter is currently disallowed");
    };
  }

  static IOBlastConfig newExternal(BlastConfig conf) {
    if (conf instanceof BlastN)
      return new IOBlastnConfigImpl();
    if (conf instanceof BlastP)
      return new IOBlastpConfigImpl();
    if (conf instanceof BlastX)
      return new IOBlastxConfigImpl();
    if (conf instanceof TBlastN)
      return new IOTBlastnConfigImpl();
    if (conf instanceof TBlastX)
      return new IOTBlastxConfigImpl();

    throw new IllegalArgumentException("unrecognized blast config type");
  }
}
