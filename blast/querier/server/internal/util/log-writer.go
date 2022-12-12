package util

import (
	"io"

	"github.com/sirupsen/logrus"
)

// StdErrLogger constructs a pair of log writers that pass
// command output through to the standard logger.
func StdErrLogger(log *logrus.Entry) (out io.Writer) {
	return &stdWriter{fn: log.Error}
}

type stdWriter struct {
	fn func(...interface{})
}

func (s *stdWriter) Write(p []byte) (n int, err error) {
	s.fn(string(p))
	return len(p), nil
}
