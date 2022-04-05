package xfiles

import (
	"archive/zip"
	"compress/flate"
	"errors"
	"io"
	"os"
	"path/filepath"
)

// ZipFiles creates a new zip file in the target directory (dirName) and copies
// the files in the given list into that zip.
//
// If the target zip file already exists in the target directory, an error will
// be returned.
func ZipFiles(dirName, zipName string, files []string) error {

	zipPath := filepath.Join(dirName, zipName)

	if exists, err := FileExists(zipPath); err != nil {
		return err
	} else if exists {
		return errors.New(zipPath + " already exists.")
	}

	if zipFile, err := os.Create(zipPath); err != nil {
		return err
	} else {
		// Close the zip file on return.
		defer func() {
			_ = zipFile.Close()
			if err != nil {
				_ = os.Remove(zipPath)
			}
		}()

		return zipFiles(zipFile, files)
	}
}

func zipFiles(zipFile *os.File, files []string) error {
	// Open zip writer.
	z := zip.NewWriter(zipFile)

	// Set compression to "best"
	z.RegisterCompressor(zip.Deflate, func(w io.Writer) (io.WriteCloser, error) {
		return flate.NewWriter(w, flate.BestCompression)
	})

	for i := range files {
		// Open a writer for the specific file in the newly created zip.
		if zf, err := z.Create(filepath.Base(files[i])); err != nil {
			return err
		} else {
			// Copy the contents of the target file into the zip handle writer.
			if err = copyFileInto(files[i], zf); err != nil {
				return err
			}
		}
	}

	// Flush the writer
	if err := z.Flush(); err != nil {
		return err
	}

	return nil
}

func copyFileInto(path string, w io.Writer) error {
	f, err := os.Open(path)
	if err != nil {
		return err
	}
	defer f.Close()

	if _, err = io.Copy(w, f); err != nil {
		return err
	}

	return nil
}
