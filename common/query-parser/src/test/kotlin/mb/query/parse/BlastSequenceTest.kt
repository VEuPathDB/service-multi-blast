package mb.query.parse

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("BlastSequence")
internal class BlastSequenceTest {

  @Nested
  @DisplayName("#toString()")
  inner class ToString1 {

    @Test
    @DisplayName("Returns only the sequence when the defline is null.")
    fun returns_only_seq_when_defline_is_null() {
      val tgt = BlastSequence(null, "hello")
      assertEquals("hello", tgt.toString())
    }

    @Test
    @DisplayName("Returns the defline concatenated with the sequence when the defline is not null.")
    fun returns_defline_and_seq_when_defline_not_null() {
      val tgt = BlastSequence("hello", "world")
      assertEquals("hello\nworld", tgt.toString())
    }
  }
}