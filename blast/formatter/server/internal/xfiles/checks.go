package xfiles

import (
	"errors"
	"os"
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
