package server_test

import (
	"fmt"
	"strings"
	"testing"

	. "github.com/smartystreets/goconvey/convey"
	"github.com/veupathdb/lib-go-blast/v2/pkg/blast/field"
	"server/internal/server"
)

func TestDecodePayload(t *testing.T) {
	Convey("server.DecodePayload", t, func() {
		tests := []string{
			`{"jobID":"EBA7E2CD7946AC8D6EB41A3550785F2C","config":{"-outfmt":{"type":0,"fields":null},"-archive":"EBA7E2CD7946AC8D6EB41A3550785F2C"}}`,
		}

		for i := range tests {
			body := strings.NewReader(tests[i])
			a, b := server.DecodePayload(body)

			So(b, ShouldBeNil)
			So(a, ShouldNotBeNil)
		}
	})
}

func TestOutputName(t *testing.T) {
	Convey("server.OutputName", t, func() {
		tests := []struct{
			Type field.FormatType
			Exp  string
		}{
			{field.FormatTypePairwise, "report.txt"},
			{field.FormatTypeQueryAnchoredShowingIdentities, "report.txt"},
			{field.FormatTypeQueryAnchoredNoIdentities, "report.txt"},
			{field.FormatTypeFlatQueryAnchoredShowingIdentities, "report.txt"},
			{field.FormatTypeFlatQueryAnchoredNoIdentities, "report.txt"},
			{field.FormatTypeBlastXML, "report.xml"},
			{field.FormatTypeTabular, "report.tsv"},
			{field.FormatTypeTabularWithCommentLines, "report.tsv"},
			{field.FormatTypeSeqAlignTextASN1, "report.asn1"},
			{field.FormatTypeSeqAlignBinaryASN1, "report.asn1"},
			{field.FormatTypeCommaSeparatedValues, "report.csv"},
			{field.FormatTypeBlastArchiveASN1, "report.asn1"},
			{field.FormatTypeSeqAlignJSON, "report.json"},
			{field.FormatTypeMultipleFileBlastJSON, "report.json"},
			{field.FormatTypeMultipleFileBlastXML2, "report.xml"},
			{field.FormatTypeSingleFileBlastJSON, "report.json"},
			{field.FormatTypeSingleFileBlastXML2, "report.xml"},
			{field.FormatTypeSequenceAlignmentMap, "report.txt"},
			{field.FormatTypeOrganismReport, "report.txt"},
		}

		for i := range tests {
			Convey(fmt.Sprintf("Correct filename for type %d", tests[i].Type), func() {
				So(server.OutputName(tests[i].Type), ShouldEqual, tests[i].Exp)
			})
		}
	})
}