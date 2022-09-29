package mb.query.parse

/**
 * Multi-Sequence Blast Query
 */
data class MBlastQuery(val sequences: List<BlastSequence>) {

  override fun toString(): String {
    var size = 0

    for (seq in sequences) {
      if (seq.defLine != null) {
        size += seq.defLine.length
        size++
      }

      size += seq.sequence.length
      size++
    }

    val buffer = StringBuilder(size)

    for (seq in sequences) {
      if (seq.defLine != null)
        buffer.appendLine(seq.defLine)

      buffer.appendLine(seq.sequence)
    }

    return buffer.substring(0, buffer.length-1)
  }
}