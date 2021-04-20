package mb.api.service.valid;

import mb.api.model.blast.IOBlastFormat;
import mb.api.model.blast.IOBlastReportFormat;
import mb.api.service.model.ErrorMap;
import mb.api.model.io.JsonKeys;

public class OutFormatValidator
{
  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Error Messages                                                   ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private static final String
    ErrFields = "the selected output format does not allow defining custom report fields.",
    ErrDelim  = "the selected output format does not allow custom delimiters.";

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Instance Management                                              ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private static OutFormatValidator instance;

  private OutFormatValidator() {}

  public static OutFormatValidator getInstance() {
    if (instance == null)
      instance = new OutFormatValidator();

    return instance;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Instance Methods                                                 ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  public ErrorMap externalUseValidation(IOBlastReportFormat fmt) {
    var errors = new ErrorMap();

    validateExtFields(errors, fmt);
    validateExtDelim(errors, fmt);

    return errors;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Static Access Shortcuts                                          ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  public static ErrorMap validateExternal(IOBlastReportFormat fmt) {
    return getInstance().externalUseValidation(fmt);
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Internal Validation Helpers                                      ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  static void validateExtFields(ErrorMap err, IOBlastReportFormat fmt) {
    var tmp = validateIntFields(fmt);

    if (tmp != null)
      err.putError(JsonKeys.Fields, tmp);
  }

  static String validateIntFields(IOBlastReportFormat fmt) {
    if (fmt.getFields() == null || fmt.getFields().isEmpty())
      return null;

    return isCustomizableFormat(fmt.getFormat()) ? null : ErrFields;
  }

  static void validateExtDelim(ErrorMap err, IOBlastReportFormat fmt) {
    var tmp = validateIntDelim(fmt);

    if (tmp != null)
      err.putError(JsonKeys.Delimiter, tmp);
  }

  static String validateIntDelim(IOBlastReportFormat fmt) {
    if (fmt.getDelim() == null)
      return null;

    return allowsDelimiters(fmt.getFormat()) ? null : ErrDelim;
  }

  static boolean allowsDelimiters(IOBlastFormat fmt) {
    return switch (fmt) {
      case TABULAR, TABULARWITHCOMMENTS, CSV -> true;
      default -> false;
    };
  }

  static boolean isCustomizableFormat(IOBlastFormat fmt) {
    return switch (fmt) {
      case TABULAR, TABULARWITHCOMMENTS, CSV, SAM -> true;
      default -> false;
    };
  }

}
