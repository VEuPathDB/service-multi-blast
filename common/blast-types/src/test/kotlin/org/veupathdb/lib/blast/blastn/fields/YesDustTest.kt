package org.veupathdb.lib.blast.blastn.fields

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.veupathdb.lib.jackson.Json

@DisplayName("YesDust")
internal class YesDustTest {

  @Test
  @DisplayName("isYes == true")
  fun test1() {
    assertTrue(YesDust.isYes)
  }

  @Test
  @DisplayName("isNo == false")
  fun test2() {
    assertFalse(YesDust.isNo)
  }

  @Test
  @DisplayName("level access throws exception")
  fun test3() {
    assertThrows<IllegalStateException> { YesDust.level }
  }

  @Test
  @DisplayName("window access throws exception")
  fun test4() {
    assertThrows<IllegalStateException> { YesDust.window }
  }

  @Test
  @DisplayName("linker access throws exception")
  fun test5() {
    assertThrows<IllegalStateException> { YesDust.linker }
  }

  @Test
  @DisplayName("isDefault == false")
  fun test6() {
    assertFalse(YesDust.isDefault)
  }

  @Test
  @DisplayName("#appendJson(ObjectNode) appends a \"yes\" dust property")
  fun test7() {
    val inp = Json.newObject()

    YesDust.appendJson(inp)

    assertEquals("""{"-dust":"yes"}""", inp.toString())
  }

  @Test
  @DisplayName("#appendCliSegment(StringBuilder) appends a \"yes\" dust cli arg")
  fun test8() {
    val inp = StringBuilder()

    YesDust.appendCliSegment(inp)

    assertEquals(" -dust yes", inp.toString())
  }

  @Test
  @DisplayName("#appendCliParts(List) appends \"yes\" dust cli parts")
  fun test9() {
    val inp = ArrayList<String>(2)

    YesDust.appendCliParts(inp)

    assertEquals(2, inp.size)
    assertEquals("-dust", inp[0])
    assertEquals("yes", inp[1])
  }
}