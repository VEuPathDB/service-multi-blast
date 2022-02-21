@file:JvmName("BlastConv")
@file:Suppress("NOTHING_TO_INLINE")

package mb.lib.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import mb.api.model.IOJobTarget
import mb.api.model.blast.*
import mb.api.model.blast.impl.*
import mb.api.model.io.JsonKeys
import mb.lib.blast.*
import mb.lib.blast.model.*
import mb.lib.blast.model.CompositionBasedStats.Companion
import mb.lib.model.EmptyBlastConfig
import mb.lib.query.model.JobTarget
import org.veupathdb.lib.blast.*
import org.veupathdb.lib.blast.field.*
import org.veupathdb.lib.blast.util.JSONObjectDecoder

fun convertReportConfig(json: String) = BlastFormatter(JSONObjectDecoder(json.parseJSON()))

fun convertJobConfig(json: String): BlastConfig = convertJobConfig(json.parseJSON<JsonNode>())

fun convertJobConfig(json: JsonNode): BlastConfig = when {
  json.isArray             -> convertLegacy(json as ArrayNode)
  !json.isObject           -> throw RuntimeException("Invalid record JSON configuration.")
  !json.has(JsonKeys.Tool) -> throw RuntimeException("Invalid record JSON configuration.  Tool field is missing.")
  else                     -> when (BlastTool.fromString(json.get(JsonKeys.Tool).textValue())) {
    BlastTool.BlastN  -> convertNJSON(json as ObjectNode)
    BlastTool.BlastP  -> convertPJSON(json as ObjectNode)
    BlastTool.BlastX  -> convertXJSON(json as ObjectNode)
    BlastTool.TBlastN -> convertTNJSON(json as ObjectNode)
    BlastTool.TBlastX -> convertTXJSON(json as ObjectNode)
    else              -> throw RuntimeException("Unsupported blast tool.")
  }
}

fun convert(tgt: JobTarget): IOJobTarget = IOJobTarget(tgt.organism, tgt.target)

@Suppress("UNCHECKED_CAST")
fun convert(tgts: Collection<IOJobTarget>): Array<JobTarget> {
  val out = arrayOfNulls<JobTarget>(tgts.size)
  var ind = 0

  for (tgt in tgts)
    out[ind++] = convert(tgt)

  return out as Array<JobTarget>
}

fun convert(tgt: IOJobTarget) = JobTarget(tgt.organism, tgt.target)

fun convert(value: BlastConfig): IOBlastConfig =
  if (value is EmptyBlastConfig) value.jsonCast() else when (value.tool) {
    BlastTool.BlastN  -> convert(value as BlastN)
    BlastTool.BlastP  -> convert(value as BlastP)
    BlastTool.BlastX  -> (value as BlastX).toExternal() //TODO update this one to be like the others (convert)
    BlastTool.TBlastN -> convert(value as TBlastN)
    BlastTool.TBlastX -> convert(value as TBlastX)
    else              -> throw IllegalArgumentException()
  }

fun convertLegacy(json: ArrayNode): BlastConfig = when (BlastTool.fromString(json[0][0].textValue())) {
  BlastTool.BlastN  -> XBlastN().apply { fromLegacyJSON(json) }
  BlastTool.BlastP  -> XBlastP().apply { fromLegacyJSON(json) }
  BlastTool.BlastX  -> XBlastX().apply { fromLegacyJSON(json) }
  BlastTool.TBlastN -> XTBlastN().apply { fromLegacyJSON(json) }
  BlastTool.TBlastX -> XTBlastX().apply { fromLegacyJSON(json) }
  else              -> throw IllegalArgumentException()
}

fun convertNJSON(json: ObjectNode): BlastConfig = BlastN(JSONObjectDecoder(json))

fun convertPJSON(json: ObjectNode): BlastConfig = BlastP(JSONObjectDecoder(json))

fun convertXJSON(json: ObjectNode): BlastConfig = BlastX(JSONObjectDecoder(json))

fun convertTNJSON(json: ObjectNode): BlastConfig = TBlastN(JSONObjectDecoder(json))

fun convertTXJSON(json: ObjectNode): BlastConfig = TBlastX(JSONObjectDecoder(json))

