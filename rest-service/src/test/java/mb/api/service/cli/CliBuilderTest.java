package mb.api.service.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import mb.lib.blast.model.ToolOption;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CliBuilder")
class CliBuilderTest
{
  @Nested
  @DisplayName("#toString()")
  class ToString
  {
    @Test
    @DisplayName("Returns the keys and values joined with '=' in a space delimited string")
    void test1() {
      var target = new CliBuilder();

      target.set(ToolOption.OutputFile, "hello")
        .set(ToolOption.CullingLimit, 1234)
        .set(ToolOption.Query, "apple")
        .append(ToolOption.Query, "orange");

      Assertions.assertEquals(
        "-out='hello' -culling_limit='1234' -query='apple,orange'",
        target.toString()
      );
    }
  }

  @Nested
  @DisplayName("#toArgArray()")
  class ToArgArray1
  {
    @Test
    @DisplayName("Returns the keys and values joined with '=' in an array")
    void test1() {
      var target = new CliBuilder();

      target.set(ToolOption.OutputFile, "hello")
        .set(ToolOption.CullingLimit, 1234)
        .set(ToolOption.Query, "apple")
        .append(ToolOption.Query, "orange");

      Assertions.assertArrayEquals(
        new String[]{"-out='hello'", "-culling_limit='1234'", "-query='apple,orange'"},
        target.toArgArray(true)
      );
    }
  }

  @Nested
  @DisplayName("#escape(String)")
  class Escape1
  {
    @Test
    @DisplayName("Properly escapes one single quote in a string.")
    void test1() {
      var in  = "They're";
      var out = "They'\"'\"'re";

      Assertions.assertEquals(out, CliBuilder.escape(in));
    }

    @Test
    @DisplayName("Properly escapes multiple adjacent single quotes in a string.")
    void test2() {
      var in  = "They''''re";
      var out = "They'\"''''\"'re";

      Assertions.assertEquals(out, CliBuilder.escape(in));
    }

    @Test
    @DisplayName("Properly escapes multiple separated single quotes in a string.")
    void test3() {
      var in  = "They''re we've";
      var out = "They'\"''\"'re we'\"'\"'ve";

      Assertions.assertEquals(out, CliBuilder.escape(in));
    }

    @Test
    @DisplayName("Returns an empty string when given a null value")
    void test4() {
      Assertions.assertEquals("", CliBuilder.escape(null));
    }

    @Test
    @DisplayName("Returns an empty string when given an empty string")
    void test5() {
      Assertions.assertEquals("", CliBuilder.escape(""));
    }

    @Test
    @DisplayName("Returns an empty string when given a blank string")
    void test6() {
      Assertions.assertEquals("", CliBuilder.escape("    "));
    }
  }

  @Nested
  @DisplayName("#joinArgs(Object[])")
  class JoinArgs1
  {
    @Nested
    @DisplayName("Returns an empty string when")
    class JAEmpty
    {
      @Test
      @DisplayName("given a null input")
      void test1() {
        Assertions.assertEquals("", CliBuilder.joinArgs(null));
      }

      @Test
      @DisplayName("given an empty array")
      void test2() {
        Assertions.assertEquals("", CliBuilder.joinArgs(new Object[0]));
      }

      @Test
      @DisplayName("given an array with a single null value")
      void test3() {
        Assertions.assertEquals("", CliBuilder.joinArgs(new Object[]{null}));
      }

      @Test
      @DisplayName("given an array containing only null values")
      void test4() {
        Assertions.assertEquals("", CliBuilder.joinArgs(new Object[]{null, null, null}));
      }
    }

    @Nested
    @DisplayName("Returns a cli flag argument with")
    class Happy
    {
      @Test
      @DisplayName("all non-null input values joined in a comma separated list")
      void test1() {
        Assertions.assertEquals("='foo,bar'", CliBuilder.joinArgs(new Object[]{
          null,
          "foo",
          null,
          null,
          "bar",
          null
        }));
      }
    }
  }

  @Nested
  @DisplayName("#setNotNull(ToolOption, Object)")
  class SetNotNull
  {
    @Nested
    @DisplayName("When given a null value")
    class NullVal
    {
      @Test
      @DisplayName("Does not add it to the argument list")
      void test1() {
        var target = new CliBuilder();

        Assertions.assertEquals("", target.toString());

        target.setNonNull(ToolOption.CullingLimit, (Object) null);

        Assertions.assertEquals("", target.toString());
      }

      @Test
      @DisplayName("Does not overwrite a previously set value")
      void test2() {
        var target = new CliBuilder();
        target.set(ToolOption.CullingLimit, 123);

        Assertions.assertEquals("-culling_limit='123'", target.toString());

        target.setNonNull(ToolOption.CullingLimit, (Object) null);

        Assertions.assertEquals("-culling_limit='123'", target.toString());
      }
    }

    @Nested
    @DisplayName("When given a non-null value")
    class NonNull
    {
      @Test
      @DisplayName("Adds the argument to the builder")
      void test1() {
        var target = new CliBuilder();

        Assertions.assertEquals("", target.toString());

        target.setNonNull(ToolOption.Dust, "hey");

        Assertions.assertEquals("-dust='hey'", target.toString());
      }

      @Test
      @DisplayName("Overwrites existing values")
      void test2() {
        var target = new CliBuilder();

        target.set(ToolOption.Dust, "hey");

        Assertions.assertEquals("-dust='hey'", target.toString());

        target.setNonNull(ToolOption.Dust, "man");

        Assertions.assertEquals("-dust='man'", target.toString());
      }
    }
  }

  @Nested
  @DisplayName("#appendNotNull(ToolOption, Object)")
  class AppendNotNull
  {
    @Nested
    @DisplayName("When given a null value")
    class NullVal
    {
      @Test
      @DisplayName("Does not add it to the argument list")
      void test1() {
        var target = new CliBuilder();

        Assertions.assertEquals("", target.toString());

        target.appendNonNull(ToolOption.CullingLimit, (Object) null);

        Assertions.assertEquals("", target.toString());
      }
    }

    @Nested
    @DisplayName("When given a non-null value")
    class NonNull
    {
      @Test
      @DisplayName("Adds the argument to the builder")
      void test1() {
        var target = new CliBuilder();

        Assertions.assertEquals("", target.toString());

        target.appendNonNull(ToolOption.Dust, "hey");

        Assertions.assertEquals("-dust='hey'", target.toString());
      }

      @Test
      @DisplayName("Adds the argument to the existing values")
      void test2() {
        var target = new CliBuilder();

        target.set(ToolOption.Dust, "hey", "man", "nice");

        Assertions.assertEquals("-dust='hey,man,nice'", target.toString());

        target.appendNonNull(ToolOption.Dust, "shot");

        Assertions.assertEquals("-dust='hey,man,nice,shot'", target.toString());
      }
    }
  }

}
