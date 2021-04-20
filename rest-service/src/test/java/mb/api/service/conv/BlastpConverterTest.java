package mb.api.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import mb.api.model.blast.IOBlastpScoringMatrix;
import mb.lib.blast.model.p.BlastpScoringMatrix;

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
      Assertions.assertEquals(IOBlastpScoringMatrix.BLOSUM45, BlastpConverter.toExternal(BlastpScoringMatrix.Blosum45));
      Assertions.assertEquals(IOBlastpScoringMatrix.BLOSUM50, BlastpConverter.toExternal(BlastpScoringMatrix.Blosum50));
      Assertions.assertEquals(IOBlastpScoringMatrix.BLOSUM62, BlastpConverter.toExternal(BlastpScoringMatrix.Blosum62));
      Assertions.assertEquals(IOBlastpScoringMatrix.BLOSUM80, BlastpConverter.toExternal(BlastpScoringMatrix.Blosum80));
      Assertions.assertEquals(IOBlastpScoringMatrix.BLOSUM90, BlastpConverter.toExternal(BlastpScoringMatrix.Blosum90));
      Assertions.assertEquals(IOBlastpScoringMatrix.PAM30, BlastpConverter.toExternal(BlastpScoringMatrix.Pam30));
      Assertions.assertEquals(IOBlastpScoringMatrix.PAM70, BlastpConverter.toExternal(BlastpScoringMatrix.Pam70));
      Assertions.assertEquals(IOBlastpScoringMatrix.PAM250, BlastpConverter.toExternal(BlastpScoringMatrix.Pam250));
      Assertions.assertEquals(IOBlastpScoringMatrix.IDENTITY, BlastpConverter.toExternal(BlastpScoringMatrix.Identity));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(BlastpConverter.toExternal((BlastpScoringMatrix) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOBlastpscoringmatrix)")
  class ToInternal2
  {
    @Test
    @DisplayName("Correctly converts IOBlastpscoringmatrix values.")
    void test1() {
      Assertions.assertEquals(BlastpScoringMatrix.Blosum45, BlastpConverter.toInternal(IOBlastpScoringMatrix.BLOSUM45));
      Assertions.assertEquals(BlastpScoringMatrix.Blosum50, BlastpConverter.toInternal(IOBlastpScoringMatrix.BLOSUM50));
      Assertions.assertEquals(BlastpScoringMatrix.Blosum62, BlastpConverter.toInternal(IOBlastpScoringMatrix.BLOSUM62));
      Assertions.assertEquals(BlastpScoringMatrix.Blosum80, BlastpConverter.toInternal(IOBlastpScoringMatrix.BLOSUM80));
      Assertions.assertEquals(BlastpScoringMatrix.Blosum90, BlastpConverter.toInternal(IOBlastpScoringMatrix.BLOSUM90));
      Assertions.assertEquals(BlastpScoringMatrix.Pam30, BlastpConverter.toInternal(IOBlastpScoringMatrix.PAM30));
      Assertions.assertEquals(BlastpScoringMatrix.Pam70, BlastpConverter.toInternal(IOBlastpScoringMatrix.PAM70));
      Assertions.assertEquals(BlastpScoringMatrix.Pam250, BlastpConverter.toInternal(IOBlastpScoringMatrix.PAM250));
      Assertions.assertEquals(BlastpScoringMatrix.Identity, BlastpConverter.toInternal(IOBlastpScoringMatrix.IDENTITY));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(BlastpConverter.toInternal((IOBlastpScoringMatrix) null));
    }
  }
}