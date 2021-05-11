package server

import (
	"encoding/json"

	"github.com/veupathdb/lib-go-blast/v2/pkg/blast"
)

type Request struct {
	JobID  string          `json:"jobID"`
	Tool   blast.Tool      `json:"tool"`
	Config json.RawMessage `json:"config"`
}
