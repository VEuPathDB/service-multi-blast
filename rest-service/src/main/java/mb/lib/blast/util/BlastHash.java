package mb.lib.blast.util;

import mb.api.model.internal.Job;
import mb.lib.util.JSON;
import org.veupathdb.lib.blast.*;

public class BlastHash
{
  public static byte[] hash(BlastN b) {
    JSON.stringify(b);
  }
  public static byte[] hash(BlastP b) {}
  public static byte[] hash(BlastX b) {}
  public static byte[] hash(TBlastN b) {}
  public static byte[] hash(TBlastX b) {}

  private static byte[] hash(Job)
}
