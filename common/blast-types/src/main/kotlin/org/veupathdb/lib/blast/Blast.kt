package org.veupathdb.lib.blast

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.blastn.BlastN
import org.veupathdb.lib.blast.blastn.BlastNImpl
import org.veupathdb.lib.blast.blastp.BlastP
import org.veupathdb.lib.blast.blastp.BlastPImpl
import org.veupathdb.lib.blast.blastx.BlastX
import org.veupathdb.lib.blast.blastx.BlastXImpl
import org.veupathdb.lib.blast.common.BlastCLI
import org.veupathdb.lib.blast.deltablast.DeltaBlast
import org.veupathdb.lib.blast.deltablast.DeltaBlastImpl
import org.veupathdb.lib.blast.formatter.BlastFormatter
import org.veupathdb.lib.blast.formatter.BlastFormatterImpl
import org.veupathdb.lib.blast.psiblast.PSIBlast
import org.veupathdb.lib.blast.psiblast.PSIBlastImpl
import org.veupathdb.lib.blast.rpsblast.RPSBlast
import org.veupathdb.lib.blast.rpsblast.RPSBlastImpl
import org.veupathdb.lib.blast.rpstblastn.RPSTBlastN
import org.veupathdb.lib.blast.rpstblastn.RPSTBlastNImpl
import org.veupathdb.lib.blast.tblastn.TBlastN
import org.veupathdb.lib.blast.tblastn.TBlastNImpl
import org.veupathdb.lib.blast.tblastx.TBlastX
import org.veupathdb.lib.blast.tblastx.TBlastXImpl
import org.veupathdb.lib.blast.util.optString

/**
 * Entrypoint to the Blast library.
 *
 * Provides methods for constructing BLAST+ tool configurations or parsing them
 * from JSON input.
 *
 * @author Elizabeth Harper [foxcapades.io@gmail.com]
 * @since  v6.0.0
 */
object Blast {

  /**
   * Creates a new [BlastCLI] instance of one of the following types from the
   * given input JSON:
   *
   * * [BlastN]
   * * [BlastP]
   * * [BlastX]
   * * [DeltaBlast]
   * * [PSIBlast]
   * * [RPSBlast]
   * * [RPSTBlastN]
   * * [TBlastN]
   * * [TBlastX]
   * * [BlastFormatter]
   *
   * This method is intended to be used to construct a BLAST+ configuration
   * instance from a JSON value where the type of the config is not known
   * beforehand.
   *
   * The [BlastCLI.tool] property may be used after deserialization to determine
   * the type of the config and cast it correctly.
   *
   * Example
   * ```kotlin
   * // input == {
   * //   "tool": "blastn",
   * //   ...
   * //}
   *
   * val config = Blast.of(input)
   *
   * if (config.tool == BlastTool.BlastN)
   *   val blastnConfig = config as BlastN
   * ```
   *
   * @param js JSON object to parse.
   *
   * @return The parsed [BlastCLI] subtype.
   *
   * @throws IllegalArgumentException If the input JSON object is missing the
   * `"tool"` property, or if the value of that property is not a valid BLAST+
   * tool name.
   */
  fun of(js: ObjectNode): BlastCLI {
    return js.optString("tool") {
      when (parseTool(it)) {
        BlastTool.BlastN         -> BlastNImpl(js)
        BlastTool.BlastP         -> BlastPImpl(js)
        BlastTool.BlastX         -> BlastXImpl(js)
        BlastTool.DeltaBlast     -> DeltaBlastImpl(js)
        BlastTool.PSIBlast       -> PSIBlastImpl(js)
        BlastTool.RPSBlast       -> RPSBlastImpl(js)
        BlastTool.RPSTBlastN     -> RPSTBlastNImpl(js)
        BlastTool.TBlastN        -> TBlastNImpl(js)
        BlastTool.TBlastX        -> TBlastXImpl(js)
        BlastTool.BlastFormatter -> BlastFormatterImpl(js)
      }
    }
      ?: throw IllegalArgumentException("Missing required property \"tool\".")
  }

  /**
   * Creates a new [BlastN] instance by parsing the given JSON input.
   *
   * @param js JSON object to parse.
   *
   * @return The new [BlastN] instance.
   */
  fun blastn(js: ObjectNode): BlastN = BlastNImpl(js)

