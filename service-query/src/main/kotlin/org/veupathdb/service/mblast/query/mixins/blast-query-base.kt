package org.veupathdb.service.mblast.query.mixins

import org.veupathdb.lib.blast.common.BlastQueryBase

/**
 * Returns an `InputStream` over the JSON stringified form of the target config.
 *
 * @receiver Config to JSON stringify then stream.
 *
 * @return An `InputStream` over the JSON stringified form of the target config.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun BlastQueryBase.toInputStream() = toJson().toString().byteInputStream()


/**
 * Returns a `Reader` over the JSON stringified form of the target config.
 *
 * @receiver Config to JSON stringify then stream.
 *
 * @return A `Reader` over the JSON stringified form of the target config.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun BlastQueryBase.toReader() = toJson().toString().reader()