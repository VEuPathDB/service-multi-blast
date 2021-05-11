package server

import "github.com/vulpine-io/midl/v1/pkg/midl"

type Response struct {
	Status  string `json:"status"`
	Message string `json:"message"`
}

func NewSuccessResponse(msg string) midl.Response {
	return midl.NewResponse().
		SetCode(200).
		SetBody(Response{"success", msg})
}

func NewFailResponse(msg string) midl.Response {
	return midl.NewResponse().
		SetCode(500).
		SetBody(Response{"permanent-failure", msg})
}