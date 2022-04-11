package server

import (
	"errors"
	"io"
	"os"
	"os/exec"
	"path/filepath"
	"server/internal/model"
	"server/internal/mtx"
	"server/internal/xfiles"
	"time"

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

	log.Trace("server.ReportEndpoint()")

	payload, err := DecodePayload(req.RawRequest().Body)
	if err != nil {
		return New400Error(err.Error())
	}

	return runReport(payload, log)
}

func runReport(job *api.JobPayload, log *logrus.Entry) midl.Response {
	workspace, err := getWorkspace(job.JobID)

	// Append blast job id and report job id to the log lines.
	log = log.WithField("job-id", job.JobID).WithField("report-id", job.ReportID)

	if err != nil {
		log.Error(err.Error())
		mtx.RecordFailedJob()
		return New500Error(err.Error())
	}

	job.Config.ArchiveFile.Set("../report.asn1")
	job.Config.OutFile.Set(OutputName(job.Config.Format.Type))

	cmd := job.Config.ToCLI()
	cmd.Path = "/blast/bin/" + cmd.Args[0]
	cmd.Stderr = os.Stderr

	log.Debug(cmd)

	outputDir := filepath.Join(workspace, job.ReportID)

	// Check to make sure the job has not already been completed.  This should
	// never happen, but if it does, we should not attempt to re-run this report
	// job.  Instead, fail the request.
	if exists, err := xfiles.CompletionFlagExists(outputDir); err != nil {
		log.Error(err)
		if e := xfiles.WriteFailedFlag(outputDir); e != nil {
			log.Error(e)
		}
		mtx.RecordFailedJob()
		return New500Error(err.Error())
	} else if exists {
		err := errors.New("attempted to re-run a report job in a completed workspace")
		log.Error(err)
		mtx.RecordFailedJob()
		return New500Error(err.Error())
	}

	// If the directory already exists, this report has already been run.
	// If the directory does not exist, create it and run the report.
	if err = runIfNeeded(cmd, outputDir, log); err != nil {
		log.Error(err)
		if e := xfiles.WriteFailedFlag(outputDir); e != nil {
			log.Error(e)
		}
		mtx.RecordFailedJob()
		return New500Error(err.Error())
	}

	log.Debug("Zipping command output.")
	err = zipDir(outputDir)
	if err != nil {
		log.Error(err.Error())
		if e := xfiles.WriteFailedFlag(outputDir); e != nil {
			log.Error(e)
		}
		mtx.RecordFailedJob()
		return New500Error(err.Error())
	}

	log.Debug("Writing out meta.json")

	if err = WriteMeta(outputDir); err != nil {
		log.Error(err.Error())
		if e := xfiles.WriteFailedFlag(outputDir); e != nil {
			log.Error(e)
		}
		mtx.RecordFailedJob()
		return New500Error(err.Error())
	}

	if e := xfiles.WriteCompletedFlag(outputDir); e != nil {
		log.Error(e)
	}

	return New200Response("Job completed successfully")
}

// zipDir zips the contents of the given directory.
func zipDir(path string) (err error) {
	var matches []string

	// Loop to wait for the report file if we get here before it's copied into the
	// workspace directory.
	//
	// Try a maximum of 10 times before bailing on the job (something is wrong if
	// we've waited a whole second and the report isn't here yet).
	for i := 0; i < 10; i++ {

		// Search for files in the workspace directory (will be empty until the
		// report is copied into the workspace).
		matches, err = filepath.Glob(filepath.Join(path, "*"))
		if err != nil {
			return err
		}

		// If we found 1 or more files in the workspace, then the file has been
		// copied in, and we can zip the report.
		//
		// The only things being zipped are the report output files, the `meta.json`
		// file and the completed flag file are created after this zip is done.
		if len(matches) > 0 {
			if err := xfiles.ZipFiles(path, reportExportName, matches); err != nil {
				return err
			}

			return nil
		}

		// If we are here, the workspace is empty.
		// Wait a tenth of a second before checking again.
		time.Sleep(100 * time.Millisecond)
	}

	// We waited a full second after the completion of the formatter and there is
	// still no report file in the workspace.
	return errors.New("no report file was found in workspace")
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

	start := time.Now()
	if err := runCommand(cmd, outDir); err != nil {
		return err
	}
	mtx.RecordJobTime(time.Since(start).Seconds())

	return nil
}

func runCommand(cmd *exec.Cmd, dir string) error {
	cmd.Dir = dir
	cmd.Env = os.Environ()
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr

	return cmd.Run()
}

// ---------------------------------------------------------------------------------------------- //

// WriteMeta collects a list of all the files in `dir` and writes out a JSON
// file with a list of those files.
func WriteMeta(dir string) error {
	if meta, err := model.CreateMeta(dir); err != nil {
		return err
	} else {
		if err = meta.WriteToFile(); err != nil {
			return err
		}
	}

	return nil
}

func DecodePayload(body io.Reader) (*api.JobPayload, error) {
	payload := api.NewJobPayload()

	dec := gojay.BorrowDecoder(body)
	defer dec.Release()

	return payload, dec.Object(payload)
}
