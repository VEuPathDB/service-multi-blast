package org.veupathdb.service.multiblast.model.blast;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlastToolTest
{
  @Test
  void fromString1() {
    assertDoesNotThrow(() -> {
      Arrays.stream(BlastTool.values())
        .map(Enum::name)
        .map(String::toLowerCase)
        .map(BlastTool::fromString)
        .forEach(o -> o.get());
    });
  }

  @Test
  void fromString2() {
    assertTrue(BlastTool.fromString(null).isEmpty());
  }

  @Test
  void fromString3() {
    assertTrue(BlastTool.fromString("something").isEmpty());
  }

  @Test
  void value() {
    for (var e : BlastTool.values()) {
      assertEquals(e.name().toLowerCase(), e.value());
    }
  }

  @Test
  void testToString() {
    for (var e : BlastTool.values()) {
      assertEquals(e.name().toLowerCase(), e.toString());
    }
  }
}
