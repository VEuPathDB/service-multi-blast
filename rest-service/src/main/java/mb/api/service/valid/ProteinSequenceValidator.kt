package mb.api.service.valid

import mb.lib.config.Config

/**
 * Validates that a given character or sequence falls within the legal character
 * set for protein sequences.
 * <p>
 * Legal character set:
 * <pre>
 * ABCDEFGHI KLMN PQRSTUVWXYZ*-
 * abcdefghi klmn pqrstuvwxyz
 * </pre>
 */
internal object ProteinSequenceValidator: SequenceValidator
{
  override val kind = "protein"

  override fun isValid(c: Char): Boolean {
    return when (c) {
      in 'A'..'I', in 'a'..'i' -> true
      in 'K'..'N', in 'k'..'n' -> true
      in 'P'..'Z', in 'p'..'z' -> true
      '-', '*'                 -> true
      else                     -> false
    }
  }

  override val maxSeqLength = Config.maxAASeqSize
}
