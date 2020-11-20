package org.veupathdb.service.multiblast.service.valid;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.ErrorMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.veupathdb.service.multiblast.model.io.JsonKeys.*;

@DisplayName("BlastValidator")
class BlastValidatorTest
{
  private InputBlastConfig conf;
  private InputBlastOutFmt format;
  private ErrorMap         err;

  @BeforeEach
  void setUp() {
    conf = new InputBlastConfigImpl();
    err = new ErrorMap();
    format = new InputBlastOutFmtImpl();
  }

  @Nested
  @DisplayName("#validateSortHits(ErrorMap, InputBlastConfig)")
  class ValidateSortHits
  {

    @Test
    @DisplayName("given a null sortHits value")
    void test1() {
      BlastValidator.validateSortHits(err, conf);
      assertTrue(err.isEmpty());
    }

    @Nested
    @DisplayName("given a non-null sortHits value")
    class NonNull
    {
      @Test
      @DisplayName("and a null outFormat value")
      void test1() {
        conf.setSortHits(InputHitSorting.BYBITSCORE);

        BlastValidator.validateSortHits(err, conf);

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("and a non-null outFormat value with a null type")
      void test2() {
        conf.setSortHits(InputHitSorting.BYBITSCORE);
        conf.setOutFormat(format);

        BlastValidator.validateSortHits(err, conf);

        assertTrue(err.isEmpty());
      }

      @Nested
      @DisplayName("and a non-null outFormat value with non-null type")
      class WithType
      {

        @TestFactory
        Stream<DynamicTest> test2() {
          return Arrays.stream(InputBlastFormat.values())
            .filter(e -> e.ordinal() <= 4)
            .map(e -> DynamicTest.dynamicTest("Allows Type " + e.name, () -> {
              err = new ErrorMap();

              format.setFormat(e);
              conf.setSortHits(InputHitSorting.BYBITSCORE);
              conf.setOutFormat(format);

              BlastValidator.validateSortHits(err, conf);

              assertTrue(err.isEmpty());
            }));
        }

        @TestFactory()
        @DisplayName("and a non-null outFormat value with a type > 4")
        Stream<DynamicTest> test3() {
          return Arrays.stream(InputBlastFormat.values())
            .filter(e -> e.ordinal() > 4)
            .map(e -> DynamicTest.dynamicTest("Rejects Type " + e.name, () -> {
              err = new ErrorMap();

              format.setFormat(e);
              conf.setSortHits(InputHitSorting.BYBITSCORE);
              conf.setOutFormat(format);

              BlastValidator.validateSortHits(err, conf);

              assertEquals(1, err.size());
              assertTrue(err.containsKey(SortHits));
              assertEquals(1, err.get(SortHits).size());
              assertEquals(BlastValidator.errNotFmtGt4, err.get(SortHits).get(0));
            }));
        }
      }
    }
  }

  @Nested
  @DisplayName("#validateSortHSPs(ErrorMap, InputBlastConfig)")
  class ValidateSortHSPs
  {
    @Test
    @DisplayName("given a null sortHSPs value")
    void test1() {
      BlastValidator.validateSortHSPs(err, conf);
      assertTrue(err.isEmpty());
    }

