package model

import (
	"github.com/francoispqt/gojay"
	"io/ioutil"
	"os"
	"path/filepath"
)

const (
	// metaFileName is the name of the metadata file that will be written out into
	// the report job directory.
	//
	// This file is consumed by the MBlast service to determine the files relevant
	// to the report job.
	metaFileName = "meta.json"

	// metaJsonKey is the file list key written to the metadata JSON object that
	// will be written out to file.
	metaJsonKey = "files"
)

// FileList is an alias over a slice of strings providing a JSON serialization
// implementation.
type FileList []string

func (f FileList) MarshalJSONArray(enc *gojay.Encoder) {
	for i := range f {
		enc.String(f[i])
	}
}

func (f FileList) IsNil() bool {
	return f == nil
}

// CreateMeta creates a new JobMeta instance wrapping all the files in the given
// target directory.
func CreateMeta(dir string) (meta JobMeta, err error) {
	files, err := ioutil.ReadDir(dir)
	if err != nil {
		return
	}

	meta.Dir = dir
	meta.Files = make([]string, len(files))

	for i := range files {
		meta.Files[i] = files[i].Name()
	}

	return
}

// JobMeta wraps the set of files in Dir in a serializable object usable to
// write out the job meta file used by the MBlast service.
type JobMeta struct {
	Dir   string
	Files FileList
}

// WriteToFile writes out this JobMeta instance to a metadata file to be
// consumed by the MBlast service.
func (j *JobMeta) WriteToFile() error {
	metaFile, err := os.Create(filepath.Join(j.Dir, metaFileName))
	if err != nil {
		return err
	}
	defer func() {
		_ = metaFile.Close()
	}()

	enc := gojay.BorrowEncoder(metaFile)
	defer enc.Release()

	return enc.EncodeObject(j)
}

func (j *JobMeta) MarshalJSONObject(enc *gojay.Encoder) {
	enc.ArrayKey(metaJsonKey, j.Files)
}

func (j *JobMeta) IsNil() bool {
	return j == nil
}
