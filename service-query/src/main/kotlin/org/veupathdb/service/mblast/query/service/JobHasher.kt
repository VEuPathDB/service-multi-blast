@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.service.mblast.query.service

import io.foxcapades.lib.md5.MD5
import io.foxcapades.lib.md5.MD5Hash
import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import org.veupathdb.service.mblast.query.model.BlastTarget
import org.veupathdb.service.mblast.query.service.JobHasher.hashJob
import java.io.File

/**
 * Container Object for the [hashJob] function and its supporting functions.
 */
object JobHasher {

  /**
   * Generates a [HashID] for the given job configuration components.
   *
   * @param blastConfig BLAST+ tool CLI config.
   *
   * This config should not have its `-db` field set.  This flag value is only
   * valid for one build due to the fact that the build number is in the path.
   * This means job configs submitted in future builds that are identical will
   * never hash the same because the DB paths will have changed.
   *
   * If the `-db` flag is set on the given config, an exception will be thrown.
   *
   * @param projectID Site name.
   *
   * @param query Query file handle.
   *
   * This query file will be read and hashed.
   *
   * @param targets List of BLAST target databases.
   *
   * @return HashID wrapping the hash of the given job configuration.
   *
   * @throws IllegalArgumentException If the given [blastConfig] has a `-db`
   * flag value.
   */
  fun hashJob(
    blastConfig: BlastQueryBase,
    projectID: String,
    query: File,
    targets: List<BlastTarget>
  ): HashID {
    validateConfig(blastConfig)
    return MD5.hash(buildHashableString(blastConfig, projectID, query, targets)).toHashID()
  }

  private inline fun buildHashableString(
    blastConfig: BlastQueryBase,
    projectID: String,
    query: File,
    targets: List<BlastTarget>
  ) = buildHashableJson(blastConfig, projectID, query, targets).toString()

  private inline fun buildHashableJson(
    blastConfig: BlastQueryBase,
    projectID: String,
    query: File,
    targets: List<BlastTarget>,
  ) = Json.newObject {
    // !! THE ORDER THE PROPERTIES ARE SET HERE AFFECTS THE OUTPUT HASH !!
    put("blastConfig", blastConfig.toJson())
    put("projectID", projectID)
    put("query", MD5.hash(query).toHexString())
    put("targets", targets.toJsonArray())
  }

  private inline fun validateConfig(blastConfig: BlastQueryBase) {
    if (blastConfig.dbFile.files.isNotEmpty())
      throw IllegalArgumentException("Hashing a blast config with db files present will produce a hash that cannot be replicated after site build number changes.")
  }

  private inline fun MD5Hash.toHashID() = HashID(unsafeBytes)

  private inline fun List<BlastTarget>.toJsonArray() = stream()
    .map { "${it.displayName}:${it.fileName}" }
    .sorted()
    .collect(Json::newArray, { a, s -> a.add(s) }, { a, b -> a.addAll(b) })
}