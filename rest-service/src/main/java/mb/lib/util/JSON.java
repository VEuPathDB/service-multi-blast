package mb.lib.util;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class JSON
{
  private static final ObjectMapper Mapper = new ObjectMapper()
    .registerModule(new ParameterNamesModule())
    .registerModule(new Jdk8Module())
    .registerModule(new JavaTimeModule());

  public static <T> T parse(InputStream stream, Class<T> cls) throws Exception {
    return Mapper.readValue(stream, cls);
  }

  public static <T> T parse(String raw, Class<T> cls) throws Exception {
    return Mapper.readValue(raw, cls);
  }

  public static String stringify(Object any) throws Exception {
    return Mapper.writeValueAsString(any);
  }
}
