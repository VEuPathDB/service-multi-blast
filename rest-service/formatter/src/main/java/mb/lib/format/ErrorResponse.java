package mb.lib.format;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse
{
  public final int code;
  public final String message;
  public final String requestID;

  @JsonCreator
  public ErrorResponse(
    @JsonProperty("type")      int code,
    @JsonProperty("message")   String message,
    @JsonProperty("requestID") String requestID)
  {
    this.code      = code;
    this.message   = message;
    this.requestID = requestID;
  }
}
