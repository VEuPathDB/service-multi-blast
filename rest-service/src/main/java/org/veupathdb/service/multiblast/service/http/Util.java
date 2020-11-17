package org.veupathdb.service.multiblast.service.http;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;

class Util
{
  static WebApplicationException wrapException(Exception e) {
    if (e instanceof WebApplicationException)
      return (WebApplicationException) e;

    return new InternalServerErrorException(e);
  }
}
