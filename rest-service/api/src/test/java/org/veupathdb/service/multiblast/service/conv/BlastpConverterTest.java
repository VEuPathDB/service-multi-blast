package org.veupathdb.service.multiblast.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.IOBlastpScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.p.BlastpScoringMatrix;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BlastpConverter")
class BlastpConverterTest
{
  @Nested
  @DisplayName("::toExternal(BlastpScoringMatrix)")
  class ToExternal2
  {
    @Test
    @DisplayName("Correctly converts BlastpScoringMatrix values.")
    void test1() {
      assertEquals(IOBlastpScoringMatrix.BLOSUM45, BlastpConverter.toExternal(BlastpScoringMatrix.Blosum45));
      assertEquals(IOBlastpScoringMatrix.BLOSUM50, BlastpConverter.toExternal(BlastpScoringMatrix.Blosum50));
      assertEquals(IOBlastpScoringMatrix.BLOSUM62, BlastpConverter.toExternal(BlastpScoringMatrix.Blosum62));
      assertEquals(IOBlastpScoringMatrix.BLOSUM80, BlastpConverter.toExternal(BlastpScoringMatrix.Blosum80));
      assertEquals(IOBlastpScoringMatrix.BLOSUM90, BlastpConverter.toExternal(BlastpScoringMatrix.Blosum90));
      assertEquals(IOBlastpScoringMatrix.PAM30, BlastpConverter.toExternal(BlastpScoringMatrix.Pam30));
      assertEquals(IOBlastpScoringMatrix.PAM70, BlastpConverter.toExternal(BlastpScoringMatrix.Pam70));
      assertEquals(IOBlastpScoringMatrix.PAM250, BlastpConverter.toExternal(BlastpScoringMatrix.Pam250));
      assertEquals(IOBlastpScoringMatrix.IDENTITY, BlastpConverter.toExternal(BlastpScoringMatrix.Identity));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastpConverter.toExternal((BlastpScoringMatrix) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOBlastpscoringmatrix)")
  class ToInternal2
  {
    @Test
    @DisplayName("Correctly converts IOBlastpscoringmatrix values.")
    void test1() {
      assertEquals(BlastpScoringMatrix.Blosum45, BlastpConverter.toInternal(IOBlastpScoringMatrix.BLOSUM45));
      assertEquals(BlastpScoringMatrix.Blosum50, BlastpConverter.toInternal(IOBlastpScoringMatrix.BLOSUM50));
      assertEquals(BlastpScoringMatrix.Blosum62, BlastpConverter.toInternal(IOBlastpScoringMatrix.BLOSUM62));
      assertEquals(BlastpScoringMatrix.Blosum80, BlastpConverter.toInternal(IOBlastpScoringMatrix.BLOSUM80));
      assertEquals(BlastpScoringMatrix.Blosum90, BlastpConverter.toInternal(IOBlastpScoringMatrix.BLOSUM90));
      assertEquals(BlastpScoringMatrix.Pam30, BlastpConverter.toInternal(IOBlastpScoringMatrix.PAM30));
      assertEquals(BlastpScoringMatrix.Pam70, BlastpConverter.toInternal(IOBlastpScoringMatrix.PAM70));
      assertEquals(BlastpScoringMatrix.Pam250, BlastpConverter.toInternal(IOBlastpScoringMatrix.PAM250));
      assertEquals(BlastpScoringMatrix.Identity, BlastpConverter.toInternal(IOBlastpScoringMatrix.IDENTITY));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastpConverter.toInternal((IOBlastpScoringMatrix) null));
    }
  }
}