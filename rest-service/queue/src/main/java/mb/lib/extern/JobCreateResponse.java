package mb.lib.extern;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobCreateResponse
{
  private int id;

  @JsonGetter("id")
  public int getId() {
    return id;
  }

  @JsonSetter("id")
  public JobCreateResponse setId(int id) {
    this.id = id;
    return this;
  }
}
