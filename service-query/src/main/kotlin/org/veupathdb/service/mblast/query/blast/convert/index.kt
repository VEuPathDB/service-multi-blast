package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.blastn.BlastN
import org.veupathdb.lib.blast.blastp.BlastP
import org.veupathdb.lib.blast.blastx.BlastX
import org.veupathdb.lib.blast.common.BlastCLI
import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.blast.deltablast.DeltaBlast
import org.veupathdb.lib.blast.psiblast.PSIBlast
import org.veupathdb.lib.blast.rpsblast.RPSBlast
import org.veupathdb.lib.blast.rpstblastn.RPSTBlastN
import org.veupathdb.lib.blast.tblastn.TBlastN
import org.veupathdb.lib.blast.tblastx.TBlastX
import org.veupathdb.service.mblast.query.generated.model.*

/**
 * Converts the source external BLAST configuration type into a form suitable
 * for use internally in the service.
 *
 * @receiver Config to convert.
 *
 * @return The converted internal configuration form.
 */
fun BlastQueryConfig.toInternal(): BlastQueryBase = when (this) {
  is BlastNConfig     -> toInternal()
  is BlastPConfig     -> toInternal()
  is BlastXConfig     -> toInternal()
  is DeltaBlastConfig -> toInternal()
  is PSIBlastConfig   -> toInternal()
  is RPSBlastConfig   -> toInternal()
  is RPSTBlastNConfig -> toInternal()
  is TBlastNConfig    -> toInternal()
  is TBlastXConfig    -> toInternal()
  else                -> throw IllegalStateException("Unknown query config type.")
}

/**
 * Converts the source internal BLAST configuration type into a form suitable
 * for returning to outside clients.
 *
 * @receiver Config to convert.
 *
 * @return The converted external configuration form.
 */
fun BlastCLI.toExternal(): BlastQueryConfig = when (this) {
  is BlastN     -> toExternal()
  is BlastP     -> toExternal()
  is BlastX     -> toExternal()
  is DeltaBlast -> toExternal()
  is PSIBlast   -> toExternal()
  is RPSBlast   -> toExternal()
  is RPSTBlastN -> toExternal()
  is TBlastN    -> toExternal()
  is TBlastX    -> toExternal()
  else          -> throw IllegalStateException("Unknown query config type.")
}

fun BlastQueryTool.toInternal() = when (this) {
  BlastQueryTool.BLASTN     -> BlastTool.BlastN
  BlastQueryTool.BLASTP     -> BlastTool.BlastP
  BlastQueryTool.BLASTX     -> BlastTool.BlastX
  BlastQueryTool.DELTABLAST -> BlastTool.DeltaBlast
  BlastQueryTool.PSIBLAST   -> BlastTool.PSIBlast
  BlastQueryTool.RPSBLAST   -> BlastTool.RPSBlast
  BlastQueryTool.RPSTBLASTN -> BlastTool.RPSTBlastN
  BlastQueryTool.TBLASTN    -> BlastTool.TBlastN
  BlastQueryTool.TBLASTX    -> BlastTool.TBlastX
}

fun BlastTool.toExternal() = when (this) {
  BlastTool.BlastN         -> BlastQueryTool.BLASTN
  BlastTool.BlastP         -> BlastQueryTool.BLASTP
  BlastTool.BlastX         -> BlastQueryTool.BLASTX
  BlastTool.DeltaBlast     -> BlastQueryTool.DELTABLAST
  BlastTool.PSIBlast       -> BlastQueryTool.PSIBLAST
  BlastTool.RPSBlast       -> BlastQueryTool.RPSBLAST
  BlastTool.RPSTBlastN     -> BlastQueryTool.RPSTBLASTN
  BlastTool.TBlastN        -> BlastQueryTool.TBLASTN
  BlastTool.TBlastX        -> BlastQueryTool.TBLASTX
  BlastTool.BlastFormatter -> throw IllegalStateException()
}