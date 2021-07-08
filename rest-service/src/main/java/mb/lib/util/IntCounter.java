package mb.lib.util;

public class IntCounter
{
  private int value;

  public IntCounter(int start) {
    value = start;
  }

  public IntCounter() {
    this(0);
  }

  public int get() {
    return value;
  }

  public void set(int val) {
    value = val;
  }

  public void increment() {
    value++;
  }
}
