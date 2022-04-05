package xfiles

import (
	"os"
	"path/filepath"
)

const (
	completeFileName = ".complete"
	failedFileName   = ".failed"
)

// WriteCompletedFlag writes out a completed file flag that indicates a report
// job completed successfully.
func WriteCompletedFlag(dirName string) error {
	return writeFlag(dirName, completeFileName)
}

// WriteFailedFlag writes out a failed file flag that indicates a report job
// failed.
func WriteFailedFlag(dirName string) error {
	return writeFlag(dirName, failedFileName)
}

func writeFlag(dirName, fileName string) error {
	if file, err := os.Create(filepath.Join(dirName, fileName)); err != nil {
		return err
	} else {
		if err = file.Close(); err != nil {
			return err
		}
	}

	return nil
}
