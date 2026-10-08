@file:JvmName("DBPaths")
package mb.lib.path

import mb.lib.config.Config
import kotlin.io.path.Path

fun findDBPath(site: String, organism: String?, target: String): String? {
  val root = Config.dbMountPath

  val build = if (organism == null)
    Config.orthoBuild
  else
    Config.dbBuild

  return findBuildVersionsFor(site, build)
    .map { SplitDBPath(root, site, it, organism, "blast", target) }
    .firstOrNull(DBPath::exists)
    ?.fullPath
}

/**
 * Finds available builds for the given site.
 *
 * @param site The site whose available builds should be gathered.
 *
 * @return An array of zero or more builds available for the given [site].  If
 * the site is invalid, or no builds exist, an empty array will be returned.
 */
fun findBuildVersionsFor(site: String, build: String): Sequence<String> =
  Path(Config.dbMountPath, site, "build-${build}").toFile()
    .let {
      if (it.exists())
        sequenceOf(it.name)
      else
        emptySequence()
    }
