package api

import (
	"errors"
	"fmt"

	"github.com/francoispqt/gojay"
	"github.com/sirupsen/logrus"
	"server/pkg/bfmt"
	"server/pkg/blast"
)

const (
	errJobIDBadValue         = "The field " + KeyJobID + " must be a string value of exactly 32 characters."
	errFormatBadValue        = "The field " + KeyFormat + " must be an integer value >= 0 and <= 18."
	errFieldDelimBadValue    = "The field " + KeyFieldDelim + " must be a string value of exactly 1 character."
	errShowGIsBadValue       = "The field " + KeyShowGIs + " must be a boolean value."
	errNumDescBadValue       = "The field " + KeyNumDescriptions + " must be an integer value >= 0."
	errNumAlignBadValue      = "The field " + KeyNumAlignments + " must be an integer value >= 0."
	errLineLengthBadValue    = "The field " + KeyLineLength + " must be an integer value >= 1."
	errHTMLBadValue          = "The field " + KeyHTML + " must be a boolean value."
	errHitSortingBadValue    = "The field " + KeySortHits + " must be an integer value >= 0 and <= 4."
	errHSPSortingBadValue    = "The field " + KeySortHSPs + " must be an integer value >= 0 and <= 4."
	errMaxTgtSeqsBadValue    = "The field " + KeyMaxTargetSeqs + " must be an integer value >= 1."
	errParseDeflinesBadValue = "The field " + KeyParseDeflines + " must be a boolean value."
	errBadKey                = "Unrecognized field \"%s\" in request payload."
)

const (
	JobPayloadDefaultFormat          = blast.FormatTypePairwise
	JobPayloadDefaultNumDescriptions = bfmt.DefaultNumDescriptions
	JobPayloadDefaultNumAlignments   = bfmt.DefaultNumAlignments
	JobPayloadDefaultLineLength      = bfmt.DefaultLineLength
	JobPayloadDefaultSortHits        = blast.HitSortingDisabled
	JobPayloadDefaultSortHSPs        = blast.HSPSortingDisabled
	JobPayloadDefaultMaxTargetSeqs   = bfmt.DefaultMaxTargetSeqs
)

func NewJobPayload() *JobPayload {
	return &JobPayload{
		Format:          JobPayloadDefaultFormat,
		NumDescriptions: JobPayloadDefaultNumDescriptions,
		NumAlignments:   JobPayloadDefaultNumAlignments,
		LineLength:      JobPayloadDefaultLineLength,
		SortHits:        JobPayloadDefaultSortHits,
		SortHSPs:        JobPayloadDefaultSortHSPs,
		MaxTargetSeqs:   JobPayloadDefaultMaxTargetSeqs,
	}
}

type JobPayload struct {
	JobID           string
	Format          blast.FormatType
	Fields          FieldSlice
	FieldDelim      string
	ShowGIs         bool
	NumDescriptions uint32
	NumAlignments   uint32
	LineLength      uint32
	HTML            bool
	SortHits        blast.HitSorting
	SortHSPs        blast.HSPSorting
	MaxTargetSeqs   uint32
	ParseDeflines   bool
}

func (j *JobPayload) UnmarshalJSONObject(dec *gojay.Decoder, k string) error {
	logrus.Tracef("api.JobPayload.UnmarshalJSONObject(dec=..., k=%s)", k)

	switch k {
	case KeyJobID:
		return j.decodeJobID(dec)
	case KeyFormat:
		return j.decodeFormat(dec)
	case KeyFields:
		return dec.DecodeArray(&j.Fields)
	case KeyFieldDelim:
		return j.decodeFieldDelim(dec)
	case KeyShowGIs:
		return j.decodeShowGIs(dec)
	case KeyNumDescriptions:
		return j.decodeNumDescriptions(dec)
	case KeyNumAlignments:
		return j.decodeNumAlignments(dec)
	case KeyLineLength:
		return j.decodeLineLength(dec)
	case KeyHTML:
		return j.decodeHTML(dec)
	case KeySortHits:
		return j.decodeHitSorting(dec)
	case KeySortHSPs:
		return j.decodeHSPSorting(dec)
	case KeyMaxTargetSeqs:
		return j.decodeMaxTgtSeqs(dec)
	case KeyParseDeflines:
		return j.decodeParseDeflines(dec)
	default:
		return fmt.Errorf(errBadKey, k)
	}
}

func (j *JobPayload) NKeys() int {
	return 0
}

func (j *JobPayload) decodeJobID(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeJobID(dec=...)")

	if err := dec.DecodeString(&j.JobID); err != nil {
		logrus.Debug("Failed to decode job ID value: ", err)
		return errors.New(errJobIDBadValue)
	}

	if len(j.JobID) != 32 {
		logrus.Debug("Rejecting job ID value for invalid length: ", j.JobID)
		return errors.New(errJobIDBadValue)
	}

	return nil
}

func (j *JobPayload) decodeFieldDelim(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeFieldDelim(dec=...)")

	var tmp string
	if err := dec.DecodeString(&tmp); err != nil {
		logrus.Debug("Failed to decode field delimiter: ", err)
		return errors.New(errFieldDelimBadValue)
	}

	if len(tmp) != 1 {
		logrus.Debug("Rejecting field delimiter value for bad length: ", tmp)
		return errors.New(errFieldDelimBadValue)
	}

	j.FieldDelim = tmp

	return nil
}

