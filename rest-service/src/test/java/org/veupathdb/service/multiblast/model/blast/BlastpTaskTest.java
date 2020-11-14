package org.veupathdb.service.multiblast.model.blast;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BlastpTask")
class BlastpTaskTest
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
        assertThrows(IllegalArgumentException.class, () -> BlastpTask.unsafeFromString("foo"));
      }
    }

    @Nested
    @DisplayName("Returns an option containing a BlastpTask value")
    class NonEmpty
    {

      @Test
      @DisplayName("when the input string is \"blastp\"")
      void test1() {
        assertEquals(BlastpTask.BlastP, BlastpTask.unsafeFromString("blastp"));
      }

      @Test
      @DisplayName("when the input string is \"blastp-fast\"")
      void test2() {
        assertEquals(BlastpTask.BlastPFast, BlastpTask.unsafeFromString("blastp-fast"));
      }

      @Test
      @DisplayName("when the input string is \"blastp-short\"")
      void test3() {
        assertEquals(BlastpTask.BlastPShort, BlastpTask.unsafeFromString("blastp-short"));
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
        assertTrue(BlastpTask.fromString("foo").isEmpty());
      }
    }

    @Nested
    @DisplayName("Returns an option containing a BlastpTask value")
    class NonEmpty
    {

      @Test
      @DisplayName("when the input string is \"blastp\"")
      void test1() {
        assertEquals(BlastpTask.BlastP, BlastpTask.fromString("blastp").orElseThrow());
      }

      @Test
      @DisplayName("when the input string is \"blastp-fast\"")
      void test2() {
        assertEquals(BlastpTask.BlastPFast, BlastpTask.fromString("blastp-fast").orElseThrow());
      }

      @Test
      @DisplayName("when the input string is \"blastp-short\"")
      void test3() {
        assertEquals(BlastpTask.BlastPShort, BlastpTask.fromString("blastp-short").orElseThrow());
      }
    }

    @Nested
    @DisplayName("#getValue()")
    class GetValue
    {
      @Test
      @DisplayName("Does not return null")
      void test1() {
        for (var e : BlastpTask.values())
          assertNotNull(e.getValue());
      }
    }

    @Nested
    @DisplayName("#toString()")
    class ToString
    {
      @Test
      @DisplayName("Returns the same value as #getValue()")
      void test1() {
        for (var e : BlastpTask.values())
          assertEquals(e.toString(), e.getValue());
      }
    }
  }
}
