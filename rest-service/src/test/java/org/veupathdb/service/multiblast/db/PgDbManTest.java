package org.veupathdb.service.multiblast.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PostgreSQL Database Manager")
class PgDbManTest {
  @Nested
  @DisplayName("JDBC Url Formatter")
  class makeJdbcUrl {
    @Test
    @DisplayName("Correctly injects the input parameters to form a valid JDBC url")
    void test1() {
      assertEquals("jdbc:postgresql://foo:123/bar", PgDbMan.makeJdbcUrl("foo", 123, "bar"));
    }
  }
}
