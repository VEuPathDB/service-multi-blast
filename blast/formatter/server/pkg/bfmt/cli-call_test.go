package bfmt_test

import (
	"strings"
	"testing"

	. "github.com/smartystreets/goconvey/convey"
	"server/pkg/bfmt"
	"server/pkg/blast"
)

func TestCLICall_ToCLIString(t *testing.T) {
	Convey("CLICall.ToCLIString()", t, func() {
		tgt := bfmt.NewCLICall()
		tgt.Archive = "hello"

		out, err := tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
		}, " "))

		tgt.OutFormat = &blast.Format{
			Type:      10,
			Delimiter: '@',
			Fields:    []blast.FormatField{
				blast.FieldAlignedQuerySequence,
				blast.FieldAlignmentLength,
			},
		}

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			bfmt.ArgOutFormat.JoinString(tgt.OutFormat.ToCLIArg()),
		}, " "))

		tgt.ShowGIs = true

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			bfmt.ArgOutFormat.JoinString(tgt.OutFormat.ToCLIArg()),
			string(bfmt.ArgShowGIs),
		}, " "))

		tgt.NumAlignments = 10

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			bfmt.ArgOutFormat.JoinString(tgt.OutFormat.ToCLIArg()),
			string(bfmt.ArgShowGIs),
			bfmt.ArgNumAlignments.JoinNumeric(10),
		}, " "))

		tgt.HTML = true

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			bfmt.ArgOutFormat.JoinString(tgt.OutFormat.ToCLIArg()),
			string(bfmt.ArgShowGIs),
			bfmt.ArgNumAlignments.JoinNumeric(10),
			string(bfmt.ArgHTML),
		}, " "))

		tgt.NumAlignments = bfmt.DefaultNumAlignments
		tgt.MaxTargetSeqs = 10

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			bfmt.ArgOutFormat.JoinString(tgt.OutFormat.ToCLIArg()),
			string(bfmt.ArgShowGIs),
			string(bfmt.ArgHTML),
			bfmt.ArgMaxTargetSeqs.JoinNumeric(10),
		}, " "))

		tgt.OutFile = "goodbye"

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			bfmt.ArgOutFormat.JoinString(tgt.OutFormat.ToCLIArg()),
			string(bfmt.ArgShowGIs),
			string(bfmt.ArgHTML),
			bfmt.ArgMaxTargetSeqs.JoinNumeric(10),
			bfmt.ArgOut.JoinString("goodbye"),
		}, " "))

		tgt.ParseDeflines = true

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			bfmt.ArgOutFormat.JoinString(tgt.OutFormat.ToCLIArg()),
			string(bfmt.ArgShowGIs),
			string(bfmt.ArgHTML),
			bfmt.ArgMaxTargetSeqs.JoinNumeric(10),
			bfmt.ArgOut.JoinString("goodbye"),
			string(bfmt.ArgParseDeflines),
		}, " "))

		tgt.OutFormat = nil
		tgt.MaxTargetSeqs = bfmt.DefaultMaxTargetSeqs
		tgt.NumDescriptions = 20

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			string(bfmt.ArgShowGIs),
			bfmt.ArgNumDescriptions.JoinNumeric(20),
			string(bfmt.ArgHTML),
			bfmt.ArgOut.JoinString("goodbye"),
			string(bfmt.ArgParseDeflines),
		}, " "))

		tgt.NumAlignments = 30

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			string(bfmt.ArgShowGIs),
			bfmt.ArgNumDescriptions.JoinNumeric(20),
			bfmt.ArgNumAlignments.JoinNumeric(30),
			string(bfmt.ArgHTML),
			bfmt.ArgOut.JoinString("goodbye"),
			string(bfmt.ArgParseDeflines),
		}, " "))

		tgt.LineLength = 40

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			string(bfmt.ArgShowGIs),
			bfmt.ArgNumDescriptions.JoinNumeric(20),
			bfmt.ArgNumAlignments.JoinNumeric(30),
			bfmt.ArgLineLength.JoinNumeric(40),
			string(bfmt.ArgHTML),
			bfmt.ArgOut.JoinString("goodbye"),
			string(bfmt.ArgParseDeflines),
		}, " "))

		tgt.SortHits = blast.HitSortingByBitScore

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			string(bfmt.ArgShowGIs),
			bfmt.ArgNumDescriptions.JoinNumeric(20),
			bfmt.ArgNumAlignments.JoinNumeric(30),
			bfmt.ArgLineLength.JoinNumeric(40),
			string(bfmt.ArgHTML),
			bfmt.ArgSortHits.JoinNumeric(blast.HitSortingByBitScore),
			bfmt.ArgOut.JoinString("goodbye"),
			string(bfmt.ArgParseDeflines),
		}, " "))

		tgt.SortHSPs = blast.HSPSortingByHSPScore

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgArchive.JoinString("hello"),
			string(bfmt.ArgShowGIs),
			bfmt.ArgNumDescriptions.JoinNumeric(20),
			bfmt.ArgNumAlignments.JoinNumeric(30),
			bfmt.ArgLineLength.JoinNumeric(40),
			string(bfmt.ArgHTML),
			bfmt.ArgSortHits.JoinNumeric(blast.HitSortingByBitScore),
			bfmt.ArgSortHSPs.JoinNumeric(blast.HSPSortingByHSPScore),
			bfmt.ArgOut.JoinString("goodbye"),
			string(bfmt.ArgParseDeflines),
		}, " "))

		tgt.RequestID = "hello"
		tgt.Archive = ""

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			bfmt.ArgRequestID.JoinString("hello"),
			string(bfmt.ArgShowGIs),
			bfmt.ArgNumDescriptions.JoinNumeric(20),
			bfmt.ArgNumAlignments.JoinNumeric(30),
			bfmt.ArgLineLength.JoinNumeric(40),
			string(bfmt.ArgHTML),
			bfmt.ArgSortHits.JoinNumeric(blast.HitSortingByBitScore),
			bfmt.ArgSortHSPs.JoinNumeric(blast.HSPSortingByHSPScore),
			bfmt.ArgOut.JoinString("goodbye"),
			string(bfmt.ArgParseDeflines),
		}, " "))

		tgt.Version = true

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			string(bfmt.ArgVersion),
		}, " "))

		tgt.LongHelp = true

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			string(bfmt.ArgLongHelp),
		}, " "))

		tgt.ShortHelp = true

		out, err = tgt.ToCLIString()
		So(err, ShouldBeNil)
		So(out, ShouldEqual, strings.Join([]string{
			bfmt.Command,
			string(bfmt.ArgShortHelp),
		}, " "))
	})
}

