package org.veupathdb.service.multiblast.service.valid;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.InputBlastLocationImpl;
import org.veupathdb.service.multiblast.generated.model.InputBlastxConfig;
import org.veupathdb.service.multiblast.generated.model.InputBlastxConfigImpl;
import org.veupathdb.service.multiblast.model.ErrorMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;
import static org.veupathdb.service.multiblast.model.io.JsonKeys.BestHitScoreEdge;

class BlastXValidatorTest
{
  private InputBlastxConfig conf;
  private ErrorMap          err;

  @BeforeEach
  void setUp() {
    conf = new InputBlastxConfigImpl();
    err  = new ErrorMap();
  }

  @Nested
  @DisplayName("#validateBestHitScoreEdge(ErrorMap, InputBlastpConfig)")
  class ValidateBestHitScoreEdge
  {
    @Test
    @DisplayName("does nothing if the " + BestHitScoreEdge + " value is null")
    void test1() {
      BlastXValidator.validateBestHitScoreEdge(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects values less than or equal to 0")
    void test2() {
      conf.setBestHitScoreEdge(0D);
      BlastXValidator.validateBestHitScoreEdge(err, conf);
      confirmSingleError(BestHitScoreEdge, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }

    @Test
    @DisplayName("rejects values greater than or equal to 0.5")
    void test3() {
      conf.setBestHitScoreEdge(0.5);
      BlastXValidator.validateBestHitScoreEdge(err, conf);
      confirmSingleError(BestHitScoreEdge, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }

    @Test
    @DisplayName("rejects config when " + CullingLimit + " is not null")
    void test4() {
      conf.setBestHitScoreEdge(0.3);
      conf.setCullingLimit(1);
      BlastXValidator.validateBestHitScoreEdge(err, conf);
      confirmSingleError(
        BestHitScoreEdge,
        String.format(ConfigValidator.errIncompat, CullingLimit)
      );
    }
  }

  @Nested
  @DisplayName("#validateBestHitOverhang(ErrorMap, InputBlastpConfig)")
  class ValidateBestHitOverhang
  {
    @Test
    @DisplayName("does nothing if the " + BestHitOverhang + " value is null")
    void test1() {
      BlastXValidator.validateBestHitOverhang(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects values less than or equal to 0")
    void test2() {
      conf.setBestHitOverhang(0D);
      BlastXValidator.validateBestHitOverhang(err, conf);
      confirmSingleError(BestHitOverhang, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }

    @Test
    @DisplayName("rejects values greater than or equal to 0.5")
    void test3() {
      conf.setBestHitOverhang(0.5);
      BlastXValidator.validateBestHitOverhang(err, conf);
      confirmSingleError(BestHitOverhang, String.format(ConfigValidator.errBetweenExcF, 0D, 0.5));
    }

    @Test
    @DisplayName("rejects config when " + CullingLimit + " is not null")
    void test4() {
      conf.setBestHitOverhang(0.3);
      conf.setCullingLimit(1);
      BlastXValidator.validateBestHitOverhang(err, conf);
      confirmSingleError(
        BestHitOverhang,
        String.format(ConfigValidator.errIncompat, CullingLimit)
      );
    }
  }

  @Nested
  @DisplayName("#validateCullingLimit(ErrorMap, InputBlastpConfig)")
  class ValidateCullingLimit
  {
    @Test
    @DisplayName("does nothing if the " + CullingLimit + " value is null")
    void test1() {
      BlastXValidator.validateCullingLimit(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects values less than 0")
    void test2() {
      conf.setCullingLimit(-1);
      BlastXValidator.validateCullingLimit(err, conf);
      confirmSingleError(CullingLimit, String.format(ConfigValidator.errGtEqD, 0));
    }

    @Test
    @DisplayName("rejects config if " + BestHitOverhang + " is not null")
    void test3() {
      conf.setCullingLimit(1);
      conf.setBestHitOverhang(0.1);
      BlastXValidator.validateCullingLimit(err, conf);
      confirmSingleError(
        CullingLimit,
        String.format(ConfigValidator.errIncompat, BestHitOverhang)
      );
    }

    @Test
    @DisplayName("rejects config if " + BestHitScoreEdge + " is not null")
    void test4() {
      conf.setCullingLimit(1);
      conf.setBestHitScoreEdge(0.1);
      BlastXValidator.validateCullingLimit(err, conf);
      confirmSingleError(
        CullingLimit,
        String.format(ConfigValidator.errIncompat, BestHitScoreEdge)
      );
    }
  }

  @Nested
  @DisplayName("#validateDbHardMask(ErrorMap, InputBlastpConfig)")
  class ValidateDbHardMask
  {
    @Test
    @DisplayName("does nothing if the " + DBHardMask + " value is null")
    void test1() {
      BlastXValidator.validateDbHardMask(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects config if " + DBSoftMask + " is not null")
    void test2() {
      conf.setDbHardMask("hi");
      conf.setDbSoftMask("ho");
      BlastXValidator.validateDbHardMask(err, conf);
      confirmSingleError(
        DBHardMask,
        String.format(ConfigValidator.errIncompat, DBSoftMask)
      );
    }

    @Test
    @DisplayName("rejects config if " + SubjectLocation + " is not null")
    void test3() {
      conf.setDbHardMask("hi");
      conf.setSubjectLoc(new InputBlastLocationImpl());
      BlastXValidator.validateDbHardMask(err, conf);
      confirmSingleError(
        DBHardMask,
        String.format(ConfigValidator.errIncompat, SubjectLocation)
      );
    }
  }

  @Nested
  @DisplayName("#validateDbSoftMask(ErrorMap, InputBlastpConfig)")
  class ValidateDbSoftMask
  {
    @Test
    @DisplayName("does nothing if the " + DBSoftMask + " value is null")
    void test1() {
      BlastXValidator.validateDbSoftMask(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects config if " + DBHardMask + " is not null")
    void test2() {
      conf.setDbHardMask("hi");
      conf.setDbSoftMask("ho");
      BlastXValidator.validateDbSoftMask(err, conf);
      confirmSingleError(
        DBSoftMask,
        String.format(ConfigValidator.errIncompat, DBHardMask)
      );
    }

    @Test
    @DisplayName("rejects config if " + SubjectLocation + " is not null")
    void test3() {
      conf.setDbSoftMask("hi");
      conf.setSubjectLoc(new InputBlastLocationImpl());
      BlastXValidator.validateDbSoftMask(err, conf);
      confirmSingleError(
        DBSoftMask,
        String.format(ConfigValidator.errIncompat, SubjectLocation)
      );
    }
  }

  @Nested
  @DisplayName("#validateTaxIds(ErrorMap, InputBlastpConfig)")
  class ValidateTaxIds
  {
    @Test
    @DisplayName("does nothing if the " + TaxIDs + " value is null")
    void test1() {
      // create an error condition to verify it's ignored
      conf.setSubjectLoc(new InputBlastLocationImpl());

      BlastXValidator.validateTaxIDs(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("does nothing if the " + TaxIDs + " list is empty")
    void test2() {
      // create an error condition to verify it's ignored
      conf.setSubjectLoc(new InputBlastLocationImpl());

      conf.setTaxIds(Collections.emptyList());

      BlastXValidator.validateTaxIDs(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects the config if " + SubjectLocation + " is not null")
    void test3() {
      conf.setTaxIds(Collections.singletonList("hi"));
      conf.setSubjectLoc(new InputBlastLocationImpl());

      BlastXValidator.validateTaxIDs(err, conf);

      confirmSingleError(
        TaxIDs,
        String.format(ConfigValidator.errIncompat, SubjectLocation)
      );
    }
  }

  @Nested
  @DisplayName("#validateNegativeTaxIds(ErrorMap, InputBlastpConfig)")
  class ValidateNegativeTaxIds
  {
    @Test
    @DisplayName("does nothing if the " + NegativeTaxIDs + " value is null")
    void test1() {
      // create an error condition to verify it's ignored
      conf.setSubjectLoc(new InputBlastLocationImpl());

      BlastXValidator.validateNegativeTaxIDs(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("does nothing if the " + NegativeTaxIDs + " list is empty")
    void test2() {
      // create an error condition to verify it's ignored
      conf.setSubjectLoc(new InputBlastLocationImpl());

      conf.setNegativeTaxIds(Collections.emptyList());

      BlastXValidator.validateNegativeTaxIDs(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects the config if " + SubjectLocation + " is not null")
    void test3() {
      conf.setNegativeTaxIds(Collections.singletonList("hi"));
      conf.setSubjectLoc(new InputBlastLocationImpl());

      BlastXValidator.validateNegativeTaxIDs(err, conf);

      confirmSingleError(
        NegativeTaxIDs,
        String.format(ConfigValidator.errIncompat, SubjectLocation)
      );
    }
  }

  @Nested
  @DisplayName("#validateSubjectLoc(ErrorMap, InputBlastpConfig)")
  class ValidateSubjectLoc
  {
    @Nested
    @DisplayName("when " + SubjectLocation + " is null")
    class Null
    {
      @Test
      @DisplayName("and " + TaxIDs + " is not null, does nothing")
      void test1() {
        conf.setTaxIds(Collections.singletonList("hello"));

        BlastXValidator.validateSubjectLoc(err, conf);
        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("and " + NegativeTaxIDs + " is not null, does nothing")
      void test2() {
        conf.setNegativeTaxIds(Collections.singletonList("hello"));

        BlastXValidator.validateSubjectLoc(err, conf);
        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("and " + DBSoftMask + " is not null, does nothing")
      void test3() {
        conf.setDbSoftMask("hello");

        BlastXValidator.validateSubjectLoc(err, conf);
        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("and " + DBHardMask + " is not null, does nothing")
      void test4() {
        conf.setDbHardMask("hello");

        BlastXValidator.validateSubjectLoc(err, conf);
        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("when " + SubjectLocation + " is not null")
    class NotNull
    {
      @Test
      @DisplayName("and " + TaxIDs + " contains values, rejects the config")
      void test1() {
        conf.setTaxIds(Collections.singletonList("goodbye"));
        conf.setSubjectLoc(new InputBlastLocationImpl());

        BlastXValidator.validateSubjectLoc(err, conf);
        confirmSingleError(SubjectLocation, String.format(ConfigValidator.errIncompat, TaxIDs));
      }

      @Test
      @DisplayName("and " + NegativeTaxIDs + " contains values, rejects the config")
      void test2() {
        conf.setNegativeTaxIds(Collections.singletonList("goodbye"));
        conf.setSubjectLoc(new InputBlastLocationImpl());

        BlastXValidator.validateSubjectLoc(err, conf);
        confirmSingleError(
          SubjectLocation,
          String.format(ConfigValidator.errIncompat, NegativeTaxIDs)
        );
      }

      @Test
      @DisplayName("and " + DBSoftMask + " is not null, rejects the config")
      void test3() {
        conf.setDbSoftMask("goodbye");
        conf.setSubjectLoc(new InputBlastLocationImpl());

        BlastXValidator.validateSubjectLoc(err, conf);
        confirmSingleError(SubjectLocation, String.format(ConfigValidator.errIncompat, DBSoftMask));
      }

      @Test
      @DisplayName("and " + DBHardMask + " is not null, rejects the config")
      void test4() {
        conf.setDbHardMask("goodbye");
        conf.setSubjectLoc(new InputBlastLocationImpl());

        BlastXValidator.validateSubjectLoc(err, conf);
        confirmSingleError(SubjectLocation, String.format(ConfigValidator.errIncompat, DBHardMask));
      }
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
