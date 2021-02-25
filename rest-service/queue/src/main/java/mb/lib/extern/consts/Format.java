package mb.lib.extern.consts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Format
{
  public static final ObjectMapper JSON = new ObjectMapper()
    .registerModule(new JavaTimeModule());
}
