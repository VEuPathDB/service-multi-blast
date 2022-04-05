package util

import (
	"os"
	"path/filepath"
)

const (
	successFlag = ".complete"
	failedFlag  = ".failed"
)

func TouchSuccessFlag(dir string) error {
	return touchFile(filepath.Join(dir, successFlag))
}

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
