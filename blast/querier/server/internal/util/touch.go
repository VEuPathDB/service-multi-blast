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
	return touchFile(filepath.Join(dir, successFlag))
}

// TouchFailedFlag creates a job success flag file.
func TouchFailedFlag(dir string) error {
	return touchFile(filepath.Join(dir, failedFlag))
}

func touchFile(fileName string) error {

	if file, err := os.Create(fileName); err != nil {
		return err
	} else {
		_ = file.Close()
		return nil
	}
}
