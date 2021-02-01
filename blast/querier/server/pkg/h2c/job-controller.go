package h2c

import (
	"encoding/json"
	"net/http"
	"strings"

	"github.com/gorilla/mux"
	"github.com/sirupsen/logrus"
)

func NewJobController(config *Config) http.Handler {
	return &endpoint{config: config}
}

type endpoint struct {
	config *Config
}

func (e *endpoint) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	if r.Body != nil {
		defer r.Body.Close()
	} else {
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte(`{"status": "permanent-failure", "message": "Missing request body."}`))
		return
	}

	// Path Variables
	vars := mux.Vars(r)
	tool := vars["tool"]
	if !e.toolAllowed(tool) {
		logrus.Infof("Call to disallowed tool %s by %s", tool, r.RemoteAddr)
		w.WriteHeader(http.StatusNotFound)
		w.Write([]byte(`{"status": "permanent-failure", "message": "Requested blast tool not found or disallowed."}`))
		return
	}

	rawArgs := make([]string, 0, 64)
	dec := json.NewDecoder(r.Body)
	if err := dec.Decode(&rawArgs); err != nil {
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte(`{"status": "permanent-failure", "message": "` + err.Error() + `"}`))
		return
	}

	args := make([]string, 0, len(rawArgs) - 1)
	hasOut := false
	hasFmt := false
	args = append(args, "-query=query.txt")
	for i := range rawArgs {
		if strings.HasPrefix(rawArgs[i], "-query=") {
			// Ignore -query in favor of stored query file
		} else if strings.HasPrefix(rawArgs[i], "-outfmt=") {
			args = append(args, "-outfmt=11")
			hasFmt = true
		} else if strings.HasPrefix(rawArgs[i], "-out") {
			args = append(args, "-out=report.asn1")
			hasOut = true
		} else {
			args = append(args, rawArgs[i])
		}
	}

	if !hasFmt {
		args = append(args, "-outfmt=11")
	}

	if !hasOut {
		args = append(args, "-out=report.asn1")
	}

	job := NewJob()
	job.ID = vars["job-id"]
	job.Tool = tool
	job.Args = args
	job.Config = e.config

	if err := job.Run(); err != nil {
		logrus.Error(err)
		logrus.Warnf("Job %s with tool %s failed.", job.ID, tool)
		w.WriteHeader(http.StatusInternalServerError)
		w.Write([]byte(`{"status": "permanent-failure", "message": "` + err.Error() + `"}`))
		return
	}

	w.WriteHeader(http.StatusOK)
	w.Write([]byte(`{"status": "success", "message": "Job completed successfully."}`))
}

func (e *endpoint) toolAllowed(tool string) bool {
	for i := range e.config.Tools {
		if tool == e.config.Tools[i] {
			return true
		}
	}

	return false
}
