package mb.lib.model;

import java.util.Arrays;
import java.util.function.Supplier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mb.lib.util.Hash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HashID
{
  private static final Logger Log = LogManager.getLogger(HashID.class);

  private final String strVal;
  private final byte[] byteVal;

  @JsonCreator
  public HashID(String val) {
    Log.trace("::new(val={})", val);
    strVal = val;
    byteVal = Hash.stringToHash(val);
  }

  public HashID(byte[] val) {
    Log.trace("::new(val={})", val);
    strVal = Hash.hashToString(val);
    byteVal = val;
  }

  @JsonValue
  public String string() {
    return strVal;
  }

  public byte[] bytes() {
    return byteVal;
  }

  @Override
  public String toString() {
    return strVal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HashID jobID = (HashID) o;
    return Arrays.equals(byteVal, jobID.byteVal);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(byteVal);
  }

  public static <T extends Throwable> HashID parseOrThrow(
    String val,
    Supplier<T> fn
  ) throws T {
    Log.trace("::parseOrThrow(val={}, fn={})", val, fn);
    try {
      return new HashID(val);
    } catch (IllegalArgumentException e) {
      Log.debug("Invalid hash ID: ", e);
      throw fn.get();
    }
  }
}
