package mb.lib.blast.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import mb.api.service.valid.SequenceValidator;
import mb.api.service.valid.SequenceValidationError;
import org.veupathdb.lib.blast.BlastTool;

public class BlastQuery
{
  private final BlastTool           tool;
  private final String              fullQuery;
  private final List<BlastSubQuery> subQueries;

  private BlastQuery(BlastTool tool, String fullQuery, List<BlastSubQuery> subQueries) {
    this.tool       = tool;
    this.fullQuery  = fullQuery;
    this.subQueries = subQueries;
  }

  public BlastTool getTool() {
    return tool;
  }

  public String getFullQuery() {
    return fullQuery;
  }

  public List<BlastSubQuery> getSubQueries() {
    return subQueries;
  }

  public int getSequenceCount() {
    return subQueries.isEmpty() ? 1 : subQueries.size();
  }

  public SequenceValidationError validate() {
    if (getSubQueries().isEmpty()) {
      return SequenceValidator.getValidator(getTool()).validate(1, getFullQuery());
    }

    var seq = 1;
    for (var sub : getSubQueries()) {
      var err = sub.validate(seq);
      if (err != null)
        return err;
      seq++;
    }

    return null;
  }

  public static BlastQuery parse(BlastTool tool, String multiQuery) {
    var scanner    = new Scanner(multiQuery);
    var subQueries = new ArrayList<BlastSubQuery>();
    var buffer     = new StringBuilder();

    String currentHeader = null;
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();

      if (line.startsWith(">")) {
        if (currentHeader != null) {
          subQueries.add(new BlastSubQuery(tool, currentHeader, buffer.toString()));
          buffer.setLength(0);
        } else {
          currentHeader = line;
        }
      } else {
        buffer.append(line).append("\n");
      }
    }

    if (!buffer.isEmpty()) {
      subQueries.add(new BlastSubQuery(tool, currentHeader, buffer.toString()));
    }

    if (subQueries.size() > 1) {
      return new BlastQuery(tool, multiQuery, subQueries);
    } else {
      return new BlastQuery(tool, multiQuery, Collections.emptyList());
    }
  }
}
