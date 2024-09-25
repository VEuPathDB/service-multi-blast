package mb.lib.query

internal abstract class AbstractMBlastSubQuery : MBlastSubQuery {
  private companion object {
    val whitespace = Regex("[\\s\\n\\r]")
    val matcher    = Regex(".{1,80}")
  }

  override fun toStandardString() = toStdString().byteInputStream()

  override fun toString() = toStdString()

  private fun toStdString(): String {
    val seq = sequence.replace(whitespace, "")
    val out = StringBuilder(seq.length + (defLine?.length ?: 0) + 100)

    defLine?.run { out.append(this).append('\n') }

    matcher.findAll(seq)
      .map { it.value }
      .forEach { out.append(it).append('\n') }

    return out.toString().trim()
  }
}
