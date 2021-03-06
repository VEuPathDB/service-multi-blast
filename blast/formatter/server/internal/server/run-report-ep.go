package server

import (
	"archive/zip"
	"bytes"
	"compress/flate"
	"crypto/md5"
	"encoding/hex"
	"io"
	"io/ioutil"
	"os"
	"os/exec"
	"path/filepath"

	"github.com/francoispqt/gojay"
	"github.com/sirupsen/logrus"
	"github.com/veupathdb/lib-go-blast/v2/pkg/blast/field"
	"github.com/vulpine-io/midl-layers/request-id/short-id/v1/pkg/midlid"
	"github.com/vulpine-io/midl/v1/pkg/midl"
	"server/pkg/api"
)

func ReportEndpoint(req midl.Request) midl.Response {
	reqID := req.AdditionalContext()[midlid.KeyRequestId].(string)
	log := logrus.WithField(midlid.KeyRequestId, reqID)

	log.Trace("server.ReportEndpoint(")

	payload, err := DecodePayload(req.RawRequest().Body)
	if err != nil {
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

	job.Config.ArchiveFile.Set("../report.asn1")
	job.Config.OutFile.Set(OutputName(job.Config.Format.Type))

	cmd  := job.Config.ToCLI()
	cmd.Path = "/blast/bin/" + cmd.Args[0]
	cmd.Stderr = os.Stderr

	log.Debug(cmd)

	outputDir := filepath.Join(workspace, job.ReportID)

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

	log.Debug("Writing out meta.json")

	if err = WriteMeta(outputDir); err != nil {
		log.Error(err.Error())
		return New500Error(err.Error())
	}

	return New200Response("Job completed successfully")
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

func OutputName(kind field.FormatType) string {
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
func runIfNeeded(cmd *exec.Cmd, outDir string, log *logrus.Entry) error {
	log.Tracef("server.runIfNeeded(cmd=..., outDir=%s, log=...)", outDir)

	_, err := os.Stat(outDir)
	if err != nil {
		if !os.IsNotExist(err) {
			return err
		}

		if err = os.Mkdir(outDir, 0775); err != nil {
			return err
		}

		if err = runCommand(cmd, outDir); err != nil {
			return err
		}
	}

	return nil
}

func runCommand(cmd *exec.Cmd, dir string) error {
	cmd.Dir = dir
	cmd.Env = os.Environ()
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr

	return cmd.Run()
}

func hashCommand(cmd *exec.Cmd) string {
	return hashSlice(cmd.Args)
}

func hashSlice(val []string) string {
	size := 0
	for i := range val {
		size += len(val[i])
	}

	buf := bytes.NewBuffer(make([]byte, size))

	for i := range val {
		buf.WriteString(val[i])
	}

	hash := md5.Sum(buf.Bytes())

	return hex.EncodeToString(hash[:])
}

type meta struct {
	files fileList
}

func (m *meta) MarshalJSONObject(enc *gojay.Encoder) {
	enc.ArrayKey("files", m.files)
}

func (m *meta) IsNil() bool {
	return m == nil
}

type fileList []string

func (f fileList) MarshalJSONArray(enc *gojay.Encoder) {
	for i := range f {
		enc.String(f[i])
	}
}

func (f fileList) IsNil() bool {
	return f == nil
}

// ---------------------------------------------------------------------------------------------- //

// WriteMeta collects a list of all the files in `dir` and writes out a JSON
// file with a list of those files.
func WriteMeta(dir string) error {
	files, err := ioutil.ReadDir(dir)
	if err != nil {
		return err
	}

	meta := meta{}
	meta.files = make([]string, 0, len(files))

	for i := range files {
		meta.files = append(meta.files, files[i].Name())
	}

	metaFile, err := os.Create(filepath.Join(dir, "meta.json"))
	if err != nil {
		return err
	}
	defer metaFile.Close()

	enc := gojay.BorrowEncoder(metaFile)
	defer enc.Release()

	return enc.EncodeObject(&meta)
}

func DecodePayload(body io.Reader) (*api.JobPayload, error) {
	payload := api.NewJobPayload()

	dec := gojay.BorrowDecoder(body)
	defer dec.Release()

	return payload, dec.Object(payload)
}