func (j *JobPayload) decodeShowGIs(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeShowGIs(dec=...)")

	if err := dec.DecodeBool(&j.ShowGIs); err != nil {
		logrus.Debug("Failed to parse showGIs value: ", err)
		return errors.New(errShowGIsBadValue)
	}

	return nil
}

func (j *JobPayload) decodeNumDescriptions(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeNumDescriptions(dec=...)")

	var tmp uint32
	if err := dec.DecodeUint32(&tmp); err != nil {
		logrus.Debug("Failed to decode "+KeyNumDescriptions+" value: ", err)
		return errors.New(errNumDescBadValue)
	}

	j.NumDescriptions = tmp

	return nil
}

func (j *JobPayload) decodeNumAlignments(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeNumAlignments(dec=...)")

	var tmp uint32
	if err := dec.DecodeUint32(&tmp); err != nil {
		logrus.Debug("Failed to decode "+KeyNumAlignments+" value: ", err)
		return errors.New(errNumAlignBadValue)
	}

	j.NumAlignments = tmp

	return nil
}

func (j *JobPayload) decodeLineLength(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeLineLength(dec=...)")

	var tmp uint32
	if err := dec.DecodeUint32(&tmp); err != nil {
		logrus.Debug("Failed to decode "+KeyLineLength+" value: ", err)
		return errors.New(errLineLengthBadValue)
	}

	if tmp < 1 {
		logrus.Debug("Rejecting " + KeyLineLength + " value for being < 1")
		return errors.New(errLineLengthBadValue)
	}

	j.LineLength = tmp

	return nil
}

func (j *JobPayload) decodeHTML(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeHTML(dec=...)")

	if err := dec.DecodeBool(&j.HTML); err != nil {
		logrus.Debug("Failed to parse "+KeyHTML+" value: ", err)
		return errors.New(errHTMLBadValue)
	}

	return nil
}

func (j *JobPayload) decodeMaxTgtSeqs(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeMaxTgtSeqs(dec=...)")

	var tmp uint32
	if err := dec.DecodeUint32(&tmp); err != nil {
		logrus.Debug("Failed to decode "+KeyMaxTargetSeqs+" value: ", err)
		return errors.New(errMaxTgtSeqsBadValue)
	}

	if tmp < 1 {
		logrus.Debug("Rejecting " + KeyMaxTargetSeqs + " value for being < 1")
		return errors.New(errMaxTgtSeqsBadValue)
	}

	j.MaxTargetSeqs = tmp

	return nil
}

func (j *JobPayload) decodeParseDeflines(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeParseDeflines(dec=...)")

	if err := dec.DecodeBool(&j.ParseDeflines); err != nil {
		logrus.Debug("Failed to parse "+KeyParseDeflines+" value: ", err)
		return errors.New(errParseDeflinesBadValue)
	}

	return nil
}

func (j *JobPayload) decodeFormat(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeFormat(dec=...)")

	err := dec.DecodeUint8((*uint8)(&j.Format))
	if err != nil {
		logrus.Debug("Failed to parse Format value: ", err)
		return errors.New(errFormatBadValue)
	}

	if !j.Format.IsValid() {
		logrus.Debug("Rejecting invalid Format value: ", j.Format)
		return errors.New(errFormatBadValue)
	}

	return nil
}

func (j *JobPayload) decodeHitSorting(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeHitSorting(dec=...)")

	if err := dec.DecodeUint8((*uint8)(&j.SortHits)); err != nil {
		logrus.Debug("Failed to parse HitSorting value: ", err)
		return errors.New(errHitSortingBadValue)
	}

	if !j.SortHits.IsValid() {
		logrus.Debug("Invalid HitSorting value given: ", j.SortHits)
		return errors.New(errHitSortingBadValue)
	}

	return nil
}

func (j *JobPayload) decodeHSPSorting(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeHSPSorting(dec=...)")

	if err := dec.DecodeUint8((*uint8)(&j.SortHSPs)); err != nil {
		logrus.Debug("Failed to parse HSPSorting value: ", err)
		return errors.New(errHSPSortingBadValue)
	}

	if !j.SortHSPs.IsValid() {
		logrus.Debug("Invalid HSPSorting value given: ", j.SortHSPs)
		return errors.New(errHSPSortingBadValue)
	}

	return nil
}

func (j *JobPayload) ToCmd() *bfmt.CLICall {
	tmp := bfmt.NewCLICall()

	if j.Format != blast.FormatTypePairwise {
		tmp.OutFormat = new(blast.Format)
		tmp.OutFormat.Type = j.Format
		if len(j.FieldDelim) > 0 {
			tmp.OutFormat.Delimiter = j.FieldDelim[0]
		}
		tmp.OutFormat.Fields = j.Fields
	}

	tmp.ShowGIs = j.ShowGIs
	tmp.NumDescriptions = j.NumDescriptions
	tmp.NumAlignments = j.NumAlignments
	tmp.LineLength = j.LineLength
	tmp.HTML = j.HTML
	tmp.SortHits = j.SortHits
	tmp.SortHSPs = j.SortHSPs
	tmp.MaxTargetSeqs = j.MaxTargetSeqs
	tmp.ParseDeflines = j.ParseDeflines

	return tmp
}