    @Nested
    @DisplayName("given a non-null sortHSPs value")
    class NonNull
    {
      @Test
      @DisplayName("and a null outFormat value")
      void test1() {
        conf.setSortHSPs(InputHSPSorting.BYHSPEVALUE);

        BlastValidator.validateSortHSPs(err, conf);

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("and a non-null outFormat value with a null type")
      void test2() {
        conf.setSortHSPs(InputHSPSorting.BYHSPEVALUE);
        conf.setOutFormat(format);

        BlastValidator.validateSortHSPs(err, conf);

        assertTrue(err.isEmpty());
      }

      @Nested
      @DisplayName("and a non-null outFormat value with non-null type")
      class WithType
      {

        @Test
        @DisplayName("Allows Type pairwise")
        void test2() {
          format.setFormat(InputBlastFormat.PAIRWISE);
          conf.setSortHSPs(InputHSPSorting.BYHSPEVALUE);
          conf.setOutFormat(format);

          BlastValidator.validateSortHSPs(err, conf);

          assertTrue(err.isEmpty());
        }

        @TestFactory()
        @DisplayName("and a non-null outFormat value with a type > 0")
        Stream<DynamicTest> test3() {
          return Arrays.stream(InputBlastFormat.values())
            .filter(e -> e.ordinal() > 0)
            .map(e -> DynamicTest.dynamicTest("Rejects Type " + e.name, () -> {
              err = new ErrorMap();

              format.setFormat(e);
              conf.setSortHSPs(InputHSPSorting.BYHSPEVALUE);
              conf.setOutFormat(format);

              BlastValidator.validateSortHSPs(err, conf);

              assertEquals(1, err.size());
              assertTrue(err.containsKey(SortHSPs));
              assertEquals(1, err.get(SortHSPs).size());
              assertEquals(BlastValidator.errFmt0, err.get(SortHSPs).get(0));
            }));
        }
      }

      @Nested
      @DisplayName("#validateQueryLocation(ErrorMap, InputBlastConfig)")
      class ValidateQueryLocation
      {
        @Nested
        @DisplayName("given a null queryLocation")
        class Null
        {
          @Test
          @DisplayName("does nothing")
          void test1() {
            BlastValidator.validateQueryLocation(err, conf);
            assertTrue(err.isEmpty());
          }
        }

        @Nested
        @DisplayName("given a non-null queryLocation")
        class NotNull
        {
          @Test
          @DisplayName("passes when start is less than end")
          void test1() {
            var q = new InputBlastLocationImpl();
            q.setStart(1L);
            q.setStop(2L);

            conf.setQueryLoc(q);

            BlastValidator.validateQueryLocation(err, conf);

            assertTrue(err.isEmpty());
          }

          @Test
          @DisplayName("fails when start is equal to end")
          void test2() {
            var q = new InputBlastLocationImpl();
            q.setStart(2L);
            q.setStop(2L);

            conf.setQueryLoc(q);

            BlastValidator.validateQueryLocation(err, conf);

            assertEquals(1, err.size());
            assertTrue(err.containsKey(QueryLocation));
            assertNotNull(err.get(QueryLocation));
            assertEquals(1, err.get(QueryLocation).size());
            assertEquals(BlastValidator.ErrQueryLoc, err.get(QueryLocation).get(0));
          }

          @Test
          @DisplayName("fails when start is less than end")
          void test3() {
            var q = new InputBlastLocationImpl();
            q.setStart(2L);
            q.setStop(1L);

            conf.setQueryLoc(q);

            BlastValidator.validateQueryLocation(err, conf);

            assertEquals(1, err.size());
            assertTrue(err.containsKey(QueryLocation));
            assertNotNull(err.get(QueryLocation));
            assertEquals(1, err.get(QueryLocation).size());
            assertEquals(BlastValidator.ErrQueryLoc, err.get(QueryLocation).get(0));
          }

          @Test
          @DisplayName("fails when start is not set")
          void test4() {
            var q = new InputBlastLocationImpl();
            q.setStop(1L);

            conf.setQueryLoc(q);

            BlastValidator.validateQueryLocation(err, conf);

            var key = QueryLocation + "." + Start;
            assertEquals(1, err.size());
            assertTrue(err.containsKey(key));
            assertNotNull(err.get(key));
            assertEquals(1, err.get(key).size());
            assertEquals(BlastValidator.errRequired, err.get(key).get(0));
          }

          @Test
          @DisplayName("fails when stop is not set")
          void test5() {
            var q = new InputBlastLocationImpl();
            q.setStart(1L);

            conf.setQueryLoc(q);

            BlastValidator.validateQueryLocation(err, conf);

            var key = QueryLocation + "." + Stop;
            assertEquals(1, err.size());
            assertTrue(err.containsKey(key));
            assertNotNull(err.get(key));
            assertEquals(1, err.get(key).size());
            assertEquals(BlastValidator.errRequired, err.get(key).get(0));
          }
        }
      }
    }
  }

