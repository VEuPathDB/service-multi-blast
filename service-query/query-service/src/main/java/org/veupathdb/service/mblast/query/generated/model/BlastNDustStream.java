
package org.veupathdb.service.mblast.query.generated.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Consumer;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.StreamingOutput;

public class BlastNDustStream extends BlastNDustImpl implements StreamingOutput {

  private final Consumer<OutputStream> _streamer;

  public BlastNDustStream(Consumer<OutputStream> streamer) {
    _streamer = streamer;
  }

  @Override
  public void write(OutputStream output) throws IOException, WebApplicationException {
    _streamer.accept(output);
  }
}