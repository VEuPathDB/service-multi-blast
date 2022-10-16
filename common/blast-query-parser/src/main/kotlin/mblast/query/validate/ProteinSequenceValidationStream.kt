package mblast.query.validate

import mblast.util.io.InStream

/**
 * # Protein Sequence Validation Stream
 *
 * [SequenceValidationStream] implementation that validates the streamed query
 * sequences against the following rules:
 *
 * 1. All characters in the query sequences are in the legal character set for
 * protein sequences.
 *
 * 2. No sequences in the query contain more than the configured max number of
 * non-ignorable characters.
 *
 * 3. The overall query contains no more than the configured max number of
 * sequences.
 *
 * 4. The overall query contains no more than the configured max number of
 * non-ignorable characters.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  1.0.0
 *
 * @constructor Constructs a new `ProteinSequenceValidationStream` instance.
 *
 * @param maxSequenceLength Max number of non-ignorable characters that may
 * appear in a single sequence.
 *
 * @param maxSequenceCount Max number of sequences that may appear in the
 * overall query.
 *
 * @param maxTotalLength Max number of non-ignorable characters that may appear
 * in the overall query.
 *
 * @param queryStream Input stream this instance will wrap.
 */
class ProteinSequenceValidationStream(
  maxSequenceLength: Int,
  maxSequenceCount: Int,
  maxTotalLength: Int,
  queryStream: InStream,
) : SequenceValidationStream(
  maxSequenceLength,
  maxSequenceCount,
  maxTotalLength,
  queryStream
) {

  override val sequenceType: SequenceType
    get() = SequenceType.Protein

  override fun isValid(c: Char) =
    when (c) {
      in 'A'..'I', in 'a'..'i' -> true
      in 'K'..'N', in 'k'..'n' -> true
      in 'P'..'Z', in 'p'..'z' -> true
      '-', '*'                 -> true
      else                     -> false
    }
}
