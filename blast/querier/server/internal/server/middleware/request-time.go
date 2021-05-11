package middleware

import (
	"time"

	"github.com/sirupsen/logrus"
	"github.com/vulpine-io/midl/v1/pkg/midl"
)

const keyTimer = "request-start"

type RequestTimer struct {}

func (r RequestTimer) Request(req midl.Request) {
	req.AdditionalContext()[keyTimer] = time.Now()
	req.AdditionalContext()[KeyLogger].(*logrus.Entry).
		Trace("Request timing started.")
}

func (r RequestTimer) Response(req midl.Request, res midl.Response) midl.Response {
	req.AdditionalContext()[KeyLogger].(*logrus.Entry).
		Infof("Request completed in %s", time.Since(req.AdditionalContext()[keyTimer].(time.Time)))

	return res
}



