package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDBFile
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put
import org.veupathdb.lib.jackson.Json


internal fun ParseDBFiles(js: ObjectNode): DBFiles {
  val node = js[FlagDBFile] ?: return DBFiles()

  if (node.isTextual)
    return DBFiles(node.textValue().split(' '))

  if (node.isArray) {
    val tmp = ArrayList<String>(node.size())

    node.forEach {
      if (it.isTextual)
        tmp.add(it.textValue())
      else
        throw IllegalArgumentException("$FlagDBFile must be a string or array of strings.")
    }

    return DBFiles(tmp)
  }

  throw IllegalArgumentException("$FlagDBFile must be a string or array of strings.")
}

/**
 * -db `<String>`
 *
 * BLAST database names
 */
class DBFiles : BlastField {
  var files: List<String>

  override val isDefault: Boolean
    get() = files.isEmpty()

  override val name: String
    get() = FlagDBFile

  constructor() {
    files = emptyList()
  }

  constructor(vararg dbFiles: String) {
    files = dbFiles.toList()
  }

  constructor(dbFiles: Iterable<String>) {
    files = dbFiles.toList()
  }

  private constructor(dbFiles: List<String>) {
    files = dbFiles
  }

  override fun appendJson(js: ObjectNode) {
    if (!isDefault) {
      js.set<ObjectNode>(FlagDBFile, Json.newArray(files.size) { files.forEach { add(it) } })
    }
  }

  override fun appendCliSegment(cli: StringBuilder) {
    if (!isDefault) {
      cli.append(' ').append(FlagDBFile).append(" '")
      files.join(cli)
      cli.append('\'')
    }
  }

  override fun appendCliParts(cli: MutableList<String>) {
    if (!isDefault) {
      val sb = StringBuilder(files.fullSize())

      files.join(sb, false)

      cli.add(FlagDBFile)
      cli.add(sb.toString())
    }
  }

  override fun clone() = DBFiles(files.toList())
}

/**
 * Joins a list of strings into a given `StringBuilder` with a single space
 * separator between them.
 */
private fun List<String>.join(sb: StringBuilder, clean: Boolean = true) {
  sb.append(this[0].escape())

  for (i in 1 until size)
    if (clean)
      sb.append(' ').append(this[i].escape())
    else
      sb.append(' ').append(this[i])
}

private fun List<String>.fullSize(): Int {
  var out = this[0].length

  for (i in 1 until size)
    out += 1 + this[i].length

  return out
}

/**
 * Replaces instances of single quotes with bash escaping.
 */
private fun String.escape() =
  if (indexOf('\'') > -1)
    replace("'", "'\"'\"'")
  else
    this