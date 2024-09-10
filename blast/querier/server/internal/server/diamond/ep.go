package diamond

import (
	"encoding/json"
	"os"
	"os/exec"
	"path/filepath"
	"time"

	"github.com/gorilla/mux"
	"github.com/sirupsen/logrus"
	"github.com/vulpine-io/midl/v1/pkg/midl"
	"github.com/vulpine-io/split-pipe/v1/pkg/spipe"

	"server/internal/config"
	"server/internal/mtx"
	"server/internal/server"
	"server/internal/server/middleware"
	"server/internal/util"
)

func NewDiamondEndpoint(conf config.Config) *Endpoint {
	return &Endpoint{config: conf}
}

type Endpoint struct {
	config config.Config
}

func (e Endpoint) Handle(req midl.Request) midl.Response {
	mtx.RecordRequest()

	// Get logger with context.
	log := req.AdditionalContext()[middleware.KeyLogger].(*logrus.Entry)

	log.Debug("deserializing request")
	request := new(Request)

	if err := json.Unmarshal(req.Body(), request); err != nil {
		log.Debug("failed to deserialize request: ", err.Error())
		return server.NewFailResponse(err.Error())
	}

	log = log.WithField("job-id", request.JobID)

	workDir := filepath.Join(e.config.OutDir, request.JobID)
	log.Debug("work directory: ", workDir)

	// Check that the workspace exists
	if _, err := os.Stat(workDir); err != nil {
		log.Error("failed to stat workspace directory: ", err.Error())
		return server.NewFailResponse(err.Error())
	}

	stdout, err := os.Create(filepath.Join(workDir, "log.txt"))
	if err != nil {
		log.Error("failed to create stdout log file: ", err.Error())
		// Create failed flag
		_ = util.TouchFailedFlag(workDir)
		return server.NewFailResponse(err.Error())
	}
	defer stdout.Close()

	errFile, err := os.Create(filepath.Join(workDir, "error.txt"))
	if err != nil {
		log.Debug("Failed to create stderr log file: ", err.Error())
		// Create failed flag
		_ = util.TouchFailedFlag(workDir)
		return server.NewFailResponse(err.Error())
	}
	defer errFile.Close()

	stderr := spipe.NewSplitWriter(errFile, util.StdErrLogger(log))
	stderr.IgnoreErrors(true)

	args := make([]string, len(request.Params)+5)
	args[0] = request.Tool
	args[1] = "--out"
	args[2] = "report.pairwise"
	args[3] = "--query"
	args[4] = "query.txt"
	copy(args[5:], request.Params)

	cmd := exec.Command("/diamond/bin/diamond", args...)
	cmd.Env = os.Environ()
	cmd.Dir = workDir
	cmd.Stdout = stdout
	cmd.Stderr = stderr

	log.Infof("starting command: %s", cmd.Args)

	start := time.Now()
	err = util.RunCommand(cmd, log)
	mtx.RecordDiamondTime(time.Since(start).Seconds())

	if err != nil {
		log.Error("command execution failed: ", err.Error())
		mtx.RecordDiamondFailure()
		// Create failed flag
		_ = util.TouchFailedFlag(workDir)
		return server.NewFailResponse(err.Error())
	}

	log.Info("command completed successfully")

	// Create success flag
	_ = util.TouchSuccessFlag(workDir)

	return server.NewSuccessResponse("success")
}

func (e Endpoint) Register(path string, r *mux.Router) {
	r.Handle(path, midl.JSONAdapter(e).
		AddWrappers(
			middleware.RequestIdentifier{},
			middleware.RequestLogger{},
			middleware.RequestTimer{},
		))
}
