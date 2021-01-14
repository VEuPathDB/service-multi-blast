package server

import (
	"archive/zip"
	"compress/flate"
	"crypto/sha256"
	"encoding/json"
	"io"
	"net/http"
	"os"
	"os/exec"
	"path/filepath"
	"sort"
	"strings"

	"github.com/gorilla/mux"
	"github.com/vulpine-io/midl/v1/pkg/midl"
)

func RunReport17Endpoint(req midl.Request) midl.Response {
	if req.RawRequest().Body != nil {
		defer req.RawRequest().Body.Close()
	}

	params := mux.Vars(req.RawRequest())
	reqID := req.AdditionalContext()[reqIDKey].(string)
	kind := params[reportTypeParam]

	reportsDir := filepath.Join(config.mountPath, params[jobIDParam])
	// reportDir := filepath.Join(workspace, reportDirPrefix + kind)

	body := new(runRequestBody)

	req.ProcessBody(midl.BodyProcessorFunc(func(bytes []byte) error {
		return json.Unmarshal(bytes, body)
	}))
	if req.Error() != nil {
		return midl.MakeResponse(http.StatusBadRequest, New400Error(req.Error().Error(), reqID))
	}

	fieldString := strings.Join()
	midl.Response().Callback()

	if _, err := os.Stat(reportDir); err != nil {
		if !os.IsNotExist(err) {
			return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
		}

		if err = os.Mkdir(reportDir, 0775); err != nil {
			return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
		}

		return midl.
	} else {

	}


	args := make([]string, 0, 4)
	args = append(args, "-archive", "output.asn", "-outfmt")
	if len(body.Fields) > 0 {
		args = append(args, "17 " + strings.Join(body.Fields, " "))
	} else {
		args = append(args, "17")
	}

	exec.Command(
		"blast_formatter",
		"-archive", "output.asn",
		"-outfmt", strings.Join(body.Fields, " "))

}

func RunReportEndpoint(req midl.Request) midl.Response {
	params := mux.Vars(req.RawRequest())
	reqID := req.AdditionalContext()[reqIDKey].(string)
	kind := params[reportTypeParam]

	workspace := filepath.Join(config.mountPath, params[jobIDParam])
	body := new(runRequestBody)

	req.ProcessBody(midl.BodyProcessorFunc(func(bytes []byte) error {
		return json.Unmarshal(bytes, body)
	}))

	fields := "std"

	exec.Command(
		"blast_formatter",
		"-archive", "output.asn",
		"-outfmt", strings.Join(body.Fields, " "))

}

type runRequestBody struct {
	Fields []string `json:"fields"`
	Delim  byte     `json:"delim"`
}

func zipDir(path string) (file *os.File, err error) {
	matches, err := filepath.Glob(filepath.Join(path, "*"))
	if err != nil {
		return
	}

	file, err = os.Create(filepath.Join(path, reportExportName))
	if err != nil {
		return
	}
	defer func() {
		if err != nil {
			_ = file.Close()
			_ = os.Remove(filepath.Join(path, reportExportName))
		}
	}()

	zp := zip.NewWriter(file)
	zp.RegisterCompressor(zip.Deflate, func(w io.Writer) (io.WriteCloser, error) {
		return flate.NewWriter(w, flate.BestCompression)
	})

	var tf io.Writer
	for i := range matches {
		tf, err = zp.Create(filepath.Base(matches[i]));
		if err != nil {
			return
		}

		if err = copyFileInto(matches[i], tf); err != nil {
			return
		}
	}

	err = zp.Close()

	return
}

func copyFileInto(path string, w io.Writer) error {
	f, err := os.Open(path)
	if err != nil {
		return err
	}
	defer f.Close()

	if _, err = io.Copy(w, f); err != nil {
		return err
	}

	return nil
}