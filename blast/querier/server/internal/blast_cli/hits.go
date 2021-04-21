package blast_cli

type HitSorting uint8

const (
	HitSortingByEValue HitSorting = iota
	HitSortingByBitScore
	HitSortingByTotalScore
	HitSortingByPercentIdentity
	HitSortingByQueryCoverage
)

type HSPSorting uint8

const (
	HSPSortingByHSPEValue HSPSorting = iota
	HSPSortingByHSPScore
	HSPSortingByHSPQueryStart
	HSPSortingByHSPPercentIdentity
	HSPSortingByHSPSubjectStart
)
