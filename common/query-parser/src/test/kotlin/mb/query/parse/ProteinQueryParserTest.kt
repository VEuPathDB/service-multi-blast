package mb.query.parse

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("ProteinQueryParser")
internal class ProteinQueryParserTest {

  @Nested
  @DisplayName("#parseQuery(String)")
  inner class ParseQuery1 {

    @Nested
    @DisplayName("When given an empty query")
    inner class ZeroSequences1 {

      @Test
      @DisplayName("that has no defline")
      fun t1() {
        val inputs = arrayOf(
          "",
          "   ",
          "\n\n\n   \n"
        )

        for (input in inputs) {
          val tgt = ProteinQueryParser(1, 100)

          assertThrows(EmptySequenceException::class.java) { tgt.parseQuery(input) }
        }
      }

      @Test
      @DisplayName("that has a defline")
      fun t2() {
        val inputs = arrayOf(
          "> hoopla",
          "> hoopla\n",
          "> hoopla\n    ",
          "> hoopla\n\n\n   \n"
        )

        for (input in inputs) {
          val tgt = ProteinQueryParser(1, 100)

          assertThrows(EmptySequenceException::class.java) { tgt.parseQuery(input) }
        }
      }
    }

    @Nested
    @DisplayName("When given a single sequence query")
    inner class SingleSequence1 {

      @Nested
      @DisplayName("that is valid and has no defline")
      inner class Valid1 {

        @Test
        @DisplayName("single line sequence")
        fun t1() {
          val input = "aaaaaaa"
          val tgt   = ProteinQueryParser(1, 100)
          val out = tgt.parseQuery(input)

          assertEquals(1, out.sequences.size)
          assertNull(out.sequences[0].defLine)
          assertEquals(input, out.sequences[0].sequence)
        }

        @Test
        @DisplayName("sequence with leading newline")
        fun t2() {
          val input = "\naaaaaaaaa"
          val tgt   = ProteinQueryParser(1, 100)
          val out   = tgt.parseQuery(input)

          assertEquals(1, out.sequences.size)
          assertNull(out.sequences[0].defLine)
          assertEquals(input, out.sequences[0].sequence)
        }

        @Test
        @DisplayName("sequence with ignorable characters")
        fun t3() {
          val input = "  600 aaa aaa aaa"
          val tgt   = ProteinQueryParser(1, 100)
          val out   = tgt.parseQuery(input)

          assertEquals(1, out.sequences.size)
          assertNull(out.sequences[0].defLine)
          assertEquals(input, out.sequences[0].sequence)
        }

        @Test
        @DisplayName("sequence with multiple lines")
        fun t4() {
          val input = "  600 aaa aaa aaa\n  601 aaa aaa aaa"
          val tgt   = ProteinQueryParser(1, 100)
          val out   = tgt.parseQuery(input)

          assertEquals(1, out.sequences.size)
          assertNull(out.sequences[0].defLine)
          assertEquals(input, out.sequences[0].sequence)
        }
      }

      @Nested
      @DisplayName("that is valid and has a defline")
      inner class Valid2 {

        @Test
        @DisplayName("single line sequence")
        fun t1() {
          val input = "> hoopla\naaaaaaa"
          val tgt   = ProteinQueryParser(1, 100)
          val out = tgt.parseQuery(input)

          assertEquals(1, out.sequences.size)
          assertEquals("> hoopla", out.sequences[0].defLine)
          assertEquals("aaaaaaa", out.sequences[0].sequence)
        }

        @Test
        @DisplayName("sequence with leading newline")
        fun t2() {
          val input = "> hoopla\n\naaaaaaaaa"
          val tgt   = ProteinQueryParser(1, 100)
          val out   = tgt.parseQuery(input)

          assertEquals(1, out.sequences.size)
          assertEquals("> hoopla", out.sequences[0].defLine)
          assertEquals("\naaaaaaaaa", out.sequences[0].sequence)
        }

        @Test
        @DisplayName("sequence with ignorable characters")
        fun t3() {
          val input = "> hoopla\n  600 aaa aaa aaa"
          val tgt   = ProteinQueryParser(1, 100)
          val out   = tgt.parseQuery(input)

          assertEquals(1, out.sequences.size)
          assertEquals("> hoopla", out.sequences[0].defLine)
          assertEquals("  600 aaa aaa aaa", out.sequences[0].sequence)
        }

        @Test
        @DisplayName("sequence with multiple lines")
        fun t4() {
          val input = "> hoopla\n  600 aaa aaa aaa\n  601 aaa aaa aaa"
          val tgt   = ProteinQueryParser(1, 100)
          val out   = tgt.parseQuery(input)

          assertEquals(1, out.sequences.size)
          assertEquals("> hoopla", out.sequences[0].defLine)
          assertEquals(
            "  600 aaa aaa aaa\n  601 aaa aaa aaa",
            out.sequences[0].sequence
          )
        }
      }

      @Nested
      @DisplayName("that is invalid")
      inner class Invalid1 {

        @Test
        @DisplayName("because it is too long")
        fun t1() {
          val input = "aaaaaaaaaa"
          val tgt   = ProteinQueryParser(1, 5)

          assertThrows(SequenceTooLongException::class.java) { tgt.parseQuery(input) }
        }

        @Test
        @DisplayName("because it contains an invalid character")
        fun t2() {
          val input = "++++++++++++++"
          val tgt   = ProteinQueryParser(1, 100)

          assertThrows(InvalidSequenceCharacterException::class.java) { tgt.parseQuery(input) }
        }
      }
    }

    @Nested
    @DisplayName("When given a multi-sequence query")
    inner class MultiSequence1 {}
  }
}