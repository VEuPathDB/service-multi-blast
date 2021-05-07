package middleware

import (
	"github.com/teris-io/shortid"
	"github.com/vulpine-io/midl/v1/pkg/midl"
)

const KeyRequestID = "request-id"

type RequestIdentifier struct {}

func (RequestIdentifier) Request(req midl.Request) {
	reqID, _ := shortid.Generate()
	req.AdditionalContext()[KeyRequestID] = reqID
}

func (RequestIdentifier) Response(_ midl.Request, res midl.Response) midl.Response {
	return res
}