  @Nested
  @DisplayName("#validateEValue(ErrorMap, InputBlastConfig)")
  class ValidateEValue
  {
    @Nested
    @DisplayName("when given a null eValue")
    class Null
    {
      @Test
      @DisplayName("does nothing")
      void test1() {
        BlastValidator.validateEValue(err, conf);
        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("when given a non-null eValue")
    class NonNull
    {
      @ParameterizedTest(name = "allows {0}")
      @ValueSource(strings = {
        "1",
        "1.0",
        "0.1e-14",
        "2.2e200",
        "12345E-150",
        "1.123E10"
      })
      void test1(String val) {
        conf.setEValue(val);
        BlastValidator.validateEValue(err, conf);
        assertTrue(err.isEmpty());
      }

      @ParameterizedTest(name = "rejects {0}")
      @ValueSource(strings = {
        ".1",
        "1.0.4",
        "0.1e-1.4",
        "2.2f200",
        "1.2.3.4.5E-1.5.0",
        "1.123tE10",
        "1.e30"
      })
      void test2(String val) {
        conf.setEValue(val);
        BlastValidator.validateEValue(err, conf);

        assertEquals(1, err.size());
        assertTrue(err.containsKey(ExpectValue));
        assertNotNull(err.get(ExpectValue));
        assertEquals(1, err.get(ExpectValue).size());
        assertEquals(BlastValidator.errEValue, err.get(ExpectValue).get(0));
      }
    }
  }

  @Nested
  @DisplayName("#validateOutFormat(ErrorMap, InputBlastConfig)")
  class ValidateOutFormat
  {
    private OutFormatValidator mockVal;

    @BeforeEach
    void setUp() throws Exception {
      var fld = OutFormatValidator.class.getDeclaredField("instance");
      fld.setAccessible(true);
      fld.set(null, mockVal = mock(OutFormatValidator.class));

      doReturn(new ErrorMap()).when(mockVal).externalUseValidation(any());
    }

    @AfterEach
    void tearDown() throws Exception {
      var fld = OutFormatValidator.class.getDeclaredField("instance");
      fld.setAccessible(true);
      fld.set(null, mockVal = null);
    }

    @Nested
    @DisplayName("when given a null format")
    class Null
    {
      @Test
      @DisplayName("does nothing")
      void test1() {
        BlastValidator.validateOutFormat(err, conf);
        verifyZeroInteractions(mockVal);
        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("when given a non-null format")
    class NotNull
    {
      @Test
      @DisplayName("passes the config to OutFormatValidator")
      void test1() {
        var fmt = new InputBlastOutFmtImpl();
        conf.setOutFormat(fmt);
        BlastValidator.validateOutFormat(err, conf);
        verify(mockVal, times(1)).externalUseValidation(fmt);
        verifyNoMoreInteractions(mockVal);
        assertTrue(err.isEmpty());
      }
    }
  }

  @Nested
  @DisplayName("#validateNumDescriptions(ErrorMap, InputBlastConfig)")
  class ValidateNumDescriptions
  {
    @Nested
    @DisplayName("when given a null numDescriptions value")
    class Null
    {
      @Test
      @DisplayName("does nothing")
      void test1() {
        BlastValidator.validateNumDescriptions(err, conf);
        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("when given a non-null numDescriptions value")
    class NonNull
    {
      @Test
      @DisplayName("rejects values < 0")
      void test1() {
        conf.setNumDescriptions(-1);
        BlastValidator.validateNumDescriptions(err, conf);
        assertEquals(1, err.size());
        assertTrue(err.containsKey(NumDescriptions));
        assertNotNull(err.get(NumDescriptions));
        assertEquals(1, err.get(NumDescriptions).size());
        assertEquals(String.format(ConfigValidator.errGtEqD, 0), err.get(NumDescriptions).get(0));
      }

      @Test
      @DisplayName("rejects conf if maxTargetSeqs is set")
      void test2() {
        conf.setNumDescriptions(1);
        conf.setMaxTargetSeqs(1);
        BlastValidator.validateNumDescriptions(err, conf);

        assertEquals(1, err.size());
        assertTrue(err.containsKey(NumDescriptions));
        assertNotNull(err.get(NumDescriptions));
        assertEquals(1, err.get(NumDescriptions).size());
        assertEquals(
          String.format(ConfigValidator.errIncompat, MaxTargetSequences),
          err.get(NumDescriptions).get(0)
        );
      }

      @Nested
      @DisplayName("and output format is not null")
      class OutFmt
      {
        @Test
        @DisplayName("passes if format type is null")
        void test3() {
          conf.setNumDescriptions(1);
          conf.setOutFormat(new InputBlastOutFmtImpl());

          BlastValidator.validateNumDescriptions(err, conf);

          assertTrue(err.isEmpty());
        }

        @TestFactory()
        @DisplayName("passes if format type <= 4")
        Stream<DynamicTest> test4() {
          return Arrays.stream(InputBlastFormat.values())
            .filter(e -> e.ordinal() <= 4)
            .map(e -> DynamicTest.dynamicTest("allows Type " + e.name, () -> {
              err = new ErrorMap();

              format.setFormat(e);
              conf.setNumDescriptions(1);
              conf.setOutFormat(format);

              BlastValidator.validateNumDescriptions(err, conf);

              assertTrue(err.isEmpty());
            }));
        }

        @TestFactory()
        @DisplayName("rejects format types > 4")
        Stream<DynamicTest> test5() {
          return Arrays.stream(InputBlastFormat.values())
            .filter(e -> e.ordinal() > 4)
            .map(e -> DynamicTest.dynamicTest("rejects type " + e.name, () -> {
              err = new ErrorMap();

              format.setFormat(e);
              conf.setNumDescriptions(1);
              conf.setOutFormat(format);

              BlastValidator.validateNumDescriptions(err, conf);

              assertEquals(1, err.size());
              assertTrue(err.containsKey(NumDescriptions));
              assertEquals(1, err.get(NumDescriptions).size());
              assertEquals(BlastValidator.errNotFmtGt4, err.get(NumDescriptions).get(0));
            }));
        }
      }
    }
  }

