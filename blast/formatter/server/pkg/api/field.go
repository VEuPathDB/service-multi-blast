package api

import (
	"errors"
	"fmt"

	"github.com/francoispqt/gojay"
	"github.com/sirupsen/logrus"
	"server/pkg/blast"
)

type FieldSlice []blast.FormatField

const (
	errFieldSliceBadValue = "Could not parse field name.  Field names must be strings."
	errFieldInvalidValue  = "Invalid " + KeyFields + " entry \"%s\"."
)

func (f *FieldSlice) UnmarshalJSONArray(dec *gojay.Decoder) error {
	logrus.Trace("api.FieldSlice.UnmarshalJSONArray(dec=...)")

	var tmp blast.FormatField
	if err := dec.DecodeString((*string)(&tmp)); err != nil {
		logrus.Debug("Could not decode field value as a string: ", err)
		return errors.New(errFieldSliceBadValue)
	}

	if !tmp.IsValid() {
		logrus.Debugf(errFieldInvalidValue, tmp)
		return fmt.Errorf(errFieldInvalidValue, tmp)
	}

	*f = append(*f, tmp)

	return nil
}
