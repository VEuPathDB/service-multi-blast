package org.veupathdb.service.multiblast.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Format")
class FormatTest
{
  @DisplayName("#isHex")
  class IsHex {
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
}
