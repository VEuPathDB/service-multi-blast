package mb.api.service.util

import jakarta.ws.rs.core.StreamingOutput
import mb.api.model.ContentRange
import java.io.InputStream

internal sealed interface RangedStream : StreamingOutput

internal fun RangedStream(range: ContentRange?, stream: InputStream): RangedStream =
  when (range?.units) {
    null                     -> UnrangedStream(stream)
    ContentRange.Units.Bytes -> ByteRangeStream(stream, range.range)
    ContentRange.Units.Lines -> LineRangeStream(stream, range.range)
  }
