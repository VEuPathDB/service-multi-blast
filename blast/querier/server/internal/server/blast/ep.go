package blast

import (
	"encoding/json"
	"log"
	"os"
	"os/exec"
	"path/filepath"
	"server/internal/util"
	"time"

	"github.com/gorilla/mux"
	"github.com/sirupsen/logrus"
	"github.com/veupathdb/lib-go-blast/v2/pkg/blast"
	"github.com/veupathdb/lib-go-blast/v2/pkg/blast/field"
	"github.com/vulpine-io/midl/v1/pkg/midl"

	"server/internal/config"
	"server/internal/mtx"
	"server/internal/server"
	"server/internal/server/middleware"
)

func NewBlastEndpoint(conf *config.Config) *Endpoint {
	return &Endpoint{config: conf}
}

type Endpoint struct {
	config *config.Config
}

func (b Endpoint) Handle(req midl.Request) midl.Response {
	// Increment request counter.
	mtx.RecordRequest()

	log.Println("Config: ", b.config)

	// Create logger with context.
	log := req.AdditionalContext()[middleware.KeyLogger].(*logrus.Entry)

	log.Debug("Deserializing request.")
	request := new(server.Request)
	if err := json.Unmarshal(req.Body(), request); err != nil {
		log.Debug("Failed to deserialize request: ", err.Error())
		return server.NewFailResponse(err.Error())
	}

	log = log.WithField("job-id", request.JobID)

	log.Debug("Parsing blast configuration.")
	config, err := blast.DecodeConfig(request.Tool, request.Config)
	if err != nil {
		log.Debug("Failed to parse blast config: ", err.Error())
		return server.NewFailResponse(err.Error())
	}

	// Query file is created outside of this service.
	config.(blast.BlastQueryConfig).GetQueryFile().Set("query.txt")

	// Override format value to always output format 11
	config.SetFormat(field.Format{Type: field.FormatTypeBlastArchiveASN1})

	// Output to a standard file
	config.GetOutFile().Set("report.asn1")

	workDir := filepath.Join(b.config.OutDir, request.JobID)
	log.Debug("Work directory: ", workDir)

	// Check that the workspace exists
	if _, err = os.Stat(workDir); err != nil {
		log.Debug("Failed to stat workspace directory: ", err.Error())
		return server.NewFailResponse(err.Error())
	}

	stdout, err := os.Create(filepath.Join(workDir, "log.txt"))
	if err != nil {
		log.Debug("Failed to create stdout log file: ", err.Error())

		// Create failed flag
		_ = util.TouchFailedFlag(workDir)

		return server.NewFailResponse(err.Error())
	}
	defer stdout.Close()

	stderr, err := os.Create(filepath.Join(workDir, "error.txt"))
	if err != nil {
		log.Debug("Failed to create stderr log file: ", err.Error())

		// Create failed flag
		_ = util.TouchFailedFlag(workDir)

		return server.NewFailResponse(err.Error())
	}
	defer stderr.Close()

	cmd := config.ToCLI()
	cmd.Path = "/blast/bin/" + cmd.Args[0]
	log.Debugf(`Command "%s"`, cmd.Args[0])
	cmd.Env = os.Environ()
	cmd.Dir = workDir
	cmd.Stdout = stdout
	cmd.Stderr = stderr

	log.Infof("Starting command \"%s\"", cmd.Args)
	start := time.Now()
	err = cmd.Run()
	mtx.RecordJobTime(time.Since(start).Seconds())
	if err != nil {
		log.Debug("Command execution failed: ", err.Error())

		// Increment job failure metric
		mtx.RecordFailedJob()

		// Create failed flag
		_ = util.TouchFailedFlag(workDir)

		return server.NewFailResponse(err.Error())
	}
	log.Info("Command completed successfully.")

	// Create success flag
	_ = util.TouchSuccessFlag(workDir)

	return server.NewSuccessResponse("success")
}

func (b Endpoint) Register(path string, r *mux.Router) {
	r.Handle(path, midl.JSONAdapter(b).
		AddWrappers(
			middleware.RequestIdentifier{},
			middleware.RequestLogger{},
			middleware.RequestTimer{},
		))
}
func isCommandAvailable(name string) bool {
	cmd := exec.Command("command", "-v", name)
	if err := cmd.Run(); err != nil {
		return false
	}
	return true
}
