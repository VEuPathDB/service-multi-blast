package mb.api.service.valid

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Sequences")
internal class SequenceTransformerTest {

  @Nested
  @DisplayName("#transform(String)")
  inner class Transform {

    @Test
    @DisplayName("Omits empty lines.")
    fun test1() {
      val input = """> Test header line
Some text that must be 80 characters per line so as to not invalidate the test b

y having unrelated transformations going on in the transform method.  We only wa

nt to test the empty line removal.
"""

      val expected = """> Test header line
Some text that must be 80 characters per line so as to not invalidate the test b
y having unrelated transformations going on in the transform method.  We only wa
nt to test the empty line removal.
"""

      assertEquals(expected, Sequences.standardize(input))
    }


    @Test
    @DisplayName("Trims leading margins.")
    fun test2() {
      val input = """> Test header line
        Some text that must be 80 characters per line so as to not invalidate the test b
        y having unrelated transformations going on in the transform method.  We only wa
        nt to test the margin removal.
        """

      val expected = """> Test header line
Some text that must be 80 characters per line so as to not invalidate the test b
y having unrelated transformations going on in the transform method.  We only wa
nt to test the margin removal.
"""

      assertEquals(expected, Sequences.standardize(input))
    }

    @Test
    @DisplayName("Breaks/merges lines to 80 character rows.")
    fun test3() {
      val input = """>Test header line
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaa
"""

      val expected = """>Test header line
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
"""

      assertEquals(expected, Sequences.standardize(input))
    }

  }
}