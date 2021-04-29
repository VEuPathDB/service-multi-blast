package mb.api.service.http.job;

import javax.ws.rs.BadRequestException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("JobUtil")
class JobUtilTest
{
  @Nested
  @DisplayName("::verifyQuery(Object)")
  class VerifyQuery
  {
    @Test
    @DisplayName("Throws an exception when the input is null")
    void test1() {
      Assertions.assertThrows(BadRequestException.class, () -> JobUtil.verifyQuery(null));
    }

    @Test
    @DisplayName("Does not throw an exception when the input is not null")
    void test2() {
      JobUtil.verifyQuery(new Object());
    }
  }

  @Nested
  @DisplayName("::verifyBody(Object)")
  class VerifyBody
  {
    @Test
    @DisplayName("Throws an exception when the input is null")
    void test1() {
      Assertions.assertThrows(BadRequestException.class, () -> JobUtil.verifyBody(null));
    }

    @Test
    @DisplayName("Does not throw an exception when the input is not null")
    void test2() {
      JobUtil.verifyBody(new Object());
    }
  }

  @Nested
  @DisplayName("::verifyProps(Object)")
  class VerifyProps
  {
    @Test
    @DisplayName("Throws an exception when the input is null")
    void test1() {
      Assertions.assertThrows(BadRequestException.class, () -> JobUtil.verifyProps(null));
    }

    @Test
    @DisplayName("Does not throw an exception when the input is not null")
    void test2() {
      JobUtil.verifyProps(new Object());
    }
  }

  @Nested
  @DisplayName("::verifyConfig(Object)")
  class VerifyConfig
  {
    @Test
    @DisplayName("Throws an exception when the input is null")
    void test1() {
      Assertions.assertThrows(BadRequestException.class, () -> JobUtil.verifyConfig(null));
    }

    @Test
    @DisplayName("Does not throw an exception when the input is not null")
    void test2() {
      JobUtil.verifyConfig(new Object());
    }
  }

  @Nested
  @DisplayName("::nullCheck(Object, String)")
  class NullCheck
  {
    @Test
    @DisplayName("Throws an exception containing the given string when the input is null")
    void test1() {
      Assertions.assertEquals("Hello", Assertions.assertThrows(BadRequestException.class, () -> JobUtil.nullCheck(null, "Hello")).getMessage());
    }

    @Test
    @DisplayName("Does not throw an exception when the input is not null")
    void test2() {
      JobUtil.nullCheck(new Object(), "Hi");
    }
  }
}