package org.veupathdb.service.mblast.report.ext

import jakarta.ws.rs.core.StreamingOutput
import java.io.InputStream

fun InputStream.toStreamingOutput() =
  StreamingOutput { o -> use { it.transferTo(o) } }