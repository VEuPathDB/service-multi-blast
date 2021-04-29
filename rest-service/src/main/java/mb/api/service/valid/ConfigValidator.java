package mb.api.service.valid;

import java.util.Collection;

import mb.api.service.model.ErrorMap;

interface ConfigValidator<C>
{
  String
    errGtEqD       = "must be greater than or equal to %d",
    errGtEqF       = "must be greater than or equal to %.1f",
    errLtEqD       = "must be less than or equal to %d",
    errBetweenIncD = "must be between [%d..%d]",
    errBetweenIncF = "must be between [%.1f..%.1f]",
    errBetweenExcD = "must be between (%d..%d)",
    errBetweenExcF = "must be between (%.1f..%.1f)",
    errIncompat    = "is incompatible with field %s";

  ErrorMap validate(C conf);

  /**
   * Int value validation methods.
   */
  interface Int
  {
    /**
     * Optional value greater than or equal to.
     * <p>
     * If the value {@code val} is not null, it will be checked against
     * {@code min} to verify that {@code val >= min}.  If validation fails, an
     * error will be appended to the validation err map for the current field.
     *
     * @param err Validation error aggregation.
     * @param val Value to validate.
     * @param min Minimum value to check against.
     * @param field Name of the field being validated.
     */
    static void optGtEq(ErrorMap err, Long val, long min, String field) {
      if (val != null) gtEq(err, val, min, field);
    }

    static void gtEq(ErrorMap err, long val, long min, String field) {
      if (val < min) err.putError(field, String.format(errGtEqD, min));
    }

    /**
     * Optional value greater than or equal to.
     * <p>
     * If the value {@code val} is not null, it will be checked against
     * {@code min} to verify that {@code val >= min}.  If validation fails, an
     * error will be appended to the validation err map for the current field.
     *
     * @param err Validation error aggregation.
     * @param val Value to validate.
     * @param min Minimum value to check against.
     * @param field Name of the field being validated.
     */
    static void optGtEq(ErrorMap err, Integer val, int min, String field) {
      if (val != null) gtEq(err, val, min, field);
    }

    static void gtEq(ErrorMap err, int val, int min, String field) {
      if (val < min) err.putError(field, String.format(errGtEqD, min));
    }

    static void gtEq(ErrorMap err, short val, short min, String field) {
      if (val < min) err.putError(field, String.format(errGtEqD, min));
    }

    /**
     * Optional value greater than or equal to.
     * <p>
     * If the value {@code val} is not null, it will be checked against
     * {@code min} to verify that {@code val >= min}.  If validation fails, an
     * error will be appended to the validation err map for the current field.
     *
     * @param err Validation error aggregation.
     * @param val Value to validate.
     * @param min Minimum value to check against.
     * @param field Name of the field being validated.
     */
    static void optGtEq(ErrorMap err, Byte val, byte min, String field) {
      if (val != null) gtEq(err, val, min, field);
    }

    static void gtEq(ErrorMap err, byte val, byte min, String field) {
      if (val < min) err.putError(field, String.format(errGtEqD, min));
    }

    /**
     * Optional value greater than or equal to.
     * <p>
     * If the value {@code val} is not null, it will be checked against
     * {@code min} to verify that {@code val >= min}.  If validation fails, an
     * error will be appended to the validation err map for the current field.
     *
     * @param err Validation error aggregation.
     * @param val Value to validate.
     * @param min Minimum value to check against.
     * @param field Name of the field being validated.
     */
    static void optGtEq(ErrorMap err, Short val, short min, String field) {
      if (val != null) gtEq(err, val, min, field);
    }

    /**
     * Optional value less than or equal to.
     * <p>
     * If the value {@code val} is not null, it will be checked against
     * {@code max} to verify that {@code val <= max}.  If validation fails, an
     * error will be appended to the validation err map for the current field.
     *
     * @param err Validation error aggregation.
     * @param val Value to validate.
     * @param max Maximum value to check against.
     * @param field Name of the field being validated.
     */
    static void optLtEq(ErrorMap err, Integer val, int max, String field) {
      if (val != null) ltEq(err, val, max, field);
    }

    static void ltEq(ErrorMap err, int val, int max, String field) {
      if (val > max) err.putError(field, String.format(errLtEqD, max));
    }
  }

  interface Dec
  {
    static void optGtEq(ErrorMap err, Double val, double min, String field) {
      if (val != null) gtEq(err, val, min, field);
    }

    static void gtEq(ErrorMap err, double val, double min, String field) {
      if (val < min) err.putError(field, String.format(errGtEqF, min));
    }

    static void betweenExc(ErrorMap err, double val, double min, double max, String field) {
      if (val <= min || val >= max) err.putError(field, String.format(errBetweenExcF, min, max));
    }

    static void optBetweenInc(ErrorMap err, Double val, double min, double max, String field) {
      if (val != null) betweenInc(err, val, min, max, field);
    }

    static void betweenInc(ErrorMap err, double val, double min, double max, String field) {
      if (val < min || val > max) err.putError(field, String.format(errBetweenIncF, min, max));
    }
  }

  interface Obj
  {
    /**
     * Incompatibility Check
     * <p>
     * If {@code obj} is null, does nothing.<br>
     * If {@code obj} is not null, appends an error keyed on {@code self} for
     * incompatibility with {@code other}.
     *
     * @param err   Error collection
     * @param obj   Object that the calling context is incompatible with.
     * @param self  Target/current field name.
     * @param other Field name for {@code obj}
     */
    static void incompat(ErrorMap err, Object obj, String self, String other) {
      if (obj != null)
        err.putError(self, String.format(errIncompat, other));
    }

    /**
     * Collection Incompatibility Check
     * <p>
     * If {@code other} is null, does nothing.<br>
     * If {@code other} is not null and is empty, does nothing.<br>
     * If {@code other} is not null and is not empty, appends an error keyed on
     * {@code tField} for incompatibility with {@code oField}.
     *
     * @param err Error collection
     * @param other Collection that the calling context is incompatible with.
     * @param tField Target/current field name.
     * @param oField Field name for {@code other}
     */
    static void colIncompat(ErrorMap err, Collection<?> other, String tField, String oField) {
      if (other != null && !other.isEmpty())
        err.putError(tField, String.format(errIncompat, oField));
    }
  }
}