  @Nested
  @DisplayName("#validateNumAlignments(ErrorMap, InputBlastConfig)")
  class ValidateNumAlignments
  {
    @Nested
    @DisplayName("when numAlignments is null")
    class Null
    {
      @Test
      @DisplayName("does nothing")
      void test1() {
        BlastValidator.validateNumAlignments(err, conf);
        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("when numAlignments is not null")
    class NotNull
    {
      @Test
      @DisplayName("rejects values < 0")
      void test1() {
        conf.setNumAlignments(-1);
        BlastValidator.validateNumAlignments(err, conf);
        assertEquals(1, err.size());
        assertTrue(err.containsKey(NumAlignments));
        assertNotNull(err.get(NumAlignments));
        assertEquals(1, err.get(NumAlignments).size());
        assertEquals(String.format(ConfigValidator.errGtEqD, 0), err.get(NumAlignments).get(0));
      }

      @Test
      @DisplayName("rejects config when maxTargetSeqs is not null")
      void test2() {
        conf.setNumAlignments(1);
        conf.setMaxTargetSeqs(1);
        BlastValidator.validateNumAlignments(err, conf);
        assertEquals(1, err.size());
        assertTrue(err.containsKey(NumAlignments));
        assertNotNull(err.get(NumAlignments));
        assertEquals(1, err.get(NumAlignments).size());
        assertEquals(
          String.format(ConfigValidator.errIncompat, MaxTargetSequences),
          err.get(NumAlignments).get(0)
        );
      }

      @Test
      @DisplayName("accepts config numAlignments is >= 0 and maxTargetSeqs is null")
      void test3() {
        conf.setNumAlignments(1);
        BlastValidator.validateNumAlignments(err, conf);
        assertTrue(err.isEmpty());
      }
    }
  }

  @Nested
  @DisplayName("#validateQCovHspPerc(ErrorMap, InputBlastConfig)")
  class ValidateQCovHSPPerc
  {
    @Test
    @DisplayName("does nothing with null " + QueryCoverageHSPPercent)
    void test1() {
      BlastValidator.validateQCovHspPerc(err, conf);
      assertTrue(err.isEmpty());
    }

    @Test
    @DisplayName("rejects values < 0")
    void test2() {
      conf.setQCovHSPPerc(-1D);
      BlastValidator.validateQCovHspPerc(err, conf);
      confirmSingleError(
        QueryCoverageHSPPercent,
        String.format(ConfigValidator.errBetweenIncF, 0d, 100d)
      );
    }

    @Test
    @DisplayName("rejects values > 100")
    void test3() {
      conf.setQCovHSPPerc(101D);
      BlastValidator.validateQCovHspPerc(err, conf);
      assertEquals(1, err.size());
      assertTrue(err.containsKey(QueryCoverageHSPPercent));
      assertNotNull(err.get(QueryCoverageHSPPercent));
      assertEquals(1, err.get(QueryCoverageHSPPercent).size());
      assertEquals(
        String.format(ConfigValidator.errBetweenIncF, 0d, 100d),
        err.get(QueryCoverageHSPPercent).get(0)
      );
    }

