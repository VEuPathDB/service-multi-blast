package org.veupathdb.service.multiblast.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.IOBlastxScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.x.BlastxScoringMatrix;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BlastxConverter")
class BlastxConverterTest
{
  @Nested
  @DisplayName("::toExternal(BlastxScoringMatrix)")
  class ToExternal2
  {
    @Test
    @DisplayName("Correctly converts BlastxScoringMatrix values.")
    void test1() {
      assertEquals(IOBlastxScoringMatrix.BLOSUM45, BlastxConverter.toExternal(BlastxScoringMatrix.Blosum45));
      assertEquals(IOBlastxScoringMatrix.BLOSUM50, BlastxConverter.toExternal(BlastxScoringMatrix.Blosum50));
      assertEquals(IOBlastxScoringMatrix.BLOSUM62, BlastxConverter.toExternal(BlastxScoringMatrix.Blosum62));
      assertEquals(IOBlastxScoringMatrix.BLOSUM80, BlastxConverter.toExternal(BlastxScoringMatrix.Blosum80));
      assertEquals(IOBlastxScoringMatrix.BLOSUM90, BlastxConverter.toExternal(BlastxScoringMatrix.Blosum90));
      assertEquals(IOBlastxScoringMatrix.PAM30, BlastxConverter.toExternal(BlastxScoringMatrix.Pam30));
      assertEquals(IOBlastxScoringMatrix.PAM70, BlastxConverter.toExternal(BlastxScoringMatrix.Pam70));
      assertEquals(IOBlastxScoringMatrix.PAM250, BlastxConverter.toExternal(BlastxScoringMatrix.Pam250));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastxConverter.toExternal((BlastxScoringMatrix) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOBlastxScoringMatrix)")
  class ToInternal1
  {
    @Test
    @DisplayName("Correctly converts IOBlastxScoringMatrix values.")
    void test1() {
      assertEquals(BlastxScoringMatrix.Blosum45, BlastxConverter.toInternal(IOBlastxScoringMatrix.BLOSUM45));
      assertEquals(BlastxScoringMatrix.Blosum50, BlastxConverter.toInternal(IOBlastxScoringMatrix.BLOSUM50));
      assertEquals(BlastxScoringMatrix.Blosum62, BlastxConverter.toInternal(IOBlastxScoringMatrix.BLOSUM62));
      assertEquals(BlastxScoringMatrix.Blosum80, BlastxConverter.toInternal(IOBlastxScoringMatrix.BLOSUM80));
      assertEquals(BlastxScoringMatrix.Blosum90, BlastxConverter.toInternal(IOBlastxScoringMatrix.BLOSUM90));
      assertEquals(BlastxScoringMatrix.Pam30, BlastxConverter.toInternal(IOBlastxScoringMatrix.PAM30));
      assertEquals(BlastxScoringMatrix.Pam70, BlastxConverter.toInternal(IOBlastxScoringMatrix.PAM70));
      assertEquals(BlastxScoringMatrix.Pam250, BlastxConverter.toInternal(IOBlastxScoringMatrix.PAM250));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastxConverter.toInternal((IOBlastxScoringMatrix) null));
    }
  }
}