package org.veupathdb.service.mblast.report.valid

typealias ErrorMap = MutableMap<String, MutableList<String>>

fun NewErrorMap(): ErrorMap = HashMap()

fun ErrorMap.appendError(path: String, error: String) {
  computeIfAbsent(path) { ArrayList(1) }
    .add(error)
}