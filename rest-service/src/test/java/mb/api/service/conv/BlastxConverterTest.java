package mb.api.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import mb.api.model.blast.IOBlastxScoringMatrix;
import mb.lib.blast.model.x.BlastxScoringMatrix;

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
      Assertions.assertEquals(IOBlastxScoringMatrix.BLOSUM45, BlastxConverter.toExternal(BlastxScoringMatrix.Blosum45));
      Assertions.assertEquals(IOBlastxScoringMatrix.BLOSUM50, BlastxConverter.toExternal(BlastxScoringMatrix.Blosum50));
      Assertions.assertEquals(IOBlastxScoringMatrix.BLOSUM62, BlastxConverter.toExternal(BlastxScoringMatrix.Blosum62));
      Assertions.assertEquals(IOBlastxScoringMatrix.BLOSUM80, BlastxConverter.toExternal(BlastxScoringMatrix.Blosum80));
      Assertions.assertEquals(IOBlastxScoringMatrix.BLOSUM90, BlastxConverter.toExternal(BlastxScoringMatrix.Blosum90));
      Assertions.assertEquals(IOBlastxScoringMatrix.PAM30, BlastxConverter.toExternal(BlastxScoringMatrix.Pam30));
      Assertions.assertEquals(IOBlastxScoringMatrix.PAM70, BlastxConverter.toExternal(BlastxScoringMatrix.Pam70));
      Assertions.assertEquals(IOBlastxScoringMatrix.PAM250, BlastxConverter.toExternal(BlastxScoringMatrix.Pam250));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(BlastxConverter.toExternal((BlastxScoringMatrix) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOBlastxScoringMatrix)")
  class ToInternal1
  {
    @Test
    @DisplayName("Correctly converts IOBlastxScoringMatrix values.")
    void test1() {
      Assertions.assertEquals(BlastxScoringMatrix.Blosum45, BlastxConverter.toInternal(IOBlastxScoringMatrix.BLOSUM45));
      Assertions.assertEquals(BlastxScoringMatrix.Blosum50, BlastxConverter.toInternal(IOBlastxScoringMatrix.BLOSUM50));
      Assertions.assertEquals(BlastxScoringMatrix.Blosum62, BlastxConverter.toInternal(IOBlastxScoringMatrix.BLOSUM62));
      Assertions.assertEquals(BlastxScoringMatrix.Blosum80, BlastxConverter.toInternal(IOBlastxScoringMatrix.BLOSUM80));
      Assertions.assertEquals(BlastxScoringMatrix.Blosum90, BlastxConverter.toInternal(IOBlastxScoringMatrix.BLOSUM90));
      Assertions.assertEquals(BlastxScoringMatrix.Pam30, BlastxConverter.toInternal(IOBlastxScoringMatrix.PAM30));
      Assertions.assertEquals(BlastxScoringMatrix.Pam70, BlastxConverter.toInternal(IOBlastxScoringMatrix.PAM70));
      Assertions.assertEquals(BlastxScoringMatrix.Pam250, BlastxConverter.toInternal(IOBlastxScoringMatrix.PAM250));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(BlastxConverter.toInternal((IOBlastxScoringMatrix) null));
    }
  }
}