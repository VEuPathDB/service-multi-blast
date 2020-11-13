package org.veupathdb.service.multiblast.service.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.OutFormat;
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

  public List<String> internalUseValidation(OutFormat fmt) {
    var out = new ArrayList<String>(2);

    Optional.ofNullable(validateIntFields(fmt)).ifPresent(out::add);
    Optional.ofNullable(validateIntDelim(fmt)).ifPresent(out::add);

    return out;
  }

  public ErrorMap externalUseValidation(OutFormat fmt) {
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

  public static List<String> validateInternal(OutFormat fmt) {
    return getInstance().internalUseValidation(fmt);
  }

  public static ErrorMap validateExternal(OutFormat fmt) {
    return getInstance().externalUseValidation(fmt);
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Internal Validation Helpers                                      ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  static void validateExtFields(ErrorMap err, OutFormat fmt) {
    var tmp = validateIntFields(fmt);

    if (tmp != null)
      err.putError(JsonKeys.Fields, tmp);
  }

  static String validateIntFields(OutFormat fmt) {
    if (fmt.getFields().isEmpty())
      return null;

    return fmt.isCustomizableFormat() ? null : ErrFields;
  }

  static void validateExtDelim(ErrorMap err, OutFormat fmt) {
    var tmp = validateIntDelim(fmt);

    if (tmp != null)
      err.putError(JsonKeys.Delimiter, tmp);
  }

  static String validateIntDelim(OutFormat fmt) {
    if (fmt.getDelim() == null)
      return null;

    return fmt.allowsDelimiters() ? null : ErrDelim;
  }
}
