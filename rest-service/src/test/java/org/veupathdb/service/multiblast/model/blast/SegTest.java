package org.veupathdb.service.multiblast.model.blast;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Seg")
class SegTest
{
  @Test
  @DisplayName("#getWindow() returns the expected value")
  void test1() {
    assertEquals(5, new Seg(5, 6, 7).getWindow());
  }

  @Test
  @DisplayName("#getLoCut() returns the expected value")
  void test2() {
    assertEquals(66.6, new Seg(5, 66.6, 7).getLoCut());
  }

  @Test
  @DisplayName("#getHiCut() returns the expected value")
  void test3() {
    assertEquals(77.7, new Seg(5, 6, 77.7).getHiCut());
  }

  @Test
  @DisplayName("#unsafeFromString(String) parses correctly formatted string")
  void test4() {
    var target = Seg.unsafeFromString("5 0.666 0.777");

    assertEquals(5, target.getWindow());
    assertEquals(0.666, target.getLoCut());
    assertEquals(0.777, target.getHiCut());
  }

  @Test
  @DisplayName("#toString() returns the expected stringified value")
  void test5() {
    var target = new Seg(5, 666.666, 777.777);

    assertEquals("5 666.666 777.777", target.toString());
  }
}
