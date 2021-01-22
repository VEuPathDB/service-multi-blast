package server

import (
	"archive/zip"
	"compress/flate"
	"encoding/json"
	"io"
	"net/http"
	"os"
	"os/exec"
	"path/filepath"
	"strconv"
	"strings"

	"github.com/gorilla/mux"
	"github.com/sirupsen/logrus"
	"github.com/vulpine-io/midl-layers/request-id/short-id/v1/pkg/midlid"
	"github.com/vulpine-io/midl/v1/pkg/midl"
)

func RunReport6Endpoint(req midl.Request) midl.Response {
	return customReport("6", req)
}

func RunReport7Endpoint(req midl.Request) midl.Response {
	return customReport("7", req)
}

func RunReport10Endpoint(req midl.Request) midl.Response {
	return customReport("10", req)
}

func RunReport17Endpoint(req midl.Request) midl.Response {
	return customReport("17", req)
}

func customReport(fmt string, req midl.Request) midl.Response {
	if req.RawRequest().Body != nil {
		defer req.RawRequest().Body.Close()
	}

	params := mux.Vars(req.RawRequest())
	reqID := req.AdditionalContext()[midlid.KeyRequestId].(string)
	log := logrus.WithField(midlid.KeyRequestId, reqID)

	kindNum, err := strconv.Atoi(fmt)
	if err != nil {
		log.Info("Rejecting request for non-numeric report type")
		return midl.MakeResponse(http.StatusNotFound, New404Error("No report format found with the given id", reqID))
	}

	workspace, err := getWorkspace(params[jobIDParam])
	if err != nil {
		log.Error(err.Error())
		return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
	}

	outputDir, err := createTmpDir(workspace)
	if err != nil {
		log.Error(err.Error())
		return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
	}

	rawInput := req.Body()
	if req.Error() != nil {
		log.Info("Rejecting request for request error: ", req.Error())
		return midl.MakeResponse(http.StatusBadRequest, New400Error(req.Error().Error(), reqID))
	}
	if len(rawInput) > 0 {
		body := new(runRequestBody)

		req.ProcessBody(midl.BodyProcessorFunc(func(bytes []byte) error {
			return json.Unmarshal(bytes, body)
		}))
		if req.Error() != nil {
			log.Info("Rejecting request for deserialization error: ", req.Error())
			return midl.MakeResponse(http.StatusBadRequest, New400Error(req.Error().Error(), reqID))
		}

		if len(body.Delim) > 0 {
			fmt += " delim=" + body.Delim
		}
		if len(body.Fields) > 0 {
			fmt += " " + strings.Join(body.Fields, " ")
		}
	}

	if err = runCommand(fmt, outputName(kindNum), outputDir); err != nil {
		log.Error(err.Error())
		return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
	}

	zip, err := zipDir(outputDir)
	if err != nil {
		log.Error(err.Error())
		return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
	}
	res := midl.MakeResponse(http.StatusOK, zip)
	res.Callback(func() {
		_ = zip.Close()
		_ = os.RemoveAll(outputDir)
	})

	return res
}

func RunReportEndpoint(req midl.Request) midl.Response {
	if req.RawRequest().Body != nil {
		defer req.RawRequest().Body.Close()
	}

	params := mux.Vars(req.RawRequest())
	reqID := req.AdditionalContext()[midlid.KeyRequestId].(string)
	kind := params[reportTypeParam]
	log := logrus.WithField(midlid.KeyRequestId, reqID)

	log.Debug("Handling request for report type ", kind)

	kindNum, err := strconv.Atoi(kind)
	if err != nil {
		log.Info("Rejecting request for non-numeric report type")
		return midl.MakeResponse(http.StatusNotFound, New404Error("No report format found with the given id", reqID))
	}

	workspace, err := getWorkspace(params[jobIDParam])
	if err != nil {
		log.Error(err.Error())
		return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
	}

	outputDir, err := createTmpDir(workspace)
	if err != nil {
		log.Error(err.Error())
		return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
	}

	outFile := outputName(kindNum)
	if err = runCommand(kind, outFile, outputDir); err != nil {
		log.Error(err.Error())
		return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
	}

	log.Debug("Zipping command output.")
	zip, err := zipDir(outputDir)
	if err != nil {
		log.Error(err.Error())
		return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
	}
	res := midl.MakeResponse(http.StatusOK, zip)
	res.Callback(func() {
		log.Debug("Removing temp dir: ", outputDir)
		_ = zip.Close()
		_ = os.RemoveAll(outputDir)
	})
	res.AddHeader("Content-Type", "application/zip")

	return res
}

type runRequestBody struct {
	Fields []string `json:"fields"`
	Delim  string   `json:"delim"`
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
		tf, err = zp.Create(filepath.Base(matches[i]))
		if err != nil {
			return
		}

		if err = copyFileInto(matches[i], tf); err != nil {
			return
		}
	}

	if err = zp.Flush(); err != nil {
		return
	}

	if err = zp.Close(); err != nil {
		return
	}

	_, err = file.Seek(0, io.SeekStart)

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

func outputName(kind int) string {
	out := "report"

	switch kind {
	case 0, 1, 2, 3, 4, 18:
		out += ".txt"
	case 5, 14, 16:
		out += ".xml"
	case 6, 7:
		out += ".tsv"
	case 8:
		out += ".txt.asn1"
	case 9, 11:
		out += ".bin.asn1"
	case 10:
		out += ".csv"
	case 12, 13, 15:
		out += ".json"
	case 17:
		out += ".sam"
	}

	return out
}

func runCommand(fmt, out, dir string) (err error) {
	cmd := exec.Command(
		"/blast/bin/blast_formatter",
		"-archive", "../report.asn1",
		"-outfmt", fmt,
		"-out", out)
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr
	cmd.Dir = dir
	cmd.Env = os.Environ()

	return cmd.Run()
}