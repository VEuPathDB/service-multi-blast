package blast

import "errors"

type FormatField string

const (
	FieldQuerySeqID                    FormatField = "qseqid"
	FieldQueryGI                       FormatField = "qgi"
	FieldQueryAccession                FormatField = "qacc"
	FieldQueryAccessionVersion         FormatField = "qaccver"
	FieldQuerySequenceLength           FormatField = "qlen"
	FieldSubjectSeqID                  FormatField = "sseqid"
	FieldAllSubjectSeqIDs              FormatField = "sallseqid"
	FieldSubjectGI                     FormatField = "sgi"
	FieldSubjectAllGIs                 FormatField = "sallgi"
	FieldSubjectAccession              FormatField = "sacc"
	FieldSubjectAccessionVersion       FormatField = "saccver"
	FieldAllSubjectAccessions          FormatField = "sallacc"
	FieldSubjectSequenceLength         FormatField = "slen"
	FieldStartOfQueryAlignment         FormatField = "qstart"
	FieldEndOfQueryAlignment           FormatField = "qend"
	FieldStartOfSubjectAlignment       FormatField = "sstart"
	FieldEndOfSubjectAlignment         FormatField = "send"
	FieldAlignedQuerySequence          FormatField = "qseq"
	FieldAlignedSubjectSequence        FormatField = "sseq"
	FieldExpectValue                   FormatField = "evalue"
	FieldBitScore                      FormatField = "bitscore"
	FieldRawScore                      FormatField = "score"
	FieldAlignmentLength               FormatField = "length"
	FieldPercentIdenticalMatches       FormatField = "pident"
	FieldNumberIdenticalMatches        FormatField = "nident"
	FieldNumberOfMismatches            FormatField = "mismatch"
	FieldNumberOfPositiveMatches       FormatField = "positive"
	FieldNumberOfGapOpenings           FormatField = "gapopen"
	FieldTotalGaps                     FormatField = "gaps"
	FieldPercentPositiveMatches        FormatField = "ppos"
	FieldFrames                        FormatField = "frames"
	FieldQueryFrame                    FormatField = "qframe"
	FieldSubjectFrame                  FormatField = "sframe"
	FieldBlastTracebackOperations      FormatField = "btop"
	FieldSubjectTaxonomyID             FormatField = "staxid"
	FieldSubjectScientificName         FormatField = "ssciname"
	FieldSubjectCommonName             FormatField = "scomname"
	FieldSubjectBlastName              FormatField = "sblastname"
	FieldSubjectSuperKingdom           FormatField = "sskingdom"
	FieldUniqueSubjectTaxonomyIDs      FormatField = "staxids"
	FieldUniqueSubjectScientificNames  FormatField = "sscinames"
	FieldUniqueSubjectCommonNames      FormatField = "scomnames"
	FieldUniqueSubjectBlastNames       FormatField = "sblastnames"
	FieldUniqueSubjectSuperKingdoms    FormatField = "sskingdoms"
	FieldSubjectTitle                  FormatField = "stitle"
	FieldAllSubjectTitles              FormatField = "salltitles"
	FieldSubjectStrand                 FormatField = "sstrand"
	FieldQueryCoveragePerSubject       FormatField = "qcovs"
	FieldQueryCoveragePerHSP           FormatField = "qcovhsp"
	FieldQueryCoveragePerUniqueSubject FormatField = "qcovus"
	FieldIncludeSequenceData           FormatField = "SQ"
	FieldSubjectAsReferenceSequence    FormatField = "SR"
	FieldStandardFields                FormatField = "std"
)

// Validation errors
var (
	ErrBadFormatField = errors.New("Unrecognized blast report field.")
)

var reportFieldMap = map[string]FormatField{
	"qseqid":      FieldQuerySeqID,
	"qgi":         FieldQueryGI,
	"qacc":        FieldQueryAccession,
	"qaccver":     FieldQueryAccessionVersion,
	"qlen":        FieldQuerySequenceLength,
	"sseqid":      FieldSubjectSeqID,
	"sallseqid":   FieldAllSubjectSeqIDs,
	"sgi":         FieldSubjectGI,
	"sallgi":      FieldSubjectAllGIs,
	"sacc":        FieldSubjectAccession,
	"saccver":     FieldSubjectAccessionVersion,
	"sallacc":     FieldAllSubjectAccessions,
	"slen":        FieldSubjectSequenceLength,
	"qstart":      FieldStartOfQueryAlignment,
	"qend":        FieldEndOfQueryAlignment,
	"sstart":      FieldStartOfSubjectAlignment,
	"send":        FieldEndOfSubjectAlignment,
	"qseq":        FieldAlignedQuerySequence,
	"sseq":        FieldAlignedSubjectSequence,
	"evalue":      FieldExpectValue,
	"bitscore":    FieldBitScore,
	"score":       FieldRawScore,
	"length":      FieldAlignmentLength,
	"pident":      FieldPercentIdenticalMatches,
	"nident":      FieldNumberIdenticalMatches,
	"mismatch":    FieldNumberOfMismatches,
	"positive":    FieldNumberOfPositiveMatches,
	"gapopen":     FieldNumberOfGapOpenings,
	"gaps":        FieldTotalGaps,
	"ppos":        FieldPercentPositiveMatches,
	"frames":      FieldFrames,
	"qframe":      FieldQueryFrame,
	"sframe":      FieldSubjectFrame,
	"btop":        FieldBlastTracebackOperations,
	"staxid":      FieldSubjectTaxonomyID,
	"ssciname":    FieldSubjectScientificName,
	"scomname":    FieldSubjectCommonName,
	"sblastname":  FieldSubjectBlastName,
	"sskingdom":   FieldSubjectSuperKingdom,
	"staxids":     FieldUniqueSubjectTaxonomyIDs,
	"sscinames":   FieldUniqueSubjectScientificNames,
	"scomnames":   FieldUniqueSubjectCommonNames,
	"sblastnames": FieldUniqueSubjectBlastNames,
	"sskingdoms":  FieldUniqueSubjectSuperKingdoms,
	"stitle":      FieldSubjectTitle,
	"salltitles":  FieldAllSubjectTitles,
	"sstrand":     FieldSubjectStrand,
	"qcovs":       FieldQueryCoveragePerSubject,
	"qcovhsp":     FieldQueryCoveragePerHSP,
	"qcovus":      FieldQueryCoveragePerUniqueSubject,
	"SQ":          FieldIncludeSequenceData,
	"SR":          FieldSubjectAsReferenceSequence,
	"std":         FieldStandardFields,
}

// IsValid returns whether the current value is a valid FormatField value.
func (f FormatField) IsValid() bool {
	_, ok := reportFieldMap[string(f)]
	return ok
}

// IsValidForFormat returns whether the current FormatField is valid for use
// with the given FormatType value.
func (f FormatField) IsValidForFormat(fmt FormatType) bool {
	if !f.IsValid() {
		return false
	}

	switch fmt {
	case FormatTypeTabular, FormatTypeTabularWithComments, FormatTypeCSV:
		return f != FieldIncludeSequenceData && f != FieldSubjectAsReferenceSequence
	case FormatTypeSequenceAlignmentMap:
		return true
	default:
		return false
	}
}

func (f FormatField) Validate() error {
	if !f.IsValid() {
		return ErrBadFormatField
	}

	return nil
}
