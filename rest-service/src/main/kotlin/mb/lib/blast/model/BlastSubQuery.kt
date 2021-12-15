package mb.lib.blast.model

import mb.api.service.valid.SequenceValidator
import org.veupathdb.lib.blast.BlastTool

data class BlastSubQuery(
  val tool:     BlastTool,
  val defline:  String?,
  val sequence: String,
) {
  fun validate(sequenceNum: Int) = SequenceValidator.getValidator(tool).validate(sequenceNum, sequence)

  override fun toString(): String {
    val tmp = StringBuilder((defline?.length?.plus(1) ?: 0) + sequence.length)

    if (!defline.isNullOrBlank())
      tmp.append(defline).append("\n")
    if (sequence.isNotBlank())
      tmp.append(sequence)

    return tmp.toString()
  }
}
