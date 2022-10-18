package org.veupathdb.lib.blast.blastn.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.jackson.Json

@DisplayName("NoDust")
internal class NoDustTest {

  @Test
  @DisplayName("isYes == false")
  fun test1() {
    assertFalse(NoDust.isYes)
  }

  @Test
  @DisplayName("isNo == true")
  fun test2() {
    assertTrue(NoDust.isNo)
  }

  @Test
  @DisplayName("level access throws exception")
  fun test3() {
    assertThrows<IllegalStateException> { NoDust.level }
  }

  @Test
  @DisplayName("window access throws exception")
  fun test4() {
    assertThrows<IllegalStateException> { NoDust.window }
  }

  @Test
  @DisplayName("linker access throws exception")
  fun test5() {
    assertThrows<IllegalStateException> { NoDust.linker }
  }

  @Test
  @DisplayName("isDefault == false")
  fun test6() {
    assertFalse(NoDust.isDefault)
  }

  @Test
  @DisplayName("#appendJson(ObjectNode) appends a \"no\" dust property")
  fun test7() {
    val inp = Json.newObject()

    NoDust.appendJson(inp)

    assertEquals("""{"-dust":"no"}""", inp.toString())
  }

  @Test
  @DisplayName("#appendCliSegment(StringBuilder) appends a \"no\" dust cli arg")
  fun test8() {
    val inp = StringBuilder()

    NoDust.appendCliSegment(inp)

    assertEquals(" -dust no", inp.toString())
  }

  @Test
  @DisplayName("#appendCliParts(List) appends \"no\" dust cli parts")
  fun test9() {
    val inp = ArrayList<String>(2)

    NoDust.appendCliParts(inp)

    assertEquals(2, inp.size)
    assertEquals("-dust", inp[0])
    assertEquals("no", inp[1])
  }
}