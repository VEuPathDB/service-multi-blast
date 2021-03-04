package org.veupathdb.service.multiblast.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Format")
class FormatTest
{
  @Nested
  @DisplayName("::isHex(String)")
  class IsHex
  {
    @Test
    @DisplayName("Returns false for strings containing non-hex characters")
    void test1() {
      var in = "0123456789ABCDEFG";
      assertFalse(Format.isHex(in));
    }

    @Test
    @DisplayName("Returns true for strings containing only hex characters")
    void test2() {
      var in = "0123456789ABCDEF";
      assertTrue(Format.isHex(in));
    }
  }

  @Nested
  @DisplayName("::toHexString(byte[])")
  class ToHexString
  {
    @Test
    @DisplayName("Returns the expected hex string for a given byte array")
    void test1() {
      var in = new byte[]{-128, -64, -32, -16, -8, -4, -2, -1, 0, 1, 2, 4, 8, 16, 32, 64, 127};
      assertEquals("80C0E0F0F8FCFEFF00010204081020407F", Format.toHexString(in));
    }
  }

  @Nested
  @DisplayName("::hexToBytes(String)")
  class HexToBytes
  {
    @Test
    @DisplayName("Returns the expected byte array for a given hex string")
    void test1() {
      var in  = "80C0E0F0F8FCFEFF00010204081020407F";
      var out = new byte[]{-128, -64, -32, -16, -8, -4, -2, -1, 0, 1, 2, 4, 8, 16, 32, 64, 127};

      assertArrayEquals(out, Format.hexToBytes(in));
    }
  }

  @Nested
  @DisplayName("::hexToByte(byte)")
  class HexToByte
  {
    @Test
    @DisplayName("Returns the expected int value for a given hex char")
    void test1() {
      assertEquals(10, Format.hexToByte((byte) 'A'));
    }
  }
}
