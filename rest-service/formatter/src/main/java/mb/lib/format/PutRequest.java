package mb.lib.format;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutRequest
{
  private final char delimiter;
  private final String[] fields;

  public PutRequest(char delimiter, String[] fields) {
    this.delimiter = delimiter;
    this.fields    = fields;
  }

  @JsonGetter("delim")
  public char getDelimiter() {
    return delimiter;
  }

  @JsonGetter("fields")
  public String[] getFields() {
    return fields;
  }
}
