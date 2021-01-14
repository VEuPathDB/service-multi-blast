package server

import (
	"net/http"
	"os"
	"path/filepath"
	"strconv"
	"strings"

	"github.com/gorilla/mux"
	"github.com/vulpine-io/midl/v1/pkg/midl"
)

func ListReportsEndpoint(req midl.Request) midl.Response {
	params  := mux.Vars(req.RawRequest())
	reqID   := req.AdditionalContext()[reqIDKey].(string)

	workspace := filepath.Join(config.mountPath, params[jobIDParam])

	// Walk the directory and record all relevant paths along with their report
	// types.
	var reports map[int][]string
	err := filepath.Walk(workspace, func(path string, info os.FileInfo, err error) error {
		// There's an error in these parts, best steer clear
		if err != nil {
			return err
		}

		// Not a file, not interested
		if info.IsDir() {
			return nil
		}

		baseName := filepath.Base(path)
		dirName  := filepath.Dir(path)

		// If the parent directory name doesn't start with the report prefix, then
		// move on.
		if !strings.HasPrefix(dirName, reportDirPrefix) {
			return nil
		}

		// Pop the report type value off the directory name.
		value, err := strconv.Atoi(dirName[len(reportDirPrefix):])
		if err != nil {
			return err
		}

		// Record the file
		if _, ok := reports[value]; ok {
			reports[value] = append(reports[value], baseName)
		} else {
			reports[value] = []string{baseName}
		}

		return nil
	})
	if err != nil {
		return midl.MakeResponse(http.StatusInternalServerError, New500Error(err.Error(), reqID))
	}

	return midl.MakeResponse(http.StatusOK, reports)
}
