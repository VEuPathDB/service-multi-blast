package org.veupathdb.service.mblast.query.service

import org.veupathdb.service.mblast.query.ServiceOptions
import java.nio.file.Path
import kotlin.io.path.exists

/**
 * # Blast Target Manager
 *
 * Not really a "manager", really just a collection of methods relating to paths
 * to BLAST+ target database files.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  2.0.0
 */
object BlastTargetManager {

  private val rootDir
    get() = ServiceOptions.blastDBRootDir

  private val buildDir
    get() = ServiceOptions.siteBuildDir

  /**
   * Tests whether a BLAST database exists matching the given parameters.
   *
   * @param site Project ID for the site that should contain the target BLAST
   * database files.
   *
   * @param target Name of the target group or organism that the DB relates to.
   *
   * @param dbName Name of the specific DB to look up.
   *
   * @return `true` if a BLAST+ database was found matching the given values,
   * otherwise `false`.
   */
  fun dbExists(site: String, target: String, dbName: String) =
    nucleotideDBPath(site, target, dbName).exists()
      || proteinDBPath(site, target, dbName).exists()

  /**
   * Constructs a valid path to a BLAST database matching the given parameters.
   *
   * If no such BLAST database exists matching the given params, an exception
   * will be thrown.
   *
   * @param site Project ID for the site that should contain the target BLAST
   * database files.
   *
   * @param target Name of the target group or organism that the DB relates to.
   *
   * @param dbName Name of the specific DB to look up.
   *
   * @return A path to the target BLAST database.
   *
   * @throws IllegalStateException If no such database was found matching the
   * given values.
   */
  fun dbPath(site: String, target: String, dbName: String): String {
    var path = nucleotideDBPath(site, target, dbName)
    if (path.exists())
      return path.toString().let { it.substring(0, it.length-4) }

    path = proteinDBPath(site, target, dbName)
    if (path.exists())
      return path.toString().let { it.substring(0, it.length-4) }

    throw IllegalStateException()
  }

  private fun nucleotideDBPath(site: String, target: String, dbName: String) =
    Path.of(rootDir, site, buildDir, target, "blast", "$dbName.nin")

  private fun proteinDBPath(site: String, target: String, dbName: String) =
    Path.of(rootDir, site, buildDir, target, "blast", "$dbName.pin")
}