package mb.api.service.http;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;

public class Util
{
  public static WebApplicationException wrapException(Exception e) {
    if (e instanceof WebApplicationException)
      return (WebApplicationException) e;

    return new InternalServerErrorException(e);
  }
}
