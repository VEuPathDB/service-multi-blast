package blast_cli

type FormatType uint8

const (
	FormatTypePairwise FormatType = iota
	FormatTypeQueryAnchoredWithIdentities
	FormatTypeQueryAnchoredNoIdentities
	FormatTypeFlatQueryAnchoredShowingIdentities
	FormatTypeFlatQueryAnchoredNoIdentities
	FormatTypeBlastXML
	FormatTypeTabular
	FormatTypeTabularWithCommentLines
	FormatTypeSeqalignTextASN1
	FormatTypeSeqalignBinaryASN1
	FormatTypeCommaSeparatedValues
	FormatTypeBLASTArchiveASN1
	FormatTypeSeqalignJSON
	FormatTypeMultipleFileBlastJSON
	FormatTypeMultipleFileBlastXML2
	FormatTypeSingleFileBlastJSON
	FormatTypeSingleFileBlastXML2
	FormatTypeSequenceAlignmentMap
	FormatTypeOrganismReport
)

func (f FormatType) IsValidForTool(tool BlastTool) bool {
	if f == FormatTypeSequenceAlignmentMap {
		return tool == BlastToolBlastN
	}

	return true
}

func (f FormatType) AllowsFields() bool {
	switch f {
	case FormatTypeTabular:
		return true
	case FormatTypeTabularWithCommentLines:
		return true
	case FormatTypeCommaSeparatedValues:
		return true
	case FormatTypeSequenceAlignmentMap:
		return true
	default:
		return false
	}
}

func (f FormatType) AllowsDelimiter() bool {
	switch f {
	case FormatTypeTabular:
		return true
	case FormatTypeTabularWithCommentLines:
		return true
	case FormatTypeCommaSeparatedValues:
		return true
	default:
		return false
	}
}

// ////////////////////////////////////////////////////////////////////////// //

type FormatField string

const (
	FormatFieldQuerySeqID                         = "qseqid"
	FormatFieldQueryGI                            = "qgi"
	FormatFieldQueryAccession                     = "qacc"
	FormatFieldQueryAccessionVersion              = "qaccver"
	FormatFieldQuerySequenceLength                = "qlen"
	FormatFieldSubjectSeqID                       = "sseqid"
	FormatFieldAllSubjectSeqID                    = "sallseqid"
	FormatFieldSubjectGI                          = "sgi"
	FormatFieldAllSubjectGIs                      = "sallgi"
	FormatFieldSubjectAccession                   = "sacc"
	FormatFieldSubjectAccessionVersion            = "saccver"
	FormatFieldAllSubjectAccessions               = "sallacc"
	FormatFieldSubjectSequenceLength              = "slen"
	FormatFieldStartOfAlignmentInQuery            = "qstart"
	FormatFieldEndOfAlignmentInQuery              = "qend"
	FormatFieldStartOfAlignmentInSubject          = "sstart"
	FormatFieldEndOfAlignmentInSubject            = "send"
	FormatFieldAlignedPartOfQuerySequence         = "qseq"
	FormatFieldAlignedPartOfSubjectSequence       = "sseq"
	FormatFieldExpectValue                        = "evalue"
	FormatFieldBitScore                           = "bitscore"
	FormatFieldRawScore                           = "score"
	FormatFieldAlignmentLength                    = "length"
	FormatFieldPercentageOfIdenticalMatches       = "pident"
	FormatFieldNumberOfIdenticalMatches           = "nident"
	FormatFieldNumberOfMismatches                 = "mismatch"
	FormatFieldNumberOfPositiveScoringMatches     = "positive"
	FormatFieldNumberOfGapOpenings                = "gapopen"
	FormatFieldTotalNumberOfGaps                  = "gaps"
	FormatFieldPercentageOfPositiveScoringMatches = "ppos"
	FormatFieldQueryAndSubjectFrames              = "frames"
	FormatFieldQueryFrame                         = "qframe"
	FormatFieldSubjectFrame                       = "sframe"
	FormatFieldBlastTracebackOperations           = "btop"
	FormatFieldSubjectTaxonomyID                  = "staxid"
	FormatFieldSubjectScientificName              = "ssciname"
	FormatFieldSubjectCommonName                  = "scomname"
	FormatFieldSubjectBlastName                   = "sblastname"
	FormatFieldSubjectSuperKingdom                = "sskingdom"
	FormatFieldUniqueSubjectTaxonomyIDs           = "staxids"
	FormatFieldUniqueSubjectScientificNames       = "sscinames"
	FormatFieldUniqueSubjectCommonNames           = "scomnames"
	FormatFieldUniqueSubjectBlastNames            = "sblastnames"
	FormatFieldUniqueSubjectSuperKingdoms         = "sskingdoms"
	FormatFieldSubjectTitle                       = "stitle"
	FormatFieldAllSubjectTitles                   = "salltitles"
	FormatFieldSubjectStrand                      = "sstrand"
	FormatFieldQueryCoveragePerSubject            = "qcovs"
	FormatFieldQueryCoveragePerHSP                = "qcovhsp"
	FormatFieldQueryCoveragePerUniqueSubject      = "qcovus"
	FormatFieldIncludeSequenceData                = "SQ"
	FormatFieldSubjectAsReferenceSeq              = "SR"
	FormatFieldStandardFields                     = "std"
)

// ////////////////////////////////////////////////////////////////////////// //

type Format struct {
	Type   FormatType
	Delim  byte
	Fields []FormatField
}