    @Test
    @DisplayName("accepts values >= 0 and <= 100")
    void test4() {
      conf.setQCovHSPPerc(45D);
      BlastValidator.validateQCovHspPerc(err, conf);
      assertTrue(err.isEmpty());
    }
  }

  @Nested
  @DisplayName("#validateMaxTargetSeqs(ErrorMap, InputBlastConfig)")
  class ValidateMaxTargetSeqs
  {
    @Nested
    @DisplayName("when " + MaxTargetSequences + " is null")
    class Null
    {
      @Test
      @DisplayName("does nothing")
      void test1() {
        BlastValidator.validateMaxTargetSeqs(err, conf);
        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("when " + MaxTargetSequences + " is not null")
    class NotNull
    {
      @Test
      @DisplayName("rejects values less than 1")
      void test1() {
        conf.setMaxTargetSeqs(0);
        conf.setOutFormat(format);
        format.setFormat(InputBlastFormat.SAM);
        BlastValidator.validateMaxTargetSeqs(err, conf);
        confirmSingleError(MaxTargetSequences, String.format(ConfigValidator.errGtEqD, 1));
      }

      @Test
      @DisplayName("rejects config if " + NumAlignments + " is not null")
      void test2() {
        conf.setMaxTargetSeqs(1);
        conf.setNumAlignments(1);
        conf.setOutFormat(format);
        format.setFormat(InputBlastFormat.SAM);
        BlastValidator.validateMaxTargetSeqs(err, conf);
        confirmSingleError(
          MaxTargetSequences,
          String.format(ConfigValidator.errIncompat, NumAlignments)
        );
      }

      @Test
      @DisplayName("rejects config if " + NumDescriptions + " is not null")
      void test3() {
        conf.setMaxTargetSeqs(1);
        conf.setNumDescriptions(1);
        conf.setOutFormat(format);
        format.setFormat(InputBlastFormat.SAM);
        BlastValidator.validateMaxTargetSeqs(err, conf);
        confirmSingleError(
          MaxTargetSequences,
          String.format(ConfigValidator.errIncompat, NumDescriptions)
        );
      }

      @Test
      @DisplayName("rejects config if " + OutFormat + " is null")
      void test7() {
        conf.setMaxTargetSeqs(1);
        BlastValidator.validateMaxTargetSeqs(err, conf);
        confirmSingleError(MaxTargetSequences, BlastValidator.errOnlyFmtGt4);
      }

      @Test
      @DisplayName("rejects config if " + OutFormat + "." + Format + " is null")
      void test8() {
        conf.setMaxTargetSeqs(1);
        conf.setOutFormat(format);
        BlastValidator.validateMaxTargetSeqs(err, conf);
        confirmSingleError(MaxTargetSequences, BlastValidator.errOnlyFmtGt4);
      }

      @TestFactory()
      @DisplayName("rejects format types <= 4")
      Stream<DynamicTest> test5() {
        return Arrays.stream(InputBlastFormat.values())
          .filter(e -> e.ordinal() <= 4)
          .map(e -> DynamicTest.dynamicTest("rejects format type " + e.name, () -> {
            err = new ErrorMap();

            format.setFormat(e);
            conf.setMaxTargetSeqs(1);
            conf.setOutFormat(format);

            BlastValidator.validateMaxTargetSeqs(err, conf);

            confirmSingleError(MaxTargetSequences, BlastValidator.errOnlyFmtGt4);
          }));
      }

      @TestFactory()
      @DisplayName("accepts format types > 4")
      Stream<DynamicTest> test6() {
        return Arrays.stream(InputBlastFormat.values())
          .filter(e -> e.ordinal() > 4)
          .map(e -> DynamicTest.dynamicTest("accepts format type " + e.name, () -> {
            err = new ErrorMap();

            format.setFormat(e);
            conf.setMaxTargetSeqs(1);
            conf.setOutFormat(format);

            BlastValidator.validateMaxTargetSeqs(err, conf);

            assertTrue(err.isEmpty());
          }));
      }
    }
  }

  @Nested
  @DisplayName("#validateGenCode(ErrorMap, Byte, String)")
  class ValidateGenCode
  {
    @Test
    @DisplayName("does nothing when the input value is null")
    void test1() {
      BlastValidator.validateGenCode(err, null, "test");
      assertTrue(err.isEmpty());
    }

