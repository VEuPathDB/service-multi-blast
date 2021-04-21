package blast_cli

type BlastBase struct {
	ShortHelp                bool
	LongHelp                 bool
	Version                  bool
	QueryFile                string
	QueryLoc                 Location
	DBFile                   string
	OutFile                  string
	EValue                   string
	OutFormat                Format
	ShowGIs                  bool
	NumDescriptions          uint32
	NumAlignments            uint32
	LineLength               uint32
	HTML                     bool
	SortHits                 HitSorting
	SortHSPs                 HSPSorting
	SoftMasking              bool
	LowercaseMasking         bool
	EntrezQuery              string
	QueryCoverageHSPPercent  float64
	MaxHSPs                  uint32
	MaxTargetSequences       uint32
	DBSize                   uint8
	SearchSpace              uint8
	ImportSearchStrategy     string
	ExportSearchStrategy     string
	ExtensionDropoffUngapped float64
	WindowSize               uint32
	ParseDefLines            bool
	Remote                   bool
}
