package mb.lib.blast.model;

import mb.api.service.valid.SequenceValidator;
import mb.api.service.valid.SequenceValidationError;
import org.veupathdb.lib.blast.BlastTool;

public class BlastSubQuery
{
  private final BlastTool tool;
  private final String defline;
  private final String sequence;

  public BlastSubQuery(BlastTool tool, String defline, String sequence) {
    this.tool     = tool;
    this.defline  = defline;
    this.sequence = sequence;
  }

  public BlastTool getTool() {
    return tool;
  }

  public String getDefline() {
    return defline;
  }

  public String getSequence() {
    return sequence;
  }

  public SequenceValidationError validate(int sequenceNum) {
    return SequenceValidator.getValidator(getTool()).validate(sequenceNum, getSequence());
  }

  @Override
  public String toString() {
    var tmp = new StringBuilder(
      (defline == null || defline.isBlank() ? 0 : defline.length() + 1) +
      (sequence == null || sequence.isBlank() ? 0 : sequence.length())
    );

    if (defline != null && !defline.isBlank())
      tmp.append(defline).append("\n");
    if (sequence != null && !sequence.isBlank())
      tmp.append(sequence);

    return tmp.toString();
  }
}
