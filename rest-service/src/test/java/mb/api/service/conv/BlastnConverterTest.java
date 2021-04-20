package mb.api.service.conv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import mb.api.model.blast.IOBlastnDust;
import mb.api.model.blast.impl.IOBlastnDustImpl;
import mb.lib.blast.model.impl.DustImpl;
import mb.lib.blast.model.n.Dust;

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

      Assertions.assertEquals(in.getWindow(), (int) out.getWindow());
      Assertions.assertEquals(in.getLevel(), (int) out.getLevel());
      Assertions.assertEquals(in.getLinker(), (int) out.getLinker());
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test2() {
      Assertions.assertNull(BlastnConverter.toExternal((Dust) null));
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

      Assertions.assertEquals((int) in.getLevel(), out.getLevel());
      Assertions.assertEquals((int) in.getWindow(), out.getWindow());
      Assertions.assertEquals((int) in.getLinker(), out.getLinker());
    }

    @Test
    @DisplayName("Converts disabled values to null.")
    void test2() {
      var in = new IOBlastnDustImpl();
      in.setEnable(false);
      in.setLevel((short) 10);
      in.setWindow((short) 11);
      in.setLinker((short) 12);

      Assertions.assertNull(BlastnConverter.toInternal(in));
    }

    @Test
    @DisplayName("Returns null when input is null")
    void test3() {
      Assertions.assertNull(BlastnConverter.toInternal((IOBlastnDust) null));
    }
  }
}