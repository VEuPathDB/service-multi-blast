package bfmt

import (
	"fmt"
	"strings"
)

type Arg string

const (
	ArgArchive         Arg = "-archive"
	ArgOutFormat       Arg = "-outfmt"
	ArgShowGIs         Arg = "-show_gis"
	ArgNumDescriptions Arg = "-num_descriptions"
	ArgNumAlignments   Arg = "-num_alignments"
	ArgLineLength      Arg = "-line_length"
	ArgHTML            Arg = "-html"
	ArgSortHits        Arg = "-sorthits"
	ArgSortHSPs        Arg = "-sorthsps"
	ArgMaxTargetSeqs   Arg = "-max_target_seqs"
	ArgOut             Arg = "-out"
	ArgParseDeflines   Arg = "-parse_deflines"
	ArgShortHelp       Arg = "-h"
	ArgLongHelp        Arg = "-help"
	ArgVersion         Arg = "-version"
	ArgRequestID       Arg = "-rid"
)

func (a Arg) JoinString(val string) string {
	if strings.IndexAny(val, " \t") < 0 {
		return string(a) + "=" + val
	} else {
		return string(a) + "='" + strings.Replace(val,"'", `'"'"'`, -1) + "'"
	}
}

func (a Arg) JoinNumeric(val interface{}) string {
	return fmt.Sprintf("%s=%d", a, val)
}