fun IOBlastConfig.toInternal(): BlastQueryConfig = when (tool) {
  BlastTool.BlastN  -> convert(this as IOBlastnConfig)
  BlastTool.BlastP  -> convert(this as IOBlastpConfig)
  BlastTool.BlastX  -> convert(this as IOBlastxConfig)
  BlastTool.TBlastN -> convert(this as IOTBlastnConfig)
  BlastTool.TBlastX -> convert(this as IOTBlastxConfig)
  else              -> throw IllegalArgumentException()
}

/**
 * Converts an external blast config form to the internal type.
 *
 * @param bn External blast config to convert.
 *
 * @return Converted internal blast config.
 */
fun convert(bn: IOBlastnConfig): BlastN = XBlastN().apply {
  queryFile = bn.query?.let(::QueryFile)
  expectValue = bn.eValue?.let(::ExpectValue)
  numDescriptions = bn.numDescriptions?.let(::NumDescriptions)
  numAlignments = bn.numAlignments?.let(::NumAlignments)
  lineLength = bn.lineLength?.let(::LineLength)
  maxTargetSequences = bn.maxTargetSeqs?.let(::MaxTargetSeqs)

  queryLocation                = bn.queryLoc
  outFormat                    = convert(bn.outFormat)
  sortHits                     = convert(bn.sortHits)
  sortHSPs                     = convert(bn.sortHSPs)
  lowercaseMasking             = bn.lcaseMasking
  queryCoverageHSPPercent      = bn.qCovHSPPerc
  maxHSPs                      = bn.maxHSPs
  dbSize                       = bn.dbSize
  searchSpace                  = bn.searchSpace
  extensionDropoffUngapped     = bn.xDropUngap
  parseDefLines                = bn.parseDefLines
  strand                       = bn.strand
  task                         = bn.task
  wordSize                     = bn.wordSize
  gapOpen                      = bn.gapOpen
  gapExtend                    = bn.gapExtend
  penalty                      = bn.penalty
  reward                       = bn.reward
  useIndex                     = bn.useIndex
  indexName                    = bn.indexName
  dust                         = bn.dust
  windowMaskerTaxID            = bn.windowMaskerTaxID
  softMasking                  = bn.softMasking
  taxIDs                       = convertTaxIDsToInternal(bn.taxIds)
  negativeTaxIDs               = convertTaxIDsToInternal(bn.negativeTaxIds)
  dbSoftMask                   = bn.dbSoftMask
  dbHardMask                   = bn.dbHardMask
  percentIdentity              = bn.percIdentity
  cullingLimit                 = bn.cullingLimit
  bestHitOverhang              = bn.bestHitOverhang
  bestHitScoreEdge             = bn.bestHitScoreEdge
  subjectBestHit               = bn.subjectBestHit
  templateType                 = bn.templateType
  templateLength               = bn.templateLength
  sumStats                     = bn.sumStats
  extensionDropoffPrelimGapped = bn.xDropGap
  extensionDropoffFinalGapped  = bn.xDropGapFinal
  nonGreedy                    = bn.noGreedy
  minRawGappedScore            = bn.minRawGappedScore
  ungappedAlignmentsOnly       = bn.ungapped
  windowSize                   = bn.windowSize
  offDiagonalRange             = bn.offDiagonalRange
}

fun convert(bn: BlastN): IOBlastnConfig = IOBlastnConfigImpl().apply {
  convert(this)

  strand            = bn.strand
  task              = bn.task
  wordSize          = bn.wordSize
  gapOpen           = bn.gapOpen
  gapExtend         = bn.gapExtend
  penalty           = bn.penalty
  reward            = bn.reward
  useIndex          = bn.useIndex
  indexName         = bn.indexName
  dust              = bn.dust
  windowMaskerTaxID = bn.windowMaskerTaxID
  softMasking       = bn.softMasking
  dbSoftMask        = bn.dbSoftMask
  dbHardMask        = bn.dbHardMask
  percIdentity      = bn.percentIdentity
  cullingLimit      = bn.cullingLimit
  bestHitOverhang   = bn.bestHitOverhang
  bestHitScoreEdge  = bn.bestHitScoreEdge
  subjectBestHit    = bn.subjectBestHit
  templateType      = bn.templateType
  templateLength    = bn.templateLength
  sumStats          = bn.sumStats
  xDropGap          = bn.extensionDropoffPrelimGapped
  xDropGapFinal     = bn.extensionDropoffFinalGapped
  noGreedy          = bn.nonGreedy
  minRawGappedScore = bn.minRawGappedScore
  ungapped          = bn.ungappedAlignmentsOnly
  windowSize        = bn.windowSize
  offDiagonalRange  = bn.offDiagonalRange
}

