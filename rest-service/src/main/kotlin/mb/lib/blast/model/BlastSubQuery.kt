package mb.lib.blast.model

import mb.api.service.valid.SequenceValidator
import mb.lib.query.AbstractMBlastSubQuery
import mb.lib.query.MBlastSubQuery
import org.veupathdb.lib.blast.BlastTool

/**
 * Represents a single sequence in a multi-blast query.
 *
 * @param index Index of this sequence in the parent query.
 *
 * @param tool Target BLAST+ tool for this sequence.
 *
 * @param defLine Sequence definition line.
 *
 * @param sequence Raw, unformatted sequence from the client input.
 */
internal class BlastSubQuery(
  index:    Int,
  tool:     BlastTool,
  defLine:  String?,
  sequence: String
) : MBlastSubQuery, AbstractMBlastSubQuery() {
  val tool  = tool
  val index = index

  override val defLine  = defLine
  override val sequence = sequence.trim()

  fun validate(sequenceNum: Int) =
    SequenceValidator.getValidator(tool).validate(sequenceNum, sequence)
}
