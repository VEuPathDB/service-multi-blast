package org.veupathdb.service.mblast.query.service

import org.veupathdb.service.mblast.query.ServiceOptions
import org.veupathdb.service.mblast.query.model.BlastableTarget
import org.veupathdb.service.mblast.query.model.BlastableTargetImpl
import java.io.File
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

  fun indexBlastTargets(): Map<String, Map<String, BlastableTarget>> {
    val output  = HashMap<String, MutableMap<String, BlastableTargetImpl>>(1)
    val rootDir = File(rootDir)

    if (!rootDir.isDirectory)
      throw IllegalStateException("root path must be a directory")

    for (siteDir in rootDir.listFiles()!!) {

      if (!siteDir.isDirectory)
        continue

      val site = siteDir.name

      for (buildDir in siteDir.listFiles()!!) {

        if (!buildDir.isDirectory || buildDir.name != this.buildDir)
          continue

        for (targetDir in buildDir.listFiles()!!) {

          if (!targetDir.isDirectory)
            continue

          val target = targetDir.name

          for (blastDir in targetDir.listFiles()!!) {

            if (!blastDir.isDirectory || blastDir.name != "blast")
              continue

            for (targetFile in blastDir.listFiles()!!) {

              if (!targetFile.isFile)
                continue

              val db = targetFile.name

              if (db.endsWith(".pin")) {
                output.computeIfAbsent(site) { HashMap(1) }
                  .computeIfAbsent(target) { BlastableTargetImpl() }
                  .aaTargets.add(db.substring(0, db.length - 4))
              } else if (db.endsWith(".nin")) {
                output.computeIfAbsent(site) { HashMap(1) }
                  .computeIfAbsent(target) { BlastableTargetImpl() }
                  .naTargets.add(db.substring(0, db.length - 4))
              }
            }
          }
        }
      }
    }

    return output

  }

  private fun nucleotideDBPath(site: String, target: String, dbName: String) =
    Path.of(rootDir, site, buildDir, target, "blast", "$dbName.nin")

  private fun proteinDBPath(site: String, target: String, dbName: String) =
    Path.of(rootDir, site, buildDir, target, "blast", "$dbName.pin")
}