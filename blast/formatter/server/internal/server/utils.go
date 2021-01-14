package server

import (
	"fmt"
	"net/http"
	"os"
	"path/filepath"

	"github.com/gorilla/mux"
	"github.com/vulpine-io/midl/v1/pkg/midl"
)

// JobDirFilter filters out requests that for jobs that do not have a workspace
// directory.
//
// Jobs that are filtered out will be sent a 404 response.
func JobDirFilter(req midl.Request) midl.Response {
	params    := mux.Vars(req.RawRequest())
	jobHash   := params[jobIDParam]
	workspace := filepath.Join(config.mountPath, jobHash)
	reqID     := req.AdditionalContext()[reqIDKey].(string)

	// Verify a workspace exists for the given hash.
	stat, err := os.Stat(workspace)
	if err != nil {
		// Workspace does not exist, return 404
		if os.IsNotExist(err) {
			return midl.MakeResponse(
				http.StatusNotFound,
				New404Error(fmt.Sprintf(ErrfJobNotFound, jobHash), reqID),
			)
		}

		// Some other error occurred, return 500
		return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
	}

	// Verify that the path is actually a directory before we attempt to walk it.
	if !stat.IsDir() {
		return midl.MakeResponse(
			http.StatusInternalServerError,
			New500Error("report path exists but is not a directory", reqID),
		)
	}

	return nil
}
