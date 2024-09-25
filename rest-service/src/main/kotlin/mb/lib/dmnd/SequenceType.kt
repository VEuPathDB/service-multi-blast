package mb.lib.dmnd

enum class SequenceType {
  Nucleotide,
  Protein,
  ;

  override fun toString() = name.lowercase()
}
