package org.veupathdb.service.mblast.query.blast.validate

/**
 * Validation Error Map
 *
 * A wrapper around `Map<String, List<String>>` with convenience methods for
 * appending errors.
 */
class ErrorMap(private val raw: MutableMap<String, MutableList<String>>) {
  fun blastField(key: String, error: String) {
    raw.computeIfAbsent("blastConfig.$key") { ArrayList(1) }
      .add(error)
  }

  fun isEmpty() = raw.isEmpty()

  fun isNotEmpty() = raw.isNotEmpty()

  fun getRaw(): Map<String, List<String>> = raw
}