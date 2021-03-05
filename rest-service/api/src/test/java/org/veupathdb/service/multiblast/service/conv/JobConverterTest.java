package org.veupathdb.service.multiblast.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.IOBlastTool;
import org.veupathdb.service.multiblast.model.blast.BlastTool;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JobConverter")
class JobConverterTest
{
  @Nested
  @DisplayName("::toInternal(IOBlastTool)")
  class ToInternal1
  {
    @Test
    @DisplayName("Correctly converts IOBlastTool values.")
    void test1() {
      assertEquals(BlastTool.BlastN, JobConverter.toInternal(IOBlastTool.BLASTN));
      assertEquals(BlastTool.BlastP, JobConverter.toInternal(IOBlastTool.BLASTP));
      assertEquals(BlastTool.BlastX, JobConverter.toInternal(IOBlastTool.BLASTX));
      assertEquals(BlastTool.TBlastN, JobConverter.toInternal(IOBlastTool.TBLASTN));
      assertEquals(BlastTool.TBlastX, JobConverter.toInternal(IOBlastTool.TBLASTX));
    }
  }
}