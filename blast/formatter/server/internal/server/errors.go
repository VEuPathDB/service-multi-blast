package server

import (
	"fmt"
	"strings"

	"github.com/vulpine-io/httpx/v1/pkg/httpx/header"
	"github.com/vulpine-io/midl/v1/pkg/midl"
)

const (
	errBody = `{"type":"permanent-failure","message":"%s"}`
)

func New400Error(message string) midl.Response {
	return makeErrBody(400, message)
}

func New500Error(message string) midl.Response {
	return makeErrBody(500, message)
}

func makeErrBody(code int, msg string) midl.Response {
	return midl.MakeResponse(code, fmt.Sprintf(
		errBody,
		strings.ReplaceAll(msg, `"`, `\"`),
	)).SetHeader(header.ContentType, "application/json")
}
