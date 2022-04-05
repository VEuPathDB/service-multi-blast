package xfiles

import (
	"errors"
	"os"
	"path/filepath"
)

func FileExists(path string) (bool, error) {
	if _, err := os.Stat(path); err == nil {
		return true, nil
	} else {
		if errors.Is(err, os.ErrNotExist) {
			return false, nil
		}

		return false, err
	}
}

// CompletionFlagExists tests whether the given report workspace directory
// contains either a successful or failed completion flag file.
func CompletionFlagExists(dir string) (bool, error) {
	// Check for the successful completion flag.
	if exists, err := FileExists(filepath.Join(dir, completeFileName)); err != nil {
		// File stat failed.
		return false, err
	} else if exists {
		// If the successful completion flag exists, return true.
		return true, nil
	}

	// The successful completion flag does not exist.

	// Check for the failed completion flag.
	if exists, err := FileExists(filepath.Join(dir, failedFileName)); err != nil {
		// File stat failed.
		return false, err
	} else {
		// Return whether the failed completion flag exists.
		return exists, nil
	}
}
