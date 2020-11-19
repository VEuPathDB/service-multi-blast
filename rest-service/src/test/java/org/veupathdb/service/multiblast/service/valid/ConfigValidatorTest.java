package org.veupathdb.service.multiblast.service.valid;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.model.ErrorMap;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConfigValidator")
class ConfigValidatorTest
{

  @Nested
  @DisplayName("$Int")
  class Int
  {
    @Nested
    @DisplayName("#optGtEq(ErrorMap, Integer, int, String)")
    class OptGtEq1
    {
      @Test
      @DisplayName("null value input")
      void test1() {
        var err = new ErrorMap();

        ConfigValidator.Int.optGtEq(err, null, 3, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input less than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Int.optGtEq(err, 3, 4, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errGtEqD, 4),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Int.optGtEq(err, 4, 4, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input greater than min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Int.optGtEq(err, 5, 4, "testing");

        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("#optGtEq(ErrorMap, Byte, byte, String)")
    class OptGtEq2
    {
      @Test
      @DisplayName("null value input")
      void test1() {
        var err = new ErrorMap();

        ConfigValidator.Int.optGtEq(err, (Byte) null, (byte) 3, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input less than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Int.optGtEq(err, (byte) 3, (byte) 4, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errGtEqD, 4),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Int.optGtEq(err, (byte) 4, (byte) 4, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input greater than min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Int.optGtEq(err, (byte) 5, (byte) 4, "testing");

        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("#gtEq(ErrorMap, int, int, String)")
    class GtEq1
    {
      @Test
      @DisplayName("value input less than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Int.gtEq(err, 3, 4, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errGtEqD, 4),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Int.gtEq(err, 4, 4, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input greater than min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Int.gtEq(err, 5, 4, "testing");

        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("#gtEq(ErrorMap, short, short, String)")
    class GtEq2
    {
      @Test
      @DisplayName("value input less than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Int.gtEq(err, (short) 3, (short) 4, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errGtEqD, 4),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Int.gtEq(err, (short) 4, (short) 4, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input greater than min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Int.gtEq(err, (short) 5, (short) 4, "testing");

        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("#gtEq(ErrorMap, byte, byte, String)")
    class GtEq3
    {
      @Test
      @DisplayName("value input less than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Int.gtEq(err, (byte) 3, (byte) 4, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errGtEqD, 4),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Int.gtEq(err, (byte) 4, (byte) 4, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input greater than min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Int.gtEq(err, (byte) 5, (byte) 4, "testing");

        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("optLtEq(ErrorMap, Integer, int, String)")
    class OptLtEq1
    {
      @Test
      @DisplayName("null value input")
      void test1() {
        var err = new ErrorMap();

        ConfigValidator.Int.optLtEq(err, null, 3, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input greater than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Int.optLtEq(err, 5, 4, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errLtEqD, 4),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Int.optLtEq(err, 4, 4, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input less than min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Int.optLtEq(err, 3, 4, "testing");

        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("ltEq(ErrorMap, int, int, String)")
    class LtEq1
    {
      @Test
      @DisplayName("value input greater than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Int.ltEq(err, 5, 4, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errLtEqD, 4),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Int.ltEq(err, 4, 4, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input less than min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Int.ltEq(err, 3, 4, "testing");

        assertTrue(err.isEmpty());
      }
    }
  }

  @Nested
  @DisplayName("$Dec")
  class Dec
  {
    @Nested
    @DisplayName("#optGtEq(ErrorMap, Double, double, String)")
    class OptGtEq1
    {
      @Test
      @DisplayName("null value input")
      void test1() {
        var err = new ErrorMap();

        ConfigValidator.Dec.optGtEq(err, null, 3, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input less than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Dec.optGtEq(err, 3d, 4, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errGtEqF, 4d),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Dec.optGtEq(err, 4d, 4, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input greater than min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Dec.optGtEq(err, 5d, 4, "testing");

        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("#gtEq(ErrorMap, double, double, String)")
    class GtEq1
    {
      @Test
      @DisplayName("value input less than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Dec.gtEq(err, 3, 4, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errGtEqF, 4d),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Dec.gtEq(err, 4, 4, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input greater than min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Dec.gtEq(err, 5, 4, "testing");

        assertTrue(err.isEmpty());
      }
    }

    @Nested
    @DisplayName("#betweenExc(ErrorMap, double, double, double, String)")
    class BetweenExc
    {
      @Test
      @DisplayName("value input less than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Dec.betweenExc(err, 3, 4, 6, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errBetweenExcF, 4d, 6d),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Dec.betweenExc(err, 4, 4, 6, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errBetweenExcF, 4d, 6d),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input between max and min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Dec.betweenExc(err, 5, 4, 6, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input equal to max")
      void test5() {
        var err = new ErrorMap();

        ConfigValidator.Dec.betweenExc(err, 6, 4, 6, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errBetweenExcF, 4d, 6d),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input greater than max")
      void test6() {
        var err = new ErrorMap();

        ConfigValidator.Dec.betweenExc(err, 7, 4, 6, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errBetweenExcF, 4d, 6d),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }
    }

    @Nested
    @DisplayName("#optBetweenInc(ErrorMap, Double, double, double, String)")
    class OptBetweenInc
    {
      @Test
      @DisplayName("null value input")
      void test1() {
        var err = new ErrorMap();

        ConfigValidator.Dec.optBetweenInc(err, null, 4, 6, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input less than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Dec.optBetweenInc(err, 3d, 4, 6, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errBetweenIncF, 4d, 6d),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Dec.optBetweenInc(err, 4d, 4, 6, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input between max and min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Dec.optBetweenInc(err, 5d, 4, 6, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input equal to max")
      void test5() {
        var err = new ErrorMap();

        ConfigValidator.Dec.optBetweenInc(err, 6d, 4, 6, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input greater than max")
      void test6() {
        var err = new ErrorMap();

        ConfigValidator.Dec.optBetweenInc(err, 7d, 4, 6, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errBetweenIncF, 4d, 6d),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }
    }

    @Nested
    @DisplayName("#betweenInc(ErrorMap, double, double, double, String)")
    class BetweenInc
    {
      @Test
      @DisplayName("value input less than min")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Dec.betweenInc(err, 3, 4, 6, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errBetweenIncF, 4d, 6d),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }

      @Test
      @DisplayName("value input equal to min")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Dec.betweenInc(err, 4, 4, 6, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input between max and min")
      void test4() {
        var err = new ErrorMap();

        ConfigValidator.Dec.betweenInc(err, 5, 4, 6, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input equal to max")
      void test5() {
        var err = new ErrorMap();

        ConfigValidator.Dec.betweenInc(err, 6, 4, 6, "testing");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("value input greater than max")
      void test6() {
        var err = new ErrorMap();

        ConfigValidator.Dec.betweenInc(err, 7, 4, 6, "testing");

        assertFalse(err.isEmpty(), "no error was appended");
        assertEquals(err.size(), 1, "more than one error was appended");
        assertTrue(err.containsKey("testing"), "error was entered under the wrong key");
        assertNotNull(err.get("testing"), "error list is null");
        assertFalse(err.get("testing").isEmpty(), "error list is empty");
        assertEquals(1, err.get("testing").size(), "error list contains more than one error");
        assertEquals(
          String.format(ConfigValidator.errBetweenIncF, 4d, 6d),
          err.get("testing").get(0),
          "incorrect error message"
        );
      }
    }
  }

  @Nested
  @DisplayName("$Obj")
  class Obj
  {
    @Nested
    @DisplayName("#incompat(ErrorMap, Object, String, String)")
    class Incompat
    {
      @Test
      @DisplayName("null value input")
      void test1() {
        var err = new ErrorMap();

        ConfigValidator.Obj.incompat(err, null, "test1", "test2");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("non null value input")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Obj.incompat(err, new Object(), "test1", "test2");

        assertEquals(1, err.size());
        assertTrue(err.containsKey("test1"));
        assertNotNull(err.get("test1"));
        assertEquals(1, err.get("test1").size());
        assertNotNull(err.get("test1").get(0));
        assertEquals(
          String.format(ConfigValidator.errIncompatibleWith, "test2"),
          err.get("test1").get(0)
        );
      }
    }

    @Nested
    @DisplayName("#colIncompat(ErrorMap, Collection, String, String)")
    class ColIncompat
    {
      @Test
      @DisplayName("null value input")
      void test1() {
        var err = new ErrorMap();

        ConfigValidator.Obj.colIncompat(err, null, "test1", "test2");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("empty collection input")
      void test2() {
        var err = new ErrorMap();

        ConfigValidator.Obj.colIncompat(err, Collections.emptyList(), "test1", "test2");

        assertTrue(err.isEmpty());
      }

      @Test
      @DisplayName("non empty collection input")
      void test3() {
        var err = new ErrorMap();

        ConfigValidator.Obj.colIncompat(err, Collections.singletonList("hi"), "test1", "test2");

        assertEquals(1, err.size());
        assertTrue(err.containsKey("test1"));
        assertNotNull(err.get("test1"));
        assertEquals(1, err.get("test1").size());
        assertNotNull(err.get("test1").get(0));
        assertEquals(
          String.format(ConfigValidator.errIncompatibleWith, "test2"),
          err.get("test1").get(0)
        );
      }
    }
  }
}

