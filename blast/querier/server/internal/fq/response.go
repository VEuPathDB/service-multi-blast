package fq

type Response struct {
	Status  Status `json:"status"`
	Message string `json:"message"`
}

func SuccessResponse(msg string) Response {
	return Response{Status: StatusSuccess, Message: msg}
}

func PermanentFailureResponse(msg string) Response {
	return Response{Status: StatusPermanentFailure, Message: msg}
}