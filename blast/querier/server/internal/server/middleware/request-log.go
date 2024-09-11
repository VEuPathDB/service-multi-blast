package middleware

import (
	"github.com/sirupsen/logrus"
	"github.com/vulpine-io/midl-layers/request-id/short-id/v1/pkg/midlid"
	"github.com/vulpine-io/midl/v1/pkg/midl"
)

const KeyLogger = "logger"

type RequestLogger struct{}

func (RequestLogger) Request(req midl.Request) {
	req.AdditionalContext()[KeyLogger] = logrus.WithFields(logrus.Fields{
		midlid.KeyRequestId: req.AdditionalContext()[midlid.KeyRequestId],
		"request-path":      req.RawRequest().URL.Path,
	})
}

func (RequestLogger) Response(req midl.Request, res midl.Response) midl.Response {
	req.AdditionalContext()[KeyLogger].(*logrus.Entry).
		Infof("Returning code %d", res.Code())
	return res
}
