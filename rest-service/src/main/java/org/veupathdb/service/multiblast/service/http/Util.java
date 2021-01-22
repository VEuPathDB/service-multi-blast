package org.veupathdb.service.multiblast.service.http;

import java.nio.file.Path;
import java.util.Base64;
import java.util.Collections;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;

import mb.lib.config.Config;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;

public class Util
{
  public static WebApplicationException wrapException(Exception e) {
    if (e instanceof WebApplicationException)
      return (WebApplicationException) e;

    return new InternalServerErrorException(e);
  }
}
