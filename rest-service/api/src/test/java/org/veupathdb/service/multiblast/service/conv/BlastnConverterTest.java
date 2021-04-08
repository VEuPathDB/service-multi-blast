package org.veupathdb.service.multiblast.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.veupathdb.service.multiblast.generated.model.IOBlastnDust;
import org.veupathdb.service.multiblast.generated.model.IOBlastnDustImpl;
import org.veupathdb.service.multiblast.model.blast.impl.DustImpl;
import org.veupathdb.service.multiblast.model.blast.n.Dust;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BlastnConverter")
class BlastnConverterTest
{
  @Nested
  @DisplayName("::toExternal(Dust)")
  class ToExternal1
  {
    @Test
    @DisplayName("Correctly converts Dust values.")
    void test1() {
      var in  = new DustImpl(10, 1, 2);
      var out = BlastnConverter.toExternal(in);

      assertEquals(in.getWindow(), (int) out.getWindow());
      assertEquals(in.getLevel(), (int) out.getLevel());
      assertEquals(in.getLinker(), (int) out.getLinker());
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      assertNull(BlastnConverter.toExternal((Dust) null));
    }
  }

  @Nested
  @DisplayName("::toInternal(IOBlastnDust)")
  class ToInternal2
  {
    @Test
    @DisplayName("Correctly converts IOBlastnDust values.")
    void test1() {
      var in = new IOBlastnDustImpl();
      in.setEnable(true);
      in.setLevel((short) 10);
      in.setWindow((short) 11);
      in.setLinker((short) 12);

      var out = BlastnConverter.toInternal(in);

      assertEquals((int) in.getLevel(), out.getLevel());
      assertEquals((int) in.getWindow(), out.getWindow());
      assertEquals((int) in.getLinker(), out.getLinker());
    }

    @Test
    @DisplayName("Converts disabled values to null.")
    void test2() {
      var in = new IOBlastnDustImpl();
      in.setEnable(false);
      in.setLevel((short) 10);
      in.setWindow((short) 11);
      in.setLinker((short) 12);

      assertNull(BlastnConverter.toInternal(in));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test3() {
      assertNull(BlastnConverter.toInternal((IOBlastnDust) null));
    }
  }
}