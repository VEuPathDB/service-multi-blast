package org.veupathdb.lib.blast.serial

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.jackson.Json

/**
 * Represents a single, defaultable field/argument for a blast tool.
 */
interface BlastField {

  /**
   * Indicates whether the field is currently set to its default value.
   */
  val isDefault: Boolean

  /**
   * The name of this field.
   */
  val name: String

  /**
   * Serializes this field into a JSON [ObjectNode].
   *
   * If this field is set to its default value, the returned object will be
   * empty.
   *
   * @return The serialized JSON object.
   */
  @JsonValue
  fun toJson() = Json.new(this::appendJson)

  /**
   * Appends this field to the given JSON [ObjectNode] only if it is not set to
   * its default value.
   */
  fun appendJson(js: ObjectNode)

  /**
   * Appends this field to the given [StringBuilder] only if it is not set to
   * its default value.
   */
  fun appendCliSegment(cli: StringBuilder)

  /**
   * Appends this field to the given [MutableList] only if it is not set to its
   * default value.
   */
  fun appendCliParts(cli: MutableList<String>)
}