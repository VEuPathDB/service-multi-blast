package org.veupathdb.service.demo;

import javax.ws.rs.ApplicationPath;

import java.io.IOException;

import org.veupathdb.lib.container.jaxrs.config.Options;
import org.veupathdb.lib.container.jaxrs.server.ContainerResources;
import org.veupathdb.lib.container.jaxrs.server.Server;

@ApplicationPath("/")
public class Main extends Server {
  public static void main(String[] args) throws IOException {
    var server = new Main();
    server.enableAccountDB();
    server.start(args);
  }

  @Override
  protected ContainerResources newResourceConfig(Options options) {
    final var out =  new Resources(options);
    out.property("jersey.config.server.tracing.type", "ALL")
      .property("jersey.config.server.tracing.threshold", "VERBOSE");
    return out;
  }
}
