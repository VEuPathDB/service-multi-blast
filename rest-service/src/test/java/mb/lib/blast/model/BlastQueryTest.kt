package mb.lib.blast.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.BlastTool

@DisplayName("BlastQuery")
internal class BlastQueryTest {

  @Nested
  @DisplayName("::fromString")
  inner class FromString {

    @Test
    @DisplayName("Single input sequence with no defline.")
    fun test1() {

      val seq = """somesequencetextthathasnodeflinethat
willbefedintothefromstringmethod."""

      val tgt = BlastQuery.fromString(BlastTool.BlastN, seq)

      val exp = "somesequencetextthathasnodeflinethatwillbefedintothefromstringmethod."

      assertEquals(exp, tgt.getFullQuery().reader().readText())
      assertEquals(1, tgt.sequences.size)
      assertEquals(exp, tgt.sequences[0].toStandardString().reader().readText())
    }

    @Test
    @DisplayName("Single input sequence with a defline.")
    fun test2() {

      val seq = """> here's a defline for stuff
somesequencetextthathasnodeflinethatwillbefedintothefromstringmethod
somesequencetextthathasnodeflinethatwillbefedintothefromstringmethod
"""
      val tgt = BlastQuery.fromString(BlastTool.BlastN, seq)

      val exp1 = """> here's a defline for stuff
somesequencetextthathasnodeflinethatwillbefedintothefromstringmethodsomesequence
textthathasnodeflinethatwillbefedintothefromstringmethod"""
      val exp2 = "> here's a defline for stuff"

      val exp3 = """somesequencetextthathasnodeflinethatwillbefedintothefromstringmethod
somesequencetextthathasnodeflinethatwillbefedintothefromstringmethod"""

      assertEquals(exp1, tgt.getFullQuery().reader().readText())
      assertEquals(1, tgt.sequences.size)
      assertEquals(exp1, tgt.sequences[0].toStandardString().reader().readText())
      assertEquals(exp2, tgt.sequences[0].defLine)
      assertEquals(exp3, tgt.sequences[0].sequence)
    }

    @Test
    @DisplayName("Multiple sequences.")
    fun test3() {
      val exp1Defline = "> defline 1"

      val exp1Sequence = """some text
some other text
wooooooo"""

      val exp2Defline = "> defline 2"

      val exp2Sequence = """blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah"""

      val fullExp = """$exp1Defline
sometextsomeothertextwooooooo
$exp2Defline
blahblahblahblahblahblahblahblahblahblahblahblahblahblahblahblahblahblahblahblah
blahblahblahblahblahblahblahblahblahblahblahblahblahblahblahblahblahblahblahblah
blahblahblahblahblah"""

      val seq = """$exp1Defline
$exp1Sequence
$exp2Defline
$exp2Sequence
"""

      val tgt = BlastQuery.fromString(BlastTool.BlastN, seq)

      assertEquals(fullExp, tgt.getFullQuery().reader().readText())
      assertEquals(2, tgt.sequences.size)

      assertEquals(exp1Defline, tgt.sequences[0].defLine)
      assertEquals(exp1Sequence, tgt.sequences[0].sequence)

      assertEquals(exp2Defline, tgt.sequences[1].defLine)
      assertEquals(exp2Sequence, tgt.sequences[1].sequence)
    }
  }
}
