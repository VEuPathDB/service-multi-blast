@file:JvmName("DBPaths")
package mb.lib.path

import mb.lib.config.Config
import java.util.*
import java.util.stream.Stream
import kotlin.io.path.Path

fun findDBPath(site: String, organism: String?, target: String): Optional<String> {
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
