package org.veupathdb.service.multiblast.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.IOTBlastxScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.tx.TBlastxScoringMatrix;

import static org.junit.jupiter.api.Assertions.*;

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
      assertEquals(IOTBlastxScoringMatrix.BLOSUM45, TBlastxConverter.toExternal(TBlastxScoringMatrix.Blosum45));
      assertEquals(IOTBlastxScoringMatrix.BLOSUM50, TBlastxConverter.toExternal(TBlastxScoringMatrix.Blosum50));
      assertEquals(IOTBlastxScoringMatrix.BLOSUM62, TBlastxConverter.toExternal(TBlastxScoringMatrix.Blosum62));
      assertEquals(IOTBlastxScoringMatrix.BLOSUM80, TBlastxConverter.toExternal(TBlastxScoringMatrix.Blosum80));
      assertEquals(IOTBlastxScoringMatrix.BLOSUM90, TBlastxConverter.toExternal(TBlastxScoringMatrix.Blosum90));
      assertEquals(IOTBlastxScoringMatrix.PAM30, TBlastxConverter.toExternal(TBlastxScoringMatrix.Pam30));
      assertEquals(IOTBlastxScoringMatrix.PAM70, TBlastxConverter.toExternal(TBlastxScoringMatrix.Pam70));
      assertEquals(IOTBlastxScoringMatrix.PAM250, TBlastxConverter.toExternal(TBlastxScoringMatrix.Pam250));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(TBlastxConverter.toExternal(null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOTBlastxScoringMatrix)")
  class ToInternal1
  {
    @Test
    @DisplayName("Correctly converts IOTBlastxScoringMatrix values.")
    void test1() {
      assertEquals(TBlastxScoringMatrix.Blosum45, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.BLOSUM45));
      assertEquals(TBlastxScoringMatrix.Blosum50, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.BLOSUM50));
      assertEquals(TBlastxScoringMatrix.Blosum62, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.BLOSUM62));
      assertEquals(TBlastxScoringMatrix.Blosum80, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.BLOSUM80));
      assertEquals(TBlastxScoringMatrix.Blosum90, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.BLOSUM90));
      assertEquals(TBlastxScoringMatrix.Pam250, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.PAM250));
      assertEquals(TBlastxScoringMatrix.Pam30, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.PAM30));
      assertEquals(TBlastxScoringMatrix.Pam70, TBlastxConverter.toInternal(IOTBlastxScoringMatrix.PAM70));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(TBlastxConverter.toInternal((IOTBlastxScoringMatrix) null));
    }
  }
}