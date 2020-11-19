package org.veupathdb.service.multiblast.service.valid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.InputBlastpConfig;
import org.veupathdb.service.multiblast.generated.model.InputBlastpConfigImpl;
import org.veupathdb.service.multiblast.model.ErrorMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

@DisplayName("BlastPValidator")
class BlastPValidatorTest
{
  private ErrorMap          err;
  private InputBlastpConfig conf;

  @BeforeEach
  void setUp() {
    err = new ErrorMap();
    conf = new InputBlastpConfigImpl();
  }

  @Nested
  @DisplayName("#validateBestHitScoreEdge(ErrorMap, InputBlastpConfig)")
  class ValidateBestHitScoreEdge
  {
    @Test
    @DisplayName("does nothing if the " + BestHitScoreEdge + " value is null")
    void test1() {
      BlastPValidator.validateBestHitScoreEdge(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects values less than or equal to 0")
    void test2() {
      conf.setBestHitScoreEdge(0);
      BlastPValidator.validateBestHitScoreEdge(err, conf);
      confirmSingleError(BestHitScoreEdge, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }

    @Test
    @DisplayName("rejects values greater than or equal to 0.5")
    void test3() {
      conf.setBestHitScoreEdge(0.5);
      BlastPValidator.validateBestHitScoreEdge(err, conf);
      confirmSingleError(BestHitScoreEdge, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }
  }

  @Nested
  @DisplayName("#validateBestHitOverhang(ErrorMap, InputBlastpConfig)")
  class ValidateBestHitOverhang
  {
    @Test
    @DisplayName("does nothing if the " + BestHitOverhang + " value is null")
    void test1() {
      BlastPValidator.validateBestHitOverhang(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects values less than or equal to 0")
    void test2() {
      conf.setBestHitOverhang(0);
      BlastPValidator.validateBestHitOverhang(err, conf);
      confirmSingleError(BestHitOverhang, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }

    @Test
    @DisplayName("rejects values greater than or equal to 0.5")
    void test3() {
      conf.setBestHitOverhang(0.5);
      BlastPValidator.validateBestHitOverhang(err, conf);
      confirmSingleError(BestHitOverhang, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }
  }

  @Nested
  @DisplayName("#validateCullingLimit(ErrorMap, InputBlastpConfig)")
  class ValidateCullingLimit
  {
    @Test
    @DisplayName("does nothing if the " + CullingLimit + " value is null")
    void test1() {
      BlastPValidator.validateCullingLimit(err, conf);
      assertTrue(err.isEmpty());
    }
  }


  @Nested
  @DisplayName("#validateDbHardMask(ErrorMap, InputBlastpConfig)")
  class ValidateDbHardMask
  {
    @Test
    @DisplayName("does nothing if the " + DBHardMask + " value is null")
    void test1() {
      BlastPValidator.validateDbHardMask(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateDbSoftMask(ErrorMap, InputBlastpConfig)")
  class ValidateDbSoftMask
  {
    @Test
    @DisplayName("does nothing if the " + DBSoftMask + " value is null")
    void test1() {
      BlastPValidator.validateDbSoftMask(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateTaxIds(ErrorMap, InputBlastpConfig)")
  class ValidateTaxIds
  {
    @Test
    @DisplayName("does nothing if the " + TaxIDs + " value is null")
    void test1() {
      BlastPValidator.validateTaxIds(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateNegativeTaxIds(ErrorMap, InputBlastpConfig)")
  class ValidateNegativeTaxIds
  {
    @Test
    @DisplayName("does nothing if the " + NegativeTaxIDs + " value is null")
    void test1() {
      BlastPValidator.validateNegativeTaxIds(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateSoftMasking(ErrorMap, InputBlastpConfig")
  class ValidateSoftMasking
  {
    @Test
    @DisplayName("does nothing if the " + SoftMasking + " value is null")
    void test1() {
      BlastPValidator.validateSoftMasking(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateSubjectLoc(ErrorMap, InputBlastpConfig)")
  class ValidateSubjectLoc
  {
    @Test
    @DisplayName("does nothing if the " + SubjectLocation + " value is null")
    void test1() {
      BlastPValidator.validateSubjectLoc(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateMatrix(ErrorMap, InputBlastpConfig")
  class ValidateMatrix
  {
    @Test
    @DisplayName("does nothing if the " + Matrix + " value is null")
    void test1() {
      BlastPValidator.validateMatrix(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateGapExtend(ErrorMap, InputBlastpConfig")
  class ValidateGapExtend
  {
    @Test
    @DisplayName("does nothing if the " + GapExtend + " value is null")
    void test1() {
      BlastPValidator.validateGapExtend(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateGapOpen(ErrorMap, InputBlastpConfig")
  class ValidateGapOpen
  {
    @Test
    @DisplayName("does nothing if the " + GapOpen + " value is null")
    void test1() {
      BlastPValidator.validateGapOpen(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  private void confirmSingleError(String key, String error) {
    assertEquals(1, err.size());
    assertTrue(err.containsKey(key));
    assertNotNull(err.get(key));
    assertEquals(1, err.get(key).size());
    assertEquals(error, err.get(key).get(0));
  }

}
