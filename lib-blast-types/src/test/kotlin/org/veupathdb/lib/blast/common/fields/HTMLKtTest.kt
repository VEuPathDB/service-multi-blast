package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.blast.common.FlagHTML
import org.veupathdb.lib.jackson.Json

@DisplayName("ParseHTML()")
internal class HTMLKtTest {

  @Test
  @DisplayName("returns a defaulted HTML instance when the input object does not contain a -h entry")
  fun t1() {
    val inp = Json.newObject()

    assertTrue(ParseHTML(inp).isDefault)
  }

  @Test
  @DisplayName("returns a HTML instance wrapping true when the input object contains -h: true")
  fun t2() {
    val inp = Json.new<ObjectNode> { put(FlagHTML, true) }

    assertTrue(ParseHTML(inp).value)
  }

  @Test
  @DisplayName("returns a HTML instance wrapping false when the input object contains -h: false")
  fun t3() {
    val inp = Json.new<ObjectNode> { put(FlagHTML, false) }

    assertFalse(ParseHTML(inp).value)
  }

  @Test
  @DisplayName("throws when the input object contains a non-boolean -h value")
  fun t4() {
    val inp = Json.new<ObjectNode> { put(FlagHTML, "yo-yo") }

    assertThrows<IllegalArgumentException> { ParseHTML(inp) }
  }
}