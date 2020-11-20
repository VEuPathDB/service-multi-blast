package org.veupathdb.service.multiblast.service.valid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.InputBlastnConfig;
import org.veupathdb.service.multiblast.generated.model.InputBlastnConfigImpl;
import org.veupathdb.service.multiblast.model.ErrorMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

@DisplayName("BlastNValidator")
class BlastNValidatorTest
{
  private ErrorMap err;
  private InputBlastnConfig conf;

  @BeforeEach
  void setUp() {
    err = new ErrorMap();
    conf = new InputBlastnConfigImpl();
  }

  @Nested
  @DisplayName("#validateBestHitScoreEdge(ErrorMap, InputBlastnConfig)")
  class ValidateBestHitScoreEdge
  {
    @Test
    @DisplayName("does nothing if the " + BestHitScoreEdge + " value is null")
    void test1() {
      BlastNValidator.validateBestHitScoreEdge(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects values less than or equal to 0")
    void test2() {
      conf.setBestHitScoreEdge(0D);
      BlastNValidator.validateBestHitScoreEdge(err, conf);
      confirmSingleError(BestHitScoreEdge, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }

    @Test
    @DisplayName("rejects values greater than or equal to 0.5")
    void test3() {
      conf.setBestHitScoreEdge(0.5);
      BlastNValidator.validateBestHitScoreEdge(err, conf);
      confirmSingleError(BestHitScoreEdge, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }
  }

  @Nested
  @DisplayName("#validateBestHitOverhang(ErrorMap, InputBlastnConfig)")
  class ValidateBestHitOverhang
  {
    @Test
    @DisplayName("does nothing if the " + BestHitOverhang + " value is null")
    void test1() {
      BlastNValidator.validateBestHitOverhang(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects values less than or equal to 0")
    void test2() {
      conf.setBestHitOverhang(0D);
      BlastNValidator.validateBestHitOverhang(err, conf);
      confirmSingleError(BestHitOverhang, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }

    @Test
    @DisplayName("rejects values greater than or equal to 0.5")
    void test3() {
      conf.setBestHitOverhang(0.5);
      BlastNValidator.validateBestHitOverhang(err, conf);
      confirmSingleError(BestHitOverhang, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }
  }

  @Nested
  @DisplayName("#validateCullingLimit(ErrorMap, InputBlastnConfig)")
  class ValidateCullingLimit
  {
    @Test
    @DisplayName("does nothing if the " + CullingLimit + " value is null")
    void test1() {
      BlastNValidator.validateCullingLimit(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateNoGreedy(ErrorMap, InputBlastnConfig)")
  class ValidateNoGreedy
  {
    @Test
    @DisplayName("does nothing if the " + NonGreedy + " value is null")
    void test1() {
      BlastNValidator.validateNoGreedy(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateTemplateLength(ErrorMap, InputBlastnConfig)")
  class ValidateTemplateLength
  {
    @Test
    @DisplayName("does nothing if the " + TemplateLength + " value is null")
    void test1() {
      BlastNValidator.validateTemplateLength(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateTemplateType(ErrorMap, InputBlastnConfig)")
  class ValidateTemplateType
  {
    @Test
    @DisplayName("does nothing if the " + TemplateType + " value is null")
    void test1() {
      BlastNValidator.validateTemplateType(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateDbHardMask(ErrorMap, InputBlastnConfig)")
  class ValidateDbHardMask
  {
    @Test
    @DisplayName("does nothing if the " + DBHardMask + " value is null")
    void test1() {
      BlastNValidator.validateDbHardMask(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateDbSoftMask(ErrorMap, InputBlastnConfig)")
  class ValidateDbSoftMask
  {
    @Test
    @DisplayName("does nothing if the " + DBSoftMask + " value is null")
    void test1() {
      BlastNValidator.validateDbSoftMask(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateTaxIds(ErrorMap, InputBlastnConfig)")
  class ValidateTaxIds
  {
    @Test
    @DisplayName("does nothing if the " + TaxIDs + " value is null")
    void test1() {
      BlastNValidator.validateTaxIds(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateNegativeTaxIds(ErrorMap, InputBlastnConfig)")
  class ValidateNegativeTaxIds
  {
    @Test
    @DisplayName("does nothing if the " + NegativeTaxIDs + " value is null")
    void test1() {
      BlastNValidator.validateNegativeTaxIds(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateSubjectLoc(ErrorMap, InputBlastnConfig)")
  class ValidateSubjectLoc
  {
    @Test
    @DisplayName("does nothing if the " + SubjectLocation + " value is null")
    void test1() {
      BlastNValidator.validateSubjectLoc(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateWindowSize(ErrorMap, InputBlastnConfig)")
  class ValidateWindowSize
  {
    @Test
    @DisplayName("does nothing if the " + MultiHitWindowSize + " value is null")
    void test1() {
      BlastNValidator.validateWindowSize(err, conf);
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
