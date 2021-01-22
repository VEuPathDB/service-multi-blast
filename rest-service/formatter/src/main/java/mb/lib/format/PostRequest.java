package mb.lib.format;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostRequest
{
  private final Character delimiter;
  private final String[] fields;

  public PostRequest(Character delimiter, String[] fields) {
    this.delimiter = delimiter;
    this.fields    = fields;
  }

  @JsonGetter("delim")
  public Character getDelimiter() {
    return delimiter;
  }

  @JsonGetter("fields")
  public String[] getFields() {
    return fields;
  }
}
