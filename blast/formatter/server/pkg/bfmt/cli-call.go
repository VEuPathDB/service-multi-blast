package bfmt

import (
	"errors"
	"io"
	"os/exec"
	"strings"

	"server/pkg/blast"
)

const (
	Command = "blast_formatter"

	DefaultNumDescriptions uint32 = 500
	DefaultNumAlignments   uint32 = 250
	DefaultLineLength      uint32 = 60
	DefaultMaxTargetSeqs   uint32 = 500
)

// Validation errors
var (
	ErrNoArchiveNoRID    = errors.New("Either -rid or -archive must be specified.")
	ErrBothArchiveRID    = errors.New("Cannot specify both -rid and -archive.")
	ErrBothMTSNumDesc    = errors.New(string("Cannot use " + ArgNumDescriptions + " with " + ArgMaxTargetSeqs))
	ErrNumDescDisallowed = errors.New(string("Cannot use " + ArgNumDescriptions + " with output formats > 4."))
	ErrBothMTSNumAlign   = errors.New(string("Cannot use " + ArgNumAlignments + " with " + ArgMaxTargetSeqs))
	ErrLineLenDisallowed = errors.New(string("Cannot use " + ArgLineLength + " with output formats > 4."))
	ErrHitSortDisallowed = errors.New(string("Cannot use " + ArgSortHits + " with output formats > 4."))
	ErrHSPSortDisallowed = errors.New(string("Cannot use " + ArgSortHSPs + " with output formats > 0."))
)

func NewCLICall() *CLICall {
	return &CLICall{
		NumDescriptions: DefaultNumDescriptions,
		NumAlignments:   DefaultNumAlignments,
		LineLength:      DefaultLineLength,
		SortHits:        blast.HitSortingDisabled,
		SortHSPs:        blast.HSPSortingDisabled,
		MaxTargetSeqs:   DefaultMaxTargetSeqs,
		OutFile:         "-",
	}
}

type CLICall struct {
	ShortHelp       bool
	LongHelp        bool
	Version         bool
	RequestID       string
	Archive         string
	OutFormat       *blast.Format
	ShowGIs         bool
	NumDescriptions uint32
	NumAlignments   uint32
	LineLength      uint32
	HTML            bool
	SortHits        blast.HitSorting
	SortHSPs        blast.HSPSorting
	MaxTargetSeqs   uint32
	OutFile         string
	ParseDeflines   bool

	WorkDirectory  string
	Environment    []string
}

func (c *CLICall) toCmd() (*exec.Cmd, error) {
	if c.ShortHelp {
		return exec.Command(Command, string(ArgShortHelp)), nil
	}

	if c.LongHelp {
		return exec.Command(Command, string(ArgLongHelp)), nil
	}

	if c.Version {
		return exec.Command(Command, string(ArgVersion)), nil
	}

	if err := c.Validate(); err != nil {
		return nil, err
	}

	out := exec.Command(Command)

	if len(c.RequestID) > 0 {
		out.Args = append(out.Args, ArgRequestID.JoinString(c.RequestID))
	} else {
		out.Args = append(out.Args, ArgArchive.JoinString(c.Archive))
	}

	if c.OutFormat != nil {
		out.Args = append(out.Args, ArgOutFormat.JoinString(c.OutFormat.ToCLIArg()))
	}

	if c.ShowGIs {
		out.Args = append(out.Args, string(ArgShowGIs))
	}

	if c.NumDescriptions != DefaultNumDescriptions {
		out.Args = append(out.Args, ArgNumDescriptions.JoinNumeric(c.NumDescriptions))
	}

	if c.NumAlignments != DefaultNumAlignments {
		out.Args = append(out.Args, ArgNumAlignments.JoinNumeric(c.NumAlignments))
	}

	if c.LineLength != DefaultLineLength {
		out.Args = append(out.Args, ArgLineLength.JoinNumeric(c.LineLength))
	}

	if c.HTML {
		out.Args = append(out.Args, string(ArgHTML))
	}

	if c.SortHits != blast.HitSortingDisabled {
		out.Args = append(out.Args, ArgSortHits.JoinNumeric(c.SortHits))
	}

	if c.SortHSPs != blast.HSPSortingDisabled {
		out.Args = append(out.Args, ArgSortHSPs.JoinNumeric(c.SortHSPs))
	}

	if c.MaxTargetSeqs != DefaultMaxTargetSeqs {
		out.Args = append(out.Args, ArgMaxTargetSeqs.JoinNumeric(c.MaxTargetSeqs))
	}

	if len(c.OutFile) > 0 && c.OutFile != "-" {
		out.Args = append(out.Args, ArgOut.JoinString(c.OutFile))
	}

	if c.ParseDeflines {
		out.Args = append(out.Args, string(ArgParseDeflines))
	}

	if len(c.WorkDirectory) > 0 {
		out.Dir = c.WorkDirectory
	}

	return out, nil
}

func (c *CLICall) Validate() error {
	if len(c.RequestID) == 0 && len(c.Archive) == 0 {
		return ErrNoArchiveNoRID
	}

	if len(c.RequestID) > 0 && len(c.Archive) > 0 {
		return ErrBothArchiveRID
	}

	if c.OutFormat != nil {
		if err := c.OutFormat.Validate(); err != nil {
			return err
		}
	}

	if c.NumDescriptions != DefaultNumDescriptions {
		if c.MaxTargetSeqs != DefaultMaxTargetSeqs {
			return ErrBothMTSNumDesc
		}

		if c.OutFormat != nil && c.OutFormat.Type > 4 {
			return ErrNumDescDisallowed
		}
	}

	if c.NumAlignments != DefaultNumAlignments {
		if c.MaxTargetSeqs != DefaultMaxTargetSeqs {
			return ErrBothMTSNumAlign
		}
	}

	if c.LineLength != DefaultLineLength {
		if c.OutFormat != nil && c.OutFormat.Type > 4 {
			return ErrLineLenDisallowed
		}
	}

	if c.SortHits != blast.HitSortingDisabled {
		if err := c.SortHits.Validate(); err != nil {
			return err
		}

		if c.OutFormat != nil && c.OutFormat.Type > 4 {
			return ErrHitSortDisallowed
		}
	}

	if c.SortHSPs != blast.HSPSortingDisabled {
		if err := c.SortHSPs.Validate(); err != nil {
			return err
		}

		if c.OutFormat != nil && c.OutFormat.Type > 0 {
			return ErrHSPSortDisallowed
		}
	}

	return nil
}

func (c *CLICall) ToCLIString() (string, error) {
	if cmd, err := c.toCmd(); err != nil {
		return "", err
	} else {
		return strings.Join(cmd.Args, " "), nil
	}
}

func (c *CLICall) Execute(stdout, stderr io.Writer) error {
	cmd, err := c.toCmd()
	if err != nil {
		return err
	}

	cmd.Stdout = stdout
	cmd.Stderr = stderr

	return cmd.Run()
}