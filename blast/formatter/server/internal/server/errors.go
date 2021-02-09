package server

import (
	"fmt"
	"strings"

	"github.com/vulpine-io/httpx/v1/pkg/httpx/header"
	"github.com/vulpine-io/midl/v1/pkg/midl"
)

const (
	ErrfJobNotFound = "no job found with the hash %s"
	errBody         = `{"type":%d,"message":"%s","requestID":"%s"}`
)

type Error struct {
	Code      uint16 `json:"type"`
	Message   string `json:"message"`
	RequestID string `json:"requestID"`
}

func New400Error(message, requestID string) midl.Response {
	return makeErrBody(400, message, requestID)
}

func New404Error(message, requestID string) midl.Response {
	return makeErrBody(404, message, requestID)
}

func New500Error(message, requestID string) midl.Response {
	return makeErrBody(500, message, requestID)
}

func makeErrBody(code int, msg, requestID string) midl.Response {
	return midl.MakeResponse(code, fmt.Sprintf(
		errBody,
		code,
		strings.ReplaceAll(msg, `"`, `\"`),
		requestID,
	)).SetHeader(header.ContentType, "application/json")
}
