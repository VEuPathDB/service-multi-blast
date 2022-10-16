package org.veupathdb.lib.blast.common.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("FormatField")
internal class FormatFieldTest {

  @Test
  @DisplayName("isDefault is only true for the standard fields")
  fun t1() {
    for (v in FormatField.values()) {
      if (v == FormatField.StandardFields)
        assertTrue(v.isDefault)
      else
        assertFalse(v.isDefault)
    }
  }
}