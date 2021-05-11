package mb.lib.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Hash")
class HashTest
{
  @Nested
  @DisplayName("::hashToString(byte[])")
  class HashToString1
  {
    @Test
    @DisplayName("Correctly translates a byte array to a hex string")
    void test1() {
      var in  = new byte[]{(byte) 255, 127, 63, 31, 15, 0, 15, 31, 63, 127, (byte) 255, 127, 63, 31, 15, 0};
      var exp = "FF7F3F1F0F000F1F3F7FFF7F3F1F0F00";

      assertEquals(exp, Hash.hashToString(in));
    }
  }

  @Nested
  @DisplayName("::stringToHash(String)")
  class StringToHash1
  {
    @Test
    @DisplayName("Correctly translates a hex string into bytes")
    void test1() {
      var in  = "FF7F3F1F0F000F1F3F7FFF7F3F1F0F00";
      var exp = new byte[]{(byte) 255, 127, 63, 31, 15, 0, 15, 31, 63, 127, (byte) 255, 127, 63, 31, 15, 0};
      var out = Hash.stringToHash(in);

      assertArrayEquals(exp, out);
    }

    @Test
    @DisplayName("Errors if the input string is malformed")
    void test2() {
      var in  = "FF7F1F0F0";
      assertThrows(IllegalArgumentException.class, () -> Hash.stringToHash(in));
    }
  }

  @Nested
  @DisplayName("::byteToHex(byte[], int, byte)")
  class ByteToHex1
  {
    @Test
    @DisplayName("Appends the correct characters to the buffer for a given input")
    void test1() {
      var buf  = new byte[2];
      var tests = new byte[][]{
        {0, '0', '0'},
        {15, '0', 'F'},
        {31, '1', 'F'},
        {127, '7', 'F'},
        {(byte) 255, 'F', 'F'}
      };

      for (var test : tests) {
        buf[0] = 0;
        buf[1] = 1;

        Hash.byteToHex(buf, 0, test[0]);

        assertEquals(buf[0], test[1]);
        assertEquals(buf[1], test[2]);
      }
    }
  }

  @Nested
  @DisplayName("::byteToChar(byte)")
  class ByteToChar1
  {
    @Test
    @DisplayName("Returns the expected character based on the given input.")
    void test1() {
      var tests = new byte[][]{
        {0, '0'}, {1, '1'}, {2, '2'}, {3, '3'},
        {4, '4'}, {5, '5'}, {6, '6'}, {7, '7'},
        {8, '8'}, {9, '9'}, {10, 'A'}, {11, 'B'},
        {12, 'C'}, {13, 'D'}, {14, 'E'}, {15, 'F'},
      };

      for (var test : tests) {
        assertEquals(test[1], Hash.byteToChar(test[0]));
      }
    }

    @Test
    @DisplayName("Errors on values > 15")
    void test2() {
      assertThrows(IllegalArgumentException.class, () -> Hash.byteToChar((byte) 16));
    }
  }

  @Nested
  @DisplayName("::charsToByte(byte, byte)")
  class CharsToByte1
  {
    @Test
    @DisplayName("Returns the expected value for given inputs")
    void test1() {
      var tests = new byte[][]{
        {'0', '0', 0},
        {'0', 'F', 15},
        {'F', 'F', (byte) 255},
      };

      for (var test : tests) {
        assertEquals(test[2], Hash.charsToByte(test[0], test[1]));
      }
    }
  }

  @Nested
  @DisplayName("::charToByte(byte)")
  class CharToByte1
  {
    @Test
    @DisplayName("Returns the expected value for chars a-f")
    void test1() {
      var tests = new byte[][]{
        {'a', 10},
        {'b', 11},
        {'c', 12},
        {'d', 13},
        {'e', 14},
        {'f', 15}
      };

      for (var test : tests) {
        assertEquals(test[1], Hash.charToByte(test[0]));
      }
    }

    @Test
    @DisplayName("Returns the expected value for chars A-F")
    void test2() {
      var tests = new byte[][]{
        {'A', 10},
        {'B', 11},
        {'C', 12},
        {'D', 13},
        {'E', 14},
        {'F', 15}
      };

      for (var test : tests) {
        assertEquals(test[1], Hash.charToByte(test[0]));
      }
    }

    @Test
    @DisplayName("Returns the expected value for chars 0-9")
    void test3() {
      var tests = new byte[][]{
        {'0', 0},
        {'1', 1},
        {'2', 2},
        {'3', 3},
        {'4', 4},
        {'5', 5},
        {'6', 6},
        {'7', 7},
        {'8', 8},
        {'9', 9},
      };

      for (var test : tests) {
        assertEquals(test[1], Hash.charToByte(test[0]));
      }
    }

    @Test
    @DisplayName("Errors if the input char is not a valid hex value")
    void test4() {
      assertThrows(IllegalArgumentException.class, () -> Hash.charToByte((byte) 'G'));
    }
  }
}