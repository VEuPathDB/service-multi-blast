package org.veupathdb.service.mblast.query.mixins

import jakarta.ws.rs.core.StreamingOutput
import java.io.InputStream

/**
 * Wraps the target `InputStream` in a JaxRS [StreamingOutput] instance.
 *
 * @receiver `InputStream` to wrap.
 *
 * @return `StreamingOutput` instance wrapping the target `InputStream`.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun InputStream.toStreamingOutput() =
  StreamingOutput { output -> this.use { it.transferTo(output) } }
