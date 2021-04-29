package blast

import (
	"encoding/json"
	"os"
	"path/filepath"
	"time"

	"github.com/gorilla/mux"
	"github.com/sirupsen/logrus"
	"github.com/veupathdb/lib-go-blast/v1/pkg/blast"
	"github.com/veupathdb/lib-go-blast/v1/pkg/blast/field"
	"github.com/vulpine-io/midl-layers/request-id/short-id/v1/pkg/midlid"
	"github.com/vulpine-io/midl/v1/pkg/midl"

	"server/internal/config"
	"server/internal/mtx"
	"server/internal/server"
	"server/internal/server/middleware"
)

func NewBlastEndpoint(config *config.Config) *BlastEndpoint {
	return &BlastEndpoint{config}
}

type BlastEndpoint struct {
	config *config.Config
}

func (b *BlastEndpoint) Handle(req midl.Request) midl.Response {
	mtx.RecordRequest()

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
	config.SetQuery("query.txt")
	// Override format value to always output format 11
	config.SetFormat(field.Format{Type: field.FormatTypeBLASTArchiveASN1})
	// Output to a standard file
	config.SetOut("report.asn1")

	workDir := filepath.Join(b.config.OutDir, request.JobID)

	// Check that the workspace exists
	if _, err = os.Stat(workDir); err != nil {
		log.Debug("Failed to stat workspace directory: ", err.Error())
		return server.NewFailResponse(err.Error())
	}

	stdout, err := os.Create(filepath.Join(workDir, "log.txt"))
	if err != nil {
		log.Debug("Failed to create stdout log file: ", err.Error())
		return server.NewFailResponse(err.Error())
	}
	defer stdout.Close()

	stderr, err := os.Create(filepath.Join(workDir, "error.txt"))
	if err != nil {
		log.Debug("Failed to create stderr log file: ", err.Error())
		return server.NewFailResponse(err.Error())
	}
	defer stderr.Close()

	cmd := config.ToCLI()
	cmd.Dir = workDir
	cmd.Env = os.Environ()
	cmd.Stdout = stdout
	cmd.Stderr = stderr

	log.Infof("Starting command \"%s\"", cmd.Args)
	start := time.Now()
	err = cmd.Run()
	mtx.RecordJobTime(time.Since(start).Seconds())
	if err != nil {
		log.Debug("Command execution failed: ", err.Error())
		mtx.RecordFailedJob()
		return server.NewFailResponse(err.Error())
	}
	log.Info("Command completed successfully.")

	return server.NewSuccessResponse("success")
}

func (b *BlastEndpoint) Register(path string, r *mux.Router) {
	r.Handle(path, midl.JSONAdapter(
		midlid.NewRequestIdProvider(),
		new(BlastEndpoint),
	).AddWrappers(
		middleware.RequestLogger{},
		middleware.RequestTimer{},
	))
}
