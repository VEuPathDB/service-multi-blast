package org.veupathdb.service.mblast.query.blast.validate

class ErrorMap(private val raw: MutableMap<String, MutableList<String>>) {
  fun blastField(key: String, error: String) {
    raw.computeIfAbsent("blastConfig.$key") { ArrayList(1) }
      .add(error)
  }

  fun isEmpty() = raw.isEmpty()

  fun isNotEmpty() = raw.isNotEmpty()

  fun getRaw(): Map<String, List<String>> = raw
}