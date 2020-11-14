package org.veupathdb.service.multiblast.model.blast;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QueryLocation")
class QueryLocationTest
{
  @Nested
  @DisplayName("#getStart()")
  class GetStart
  {
    @Test
    @DisplayName("Returns the expected value")
    void test1() {
      assertEquals(13, new QueryLocation(13, 14).getStart());
    }
  }

  @Nested
  @DisplayName("#getStop()")
  class GetStop
  {
    @Test
    @DisplayName("Returns the expected value")
    void test1() {
      assertEquals(14, new QueryLocation(13, 14).getStop());
    }
  }

  @Nested
  @DisplayName("#toString()")
  class ToString
  {
    @Test
    @DisplayName("Returns the expected value")
    void test1() {
      assertEquals("13-14", new QueryLocation(13, 14).toString());
    }
  }

  @Nested
  @DisplayName("#unsafeFromString(String)")
  class UnsafeFromString
  {
    @Test
    @DisplayName("Correctly parses the input string")
    void test1() {
      var target = QueryLocation.unsafeFromString("5-6");

      assertEquals(5, target.getStart());
      assertEquals(6, target.getStop());
    }
  }
}