    @ParameterizedTest(name = "accepts value {0}")
    @ValueSource(bytes = {
      1, 2, 3, 4, 5, 6,
      9, 10, 11, 12, 13, 14, 15, 16,
      21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
      33
    })
    void test2(byte value) {
      BlastValidator.validateGenCode(err, value, "test");
      assertTrue(err.isEmpty());
    }

    @ParameterizedTest(name = "rejects value {0}")
    @ValueSource(bytes = { 7, 8, 17, 18, 19, 20, 32 })
    void test3(byte value) {
      BlastValidator.validateGenCode(err, value, "test");
      confirmSingleError("test", BlastValidator.ErrGenCode);
    }

    @Test
    @DisplayName("rejects values < 1")
    void test4() {
      BlastValidator.validateGenCode(err, (byte) 0, "test");
      confirmSingleError("test", BlastValidator.ErrGenCode);
    }

    @Test
    @DisplayName("rejects values > 33")
    void test5() {
      BlastValidator.validateGenCode(err, (byte) 34, "test");
      confirmSingleError("test", BlastValidator.ErrGenCode);
    }
  }

  @Nested
  @DisplayName("#validate(InputBlastConfig)")
  class Validate
  {
    private BlastNValidator bn;
    private BlastPValidator bp;
    private BlastXValidator bx;
    private TBlastNValidator tbn;
    private TBlastXValidator tbx;
    private BlastValidator target;

    @BeforeEach
    void setUp() throws Exception {
      var empty = new ErrorMap();

      target = BlastValidator.getInstance();

      bn = mock(BlastNValidator.class);
      bp = mock(BlastPValidator.class);
      bx = mock(BlastXValidator.class);
      tbn = mock(TBlastNValidator.class);
      tbx = mock(TBlastXValidator.class);

      doReturn(empty).when(bn).validate(any());
      doReturn(empty).when(bp).validate(any());
      doReturn(empty).when(bx).validate(any());
      doReturn(empty).when(tbn).validate(any());
      doReturn(empty).when(tbx).validate(any());

      var m = BlastNValidator.class.getDeclaredField("instance");
      m.setAccessible(true);
      m.set(null, bn);

      m = BlastPValidator.class.getDeclaredField("instance");
      m.setAccessible(true);
      m.set(null, bp);

      m = BlastXValidator.class.getDeclaredField("instance");
      m.setAccessible(true);
      m.set(null, bx);

      m = TBlastNValidator.class.getDeclaredField("instance");
      m.setAccessible(true);
      m.set(null, tbn);

      m = TBlastXValidator.class.getDeclaredField("instance");
      m.setAccessible(true);
      m.set(null, tbx);
    }

    @AfterEach
    void tearDown() throws Exception {
      var m = BlastNValidator.class.getDeclaredField("instance");
      m.setAccessible(true);
      m.set(null, null);

      m = BlastPValidator.class.getDeclaredField("instance");
      m.setAccessible(true);
      m.set(null, null);

      m = BlastXValidator.class.getDeclaredField("instance");
      m.setAccessible(true);
      m.set(null, null);

      m = TBlastNValidator.class.getDeclaredField("instance");
      m.setAccessible(true);
      m.set(null, null);

      m = TBlastXValidator.class.getDeclaredField("instance");
      m.setAccessible(true);
      m.set(null, null);
    }

    @Test
    @DisplayName("passes blastn configs to BlastNValidator")
    void test1() {
      var val = new InputBlastnConfigImpl();
      target.validate(val);
      verify(bn, times(1)).validate(val);
    }

    @Test
    @DisplayName("passes blastp configs to BlastPValidator")
    void test2() {
      var val = new InputBlastpConfigImpl();
      target.validate(val);
      verify(bp, times(1)).validate(val);
    }

    @Test
    @DisplayName("passes blastx configs to BlastXValidator")
    void test3() {
      var val = new InputBlastxConfigImpl();
      target.validate(val);
      verify(bx, times(1)).validate(val);
    }

    @Test
    @DisplayName("passes tblastn configs to BlastXValidator")
    void test4() {
      var val = new InputTBlastnConfigImpl();
      target.validate(val);
      verify(tbn, times(1)).validate(val);
    }

    @Test
    @DisplayName("passes tblastx configs to BlastXValidator")
    void test5() {
      var val = new InputTBlastxConfigImpl();
      target.validate(val);
      verify(tbx, times(1)).validate(val);
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
