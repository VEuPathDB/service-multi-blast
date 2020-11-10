package org.veupathdb.service.multiblast.model;

/**
 * Validatable describes a type (typically a POJO) whose internal state may be
 * subject to some form of validation.
 *
 * Implementing types may perform any form of validation on their internal
 * fields.
 */
public interface Validatable
{
  /**
   * Validates the internal state of this object, returning a map of field names
   * to one or more errors relating to the named field.
   *
   * Implementations of this method must not return <code>null</code> in the
   * event of a successful validation.  They should instead return an empty
   * {@link ErrorMap}.
   *
   * @return Mapping of 0 or more validation errors for this object.
   */
  ErrorMap validate();
}
