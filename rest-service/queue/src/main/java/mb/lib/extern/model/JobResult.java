package mb.lib.extern.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.lib.extern.consts.JsonKeys;

public class JobResult
{
  private String status;
  private int code;
  private String message;

  @JsonGetter(JsonKeys.Status)
  public String getStatus() {
    return status;
  }

  @JsonSetter(JsonKeys.Status)
  public JobResult setStatus(String status) {
    this.status = status;
    return this;
  }

  @JsonGetter(JsonKeys.Code)
  public int getCode() {
    return code;
  }

  @JsonSetter(JsonKeys.Code)
  public JobResult setCode(int code) {
    this.code = code;
    return this;
  }

  @JsonGetter(JsonKeys.Message)
  public String getMessage() {
    return message;
  }

  @JsonSetter(JsonKeys.Message)
  public JobResult setMessage(String message) {
    this.message = message;
    return this;
  }
}
