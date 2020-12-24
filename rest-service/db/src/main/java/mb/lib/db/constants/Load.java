package mb.lib.db.constants;

import java.util.function.Supplier;
import java.util.regex.Pattern;

import io.vulpine.lib.sql.load.SqlLoader;

class Load
{
  private static final SqlLoader loader = new SqlLoader();
  private static final Pattern   envPat = Pattern.compile("\\$\\{([\\w]+)}");

  static String delete(String... path) {
    return injectVars(loader.delete(join(path)).orElseThrow(makeError("delete", path)));
  }

  static String insert(String... path) {
    return injectVars(loader.insert(join(path)).orElseThrow(makeError("insert", path)));
  }

  static String select(String... path) {
    return injectVars(loader.select(join(path)).orElseThrow(makeError("select", path)));
  }

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