// ---------------------------------------------------------------------- //

fun convert(conf: IOBlastpConfig): BlastP = XBlastP().apply {
  queryFile = conf.query?.let(::QueryFile)
  expectValue = conf.eValue?.let(::ExpectValue)
  numDescriptions = conf.numDescriptions?.let(::NumDescriptions)
  numAlignments = conf.numAlignments?.let(::NumAlignments)
  lineLength = conf.lineLength?.let(::LineLength)
  maxTargetSequences = conf.maxTargetSeqs?.let(::MaxTargetSeqs)

  queryLocation                = conf.queryLoc
  outFormat                    = convert(conf.outFormat)
  sortHits                     = convert(conf.sortHits)
  sortHSPs                     = convert(conf.sortHSPs)
  lowercaseMasking             = conf.lcaseMasking
  queryCoverageHSPPercent      = conf.qCovHSPPerc
  maxHSPs                      = conf.maxHSPs
  dbSize                       = conf.dbSize
  searchSpace                  = conf.searchSpace
  extensionDropoffUngapped     = conf.xDropUngap
  parseDefLines                = conf.parseDefLines
  task                         = conf.task
  wordSize                     = conf.wordSize?.toLong()
  gapOpen                      = conf.gapOpen
  gapExtend                    = conf.gapExtend
  matrix                       = conf.matrix
  threshold                    = conf.threshold
  compBasedStats               = conf.compBasedStats?.toInternalLong()
  seg                          = conf.seg
  softMasking                  = conf.softMasking
  taxIDs                       = convertTaxIDsToInternal(conf.taxIds)
  negativeTaxIDs               = convertTaxIDsToInternal(conf.negativeTaxIds)
  dbSoftMask                   = conf.dbSoftMask
  dbHardMask                   = conf.dbHardMask
  cullingLimit                 = conf.cullingLimit?.toLong()
  bestHitOverhang              = conf.bestHitOverhang
  bestHitScoreEdge             = conf.bestHitScoreEdge
  subjectBestHit               = conf.subjectBestHit
  extensionDropoffPrelimGapped = conf.xDropGap
  extensionDropoffFinalGapped  = conf.xDropGapFinal
  windowSize                   = conf.windowSize?.toLong()
  ungappedAlignmentsOnly       = conf.ungapped
  useSmithWatermanTraceback    = conf.useSWTraceback
}

fun convert(bp: BlastP): IOBlastpConfig = IOBlastpConfigImpl().apply {
  setEValue(bp.expectValue)
  convert(this)

  task             = bp.task
  wordSize         = bp.wordSize?.toInt()
  gapOpen          = bp.gapOpen
  gapExtend        = bp.gapExtend
  matrix           = bp.matrix
  threshold        = bp.threshold
  compBasedStats   = bp.compBasedStats?.let(CompositionBasedStats::fromValue)
  seg              = bp.seg
  softMasking      = bp.softMasking
  dbSoftMask       = bp.dbSoftMask
  dbHardMask       = bp.dbHardMask
  cullingLimit     = bp.cullingLimit?.toInt()
  bestHitOverhang  = bp.bestHitOverhang
  bestHitScoreEdge = bp.bestHitScoreEdge
  subjectBestHit   = bp.subjectBestHit
  xDropGap         = bp.extensionDropoffPrelimGapped
  xDropGapFinal    = bp.extensionDropoffFinalGapped
  ungapped         = bp.ungappedAlignmentsOnly
  useSWTraceback   = bp.useSmithWatermanTraceback
}

// ---------------------------------------------------------------------- //

