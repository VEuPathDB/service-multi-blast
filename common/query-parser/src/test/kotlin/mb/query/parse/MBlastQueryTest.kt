package mb.query.parse

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("MBlastQuery")
internal class MBlastQueryTest {

  @Nested
  @DisplayName("#toString()")
  inner class ToString1 {

    @Test
    @DisplayName("single sequence no defline")
    fun t1() {
      val tgt = MBlastQuery(listOf(
        BlastSequence(null, "hello")
      ))
      assertEquals("hello", tgt.toString())
    }

    @Test
    @DisplayName("single sequence with defline")
    fun t2() {
      val tgt = MBlastQuery(listOf(
        BlastSequence("hello", "world")
      ))
      assertEquals("hello\nworld", tgt.toString())
    }

    @Test
    @DisplayName("multiple sequences, no leading defline")
    fun t3() {
      val tgt = MBlastQuery(listOf(
        BlastSequence(null, "goodbye"),
        BlastSequence("cruel", "world"),
      ))
      assertEquals("goodbye\ncruel\nworld", tgt.toString())
    }
  }
}