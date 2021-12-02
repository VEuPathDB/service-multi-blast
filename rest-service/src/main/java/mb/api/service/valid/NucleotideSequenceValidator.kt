package mb.api.service.valid

import mb.lib.config.Config

/**
 * Validates that a given character or sequence falls within the legal character
 * set for nucleotide sequences.
 * <p>
 * Legal character set:
 * <pre>
 * ABCD GH K MN RSTUVW Y -
 * abcd gh k mn rstuvw y
 * </pre>
 */
object NucleotideSequenceValidator: SequenceValidator
{
  override val kind = "nucleotide"

  override fun isValid(c: Char) = when (c) {
    in 'A'..'D', in 'a'..'d' -> true
    in 'G'..'H', in 'g'..'h' -> true
    'K', 'k', 'Y', 'y', '-'  -> true
    in 'M'..'N', in 'm'..'n' -> true
    in 'R'..'W', in 'r'..'w' -> true
    else                     -> false
  }

  override val maxSeqLength = Config.maxNASeqSize
}
