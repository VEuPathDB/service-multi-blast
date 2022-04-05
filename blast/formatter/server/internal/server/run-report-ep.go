package server

import (
	"bytes"
	"crypto/md5"
	"encoding/hex"
	"errors"
	"io"
	"io/ioutil"
	"os"
	"os/exec"
	"path/filepath"
	"server/internal/xfiles"

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

	cmd := job.Config.ToCLI()
	cmd.Path = "/blast/bin/" + cmd.Args[0]
	cmd.Stderr = os.Stderr

	log.Debug(cmd)

	outputDir := filepath.Join(workspace, job.ReportID)

	// If the directory already exists, this report has already been run.
	// If the directory does not exist, create it and run the report.
	if err = runIfNeeded(cmd, outputDir, log); err != nil {
		log.Error(err)
		if e := xfiles.WriteFailedFlag(outputDir); e != nil {
			log.Error(e)
		}
		return New500Error(err.Error())
	}

	log.Debug("Zipping command output.")
	err = zipDir(outputDir)
	if err != nil {
		log.Error(err.Error())
		if e := xfiles.WriteFailedFlag(outputDir); e != nil {
			log.Error(e)
		}
		return New500Error(err.Error())
	}

	log.Debug("Writing out meta.json")

	if err = WriteMeta(outputDir); err != nil {
		log.Error(err.Error())
		if e := xfiles.WriteFailedFlag(outputDir); e != nil {
			log.Error(e)
		}
		return New500Error(err.Error())
	}

	if e := xfiles.WriteCompletedFlag(outputDir); e != nil {
		log.Error(e)
	}

	return New200Response("Job completed successfully")
}

// zipDir zips the contents of the given directory.
func zipDir(path string) error {
	if matches, err := filepath.Glob(filepath.Join(path, "*")); err != nil {
		return err
	} else {
		if err := xfiles.ZipFiles(path, reportExportName, matches); err != nil {
			return err
		}
	}

	return nil
}

// OutputName returns a file name with an appropriate file extension for the
// given file kind.
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

	if stat, err := os.Stat(outDir); err != nil {
		return err
	} else {
		if !stat.IsDir() {
			return errors.New("path " + outDir + " exists but is not a directory")
		}
	}

	if err := runCommand(cmd, outDir); err != nil {
		return err
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
