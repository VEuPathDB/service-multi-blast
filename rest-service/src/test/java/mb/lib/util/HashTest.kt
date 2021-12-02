package mb.lib.util

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

@DisplayName("Hash")
class HashTest
{
  @Nested
  @DisplayName("::hashToString(byte[])")
  inner class HashToString1
  {
    @Test
    @DisplayName("Correctly translates a byte array to a hex string")
    fun test1() {
      val inp  = byteArrayOf(255.toByte(), 127, 63, 31, 15, 0, 15, 31, 63, 127, 255.toByte(), 127, 63, 31, 15, 0)
      val exp = "FF7F3F1F0F000F1F3F7FFF7F3F1F0F00"

      assertEquals(exp, hashToString(inp))
    }
  }

  @Nested
  @DisplayName("::stringToHash(String)")
  inner class StringToHash1
  {
    @Test
    @DisplayName("Correctly translates a hex string into bytes")
    fun test1() {
      val inp = "FF7F3F1F0F000F1F3F7FFF7F3F1F0F00"
      val exp = byteArrayOf(255.toByte(), 127, 63, 31, 15, 0, 15, 31, 63, 127, 255.toByte(), 127, 63, 31, 15, 0)
      val out = stringToHash(inp)

      assertArrayEquals(exp, out)
    }

    @Test
    @DisplayName("Errors if the input string is malformed")
    fun test2() {
      val inp  = "FF7F1F0F0"
      assertThrows(IllegalArgumentException::class.java) { stringToHash(inp) }
    }
  }

  @Nested
  @DisplayName("::byteToHex(byte[], int, byte)")
  inner class ByteToHex1
  {
    @Test
    @DisplayName("Appends the correct characters to the buffer for a given input")
    fun test1() {
      val buf  = ByteArray(2)
      val tests = arrayOf(
        byteArrayOf(0, '0'.code.toByte(), '0'.code.toByte()),
        byteArrayOf(15, '0'.code.toByte(), 'F'.code.toByte()),
        byteArrayOf(31, '1'.code.toByte(), 'F'.code.toByte()),
        byteArrayOf(127, '7'.code.toByte(), 'F'.code.toByte()),
        byteArrayOf(255.toByte(), 'F'.code.toByte(), 'F'.code.toByte()),
      )

      for (test in tests) {
        buf[0] = 0
        buf[1] = 1

        byteToHex(buf, 0, test[0])

        assertEquals(buf[0], test[1])
        assertEquals(buf[1], test[2])
      }
    }
  }

  @Nested
  @DisplayName("::byteToChar(byte)")
  inner class ByteToChar1
  {
    @Test
    @DisplayName("Returns the expected character based on the given input.")
    fun test1() {
      val tests = arrayOf(
        byteArrayOf(0, '0'.code.toByte()),
        byteArrayOf(1, '1'.code.toByte()),
        byteArrayOf(2, '2'.code.toByte()),
        byteArrayOf(3, '3'.code.toByte()),
        byteArrayOf(4, '4'.code.toByte()),
        byteArrayOf(5, '5'.code.toByte()),
        byteArrayOf(6, '6'.code.toByte()),
        byteArrayOf(7, '7'.code.toByte()),
        byteArrayOf(8, '8'.code.toByte()),
        byteArrayOf(9, '9'.code.toByte()),
        byteArrayOf(10, 'A'.code.toByte()),
        byteArrayOf(11, 'B'.code.toByte()),
        byteArrayOf(12, 'C'.code.toByte()),
        byteArrayOf(13, 'D'.code.toByte()),
        byteArrayOf(14, 'E'.code.toByte()),
        byteArrayOf(15, 'F'.code.toByte()),
      )

      for (test in tests) {
        assertEquals(test[1], byteToChar(test[0]))
      }
    }

    @Test
    @DisplayName("Errors on values > 15")
    fun test2() {
      assertThrows(IllegalArgumentException::class.java) { byteToChar(16) }
    }
  }

  @Nested
  @DisplayName("::charsToByte(byte, byte)")
  inner class CharsToByte1
  {
    @Test
    @DisplayName("Returns the expected value for given inputs")
    fun test1() {
      val tests: Array<Triple<Char, Char, Byte>> = arrayOf(
        Triple('0', '0', 0),
        Triple('0', 'F', 15),
        Triple('F', 'F', 255.toByte()),
      )

      for ((a, b, c) in tests) {
        assertEquals(c, charsToByte(a, b))
      }
    }
  }

  @Nested
  @DisplayName("::charToByte(byte)")
  inner class CharToByte1
  {
    @Test
    @DisplayName("Returns the expected value for chars a-f")
    fun test1() {
      val tests = arrayOf(
        Pair('a', 10),
        Pair('b', 11),
        Pair('c', 12),
        Pair('d', 13),
        Pair('e', 14),
        Pair('f', 15),
      )

      for ((a, b) in tests) {
        assertEquals(b, charToByte(a))
      }
    }

    @Test
    @DisplayName("Returns the expected value for chars A-F")
    fun test2() {
      val tests: Array<Pair<Char, Int>> = arrayOf(
        Pair('A', 10),
        Pair('B', 11),
        Pair('C', 12),
        Pair('D', 13),
        Pair('E', 14),
        Pair('F', 15),
      )

      for (test in tests) {
        assertEquals(test.second, charToByte(test.first))
      }
    }

    @Test
    @DisplayName("Returns the expected value for chars 0-9")
    fun test3() {
      val tests: Array<Pair<Char, Int>> = arrayOf(
        Pair('0', 0),
        Pair('1', 1),
        Pair('2', 2),
        Pair('3', 3),
        Pair('4', 4),
        Pair('5', 5),
        Pair('6', 6),
        Pair('7', 7),
        Pair('8', 8),
        Pair('9', 9),
      )

      for (test in tests) {
        assertEquals(test.second, charToByte(test.first))
      }
    }

    @Test
    @DisplayName("Errors if the input char is not a valid hex value")
    fun test4() {
      assertThrows(IllegalArgumentException::class.java) { charToByte('G') }
    }
  }
}