fun convert(conf: IOBlastxConfig): BlastX = XBlastX().apply {
  queryFile = conf.query?.let(::QueryFile)
  expectValue = conf.eValue?.let(::ExpectValue)
  numDescriptions = conf.numDescriptions?.let(::NumDescriptions)
  numAlignments = conf.numAlignments?.let(::NumAlignments)
  lineLength = conf.lineLength?.let(::LineLength)
  maxTargetSequences = conf.maxTargetSeqs?.let(::MaxTargetSeqs)

  queryLocation                = conf.queryLoc
  outFormat                    = convert(conf.outFormat)
  sortHits                     = convert(conf.sortHits)
  sortHSPs                     = convert(conf.sortHSPs)
  lowercaseMasking             = conf.lcaseMasking
  queryCoverageHSPPercent      = conf.qCovHSPPerc
  maxHSPs                      = conf.maxHSPs
  dbSize                       = conf.dbSize
  searchSpace                  = conf.searchSpace
  extensionDropoffUngapped     = conf.xDropUngap
  parseDefLines                = conf.parseDefLines
  strand                       = conf.strand
  queryGenCode                 = conf.queryGeneticCode
  task                         = conf.task
  wordSize                     = conf.wordSize?.toLong()
  gapOpen                      = conf.gapOpen
  gapExtend                    = conf.gapExtend
  maxIntronLength              = conf.maxIntronLength?.toLong()
  matrix                       = conf.matrix
  threshold                    = conf.threshold
  compBasedStats               = conf.compBasedStats?.toInternalLong()
  seg                          = conf.seg
  softMasking                  = conf.softMasking
  taxIDs                       = convertTaxIDsToInternal(conf.taxIds)
  negativeTaxIDs               = convertTaxIDsToInternal(conf.negativeTaxIds)
  dbSoftMask                   = conf.dbSoftMask
  dbHardMask                   = conf.dbHardMask
  cullingLimit                 = conf.cullingLimit?.toLong()
  bestHitOverhang              = conf.bestHitOverhang
  bestHitScoreEdge             = conf.bestHitScoreEdge
  subjectBestHit               = conf.subjectBestHit
  sumStats                     = conf.sumStats
  extensionDropoffPrelimGapped = conf.xDropGap
  extensionDropoffFinalGapped  = conf.xDropGapFinal
  windowSize                   = conf.windowSize?.toLong()
  ungappedAlignmentsOnly       = conf.ungapped
  useSmithWatermanTraceback    = conf.useSWTraceback
}

fun BlastX.toExternal(): IOBlastxConfig = IOBlastxConfigImpl().also {
  convert(it)
  it.strand = strand
  it.queryGeneticCode = queryGenCode
  it.task = task
  it.wordSize = wordSize?.toInt()
  it.gapOpen = gapOpen
  it.gapExtend = gapExtend
  it.maxIntronLength = maxIntronLength?.toInt()
  it.matrix = matrix
  it.threshold = threshold
  it.compBasedStats = compBasedStats?.let(Companion::fromValue)
  it.seg = seg
  it.softMasking = softMasking
  it.dbSoftMask = dbSoftMask
  it.dbHardMask = dbHardMask
  it.cullingLimit = cullingLimit?.toInt()
  it.bestHitOverhang = bestHitOverhang
  it.bestHitScoreEdge = bestHitScoreEdge
  it.subjectBestHit = subjectBestHit
  it.sumStats = sumStats
  it.xDropGap = extensionDropoffPrelimGapped
  it.xDropGapFinal = extensionDropoffFinalGapped
  it.windowSize = windowSize?.toInt()
  it.ungapped = ungappedAlignmentsOnly
  it.useSWTraceback = useSmithWatermanTraceback
}

// ---------------------------------------------------------------------- //

