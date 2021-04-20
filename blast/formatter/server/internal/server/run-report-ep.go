package server

import (
	"archive/zip"
	"compress/flate"
	"crypto/md5"
	"encoding/hex"
	"io"
	"net/http"
	"os"
	"path/filepath"

	"github.com/francoispqt/gojay"
	"github.com/sirupsen/logrus"
	"github.com/vulpine-io/midl-layers/request-id/short-id/v1/pkg/midlid"
	"github.com/vulpine-io/midl/v1/pkg/midl"
	"server/pkg/api"
	"server/pkg/bfmt"
	"server/pkg/blast"
)

func ReportEndpoint(req midl.Request) midl.Response {
	reqID := req.AdditionalContext()[midlid.KeyRequestId].(string)
	log   := logrus.WithField(midlid.KeyRequestId, reqID)

	log.Trace("server.ReportEndpoint(")

	dec     := gojay.BorrowDecoder(req.RawRequest().Body)
	payload := api.NewJobPayload()

	if err := dec.DecodeObject(payload); err != nil {
		return New400Error(err.Error())
	}

	return runReport(payload, log)
}


func runReport(job *api.JobPayload, log *logrus.Entry) midl.Response {
	workspace, err := getWorkspace(job.JobID)
	if err != nil {
		log.Error(err.Error())
		return New500Error(err.Error())
	}

	cmd       := job.ToCmd()
	hash, err := hashCommand(cmd)
	outputDir := filepath.Join(workspace, hash)

	// If the directory already exists, this report has already been run.
	// If the directory does not exist, create it and run the report.
	if err = runIfNeeded(cmd, outputDir, log); err != nil {
		log.Error(err)
		return New500Error(err.Error())
	}

	log.Debug("Zipping command output.")
	err = zipDir(outputDir)
	if err != nil {
		log.Error(err.Error())
		return New500Error(err.Error())
	}
	res := midl.MakeResponse(http.StatusOK, `{"status":"success","message":"Job completed successfully"}`)
	res.AddHeader("Content-Type", "application/json")

	return res
}

func zipDir(path string) (err error) {
	matches, err := filepath.Glob(filepath.Join(path, "*"))
	if err != nil {
		return
	}

	file, err := os.Create(filepath.Join(path, reportExportName))
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

func outputName(kind blast.FormatType) string {
	out := "report"

	switch kind {
	case 0, 1, 2, 3, 4, 17, 18:
		out += ".txt"
	case 5, 14, 16:
		out += ".xml"
	case 6, 7:
		out += ".tsv"
	case 8, 9, 11:
		out += ".asn1"
	case 10:
		out += ".csv"
	case 12, 13, 15:
		out += ".json"
	}

	return out
}

// runIfNeeded runs the blast_formatter job if it has not already been run.
func runIfNeeded(cmd *bfmt.CLICall, outDir string, log *logrus.Entry) error {
	log.Tracef("server.runIfNeeded(cmd=..., outDir=%s, log=...)", outDir)

	_, err := os.Stat(outDir)
	if err != nil {
		if !os.IsNotExist(err) {
			return err
		}

		if err = os.Mkdir(outDir, 0775); err != nil {
			return err
		}

		outFile := outputName(cmd.OutFormat.Type)
		if err = runCommand(cmd, outFile, outDir); err != nil {
			return err
		}
	}

	return nil
}

func runCommand(cmd *bfmt.CLICall, out, dir string) error {
	cmd.Archive = "../report.asn1"
	cmd.WorkDirectory = dir
	cmd.OutFile = out
	cmd.Environment = os.Environ()

	return cmd.Execute(os.Stdout, os.Stderr)
}

func hashCommand(cmd *bfmt.CLICall) (string, error) {
	raw, err := cmd.ToCLIString()
	if err != nil {
		return "", err
	}

	hash := md5.Sum([]byte(raw))

	return hex.EncodeToString(hash[:]), nil
}