package org.veupathdb.lib.blast.serial

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.err.ErrorMap

/**
 * Blast Command Type Base
 */
interface BlastCommand {

  /**
   * Serializes the current [BlastCommand] to a JSON [ObjectNode].
   *
   * Only non-default properties and the `"tool"` property should be present in
   * the output JSON.
   *
   * @return JSON [ObjectNode] representation of this [BlastCommand].
   */
  fun toJson(): ObjectNode

  /**
   * Renders the current [BlastCommand] as a CLI call string.
   *
   * Only non-default properties and the tool name should be present in the
   * output string.
   *
   * @return CLI call string.
   */
  fun toCliString(): String

  /**
   * Converts the current [BlastCommand] to an array of strings making up a CLI
   * call.
   *
   * Only non-default properties and the tool name should be present in the
   * output array.
   *
   * @return CLI call argument array.
   */
  fun toCliArray(): Array<String>

  /**
   * Validates this Blast+ config checking for flags that are incompatible and
   * values that are out of range or invalid.
   *
   * @return An [ErrorMap] containing any errors found in the configuration.
   */
  fun validate(): ErrorMap
}