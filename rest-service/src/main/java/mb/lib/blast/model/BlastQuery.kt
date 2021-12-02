package mb.lib.blast.model


import mb.api.service.valid.SequenceValidationError
import mb.api.service.valid.SequenceValidator
import org.veupathdb.lib.blast.BlastTool
import java.util.*

@Suppress("NOTHING_TO_INLINE")
inline fun String.parseAsQuery(tool: BlastTool): BlastQuery {
  val scanner    = Scanner(this)
  val subQueries = ArrayList<BlastSubQuery>()
  val buffer     = StringBuilder()

  var currentHeader: String? = null

  while (scanner.hasNextLine()) {
    val line = scanner.nextLine()

    if (line.startsWith(">")) {
      if (currentHeader != null) {
        subQueries.add(BlastSubQuery(tool, currentHeader, buffer.toString()))
        buffer.setLength(0)
      } else {
        currentHeader = line
      }
    } else {
      buffer.append(line).append("\n")
    }
  }

  if (buffer.isNotEmpty()) {
    subQueries.add(BlastSubQuery(tool, currentHeader, buffer.toString()))
  }

  return BlastQuery(tool, this, if (subQueries.size > 1) subQueries else emptyList())
}

class BlastQuery(val tool: BlastTool, val fullQuery: String, val subQueries: List<BlastSubQuery>) {

  val sequenceCount = if (subQueries.isEmpty()) 1 else subQueries.size

  fun validate(): SequenceValidationError? {
    subQueries.ifEmpty {
      return SequenceValidator.getValidator(tool).validate(1, fullQuery)
    }

    var seq = 1
    for (sub in subQueries) {
      val err = sub.validate(seq)
      if (err != null)
        return err
      seq++
    }

    return null
  }
}