  /**
   * Creates a new, defaulted [BlastN] instance.
   *
   * @return The new [BlastN] instance.
   */
  fun blastn(): BlastN = BlastNImpl()

  /**
   * Creates a new [BlastP] instance by parsing the given JSON input.
   *
   * @param js JSON object to parse.
   *
   * @return The new [BlastP] instance.
   */
  fun blastp(js: ObjectNode): BlastP = BlastPImpl(js)

  /**
   * Creates a new, defaulted [BlastP] instance.
   *
   * @return The new [BlastP] instance.
   */
  fun blastp(): BlastP = BlastPImpl()

  /**
   * Creates a new [BlastX] instance by parsing the given JSON input.
   *
   * @param js JSON object to parse.
   *
   * @return The new [BlastX] instance.
   */
  fun blastx(js: ObjectNode): BlastX = BlastXImpl(js)

  /**
   * Creates a new, defaulted [BlastX] instance.
   *
   * @return The new [BlastX] instance.
   */
  fun blastx(): BlastX = BlastXImpl()

  /**
   * Creates a new [DeltaBlast] instance by parsing the given JSON input.
   *
   * @param js JSON object to parse.
   *
   * @return The new [DeltaBlast] instance.
   */
  fun deltablast(js: ObjectNode): DeltaBlast = DeltaBlastImpl(js)

  /**
   * Creates a new, defaulted [DeltaBlast] instance.
   *
   * @return The new [DeltaBlast] instance.
   */
  fun deltablast(): DeltaBlast = DeltaBlastImpl()

  /**
   * Creates a new [PSIBlast] instance by parsing the given JSON input.
   *
   * @param js JSON object to parse.
   *
   * @return The new [PSIBlast] instance.
   */
  fun psiblast(js: ObjectNode): PSIBlast = PSIBlastImpl(js)

  /**
   * Creates a new, defaulted [PSIBlast] instance.
   *
   * @return The new [PSIBlast] instance.
   */
  fun psiblast(): PSIBlast = PSIBlastImpl()

  /**
   * Creates a new [RPSBlast] instance by parsing the given JSON input.
   *
   * @param js JSON object to parse.
   *
   * @return The new [RPSBlast] instance.
   */
  fun rpsblast(js: ObjectNode): RPSBlast = RPSBlastImpl(js)

  /**
   * Creates a new, defaulted [RPSBlast] instance.
   *
   * @return The new [RPSBlast] instance.
   */
  fun rpsblast(): RPSBlast = RPSBlastImpl()

  /**
   * Creates a new [RPSTBlastN] instance by parsing the given JSON input.
   *
   * @param js JSON object to parse.
   *
   * @return The new [RPSTBlastN] instance.
   */
  fun rpstblastn(js: ObjectNode): RPSTBlastN = RPSTBlastNImpl(js)

  /**
   * Creates a new, defaulted [RPSTBlastN] instance.
   *
   * @return The new [RPSTBlastN] instance.
   */
  fun rpstblastn(): RPSTBlastN = RPSTBlastNImpl()

  /**
   * Creates a new [TBlastN] instance by parsing the given JSON input.
   *
   * @param js JSON object to parse.
   *
   * @return The new [TBlastN] instance.
   */
  fun tblastn(js: ObjectNode): TBlastN = TBlastNImpl(js)

  /**
   * Creates a new, defaulted [TBlastN] instance.
   *
   * @return The new [TBlastN] instance.
   */
  fun tblastn(): TBlastN = TBlastNImpl()

  /**
   * Creates a new [TBlastX] instance by parsing the given JSON input.
   *
   * @param js JSON object to parse.
   *
   * @return The new [TBlastX] instance.
   */
  fun tblastx(js: ObjectNode): TBlastX = TBlastXImpl(js)

  /**
   * Creates a new, defaulted [TBlastX] instance.
   *
   * @return The new [TBlastX] instance.
   */
  fun tblastx(): TBlastX = TBlastXImpl()

  /**
   * Creates a new [BlastFormatter] instance by parsing the given JSON input.
   *
   * @param js JSON object to parse.
   *
   * @return The new [BlastFormatter] instance.
   */
  fun blastFormatter(js: ObjectNode): BlastFormatter = BlastFormatterImpl(js)

  /**
   * Creates a new, defaulted [BlastFormatter] instance.
   *
   * @return The new [BlastFormatter] instance.
   */
  fun blastFormatter(): BlastFormatter = BlastFormatterImpl()
}