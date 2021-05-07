package mb.lib.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BlastQueryUtil
{
  static List<String> splitQuery(String query) {
    var build = new StringBuilder();
    var scan  = new Scanner(query);
    var out   = new ArrayList<String>(16);

    while (scan.hasNextLine()) {
      var line = scan.nextLine();
      if (line.startsWith(">")) {
        if (build.length() > 0) {
          out.add(build.toString().trim());
          build.setLength(0);
        }
      }

      build.append(line).append("\n");
    }

    if (build.length() > 0)
      out.add(build.toString().trim());

    return out;
  }
}
