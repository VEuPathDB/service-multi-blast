package blast

import "errors"

type FormatType uint8

const (
	FormatTypePairwise FormatType = iota
	FormatTypeQueryAnchoredWithIdentities
	FormatTypeQueryAnchoredNoIdentities
	FormatTypeFlatQueryAnchoredWithIdentities
	FormatTypeFlatQueryAnchoredNoIdentities
	FormatTypeBlastXML
	FormatTypeTabular
	FormatTypeTabularWithComments
	FormatTypeSeqAlignText
	FormatTypeSeqAlignBinary
	FormatTypeCSV
	FormatTypeBlastArchive
	FormatTypeSeqAlignJSON
	FormatTypeMultiFileBlastJSON
	FormatTypeMultiFileBlastXML2
	FormatTypeSingleFileBlastJSON
	FormatTypeSingleFileBlastXML2
	FormatTypeSequenceAlignmentMap
	FormatTypeOrganismReport
)

var (
	ErrBadFormatType = errors.New("Invalid format type value, must be >= 0 and <= 18.")
)

func (f FormatType) IsValid() bool {
	return f.Validate() == nil
}

func (f FormatType) Validate() error {
	if f < 19 {
		return nil
	}

	return ErrBadFormatType
}