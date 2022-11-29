@file:JvmName("DBPaths")
package mb.lib.path

import mb.lib.config.Config
import java.util.*
import java.util.stream.Stream
import kotlin.io.path.Path

fun findDBPath(site: String, organism: String, target: String): Optional<String> {
  val root = Config.dbMountPath

  return findBuildVersionsFor(site)
    .map { SplitDBPath(root, site, it, organism, "blast", target) }
    .filter(DBPath::exists)
    .findFirst()
    .map(DBPath::fullPath)
}

/**
 * Finds available builds for the given site.
 *
 * @param site The site whose available builds should be gathered.
 *
 * @return An array of zero or more builds available for the given [site].  If
 * the site is invalid, or no builds exist, an empty array will be returned.
 */
fun findBuildVersionsFor(site: String): Stream<String> =
  Path(Config.dbMountPath, site, "build-${Config.dbBuild}").toFile()
    .let {
      if (it.exists())
        Stream.of(it.name)
      else
        Stream.empty()
    }

/**
 * Attempts to locate the correct, possibly new Blast target DB path.
 *
 * This is necessary to correct jobs being re-run between site releases.  When a
 * new site build is released, the Blast targets will be deleted and a new set
 * for the new build will be created.  This function attempts to locate the
 * target either in the same build if possible, or in a later build.
 *
 * If the given path exists, it will be the value of the returned option.
 *
 * If the given path does not exist, but that same target exists in a later
 * build, the returned option will be the path to that target.
 *
 * If the given path does not exist and that same target does not exist in a
 * later build, the returned option will be empty.
 *
 * ### Examples
 *
 * **Still Exists**
 * ```
 * Input:  /db/PlasmoDB/build-54/SomeOrganism/blast/SomeOrganismAnnotatedTranscripts
 * Output: /db/PlasmoDB/build-54/SomeOrganism/blast/SomeOrganismAnnotatedTranscripts
 * ```
 *
 * **Found In Later Build**
 * ```
 * Input:  /db/PlasmoDB/build-49/SomeOrganism/blast/SomeOrganismAnnotatedTranscripts
 * Output: /db/PlasmoDB/build-54/SomeOrganism/blast/SomeOrganismAnnotatedTranscripts
 * ```
 *
 * @param fullPath Full path to the original target.
 *
 * Example input: `/db/PlasmoDB/build-52/Pfalciparum3D7/blast/Pfalciparum3D7AnnotatedTranscripts`.
 *
 * @return An option containing one of: the input value if that path still
 * exists, a new path if the same target exists in a later build, or nothing if
 * the given target cannot be found in any available build.
 */
fun findNewDBPath(fullPath: String) = findNewDBPath(RawDBPath(fullPath))

/**
 * Attempts to locate the correct, possibly new Blast target DB path.
 *
 * This is necessary to correct jobs being re-run between site releases.  When a
 * new site build is released, the Blast targets will be deleted and a new set
 * for the new build will be created.  This function attempts to locate the
 * target either in the same build if possible, or in a later build.
 *
 * If the given path exists, it will be the value of the returned option.
 *
 * If the given path does not exist, but that same target exists in a later
 * build, the returned option will be the path to that target.
 *
 * If the given path does not exist and that same target does not exist in a
 * later build, the returned option will be empty.
 *
 * ### Examples
 *
 * **Still Exists**
 * ```
 * Input:  /db/PlasmoDB/build-54/SomeOrganism/blast/SomeOrganismAnnotatedTranscripts
 * Output: /db/PlasmoDB/build-54/SomeOrganism/blast/SomeOrganismAnnotatedTranscripts
 * ```
 *
 * **Found In Later Build**
 * ```
 * Input:  /db/PlasmoDB/build-49/SomeOrganism/blast/SomeOrganismAnnotatedTranscripts
 * Output: /db/PlasmoDB/build-54/SomeOrganism/blast/SomeOrganismAnnotatedTranscripts
 * ```
 *
 * @param path Full path to the original target.
 *
 * Example input: `/db/PlasmoDB/build-52/Pfalciparum3D7/blast/Pfalciparum3D7AnnotatedTranscripts`.
 *
 * @return An option containing one of: the input value if that path still
 * exists, a new path if the same target exists in a later build, or nothing if
 * the given target cannot be found in any available build.
 */
fun findNewDBPath(path: DBPath): Optional<String> {
  if (path.exists)
    return Optional.of(path.fullPath)

  return findBuildVersionsFor(path.site)
    .map { path.withBuild(it) }
    .filter(DBPath::exists)
    .findFirst()
    .map(DBPath::fullPath)
}