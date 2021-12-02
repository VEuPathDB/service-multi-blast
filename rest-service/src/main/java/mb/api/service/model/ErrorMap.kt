package mb.api.service.model


import mb.lib.blast.model.ToolOption

/**
 * ErrorMap is a convenience implementation of the map structure used by
 * {@code UnprocessableEntityException}s.
 */
class ErrorMap: HashMap<String, MutableList<String>> {
  /**
   * Constructs an empty {@code ErrorMap}.
   */
  constructor(): super()

  constructor(size: Int): super(size)

  /**
   * Constructs an {@code ErrorMap} containing the given key mapped to a list
   * containing the given value.
   *
   * @param key   Error key.
   * @param value Error value.
   */
  constructor(key: String, value: String): super(1) {
    putError(key, value)
  }

  /**
   * Constructs an {@code ErrorMap} containing the given key mapped to a list
   * containing the given value.
   *
   * @param key   Error key.
   * @param value Error value.
   */
  constructor(key: ToolOption, value: String): super(1) {
    putError(key, value)
  }

  fun putError(key: ToolOption, error: String): ErrorMap {
    putError(key.toString(), error)
    return this
  }

  fun putError(key: String, error: String): ErrorMap {
    computeIfAbsent(key, this::newList).add(error)
    return this
  }

  fun putError(prefix: String, key: String, error: String): ErrorMap {
    computeIfAbsent(prefix + "." + key, this::newList).add(error)
    return this
  }

  fun putError(prefix: String, key: String, errors: List<String>): ErrorMap {
    computeIfAbsent("$prefix.$key", this::newList).addAll(errors)
    return this
  }

  private fun newList(ignored: String): MutableList<String> {
    return ArrayList()
  }
}
