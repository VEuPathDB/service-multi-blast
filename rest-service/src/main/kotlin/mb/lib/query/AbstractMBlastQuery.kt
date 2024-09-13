package mb.lib.query

internal abstract class AbstractMBlastQuery : MBlastQuery {
  /**
   * Concatenates all the sequences in this query into a single query string.
   */
  override fun getFullQuery(): String =
    sequences.asSequence()
      .map { it.toStandardString() }
      .joinToString("\n")
}
