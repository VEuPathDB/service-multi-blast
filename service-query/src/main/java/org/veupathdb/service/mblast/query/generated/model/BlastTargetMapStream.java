
package org.veupathdb.service.mblast.query.generated.model;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.StreamingOutput;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Consumer;

public class BlastTargetMapStream extends BlastTargetMapImpl implements StreamingOutput {

  private final Consumer<OutputStream> _streamer;

  public BlastTargetMapStream(Consumer<OutputStream> streamer) {
    _streamer = streamer;
  }

  @Override
  public void write(OutputStream output) throws IOException, WebApplicationException {
    _streamer.accept(output);
  }
}
