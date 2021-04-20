package mb.api.service.valid;

import java.util.Collection;

import mb.api.model.IOJobTarget;
import mb.api.model.IOJsonJobRequest;
import mb.api.service.model.ErrorMap;
import mb.api.model.io.JsonKeys;

public class JobPropsValidator
{
  public static ErrorMap validate(IOJsonJobRequest req) {
    var out = new ErrorMap();

    validateTargets(req.getTargets(), out);
    validateMaxSequences(req.getMaxSequences(), out);
    validateSite(req.getSite(), out);

    return out;
  }

  private static void validateTargets(Collection<IOJobTarget> tgts, ErrorMap out) {
    if (tgts.size() == 0) {
      out.putError(JsonKeys.Targets, "Must specify at least one job target.");
    }

    for (var tgt : tgts) {
      if (tgt.target() == null) {
        out.putError(JsonKeys.Targets, "Target file cannot be null.");
      }
    }
  }

  private static void validateMaxSequences(Byte b, ErrorMap out) {
    if (b != null) {
      if (b < 1) {
        out.putError(JsonKeys.MaxSequences, "Must be >= 1");
      } else if (b > 100) {
        out.putError(JsonKeys.MaxSequences, "Must be <= 100");
      }
    }
  }

  private static void validateSite(String site, ErrorMap out) {
    if (site == null || site.isBlank())
      out.putError(JsonKeys.Site, "Target site is required.");
  }
}
