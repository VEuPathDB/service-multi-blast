package server

import (
	"github.com/vulpine-io/httpx/v1/pkg/httpx/header"
	"github.com/vulpine-io/midl/v1/pkg/midl"
)

const (
	StatusFailed  = "permanent-failure"
	StatusSuccess = "success"
)

type err struct {
	Status  string `json:"status"`
	Message string `json:"message"`
}

func New200Response(message string) midl.Response {
	return makeErrBody(200, StatusSuccess, message)
}

func New400Error(message string) midl.Response {
	return makeErrBody(400, StatusFailed, message)
}

func New500Error(message string) midl.Response {
	return makeErrBody(500, StatusFailed, message)
}

func makeErrBody(code int, stat, msg string) midl.Response {
	return midl.MakeResponse(code, err{stat, msg}).
		SetHeader(header.ContentType, "application/json")
}