func TestCLICall_Validate(t *testing.T) {
	Convey("CLICall.Validate()", t, func() {
		tgt := bfmt.NewCLICall()

		So(tgt.Validate(), ShouldPointTo, bfmt.ErrNoArchiveNoRID)

		tgt.Archive = "hi"
		tgt.RequestID = "bi"

		So(tgt.Validate(), ShouldPointTo, bfmt.ErrBothArchiveRID)

		tgt = bfmt.NewCLICall()
		tgt.Archive = "hi"
		tgt.OutFormat = &blast.Format{Type: 19}

		So(tgt.Validate(), ShouldPointTo, blast.ErrBadFormatType)

		tgt.OutFormat.Type = 18
		tgt.NumDescriptions = 10

		So(tgt.Validate(), ShouldPointTo, bfmt.ErrNumDescDisallowed)

		tgt.MaxTargetSeqs = 15

		So(tgt.Validate(), ShouldPointTo, bfmt.ErrBothMTSNumDesc)

		tgt.NumDescriptions = bfmt.DefaultNumDescriptions
		tgt.NumAlignments = 10

		So(tgt.Validate(), ShouldPointTo, bfmt.ErrBothMTSNumAlign)

		tgt.NumAlignments = bfmt.DefaultNumAlignments
		tgt.LineLength = 50

		So(tgt.Validate(), ShouldPointTo, bfmt.ErrLineLenDisallowed)

		tgt.LineLength = bfmt.DefaultLineLength
		tgt.SortHits = 45

		So(tgt.Validate(), ShouldPointTo, blast.ErrBadHitSorting)

		tgt.SortHits = blast.HitSortingByEValue

		So(tgt.Validate(), ShouldPointTo, bfmt.ErrHitSortDisallowed)

		tgt.SortHits = blast.HitSortingDisabled
		tgt.SortHSPs = 45

		So(tgt.Validate(), ShouldPointTo, blast.ErrBadHSPSorting)

		tgt.SortHSPs = blast.HSPSortingByHSPScore

		So(tgt.Validate(), ShouldPointTo, bfmt.ErrHSPSortDisallowed)

	})
}
