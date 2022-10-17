package mblast.query.validate

enum class SequenceType {
  Nucleotide,
  Protein,
  ;

  override fun toString() = name.lowercase()
}