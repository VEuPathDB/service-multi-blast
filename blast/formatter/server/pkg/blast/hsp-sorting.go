package blast

import (
	"errors"
	"strconv"
)

type HSPSorting uint8

const (
	HSPSortingByHSPEValue HSPSorting = iota
	HSPSortingByHSPScore
	HSPSortingByQueryStart
	HSPSortingByPercentIdentity
	HSPSortingBySubjectStart

	HSPSortingDisabled HSPSorting = 255
)

var ErrBadHSPSorting = errors.New("Invalid hsp sorting value, must be >= 0 and <= 4.")

func (h *HSPSorting) UnmarshalJSON(bytes []byte) error {
	if val, err := strconv.ParseUint(string(bytes), 10, 8); err != nil {
		return err
	} else {
		*h = HSPSorting(val)
		return nil
	}
}

func (h HSPSorting) IsValid() bool {
	return h.Validate() == nil
}

func (h HSPSorting) Validate() error {
	if h < 5 || h == 255 {
		return nil
	}

	return ErrBadHSPSorting
}

func (h HSPSorting) IsValidForFormat(fmt FormatType) bool {
	return fmt == 0
}
