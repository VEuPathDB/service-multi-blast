package org.veupathdb.service.mblast.report.service

import io.prometheus.client.Histogram

object Metrics {
  val BlastTimes = Histogram.build()
    .name("blast_command_time_millis")
    .help("BLAST+ CLI tool execution time in milliseconds.")
    .buckets(
      50.0,         // 50ms
      100.0,        // 100ms
      500.0,        // 500ms
      1_000.0,      // 1s
      5_000.0,      // 5s
      10_000.0,     // 10s
      30_000.0,     // 30s
      60_000.0,     // 1m
      120_000.0,    // 2m
      300_000.0,    // 5m
      600_000.0,    // 10m
    )
    .labelNames("blast_tool")
    .register()
}