package org.veupathdb.service.demo.container.middleware;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

import javax.annotation.Priority;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.ext.Provider;
import java.util.function.Consumer;

import static java.lang.String.format;

@Provider
@Priority(1)
@PreMatching
public class RequestLogger implements ContainerRequestFilter, ContainerResponseFilter {
  private static final String
    START_FORMAT = "Request start: %s %s",
    END_FORMAT   = "Request end: %s %s %d";

  private static final Logger LOG = LogManager.getLogger(RequestLogger.class);

  @Override
  public void filter(ContainerRequestContext req) {
    LOG.debug((Supplier<?>) () -> format(START_FORMAT, req.getMethod(),
      "/" + req.getUriInfo().getPath()));
  }

  @Override
  public void filter(ContainerRequestContext req, ContainerResponseContext res) {
    final Consumer<Supplier<?>> fn;
    if (res.getStatusInfo().getFamily() == Family.SERVER_ERROR) {
      fn = LOG::warn;
    } else {
      fn = LOG::debug;
    }

    fn.accept(() -> format(END_FORMAT, req.getMethod(),
      "/" + req.getUriInfo().getPath(), res.getStatus()));
  }
}
