package mb.api.service.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Format")
internal class FormatTest
{
  @Nested
  @DisplayName("::isHex(String)")
  inner class IsHex
  {
    @Test
    @DisplayName("Returns false for strings containing non-hex characters")
    fun test1() {
      val inp = "0123456789ABCDEFG"
      Assertions.assertFalse(Format.isHex(inp))
    }

    @Test
    @DisplayName("Returns true for strings containing only hex characters")
    fun test2() {
      val inp = "0123456789ABCDEF"
      Assertions.assertTrue(Format.isHex(inp))
    }
  }

  @Nested
  @DisplayName("::toHexString(byte[])")
  inner class ToHexString
  {
    @Test
    @DisplayName("Returns the expected hex string for a given byte array")
    fun test1() {
      val inp = byteArrayOf(-128, -64, -32, -16, -8, -4, -2, -1, 0, 1, 2, 4, 8, 16, 32, 64, 127)
      Assertions.assertEquals("80C0E0F0F8FCFEFF00010204081020407F", Format.toHexString(inp))
    }
  }

  @Nested
  @DisplayName("::hexToBytes(String)")
  inner class HexToBytes
  {
    @Test
    @DisplayName("Returns the expected byte array for a given hex string")
    fun test1() {
      val inp  = "80C0E0F0F8FCFEFF00010204081020407F"
      val out = byteArrayOf(-128, -64, -32, -16, -8, -4, -2, -1, 0, 1, 2, 4, 8, 16, 32, 64, 127)

      Assertions.assertArrayEquals(out, Format.hexToBytes(inp))
    }
  }

  @Nested
  @DisplayName("::hexToByte(byte)")
  inner class HexToByte
  {
    @Test
    @DisplayName("Returns the expected int value for a given hex char")
    fun test1() {
      Assertions.assertEquals(10, Format.hexToByte('A'.code.toByte()))
    }
  }
}
