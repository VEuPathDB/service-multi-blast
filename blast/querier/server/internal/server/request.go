package server

import (
	"encoding/json"

	"server/pkg/blast"
)

type Request struct {
	JobID  string          `json:"jobID"`
	Tool   blast.Tool      `json:"tool"`
	Config json.RawMessage `json:"config"`
}
