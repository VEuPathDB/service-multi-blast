package api

import (
	"errors"
	"fmt"

	"github.com/veupathdb/lib-go-blast/v2/pkg/blast"

	"github.com/francoispqt/gojay"
	"github.com/sirupsen/logrus"
)

const (
	errReportIDBadValue = "The field " + KeyReportID + " must be a string value of exactly 32 characters."
	errJobIDBadValue    = "The field " + KeyJobID + " must be a string value of exactly 32 characters."
	errBadKey           = "Unrecognized field \"%s\" in request payload."
)

func NewJobPayload() *JobPayload {
	return &JobPayload{}
}

type JobPayload struct {
	JobID    string
	ReportID string
	Config   blast.BlastFormatter
}

func (j *JobPayload) UnmarshalJSONObject(dec *gojay.Decoder, k string) error {
	logrus.Tracef("api.JobPayload.UnmarshalJSONObject(dec=..., k=%s)", k)

	switch k {
	case KeyJobID:
		return j.decodeJobID(dec)
	case KeyReportID:
		return j.decodeReportID(dec)
	case KeyConfig:
		return dec.Object(&j.Config)
	default:
		return fmt.Errorf(errBadKey, k)
	}
}

func (j *JobPayload) NKeys() int {
	return 0
}

func (j *JobPayload) decodeJobID(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeJobID(dec=...)")

	if err := dec.String(&j.JobID); err != nil {
		logrus.Debug("Failed to decode job ID value: ", err)
		return errors.New(errJobIDBadValue)
	}

	if len(j.JobID) != 32 {
		logrus.Debug("Rejecting job ID value for invalid length: ", j.JobID)
		return errors.New(errJobIDBadValue)
	}

	return nil
}

func (j *JobPayload) decodeReportID(dec *gojay.Decoder) error {
	logrus.Trace("api.JobPayload.decodeReportID(dec=...)")

	if err := dec.String(&j.ReportID); err != nil {
		logrus.Debug("Failed to decode report ID value: ", err)
		return errors.New(errJobIDBadValue)
	}

	if len(j.ReportID) != 32 {
		logrus.Debug("Rejecting report ID value for invalid length: ", j.ReportID)
		return errors.New(errJobIDBadValue)
	}

	return nil
}
