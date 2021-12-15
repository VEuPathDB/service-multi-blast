package mb.lib.util;

internal data class IntCounter(var value: Int = 0) {
  fun increment() {
    value++;
  }
}
