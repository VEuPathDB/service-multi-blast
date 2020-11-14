package org.veupathdb.service.multiblast.model.blast;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dust")
class DustTest {
  @Test
  @DisplayName("#getLevel() returns the expected value")
  void test1() {
    assertEquals(3, new Dust(3, 4, 5).getLevel());
  }

  @Test
  @DisplayName("#getWindow() returns the expected value")
  void test2() {
    assertEquals(4, new Dust(3, 4, 5).getWindow());
  }

  @Test
  @DisplayName("#getLinker() returns the expected value")
  void test3() {
    assertEquals(5, new Dust(3, 4, 5).getLinker());
  }

  @Test
  @DisplayName("#unsafeFromString() parses a correctly formatted dust string")
  void test4() {
    var target = Dust.unsafeFromString("6 7 8");

    assertEquals(6, target.getLevel());
    assertEquals(7, target.getWindow());
    assertEquals(8, target.getLinker());
  }
}
