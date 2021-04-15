package blast

import (
	"fmt"
	"strconv"
	"strings"
)

type Format struct {
	Type      FormatType
	Delimiter byte
	Fields    []FormatField
}

func (f *Format) IsValid() bool {
	return f.Validate() == nil
}

func (f *Format) Validate() error {
	if err := f.Type.Validate(); err != nil {
		return err
	}

	if len(f.Fields) > 0 {
		switch f.Type {
		case FormatTypeTabular, FormatTypeTabularWithComments, FormatTypeCSV, FormatTypeSequenceAlignmentMap:
			for _, field := range f.Fields {
				if err := field.Validate(); err != nil {
					return err
				}

				if !field.IsValidForFormat(f.Type) {
					return fmt.Errorf("Field %s cannot be used with report type %d", field, f.Type)
				}
			}
		default:
			return fmt.Errorf("Format type %d does not allow specifying report fields.", f.Type)
		}
	}

	return nil
}

func (f *Format) ToCLIArg() string {
	out := new(strings.Builder)

	if f.Delimiter != 0 || len(f.Fields) > 0 {
		out.WriteByte('\'')
	}

	out.WriteString(strconv.Itoa(int(f.Type)))

	if f.Delimiter != 0 {
		out.WriteByte(' ')
		out.WriteString("delim=")
		out.WriteByte(f.Delimiter)
	}

	for _, val := range f.Fields {
		out.WriteByte(' ')
		out.WriteString(string(val))
	}

	if f.Delimiter != 0 || len(f.Fields) > 0 {
		out.WriteByte('\'')
	}

	return out.String()
}
