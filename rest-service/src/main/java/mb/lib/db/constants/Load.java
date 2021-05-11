package mb.lib.db.constants;

import java.util.function.Supplier;
import java.util.regex.Pattern;

import io.vulpine.lib.sql.load.SqlLoader;

/**
 * Utility class for loading queries from the resources directory.
 * <p>
 * Queries loaded through this class will also have any `${}` variables replaced
 * values from the environment.
 */
class Load
{
  private static final SqlLoader loader = new SqlLoader();
  private static final Pattern   envPat = Pattern.compile("\\$\\{([\\w]+)}");

  /**
   * Loads a select query from the resources directory and injects environment
   * variables.
   *
   * @param path Array of path segments to the query file to load.
   *
   * @return The loaded query text.
   */
  static String select(String... path) {
    return injectVars(loader.select(join(path)).orElseThrow(makeError("select", path)));
  }

  /**
   * Loads an update query from the resources directory and injects environment
   * variables.
   *
   * @param path Array of path segments to the query file to load.
   *
   * @return The loaded query text.
   */
  static String update(String... path) {
    return injectVars(loader.udpate(join(path)).orElseThrow(makeError("update", path)));
  }

  private static String injectVars(String sql) {
    return envPat.matcher(sql).replaceAll(r -> System.getenv(r.group(1)));
  }

  private static String join(String... parts) {
    return String.join(".", parts);
  }

  private static Supplier<RuntimeException> makeError(String mode, String... path) {
    return () -> new RuntimeException(String.format(
      "Failed to load query main/resources/sql/%s/%s.sql",
      mode,
      join(path).replace('.', '/')
    ));
  }
}
