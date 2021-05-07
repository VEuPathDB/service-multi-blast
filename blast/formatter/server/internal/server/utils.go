package server

import (
	"errors"
	"os"
	"path/filepath"
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
