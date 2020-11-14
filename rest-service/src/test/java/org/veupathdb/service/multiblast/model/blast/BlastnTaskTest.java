package org.veupathdb.service.multiblast.model.blast;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BlastnTask")
class BlastnTaskTest
{
  @Nested
  @DisplayName("#unsafeFromString(String)")
  class UnsafeFromString
  {

    @Nested
    @DisplayName("Returns an empty option")
    class Empty
    {

      @Test
      @DisplayName("when the input string does not match any known value")
      void test1() {
        assertThrows(IllegalArgumentException.class, () -> BlastnTask.unsafeFromString("foo"));
      }
    }

    @Nested
    @DisplayName("Returns an option containing a BlastnTask value")
    class NonEmpty
    {

      @Test
      @DisplayName("when the input string is \"blastn\"")
      void test1() {
        assertEquals(BlastnTask.BlastN, BlastnTask.unsafeFromString("blastn"));
      }

      @Test
      @DisplayName("when the input string is \"blastn-short\"")
      void test2() {
        assertEquals(BlastnTask.BlastNShort, BlastnTask.unsafeFromString("blastn-short"));
      }

      @Test
      @DisplayName("when the input string is \"dc-megablast\"")
      void test3() {
        assertEquals(BlastnTask.DiscontiguousMegablast, BlastnTask.unsafeFromString("dc-megablast"));
      }

      @Test
      @DisplayName("when the input string is \"megablast\"")
      void test4() {
        assertEquals(BlastnTask.Megablast, BlastnTask.unsafeFromString("megablast"));
      }

      @Test
      @DisplayName("when the input string is \"rmblastn\"")
      void test5() {
        assertEquals(BlastnTask.RMBlastN, BlastnTask.unsafeFromString("rmblastn"));
      }
    }
  }

  @Nested
  @DisplayName("#fromString(String)")
  class FromString
  {

    @Nested
    @DisplayName("Returns an empty option")
    class Empty
    {

      @Test
      @DisplayName("when the input string does not match any known value")
      void test1() {
        assertTrue(BlastnTask.fromString("foo").isEmpty());
      }
    }

    @Nested
    @DisplayName("Returns an option containing a BlastnTask value")
    class NonEmpty
    {

      @Test
      @DisplayName("when the input string is \"blastn\"")
      void test1() {
        assertEquals(BlastnTask.BlastN, BlastnTask.fromString("blastn").orElseThrow());
      }

      @Test
      @DisplayName("when the input string is \"blastn-short\"")
      void test2() {
        assertEquals(BlastnTask.BlastNShort, BlastnTask.fromString("blastn-short").orElseThrow());
      }

      @Test
      @DisplayName("when the input string is \"dc-megablast\"")
      void test3() {
        assertEquals(BlastnTask.DiscontiguousMegablast, BlastnTask.fromString("dc-megablast").orElseThrow());
      }

      @Test
      @DisplayName("when the input string is \"megablast\"")
      void test4() {
        assertEquals(BlastnTask.Megablast, BlastnTask.fromString("megablast").orElseThrow());
      }

      @Test
      @DisplayName("when the input string is \"rmblastn\"")
      void test5() {
        assertEquals(BlastnTask.RMBlastN, BlastnTask.fromString("rmblastn").orElseThrow());
      }
    }

    @Nested
    @DisplayName("#getValue()")
    class GetValue
    {
      @Test
      @DisplayName("Does not return null")
      void test1() {
        for (var e : BlastnTask.values())
          assertNotNull(e.getValue());
      }
    }
  }
}
