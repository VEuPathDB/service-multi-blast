package server

import (
	"errors"
	"os"
	"path/filepath"

	"github.com/teris-io/shortid"
)

func getWorkspace(jobKey string) (string, error) {
	workspace := filepath.Join(config.WSMountPath, jobKey)

	if stat, err := os.Stat(workspace); err != nil {
		return "", err
	} else if !stat.IsDir() {
		return "", errors.New("workspace dir is a regular file")
	}

	return workspace, nil
}

func createTmpDir(root string) (path string, err error) {
	// Generate unique temp dir name
	id, err := shortid.Generate()
	if err != nil {
		return
	}

	// Create output directory
	path = filepath.Join(root, id)
	err = os.Mkdir(path, 0775)

	return
}