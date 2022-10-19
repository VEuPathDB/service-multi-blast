package org.veupathdb.service.mblast.query.blast.validate


fun ErrorMap.validateWordSize(min: Int, actual: Int?) {
  if ((actual ?: return) < min)
    blastField("wordSize", "must be >= $min")
}

fun ErrorMap.validatePercentIdentity(value: Double?) {
  if ((value ?: return) < 0.0 || value > 100.0)
    blastField("percentIdentity", "must be >= 0 and <= 100")
}

fun ErrorMap.validateCullingLimit(value: Int?) {
  if ((value ?: return) < 0)
    blastField("cullingLimit", "must be >= 0")
}

fun ErrorMap.validateThreshold(value: Double?) {
  if ((value ?: return) < 0)
    blastField("threshold", "must be >= 0")
}

fun ErrorMap.validateDBSoftHardMask(softMask: String?, hardMask: String?) {
  if (softMask != null && hardMask != null) {
    blastField("dbSoftMask", "incompatible with dbHardMask")
    blastField("dbHardMask", "incompatible with dbSoftMask")
  }
}

fun ErrorMap.validateQueryGenCode(value: Byte?) {
  when (value ?: return) {
    in 1..6     -> return
    in 9..16    -> return
    in 21..31   -> return
    33.toByte() -> return
    else        -> blastField("queryGenCode", "must be between 1-6, 9-16, 21-31, or equal 33")
  }
}

fun ErrorMap.validateMaxIntronLength(value: Int?) {
  if ((value ?: return) < 0)
    blastField("maxIntronLength", "must be >= 0")
}

fun ErrorMap.validateDBGenCode(value: Byte?) {
  when (value ?: return) {
    in 1..6     -> return
    in 9..16    -> return
    in 21..31   -> return
    33.toByte() -> return
    else        -> blastField("dbGenCode", "must be between 1-6, 9-16, 21-31, or equal 33")
  }
}
