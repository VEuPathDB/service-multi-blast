package org.veupathdb.service.multiblast.model.blast;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlastToolTest
{
  @Test
  void fromString() {
    assertDoesNotThrow(() -> {
      Arrays.stream(BlastTool.values())
        .map(Enum::name)
        .map(String::toLowerCase)
        .map(BlastTool::fromString)
        .forEach(o -> o.get());
    });
  }
}