fun convert(conf: IOTBlastnConfig): TBlastN = XTBlastN().apply {
  queryFile = conf.query?.let(::QueryFile)
  expectValue = conf.eValue?.let(::ExpectValue)
  numDescriptions = conf.numDescriptions?.let(::NumDescriptions)
  numAlignments = conf.numAlignments?.let(::NumAlignments)
  lineLength = conf.lineLength?.let(::LineLength)
  maxTargetSequences = conf.maxTargetSeqs?.let(::MaxTargetSeqs)

  queryLocation                = conf.queryLoc
  outFormat                    = convert(conf.outFormat)
  sortHits                     = convert(conf.sortHits)
  sortHSPs                     = convert(conf.sortHSPs)
  lowercaseMasking             = conf.lcaseMasking
  queryCoverageHSPPercent      = conf.qCovHSPPerc
  maxHSPs                      = conf.maxHSPs
  dbSize                       = conf.dbSize
  searchSpace                  = conf.searchSpace
  extensionDropoffUngapped     = conf.xDropUngap
  parseDefLines                = conf.parseDefLines
  task                         = conf.task
  wordSize                     = conf.wordSize?.toLong()
  gapOpen                      = conf.gapOpen
  gapExtend                    = conf.gapExtend
  dbGenCode                    = conf.dbGencode?.toShort()
  maxIntronLength              = conf.maxIntronLength?.toLong()
  matrix                       = conf.matrix
  threshold                    = conf.threshold
  compBasedStats               = conf.compBasedStats?.toInternalLong()
  seg                          = conf.seg
  softMasking                  = conf.softMasking
  taxIDs                       = convertTaxIDsToInternal(conf.taxIds)
  negativeTaxIDs               = convertTaxIDsToInternal(conf.negativeTaxIds)
  dbSoftMask                   = conf.dbSoftMask
  dbHardMask                   = conf.dbHardMask
  cullingLimit                 = conf.cullingLimit?.toLong()
  bestHitOverhang              = conf.bestHitOverhang
  bestHitScoreEdge             = conf.bestHitScoreEdge
  subjectBestHit               = conf.subjectBestHit
  sumStats                     = conf.sumStats
  extensionDropoffPrelimGapped = conf.xDropGap
  extensionDropoffFinalGapped  = conf.xDropGapFinal
  ungappedAlignmentsOnly       = conf.ungapped
  windowSize                   = conf.windowSize?.toLong()
  useSmithWatermanTraceback    = conf.useSWTraceback
}

fun convert(b: TBlastN): IOTBlastnConfig = IOTBlastnConfigImpl().apply {
  b.convert(this)

  task             = b.task
  wordSize         = b.wordSize?.toInt()
  gapOpen          = b.gapOpen
  gapExtend        = b.gapExtend
  dbGencode        = b.dbGenCode?.toByte()
  maxIntronLength  = b.maxIntronLength?.toInt()
  matrix           = b.matrix
  threshold        = b.threshold
  compBasedStats   = b.compBasedStats?.let(CompositionBasedStats::fromValue)
  seg              = b.seg
  softMasking      = b.softMasking
  dbSoftMask       = b.dbSoftMask
  dbHardMask       = b.dbHardMask
  cullingLimit     = b.cullingLimit?.toInt()
  bestHitOverhang  = b.bestHitOverhang
  bestHitScoreEdge = b.bestHitScoreEdge
  subjectBestHit   = b.subjectBestHit
  sumStats         = b.sumStats
  xDropGap         = b.extensionDropoffPrelimGapped
  xDropGapFinal    = b.extensionDropoffFinalGapped
  ungapped         = b.ungappedAlignmentsOnly
  windowSize       = b.windowSize?.toInt()
  useSWTraceback   = b.useSmithWatermanTraceback
}

// ---------------------------------------------------------------------- //

fun convert(conf: IOTBlastxConfig): TBlastX = XTBlastX().apply {
  queryFile = conf.query?.let(::QueryFile)
  expectValue = conf.eValue?.let(::ExpectValue)
  numDescriptions = conf.numDescriptions?.let(::NumDescriptions)
  numAlignments = conf.numAlignments?.let(::NumAlignments)
  lineLength = conf.lineLength?.let(::LineLength)
  maxTargetSequences = conf.maxTargetSeqs?.let(::MaxTargetSeqs)

  queryLocation            = conf.queryLoc
  outFormat                = convert(conf.outFormat)
  sortHits                 = convert(conf.sortHits)
  sortHSPs                 = convert(conf.sortHSPs)
  lowercaseMasking         = conf.lcaseMasking
  queryCoverageHSPPercent  = conf.qCovHSPPerc
  maxHSPs                  = conf.maxHSPs
  dbSize                   = conf.dbSize
  searchSpace              = conf.searchSpace
  extensionDropoffUngapped = conf.xDropUngap
  parseDefLines            = conf.parseDefLines
  strand                   = conf.strand
  queryGenCode             = conf.queryGeneticCode?.toShort()
  wordSize                 = conf.wordSize?.toLong()
  maxIntronLength          = conf.maxIntronLength?.toLong()
  matrix                   = conf.matrix
  threshold                = conf.threshold
  dbGenCode                = conf.dbGencode?.toShort()
  seg                      = conf.seg
  softMasking              = conf.softMasking
  taxIDs                   = convertTaxIDsToInternal(conf.taxIds)
  negativeTaxIDs           = convertTaxIDsToInternal(conf.negativeTaxIds)
  dbSoftMask               = conf.dbSoftMask
  dbHardMask               = conf.dbHardMask
  cullingLimit             = conf.cullingLimit?.toLong()
  bestHitOverhang          = conf.bestHitOverhang
  bestHitScoreEdge         = conf.bestHitScoreEdge
  subjectBestHit           = conf.subjectBestHit
  sumStats                 = conf.sumStats
  windowSize               = conf.windowSize?.toLong()
}

