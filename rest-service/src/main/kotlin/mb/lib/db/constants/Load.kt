package mb.lib.db.constants

import io.vulpine.lib.sql.load.SqlLoader
import java.util.function.Supplier
import java.util.regex.MatchResult
import java.util.regex.Pattern

/**
 * Utility class for loading queries from the resources directory.
 *
 *
 * Queries loaded through this class will also have any `${}` variables replaced
 * values from the environment.
 */
internal object Load {

  private val loader = SqlLoader()
  private val envPat = Pattern.compile("\\$\\{(\\w+)}")

  /**
   * Loads a select query from the resources directory and injects environment
   * variables.
   *
   * @param path Array of path segments to the query file to load.
   *
   * @return The loaded query text.
   */
  fun select(vararg path: String): String {
    return injectVars(loader.select(join(path)).orElseThrow(makeError("select", path)))
  }

  /**
   * Loads an update query from the resources directory and injects environment
   * variables.
   *
   * @param path Array of path segments to the query file to load.
   *
   * @return The loaded query text.
   */
  fun update(vararg path: String): String {
    return injectVars(loader.udpate(join(path)).orElseThrow(makeError("update", path)))
  }

  private fun injectVars(sql: String): String {
    return envPat.matcher(sql).replaceAll { r: MatchResult -> System.getenv(r.group(1)) }
  }

  private fun join(parts: Array<out String>): String {
    return java.lang.String.join(".", *parts)
  }

  private fun makeError(mode: String, path: Array<out String>): Supplier<RuntimeException> {
    return Supplier {
      RuntimeException(String.format(
        "Failed to load query main/resources/sql/%s/%s.sql",
        mode,
        join(path).replace('.', '/')
      ))
    }
  }
}
