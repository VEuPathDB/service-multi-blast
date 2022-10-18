package org.veupathdb.lib.blast.err

import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.jackson.Json

class ErrorMap(size: Int = 16) {
  private val root = HashMap<String, ArrayList<String>>(size)

  /**
   * Appends an error to this [ErrorMap].
   *
   * @param key Name of the field the given error message relates to.
   *
   * @param msg Error message to append.
   */
  fun addError(key: String, msg: String) {
    root.computeIfAbsent(key) { ArrayList(1) }
      .add(msg)
  }

  /**
   * Extracts a Java [Map] of errors from this [ErrorMap].
   *
   * @return The raw [Map] of errors wrapped by this [ErrorMap].
   */
  fun toMap(): Map<String, List<String>> = root

  /**
   * Tests whether this [ErrorMap] contains any errors.
   *
   * @return `true` if this [ErrorMap] contains 1 or more errors, otherwise
   * `false`.
   */
  fun hasErrors() = root.isNotEmpty()

  @Suppress("DEPRECATION")
  fun toJson() = Json.newObject {
    root.forEach { (k, v) ->
      put(k, Json.newArray {
        v.forEach {
          add(it)
        }
      })
    }
  }

  /**
   * Tests flags that are incompatible with one another and appends errors for
   * both incompatible flags when an entry in [others] has a non-default value.
   *
   * If [field] is its default value, this method does nothing.
   *
   * ```
   * errs := ErrorMap()
   *
   * inp_1 := {name: foo, isDefault: false}
   * inp_2 := {name: bar, isDefault: false}
   *
   * errs.incompatible(inp_1, inp_2)
   *
   * errs.toMap == {
   *   foo: Incompatible with bar
   *   bar: Incompatible with foo
   * }
   * ```
   *
   * @param field Primary field to test against.
   *
   * @param others Other fields to test against.
   */
  fun incompatible(field: BlastField, vararg others: BlastField) {
    if (field.isDefault)
      return

    for (f in others) {
      if (!f.isDefault) {
        addError(field.name, "Incompatible with ${f.name}")
        addError(f.name, "Incompatible with ${field.name}")
      }
    }
  }

  /**
   * Tests a flag that requires another flag to ensure that either the first
   * field is its default value, otherwise both fields are non-default.
   *
   * If the first field is non-default and the second field is default, an error
   * will be appended to this [ErrorMap].
   *
   * ```
   * errs := ErrorMap()
   *
   * inp_1 := {name: foo, isDefault: false}
   * inp_2 := {name: bar, isDefault: true}
   *
   * errs.requires(inp_1, inp_2)
   *
   * errs.toMap == {
   *   foo: Requires bar
   * }
   */
  fun requires(field1: BlastField, field2: BlastField) {
    if (!field1.isDefault && field2.isDefault)
      addError(field1.name, "Requires ${field2.name}")
  }
}