fun convert(b: TBlastX): IOTBlastxConfig = IOTBlastxConfigImpl().apply {
  b.convert(this)
  queryLoc         = b.queryLocation
  eValue           = b.expectValue?.value
  lcaseMasking     = b.lowercaseMasking
  qCovHSPPerc      = b.queryCoverageHSPPercent
  maxHSPs          = b.maxHSPs
  dbSize           = b.dbSize
  searchSpace      = b.searchSpace
  xDropUngap       = b.extensionDropoffUngapped
  strand           = b.strand
  queryGeneticCode = b.queryGenCode?.toByte()
  wordSize         = b.wordSize?.toInt()
  maxIntronLength  = b.maxIntronLength?.toInt()
  matrix           = b.matrix
  threshold        = b.threshold
  dbGencode        = b.dbGenCode?.toByte()
  seg              = b.seg
  softMasking      = b.softMasking
  dbSoftMask       = b.dbSoftMask
  dbHardMask       = b.dbHardMask
  cullingLimit     = b.cullingLimit?.toInt()
  bestHitOverhang  = b.bestHitOverhang
  bestHitScoreEdge = b.bestHitScoreEdge
  subjectBestHit   = b.subjectBestHit
  sumStats         = b.sumStats
  windowSize       = b.windowSize?.toInt()
}



private inline fun BlastWithLists.convert(o: IOBlastWithLists) {
  o.taxIds         = convertTaxIDsToExternal(taxIDs)
  o.negativeTaxIds = convertTaxIDsToExternal(negativeTaxIDs)

  (this as BlastQueryConfig).convert(o)
}

private inline fun BlastQueryConfig.convert(o: IOBlastConfig) {
  o.dbSize       = dbSize
  o.eValue       = expectValue?.value
  o.lcaseMasking = lowercaseMasking
  o.maxHSPs      = maxHSPs
  o.qCovHSPPerc  = queryCoverageHSPPercent
  o.queryLoc     = queryLocation
  o.searchSpace  = searchSpace
  o.xDropUngap   = extensionDropoffUngapped

  (this as BlastConfig).convert(o)
}

private inline fun BlastConfig.convert(o: IOBlastConfig) {
  o.lineLength      = lineLength?.value
  o.maxTargetSeqs   = maxTargetSequences?.value
  o.numAlignments   = numAlignments?.value
  o.numDescriptions = numDescriptions?.value
  o.outFormat       = outFormat?.toExternal()
  o.parseDefLines   = parseDefLines
  o.sortHits        = sortHits?.toExternal()
  o.sortHSPs        = sortHSPs?.toExternal()
}

// ---------------------------------------------------------------------- //

fun convertCBS(value: String?): CompositionBasedStats? = value?.let(CompositionBasedStats::fromValue)

fun convertCBS(value: CompositionBasedStats?) = value?.publicValue

fun OutFormat.toExternal() = IOBlastReportFormat.fromInternalValue(this)

fun convert(value: IOBlastReportFormat?) = value?.toInternalValue

fun HitSorting.toExternal() = IOHitSorting[this]

fun convert(value: IOHitSorting?) = value?.internalValue

fun HSPSorting.toExternal() = IOHSPSorting[this]

fun convert(value: IOHSPSorting?) = value?.internalValue

private fun convertTaxIDsToExternal(value: List<String>?) = value?.map(String::toInt) ?: emptyList()

private fun convertTaxIDsToInternal(value: List<Int>?) = value?.map(Int::toString) ?: emptyList()
