package diamond

type Request struct {
	JobID  string   `json:"jobID"`
	Tool   string   `json:"tool"`
	Params []string `json:"params"`
}
