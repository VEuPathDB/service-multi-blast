package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.common.fields.QueryLocation
import org.veupathdb.lib.blast.common.fields.Strand
import org.veupathdb.lib.blast.common.fields.StrandType
import org.veupathdb.service.mblast.query.generated.model.BlastLocation
import org.veupathdb.service.mblast.query.generated.model.BlastLocationImpl
import org.veupathdb.service.mblast.query.generated.model.BlastStrand

//
// Strand
//

fun BlastStrand.toInternal() = when(this) {
  BlastStrand.BOTH  -> Strand(StrandType.Both)
  BlastStrand.MINUS -> Strand(StrandType.Minus)
  BlastStrand.PLUS  -> Strand(StrandType.Plus)
}

fun Strand.toExternal(): BlastStrand =
  when(this.value) {
    StrandType.Both  -> BlastStrand.BOTH
    StrandType.Minus -> BlastStrand.MINUS
    StrandType.Plus  -> BlastStrand.PLUS
  }

//
// Location
//

fun BlastLocation.toInternal(): QueryLocation =
  QueryLocation(start.toUInt(), stop.toUInt())

fun QueryLocation.toExternal(): BlastLocation =
  BlastLocationImpl().also {
    it.start = start.toInt()
    it.stop  = stop.toInt()
  }
