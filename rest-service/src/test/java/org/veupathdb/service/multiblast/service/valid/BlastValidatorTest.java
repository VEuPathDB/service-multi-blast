package org.veupathdb.service.multiblast.service.valid;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

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
    conf   = new InputBlastConfigImpl();
    err    = new ErrorMap();
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
      class WithType {

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
              assertEquals(BlastValidator.errOnlyFmtGt4, err.get(SortHits).get(0));
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
      class WithType {

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
            q.setStart(1);
            q.setStop(2);

            conf.setQueryLoc(q);

            BlastValidator.validateQueryLocation(err, conf);

            assertTrue(err.isEmpty());
          }

          @Test
          @DisplayName("fails when start is equal to end")
          void test2() {
            var q = new InputBlastLocationImpl();
            q.setStart(2);
            q.setStop(2);

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
            q.setStart(2);
            q.setStop(1);

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
            q.setStop(1);

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
            q.setStart(1);

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
}
