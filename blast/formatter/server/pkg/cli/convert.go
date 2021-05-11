package cli

import (
	"strconv"
	"strings"

	"server/pkg/api"
	"server/pkg/bfmt"
	"server/pkg/blast"
)

func JobPayloadToCLI(job *api.JobPayload, report string) (cmd string, args []string) {
	args = make([]string, 0, 12)
	cmd = "blast_formatter"

	args = append(args, bfmt.KeyArchive.JoinString(report))

	if val, ok := makeOutFmt(job); ok {
		args = append(args, bfmt.KeyOutFormat.JoinString(val))
	}

	if job.ShowGIs {
		args = append(args, string(bfmt.KeyShowGIs))
	}

	if job.NumDescriptions != api.JobPayloadDefaultNumDescriptions {
		args = append(args, bfmt.KeyNumDescriptions.JoinNumeric(job.NumDescriptions))
	}

	if job.NumAlignments != api.JobPayloadDefaultNumAlignments {
		args = append(args, bfmt.KeyNumDescriptions.JoinNumeric(job.NumAlignments))
	}

	if job.LineLength != api.JobPayloadDefaultLineLength {
		args = append(args, bfmt.KeyLineLength.JoinNumeric(job.LineLength))
	}

	if job.HTML {
		args = append(args, string(bfmt.KeyHTML))
	}

	if job.SortHits != api.JobPayloadDefaultSortHits {
		args = append(args, bfmt.KeySortHits.JoinNumeric(job.SortHits))
	}

	if job.SortHSPs != api.JobPayloadDefaultSortHSPs {
		args = append(args, bfmt.KeySortHSPs.JoinNumeric(job.SortHSPs))
	}

	if job.MaxTargetSeqs != api.JobPayloadDefaultMaxTargetSeqs {
		args = append(args, bfmt.KeyMaxTargetSeqs.JoinNumeric(job.MaxTargetSeqs))
	}

	if job.ParseDeflines {
		args = append(args, string(bfmt.KeyParseDeflines))
	}

	return
}

func makeOutFmt(job *api.JobPayload) (string, bool) {
	if job.Format == blast.FormatPairwise {
		return "", false
	}

	out := new(strings.Builder)

	out.WriteString(strconv.Itoa(int(job.Format)))

	switch job.Format {
	case blast.FormatTabular, blast.FormatTabularWithComments, blast.FormatCSV, blast.FormatSequenceAlignmentMap:
		if len(job.FieldDelim) > 0 {
			out.WriteByte(' ')
			out.WriteByte(job.FieldDelim[0])
		}

		for _, field := range job.Fields {
			out.WriteByte(' ')
			out.WriteString(string(field))
		}
	}

	return out.String(), true
}
