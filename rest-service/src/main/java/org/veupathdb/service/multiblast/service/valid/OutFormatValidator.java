package org.veupathdb.service.multiblast.service.valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.veupathdb.service.multiblast.generated.model.InputBlastFormat;
import org.veupathdb.service.multiblast.generated.model.InputBlastOutFmt;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

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

  public List<String> internalUseValidation(InputBlastOutFmt fmt) {
    var out = new ArrayList<String>(2);

    Optional.ofNullable(validateIntFields(fmt)).ifPresent(out::add);
    Optional.ofNullable(validateIntDelim(fmt)).ifPresent(out::add);

    return out;
  }

  public ErrorMap externalUseValidation(InputBlastOutFmt fmt) {
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

  public static ErrorMap validateExternal(InputBlastOutFmt fmt) {
    return getInstance().externalUseValidation(fmt);
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Internal Validation Helpers                                      ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  static void validateExtFields(ErrorMap err, InputBlastOutFmt fmt) {
    var tmp = validateIntFields(fmt);

    if (tmp != null)
      err.putError(JsonKeys.Fields, tmp);
  }

  static String validateIntFields(InputBlastOutFmt fmt) {
    if (fmt.getFields().isEmpty())
      return null;

    return isCustomizableFormat(fmt.getFormat()) ? null : ErrFields;
  }

  static void validateExtDelim(ErrorMap err, InputBlastOutFmt fmt) {
    var tmp = validateIntDelim(fmt);

    if (tmp != null)
      err.putError(JsonKeys.Delimiter, tmp);
  }

  static String validateIntDelim(InputBlastOutFmt fmt) {
    if (fmt.getDelim() == null)
      return null;

    return allowsDelimiters(fmt.getFormat()) ? null : ErrDelim;
  }

  static boolean allowsDelimiters(InputBlastFormat fmt) {
    return switch (fmt) {
      case TABULAR, TABULARWITHCOMMENTS, CSV -> true;
      default -> false;
    };
  }

  static boolean isCustomizableFormat(InputBlastFormat fmt) {
    return switch (fmt) {
      case TABULAR, TABULARWITHCOMMENTS, CSV, SAM -> true;
      default -> false;
    };
  }

}
