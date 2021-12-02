package mb.api.service.http.job

import javax.ws.rs.BadRequestException

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("JobUtil")
class JobUtilTest
{
  @Nested
  @DisplayName("::verifyQuery(Object)")
  inner class VerifyQuery
  {
    @Test
    @DisplayName("Throws an exception when the input is null")
    fun test1() {
      Assertions.assertThrows(BadRequestException::class.java) { verifyQuery(null) }
    }

    @Test
    @DisplayName("Does not throw an exception when the input is not null")
    fun test2() {
      verifyQuery(Object())
    }
  }

  @Nested
  @DisplayName("::verifyBody(Object)")
  inner class VerifyBody
  {
    @Test
    @DisplayName("Throws an exception when the input is null")
    fun test1() {
      Assertions.assertThrows(BadRequestException::class.java) { verifyBody(null) }
    }

    @Test
    @DisplayName("Does not throw an exception when the input is not null")
    fun test2() {
      verifyBody(Object())
    }
  }

  @Nested
  @DisplayName("::verifyProps(Object)")
  inner class VerifyProps
  {
    @Test
    @DisplayName("Throws an exception when the input is null")
    fun test1() {
      Assertions.assertThrows(BadRequestException::class.java) { verifyProps(null) }
    }

    @Test
    @DisplayName("Does not throw an exception when the input is not null")
    fun test2() {
      verifyProps(Object())
    }
  }

  @Nested
  @DisplayName("::verifyConfig(Object)")
  inner class VerifyConfig
  {
    @Test
    @DisplayName("Throws an exception when the input is null")
    fun test1() {
      Assertions.assertThrows(BadRequestException::class.java) { verifyConfig(null) }
    }

    @Test
    @DisplayName("Does not throw an exception when the input is not null")
    fun test2() {
      verifyConfig(Object())
    }
  }

  @Nested
  @DisplayName("::nullCheck(Object, String)")
  inner class NullCheck
  {
    @Test
    @DisplayName("Throws an exception containing the given string when the input is null")
    fun test1() {
      Assertions.assertEquals("Hello", Assertions.assertThrows(BadRequestException::class.java) { nullCheck(null, "Hello") }.message)
  }

    @Test
    @DisplayName("Does not throw an exception when the input is not null")
    fun test2() {
      nullCheck(Object(), "Hi")
    }
  }
}