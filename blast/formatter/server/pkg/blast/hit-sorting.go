package blast

import "errors"

type HitSorting uint8

const (
	HitSortingByEValue HitSorting = iota
	HitSortingByBitScore
	HitSortingByTotalScore
	HitSortingByPercentIdentity
	HitSortingByQueryCoverage

	HitSortingDisabled HitSorting = 255
)

var ErrBadHitSorting = errors.New("Invalid hit sorting value, must be >= 0 and <= 4.")

func (h HitSorting) IsValid() bool {
	return h.Validate() == nil
}

func (h HitSorting) Validate() error {
	if h < 5 || h == 255 {
		return nil
	}

	return ErrBadHitSorting
}

func (h HitSorting) IsValidForFormat(fmt FormatType) bool {
	return fmt < 5
}
