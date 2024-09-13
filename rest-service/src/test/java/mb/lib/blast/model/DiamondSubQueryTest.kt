package mb.lib.blast.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.veupathdb.lib.blast.BlastTool

@DisplayName("BlastSubQuery")
internal class DiamondSubQueryTest {

  @Nested
  @DisplayName("#toStandardString")
  inner class ToStandardString {

    @Test
    @DisplayName("Writes out sequence when the input is less than 80 characters.")
    fun test1() {
      val seq = "thisIsASequence"
      val tgt = BlastSubQuery(1, BlastTool.BlastN, null, seq)

      assertEquals(seq, tgt.toStandardString())
    }

    @Test
    @DisplayName("Writes out the sequence broken into 80 character rows.")
    fun test2() {
      val seq = "thisIsASeriesOfCharactersThatWillHaveALengthOf160thisIsASeriesOfCharactersThatWillHaveALengthOf160thisIsASeriesOfCharactersThatWillHaveALengthOf160thisIsASeries"
      val tgt = BlastSubQuery(1, BlastTool.BlastN, null, seq)
      val exp = """thisIsASeriesOfCharactersThatWillHaveALengthOf160thisIsASeriesOfCharactersThatWi
llHaveALengthOf160thisIsASeriesOfCharactersThatWillHaveALengthOf160thisIsASeries"""

      assertEquals(exp, tgt.toStandardString())
    }

    @Test
    @DisplayName("Writes out a short line at the end if the sequence length is not a multiple of 80 in length.")
    fun test3() {
      val seq = "thisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumber"
      val tgt = BlastSubQuery(1, BlastTool.BlastN, null, seq)
      val exp = """thisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharacter
sThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSo
meNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfC
haractersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALe
ngthOfSomeNumber"""

      assertEquals(exp, tgt.toStandardString())
    }

    @Test
    @DisplayName("Includes the unaltered defline regardless of length.")
    fun test4() {
      val def = "> This is a defline that will have more than 80 characters in it to prove that the defline is not altered by the function under test."
      val seq = "thisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumber"
      val tgt = BlastSubQuery(1, BlastTool.BlastN, def, seq)
      val exp = """> This is a defline that will have more than 80 characters in it to prove that the defline is not altered by the function under test.
thisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharacter
sThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSo
meNumberthisIsASeriesOfCharactersThatWillHaveALengthOfSomeNumberthisIsASeriesOfC
haractersThatWillHaveALengthOfSomeNumberthisIsASeriesOfCharactersThatWillHaveALe
ngthOfSomeNumber"""

      assertEquals(exp, tgt.toStandardString())
    }

    @Test
    @DisplayName("Handles multiline queries under with < 80 char columns")
    fun test5() {
      val seq = """somesequencetextthathasnodeflinethat
willbefedintothefromstringmethod."""

      val tgt = BlastSubQuery(1, BlastTool.BlastN, null, seq)

      val exp = "somesequencetextthathasnodeflinethatwillbefedintothefromstringmethod."

      assertEquals(exp, tgt.toStandardString())

    }
  }
}
