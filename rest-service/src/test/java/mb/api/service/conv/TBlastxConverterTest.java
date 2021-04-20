package mb.api.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import mb.api.model.blast.IOTBlastxScoringMatrix;
import mb.lib.blast.model.tx.TBlastxScoringMatrix;

@DisplayName("TBlastxConverter")
class TBlastxConverterTest
{
  @Nested
  @DisplayName("::toExternal(TBlastxScoringMatrix)")
  class ToExternal1
  {
    @Test
    @DisplayName("Correctly converts TBlastxScoringMatrix values.")
    void test1() {
      Assertions.assertEquals(IOTBlastxScoringMatrix.BLOSUM45, TBlastxConverter.toExternal(TBlastxScoringMatrix.Blosum45));
      Assertions.assertEquals(IOTBlastxScoringMatrix.BLOSUM50, TBlastxConverter.toExternal(TBlastxScoringMatrix.Blosum50));
      Assertions.assertEquals(IOTBlastxScoringMatrix.BLOSUM62, TBlastxConverter.toExternal(TBlastxScoringMatrix.Blosum62));
      Assertions.assertEquals(IOTBlastxScoringMatrix.BLOSUM80, TBlastxConverter.toExternal(TBlastxScoringMatrix.Blosum80));
      Assertions.assertEquals(IOTBlastxScoringMatrix.BLOSUM90, TBlastxConverter.toExternal(TBlastxScoringMatrix.Blosum90));
      Assertions.assertEquals(IOTBlastxScoringMatrix.PAM30, TBlastxConverter.toExternal(TBlastxScoringMatrix.Pam30));
      Assertions.assertEquals(IOTBlastxScoringMatrix.PAM70, TBlastxConverter.toExternal(TBlastxScoringMatrix.Pam70));
      Assertions.assertEquals(IOTBlastxScoringMatrix.PAM250, TBlastxConverter.toExternal(TBlastxScoringMatrix.Pam250));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(TBlastxConverter.toExternal(null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOTBlastxScoringMatrix)")
  class ToInternal1
  {
    @Test
    @DisplayName("Correctly converts IOTBlastxScoringMatrix values.")
    void test1() {
      Assertions.assertEquals(TBlastxScoringMatrix.Blosum45, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.BLOSUM45));
      Assertions.assertEquals(TBlastxScoringMatrix.Blosum50, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.BLOSUM50));
      Assertions.assertEquals(TBlastxScoringMatrix.Blosum62, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.BLOSUM62));
      Assertions.assertEquals(TBlastxScoringMatrix.Blosum80, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.BLOSUM80));
      Assertions.assertEquals(TBlastxScoringMatrix.Blosum90, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.BLOSUM90));
      Assertions.assertEquals(TBlastxScoringMatrix.Pam250, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.PAM250));
      Assertions.assertEquals(TBlastxScoringMatrix.Pam30, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.PAM30));
      Assertions.assertEquals(TBlastxScoringMatrix.Pam70, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.PAM70));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(TBlastxConverter.toInternal((IOTBlastxScoringMatrix) null));
    }
  }
}