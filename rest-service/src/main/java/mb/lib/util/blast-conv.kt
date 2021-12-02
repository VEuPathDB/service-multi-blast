package mb.lib.util


import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import mb.api.model.IOJobTarget
import mb.api.model.blast.*
import mb.api.model.blast.impl.*
import mb.api.model.io.JsonKeys
import mb.lib.blast.*
import mb.lib.blast.model.CompositionBasedStats
import mb.lib.blast.model.IOHSPSorting
import mb.lib.blast.model.IOHitSorting
import mb.lib.model.EmptyBlastConfig
import mb.lib.query.model.JobTarget
import org.veupathdb.lib.blast.*
import org.veupathdb.lib.blast.field.HSPSorting
import org.veupathdb.lib.blast.field.HitSorting
import org.veupathdb.lib.blast.field.OutFormat
import org.veupathdb.lib.blast.util.JSONObjectDecoder

fun convertReportConfig(json: String): BlastFormatter = json.parseJSON()

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

fun convert(tgts: Collection<IOJobTarget>): Array<JobTarget> = tgts.stream().map(::convert).toArray(::arrayOfNulls)

fun convert(tgt: IOJobTarget) = JobTarget(tgt.organism, tgt.target)

fun convert(value: BlastConfig): IOBlastConfig =
  if (value is EmptyBlastConfig) value.jsonCast() else when (value.tool) {
    BlastTool.BlastN  -> convert(value as BlastN)
    BlastTool.BlastP  -> convert(value as BlastP)
    BlastTool.BlastX  -> convert(value as BlastX)
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

fun convertNJSON(json: ObjectNode): BlastConfig = BlastN.fromJSON(JSONObjectDecoder(json))

fun convertPJSON(json: ObjectNode): BlastConfig = BlastP.fromJSON(JSONObjectDecoder(json))

fun convertXJSON(json: ObjectNode): BlastConfig = BlastX.fromJSON(JSONObjectDecoder(json))

fun convertTNJSON(json: ObjectNode): BlastConfig = TBlastN.fromJSON(JSONObjectDecoder(json))

fun convertTXJSON(json: ObjectNode): BlastConfig = TBlastX.fromJSON(JSONObjectDecoder(json))

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
  setQueryFile(bn.query)
  setExpectValue(bn.eValue)
  setNumDescriptions(bn.numDescriptions)
  setNumAlignments(bn.numAlignments)
  setLineLength(bn.lineLength)
  setMaxTargetSequences(bn.maxTargetSeqs)

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
  queryLoc          = bn.queryLocation
  eValue            = bn.expectValue.value()
  outFormat         = bn.outFormat.toExternal()
  numDescriptions   = bn.numDescriptions?.value
  numAlignments     = bn.numAlignments?.value
  lineLength        = bn.lineLength?.value
  sortHits          = bn.sortHits?.toExternal()
  sortHSPs          = bn.sortHSPs?.toExternal()
  lcaseMasking      = bn.lowercaseMasking
  qCovHSPPerc       = bn.queryCoverageHSPPercent
  maxHSPs           = bn.maxHSPs
  maxTargetSeqs     = bn.maxTargetSequences?.value
  dbSize            = bn.dbSize
  searchSpace       = bn.searchSpace
  xDropUngap        = bn.extensionDropoffUngapped
  parseDefLines     = bn.parseDefLines
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
  taxIds            = convertTaxIDsToExternal(bn.taxIDs)
  negativeTaxIds    = convertTaxIDsToExternal(bn.negativeTaxIDs)
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
  setQueryFile(conf.query)
  setExpectValue(conf.eValue)
  setNumDescriptions(conf.numDescriptions)
  setNumAlignments(conf.numAlignments)
  setLineLength(conf.lineLength)
  setMaxTargetSequences(conf.maxTargetSeqs)

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
  compBasedStats               = convertCBS(conf.compBasedStats)
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

  queryLoc         = bp.queryLocation
  outFormat        = bp.outFormat?.toExternal()
  numDescriptions  = bp.numDescriptions.value
  numAlignments    = bp.numAlignments.value
  lineLength       = bp.lineLength.value
  sortHits         = bp.sortHits?.toExternal()
  sortHSPs         = bp.sortHSPs?.toExternal()
  lcaseMasking     = bp.lowercaseMasking
  qCovHSPPerc      = bp.queryCoverageHSPPercent
  maxHSPs          = bp.maxHSPs
  maxTargetSeqs    = bp.maxTargetSequences.value
  dbSize           = bp.dbSize
  searchSpace      = bp.searchSpace
  xDropUngap       = bp.extensionDropoffUngapped
  parseDefLines    = bp.parseDefLines
  task             = bp.task
  wordSize         = bp.wordSize?.toInt()
  gapOpen          = bp.gapOpen
  gapExtend        = bp.gapExtend
  matrix           = bp.matrix
  threshold        = bp.threshold
  compBasedStats   = convertCBS(bp.compBasedStats)
  seg              = bp.seg
  softMasking      = bp.softMasking
  taxIds           = convertTaxIDsToExternal(bp.taxIDs)
  negativeTaxIds   = convertTaxIDsToExternal(bp.negativeTaxIDs)
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
  setQueryFile(conf.query)
  setExpectValue(conf.eValue)
  setNumDescriptions(conf.numDescriptions)
  setNumAlignments(conf.numAlignments)
  setLineLength(conf.lineLength)
  setMaxTargetSequences(conf.maxTargetSeqs)

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
  compBasedStats               = convertCBS(conf.compBasedStats)
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

fun BlastX.toExternal(): IOBlastxConfig = IOBlastxConfigImpl(
  queryLoc         = queryLocation,
  eValue           = expectValue?.value,
  outFormat        = outFormat?.toExternal(),
  numDescriptions  = numDescriptions?.value,
  numAlignments    = numAlignments?.value,
  lineLength       = lineLength?.value,
  sortHits         = sortHits?.toExternal(),
  sortHSPs         = sortHSPs?.toExternal(),
  lcaseMasking     = lowercaseMasking,
  qCovHSPPerc      = queryCoverageHSPPercent,
  maxHSPs          = maxHSPs,
  maxTargetSeqs    = maxTargetSequences?.value,
  dbSize           = dbSize,
  searchSpace      = searchSpace,
  xDropUngap       = extensionDropoffUngapped,
  parseDefLines    = parseDefLines,
  strand           = strand,
  queryGeneticCode = queryGenCode,
  task             = task,
  wordSize         = wordSize?.toInt(),
  gapOpen          = gapOpen,
  gapExtend        = gapExtend,
  maxIntronLength  = maxIntronLength.toInt(),
  matrix           = matrix,
  threshold        = threshold,
  compBasedStats   = convertCBS(compBasedStats),
  seg              = seg,
  softMasking      = softMasking,
  taxIds           = convertTaxIDsToExternal(taxIDs),
  negativeTaxIds   = convertTaxIDsToExternal(negativeTaxIDs),
  dbSoftMask       = dbSoftMask,
  dbHardMask       = dbHardMask,
  cullingLimit     = cullingLimit?.toInt(),
  bestHitOverhang  = bestHitOverhang,
  bestHitScoreEdge = bestHitScoreEdge,
  subjectBestHit   = subjectBestHit,
  sumStats         = sumStats,
  xDropGap         = extensionDropoffPrelimGapped,
  xDropGapFinal    = extensionDropoffFinalGapped,
  windowSize       = windowSize?.toInt(),
  ungapped         = ungappedAlignmentsOnly,
  useSWTraceback   = useSmithWatermanTraceback,
)

// ---------------------------------------------------------------------- //

fun convert(conf: IOTBlastnConfig): TBlastN = XTBlastN().apply {
  setQueryFile(conf.query)
  setExpectValue(conf.eValue)
  setNumDescriptions(conf.numDescriptions)
  setNumAlignments(conf.numAlignments)
  setLineLength(conf.lineLength)
  setMaxTargetSequences(conf.maxTargetSeqs)

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
  compBasedStats               = convertCBS(conf.compBasedStats)
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
  queryLoc         = b.queryLocation
  eValue           = b.expectValue.value
  outFormat        = b.outFormat?.toExternal()
  numDescriptions  = b.numDescriptions.value
  numAlignments    = b.numAlignments.value
  lineLength       = b.lineLength.value
  sortHits         = b.sortHits?.toExternal()
  sortHSPs         = b.sortHSPs?.toExternal()
  lcaseMasking     = b.lowercaseMasking
  qCovHSPPerc      = b.queryCoverageHSPPercent
  maxHSPs          = b.maxHSPs
  maxTargetSeqs    = b.maxTargetSequences.value
  dbSize           = b.dbSize
  searchSpace      = b.searchSpace
  xDropUngap       = b.extensionDropoffUngapped
  parseDefLines    = b.parseDefLines
  task             = b.task
  wordSize         = b.wordSize?.toInt()
  gapOpen          = b.gapOpen
  gapExtend        = b.gapExtend
  dbGencode        = b.dbGenCode?.toByte()
  maxIntronLength  = b.maxIntronLength?.toInt()
  matrix           = b.matrix
  threshold        = b.threshold
  compBasedStats   = convertCBS(b.compBasedStats)
  seg              = b.seg
  softMasking      = b.softMasking
  taxIds           = convertTaxIDsToExternal(b.taxIDs)
  negativeTaxIds   = convertTaxIDsToExternal(b.negativeTaxIDs)
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
  setQueryFile(conf.query)
  setExpectValue(conf.eValue)
  setNumDescriptions(conf.numDescriptions)
  setNumAlignments(conf.numAlignments)
  setLineLength(conf.lineLength)
  setMaxTargetSequences(conf.maxTargetSeqs)

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
  queryLoc         = b.queryLocation
  eValue           = b.expectValue.value
  outFormat        = b.outFormat?.toExternal()
  numDescriptions  = b.numDescriptions.value
  numAlignments    = b.numAlignments.value
  lineLength       = b.lineLength.value
  sortHits         = b.sortHits?.toExternal()
  sortHSPs         = b.sortHSPs?.toExternal()
  lcaseMasking     = b.lowercaseMasking
  qCovHSPPerc      = b.queryCoverageHSPPercent
  maxHSPs          = b.maxHSPs
  maxTargetSeqs    = b.maxTargetSequences.value
  dbSize           = b.dbSize
  searchSpace      = b.searchSpace
  xDropUngap       = b.extensionDropoffUngapped
  parseDefLines    = b.parseDefLines
  strand           = b.strand
  queryGeneticCode = b.queryGenCode?.toByte()
  wordSize         = b.wordSize?.toInt()
  maxIntronLength  = b.maxIntronLength?.toInt()
  matrix           = b.matrix
  threshold        = b.threshold
  dbGencode        = b.dbGenCode?.toByte()
  seg              = b.seg
  softMasking      = b.softMasking
  taxIds           = convertTaxIDsToExternal(b.taxIDs)
  negativeTaxIds   = convertTaxIDsToExternal(b.negativeTaxIDs)
  dbSoftMask       = b.dbSoftMask
  dbHardMask       = b.dbHardMask
  cullingLimit     = b.cullingLimit?.toInt()
  bestHitOverhang  = b.bestHitOverhang
  bestHitScoreEdge = b.bestHitScoreEdge
  subjectBestHit   = b.subjectBestHit
  sumStats         = b.sumStats
  windowSize       = b.windowSize?.toInt()
}

// ---------------------------------------------------------------------- //

fun convertCBS(value: String?): CompositionBasedStats? = value?.let(CompositionBasedStats::fromValue)

fun convertCBS(value: CompositionBasedStats?) = value?.externalValue

fun OutFormat.toExternal() = IOBlastReportFormat.fromInternalValue(this)

fun convert(value: IOBlastReportFormat?) = value?.toInternalValue

fun HitSorting.toExternal() = IOHitSorting[this]

fun convert(value: IOHitSorting?) = value?.internalValue

fun HSPSorting.toExternal() = IOHSPSorting[this]

fun convert(value: IOHSPSorting?) = value?.internalValue

private fun convertTaxIDsToExternal(value: List<String>?) = value?.map(String::toInt) ?: emptyList()

private fun convertTaxIDsToInternal(value: List<Int>?) = value?.map(Int::toString) ?: emptyList()
