package util

import (
	"os"
	"path/filepath"
)

const (
	successFlag = ".complete"
	failedFlag  = ".failed"
)

// TouchSuccessFlag creates a job success flag file.
func TouchSuccessFlag(dir string) error {
	return TouchFile(filepath.Join(dir, successFlag))
}

// TouchFailedFlag creates a job success flag file.
func TouchFailedFlag(dir string) error {
	return TouchFile(filepath.Join(dir, failedFlag))
}

func TouchFile(fileName string) error {

	if file, err := os.Create(fileName); err != nil {
		return err
	} else {
		_ = file.Close()
		return nil
	}
}
