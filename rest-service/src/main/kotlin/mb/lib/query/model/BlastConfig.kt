package mb.lib.query.model

import org.veupathdb.lib.blast.BlastConfig

@JvmInline
value class BlastConfig(val config: BlastConfig) : JobConfig
