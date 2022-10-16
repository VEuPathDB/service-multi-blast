package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.jackson.Json

@DisplayName("ParseHelpLong()")
internal class HelpLongKtTest {

  @Test
  @DisplayName("returns a defaulted HelpLong instance when the input object does not contain a -help entry")
  fun t1() {
    val inp = Json.newObject()

    assertTrue(ParseHelpLong(inp).isDefault)
  }

  @Test
  @DisplayName("returns a HelpLong instance wrapping true when the input object contains -help: true")
  fun t2() {
    val inp = Json.new<ObjectNode> { put("-help", true) }

    assertTrue(ParseHelpLong(inp).value)
  }

  @Test
  @DisplayName("returns a HelpLong instance wrapping false when the input object contains -help: false")
  fun t3() {
    val inp = Json.new<ObjectNode> { put("-help", false) }

    assertFalse(ParseHelpLong(inp).value)
  }

  @Test
  @DisplayName("throws when the input object contains a non-boolean -help value")
  fun t4() {
    val inp = Json.new<ObjectNode> { put("-help", "yo-yo") }

    assertThrows<IllegalArgumentException> { ParseHelpLong(inp) }
  }
}