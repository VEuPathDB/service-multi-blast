package org.veupathdb.service.multiblast.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.IOTBlastnScoringMatrix;
import org.veupathdb.service.multiblast.generated.model.IOTBlastnTask;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastNScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastNTask;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TBlastnConverter")
class TBlastnConverterTest
{
  @Nested
  @DisplayName("::toExternal(TBlastNTask)")
  class ToExternal1
  {
    @Test
    @DisplayName("Correctly converts TBlastNTask values.")
    void test1() {
      assertEquals(IOTBlastnTask.TBLASTN, TBlastnConverter.toExternal(TBlastNTask.TBlastN));
      assertEquals(IOTBlastnTask.TBLASTNFAST, TBlastnConverter.toExternal(TBlastNTask.TBlastNFast));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(TBlastnConverter.toExternal((TBlastNTask) null));
    }
  }

  @Nested
  @DisplayName("::toExternal(TBlastNScoringMatrix)")
  class ToExternal2
  {
    @Test
    @DisplayName("Correctly converts TBlastNScoringMatrix values.")
    void test1() {
      assertEquals(IOTBlastnScoringMatrix.BLOSUM45, TBlastnConverter.toExternal(TBlastNScoringMatrix.Blosum45));
      assertEquals(IOTBlastnScoringMatrix.BLOSUM50, TBlastnConverter.toExternal(TBlastNScoringMatrix.Blosum50));
      assertEquals(IOTBlastnScoringMatrix.BLOSUM62, TBlastnConverter.toExternal(TBlastNScoringMatrix.Blosum62));
      assertEquals(IOTBlastnScoringMatrix.BLOSUM80, TBlastnConverter.toExternal(TBlastNScoringMatrix.Blosum80));
      assertEquals(IOTBlastnScoringMatrix.BLOSUM90, TBlastnConverter.toExternal(TBlastNScoringMatrix.Blosum90));
      assertEquals(IOTBlastnScoringMatrix.PAM30, TBlastnConverter.toExternal(TBlastNScoringMatrix.Pam30));
      assertEquals(IOTBlastnScoringMatrix.PAM70, TBlastnConverter.toExternal(TBlastNScoringMatrix.Pam70));
      assertEquals(IOTBlastnScoringMatrix.PAM250, TBlastnConverter.toExternal(TBlastNScoringMatrix.Pam250));
      assertEquals(IOTBlastnScoringMatrix.IDENTITY, TBlastnConverter.toExternal(TBlastNScoringMatrix.Identity));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(TBlastnConverter.toExternal((TBlastNScoringMatrix) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOBlastnTask)")
  class ToInternal1
  {
    @Test
    @DisplayName("Correctly converts IOBlastnTask values.")
    void test1() {
      assertEquals(TBlastNTask.TBlastN, TBlastnConverter.toInternal(IOTBlastnTask.TBLASTN));
      assertEquals(TBlastNTask.TBlastNFast, TBlastnConverter.toInternal(IOTBlastnTask.TBLASTNFAST));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(TBlastnConverter.toInternal((IOTBlastnTask) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOTBlastnScoringMatrix)")
  class ToInternal2
  {
    @Test
    @DisplayName("Correctly converts IOBlastnScoringMatrix")
    void test1() {
      assertEquals(TBlastNScoringMatrix.Blosum45, TBlastnConverter.toInternal(IOTBlastnScoringMatrix.BLOSUM45));
      assertEquals(TBlastNScoringMatrix.Blosum50, TBlastnConverter.toInternal(IOTBlastnScoringMatrix.BLOSUM50));
      assertEquals(TBlastNScoringMatrix.Blosum62, TBlastnConverter.toInternal(IOTBlastnScoringMatrix.BLOSUM62));
      assertEquals(TBlastNScoringMatrix.Blosum80, TBlastnConverter.toInternal(IOTBlastnScoringMatrix.BLOSUM80));
      assertEquals(TBlastNScoringMatrix.Blosum90, TBlastnConverter.toInternal(IOTBlastnScoringMatrix.BLOSUM90));
      assertEquals(TBlastNScoringMatrix.Pam30, TBlastnConverter.toInternal(IOTBlastnScoringMatrix.PAM30));
      assertEquals(TBlastNScoringMatrix.Pam70, TBlastnConverter.toInternal(IOTBlastnScoringMatrix.PAM70));
      assertEquals(TBlastNScoringMatrix.Pam250, TBlastnConverter.toInternal(IOTBlastnScoringMatrix.PAM250));
      assertEquals(TBlastNScoringMatrix.Identity, TBlastnConverter.toInternal(IOTBlastnScoringMatrix.IDENTITY));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(TBlastnConverter.toInternal((IOTBlastnScoringMatrix) null));
    }
  }
}