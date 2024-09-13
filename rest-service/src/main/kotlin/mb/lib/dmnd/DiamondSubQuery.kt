package mb.lib.dmnd

import mb.api.service.valid.SequenceValidator
import mb.lib.query.AbstractMBlastSubQuery
import mb.lib.query.MBlastSubQuery
import org.veupathdb.lib.cli.diamond.DiamondCommand

/**
 * Represents a single sequence in a multi-diamond query.
 *
 * @param index Index of this sequence in the parent query.
 *
 * @param tool Target DIAMOND tool for this sequence.
 *
 * @param defLine Sequence definition line.
 *
 * @param sequence Raw, unformatted sequence from the client input.
 */
internal class DiamondSubQuery(
  index:    Int,
  tool:     DiamondCommand,
  defLine:  String?,
  sequence: String
) : MBlastSubQuery, AbstractMBlastSubQuery() {
  val index = index
  val tool = tool

  override val defLine = defLine
  override val sequence = sequence.trim()

  fun validate(sequenceNum: Int) =
    SequenceValidator.getValidator(tool).validate(sequenceNum, sequence)
}
